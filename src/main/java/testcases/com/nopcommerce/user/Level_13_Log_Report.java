package testcases.com.nopcommerce.user;

import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.nopcommerce.admin.AdminDashboardPageObjects;
import actions.pageObjects.nopcommerce.admin.AdminLoginPageObjects;
import actions.pageObjects.nopcommerce.user.UserAccountPageObjects;
import actions.pageObjects.nopcommerce.user.UserHomePageObjects;
import actions.pageObjects.nopcommerce.user.UserLoginPageObjects;
import actions.pageObjects.nopcommerce.user.UserRegisterPageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Level_13_Log_Report extends BaseTest {
    @Parameters({"browser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        log.info("Pre-condition info: Open browser '" + browserName + "'");

//        driver = getBrowserDriver(browserName, environmentName);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--user-data-dir=/Users/mastery/Library/Application Support/Google/Chrome/");
        chromeOptions.addArguments("--profile-directory=Profile 8");
        driver = new ChromeDriver(chromeOptions);

        userHomePage = PageGeneratorManager.getUserHomePage(driver);
        userAccountPage = PageGeneratorManager.getUserMyAccountPage(driver);

        adminEmailAddress = "admin@yourstore.com";
        adminPassword = "admin";
        password = "123456aA@@";
        userEmailAddress = "test" + generateFakeNumber() + "@mail.com";
    }

    @Test
    public void Role_01_Admin() throws IOException {
        log.info("Admin_01_Login - Step 01: Open admin login page");
        adminLoginPage = userHomePage.openAdminLoginPage();

        log.info("Admin_01_Login - Step 02: Login with email: " + adminEmailAddress + "password: " + adminPassword);
        adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);

        log.info("Admin_01_Login - Step 03: Verify dashboard link is displayed");
        Assert.assertTrue(adminDashboardPage.isDashboardLinkDisplayed());

        log.info("Admin_01_Login - Step 04: Click to 'Logout' link");
        adminDashboardPage.clickToElementByJS(driver, GlobalConstants.LOGOUT_LINK);
    }

    @Test
    public void Role_02_Admin() {
        log.info("Admin_02_Login - Step 01: Open admin login page");
        adminLoginPage = userHomePage.openAdminLoginPage();

        log.info("Admin_02.1_Login - Step 01.1: Click to 'Logout' link");
        adminDashboardPage.clickToElementByJS(driver, GlobalConstants.LOGOUT_LINK);

        log.info("Admin_02_Login - Step 02: Login with email: " + adminEmailAddress + "password: " + adminPassword);
        adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);

        log.info("Admin_02_Login - Step 03: Verify dashboard link is displayed");
        Assert.assertTrue(adminDashboardPage.isDashboardLinkDisplayed());

        log.info("Admin_02_Login - Step 04: Click to 'Logout' link");
        adminDashboardPage.clickToElementByJS(driver, GlobalConstants.LOGOUT_LINK);
    }

    @AfterClass
    public void afterClass() {
        log.info("Post-condition: Close driver");
        driver.quit();
    }

    public void readFile() throws IOException {
        String testFile = "/Users/mastery/Documents/selenium/selenium/src/main/resources/dataTestNopCommerce.txt";
        FileReader fileReader = new FileReader(testFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String Content = "";
        while ((Content = bufferedReader.readLine()) != null) {
            String[] index = Content.split(";");
            registerEmail = index[0];
            password = index[1];
        }
    }

    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }

    private WebDriver driver;
    String userEmailAddress, adminEmailAddress, adminPassword, registerEmail, password;
    private UserLoginPageObjects userLoginPage;
    private UserRegisterPageObjects registerPage;
    private UserHomePageObjects userHomePage;
    private UserAccountPageObjects userAccountPage;
    private AdminDashboardPageObjects adminDashboardPage;
    private AdminLoginPageObjects adminLoginPage;
}
