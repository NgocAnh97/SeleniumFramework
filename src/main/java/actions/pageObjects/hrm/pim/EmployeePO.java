package actions.pageObjects.hrm.pim;

import org.openqa.selenium.WebDriver;

public class EmployeePO extends PersonalListTabs {
    public EmployeePO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
