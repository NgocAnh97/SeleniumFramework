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
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_13_Register_Login_Log_Report extends BaseTest {
    String projectPath = System.getProperty("user.dir");

    @Parameters({"browser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        log.info("Pre-condition info: Open browser '" + browserName + "'");

        driver = getBrowserDriver(browserName, environmentName);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);
        userAccountPage = PageGeneratorManager.getUserMyAccountPage(driver);

        adminEmailAddress = "admin@yourstore.com";
        adminPassWord = "admin";
        passWord = "123456aA@@";
        userEmailAddress = "test" + generateFakeNumber() + "@yopmail.com";
    }

    @Test
    public void Role_01_User() throws IOException {
        log.info("User_01_Register - Step 01: Open register page and input values to textbox");
        registerPage = userHomePage.openRegisterPage();
        registerPage.inputToFirstnameTextbox("testFirstName");
        registerPage.inputToLastnameTextbox("testLastName");
        registerPage.inputToEmailTextbox(userEmailAddress);
        registerPage.inputToPasswordTextbox(passWord);
        registerPage.inputToConfirmPasswordTextbox(passWord);

        log.info("User_01_Register - Step 02: Click to register button");
        registerPage.clickToRegisterButton();

        log.info("User_01_Register - Step 03: Verify register completed message");
        verifyEquals(registerPage.getRegisterCompletedResultMessage(), "Your registration completed");

        log.info("User_01_Register - Step 04: Write registered email to file");
        writeFile(userEmailAddress, passWord);

        log.info("User_01_Register - Step 05: Read email from file and login with this email "
                + userEmailAddress + " and password " + passWord);
        readFile();
        userLoginPage = userHomePage.openLoginPage();
        userHomePage = userLoginPage.loginAsUser(userEmailAddress, passWord);

        log.info("User_01_Register - Step 06: Verify my account link is displayed");
        verifyTrue(userHomePage.isMyAccountLinkDisplayed());

        log.info("User_01_Register - Step 07: Click to logout button");
        userHomePage = userAccountPage.clickToLogout();
    }

    @Test
    public void Role_02_Admin() {
        log.info("Admin_02_Login - Step 01: Open admin login page");
        adminLoginPage = userHomePage.openAdminLoginPage();

        log.info("Admin_02_Login - Step 02: Login with email: " + adminEmailAddress + "password: " + adminPassWord);
        adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassWord);

        log.info("Admin_02_Login - Step 03: Verify dashboard link is displayed");
        verifyTrue(adminDashboardPage.isDashboardLinkDisplayed());

        log.info("Admin_02_Login - Step 04: Click to logout link");
        adminDashboardPage.clickToElementByJS(driver, GlobalConstants.LOGOUT_LINK);
    }

    @AfterClass
    public void afterClass() {
        log.info("Post-condition: Close driver");
        driver.quit();
    }

    public void writeFile(String emailAddress, String passWord) throws IOException {
        String TestFile = "/Users/mastery/Documents/selenium/selenium/src/main/resources/dataTestNopCommerce.txt";
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
        String TestFile = "/Users/mastery/Documents/selenium/selenium/src/main/resources/dataTestNopCommerce.txt";
        FileReader fileReader = new FileReader(TestFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String Content = "";
        while ((Content = bufferedReader.readLine()) != null) {
            String[] index = Content.split(";");
            registerEmail = index[0];
            passWord = index[1];
        }
    }

    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }

    private WebDriver driver;
    String userEmailAddress, adminEmailAddress,
            adminPassWord, registerEmail, passWord;
    private UserLoginPageObjects userLoginPage;
    private UserRegisterPageObjects registerPage;
    private UserHomePageObjects userHomePage;
    private UserAccountPageObjects userAccountPage;
    private AdminDashboardPageObjects adminDashboardPage;
    private AdminLoginPageObjects adminLoginPage;
}
