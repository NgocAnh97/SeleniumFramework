package actions.pageObjects.hrm;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;

public class PersonalDetailPO extends BasePage {
    private WebDriver driver;

    public PersonalDetailPO(WebDriver driver) {
        this.driver = driver;
    }

    public EmployeeListPO openEmployeeListPage() {
        return new EmployeeListPO(driver);
    }
}
