package testcases.com.nopcommerce.user;

import actions.commons.BasePage;
import actions.pageObjects.nopcommerce.user.UserHomePageObjects;
import actions.pageObjects.nopcommerce.user.UserRegisterPageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

public class Level_03_Register_Page_Object extends BasePage {
    private WebDriver driver;
    private String emailAddress;
    private String passWord = "123456aA@@";
    private String projectPath = System.getProperty("user.dir");
    private UserHomePageObjects homePage;
    private UserRegisterPageObjects registerPage;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        homePage = new UserHomePageObjects(driver);

        emailAddress = "test" + generateFakeNumber() + "@yopmail.com";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://demo.nopcommerce.com/");
        homePage = new UserHomePageObjects(driver);
        registerPage = new UserRegisterPageObjects(driver);
    }

    @Test
    public void Register_01_Register_Empty_Data() {
//        waitForElementClickable(driver, "//a[@class='ico-register']");
//        clickToElement(driver, "//a[@class='ico-register']");
        System.out.println("Register_01 - Step 01: Click to Register link");
        homePage.openRegisterPage();
        registerPage = new UserRegisterPageObjects(driver);

//        waitForElementClickable(driver, "//button[@id='register-button']");
//        clickToElement(driver, "//button[@id='register-button']");
        System.out.println("Register_01 - Step 02: Click to Register button");
        registerPage.clickToRegisterButton();

//        Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
//        Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
//        Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
//        Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
//        Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");

        System.out.println("Register_01 - Step 03: Verify error message displayed");
        Assert.assertEquals(registerPage.getFirstnameErrorMessage(), "First name is required.");
        Assert.assertEquals(registerPage.getLastnameErrorMessage(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
        Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");

    }

    @Test
    public void Register_02_Register_Invalid_Email() {
//        waitForElementClickable(driver, "//a[@class='ico-register']");
//        clickToElement(driver, "//a[@class='ico-register']");
        System.out.println("Register_02 - Step 01: Click to Register link");
        homePage.openRegisterPage();

//        senKeysToElement(driver, "//input[@id='FirstName']", "testFirstName");
//        senKeysToElement(driver, "//input[@id='LastName']", "testLastName");
//        senKeysToElement(driver, "//input[@id='Email']", passWord);
//        senKeysToElement(driver, "//input[@id='Password']", passWord);
//        senKeysToElement(driver, "//input[@id='ConfirmPassword']", passWord);
        System.out.println("Register_02 - Step 02: Input to required fields");
        registerPage.inputToFirstnameTextbox("testFirstName");
        registerPage.inputToLastnameTextbox("testLastName");
        registerPage.inputToEmailTextbox(passWord);
        registerPage.inputToPasswordTextbox(passWord);
        registerPage.inputToConfirmPasswordTextbox(passWord);

//        waitForElementClickable(driver, "//button[@id='register-button']");
//        clickToElement(driver, "//button[@id='register-button']");
        System.out.println("Register_02 - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

//        Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
        System.out.println("Register_02 - Step 04: Verify error message displayed");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");
    }

    @Test
    public void Register_03_Register_Valid_Data() throws IOException {
//       waitForElementClickable(driver, "//a[@class='ico-register']");
//        clickToElement(driver, "//a[@class='ico-register']");
        System.out.println("Register_03 - Step 01: Click to Register link");
        homePage.openRegisterPage();

//        senKeysToElement(driver, "//input[@id='FirstName']", "testFirstName");
//        senKeysToElement(driver, "//input[@id='LastName']", "testLastName");
//        senKeysToElement(driver, "//input[@id='Email']", emailAddress);
//        senKeysToElement(driver, "//input[@id='Password']", passWord);
//        senKeysToElement(driver, "//input[@id='ConfirmPassword']", passWord);
        System.out.println("Register_03 - Step 02: Input to required fields");
        registerPage.inputToFirstnameTextbox("testFirstName");
        registerPage.inputToLastnameTextbox("testLastName");
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(passWord);
        registerPage.inputToConfirmPasswordTextbox(passWord);

//        waitForElementClickable(driver, "//button[@id='register-button']");
//        clickToElement(driver, "//button[@id='register-button']");
        System.out.println("Register_03 - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

        System.out.println(emailAddress);
//        Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
        System.out.println("Register_03 - Step 04: Verify register completed message displayed");
        Assert.assertEquals(registerPage.getRegisterCompletedResultMessage(), "Your registration completed");

        System.out.println("Register_03 - Step 03: Write to file");
        writeFile(emailAddress, passWord);
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

    @Test
    public void Register_04_Register_Exist_Email() {
//        waitForElementClickable(driver, "//a[@class='ico-register']");
//        clickToElement(driver, "//a[@class='ico-register']");
        System.out.println("Register_04 - Step 01: Click to Register link");
        homePage.openRegisterPage();

//        senKeysToElement(driver, "//input[@id='FirstName']", "testFirstName");
//        senKeysToElement(driver, "//input[@id='LastName']", "testLastName");
//        senKeysToElement(driver, "//input[@id='Email']", emailAddress);
//        senKeysToElement(driver, "//input[@id='Password']", passWord);
//        senKeysToElement(driver, "//input[@id='ConfirmPassword']", passWord);
        System.out.println("Register_04 - Step 02: Input to required fields");
        registerPage.inputToFirstnameTextbox("testFirstName");
        registerPage.inputToLastnameTextbox("testLastName");
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(passWord);
        registerPage.inputToConfirmPasswordTextbox(passWord);

//        waitForElementClickable(driver, "//button[@id='register-button']");
//        clickToElement(driver, "//button[@id='register-button']");
        System.out.println("Register_04 - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

//        Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
        System.out.println("Register_04 - Step 04: Verify existed email message displayed");
        Assert.assertEquals(registerPage.getExistedEmailErrorMessage(), "The specified email already exists");
    }

    @Test
    public void Register_05_Register_Password_Less_Than_6_Characters() {
//        waitForElementClickable(driver, "//a[@class='ico-register']");
//        clickToElement(driver, "//a[@class='ico-register']");
        System.out.println("Register_05 - Step 01: Click to Register link");
        homePage.openRegisterPage();

//        senKeysToElement(driver, "//input[@id='FirstName']", "testFirstName");
//        senKeysToElement(driver, "//input[@id='LastName']", "testLastName");
//        senKeysToElement(driver, "//input[@id='Email']", emailAddress);
//        senKeysToElement(driver, "//input[@id='Password']", "12345");
//        senKeysToElement(driver, "//input[@id='ConfirmPassword']", "12345");
        System.out.println("Register_05 - Step 02: Input to required fields");
        registerPage.inputToFirstnameTextbox("testFirstName");
        registerPage.inputToLastnameTextbox("testLastName");
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox("12345");
        registerPage.inputToConfirmPasswordTextbox("12345");

//        waitForElementClickable(driver, "//button[@id='register-button']");
//        clickToElement(driver, "//button[@id='register-button']");
        System.out.println("Register_05 - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

//        Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\n"
//                + "must have at least 6 characters");
        System.out.println("Register_05 - Step 04: Verify number password character error message displayed");
        Assert.assertEquals(registerPage.getNumberPasswordCharacterErrorMessage(), "Password must meet the following rules:\n" + "must have at least 6 characters");
    }

    @Test
    public void Register_06_Register_Confirm_Password_Not_Match_With_Password() {
//        waitForElementClickable(driver, "//a[@class='ico-register']");
//        clickToElement(driver, "//a[@class='ico-register']");
        System.out.println("Register_06 - Step 01: Click to Register link");
        homePage.openRegisterPage();

//        senKeysToElement(driver, "//input[@id='FirstName']", "testFirstName");
//        senKeysToElement(driver, "//input[@id='LastName']", "testLastName");
//        senKeysToElement(driver, "//input[@id='Email']", "test@gmail.com");
//        senKeysToElement(driver, "//input[@id='Password']", "123456@@");
//        senKeysToElement(driver, "//input[@id='ConfirmPassword']", "123456@");
        System.out.println("Register_06 - Step 02: Input to required fields");
        registerPage.inputToFirstnameTextbox("testFirstName");
        registerPage.inputToLastnameTextbox("testLastName");
        registerPage.inputToEmailTextbox("test@gmail.com");
        registerPage.inputToPasswordTextbox("123456@@");
        registerPage.inputToConfirmPasswordTextbox("123456@");

//        waitForElementClickable(driver, "//button[@id='register-button']");
//        clickToElement(driver, "//button[@id='register-button']");
        System.out.println("Register_06 - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

//        Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
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
}
