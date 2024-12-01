package interfaces.pageUIs.orangehrm.pim;

public class AddEmployeePageUI {
    public static final String  EMPLOYEE_ID = "xpath=//label[@class='oxd-label' and text()='Employee Id']/parent::div/following-sibling::div/input";
    public static final String FIRST_NAME_TEXTBOX = "name=firstName";
    public static final String LAST_NAME_TEXTBOX = "name=lastName";
    public static final String SAVE_BUTTON_AT_ADD_EMPLOYEE_CONTAINER = "xpath=//h6[text()='Add Employee']/parent::div//button[contains(string(),'Save')]";
}
