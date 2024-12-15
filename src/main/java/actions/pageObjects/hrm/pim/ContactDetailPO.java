package actions.pageObjects.hrm.pim;

import interfaces.pageUIs.orangehrm.pim.ContactDetailPageUI;
import org.openqa.selenium.WebDriver;

public class ContactDetailPO extends PersonalListTabs {
    private WebDriver driver;

    public ContactDetailPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void enterToStreet1Textbox(String street1) {
        waitForElementVisible(driver, ContactDetailPageUI.STREET_1_TEXTBOX);
        sendKeysToElement(driver, ContactDetailPageUI.STREET_1_TEXTBOX, street1);
    }

    public void enterToStreet2Textbox(String street2) {
        waitForElementVisible(driver, ContactDetailPageUI.STREET_2_TEXTBOX);
        sendKeysToElement(driver, ContactDetailPageUI.STREET_2_TEXTBOX, street2);
    }

    public void selectCountryDropdown(String country) {
        waitForElementClickable(driver, ContactDetailPageUI.COUNTRY_DROPDOWN_PARENT);
        selectItemInCustomDropdown(driver, ContactDetailPageUI.COUNTRY_DROPDOWN_PARENT, ContactDetailPageUI.COUNTRY_DROPDOWN_CHILD, country);
    }

    public void clickToSaveButtonAtContactDetailContainer() {
        waitForElementClickable(driver, ContactDetailPageUI.SAVE_BUTTON_AT_CONTACT_DETAIL_CONTAINER);
        clickToElement(driver, ContactDetailPageUI.SAVE_BUTTON_AT_CONTACT_DETAIL_CONTAINER);
    }

    public String getStreet1TextboxValue() {
        waitForElementVisible(driver, ContactDetailPageUI.STREET_1_TEXTBOX);
        return getValue(driver, ContactDetailPageUI.STREET_1_TEXTBOX);
    }

    public String getStreet2TextboxValue() {
        waitForElementVisible(driver, ContactDetailPageUI.STREET_2_TEXTBOX);
        return getValue(driver, ContactDetailPageUI.STREET_2_TEXTBOX);
    }

    public String getCountryDropdownValue() {
        waitForElementVisible(driver, ContactDetailPageUI.COUNTRY_DROPDOWN_ITEM_SELECTED);
        return getElementText(driver, ContactDetailPageUI.COUNTRY_DROPDOWN_ITEM_SELECTED);
    }
}
