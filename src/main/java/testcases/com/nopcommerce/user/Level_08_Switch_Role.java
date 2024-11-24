package testcases.com.nopcommerce.user;

import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.nopcommerce.admin.AdminDashboardPageObjects;
import actions.pageObjects.nopcommerce.admin.AdminLoginPageObjects;
import actions.pageObjects.nopcommerce.user.UserAccountPageObjects;
import actions.pageObjects.nopcommerce.user.UserHomePageObjects;
import actions.pageObjects.nopcommerce.user.UserLoginPageObjects;
import actions.pageObjects.nopcommerce.user.UserRegisterPageObjects;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Random;

public class Level_08_Switch_Role extends BaseTest {
    @Parameters({"browser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        driver = getBrowserDriver(browserName, environmentName);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);
        userAccountPage = PageGeneratorManager.getUserMyAccountPage(driver);

        adminEmailAddress = "admin@yourstore.com";
        adminPassWord = "admin";
        password = "123456aA@@";
        userEmailAddress = "test" + generateFakeNumber() + "@yopmail.com";
    }

    @Test
    public void Role_01_User() throws IOException {
        registerPage = userHomePage.openRegisterPage();
        registerPage.inputToFirstnameTextbox("testFirstName");
        registerPage.inputToLastnameTextbox("testLastName");
        registerPage.inputToEmailTextbox(userEmailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmPasswordTextbox(password);

        registerPage.clickToRegisterButton();

        System.out.println(userEmailAddress);
        Assert.assertEquals(registerPage.getRegisterCompletedResultMessage(), "Your registration completed");

        writeFile(userEmailAddress, password);

        readFile();
        userLoginPage = userHomePage.openLoginPage();
        userHomePage = userLoginPage.loginAsUser(userEmailAddress, password);
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
        userHomePage = userAccountPage.clickToLogout();
    }

    @Test
    public void Role_02_Admin() {
        adminLoginPage = userHomePage.openAdminLoginPage();

        adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassWord);
        Assert.assertTrue(adminDashboardPage.isDashboardLinkDisplayed());
        adminDashboardPage.clickToElementByJS(driver, GlobalConstants.LOGOUT_LINK);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void writeFile(String emailAddress, String passWord) throws IOException {
        File testFile = new File(TestDir);
        testFile.createNewFile();

        FileWriter fileWriter = new FileWriter(TestDir);
        BufferedWriter bufferReader = new BufferedWriter(fileWriter);
        bufferReader.write(emailAddress);
        bufferReader.write(";");
        bufferReader.write(passWord);
        bufferReader.close();
    }

    public void readFile() throws IOException {
        FileReader fileReader = new FileReader(TestDir);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String Content = "";
        while ((Content = bufferedReader.readLine()) != null) {
            String[] index = Content.split(";");
            registerEmail = index[0];
            password = index[1];
        }
    }

    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }

    String TestDir = "/Users/mastery/Documents/selenium/selenium/src/main/resources/dataTestNopCommerce.txt";
    private WebDriver driver;
    String userEmailAddress, adminEmailAddress,
            adminPassWord, registerEmail, password;
    private UserLoginPageObjects userLoginPage;
    private UserRegisterPageObjects registerPage;
    private UserHomePageObjects userHomePage;
    private UserAccountPageObjects userAccountPage;
    private AdminDashboardPageObjects adminDashboardPage;
    private AdminLoginPageObjects adminLoginPage;
}
