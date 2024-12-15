package actions.pageObjects.hrm.pim;

import interfaces.pageUIs.orangehrm.pim.EmergencyContactPageUI;
import org.openqa.selenium.WebDriver;

public class EmergencyContactPO extends PersonalListTabs {
    private WebDriver driver;

    public EmergencyContactPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickToAddButton() {
        waitForElementClickable(driver, EmergencyContactPageUI.ADD_BUTTON);
        clickToElement(driver, EmergencyContactPageUI.ADD_BUTTON);
    }

    public void enterToNameTextbox(String name) {
        waitForElementVisible(driver, EmergencyContactPageUI.NAME_TEXTBOX);
        sendKeysToElement(driver, EmergencyContactPageUI.NAME_TEXTBOX, name);
    }

    public void enterToRelationshipTextbox(String relationship) {
        waitForElementVisible(driver, EmergencyContactPageUI.RELATIONSHIP_TEXTBOX);
        sendKeysToElement(driver, EmergencyContactPageUI.RELATIONSHIP_TEXTBOX, relationship);
    }

    public void enterToHomeTelephoneTextbox(String homeTelephone) {
        waitForElementVisible(driver, EmergencyContactPageUI.HOME_TELEPHONE_TEXTBOX);
        sendKeysToElement(driver, EmergencyContactPageUI.HOME_TELEPHONE_TEXTBOX, homeTelephone);
    }

    public void clickToSaveButtonAtEmergencyContactContainer() {
        waitForElementClickable(driver, EmergencyContactPageUI.SAVE_BUTTON_AT_EMERGENCY_CONTACT_CONTAINER);
        clickToElement(driver, EmergencyContactPageUI.SAVE_BUTTON_AT_EMERGENCY_CONTACT_CONTAINER);
    }

    public String getNameInRecordValue() {
        waitForElementVisible(driver, EmergencyContactPageUI.NAME_RECORD);
        return getElementText(driver, EmergencyContactPageUI.NAME_RECORD);
    }

    public String getRelationshipInRecordValue() {
        waitForElementVisible(driver, EmergencyContactPageUI.RELATIONSHIP_RECORD);
        return getElementText(driver, EmergencyContactPageUI.RELATIONSHIP_RECORD);
    }

    public String getHomeTelephoneInRecordValue() {
        waitForElementVisible(driver, EmergencyContactPageUI.HOME_TELEPHONE_RECORD);
        return getElementText(driver, EmergencyContactPageUI.HOME_TELEPHONE_RECORD);
    }
}