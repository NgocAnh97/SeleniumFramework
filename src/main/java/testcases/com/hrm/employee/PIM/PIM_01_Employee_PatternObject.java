package testcases.com.hrm.employee.PIM;

import actions.commons.BaseTest;
import actions.pageObjects.hrm.DashboardPO;
import actions.pageObjects.hrm.LoginPO;
import actions.pageObjects.hrm.PageGenerator;
import actions.pageObjects.hrm.pim.AddEmployeePO;
import actions.pageObjects.hrm.pim.EmployeePO;
import actions.pageObjects.hrm.pim.PersonalDetailPO;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class PIM_01_Employee_PatternObject extends BaseTest {
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
    }

    @Test
    public void Employee_01_Add_New_Employee() throws InterruptedException {
        log.info("Add_New_01 - Step 01: Open 'Employee list' page");
        dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
        employeeListPage = PageGenerator.getEmployeeListPage(driver);

        log.info("Add_New_01 - Step 02: Click to 'Add' button");
        employeeListPage.clickToButtonByType(driver, "button");
        addEmployeePage = PageGenerator.getAddEmployeePage(driver);

        log.info("Add_New_01 - Step 03: Enter valid info to 'First name' textbox");
        addEmployeePage.enterToTextboxByName(driver, "firstName", "firstName");

        log.info("Add_New_01 - Step 04: Enter valid info to 'Last name' textbox");
        addEmployeePage.enterToTextboxByName(driver, "lastName", "lastName");

        log.info("Add_New_01 - Step 05: Get value of 'Employee ID'");
        employeeID = addEmployeePage.getEmployeeID();

        log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
        addEmployeePage.clickToCreateLoginDetailCheckbox("");

        log.info("Add_New_01 - Step 07: Enter valid info to 'User Name' textbox");
        addEmployeePage.enterToUserNameTextbox("");

        log.info("Add_New_01 - Step 08: Enter valid info to 'Password' textbox");
        addEmployeePage.enterToPasswordTextbox("");

        log.info("Add_New_01 - Step 09: Enter valid info to 'Confirm Password' textbox");
        addEmployeePage.enterToConfirmPasswordTextbox("");

        log.info("Add_New_01 - Step 10: Select '" + statusValue + "' value in 'Status' dropdown");
        addEmployeePage.selectValueInStatusDropdown(statusValue);

        log.info("Add_New_01 - Step 11: Click to 'Save' button");
        sleep(2000);
        employeeListPage.clickToButtonByType(driver, "submit");
        personalDetailPage = PageGenerator.getPersonalDetailPage(driver);

        log.info("Add_New_01 - Step 12: Open 'Employee List' page");
        employeeListPage = personalDetailPage.openEmployeeListPage();

        log.info("Add_New_01 - Step 13: Enter valid info to 'Employee Name' textbox");
//        employeeListPage.enterToEmployeeNameTextbox("");

        log.info("Add_New_01 - Step 14: Click to 'Search' button");
        employeeListPage.clickToButtonByType(driver, "submit");

        log.info("Add_New_01 - Step 15: Verify Employee Information displayed at 'Result Table'");
        verifyTrue(employeeListPage.isEmployeeInfoDisplayedAtTable("", "", ""));
    }

    @Parameters({"browser"})
    @AfterClass()
    public void afterClass(String browserName) {
        log.info("Post-condition: Close browser '" + browserName + "'");
        driver.quit();
    }

    private WebDriver driver;
    private LoginPO loginPage;
    private AddEmployeePO addEmployeePage;
    private DashboardPO dashboardPage;
    private EmployeePO employeeListPage;
    private PersonalDetailPO personalDetailPage;
}
