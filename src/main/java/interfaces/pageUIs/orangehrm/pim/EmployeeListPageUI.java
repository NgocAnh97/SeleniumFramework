package interfaces.pageUIs.orangehrm.pim;

public class EmployeeListPageUI {
    public static final String ADD_EMPLOYEE_SUCCESS_MESSAGE = "xpath=//p[contains(@class,'toast-message') and text()='Successfully Saved']";
    public static final String EMPLOYEE_IMAGE = "css=div.orangehrm-edit-employee-image>img.employee-image";
    public static final String SAVE_BUTTON_AT_PROFILE_CONTAINER = "xpath=//h6[text()='Change Profile Picture']/following-sibling::form//button[@type='submit' or contains(string(),'Save')]";
    public static final String  UPDATE_EMPLOYEE_SUCCESS_MESSAGE = "xpath=//p[contains(@class,'toast-message') and text()='Successfully Updated']";
}
