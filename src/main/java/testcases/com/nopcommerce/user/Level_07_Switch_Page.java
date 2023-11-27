package testcases.com.nopcommerce.user;

import actions.commons.BasePage;
import actions.commons.BaseTest;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.nopcommerce.user.UserAccountPageObjects;
import actions.pageObjects.nopcommerce.user.UserHomePageObjects;
import actions.pageObjects.nopcommerce.user.UserLoginPageObjects;
import actions.pageObjects.nopcommerce.user.UserRegisterPageObjects;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Random;

public class Level_07_Switch_Page extends BaseTest {
    private WebDriver driver;
    String emailAddress, registerEmail, passWord = "123456aA@@";
    String firstNameUpdate = "firstNameUpdate";
    String lastNameUpdate = "lastNameUpdate";
    String emailUpdate;
    String TestFile = "/Users/mastery/Documents/selenium/selenium/src/main/resources/dataTestNopCommerce.txt";
    private UserHomePageObjects homePage;
    private UserRegisterPageObjects registerPage;
    private UserLoginPageObjects loginPage;
    private UserAccountPageObjects myAccountPage;
    private BasePage addressesPage;
    private BasePage rewardPointPage;
    private BasePage myProductReviewPage;

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) throws IOException {
        driver = getBrowser(browserName);
        homePage = PageGeneratorManager.getUserHomePage(driver);
        loginPage = PageGeneratorManager.getUserLoginPage(driver);
        registerPage = PageGeneratorManager.getUserRegisterPage(driver);
        myAccountPage = PageGeneratorManager.getUserMyAccountPage(driver);
        addressesPage = PageGeneratorManager.getUserAddressPage(driver);
        rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
        myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);

        emailAddress = "test" + generateFakeNumber() + "@yopmail.com";
    }

    public void writeFile(String emailAddress, String passWord) throws IOException {
        File testFile = new File(TestFile);
        testFile.createNewFile();

        FileWriter fileWriter = new FileWriter(TestFile);
        BufferedWriter bufferReader = new BufferedWriter(fileWriter);
        bufferReader.write(emailAddress);
        bufferReader.write(";");
        bufferReader.write(passWord);
        bufferReader.close();
    }

    public void readFile() throws IOException {
        FileReader fileReader = new FileReader(TestFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String Content = "";
        while ((Content = bufferedReader.readLine()) != null) {
            String[] index = Content.split(";");
            registerEmail = index[0];
            passWord = index[1];
        }
    }

    @Test
    public void User_01_Register() throws IOException {
        registerPage = homePage.openRegisterPage();
        registerPage.inputToFirstnameTextbox("testFirstName");
        registerPage.inputToLastnameTextbox("testLastName");
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(passWord);
        registerPage.inputToConfirmPasswordTextbox(passWord);

        registerPage.clickToRegisterButton();

        System.out.println(emailAddress);
        Assert.assertEquals(registerPage.getRegisterCompletedResultMessage(), "Your registration completed");

        writeFile(emailAddress, passWord);
    }

    @Test
    public void User_02_Login() throws IOException {
        readFile();
        loginPage = homePage.openLoginPage();

        loginPage.loginAsUser(registerEmail, passWord);
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_03_My_Account() throws IOException {
        myAccountPage = loginPage.openAccountPage();
        Assert.assertEquals(loginPage.getTitleAccountPage(), "nopCommerce demo store. Account");
        emailUpdate = "emailUpdate_" + registerEmail;

        myAccountPage.clickToFemaleGender();
        myAccountPage.inputToFirstName(firstNameUpdate);
        myAccountPage.inputToLastName(lastNameUpdate);
        myAccountPage.inputToEmail(emailUpdate);

        myAccountPage.clickToSave();
        Assert.assertEquals(myAccountPage.getFirstNameUpdate(), firstNameUpdate);
        Assert.assertEquals(myAccountPage.getLastNameUpdate(), lastNameUpdate);
        Assert.assertEquals(myAccountPage.getEmailUpdate(), emailUpdate);

        writeFile(emailUpdate, passWord);
        myAccountPage.closeSuccessMessage();
        homePage = myAccountPage.clickToLogout();
    }

    @Test
    public void User_04_Switch_Page() throws InterruptedException {
//		try {
        loginPage = homePage.openLoginPage();

        homePage = loginPage.loginAsUser(emailUpdate, passWord);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

        myAccountPage = loginPage.openAccountPage();

        rewardPointPage = myAccountPage.openRewardPointsPage(driver);
        myProductReviewPage = rewardPointPage.openMyProductReviewsPage(driver);
        Assert.assertTrue(rewardPointPage.isHeaderDisplayed(driver, "My account - My product reviews"));

        rewardPointPage = myAccountPage.openRewardPointsPage(driver);
        Assert.assertTrue(rewardPointPage.isHeaderDisplayed(driver, "My account - Reward points"));

        addressesPage = rewardPointPage.openAddressPage(driver);
        Thread.sleep(5000);
        Assert.assertTrue(rewardPointPage.isHeaderDisplayed(driver, "My account - Addresses"));
        rewardPointPage = addressesPage.openRewardPointsPage(driver);
        Assert.assertTrue(rewardPointPage.isHeaderDisplayed(driver, "My account - Reward points"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }
}
