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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Random;

//@Listeners(actions.commons.MethodListener.class)
public class Level_09_Assert extends BaseTest {
    @Parameters({"browsser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        driver = getBrowserDriver(browserName, environmentName);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);
        userAccountPage = PageGeneratorManager.getUserMyAccountPage(driver);

        adminEmailAddress = "admin@yourstore.com";
        adminPassword = "admin";
        password = "123456aA@@";
        userEmailAddress = "test" + generateFakeNumber() + "@mail.com";
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
        verifyEquals(registerPage.getRegisterCompletedResultMessage(), "Your registration completed");

        writeFile(userEmailAddress, password);

        readFile();
        userLoginPage = userHomePage.openLoginPage();
        userHomePage = userLoginPage.loginAsUser(userEmailAddress, password);
        verifyTrue(userHomePage.isMyAccountLinkDisplayed());
        userHomePage = userAccountPage.clickToLogout();
    }

    @Test
    public void Role_02_Admin() {
        adminLoginPage = userHomePage.openAdminLoginPage();

        adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
        verifyTrue(adminDashboardPage.isDashboardLinkDisplayed());
        adminDashboardPage.clickToElementByJS(driver, GlobalConstants.LOGOUT_LINK);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void writeFile(String emailAddress, String passWord) throws IOException {
        File testFile = new File("/Users/mastery/Documents/selenium/selenium/src/main/resources/dataTestNopCommerce.txt");
        testFile.createNewFile();

        FileWriter fileWriter = new FileWriter(testFile);
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
            password = index[1];
        }
    }

    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }

    private WebDriver driver;
    String userEmailAddress, adminEmailAddress, adminPassword, registerEmail, password;
    private UserLoginPageObjects userLoginPage;
    private UserRegisterPageObjects registerPage;
    private UserHomePageObjects userHomePage;
    private UserAccountPageObjects userAccountPage;
    private AdminDashboardPageObjects adminDashboardPage;
    private AdminLoginPageObjects adminLoginPage;
}
