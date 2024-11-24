package testcases.com.nopcommerce.admin;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.time.Duration;
import java.util.Random;

public class Admin_02_MyAccount extends BasePage {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String emailAddress, registerEmail, password = "123456aA@@";

    String genderFemaleInputFieldLocator = "class=female";
    String firstNameInputFieldLocator = "id=FirstName";
    String lastNameInputFieldLocator = "id=LastName";
    String dateSelectFieldLocator = "xpath=//select[@name='DateOfBirthDay']";
    String monthSelectFieldLocator = "xpath=//select[@name='DateOfBirthMonth']";
    String yearSelectFieldLocator = "name=DateOfBirthYear";
    String emailInputFieldLocator = "id=Email";
    String companyInputFieldLocator = "id=Company";
    String submitButtonFieldLocator = "id=save-info-button";

    @BeforeClass
    public void beforeClass() throws IOException {
        driver = new FirefoxDriver();

        emailAddress = "test" + generateFakeNumber() + "@mail.com";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://demo.nopcommerce.com/");

        Register_Valid_Data();
        readFile();
        Login_With_Registered_Email_True_Password();
    }

    @Test
    public void TC_Update_Customer_Info() {
        waitForElementClickable(driver, genderFemaleInputFieldLocator);
        clickToElement(driver, genderFemaleInputFieldLocator);

        sendKeysToElement(driver, firstNameInputFieldLocator, "Automation");
        sendKeysToElement(driver, lastNameInputFieldLocator, "FC");

        selectItemInDropdown(driver, dateSelectFieldLocator, "1");
        selectItemInDropdown(driver, monthSelectFieldLocator, "3");
        selectItemInDropdown(driver, yearSelectFieldLocator, "1997");

        sendKeysToElement(driver, emailInputFieldLocator, "test.vn@gmail.com");
        sendKeysToElement(driver, companyInputFieldLocator, "Automation FC");

        clickToElement(driver, submitButtonFieldLocator);

//        Assert.assertTrue(isElementSelected(driver, genderFemaleInputFieldLocator));
        Assert.assertEquals(getValue(driver, firstNameInputFieldLocator), "Automation");
        Assert.assertEquals(getValue(driver, lastNameInputFieldLocator), "FC");
        Assert.assertEquals(getSelectedItemDefaultDropdown(driver, dateSelectFieldLocator), "1");
        Assert.assertEquals(getSelectedItemDefaultDropdown(driver, monthSelectFieldLocator), "March");
        Assert.assertEquals(getSelectedItemDefaultDropdown(driver, yearSelectFieldLocator), "1997");
        Assert.assertEquals(getValue(driver, emailInputFieldLocator), "test.vn@gmail.com");
        Assert.assertEquals(getValue(driver, companyInputFieldLocator), "Automation FC");
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
        Assert.assertEquals(getElementText(driver, "class=result"), "Your registration completed");
        waitForElementClickable(driver, "class=ico-logout");
        clickToElement(driver, "class=ico-logout");

        writeFile(emailAddress, password);
    }

    public void Login_With_Registered_Email_True_Password() throws IOException {
        waitForElementClickable(driver, "class=ico-login");
        clickToElement(driver, "class=ico-login");

        sendKeysToElement(driver, "xpath=//input[contains(@id,'Email')]", registerEmail);
        sendKeysToElement(driver, "xpath=//input[contains(@id,'Password')]", password);

        waitForElementClickable(driver, "xpath=//button[contains(@class,'login-button')]");
        clickToElement(driver, "xpath=//button[contains(@class,'login-button')]");

        waitForElementClickable(driver, "xpath=//a[contains(@class,'ico-account')]");
        clickToElement(driver, "xpath=//a[contains(@class,'ico-account')]");

        Assert.assertEquals(getTitle(driver), "nopCommerce demo store. Account");
        writeFile(emailAddress, password);
    }

    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
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
}
