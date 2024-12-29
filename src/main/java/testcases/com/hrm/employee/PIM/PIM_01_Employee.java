package testcases.com.hrm.employee.PIM;

import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import actions.pageObjects.hrm.DashboardPO;
import actions.pageObjects.hrm.LoginPO;
import actions.pageObjects.hrm.PageGenerator;
import actions.pageObjects.hrm.pim.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PIM_01_Employee extends BaseTest {
    String employeeID, statusValue, firstName, lastName, editFirstName, editLastName;
    String driverLicenseNumber, licenseExpiryDate, nationality, maritalStatus, dateOfBirth,
            gender, street1, street2, country, name, relationship, homeTelephone, pleaseSpecify;
    String avatarImageName = "yoona.jpg";

    @Parameters({"browser", "env"})
    @BeforeClass
    public void beforeClass(String browserName, String env) {
        driver = getBrowserDriver(browserName, env);

        loginPage = PageGenerator.getLoginPage(driver);
        statusValue = "Enabled";
        firstName = "Yoonah";
        lastName = "SNSD";
        editFirstName = "Yoona";
        editLastName = "Im";

        driverLicenseNumber = "012345678";
        licenseExpiryDate = "2024-01-12";
        nationality = "American";
        maritalStatus = "Single";
        dateOfBirth = "1990-30-05";
        gender = "Female";
        street1 = "Jeju";
        street2 = "Soul";
        country = "Bhutan";

        name = "Yoona Im";
        relationship = "Other";
        homeTelephone = "0905000000";
        pleaseSpecify = "Idol";

        loginPage.enterToTextboxByName(driver, GlobalConstants.ADMIN_ORANGE_HRM_USERNAME, "username");
        loginPage.enterToTextboxByName(driver, GlobalConstants.ADMIN_ORANGE_HRM_PASSWORD, "password");
        loginPage.clickToButtonByType(driver, "submit");

        dashboardPage = PageGenerator.getDashboardPage(driver);
    }

    @Test
    public void Employee_01_Add_New_Employee() {
        log.info("Add_New - Step 01: Open 'Employee list' page");
        employeeListPage = dashboardPage.openPIMPage();

        log.info("Add_New - Step 02: Click to 'Add employee' page");
        addEmployeePage = employeeListPage.openAddEmployeePage();

        log.info("Add_New - Step 03: Enter valid info {} to 'First name' textbox", firstName);
        addEmployeePage.enterToFirstNameTextbox(firstName);

        log.info("Add_New - Step 04: Enter valid info {} to 'Last name' textbox", lastName);
        addEmployeePage.enterToLastNameTextbox(lastName);

        log.info("Add_New - Step 05: Get value of 'Employee ID'");
        employeeID = addEmployeePage.getEmployeeID();

        log.info("Add_New - Step 06: Click to 'Save' button");
        personalDetailPage = addEmployeePage.clickToSaveButtonAtAddEmployeeContainer();

        log.info("Add_New - Step 07: Verify success message is displayed");
        verifyTrue(personalDetailPage.isAddSuccessMessageDisplayed());
    }

    @Test
    public void Employee_02_Upload_Avatar() {
        log.info("Upload_Avatar - Step 01: Click to Employee avatar image");
        personalDetailPage.clickToEmployeeAvatarImage();

        log.info("Upload_Avatar - Step 02: Upload avatar image {}", avatarImageName);
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

    @Test
    public void Employee_04_Contact_Details() {
        log.info("Contact_Details - Step 01: Update contact employee");
        contactDetailPage = personalDetailPage.openContactDetailPage(driver);
        contactDetailPage.enterToStreet1Textbox(street1);
        contactDetailPage.enterToStreet2Textbox(street2);
        contactDetailPage.selectCountryDropdown(country);

        contactDetailPage.clickToSaveButtonAtContactDetailContainer();
        contactDetailPage.isSuccessMessageDisplayed(driver);
        contactDetailPage.waitAllLoadingIconInvisible(driver);

        log.info("Contact_Details - Step 02: Verify contact employee information updated success");
        Assert.assertEquals(contactDetailPage.getStreet1TextboxValue(), street1);
        Assert.assertEquals(contactDetailPage.getStreet2TextboxValue(), street2);
        Assert.assertEquals(contactDetailPage.getCountryDropdownValue(), country);
    }

    @Test
    public void Employee_05_Emergency_Contacts() {
        log.info("Emergency_Contacts - Step 01: Update Emergency Contacts");
        emergencyContactPage = personalDetailPage.openEmergencyContactPage(driver);
        emergencyContactPage.clickToAddButton();
        emergencyContactPage.enterToNameTextbox(name);
        emergencyContactPage.enterToRelationshipTextbox(relationship);
        emergencyContactPage.enterToHomeTelephoneTextbox(homeTelephone);

        emergencyContactPage.clickToSaveButtonAtEmergencyContactContainer();
        emergencyContactPage.isAddInfoSuccessMessageDisplayed(driver);
        emergencyContactPage.waitAllLoadingIconInvisible(driver);

        log.info("Emergency_Contacts - Step 02: Verify Emergency Contacts information updated success");
        Assert.assertEquals(emergencyContactPage.getNameInRecordValue(), name);
        Assert.assertEquals(emergencyContactPage.getRelationshipInRecordValue(), relationship);
        Assert.assertEquals(emergencyContactPage.getHomeTelephoneInRecordValue(), homeTelephone);
    }

    @Test
    public void Employee_06_Dependents() {
        log.info("Dependents - Step 01: Update Dependents");
        dependentPage = emergencyContactPage.openDependentPage(driver);
        dependentPage.clickToAddButton();
        dependentPage.enterToNameTextbox(name);
        dependentPage.enterToRelationshipDropdown(relationship);
        dependentPage.enterToPleaseSpecifyTextbox(pleaseSpecify);

        dependentPage.clickToSaveButtonAtDependentContainer();
        dependentPage.isAddInfoSuccessMessageDisplayed(driver);
        dependentPage.waitAllLoadingIconInvisible(driver);

        log.info("Dependents - Step 02: Verify Dependents information updated success");
        Assert.assertEquals(dependentPage.getNameInRecordValue(), name);
        Assert.assertEquals(dependentPage.getRelationshipInRecordValue(), pleaseSpecify);
    }

    @Parameters({"browser"})
    @AfterClass
    public void afterClass(String browserName) {
        log.info("Post-condition: Close browser '{}'", browserName);
        driver.quit();
    }

    private WebDriver driver;
    private LoginPO loginPage;
    private AddEmployeePO addEmployeePage;
    private DashboardPO dashboardPage;
    private EmployeePO employeeListPage;
    private PersonalDetailPO personalDetailPage;
    private ContactDetailPO contactDetailPage;
    private EmergencyContactPO emergencyContactPage;
    private DependentPO dependentPage;
}
