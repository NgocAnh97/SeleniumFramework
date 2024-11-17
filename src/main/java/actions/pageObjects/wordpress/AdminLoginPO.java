package actions.pageObjects.wordpress;

import actions.commons.BasePage;
import interfaces.pageUIs.wordpress.AdminLoginPageUI;
import org.openqa.selenium.WebDriver;

public class AdminLoginPO extends BasePage {
    WebDriver driver;

    public AdminLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToLoginButtonInHeader() {
        waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
    }

    public void enterToEmailTextbox(String adminEmail) {
        waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
        sendKeysToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, adminEmail);
    }

    public void clickToContinueButton() {
        waitForElementClickable(driver, AdminLoginPageUI.CONTINUE_BUTTON);
        clickToElement(driver, AdminLoginPageUI.CONTINUE_BUTTON);
    }

    public void enterToPasswordTextbox(String adminPassword) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendKeysToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, adminPassword);
    }

    public AdminDashboardPO clickToLoginButton() {
        waitForElementClickable(driver, AdminLoginPageUI.SUBMIT_BUTTON);
        clickToElement(driver, AdminLoginPageUI.SUBMIT_BUTTON);
        return PageGeneratorManager.getAdminDashboardPage(driver);
    }
}
