package actions.pageObjects.nopcommerce.admin;

import actions.commons.BasePage;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;

public class AdminLoginPageObjects extends BasePage {
    private WebDriver driver;

    public AdminLoginPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToEmailTextbox(String email) {
        waitForElementVisible(driver, GlobalConstants.LoginPageUI.EMAIL_TEXTBOX);
        sendKeysToElement(driver, GlobalConstants.LoginPageUI.EMAIL_TEXTBOX, email);
    }

    public void inputToPasswordTextbox(String passWord) {
        waitForElementVisible(driver, GlobalConstants.LoginPageUI.PASSWORD_TEXTBOX);
        sendKeysToElement(driver, GlobalConstants.LoginPageUI.PASSWORD_TEXTBOX, passWord);
    }

    public AdminDashboardPageObjects openLoginPage() {
        waitForElementClickable(driver, GlobalConstants.LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, GlobalConstants.LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getAdminDashboardPage(driver);
    }

    public AdminDashboardPageObjects loginAsAdmin(String adminEmailAddress, String passWord) {
        inputToEmailTextbox(adminEmailAddress);
        inputToPasswordTextbox(passWord);
        return openLoginPage();
    }
}
