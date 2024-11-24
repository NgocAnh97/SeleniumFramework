package testcases.com.nopcommerce.admin;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.time.Duration;
import java.util.Random;

public class Admin_01_Login extends BasePage {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String emailAddress, registerEmail, password;

    @BeforeClass
    public void beforeClass() throws IOException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--user-data-dir=/Users/mastery/Library/Application Support/Google/Chrome/");
        chromeOptions.addArguments("--profile-directory=Profile 10");
        driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        emailAddress = "test" + generateFakeNumber() + "@mail.com";

        driver.get("https://demo.nopcommerce.com/");
        Register_Valid_Data();
        readFile();
    }

    public void Register_Valid_Data() throws IOException {
        waitForElementClickable(driver, "class=ico-register");
        clickToElement(driver, "class=ico-register");

        sendKeysToElement(driver, "id=FirstName", "testFirstName");
        sendKeysToElement(driver, "id=LastName", "testLastName");
        sendKeysToElement(driver, "id=Email", emailAddress);
        sendKeysToElement(driver, "id=Password", password);
        sendKeysToElement(driver, "id=ConfirmPassword", password);

        waitForElementClickable(driver, "id=register-button");
        clickToElement(driver, "id=register-button");

        System.out.println(emailAddress);
        Assert.assertEquals(getElementText(driver, "class=result"),
                "Your registration completed");
        waitForElementClickable(driver, "class=ico-logout");
        clickToElement(driver, "class=ico-logout");

        writeFile(emailAddress, password);
    }

    @Test
    public void TC_01_Login_With_Empty_Data() {
        waitForElementClickable(driver, "class=ico-login");
        clickToElement(driver, "class=ico-login");

        waitForElementClickable(driver, "xpath=//button[contains(@class,'login-button')]");
        clickToElement(driver, "xpath=//button[contains(@class,'login-button')]");

        Assert.assertEquals(getElementText(driver, "xpath=//span[contains(@class,'field-validation-error')]"),
                "Please enter your email");
    }

    @Test
    public void TC_02_Login_With_Invalid_Email() {
        waitForElementClickable(driver, "class=ico-login");
        clickToElement(driver, "class=ico-login");

        sendKeysToElement(driver, "xpath=//input[contains(@id,'Email')]", "invalidemail");
        waitForElementClickable(driver, "xpath=//button[contains(@class,'login-button')]");
        clickToElement(driver, "xpath=//button[contains(@class,'login-button')]");
        Assert.assertEquals(getElementText(driver, "class=field-validation-error"),
                "Please enter a valid email address.");
    }

    @Test
    public void TC_03_Login_With_Unregister_Email() {
        waitForElementClickable(driver, "class=ico-login");
        clickToElement(driver, "class=ico-login");

        sendKeysToElement(driver, "xpath=//input[contains(@id,'Email')]", emailAddress);
        sendKeysToElement(driver, "xpath=//input[contains(@id,'Password')]", password);

        waitForElementClickable(driver, "xpath=//button[contains(@class,'login-button')]");
        clickToElement(driver, "xpath=//button[contains(@class,'login-button')]");
        Assert.assertEquals(getElementText(driver, "xpath=//div[contains(@class,'message-error validation-summary-errors')]"),
                "Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
    }

    @Test
    public void TC_04_Login_With_Registered_Email_Empty_Password() {
        waitForElementClickable(driver, "class=ico-login");
        clickToElement(driver, "class=ico-login");

        sendKeysToElement(driver, "xpath=//input[contains(@id,'Email')]", registerEmail);

        waitForElementClickable(driver, "xpath=//button[contains(@class,'login-button')]");
        clickToElement(driver, "xpath=//button[contains(@class,'login-button')]");
        Assert.assertEquals(getElementText(driver, "xpath=//div[contains(@class,'message-error validation-summary-errors')]"),
                "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
    }

    @Test
    public void TC_05_Login_With_Registered_Email_Wrong_Password() {
        waitForElementClickable(driver, "class=ico-login");
        clickToElement(driver, "class=ico-login");

        sendKeysToElement(driver, "Xpath=//input[contains(@id,'Email')]", registerEmail);
        sendKeysToElement(driver, "xpath=//input[contains(@id,'Password')]", "123456");

        waitForElementClickable(driver, "xpath=//button[contains(@class,'login-button')]");
        clickToElement(driver, "xpath=//button[contains(@class,'login-button')]");
        Assert.assertEquals(getElementText(driver, "xpath=//div[contains(@class,'message-error validation-summary-errors')]"),
                "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
    }

    @Test
    public void TC_06_Login_With_Registered_Email_True_Password() {
        waitForElementClickable(driver, "class=ico-login");
        clickToElement(driver, "class=ico-login");

        sendKeysToElement(driver, "xpath=//input[contains(@id,'Email')]", registerEmail);
        sendKeysToElement(driver, "xpath=//input[contains(@id,'Password')]", password);

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

    public void readFile() throws IOException {
        String TestFile = projectPath + "/src/main/resources/dataTestNopCommerce.txt";
        FileReader fileReader = new FileReader(TestFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String Content = "";
        while ((Content = bufferedReader.readLine()) != null) {
            String[] index = Content.split(";");
            registerEmail = index[0];
            password = index[1];
        }
    }

    public void writeFile(String emailAddress, String passWord) throws IOException {
        String TestFile = projectPath + "/src/main/resources/dataTestNopCommerce.txt";

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
