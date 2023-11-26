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
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Admin_01_Login extends BasePage {
//    public User_01_Login(String emailAddress, WebDriver driver) {
//        this.emailAddress = emailAddress;
//        this.driver = driver;
//    }

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String emailAddress, registerEmail, passWord;

    @BeforeClass
    public void beforeClass() throws IOException {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();

        readFile();
        emailAddress = "test" + generateFakeNumber() + "@yopmail.com";
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
    }

    public void readFile() throws IOException {
        String TestFile = "/Users/mastery/Documents/selenium/selenium/src/main/resources/dataTestNopCommerce.txt";
        FileReader fileReader= new FileReader(TestFile);
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
        waitForElementClickable(driver, "//a[@class='ico-login']");
        clickToElement(driver, "//a[@class='ico-login']");

        waitForElementClickable(driver, "//button[contains(@class,'login-button')]");
        clickToElement(driver, "//button[contains(@class,'login-button')]");

        Assert.assertEquals(getElementText(driver, "//span[contains(@class,'field-validation-error')]"), "Please enter your email");
    }

    @Test
    public void TC_02_Login_With_Invalid_Email() {
        waitForElementClickable(driver, "//a[@class='ico-login']");
        clickToElement(driver, "//a[@class='ico-login']");

        senKeysToElement(driver, "//input[contains(@id,'Email')]", "invalidemail");
        waitForElementClickable(driver, "//button[contains(@class,'login-button')]");
        clickToElement(driver, "//button[contains(@class,'login-button')]");
        Assert.assertEquals(getElementText(driver, "//span[contains(@class,'field-validation-error')]"), "Wrong email");
    }

    @Test
    public void TC_03_Login_With_Unregister_Email() {
        waitForElementClickable(driver, "//a[@class='ico-login']");
        clickToElement(driver, "//a[@class='ico-login']");

        senKeysToElement(driver, "//input[contains(@id,'Email')]", emailAddress);
        senKeysToElement(driver, "//input[contains(@id,'Password')]", passWord);

        waitForElementClickable(driver, "//button[contains(@class,'login-button')]");
        clickToElement(driver, "//button[contains(@class,'login-button')]");
        Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error validation-summary-errors')]"),
                "Login was unsuccessful. Please correct the errors and try again.\n" +
                        "No customer account found");
    }

    @Test
    public void TC_04_Login_With_Registered_Email_Empty_Password() {
        waitForElementClickable(driver, "//a[@class='ico-login']");
        clickToElement(driver, "//a[@class='ico-login']");

        senKeysToElement(driver, "//input[contains(@id,'Email')]", registerEmail);
//        senKeysToElement(driver, "//input[contains(@id,'Password')]", "");

        waitForElementClickable(driver, "//button[contains(@class,'login-button')]");
        clickToElement(driver, "//button[contains(@class,'login-button')]");
        Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error validation-summary-errors')]"),
                "Login was unsuccessful. Please correct the errors and try again.\n" +
                        "The credentials provided are incorrect");
    }

    @Test
    public void TC_05_Login_With_Registered_Email_Wrong_Password() {
        waitForElementClickable(driver, "//a[@class='ico-login']");
        clickToElement(driver, "//a[@class='ico-login']");

        senKeysToElement(driver, "//input[contains(@id,'Email')]", registerEmail);
        senKeysToElement(driver, "//input[contains(@id,'Password')]", "123456");

        waitForElementClickable(driver, "//button[contains(@class,'login-button')]");
        clickToElement(driver, "//button[contains(@class,'login-button')]");
        Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error validation-summary-errors')]"),
                "Login was unsuccessful. Please correct the errors and try again.\n" +
                        "The credentials provided are incorrect");
    }

    @Test
    public void TC_06_Login_With_Registered_Email_True_Password() {
        waitForElementClickable(driver, "//a[@class='ico-login']");
        clickToElement(driver, "//a[@class='ico-login']");

        senKeysToElement(driver, "//input[contains(@id,'Email')]", registerEmail);
        senKeysToElement(driver, "//input[contains(@id,'Password')]", passWord);

        waitForElementClickable(driver, "//button[contains(@class,'login-button')]");
        clickToElement(driver, "//button[contains(@class,'login-button')]");

        waitForElementClickable(driver, "//a[contains(@class,'ico-account')]");
        clickToElement(driver, "//a[contains(@class,'ico-account')]");

        Assert.assertEquals(getTitle(driver), "nopCommerce demo store. Account");
    }

    @AfterTest
    public void afterClass(){
        driver.quit();
    }


    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }
}
