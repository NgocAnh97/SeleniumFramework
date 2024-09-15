package testcases.com.nopcommerce.user;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_Apply_BasePage_II {
    BasePage basePage;
    WebDriver driver;
    String emailAddress;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        //Hide initial of element
        basePage = BasePage.getBasePageObject();

        emailAddress = "test" + generateFakeNumber() + "@yopmail.com";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void TC_01_Register_Empty_Data() {
        basePage.waitForElementClickable(driver, "class=ico-register");
        basePage.clickToElement(driver, "class=ico-register");

        basePage.waitForElementClickable(driver, "id=register-button");
        basePage.clickToElement(driver, "id=register-button");
        Assert.assertEquals(basePage.getElementText(driver, "xpath=//span[@data-valmsg-for='FirstName']"), "First name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "xpath=//span[@data-valmsg-for='LastName']"), "Last name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "xpath=//span[@data-valmsg-for='Email']"), "Email is required.");
        Assert.assertEquals(basePage.getElementText(driver, "xpath=//span[@data-valmsg-for='ConfirmPassword']"), "Password is required.");
    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        basePage.waitForElementClickable(driver, "class=ico-register");
        basePage.clickToElement(driver, "class=ico-register");

        basePage.sendKeysToElement(driver, "id=FirstName", "testFirstName");
        basePage.sendKeysToElement(driver, "id=LastName", "testLastName");
        basePage.sendKeysToElement(driver, "id=Email", "123456aA@@");
        basePage.sendKeysToElement(driver, "id=Password", "123456aA@@");
        basePage.sendKeysToElement(driver, "id=ConfirmPassword", "123456aA@@");

        basePage.waitForElementClickable(driver, "id=register-button");
        basePage.clickToElement(driver, "id=register-button");
        basePage.sleepInSeconds(2);
        Assert.assertEquals(basePage.getElementText(driver, "xpath=//span[@data-valmsg-for='Email']"), "Please enter a valid email address.");
    }

    @Test
    public void TC_03_Register_Valid_Data() {
        basePage.waitForElementClickable(driver, "class=ico-register");
        basePage.clickToElement(driver, "class=ico-register");

        basePage.sendKeysToElement(driver, "id=FirstName", "testFirstName");
        basePage.sendKeysToElement(driver, "id=LastName", "testLastName");
        basePage.sendKeysToElement(driver, "id=Email", emailAddress);
        basePage.sendKeysToElement(driver, "id=Password", "123456aA@@");
        basePage.sendKeysToElement(driver, "id=ConfirmPassword", "123456aA@@");

        basePage.waitForElementClickable(driver, "id=register-button");
        basePage.clickToElement(driver, "id=register-button");

        Assert.assertEquals(basePage.getElementText(driver, "class=result"), "Your registration completed");

        basePage.waitForElementClickable(driver, "class=ico-logout");
        basePage.clickToElement(driver, "class=ico-logout");
    }

    @Test
    public void TC_04_Register_Exist_Email() {
        basePage.waitForElementClickable(driver, "class=ico-register");
        basePage.clickToElement(driver, "class=ico-register");

        basePage.sendKeysToElement(driver, "id=FirstName", "testFirstName");
        basePage.sendKeysToElement(driver, "id=LastName", "testLastName");
        basePage.sendKeysToElement(driver, "id=Email", emailAddress);
        basePage.sendKeysToElement(driver, "id=Password", "123456aA@@");
        basePage.sendKeysToElement(driver, "id=ConfirmPassword", "123456aA@@");

        basePage.waitForElementClickable(driver, "id=register-button");
        basePage.clickToElement(driver, "id=register-button");

        Assert.assertEquals(basePage.getElementText(driver, "xpath=//div[contains(@class,'message-error')]//li"), "The specified email already exists");
    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Characters() {
        basePage.waitForElementClickable(driver, "class=ico-register");
        basePage.clickToElement(driver, "class=ico-register");

        basePage.sendKeysToElement(driver, "id=FirstName", "testFirstName");
        basePage.sendKeysToElement(driver, "id=LastName", "testLastName");
        basePage.sendKeysToElement(driver, "id=Email", emailAddress);
        basePage.sendKeysToElement(driver, "id=Password", "12345");
        basePage.sendKeysToElement(driver, "id=ConfirmPassword", "12345");

        basePage.waitForElementClickable(driver, "id=register-button");
        basePage.clickToElement(driver, "id=register-button");

        Assert.assertEquals(basePage.getElementText(driver, "xpath=//span[@data-valmsg-for='Password']"),
                "<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
    }

    @Test
    public void TC_06_Register_Confirm_Password_Not_Match_With_Password() {
        basePage.waitForElementClickable(driver, "class=ico-register");
        basePage.clickToElement(driver, "class=ico-register");

        basePage.sendKeysToElement(driver, "id=FirstName", "testFirstName");
        basePage.sendKeysToElement(driver, "id=LastName", "testLastName");
        basePage.sendKeysToElement(driver, "id=Email", "test@yopmail.com");
        basePage.sendKeysToElement(driver, "id=Password", "123456@@");
        basePage.sendKeysToElement(driver, "id=ConfirmPassword", "123456@");

        basePage.waitForElementClickable(driver, "id=register-button");
        basePage.clickToElement(driver, "id=register-button");

        Assert.assertEquals(basePage.getElementText(driver, "xpath=//span[@data-valmsg-for='ConfirmPassword']"), "The password and confirmation password do not match.");
    }

    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }
}
