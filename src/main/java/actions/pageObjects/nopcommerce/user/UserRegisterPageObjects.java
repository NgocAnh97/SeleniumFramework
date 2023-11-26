package actions.pageObjects.nopcommerce.user;

import actions.commons.BasePage;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;

public class UserRegisterPageObjects extends BasePage {

    private WebDriver driver;

    public UserRegisterPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public UserRegisterPageObjects clickToRegisterButton() {
        waitForElementClickable(driver, GlobalConstants.RegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, GlobalConstants.RegisterPageUI.REGISTER_BUTTON);
        return PageGeneratorManager.getUserRegisterPage(driver);
    }

    public UserHomePageObjects clickToLogoutButton() {
        waitForElementClickable(driver, GlobalConstants.RegisterPageUI.LOGOUT_LINK);
        clickToElement(driver, GlobalConstants.RegisterPageUI.LOGOUT_LINK);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    public String getFirstnameErrorMessage() {
        waitForElementVisible(driver, GlobalConstants.RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
        return getElementText(driver, GlobalConstants.RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
    }

    public String getLastnameErrorMessage() {
        waitForElementVisible(driver, GlobalConstants.RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
        return getElementText(driver, GlobalConstants.RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
    }

    public String getEmailErrorMessage() {
        waitForElementVisible(driver, GlobalConstants.RegisterPageUI.EMAIL_ERROR_MESSAGE);
        return getElementText(driver, GlobalConstants.RegisterPageUI.EMAIL_ERROR_MESSAGE);
    }

    public String getPasswordErrorMessage() {
        waitForElementVisible(driver, GlobalConstants.RegisterPageUI.PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, GlobalConstants.RegisterPageUI.PASSWORD_ERROR_MESSAGE);
    }

    public String getConfirmPasswordErrorMessage() {
        waitForElementVisible(driver, GlobalConstants.RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, GlobalConstants.RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    public void inputToFirstnameTextbox(String testFirstName) {
        waitForElementVisible(driver, GlobalConstants.RegisterPageUI.FIRST_NAME_TEXTBOX);
        senKeysToElement(driver, GlobalConstants.RegisterPageUI.FIRST_NAME_TEXTBOX, testFirstName);
    }

    public void inputToLastnameTextbox(String testLastName) {
        waitForElementVisible(driver, GlobalConstants.RegisterPageUI.LAST_NAME_TEXTBOX);
        senKeysToElement(driver, GlobalConstants.RegisterPageUI.LAST_NAME_TEXTBOX, testLastName);
    }

    public void inputToEmailTextbox(String email) {
        waitForElementVisible(driver, GlobalConstants.RegisterPageUI.EMAIL_TEXTBOX);
        senKeysToElement(driver, GlobalConstants.RegisterPageUI.EMAIL_TEXTBOX, email);
    }

    public void inputToPasswordTextbox(String passWord) {
        waitForElementVisible(driver, GlobalConstants.RegisterPageUI.PASSWORD_TEXTBOX);
        senKeysToElement(driver, GlobalConstants.RegisterPageUI.PASSWORD_TEXTBOX, passWord);
    }

    public void inputToConfirmPasswordTextbox(String confirmPassWord) {
        waitForElementVisible(driver, GlobalConstants.RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        senKeysToElement(driver, GlobalConstants.RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassWord);
    }

    public String getRegisterCompletedResultMessage() {
        waitForElementVisible(driver, GlobalConstants.RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, GlobalConstants.RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public String getExistedEmailErrorMessage() {
        waitForElementVisible(driver, GlobalConstants.RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
        return getElementText(driver, GlobalConstants.RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
    }

    public String getNumberPasswordCharacterErrorMessage() {
        waitForElementVisible(driver, GlobalConstants.RegisterPageUI.PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, GlobalConstants.RegisterPageUI.PASSWORD_ERROR_MESSAGE);
    }

    public String getPasswordNotMatchErrorMessage() {
        waitForElementVisible(driver, GlobalConstants.RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, GlobalConstants.RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }
}
