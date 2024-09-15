package testcases.com.nopcommerce.admin;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

public class Admin_01_Login extends BasePage {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String emailAddress, registerEmail, passWord;

    @BeforeClass
    public void beforeClass() throws IOException {
        driver = new FirefoxDriver();

        readFile();
        emailAddress = "test" + generateFakeNumber() + "@yopmail.com";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://demo.nopcommerce.com/");
    }

    public void readFile() throws IOException {
        String TestFile = projectPath + "/src/main/resources/dataTestNopCommerce.txt";
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
    public void TC_01_Login_With_Empty_Data() {
        waitForElementClickable(driver, "class=ico-login");
        clickToElement(driver, "class=ico-login");

        waitForElementClickable(driver, "xpath=//button[contains(@class,'login-button')]");
        clickToElement(driver, "xpath=//button[contains(@class,'login-button')]");

        Assert.assertEquals(getElementText(driver, "xpath=//span[contains(@class,'field-validation-error')]"), "Please enter your email");
    }

    @Test
    public void TC_02_Login_With_Invalid_Email() {
        waitForElementClickable(driver, "class=ico-login");
        clickToElement(driver, "class=ico-login");

        sendKeysToElement(driver, "xpath=//input[contains(@id,'Email')]", "invalidemail");
        waitForElementClickable(driver, "xpath=//button[contains(@class,'login-button')]");
        clickToElement(driver, "xpath=//button[contains(@class,'login-button')]");
        Assert.assertEquals(getElementText(driver, "class=field-validation-error"), "Please enter a valid email address.");
    }

    @Test
    public void TC_03_Login_With_Unregister_Email() {
        waitForElementClickable(driver, "class=ico-login");
        clickToElement(driver, "class=ico-login");

        sendKeysToElement(driver, "xpath=//input[contains(@id,'Email')]", emailAddress);
        sendKeysToElement(driver, "xpath=//input[contains(@id,'Password')]", passWord);

        waitForElementClickable(driver, "xpath=//button[contains(@class,'login-button')]");
        clickToElement(driver, "xpath=//button[contains(@class,'login-button')]");
        Assert.assertEquals(getElementText(driver, "xpath=//div[contains(@class,'message-error validation-summary-errors')]"), "Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
    }

    @Test
    public void TC_04_Login_With_Registered_Email_Empty_Password() {
        waitForElementClickable(driver, "class=ico-login");
        clickToElement(driver, "class=ico-login");

        sendKeysToElement(driver, "xpath=//input[contains(@id,'Email')]", registerEmail);
//        senKeysToElement(driver, "//input[contains(@id,'Password')]", "");

        waitForElementClickable(driver, "xpath=//button[contains(@class,'login-button')]");
        clickToElement(driver, "xpath=//button[contains(@class,'login-button')]");
        Assert.assertEquals(getElementText(driver, "xpath=//div[contains(@class,'message-error validation-summary-errors')]"), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
    }

    @Test
    public void TC_05_Login_With_Registered_Email_Wrong_Password() {
        waitForElementClickable(driver, "class=ico-login");
        clickToElement(driver, "class=ico-login");

        sendKeysToElement(driver, "Xpath=//input[contains(@id,'Email')]", registerEmail);
        sendKeysToElement(driver, "xpath=//input[contains(@id,'Password')]", "123456");

        waitForElementClickable(driver, "xpath=//button[contains(@class,'login-button')]");
        clickToElement(driver, "xpath=//button[contains(@class,'login-button')]");
        Assert.assertEquals(getElementText(driver, "xpath=//div[contains(@class,'message-error validation-summary-errors')]"), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
    }

    @Test
    public void TC_06_Login_With_Registered_Email_True_Password() {
        waitForElementClickable(driver, "class=ico-login");
        clickToElement(driver, "class=ico-login");

        sendKeysToElement(driver, "xpath=//input[contains(@id,'Email')]", registerEmail);
        sendKeysToElement(driver, "xpath=//input[contains(@id,'Password')]", passWord);

        waitForElementClickable(driver, "xpath=//button[contains(@class,'login-button')]");
        clickToElement(driver, "xpath=//button[contains(@class,'login-button')]");

        sleepInSeconds(3);
        waitForElementClickable(driver, "xpath=//a[contains(@class,'ico-account')]");
        clickToElement(driver, "xpath=//a[contains(@class,'ico-account')]");

        Assert.assertEquals(getTitle(driver), "nopCommerce demo store. Account");
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
