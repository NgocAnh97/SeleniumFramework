package testcases.com.liveguru.user;

import actions.commons.BasePage;
import actions.commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import actions.pageObjects.liveguru.*;

import java.io.*;
import java.util.Random;

public class User_Login_Page_Object extends BaseTest {
    private WebDriver driver;
    String emailAddress, registerEmail, passWord = "123456";

    HomePageObjects homePage;
    LoginPageObjects loginPage;
    AccountInfoPageObjects accountInfoPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeTest(String browserName) throws IOException{
        driver = getBrowser(browserName);

        homePage = PageGeneratorManager.getHomePage(driver);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        accountInfoPage = PageGeneratorManager.getAccountPage(driver);

        homePage.clickToMyAccountLink();
        loginPage = homePage.clickToLoginLink();
        readFile();

        emailAddress = "test"+ generateFakeNumber()+"@yopmail.com";
    }

    @Test
    public void Login_01_Empty_Data(){
        loginPage.clickToLoginButton();
        Assert.assertEquals(loginPage.getRequiredEmailErrorMessage(),"This is a required field.");
        Assert.assertEquals(loginPage.getRequiredPasswordErrorMessage(),"This is a required field.");
    }

//    @Test
    //Fail to verify
    public void Login_02_Invalid_Email(){
        loginPage.inputToEmail("invalidEmail");
        loginPage.inputToPassword(passWord);
        loginPage.clickToLoginButton();
        Assert.assertEquals(loginPage.getRequiredEmailErrorMessage(),"");
    }

    @Test
    public void Login_03_Invalid_Password(){
        loginPage.inputToEmail(emailAddress);
        loginPage.inputToPassword("123");
        loginPage.clickToLoginButton();
        Assert.assertEquals(loginPage.getInvalidPasswordErrorMessage(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void Login_04_Unregister_Email(){
        loginPage.inputToEmail(emailAddress);
        loginPage.inputToPassword(passWord);
        loginPage.clickToLoginButton();
        Assert.assertEquals(loginPage.getUnregisterEmailMessage(),"Invalid login or password.");
    }

    @Test
    public void Login_05_Valid_Data(){
        loginPage.inputToEmail(registerEmail);
        loginPage.inputToPassword(passWord);
        accountInfoPage = loginPage.clickToLoginButton();
        Assert.assertEquals(accountInfoPage.getTitleAccountPage(),"My Account");

        accountInfoPage.clickToAccountLink();
        homePage = accountInfoPage.clickToLogoutLink();
        Assert.assertEquals(homePage.getTitleHomePage(),"Magento Commerce");
    }

    public void readFile() throws IOException {
        String TestFile = "/Users/mastery/Desktop/Automation_Framework/hybrid-framework-nopcommerce/resources/dataTestLiveGuru.txt";
        FileReader fileReader = new FileReader(TestFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String Content = "";
        while ((Content = bufferedReader.readLine()) != null) {
            String[] index = Content.split(";");
            registerEmail = index[0];
            passWord = index[1];
        }
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
