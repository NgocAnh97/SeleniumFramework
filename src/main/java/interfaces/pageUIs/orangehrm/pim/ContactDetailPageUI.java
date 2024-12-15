package interfaces.pageUIs.orangehrm.pim;

public class ContactDetailPageUI {
    public static final String STREET_1_TEXTBOX = "xpath=//label[text()='Street 1']/parent::div/following-sibling::div/input";
    public static final String STREET_2_TEXTBOX = "xpath=//label[text()='Street 2']/parent::div/following-sibling::div/input";
    public static final String COUNTRY_DROPDOWN_PARENT = "xpath=//label[text()='Country']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text--active')]";
    public static final String COUNTRY_DROPDOWN_CHILD = "xpath=//div[@role='listbox']//div[contains(@class,'oxd-select-option')]";
    public static final String COUNTRY_DROPDOWN_ITEM_SELECTED = "xpath=//label[text()='Country']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']";
    public static final String SAVE_BUTTON_AT_CONTACT_DETAIL_CONTAINER = "xpath=//h6[text()='Contact Details']/following-sibling::form//button[contains(string(),'Save')]";
}