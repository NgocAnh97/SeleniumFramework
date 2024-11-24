package testcases.com.hrm.employee.PIM;

import actions.commons.BaseTest;
import actions.pageObjects.hrm.*;
import actions.pageObjects.hrm.pim.AddEmployeePO;
import actions.pageObjects.hrm.pim.EmployeePO;
import actions.pageObjects.hrm.pim.PersonalDetailPO;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static java.lang.Thread.sleep;

public class PIM_02_Employee extends BaseTest {
    String employeeID, statusValue;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserUrl(browserName, url);
        log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + url + "'");

        loginPage = PageGenerator.getLoginPage(driver);
        statusValue = "Enabled";

        log.info("Pre-condition - Step 02: Login with Admin role");
        loginPage.enterToTextboxByName(driver, "Admin", "username");
        loginPage.enterToTextboxByName(driver, "admin123", "password");
        loginPage.clickToButtonByType(driver, "submit");

        dashboardPage = PageGenerator.getDashboardPage(driver);

        //Pre-condition - Add new
    }

//    @Test
//    public void Employee_07_Edit_View_Job() {
//
//    }
//
//    @Test
//    public void Employee_08_Edit_View_Salary() {
//
//    }
//
//    @Test
//    public void Employee_09_Edit_View_Tax() {
//
//    }
//
//    @Test
//    public void Employee_10_Qualifications() {
//
//    }
//
//    @Test
//    public void Employee_11_Search_Employee() {
//
//    }

    @Parameters({"browser"})
    @AfterClass()
    public void afterClass(String browserName) {
        log.info("Post-condition: Close browser '" + browserName + "'");
        driver.quit();
    }

    private WebDriver driver;
    LoginPO loginPage;
    AddEmployeePO addEmployeePage;
    DashboardPO dashboardPage;
    EmployeePO employeeListPage;
    PersonalDetailPO personalDetailPage;
}
