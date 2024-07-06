package actions.pageObjects.hrm;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;

public class EmployeeListPO extends BasePage {
    private WebDriver driver;

    public EmployeeListPO(WebDriver driver) {
        this.driver = driver;
    }

    public AddEmployeePO clickToAddButton() {
        return new AddEmployeePO(driver);
    }

    public void enterToEmployeeNameTextbox(String s) {
    }

    public void clickToSearchButton() {
    }

    public boolean isEmployeeInfoDisplayedAtTable(String s, String s1, String s2) {
        return false;
    }
}
