package actions.pageObjects.liveguru;

import actions.commons.BasePage;
import actions.commons.LiveGuruConstants;
import org.openqa.selenium.WebDriver;

public class RegisterPageObjects extends BasePage {
    private WebDriver driver;

    public RegisterPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public AccountInfoPageObjects clickToRegisterButton() {
        waitForElementClickable(driver, LiveGuruConstants.RegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, LiveGuruConstants.RegisterPageUI.REGISTER_BUTTON);
        return PageGeneratorManager.getAccountPage(driver);
    }

    public void inputToFirstNameTextbox(String testFirstName) {
        waitForElementVisible(driver, LiveGuruConstants.RegisterPageUI.FIRST_NAME_TEXTBOX);
        senKeysToElement(driver, LiveGuruConstants.RegisterPageUI.FIRST_NAME_TEXTBOX, testFirstName);
    }

    public void inputToLastNameTextbox(String testLastName) {
        waitForElementVisible(driver, LiveGuruConstants.RegisterPageUI.LAST_NAME_TEXTBOX);
        senKeysToElement(driver, LiveGuruConstants.RegisterPageUI.LAST_NAME_TEXTBOX, testLastName);
    }

    public void inputToEmailTextbox(String invalidEmail) {
        waitForElementVisible(driver, LiveGuruConstants.RegisterPageUI.EMAIL_TEXTBOX);
        senKeysToElement(driver, LiveGuruConstants.RegisterPageUI.EMAIL_TEXTBOX, invalidEmail);
    }

    public void inputToPasswordTextbox(String passWord) {
        waitForElementVisible(driver, LiveGuruConstants.RegisterPageUI.PASSWORD_TEXTBOX);
        senKeysToElement(driver, LiveGuruConstants.RegisterPageUI.PASSWORD_TEXTBOX, passWord);
    }

    public void inputToConfirmPasswordTextbox(String passWord) {
        waitForElementVisible(driver, LiveGuruConstants.RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        senKeysToElement(driver, LiveGuruConstants.RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, passWord);
    }

    public String getRequiredFirstNameErrorMessage() {
        waitForElementVisible(driver, LiveGuruConstants.RegisterPageUI.REQUIRED_FIRST_NAME_ERROR_MESSAGE);
        return getElementText(driver, LiveGuruConstants.RegisterPageUI.REQUIRED_FIRST_NAME_ERROR_MESSAGE);
    }

    public String getRequiredLastNameErrorMessage() {
        waitForElementVisible(driver, LiveGuruConstants.RegisterPageUI.REQUIRED_LAST_NAME_ERROR_MESSAGE);
        return getElementText(driver, LiveGuruConstants.RegisterPageUI.REQUIRED_LAST_NAME_ERROR_MESSAGE);
    }

    public String getRequiredEmailErrorMessage() {
        waitForElementVisible(driver, LiveGuruConstants.RegisterPageUI.REQUIRED_EMAIL_ERROR_MESSAGE);
        return getElementText(driver, LiveGuruConstants.RegisterPageUI.REQUIRED_EMAIL_ERROR_MESSAGE);
    }

    public String getRequiredPasswordErrorMessage() {
        waitForElementVisible(driver, LiveGuruConstants.RegisterPageUI.REQUIRED_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, LiveGuruConstants.RegisterPageUI.REQUIRED_PASSWORD_ERROR_MESSAGE);
    }

    public String getRequiredConfirmPasswordErrorMessage() {
        waitForElementVisible(driver, LiveGuruConstants.RegisterPageUI.REQUIRED_CONFIRM_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, LiveGuruConstants.RegisterPageUI.REQUIRED_CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    public String getInvalidEmailErrorMessage() {
        waitForElementVisible(driver, LiveGuruConstants.RegisterPageUI.INVALID_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, LiveGuruConstants.RegisterPageUI.INVALID_PASSWORD_ERROR_MESSAGE);
    }

    public String getInvalidConfirmEmailErrorMessage() {
        waitForElementVisible(driver, LiveGuruConstants.RegisterPageUI.INVALID_CONFIRM_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, LiveGuruConstants.RegisterPageUI.INVALID_CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, LiveGuruConstants.AccountPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver,LiveGuruConstants.AccountPageUI.REGISTER_SUCCESS_MESSAGE);
    }
}
