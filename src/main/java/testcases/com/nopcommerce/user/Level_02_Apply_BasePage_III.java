package testcases.com.nopcommerce.user;

import actions.commons.BasePage;
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

public class Level_02_Apply_BasePage_III extends BasePage {
    WebDriver driver;
    String emailAddress;
    String passWord = "123456aA@@";
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();

        emailAddress = "test" + generateFakeNumber() + "@yopmail.com";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void TC_01_Register_Empty_Data() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        waitForElementClickable(driver, "//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        senKeysToElement(driver, "//input[@id='FirstName']", "testFirstName");
        senKeysToElement(driver, "//input[@id='LastName']", "testLastName");
        senKeysToElement(driver, "//input[@id='Email']", passWord);
        senKeysToElement(driver, "//input[@id='Password']", passWord);
        senKeysToElement(driver, "//input[@id='ConfirmPassword']", passWord);

        waitForElementClickable(driver, "//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");

    }

    @Test
    public void TC_03_Register_Valid_Data() throws IOException {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        senKeysToElement(driver, "//input[@id='FirstName']", "testFirstName");
        senKeysToElement(driver, "//input[@id='LastName']", "testLastName");
        senKeysToElement(driver, "//input[@id='Email']", emailAddress);
        senKeysToElement(driver, "//input[@id='Password']", passWord);
        senKeysToElement(driver, "//input[@id='ConfirmPassword']", passWord);

        waitForElementClickable(driver, "//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");

        System.out.println(emailAddress);
        Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
        waitForElementClickable(driver, "//a[@class='ico-logout']");
        clickToElement(driver, "//a[@class='ico-logout']");

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

    public String getEmailAddress() {
        waitForElementClickable(driver, "//a[contains(@class,'ico-account')]");
        clickToElement(driver, "//a[contains(@class,'ico-account')]");
        return getElementAttributeValue(driver, "//input[contains(@id,'Email')]", "value");
    }

    @Test
    public void TC_04_Register_Exist_Email() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        senKeysToElement(driver, "//input[@id='FirstName']", "testFirstName");
        senKeysToElement(driver, "//input[@id='LastName']", "testLastName");
        senKeysToElement(driver, "//input[@id='Email']", emailAddress);
        senKeysToElement(driver, "//input[@id='Password']", passWord);
        senKeysToElement(driver, "//input[@id='ConfirmPassword']", passWord);

        waitForElementClickable(driver, "//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Characters() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        senKeysToElement(driver, "//input[@id='FirstName']", "testFirstName");
        senKeysToElement(driver, "//input[@id='LastName']", "testLastName");
        senKeysToElement(driver, "//input[@id='Email']", emailAddress);
        senKeysToElement(driver, "//input[@id='Password']", "12345");
        senKeysToElement(driver, "//input[@id='ConfirmPassword']", "12345");

        waitForElementClickable(driver, "//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\n" +
                "must have at least 6 characters");
    }

    @Test
    public void TC_06_Register_Confirm_Password_Not_Match_With_Password() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        senKeysToElement(driver, "//input[@id='FirstName']", "testFirstName");
        senKeysToElement(driver, "//input[@id='LastName']", "testLastName");
        senKeysToElement(driver, "//input[@id='Email']", "test@yopmail.com");
        senKeysToElement(driver, "//input[@id='Password']", "123456@@");
        senKeysToElement(driver, "//input[@id='ConfirmPassword']", "123456@");

        waitForElementClickable(driver, "//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
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
