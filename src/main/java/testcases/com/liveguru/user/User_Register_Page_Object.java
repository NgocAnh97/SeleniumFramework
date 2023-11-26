package testcases.com.liveguru.user;

import actions.commons.BaseTest;
import actions.commons.LiveGuruConstants;
import org.eclipse.jetty.util.IO;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import actions.pageObjects.liveguru.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class User_Register_Page_Object extends BaseTest {
    private WebDriver driver;
    String emailAddress,passWord = "123456";
    HomePageObjects homePage;
    RegisterPageObjects registerPage;
    AccountInfoPageObjects accountInfoPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        driver = getBrowser(browserName);
        homePage = PageGeneratorManager.getHomePage(driver);
        registerPage = PageGeneratorManager.getRegisterPage(driver);
        accountInfoPage = PageGeneratorManager.getAccountPage(driver);

        homePage.clickToMyAccountLink();
        registerPage = homePage.clickToRegisterLink();

        emailAddress = "test"+ generateFakeNumber()+"@yopmail.com";
    }

    @Test
    public void Register_01_Empty_Data(){
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRequiredFirstNameErrorMessage(),"This is a required field.");
        Assert.assertEquals(registerPage.getRequiredLastNameErrorMessage(),"This is a required field.");
        Assert.assertEquals(registerPage.getRequiredEmailErrorMessage(),"This is a required field.");
        Assert.assertEquals(registerPage.getRequiredPasswordErrorMessage(),"This is a required field.");
        Assert.assertEquals(registerPage.getRequiredConfirmPasswordErrorMessage(),"This is a required field.");
    }

//    @Test
    //Fail
    public void Register_02_Invalid_Email(){
        homePage.clickToMyAccountLink();
        registerPage = homePage.clickToRegisterLink();
        registerPage.inputToFirstNameTextbox("testFirstName");
        registerPage.inputToLastNameTextbox("testLastName");
        registerPage.inputToEmailTextbox("invalidEmail");
        registerPage.inputToPasswordTextbox(passWord);
        registerPage.inputToConfirmPasswordTextbox(passWord);
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRequiredEmailErrorMessage(),"");
    }

    @Test
    public void Register_03_Invalid_Password(){
        registerPage.inputToFirstNameTextbox("testFirstName");
        registerPage.inputToLastNameTextbox("testLastName");
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox("12345");
        registerPage.inputToConfirmPasswordTextbox("12345");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getInvalidEmailErrorMessage(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void Register_04_Invalid_Confirm_Password(){
        registerPage.inputToFirstNameTextbox("testFirstName");
        registerPage.inputToLastNameTextbox("testLastName");
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(passWord);
        registerPage.inputToConfirmPasswordTextbox("diff"+ passWord);
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getInvalidConfirmEmailErrorMessage(),"Please make sure your passwords match.");
    }

    @Test
    public void Register_05_Valid_Data() throws IOException {
        registerPage.inputToFirstNameTextbox("testFirstName");
        registerPage.inputToLastNameTextbox("testLastName");
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(passWord);
        registerPage.inputToConfirmPasswordTextbox(passWord);
        accountInfoPage = registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Thank you for registering with Main Website Store.");
        writeFile(emailAddress,passWord);
    }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }

    public void writeFile(String emailAddress, String passWord) throws IOException {
        String TestFile = "/Users/mastery/Desktop/Automation_Framework/hybrid-framework-nopcommerce/resources/dataTestLiveGuru.txt";
        File testFile = new File(TestFile);
        testFile.createNewFile();

        FileWriter fileWriter = new FileWriter(TestFile);
        BufferedWriter bufferReader = new BufferedWriter(fileWriter);
        bufferReader.write(emailAddress);
        bufferReader.write(";");
        bufferReader.write(passWord);
        bufferReader.close();
    }

    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }
}
