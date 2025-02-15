package actions.pageObjects.hrm;

import actions.pageObjects.hrm.pim.*;
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

    public static ChangeProfilePicturePO getChangeProfilePicturePage(WebDriver driver){
        return new ChangeProfilePicturePO(driver);
    }

    public static ContactDetailPO getContactDetailPage(WebDriver driver) {
        return new ContactDetailPO(driver);
    }

    public static EmergencyContactPO getEmergencyContactPage(WebDriver driver) {
        return new EmergencyContactPO(driver);
    }

    public static DependentPO getDependentPage(WebDriver driver) {
        return new DependentPO(driver);
    }
}
