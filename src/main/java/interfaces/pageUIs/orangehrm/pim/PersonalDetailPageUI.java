package interfaces.pageUIs.orangehrm.pim;

public class PersonalDetailPageUI {
    public static final String FIRST_NAME_TEXTBOX = "name=firstName";
    public static final String LAST_NAME_TEXTBOX = "name=lastName";
    public static final String EMPLOYEE_ID = "xpath=//label[@class='oxd-label' and text()='Employee Id']/parent::div/following-sibling::div/input";
    public static final String DRIVER_LICENSE_NUMBER_TEXTBOX = "xpath=//label[@class='oxd-label' and text()=\"Driver's License Number\"]/parent::div/following-sibling::div/input";
    public static final String LICENSE_EXPIRY_DATE_TEXTBOX = "xpath=//label[@class='oxd-label' and text()='License Expiry Date']/parent::div/following-sibling::div//input";
    public static final String NATIONALITY_TEXTBOX = "xpath=//label[@class='oxd-label' and text()='Nationality']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text--active')]";
    public static final String NATIONALITY_ITEMS = "xpath=//div[@role='listbox']//div[contains(@class,'oxd-select-option')]";
    public static final String MARITAL_STATUS_TEXTBOX = "xpath=//label[@class='oxd-label' and text()='Marital Status']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text--active')]";
    public static final String MARITAL_ITEMS = "xpath=//div[@role='listbox']//div[contains(@class,'oxd-select-option')]";
    public static final String DATE_OF_BIRTH_TEXTBOX = "xpath=//label[@class='oxd-label' and text()='Date of Birth']/parent::div/following-sibling::div//input";
    public static final String GENDER_RADIO_BUTTON = "xpath=//label[@class='oxd-label' and text()='Gender']/parent::div/following-sibling::div//span";
}