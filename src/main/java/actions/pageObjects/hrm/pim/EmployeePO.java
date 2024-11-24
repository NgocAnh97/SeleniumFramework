package actions.pageObjects.hrm.pim;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;

public class EmployeePO extends BasePage {
    private WebDriver driver;

    public EmployeePO(WebDriver driver) {
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
