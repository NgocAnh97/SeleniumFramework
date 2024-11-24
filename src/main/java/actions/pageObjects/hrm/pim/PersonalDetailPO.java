package actions.pageObjects.hrm.pim;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;

public class PersonalDetailPO extends BasePage {
    private WebDriver driver;

    public PersonalDetailPO(WebDriver driver) {
        this.driver = driver;
    }

    public EmployeePO openEmployeeListPage() {
        return new EmployeePO(driver);
    }
}
