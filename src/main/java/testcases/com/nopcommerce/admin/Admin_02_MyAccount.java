package testcases.com.nopcommerce.admin;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Admin_02_MyAccount extends BasePage {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String emailAddress, registerEmail, passWord = "123456aA@@";

    String genderFemaleInputFieldLocator = "//span[@class='female']";
    String firstNameInputFieldLocator = "//input[@id='FirstName']";
    String lastNameInputFieldLocator = "//input[@id='LastName']";
    String dateSelectFieldLocator = "//select[@name='DateOfBirthDay']";
    String monthSelectFieldLocator = "//select[@name='DateOfBirthMonth']";
    String yearSelectFieldLocator = "//select[@name='DateOfBirthYear']";
    String emailInputFieldLocator = "//input[@id='Email']";
    String companyInputFieldLocator = "//input[@id='Company']";
    String submitButtonFieldLocator = "//button[@id='save-info-button']";

    @BeforeClass
    public void beforeClass() throws IOException {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

        driver = new FirefoxDriver();

        emailAddress = "test" + generateFakeNumber() + "@yopmail.com";
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        Register_Valid_Data();
        readFile();
        Login_With_Registered_Email_True_Password();
    }

    public void Register_Valid_Data() throws IOException {
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

    public void readFile() throws IOException {
        String TestFile = "/Users/mastery/Documents/selenium/selenium/src/main/resources/dataTestNopCommerce.txt";
        FileReader fileReader = new FileReader(TestFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String Content = "";
        while ((Content = bufferedReader.readLine()) != null) {
            String[] index = Content.split(";");
            registerEmail = index[0];
            passWord = index[1];
        }
    }

    public void Login_With_Registered_Email_True_Password() throws IOException {
        waitForElementClickable(driver, "//a[@class='ico-login']");
        clickToElement(driver, "//a[@class='ico-login']");

        senKeysToElement(driver, "//input[contains(@id,'Email')]", registerEmail);
        senKeysToElement(driver, "//input[contains(@id,'Password')]", passWord);

        waitForElementClickable(driver, "//button[contains(@class,'login-button')]");
        clickToElement(driver, "//button[contains(@class,'login-button')]");

        waitForElementClickable(driver, "//a[contains(@class,'ico-account')]");
        clickToElement(driver, "//a[contains(@class,'ico-account')]");

        Assert.assertEquals(getTitle(driver), "nopCommerce demo store. Account");
        writeFile(emailAddress, passWord);
    }

    @Test
    public void TC_01_Update_Customer_Info() {
        waitForElementClickable(driver, genderFemaleInputFieldLocator);
        clickToElement(driver, genderFemaleInputFieldLocator);

        senKeysToElement(driver, firstNameInputFieldLocator, "Automation");
        senKeysToElement(driver, lastNameInputFieldLocator, "FC");

        selectItemInDefaultDropdown(driver, dateSelectFieldLocator, "1");
        selectItemInDefaultDropdown(driver, monthSelectFieldLocator, "3");
        selectItemInDefaultDropdown(driver, yearSelectFieldLocator, "1997");

        senKeysToElement(driver, emailInputFieldLocator, "qwert.vn@gmail.com");
        senKeysToElement(driver, companyInputFieldLocator, "Automation FC");

        clickToElement(driver, submitButtonFieldLocator);

//        Assert.assertTrue(isElementSelected(driver, genderFemaleInputFieldLocator));
        Assert.assertEquals(getValue(driver,firstNameInputFieldLocator),"Automation");
        Assert.assertEquals(getElementCssValue(driver,lastNameInputFieldLocator,"value"),"FC");
//        Assert.assertEquals(getElementText(driver, firstNameInputFieldLocator), "Automation");
//        Assert.assertEquals(getElementText(driver, lastNameInputFieldLocator), "FC");
        Assert.assertEquals(getSelectedItemDefaultDropdown(driver, dateSelectFieldLocator), "1");
        Assert.assertEquals(getSelectedItemDefaultDropdown(driver, monthSelectFieldLocator), "3");
        Assert.assertEquals(getSelectedItemDefaultDropdown(driver, yearSelectFieldLocator), "1997");
        Assert.assertEquals(getElementCssValue(driver,emailInputFieldLocator,"value"),"automationfc.vn@gmail.com");
        Assert.assertEquals(getElementCssValue(driver,companyInputFieldLocator,"value"),"Automation FC");
//        Assert.assertEquals(getElementText(driver, emailInputFieldLocator), "automationfc.vn@gmail.com");
//        Assert.assertEquals(getElementText(driver, companyInputFieldLocator), "Automation FC");
    }

    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }

}
