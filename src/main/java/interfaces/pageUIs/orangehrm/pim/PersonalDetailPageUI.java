package interfaces.pageUIs.orangehrm.pim;

public class PersonalDetailPageUI {
    public static final String FIRST_NAME_TEXTBOX = "name=firstName";
    public static final String LAST_NAME_TEXTBOX = "name=lastName";
    public static final String EMPLOYEE_ID = "xpath=//label[text()='Employee Id']/parent::div/following-sibling::div/input";
    public static final String DRIVER_LICENSE_NUMBER_TEXTBOX = "xpath=//label[text()=\"Driver's License Number\"]/parent::div/following-sibling::div/input";
    public static final String LICENSE_EXPIRY_DATE_TEXTBOX = "xpath=//label[text()='License Expiry Date']/parent::div/following-sibling::div//input";
    public static final String NATIONALITY_DROPDOWN_PARENT = "xpath=//label[text()='Nationality']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text--active')]";
    public static final String NATIONALITY_DROPDOWN_ITEM_SELECTED = "xpath=//label[text()='Nationality']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']";
    public static final String NATIONALITY_DROPDOWN_CHILD = "xpath=//div[@role='listbox']//div[contains(@class,'oxd-select-option')]";
    public static final String MARITAL_STATUS_DROPDOWN_PARENT = "xpath=//label[text()='Marital Status']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text--active')]";
    public static final String MARITAL_STATUS_DROPDOWN_ITEM_SELECTED = "xpath=//label[text()='Marital Status']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']";
    public static final String MARITAL_DROPDOWN_CHILD = "xpath=//div[@role='listbox']//div[contains(@class,'oxd-select-option')]";
    public static final String DATE_OF_BIRTH_TEXTBOX = "xpath=//label[text()='Date of Birth']/parent::div/following-sibling::div//input";
    public static final String GENDER_RADIO_BUTTON = "xpath=//label[text()='Gender']/parent::div/following-sibling::div//label[contains(string(),'%s')]/input";
    public static final String SAVE_BUTTON_AT_PERSONAL_DETAIL_CONTAINER = "xpath=//h6[text()='Personal Details']/following-sibling::form//button[contains(string(),'Save')]";
}