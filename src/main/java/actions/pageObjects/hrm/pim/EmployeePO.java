package actions.pageObjects.hrm.pim;

import actions.pageObjects.hrm.PageGenerator;
import interfaces.pageUIs.orangehrm.DashboardPageUI;
import org.openqa.selenium.WebDriver;

public class EmployeePO extends PersonalListTabs {
    public EmployeePO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public AddEmployeePO clickToAddEmployeeButton() {
        waitForElementClickable(driver, DashboardPageUI.ADD_EMPLOYEE_BUTTON);
        clickToElement(driver, DashboardPageUI.ADD_EMPLOYEE_BUTTON);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getAddEmployeePage(driver);
    }
}
