package actions.pageObjects.nopcommerce.user;

import actions.commons.BasePage;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;

public class UserLoginPageObjects extends BasePage {
    private WebDriver driver;

    public UserLoginPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public UserHomePageObjects openLoginPage() {
        waitForElementClickable(driver, GlobalConstants.LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, GlobalConstants.LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    public UserAccountPageObjects openAccountPage() {
        waitForElementClickable(driver, GlobalConstants.HomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, GlobalConstants.HomePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getUserMyAccountPage(driver);
    }

    public String getFieldValidationErrorMessage() {
        waitForElementVisible(driver, GlobalConstants.LoginPageUI.FIELD_VALIDATION_ERROR_MESSAGE);
        return getElementText(driver, GlobalConstants.LoginPageUI.FIELD_VALIDATION_ERROR_MESSAGE);
    }

    public String getLoginErrorMessage() {
        waitForElementVisible(driver, GlobalConstants.LoginPageUI.LOGIN_ERROR_MESSAGE);
        return getElementText(driver, GlobalConstants.LoginPageUI.LOGIN_ERROR_MESSAGE);
    }

    public void inputToEmailTextbox(String email) {
        waitForElementVisible(driver, GlobalConstants.LoginPageUI.EMAIL_TEXTBOX);
        senKeysToElement(driver, GlobalConstants.LoginPageUI.EMAIL_TEXTBOX, email);
    }

    public void inputToPasswordTextbox(String passWord) {
        waitForElementVisible(driver, GlobalConstants.LoginPageUI.PASSWORD_TEXTBOX);
        senKeysToElement(driver, GlobalConstants.LoginPageUI.PASSWORD_TEXTBOX, passWord);
    }

    public String getTitleAccountPage() {
        return getTitle(driver);
    }

    public UserHomePageObjects loginAsUser(String emailAddress, String passWord) {
        inputToEmailTextbox(emailAddress);
        inputToPasswordTextbox(passWord);
        return openLoginPage();
    }
}
