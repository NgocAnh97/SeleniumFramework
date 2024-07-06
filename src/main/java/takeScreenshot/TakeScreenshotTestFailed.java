package takeScreenshot;

import actions.commons.BasePage;
import actions.pageObjects.nopcommerce.user.UserHomePageObjects;
import actions.pageObjects.nopcommerce.user.UserLoginPageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;
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

//@Listeners(TestListener.class)
public class TakeScreenshotTestFailed extends BasePage {
    private WebDriver driver;
    String emailAddress, registerEmail, passWord;
    private UserHomePageObjects homePage;
    private UserLoginPageObjects loginPage;

    @BeforeClass
    public void beforeClass() throws IOException {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/");
        homePage = new UserHomePageObjects(driver);
        loginPage = new UserLoginPageObjects(driver);
        emailAddress = "test" + generateFakeNumber() + "@yopmail.com";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void readFile() throws IOException {
        String TestFile = System.getProperty("user.dir") + "/resources/dataTestNopCommerce.txt";
        FileReader fileReader = new FileReader(TestFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String Content = "";
        while ((Content = bufferedReader.readLine()) != null) {
            String[] index = Content.split(";");
            registerEmail = index[0];
            passWord = index[1];
        }
    }

    @Test(priority = 2)
    public void Login_01_Login_Empty_Data() {
        System.out.println("Login_01 - Step 01: Click to Login link");
        homePage.openLoginPage();

        System.out.println("Login_01 - Step 02: Click to Login button");
        loginPage.openLoginPage();

        System.out.println("Login_01 - Step 03: Verify error message displayed");
        Assert.assertEquals(loginPage.getFieldValidationErrorMessage(), "? Please enter your email");
    }

    @Test(priority = 1)
    public void Login_02_Login_Invalid_Data() {
        System.out.println("Login_02 - Step 01: Click to Login link");
        homePage.openLoginPage();

        System.out.println("Login_02 - Step 02: Input to required fields");
        loginPage.inputToEmailTextbox("invalidEmail");

        System.out.println("Login_02 - Step 03: Click to Login button");
        loginPage.openLoginPage();

        System.out.println("Login_02 - Step 04: Verify error message displayed");
        Assert.assertEquals(loginPage.getFieldValidationErrorMessage(), "Wrong email");
    }

    @Test
    public void Login_03_Login_Unregister_Email() {
        System.out.println("Login_03 - Step 01: Click to Login link");
        homePage.openLoginPage();

        System.out.println("Login_03 - Step 02: Input to required fields");
        loginPage.inputToEmailTextbox("unregisterEmail@yopmail.com");
        loginPage.inputToPasswordTextbox(passWord);

        System.out.println("Login_03 - Step 03: Click to Login button");
        loginPage.openLoginPage();

        System.out.println("Login_03 - Step 04: Verify error message displayed");
        Assert.assertEquals(loginPage.getLoginErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found");
    }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }

    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }

    public WebDriver getWedDriver() {
        return this.driver;
    }
}
