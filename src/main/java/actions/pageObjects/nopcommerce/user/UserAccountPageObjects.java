package actions.pageObjects.nopcommerce.user;

import actions.commons.BasePage;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;

public class UserAccountPageObjects extends BasePage {
    private WebDriver driver;

    public UserAccountPageObjects(WebDriver driver){
        this.driver = driver;
    }

    public UserHomePageObjects clickToLogout() {
        waitForElementInvisible(driver,GlobalConstants.AccountPageUI.CLOSE_SUCCESS_BUTTON);
        waitForElementClickable(driver, GlobalConstants.AccountPageUI.LOGOUT_LINK);
        clickToElement(driver, GlobalConstants.AccountPageUI.LOGOUT_LINK);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    public void openMyAccountPage() {
        waitForElementClickable(driver, GlobalConstants.AccountPageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, GlobalConstants.AccountPageUI.MY_ACCOUNT_LINK);
    }

    public void inputToEmail(String emailUpdate) {
        waitForElementVisible(driver, GlobalConstants.AccountPageUI.EMAIL_TEXTBOX);
        sendKeysToElement(driver, GlobalConstants.AccountPageUI.EMAIL_TEXTBOX, emailUpdate);
    }

    public void inputToFirstName(String firstNameUpdate) {
        waitForElementVisible(driver, GlobalConstants.AccountPageUI.FIRST_NAME_TEXTBOX);
        sendKeysToElement(driver, GlobalConstants.AccountPageUI.FIRST_NAME_TEXTBOX, firstNameUpdate);
    }

    public void inputToLastName(String lastNameUpdate) {
        waitForElementVisible(driver, GlobalConstants.AccountPageUI.LAST_NAME_TEXTBOX);
        sendKeysToElement(driver, GlobalConstants.AccountPageUI.LAST_NAME_TEXTBOX, lastNameUpdate);
    }

    public void clickToFemaleGender() {
        waitForElementClickable(driver, GlobalConstants.AccountPageUI.GENDER_FEMALE_CHECKBOX);
        clickToElement(driver, GlobalConstants.AccountPageUI.GENDER_FEMALE_CHECKBOX);
    }

    public UserAccountPageObjects clickToSave() {
        waitForElementClickable(driver, GlobalConstants.AccountPageUI.SAVE_BUTTON);
        clickToElement(driver, GlobalConstants.AccountPageUI.SAVE_BUTTON);
        return PageGeneratorManager.getUserMyAccountPage(driver);
    }

    public String getFirstNameUpdate() {
        waitForElementVisible(driver,GlobalConstants.AccountPageUI.FIRST_NAME_TEXTBOX);
        return getValue(driver,GlobalConstants.AccountPageUI.FIRST_NAME_TEXTBOX);
    }

    public String getLastNameUpdate() {
        waitForElementVisible(driver,GlobalConstants.AccountPageUI.LAST_NAME_TEXTBOX);
        return getValue(driver,GlobalConstants.AccountPageUI.LAST_NAME_TEXTBOX);
    }

    public String getEmailUpdate() {
        waitForElementVisible(driver,GlobalConstants.AccountPageUI.EMAIL_TEXTBOX);
        return getValue(driver,GlobalConstants.AccountPageUI.EMAIL_TEXTBOX);
    }

    public void closeSuccessMessage() {
        waitForElementClickable(driver,GlobalConstants.AccountPageUI.CLOSE_SUCCESS_BUTTON);
        clickToElement(driver,GlobalConstants.AccountPageUI.CLOSE_SUCCESS_BUTTON);
    }
}
