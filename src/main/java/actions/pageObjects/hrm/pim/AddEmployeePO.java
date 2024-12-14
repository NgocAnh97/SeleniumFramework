package actions.pageObjects.hrm.pim;

import actions.commons.BasePage;
import actions.pageObjects.hrm.PageGenerator;
import interfaces.pageUIs.orangehrm.pim.AddEmployeePageUI;
import org.openqa.selenium.WebDriver;

public class AddEmployeePO extends BasePage {
    private WebDriver driver;

    public AddEmployeePO(WebDriver driver) {
        this.driver = driver;
    }

    public String getEmployeeID() {
        waitForElementVisible(driver, AddEmployeePageUI.EMPLOYEE_ID);
        return getElementText(driver, AddEmployeePageUI.EMPLOYEE_ID);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX);
        sendKeysToElement(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX);
        sendKeysToElement(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public PersonalDetailPO clickToSaveButtonAtAddEmployeeContainer() {
        waitForElementClickable(driver, AddEmployeePageUI.SAVE_BUTTON_AT_ADD_EMPLOYEE_CONTAINER);
        clickToElement(driver, AddEmployeePageUI.SAVE_BUTTON_AT_ADD_EMPLOYEE_CONTAINER);

        return PageGenerator.getPersonalDetailPage(driver);
    }
}
