package actions.pageObjects.liveguru;

import actions.commons.BasePage;
import actions.commons.LiveGuruConstants;
import org.openqa.selenium.WebDriver;

public class LoginPageObjects extends BasePage {
    private WebDriver driver;

    public LoginPageObjects(WebDriver driver){
        this.driver = driver;
    }

    public AccountInfoPageObjects clickToLoginButton() {
        waitForElementClickable(driver, LiveGuruConstants.LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LiveGuruConstants.LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getAccountPage(driver);
    }

    public void inputToEmail(String invalidEmail) {
        waitForElementVisible(driver, LiveGuruConstants.LoginPageUI.EMAIL_TEXTBOX);
        sendKeysToElement(driver, LiveGuruConstants.LoginPageUI.EMAIL_TEXTBOX, invalidEmail);
    }

    public void inputToPassword(String passWord) {
        waitForElementVisible(driver, LiveGuruConstants.LoginPageUI.PASSWORD_TEXTBOX);
        sendKeysToElement(driver, LiveGuruConstants.LoginPageUI.PASSWORD_TEXTBOX, passWord);
    }

    public String getRequiredEmailErrorMessage() {
        waitForElementVisible(driver, LiveGuruConstants.LoginPageUI.REQUIRED_EMAIL_ERROR_MESSAGE);
        return getElementText(driver, LiveGuruConstants.LoginPageUI.REQUIRED_EMAIL_ERROR_MESSAGE);
    }

    public String getRequiredPasswordErrorMessage() {
        waitForElementVisible(driver, LiveGuruConstants.LoginPageUI.REQUIRED_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, LiveGuruConstants.LoginPageUI.REQUIRED_PASSWORD_ERROR_MESSAGE);
    }

    public String getInvalidPasswordErrorMessage() {
        waitForElementVisible(driver, LiveGuruConstants.LoginPageUI.INVALID_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver,LiveGuruConstants.LoginPageUI.INVALID_PASSWORD_ERROR_MESSAGE);
    }

    public String getUnregisterEmailMessage() {
        waitForElementVisible(driver, LiveGuruConstants.LoginPageUI.UNREGISTER_EMAIL_ERROR_MESSAGE);
        return getElementText(driver, LiveGuruConstants.LoginPageUI.UNREGISTER_EMAIL_ERROR_MESSAGE);
    }
}
