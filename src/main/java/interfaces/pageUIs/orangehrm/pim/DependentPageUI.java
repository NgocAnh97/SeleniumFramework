package interfaces.pageUIs.orangehrm.pim;

public class DependentPageUI {
    public static final String ADD_BUTTON = "xpath=//h6[text()='Assigned Dependents']/parent::div/button";
    public static final String NAME_TEXTBOX = "xpath=//label[text()='Name']/parent::div/following-sibling::div/input";
    public static final String PLEASE_SPECIFY_TEXTBOX = "xpath=//label[text()='Please Specify']/parent::div/following-sibling::div/input";
    public static final String RELATIONSHIP_DROPDOWN_PARENT = "xpath=//label[text()='Relationship']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text--active')]";
    public static final String RELATIONSHIP_DROPDOWN_CHILD = "xpath=//div[@role='listbox']//div[contains(@class,'oxd-select-option')]";
    public static final String RELATIONSHIP_DROPDOWN_ITEM_SELECTED = "xpath=//label[text()='Relationship']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']";
    public static final String SAVE_BUTTON_AT_DEPENDENT_CONTAINER = "xpath=//h6[text()='Add Dependent']/following-sibling::form//button[contains(string(),'Save')]";
    public static final String NAME_RECORD = "xpath=//div[@class='orangehrm-container']//div[@class='oxd-table-card']/div/div[2]/div";
    public static final String RELATIONSHIP_RECORD = "xpath=//div[@class='orangehrm-container']//div[@class='oxd-table-card']/div/div[3]/div";
}