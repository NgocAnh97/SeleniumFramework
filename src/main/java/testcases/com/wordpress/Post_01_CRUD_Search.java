package testcases.com.wordpress;

import actions.commons.BaseTest;
import actions.pageObjects.wordpress.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;

public class Post_01_CRUD_Search extends BaseTest {
    WebDriver driver;
    int randomNumber = generateFakeNumber();
    private String adminEmail = "automationfc.vn@gmail.com";
    private String adminPassword = "d*4YKn68TWGhquZA7sUmcTUx";
    private String postTitleValue = "WordPress admin title " + randomNumber;
    private String postBodyValue = "WordPress admin body " + randomNumber;

    @Parameters({"browser", "adminUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserUrl(browserName, url);
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
        log.info("Pre-condition - Step 1: Click 'Log in' button");
        adminLoginPage.clickToLoginButtonInHeader();

        log.info("Pre-condition - Step 2: Enter to 'Email Address' textbox with the value: " + adminEmail);
        adminLoginPage.enterToEmailTextbox(adminEmail);
        log.info("Pre-condition - Step 3: Click 'Continue' button");
        adminLoginPage.clickToContinueButton();
        log.info("Pre-condition - Step 4: Enter to 'Password' textbox with the value: " + adminPassword);
        adminLoginPage.enterToPasswordTextbox(adminPassword);
        log.info("Pre-condition - Step 5: Click 'Log in' button");
        adminDashboardPage = adminLoginPage.clickToLoginButton();
    }

    @Test
    public void Post_01_Create_New_Post() {
        log.info("Create_Post - Step 1: Click 'My Home' in sidebar");
        adminDashboardPage.clickToHomeLink();

        log.info("Create_Post - Step 2: Click 'Posts' in sidebar");
        adminPostSearchPage = adminDashboardPage.clickToPostsLink();

        log.info("Create_Post - Step 3: Click 'Add new post' button");
        adminPostAddNewPage = adminPostSearchPage.clickToAddNewPostButton();

        log.info("Create_Post - Step 4: Enter to post title: " + postTitleValue);
        adminPostAddNewPage.enterToPostTitle(postTitleValue);

        log.info("Create_Post - Step 5: Enter to post body: " + postBodyValue);
        adminPostAddNewPage.enterToPostBody(postBodyValue);

        log.info("Create_Post - Step 6: Click 'Publish' button");
        adminPostAddNewPage.clickToPublishButton();

        log.info("Create_Post - Step 7: Verify 'Post published!' popup is displayed");
        adminPostAddNewPage.verifyPostPublishedPopup();

        log.info("Create_Post - Step 7b: Verify success message is displayed");
        verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post published."));
    }

    @Test
    public void Post_02_Search_And_View_Post() {
        log.info("Search_Post - Step 1: Click 'My Home' in sidebar");
        adminPostSearchPage = adminPostAddNewPage.clickToHomeLink();

        log.info("Search_Post - Step 1: Click 'Search' button");
        adminPostSearchPage.clickToSearchButton();

        log.info("Search_Post - Step 2: Enter to search textbox: " + postTitleValue);
        adminPostSearchPage.enterToSearchTextbox(postTitleValue);

        log.info("Search_Post - Step 3: Verify the Post information is displayed in Home page");
        adminPostSearchPage.verifySearchResult(postTitleValue);

        log.info("Search_Post - Step 4: Click to Post title");
        adminPostDetailPage = adminPostSearchPage.clickToPostTitle(postTitleValue);

        log.info("Search_Post - Step 5: Verify the Post information is displayed in Post detail page");
        adminPostDetailPage.verifyPostDetail(postTitleValue, postBodyValue);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }

    AdminLoginPO adminLoginPage;
    AdminPostAddNewPO adminPostAddNewPage;
    AdminPostSearchPO adminPostSearchPage;
    AdminDashboardPO adminDashboardPage;
    AdminPostDetailPO adminPostDetailPage;
}
