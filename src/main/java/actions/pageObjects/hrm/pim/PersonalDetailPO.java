package actions.pageObjects.hrm.pim;

import interfaces.pageUIs.orangehrm.pim.EmployeeListPageUI;
import interfaces.pageUIs.orangehrm.pim.PersonalDetailPageUI;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class PersonalDetailPO extends PersonalListTabs {
    private WebDriver driver;

    public PersonalDetailPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public static String getFirstNameTextboxValue() {
        return null;
    }

    public static String getLastNameTextboxValue() {
        return null;
    }

    public static String getDriverLicenseTextboxValue() {
        return null;
    }

    public static String getLicenseExpiryTextboxValue() {
        return null;
    }

    public static String getNationalityDropdownValue() {
        return null;
    }

    public static String getMaritalStatusDropdownValue() {
        return null;
    }

    public static String getDateOfBirthTextboxValue() {
        return null;
    }

    public boolean getGenderRadioButtonSelected() {
        return true;
    }

    public EmployeePO openEmployeeListPage() {
        return new EmployeePO(driver);
    }

    public boolean verifyAddSuccessMessage() {
        waitForElementVisible(driver, EmployeeListPageUI.ADD_EMPLOYEE_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, EmployeeListPageUI.ADD_EMPLOYEE_SUCCESS_MESSAGE);
    }

    public void clickToEmployeeAvatarImage() {
        waitForElementClickable(driver, EmployeeListPageUI.EMPLOYEE_IMAGE);
        clickToElement(driver, EmployeeListPageUI.EMPLOYEE_IMAGE);
    }

    public void clickToSaveButtonAtProfileContainer() {
        waitForElementClickable(driver, EmployeeListPageUI.SAVE_BUTTON_AT_PROFILE_CONTAINER);
        clickToElement(driver, EmployeeListPageUI.SAVE_BUTTON_AT_PROFILE_CONTAINER);
    }

    public void clickToSaveButtonAtPersonalDetailContainer() {
        waitForElementClickable(driver, EmployeeListPageUI.SAVE_BUTTON_AT_PERSONAL_DETAIL_CONTAINER);
        clickToElement(driver, EmployeeListPageUI.SAVE_BUTTON_AT_PERSONAL_DETAIL_CONTAINER);
    }

    public boolean isProfileAvatarUploadSuccess(Dimension beforeUpload) {
        Dimension afterUpload = getAvatarSize();

        return beforeUpload.equals(afterUpload);
    }

    public Dimension getAvatarSize() {
        return getElementSize(driver, EmployeeListPageUI.EMPLOYEE_IMAGE);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX);
        sendKeysToElement(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX);
        sendKeysToElement(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public String getEmployeeID() {
        waitForElementVisible(driver, PersonalDetailPageUI.EMPLOYEE_ID);
        return getElementText(driver, PersonalDetailPageUI.EMPLOYEE_ID);
    }

    public void enterToDriverLicenseTextbox(String driverLicenseNumber) {
        waitForElementVisible(driver, PersonalDetailPageUI.DRIVER_LICENSE_NUMBER_TEXTBOX);
        sendKeysToElement(driver, PersonalDetailPageUI.DRIVER_LICENSE_NUMBER_TEXTBOX, driverLicenseNumber);
    }

    public void enterToLicenseExpiryDateTextbox(String licenseExpiryDate) {
        waitForElementVisible(driver, PersonalDetailPageUI.LICENSE_EXPIRY_DATE_TEXTBOX);
        sendKeysToElement(driver, PersonalDetailPageUI.LICENSE_EXPIRY_DATE_TEXTBOX, licenseExpiryDate);
    }

    public void selectNationalityDropdown(String nationalityValue) {
        waitForElementClickable(driver, PersonalDetailPageUI.NATIONALITY_TEXTBOX);
        selectItemInCustomDropdown(driver, PersonalDetailPageUI.NATIONALITY_TEXTBOX, PersonalDetailPageUI.NATIONALITY_ITEMS, nationalityValue);
    }

    public void selectMaritalStatusDropdown(String maritalStatusValue) {
        waitForElementClickable(driver, PersonalDetailPageUI.MARITAL_STATUS_TEXTBOX);
        selectItemInCustomDropdown(driver, PersonalDetailPageUI.MARITAL_STATUS_TEXTBOX, PersonalDetailPageUI.MARITAL_ITEMS, maritalStatusValue);
    }

    public void enterToDateOfBirthTextbox(String dateOfBirth) {
        waitForElementVisible(driver, PersonalDetailPageUI.DATE_OF_BIRTH_TEXTBOX);
        sendKeysToElement(driver, PersonalDetailPageUI.DATE_OF_BIRTH_TEXTBOX, dateOfBirth);
    }

    public void selectGenderRadioButton(String genderValue) {
        waitForElementClickable(driver, PersonalDetailPageUI.GENDER_RADIO_BUTTON);
        checkToDefaultCheckboxRadio(driver, PersonalDetailPageUI.GENDER_RADIO_BUTTON);
    }
}
