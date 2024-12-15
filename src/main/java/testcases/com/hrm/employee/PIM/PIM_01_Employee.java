package testcases.com.hrm.employee.PIM;

import actions.commons.BaseTest;
import actions.pageObjects.hrm.DashboardPO;
import actions.pageObjects.hrm.LoginPO;
import actions.pageObjects.hrm.PageGenerator;
import actions.pageObjects.hrm.pim.AddEmployeePO;
import actions.pageObjects.hrm.pim.EmployeePO;
import actions.pageObjects.hrm.pim.PersonalDetailPO;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PIM_01_Employee extends BaseTest {
    String employeeID, statusValue, firstName, lastName, editFirstName, editLastName;
    String driverLicenseNumber, licenseExpiryDate, nationality, maritalStatus, dateOfBirth, gender;
    String avatarImageName = "yoona.jpg";

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserUrl(browserName, url);
        log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + url + "'");

        loginPage = PageGenerator.getLoginPage(driver);
        statusValue = "Enabled";
        firstName = "Yoonah";
        lastName = "SNSD";
        editFirstName = "Yoona";
        editLastName = "Im";

        driverLicenseNumber = "012345678";
        licenseExpiryDate = "2024-01-12";
        nationality = "American";
        maritalStatus = "Married";
        dateOfBirth = "1990-30-05";
        gender = "Female";

        log.info("Pre-condition - Step 02: Login with Admin role");
        loginPage.enterToTextboxByName(driver, "Admin", "username");
        loginPage.enterToTextboxByName(driver, "admin123", "password");
        loginPage.clickToButtonByType(driver, "submit");

        dashboardPage = PageGenerator.getDashboardPage(driver);
    }

    @Test
    public void Employee_01_Add_New_Employee() throws InterruptedException {
        log.info("Add_New - Step 01: Open 'Employee list' page");
        employeeListPage = dashboardPage.clickToPIMPage();

        log.info("Add_New - Step 02: Click to 'Add employee' page");
        addEmployeePage = employeeListPage.openAddEmployeePage();

        log.info("Add_New - Step 03: Enter valid info to 'First name' textbox");
        addEmployeePage.enterToFirstNameTextbox(firstName);

        log.info("Add_New - Step 04: Enter valid info to 'Last name' textbox");
        addEmployeePage.enterToLastNameTextbox(lastName);

        log.info("Add_New - Step 05: Get value of 'Employee ID'");
        employeeID = addEmployeePage.getEmployeeID();

        log.info("Add_New - Step 11: Click to 'Save' button");
        personalDetailPage = addEmployeePage.clickToSaveButtonAtAddEmployeeContainer();

        log.info("Add_New - Step 12: Verify success message is displayed");
        verifyTrue(personalDetailPage.verifyAddSuccessMessage());
    }

    @Test
    public void Employee_02_Upload_Avatar() {
        log.info("Upload_Avatar - Step 01: Click to Employee avatar image");
        personalDetailPage.clickToEmployeeAvatarImage();

        log.info("Upload_Avatar - Step 02: Upload avatar image");
        Dimension beforeUpload = personalDetailPage.getAvatarSize();
        personalDetailPage.uploadMultipleFiles(driver, avatarImageName);

        log.info("Upload_Avatar - Step 03: Click to 'Save' button at Profile container");
        personalDetailPage.clickToSaveButtonAtProfileContainer();

        log.info("Upload_Avatar - Step 04: Verify upload message is displayed");
        Assert.assertTrue(personalDetailPage.isSuccessMessageDisplayed(driver));

        log.info("Upload_Avatar - Step 05: Verify avatar upload, change success");
        personalDetailPage.waitAllLoadingIconInvisible(driver);

        Assert.assertTrue(personalDetailPage.isProfileAvatarUploadSuccess(beforeUpload));
    }

    @Test
    public void Employee_03_Personal_Details() {
        log.info("Personal_Details - Step 01: Upload employee information");
        personalDetailPage = personalDetailPage.openPersonalDetailPage(driver);

        personalDetailPage.enterToFirstNameTextbox(editFirstName);
        personalDetailPage.enterToLastNameTextbox(editLastName);

        Assert.assertEquals(personalDetailPage.getEmployeeID(), employeeID);

        personalDetailPage.enterToDriverLicenseTextbox(driverLicenseNumber);
        personalDetailPage.enterToLicenseExpiryDateTextbox(licenseExpiryDate);
        personalDetailPage.selectNationalityDropdown(nationality);
        personalDetailPage.selectMaritalStatusDropdown(maritalStatus);
        personalDetailPage.enterToDateOfBirthTextbox(dateOfBirth);
        personalDetailPage.selectGenderRadioButton(gender);

        personalDetailPage.clickToSaveButtonAtPersonalDetailContainer();

        personalDetailPage.isSuccessMessageDisplayed(driver);
        personalDetailPage.waitAllLoadingIconInvisible(driver);

        log.info("Personal_Details - Step 02: Verify employee information uploaded success");
        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(), editFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(), editLastName);
        Assert.assertEquals(personalDetailPage.getEmployeeID(), employeeID);
        Assert.assertEquals(personalDetailPage.getDriverLicenseTextboxValue(), driverLicenseNumber);
        Assert.assertEquals(personalDetailPage.getLicenseExpiryTextboxValue(), licenseExpiryDate);
        Assert.assertEquals(personalDetailPage.getNationalityDropdownValue(), nationality);
        Assert.assertEquals(personalDetailPage.getMaritalStatusDropdownValue(), maritalStatus);
        Assert.assertEquals(personalDetailPage.getDateOfBirthTextboxValue(), dateOfBirth);
        Assert.assertTrue(personalDetailPage.getGenderRadioButtonSelectedByValue(gender));
    }
//
//    @Test
//    public void Employee_04_Contact_Details() {
//
//    }
//
//    @Test
//    public void Employee_05_Emergency_Details() {
//
//    }
//
//    @Test
//    public void Employee_06_Assigned_Dependents() {
//
//     }

    @Parameters({"browser"})
    @AfterClass
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
