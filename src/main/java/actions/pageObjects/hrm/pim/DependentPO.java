package actions.pageObjects.hrm.pim;

import interfaces.pageUIs.orangehrm.pim.DependentPageUI;
import org.openqa.selenium.WebDriver;

public class DependentPO extends PersonalListTabs {
    private WebDriver driver;

    public DependentPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickToAddButton() {
        waitForElementClickable(driver, DependentPageUI.ADD_BUTTON);
        clickToElement(driver, DependentPageUI.ADD_BUTTON);
    }

    public void enterToNameTextbox(String name) {
        waitForElementVisible(driver, DependentPageUI.NAME_TEXTBOX);
        sendKeysToElement(driver, DependentPageUI.NAME_TEXTBOX, name);
    }

    public void enterToRelationshipDropdown(String relationship) {
        waitForElementClickable(driver, DependentPageUI.RELATIONSHIP_DROPDOWN_PARENT);
        selectItemInCustomDropdown(driver, DependentPageUI.RELATIONSHIP_DROPDOWN_PARENT, DependentPageUI.RELATIONSHIP_DROPDOWN_CHILD, relationship);
    }

    public void enterToPleaseSpecifyTextbox(String pleaseSpecify) {
        waitForElementVisible(driver, DependentPageUI.PLEASE_SPECIFY_TEXTBOX);
        sendKeysToElement(driver, DependentPageUI.PLEASE_SPECIFY_TEXTBOX, pleaseSpecify);
    }

    public void clickToSaveButtonAtDependentContainer() {
        waitForElementClickable(driver, DependentPageUI.SAVE_BUTTON_AT_DEPENDENT_CONTAINER);
        clickToElement(driver, DependentPageUI.SAVE_BUTTON_AT_DEPENDENT_CONTAINER);
    }

    public String getNameInRecordValue() {
        waitForElementVisible(driver, DependentPageUI.NAME_RECORD);
        return getElementText(driver, DependentPageUI.NAME_RECORD);
    }

    public String getRelationshipInRecordValue() {
        waitForElementVisible(driver, DependentPageUI.RELATIONSHIP_RECORD);
        return getElementText(driver, DependentPageUI.RELATIONSHIP_RECORD);
    }
}