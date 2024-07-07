package testcases.com.nopcommerce.user;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_Apply_BasePage extends BasePage {
    WebDriver driver;
    String emailAddress;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        emailAddress = "test" + generateFakeNumber() + "@yopmail.com";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void TC_01_Register_Empty_Data() {
        waitForElementClickable(driver, "class=ico-register");
        clickToElement(driver, "class=ico-register");

        waitForElementClickable(driver, "id=register-button");
        clickToElement(driver, "id=register-button");
        Assert.assertEquals(getElementText(driver, "xpath=//span[@data-valmsg-for='FirstName']"), "First name is required.");
        Assert.assertEquals(getElementText(driver, "xpath=//span[@data-valmsg-for='LastName']"), "Last name is required.");
        Assert.assertEquals(getElementText(driver, "xpath=//span[@data-valmsg-for='Email']"), "Email is required.");
        Assert.assertEquals(getElementText(driver, "xpath=//span[@data-valmsg-for='ConfirmPassword']"), "Password is required.");
    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        waitForElementClickable(driver, "class=ico-register");
        clickToElement(driver, "class=ico-register");

        senKeysToElement(driver, "id=FirstName", "testFirstName");
        senKeysToElement(driver, "id=LastName", "testLastName");
        senKeysToElement(driver, "id=Email", "wrongEmail");
        senKeysToElement(driver, "id=Password", "123456aA@@");
        senKeysToElement(driver, "id=ConfirmPassword", "123456aA@@");

        waitForElementClickable(driver, "id=register-button");
        clickToElement(driver, "id=register-button");
        sleepInSecond(2);
        Assert.assertEquals(getElementText(driver, "xpath=//span[@data-valmsg-for='Email']"), "Please enter a valid email address.");
    }

    @Test
    public void TC_03_Register_Valid_Data() {
        waitForElementClickable(driver, "class=ico-register");
        clickToElement(driver, "class=ico-register");

        senKeysToElement(driver, "id=FirstName", "testFirstName");
        senKeysToElement(driver, "id=LastName", "testLastName");
        senKeysToElement(driver, "id=Email", emailAddress);
        senKeysToElement(driver, "id=Password", "123456aA@@");
        senKeysToElement(driver, "id=ConfirmPassword", "123456aA@@");

        waitForElementClickable(driver, "id=register-button");
        clickToElement(driver, "id=register-button");

        Assert.assertEquals(getElementText(driver, "class=result"), "Your registration completed");

        waitForElementClickable(driver, "class=ico-logout");
        clickToElement(driver, "class=ico-logout");
    }

    @Test
    public void TC_04_Register_Exist_Email() {
        waitForElementClickable(driver, "class=ico-register");
        clickToElement(driver, "class=ico-register");

        senKeysToElement(driver, "id=FirstName", "testFirstName");
        senKeysToElement(driver, "id=LastName", "testLastName");
        senKeysToElement(driver, "id=Email", emailAddress);
        senKeysToElement(driver, "id=Password", "123456aA@@");
        senKeysToElement(driver, "id=ConfirmPassword", "123456aA@@");

        waitForElementClickable(driver, "id=register-button");
        clickToElement(driver, "id=register-button");

        Assert.assertEquals(getElementText(driver, "xpath=//div[contains(@class,'message-error')]//li"), "The specified email already exists");
    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Characters() {
        waitForElementClickable(driver, "class=ico-register");
        clickToElement(driver, "class=ico-register");

        senKeysToElement(driver, "id=FirstName", "testFirstName");
        senKeysToElement(driver, "id=LastName", "testLastName");
        senKeysToElement(driver, "id=Email", emailAddress);
        senKeysToElement(driver, "id=Password", "12345");
        senKeysToElement(driver, "id=ConfirmPassword", "12345");

        waitForElementClickable(driver, "id=register-button");
        clickToElement(driver, "id=register-button");

        Assert.assertEquals(getElementText(driver, "xpath=//span[@data-valmsg-for='Password']"),
                "<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
    }

    @Test
    public void TC_06_Register_Confirm_Password_Not_Match_With_Password() {
        waitForElementClickable(driver, "class=ico-register");
        clickToElement(driver, "class=ico-register");

        senKeysToElement(driver, "id=FirstName", "testFirstName");
        senKeysToElement(driver, "id=LastName", "testLastName");
        senKeysToElement(driver, "id=Email", "test@yopmail.com");
        senKeysToElement(driver, "id=Password", "123456@@");
        senKeysToElement(driver, "id=ConfirmPassword", "123456@");

        waitForElementClickable(driver, "id=register-button");
        clickToElement(driver, "id=register-button");

        Assert.assertEquals(getElementText(driver, "xpath=//span[@data-valmsg-for='ConfirmPassword']"), "The password and confirmation password do not match.");
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
