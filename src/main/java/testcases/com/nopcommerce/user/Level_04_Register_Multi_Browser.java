package testcases.com.nopcommerce.user;

import actions.commons.BaseTest;
import actions.pageObjects.nopcommerce.user.UserHomePageObjects;
import actions.pageObjects.nopcommerce.user.UserRegisterPageObjects;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Level_04_Register_Multi_Browser extends BaseTest {
    private WebDriver driver;
    private String emailAddress;
    private String password = "123456aA@@";
    private UserHomePageObjects homePage;
    private UserRegisterPageObjects registerPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowser(browserName);
        homePage = new UserHomePageObjects(driver);

        emailAddress = "test" + generateFakeNumber() + "@mail.com";
        homePage = new UserHomePageObjects(driver);
    }

    @Test
    public void Register_01_Register_Empty_Data() {
        System.out.println("Register_01 - Step 01: Click to Register link");
        homePage.openRegisterPage();
        registerPage = new UserRegisterPageObjects(driver);

        System.out.println("Register_01 - Step 02: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_01 - Step 03: Verify error message displayed");
        Assert.assertEquals(registerPage.getFirstnameErrorMessage(), "First name is required.");
        Assert.assertEquals(registerPage.getLastnameErrorMessage(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
        Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");
    }

    @Test
    public void Register_02_Register_Invalid_Email() {
        System.out.println("Register_02 - Step 01: Click to Register link");
        homePage.openRegisterPage();

        System.out.println("Register_02 - Step 02: Input to required fields");
        registerPage.inputToFirstnameTextbox("testFirstName");
        registerPage.inputToLastnameTextbox("testLastName");
        registerPage.inputToEmailTextbox(password);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        System.out.println("Register_02 - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_02 - Step 04: Verify error message displayed");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");
    }

    @Test
    public void Register_03_Register_Valid_Data() throws IOException {
        System.out.println("Register_03 - Step 01: Click to Register link");
        homePage.openRegisterPage();

        System.out.println("Register_03 - Step 02: Input to required fields");
        registerPage.inputToFirstnameTextbox("testFirstName");
        registerPage.inputToLastnameTextbox("testLastName");
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        System.out.println("Register_03 - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println(emailAddress);
        System.out.println("Register_03 - Step 04: Verify register completed message displayed");
        Assert.assertEquals(registerPage.getRegisterCompletedResultMessage(), "Your registration completed");

        System.out.println("Register_03 - Step 03: Click to Logout button");
        registerPage.clickToLogoutButton();

        writeFile(emailAddress, password);
    }

    @Test
    public void Register_04_Register_Exist_Email() {
        System.out.println("Register_04 - Step 01: Click to Register link");
        homePage.openRegisterPage();

        System.out.println("Register_04 - Step 02: Input to required fields");
        registerPage.inputToFirstnameTextbox("testFirstName");
        registerPage.inputToLastnameTextbox("testLastName");
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        System.out.println("Register_04 - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_04 - Step 04: Verify existed email message displayed");
        Assert.assertEquals(registerPage.getExistedEmailErrorMessage(), "The specified email already exists");
    }

    @Test
    public void Register_05_Register_Password_Less_Than_6_Characters() {
        System.out.println("Register_05 - Step 01: Click to Register link");
        homePage.openRegisterPage();

        System.out.println("Register_05 - Step 02: Input to required fields");
        registerPage.inputToFirstnameTextbox("testFirstName");
        registerPage.inputToLastnameTextbox("testLastName");
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox("12345");
        registerPage.inputToConfirmPasswordTextbox("12345");

        System.out.println("Register_05 - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_05 - Step 04: Verify number password character error message displayed");
        Assert.assertEquals(registerPage.getNumberPasswordCharacterErrorMessage(), "Password must meet the following rules:\n" + "must have at least 6 characters");
    }

    @Test
    public void Register_06_Register_Confirm_Password_Not_Match_With_Password() {
        System.out.println("Register_06 - Step 01: Click to Register link");
        homePage.openRegisterPage();

        System.out.println("Register_06 - Step 02: Input to required fields");
        registerPage.inputToFirstnameTextbox("testFirstName");
        registerPage.inputToLastnameTextbox("testLastName");
        registerPage.inputToEmailTextbox("test@gmail.com");
        registerPage.inputToPasswordTextbox("123456@@");
        registerPage.inputToConfirmPasswordTextbox("123456@");

        System.out.println("Register_06 - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println("Register_06 - Step 04: Verify confirm password not match error message displayed");
        Assert.assertEquals(registerPage.getPasswordNotMatchErrorMessage(), "The password and confirmation password do not match.");
    }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }

    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
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
}
