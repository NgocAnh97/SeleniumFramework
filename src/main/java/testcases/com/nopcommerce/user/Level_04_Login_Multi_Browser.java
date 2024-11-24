package testcases.com.nopcommerce.user;

import actions.commons.BaseTest;
import actions.pageObjects.nopcommerce.user.UserHomePageObjects;
import actions.pageObjects.nopcommerce.user.UserLoginPageObjects;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Level_04_Login_Multi_Browser extends BaseTest {
    private WebDriver driver;
    String emailAddress, registerEmail, password;
    private UserHomePageObjects homePage;
    private UserLoginPageObjects loginPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) throws IOException {
        driver = getBrowser(browserName);
        homePage = new UserHomePageObjects(driver);
        loginPage = new UserLoginPageObjects(driver);
        readFile();
        emailAddress = "test" + generateFakeNumber() + "@yopmail.com";
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
        loginPage.inputToPasswordTextbox(password);

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
    public void Login_06_Login_Registered_Email_Password() {
        System.out.println("Login_06 - Step 01: Click to Login link");
        loginPage = homePage.openLoginPage();

        System.out.println("Login_06 - Step 02: Input to required fields");
        loginPage.inputToEmailTextbox(registerEmail);
        loginPage.inputToPasswordTextbox(password);

        System.out.println("Login_06 - Step 03: Click to Login button");
        loginPage.openLoginPage();

        System.out.println("Login_06 - Step 04: Verify account link displayed");
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
//        loginPage.clickToAccountLink();
//        Assert.assertEquals(loginPage.getTitleAccountPage(), "nopCommerce demo store. Account");
    }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }

    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }

    public void readFile() throws IOException {
        String TestFile = "/Users/mastery/Documents/selenium/selenium/src/main/resources/dataTestNopCommerce.txt";
        FileReader fileReader = new FileReader(TestFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String Content = "";
        while ((Content = bufferedReader.readLine()) != null) {
            String[] index = Content.split(";");
            registerEmail = index[0];
            password = index[1];
        }
    }
}
