package actions.pageObjects.hrm;

import actions.pageObjects.hrm.pim.AddEmployeePO;
import actions.pageObjects.hrm.pim.ContactDetailPO;
import actions.pageObjects.hrm.pim.EmployeePO;
import actions.pageObjects.hrm.pim.PersonalDetailPO;
import org.openqa.selenium.WebDriver;

public class PageGenerator {
    public static LoginPO getLoginPage(WebDriver driver) {
        return new LoginPO(driver);
    }

    public static EmployeePO getEmployeeListPage(WebDriver driver) {
        return new EmployeePO(driver);
    }

    public static PersonalDetailPO getPersonalDetailPage(WebDriver driver) {
        return new PersonalDetailPO(driver);
    }

    public static DashboardPO getDashboardPage(WebDriver driver) {
        return new DashboardPO(driver);
    }

    public static AddEmployeePO getAddEmployeePage(WebDriver driver) {
        return new AddEmployeePO(driver);
    }

    public static ContactDetailPO getContactDetailPage(WebDriver driver) {
        return new ContactDetailPO(driver);
    }
}
