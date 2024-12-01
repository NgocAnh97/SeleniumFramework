package actions.pageObjects.hrm.pim;

import actions.pageObjects.hrm.PageGenerator;
import interfaces.pageUIs.orangehrm.DashboardPageUI;
import org.openqa.selenium.WebDriver;

public class EmployeePO extends PersonalListTabs {
    private WebDriver driver;

    public EmployeePO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public AddEmployeePO clickToAddButton() {
        return new AddEmployeePO(driver);
    }

    public boolean isEmployeeInfoDisplayedAtTable(String s, String s1, String s2) {
        return false;
    }

    public AddEmployeePO clickToAddEmployeeButton() {
        waitForElementClickable(driver, DashboardPageUI.ADD_EMPLOYEE_BUTTON);
        clickToElement(driver, DashboardPageUI.ADD_EMPLOYEE_BUTTON);
        waitAllLoadingIconInvisible(driver);
        return PageGenerator.getAddEmployeePage(driver);
    }
}
