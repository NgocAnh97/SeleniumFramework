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
        firstName = "Irene";
        lastName = "Bae";
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
        log.info("Add_New_01 - Step 01: Open 'Employee list' page");
        employeeListPage = dashboardPage.clickToPIMPage();

        log.info("Add_New_01 - Step 02: Click to 'Add' button");
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();

        log.info("Add_New_01 - Step 03: Enter valid info to 'First name' textbox");
        addEmployeePage.enterToFirstNameTextbox(firstName);

        log.info("Add_New_01 - Step 04: Enter valid info to 'Last name' textbox");
        addEmployeePage.enterToLastNameTextbox(lastName);

        log.info("Add_New_01 - Step 05: Get value of 'Employee ID'");
        employeeID = addEmployeePage.getEmployeeID();
        System.out.println("employeeID: " + employeeID);

        log.info("Add_New_01 - Step 11: Click to 'Save' button");
        personalDetailPage = addEmployeePage.clickToSaveButton();

        verifyTrue(personalDetailPage.verifyAddSuccessMessage());
    }

    @Test
    public void Employee_02_Upload_Avatar() {
        personalDetailPage.clickToEmployeeAvatarImage();

        Dimension beforeUpload = personalDetailPage.getAvatarSize();
        personalDetailPage.uploadMultipleFiles(driver, avatarImageName);

        personalDetailPage.clickToSaveButtonAtProfileContainer();

        Assert.assertTrue(personalDetailPage.isSuccessMessageDisplayed(driver));

        personalDetailPage.waitAllLoadingIconInvisible(driver);

        Assert.assertTrue(personalDetailPage.isProfileAvatarUploadSuccess(beforeUpload));
    }

    @Test
    public void Employee_03_Personal_Details() {
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
