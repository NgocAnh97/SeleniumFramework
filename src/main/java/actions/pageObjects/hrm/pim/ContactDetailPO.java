package actions.pageObjects.hrm.pim;

import interfaces.pageUIs.orangehrm.pim.EmployeeListPageUI;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class ContactDetailPO extends PersonalListTabs {
    private WebDriver driver;

    public ContactDetailPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
