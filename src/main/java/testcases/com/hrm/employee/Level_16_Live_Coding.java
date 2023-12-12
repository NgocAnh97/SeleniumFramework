package testcases.com.hrm.employee;

import actions.commons.BaseTest;
import actions.pageObjects.hrm.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Level_16_Live_Coding extends BaseTest {
    String employeeID, statusValue;

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {
        log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + "\"https://opensource-demo.orangehrmlive.com/\"" + "'");
        driver = getBrowser(browserName);
        driver.get("https://opensource-demo.orangehrmlive.com/");

        loginPage = PageGenerator.getLoginPage(driver);

        log.info("Pre-condition - Step 02: Login with Admin role");
        dashboardPage = loginPage.loginToSystem("","");
    }

    @Test
    public void Employee_01_Add_New_Employee() {
        log.info("Add_New_01 - Step 01: Open 'Employee list' page");
        employeeListPage = dashboardPage.openEmployeeListPage();
        log.info("Add_New_01 - Step 02: Click to 'Add' button");
        addEmployeePage = employeeListPage.clickToAddButton();

        log.info("Add_New_01 - Step 03: Enter valid info to 'First name' textbox");
        addEmployeePage.enterToFirstNameTextbox("");
        log.info("Add_New_01 - Step 04: Enter valid info to 'Last name' textbox");
        addEmployeePage.enterToLastNameTextbox("");
        log.info("Add_New_01 - Step 05: Get value of 'Employee ID'");
        employeeID = addEmployeePage.getEmployeeID();
        log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
        addEmployeePage.clickToCreateLoginDetailCheckbox("");

        log.info("Add_New_01 - Step 07: Enter valid info to 'User name' textbox");
        addEmployeePage.enterToUserNameTextbox("");

        log.info("Add_New_01 - Step 08: Enter valid info to 'Password' textbox");
        addEmployeePage.enterToPasswordTextbox("");

        log.info("Add_New_01 - Step 09: Enter valid info to 'Confirm Password' textbox");
        addEmployeePage.enterToConfirmPasswordTextbox("");

        log.info("Add_New_01 - Step 10: Select '"+ statusValue +"' value in 'Status' dropdown");
        addEmployeePage.selectValueInStatusDropdown(statusValue);

        log.info("Add_New_01 - Step 11: Click to 'Save' button");
        personalDetailPage = addEmployeePage.clickToSaveButton();

        log.info("Add_New_01 - Step 12: Open 'Employee List' page");
        employeeListPage = personalDetailPage.openEmployeeListPage();

        log.info("Add_New_01 - Step 13: Enter valid info to 'Employee Name' textbox");
        employeeListPage.enterToEmployeeNameTextbox("");

        log.info("Add_New_01 - Step 14: Click to 'Search' button");
        employeeListPage.clickToSearchButton();

        log.info("Add_New_01 - Step 15: Verify Employee Information displayed at 'Result Table'");
        verifyTrue(employeeListPage.isEmployeeInfoDisplayedAtTable("","","");

    }

    @Test
    public void Employee_02_Upload_Avatar() {

    }

    @Test
    public void Employee_03_Personal_Details() {

    }

    @Test
    public void Employee_04_Contact_Details() {

    }

    @Test
    public void Employee_05_Emergency_Details() {

    }

    @Test
    public void Employee_06_Assigned_Dependents() {

    }

    @Test
    public void Employee_07_Edit_View_Job() {

    }

    @Test
    public void Employee_08_Edit_View_Salary() {

    }

    @Test
    public void Employee_09_Edit_View_Tax() {

    }

    @Test
    public void Employee_10_Qualifications() {

    }

    @Test
    public void Employee_11_Search_Employee() {

    }

    @Parameters({"browser"})
    @AfterClass(alwaysRun = true)
    public void afterClass(String browserName) {
        log.info("Post-condition: Close browser '" + browserName + "'");
    }

    WebDriver driver;
    LoginPO loginPage;
    AddEmployeePO addEmployeePage;
    DashboardPO dashboardPage;
    EmployeeListPO employeeListPage;
    PersonalDetailPO personalDetailPage;
}
