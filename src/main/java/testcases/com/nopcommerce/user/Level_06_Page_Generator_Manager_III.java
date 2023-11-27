package testcases.com.nopcommerce.user;

import actions.commons.BaseTest;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.nopcommerce.user.UserAccountPageObjects;
import actions.pageObjects.nopcommerce.user.UserHomePageObjects;
import actions.pageObjects.nopcommerce.user.UserLoginPageObjects;
import actions.pageObjects.nopcommerce.user.UserRegisterPageObjects;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Random;

public class Level_06_Page_Generator_Manager_III extends BaseTest {
    private WebDriver driver;
    String emailAddress, registerEmail, passWord = "123456aA@@";
    private UserHomePageObjects homePage;
    private UserRegisterPageObjects registerPage;
    private UserLoginPageObjects loginPage;
    private UserAccountPageObjects myAccountPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) throws IOException {
        driver = getBrowser(browserName);
        homePage = PageGeneratorManager.getUserHomePage(driver);
        loginPage = PageGeneratorManager.getUserLoginPage(driver);
        registerPage = PageGeneratorManager.getUserRegisterPage(driver);
        myAccountPage = PageGeneratorManager.getUserMyAccountPage(driver);

        emailAddress = "test" + generateFakeNumber() + "@yopmail.com";

        System.out.println("Pre-Condition Register - Step 01: Click to Register link");
        registerPage = homePage.openRegisterPage();
        System.out.println("Pre-Condition Register - Step 02: Input to required fields");
        registerPage.inputToFirstnameTextbox("testFirstName");
        registerPage.inputToLastnameTextbox("testLastName");
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(passWord);
        registerPage.inputToConfirmPasswordTextbox(passWord);

        System.out.println("Pre-Condition Register - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println(emailAddress);
        System.out.println("Pre-Condition Register - Step 04: Verify register completed message displayed");
        Assert.assertEquals(registerPage.getRegisterCompletedResultMessage(), "Your registration completed");

        System.out.println("Pre-Condition Register - Step 03: Click to Logout button");
        homePage = registerPage.clickToLogoutButton();

        writeFile(emailAddress, passWord);

        readFile();
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

    @Test
    public void Login_01_Login_Empty_Data() {
        System.out.println("Login_01 - Step 01: Click to Login link");
        loginPage = homePage.openLoginPage();

        System.out.println("Login_01 - Step 02: Click to Login button");
        loginPage.openLoginPage();

        System.out.println("Login_01 - Step 03: Verify error message displayed");
        Assert.assertEquals(loginPage.getFieldValidationErrorMessage(), "Please enter your email");
    }

    @Test
    public void Login_02_Login_Invalid_Data() {
        System.out.println("Login_02 - Step 01: Click to Login link");
        loginPage = homePage.openLoginPage();

        System.out.println("Login_02 - Step 02: Input to required fields");
        loginPage.inputToEmailTextbox("invalidEmail");

        System.out.println("Login_02 - Step 03: Click to Login button");
        loginPage.openLoginPage();

        System.out.println("Login_02 - Step 04: Verify error message displayed");
        Assert.assertEquals(loginPage.getFieldValidationErrorMessage(), "Wrong email");
    }

    @Test
    public void Login_03_Login_Unregister_Email() {
        System.out.println("Login_03 - Step 01: Click to Login link");
        loginPage = homePage.openLoginPage();

        System.out.println("Login_03 - Step 02: Input to required fields");
        loginPage.inputToEmailTextbox("unregisterEmail@yopmail.com");
        loginPage.inputToPasswordTextbox(passWord);

        System.out.println("Login_03 - Step 03: Click to Login button");
        loginPage.openLoginPage();

        System.out.println("Login_03 - Step 04: Verify error message displayed");
        Assert.assertEquals(loginPage.getLoginErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found");
    }

    @Test
    public void Login_04_Login_Registered_Email_Empty_Password() {
        System.out.println("Login_04 - Step 01: Click to Login link");
        loginPage = homePage.openLoginPage();

        System.out.println("Login_04 - Step 02: Input to required fields");
        loginPage.inputToEmailTextbox(registerEmail);

        System.out.println("Login_04 - Step 03: Click to Login button");
        loginPage.openLoginPage();

        System.out.println("Login_04 - Step 04: Verify error message displayed");
        Assert.assertEquals(loginPage.getLoginErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" +
                "The credentials provided are incorrect");
    }

    @Test
    public void Login_05_Login_Registered_Email_Wrong_Password() {
        System.out.println("Login_05 - Step 01: Click to Login link");
        loginPage = homePage.openLoginPage();

        System.out.println("Login_05 - Step 02: Input to required fields");
        loginPage.inputToEmailTextbox(registerEmail);
        loginPage.inputToPasswordTextbox("wrongPassword");

        System.out.println("Login_05 - Step 03: Click to Login button");
        loginPage.openLoginPage();

        System.out.println("Login_05 - Step 04: Verify error message displayed");
        Assert.assertEquals(loginPage.getLoginErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" +
                "The credentials provided are incorrect");
    }

    @Test
    public void Login_06_Login_Valid_Email_Password() {
        System.out.println("Login_06 - Step 01: Click to Login link");
        loginPage = homePage.openLoginPage();

        System.out.println("Login_06 - Step 02: Input to required fields");
        loginPage.inputToEmailTextbox(registerEmail);
        loginPage.inputToPasswordTextbox(passWord);

        System.out.println("Login_06 - Step 03: Click to Login button");
        homePage = loginPage.openLoginPage();

        System.out.println("Login_06 - Step 04: Verify account link displayed");
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

//        myAccountPage = loginPage.clickToAccountLink();
//        Assert.assertEquals(loginPage.getTitleAccountPage(), "nopCommerce demo store. Account");
//
//        myAccountPage.clickToMyAccountLink();
    }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }

    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }
}
