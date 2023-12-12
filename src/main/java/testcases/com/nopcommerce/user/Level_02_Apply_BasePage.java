package testcases.com.nopcommerce.user;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_02_Apply_BasePage extends BasePage {
    BasePage basePage;
    WebDriver driver;
    String emailAddress;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();

//        basePage = new BasePage();

        emailAddress = "test" + generateFakeNumber()+ "@yopmail.com";
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void TC_01_Register_Empty_Data() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.senKeysToElement(driver, "//input[@id='FirstName']","testFirstName");
        basePage.senKeysToElement(driver, "//input[@id='LastName']","testLastName");
        basePage.senKeysToElement(driver, "//input[@id='Email']", "123456aA@@");
        basePage.senKeysToElement(driver, "//input[@id='Password']","123456aA@@");
        basePage.senKeysToElement(driver, "//input[@id='ConfirmPassword']","123456aA@@");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");

    }

    @Test
    public void TC_03_Register_Valid_Data() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.senKeysToElement(driver, "//input[@id='FirstName']","testFirstName");
        basePage.senKeysToElement(driver, "//input[@id='LastName']","testLastName");
        basePage.senKeysToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.senKeysToElement(driver, "//input[@id='Password']","123456aA@@");
        basePage.senKeysToElement(driver, "//input[@id='ConfirmPassword']","123456aA@@");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");

        basePage.waitForElementClickable(driver, "//a[@class='ico-logout']");
        basePage.clickToElement(driver,"//a[@class='ico-logout']");
    }

    @Test
    public void TC_04_Register_Exist_Email() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.senKeysToElement(driver, "//input[@id='FirstName']","testFirstName");
        basePage.senKeysToElement(driver, "//input[@id='LastName']","testLastName");
        basePage.senKeysToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.senKeysToElement(driver, "//input[@id='Password']","123456aA@@");
        basePage.senKeysToElement(driver, "//input[@id='ConfirmPassword']","123456aA@@");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Characters() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.senKeysToElement(driver, "//input[@id='FirstName']","testFirstName");
        basePage.senKeysToElement(driver, "//input[@id='LastName']","testLastName");
        basePage.senKeysToElement(driver, "//input[@id='Email']", emailAddress);
        basePage.senKeysToElement(driver, "//input[@id='Password']","12345");
        basePage.senKeysToElement(driver, "//input[@id='ConfirmPassword']","12345");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\n" +
                "must have at least 6 characters");
    }

    @Test
    public void TC_06_Register_Confirm_Password_Not_Match_With_Password() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.senKeysToElement(driver, "//input[@id='FirstName']","testFirstName");
        basePage.senKeysToElement(driver, "//input[@id='LastName']","testLastName");
        basePage.senKeysToElement(driver, "//input[@id='Email']", "test@yopmail.com");
        basePage.senKeysToElement(driver, "//input[@id='Password']","123456@@");
        basePage.senKeysToElement(driver, "//input[@id='ConfirmPassword']","123456@");

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
    }

    public int generateFakeNumber(){
        Random random = new Random();
        return random.nextInt(500);
    }
}
