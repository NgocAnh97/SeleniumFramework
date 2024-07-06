package actions.pageObjects.hrm;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
    public static LoginPO getLoginPage(WebDriver driver) {
        return new LoginPO(driver);
    }

    public static EmployeeListPO getEmployeeListPage(WebDriver driver) {
        return new EmployeeListPO(driver);
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
}
