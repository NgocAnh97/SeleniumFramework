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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class PIM_01_Employee extends BaseTest {
    String employeeID, statusValue, firstName, lastName, editFirstName, editLastName;
    String driverLicenseNumber, licenseExpiryDate, nationality, maritalStatus, dateOfBirth,
            gender, street1, street2, country, name, relationship, homeTelephone, pleaseSpecify;
    String avatarImageName = "yoona.jpg";
    private WebDriver driver;
    private EmployeePO employeeListPage;
    private AddEmployeePO addEmployeePage;
    private ChangeProfilePicturePO changeProfilePicturePage;
    private DashboardPO dashboardPage;
    private PersonalDetailPO personalDetailPage;
    private ContactDetailPO contactDetailPage;
    private EmergencyContactPO emergencyContactPage;
    private DependentPO dependentPage;

    @Parameters({"browser", "environment"})
    @BeforeMethod
    public void beforeClass(String browserName, String environment) {
        driver = getBrowserDriver(browserName, environment);

        LoginPO loginPage = PageGenerator.getLoginPage(driver);
        Random random = new Random();
        employeeID = String.valueOf(random.nextInt(1000));
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
        employeeListPage = dashboardPage.openPIMPage();

        personalDetailPage = employeeListPage.clickEditButton();
    }

    @Test
    public void Employee_01_Add_New_Employee() {
        log.info("Add_New - Step 02: Click to 'Add employee' page");
        addEmployeePage = personalDetailPage.openAddEmployeePage();

        log.info("Add_New - Step 03: Enter valid info {} to 'First name' textbox", firstName);
        addEmployeePage.enterToFirstNameTextbox(firstName);

        log.info("Add_New - Step 04: Enter valid info {} to 'Last name' textbox", lastName);
        addEmployeePage.enterToLastNameTextbox(lastName);

        log.info("Add_New - Step 05: Get value of 'Employee ID'");
        addEmployeePage.enterToEmployeeId(employeeID);

        log.info("Add_New - Step 06: Click to 'Save' button");
        personalDetailPage = addEmployeePage.clickToSaveButtonAtAddEmployeeContainer();

        log.info("Add_New - Step 07: Verify success message is displayed");
        verifyTrue(personalDetailPage.isAddSuccessMessageDisplayed());
    }

    @Test
    public void Employee_02_Upload_Avatar() {
        log.info("Upload_Avatar - Step 01: Click to Employee avatar image");
        changeProfilePicturePage = personalDetailPage.clickToEmployeeAvatarImage();

        log.info("Upload_Avatar - Step 02: Upload avatar image {}", avatarImageName);
        Dimension beforeUpload = changeProfilePicturePage.getAvatarSize();
        changeProfilePicturePage.uploadMultipleFiles(driver, avatarImageName);

        log.info("Upload_Avatar - Step 03: Click to 'Save' button at Profile container");
        changeProfilePicturePage.clickToSaveButtonAtProfileContainer();

        log.info("Upload_Avatar - Step 04: Verify upload message is displayed");
        Assert.assertTrue(changeProfilePicturePage.isSuccessMessageDisplayed(driver));

        log.info("Upload_Avatar - Step 05: Verify avatar upload, change success");
        changeProfilePicturePage.waitAllLoadingIconInvisible(driver);

        Assert.assertTrue(changeProfilePicturePage.isProfileAvatarUploadSuccess(beforeUpload));
    }

    @Test
    public void Employee_03_Personal_Details() {
        log.info("Personal_Details - Step 01: Upload employee information");
        personalDetailPage = personalDetailPage.openPersonalDetailPage();

        personalDetailPage.enterToFirstNameTextbox(editFirstName);
        personalDetailPage.enterToLastNameTextbox(editLastName);
        personalDetailPage.enterToEmployeeId(employeeID);
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
        contactDetailPage = personalDetailPage.openContactDetailPage();
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
        emergencyContactPage = personalDetailPage.openEmergencyContactPage();
        emergencyContactPage.clickToAddButton();
        emergencyContactPage.enterToNameTextbox(name);
        emergencyContactPage.enterToRelationshipTextbox(relationship);
        emergencyContactPage.enterToHomeTelephoneTextbox(homeTelephone);

        emergencyContactPage.clickToSaveButtonAtEmergencyContactContainer();
        Assert.assertTrue(emergencyContactPage.isAddInfoSuccessMessageDisplayed(driver));
        emergencyContactPage.waitAllLoadingIconInvisible(driver);

        log.info("Emergency_Contacts - Step 02: Verify Emergency Contacts information updated success");
        Assert.assertEquals(emergencyContactPage.getNameInRecordValue(), name);
        Assert.assertEquals(emergencyContactPage.getRelationshipInRecordValue(), relationship);
        Assert.assertEquals(emergencyContactPage.getHomeTelephoneInRecordValue(), homeTelephone);
    }

    @Test
    public void Employee_06_Dependents() {
        log.info("Dependents - Step 01: Update Dependents");
        dependentPage = personalDetailPage.openDependentPage();
        dependentPage.clickToAddButton();
        dependentPage.enterToNameTextbox(name);
        dependentPage.enterToRelationshipDropdown(relationship);
        dependentPage.enterToPleaseSpecifyTextbox(pleaseSpecify);

        dependentPage.clickToSaveButtonAtDependentContainer();
        Assert.assertTrue(dependentPage.isAddInfoSuccessMessageDisplayed(driver));
        dependentPage.waitAllLoadingIconInvisible(driver);

        log.info("Dependents - Step 02: Verify Dependents information updated success");
        Assert.assertEquals(dependentPage.getNameInRecordValue(), name);
        Assert.assertEquals(dependentPage.getRelationshipInRecordValue(), pleaseSpecify);
    }

    @Parameters("browser")
    @AfterMethod(alwaysRun = true)
    public void afterClass(String browserName) {
        log.info("Post-condition: Close browser '{}'", browserName);
        closeBrowserDriver(driver);
    }

    protected void closeBrowserDriver(WebDriver driver) {
        String cmd = null;
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = {}", osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = {}", driverInstanceName);

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("internetexplorer")) {
                browserDriverName = "IEDriverServer";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            driver.manage().deleteAllCookies();
            driver.quit();
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
