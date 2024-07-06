package actions.pageObjects.hrm;

import actions.commons.BasePage;
import interfaces.pageUIs.hrm.BasePageUI;
import org.openqa.selenium.WebDriver;

public class AddEmployeePO extends BasePage {
    private WebDriver driver;

    public AddEmployeePO(WebDriver driver) {
        this.driver = driver;
    }

//    public void enterToTextboxByID(WebDriver driver, String value, String info) {
//        waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_NAME, info);
//        senKeysToElement(driver, BasePageUI.TEXTBOX_BY_NAME, value,info);
//    }

    public String getEmployeeID() {

        return null;
    }

    public void clickToCreateLoginDetailCheckbox(String s) {
    }

    public void enterToUserNameTextbox(String s) {
    }

    public void enterToPasswordTextbox(String s) {
    }

    public void enterToConfirmPasswordTextbox(String s) {
    }

    public void selectValueInStatusDropdown(String statusValue) {
    }

    public PersonalDetailPO clickToSaveButton() {
        return null;
    }
}
