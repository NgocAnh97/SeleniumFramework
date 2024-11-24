package actions.pageObjects.hrm;

import actions.commons.BasePage;
import actions.pageObjects.hrm.pim.EmployeePO;
import org.openqa.selenium.WebDriver;

public class DashboardPO extends BasePage {
    private WebDriver driver;

    public DashboardPO(WebDriver driver) {
        this.driver = driver;
    }


    public EmployeePO openEmployeeListPage() {
        return new EmployeePO(driver);
    }
}
