package interfaces.pageUIs.orangehrm.pim;

public class EmergencyContactPageUI {
    public static final String ADD_BUTTON = "xpath=//h6[text()='Assigned Emergency Contacts']/parent::div/button";
    public static final String NAME_TEXTBOX = "xpath=//label[text()='Name']/parent::div/following-sibling::div/input";
    public static final String RELATIONSHIP_TEXTBOX = "xpath=//label[text()='Relationship']/parent::div/following-sibling::div/input";
    public static final String HOME_TELEPHONE_TEXTBOX = "xpath=//label[text()='Home Telephone']/parent::div/following-sibling::div/input";
    public static final String SAVE_BUTTON_AT_EMERGENCY_CONTACT_CONTAINER = "xpath=//h6[text()='Save Emergency Contact']/following-sibling::form//button[contains(string(),'Save')]";
    public static final String NAME_RECORD = "xpath=//div[@class='orangehrm-container']//div[@class='oxd-table-card']/div/div[2]/div";
    public static final String RELATIONSHIP_RECORD = "xpath=//div[@class='orangehrm-container']//div[@class='oxd-table-card']/div/div[3]/div";
    public static final String HOME_TELEPHONE_RECORD = "xpath=//div[@class='orangehrm-container']//div[@class='oxd-table-card']/div/div[4]/div";
}