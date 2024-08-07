package actions.pageObjects.hrm;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;

public class DashboardPO extends BasePage {
    private WebDriver driver;

    public DashboardPO(WebDriver driver) {
        this.driver = driver;
    }


    public EmployeeListPO openEmployeeListPage() {
        return new EmployeeListPO(driver);
    }
}
