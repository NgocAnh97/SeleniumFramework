package actions.pageObjects.hrm.pim;

import actions.pageObjects.hrm.PageGenerator;
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

    public EmployeePO openEmployeeListPage() {
        return PageGenerator.getEmployeeListPage(driver);
    }

    public boolean verifyAddSuccessMessage() {
        waitForElementVisible(driver, EmployeeListPageUI.ADD_EMPLOYEE_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, EmployeeListPageUI.ADD_EMPLOYEE_SUCCESS_MESSAGE);
    }

    public void clickToEmployeeAvatarImage() {
        waitAllLoadingIconInvisible(driver);
        waitForElementClickable(driver, EmployeeListPageUI.EMPLOYEE_IMAGE);
        clickToElement(driver, EmployeeListPageUI.EMPLOYEE_IMAGE);
    }

    public void clickToSaveButtonAtProfileContainer() {
        waitForElementClickable(driver, EmployeeListPageUI.SAVE_BUTTON_AT_PROFILE_CONTAINER);
        clickToElement(driver, EmployeeListPageUI.SAVE_BUTTON_AT_PROFILE_CONTAINER);
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

    public void enterToDriverLicenseTextbox(String driverLicenseNumber) {
        waitForElementVisible(driver, PersonalDetailPageUI.DRIVER_LICENSE_NUMBER_TEXTBOX);
        sendKeysToElement(driver, PersonalDetailPageUI.DRIVER_LICENSE_NUMBER_TEXTBOX, driverLicenseNumber);
    }

    public void enterToLicenseExpiryDateTextbox(String licenseExpiryDate) {
        waitForElementVisible(driver, PersonalDetailPageUI.LICENSE_EXPIRY_DATE_TEXTBOX);
        sendKeysToElement(driver, PersonalDetailPageUI.LICENSE_EXPIRY_DATE_TEXTBOX, licenseExpiryDate);
    }

    public void selectNationalityDropdown(String nationality) {
        waitForElementClickable(driver, PersonalDetailPageUI.NATIONALITY_DROPDOWN_PARENT);
        selectItemInCustomDropdown(driver, PersonalDetailPageUI.NATIONALITY_DROPDOWN_PARENT, PersonalDetailPageUI.NATIONALITY_DROPDOWN_CHILD, nationality);
    }

    public void selectMaritalStatusDropdown(String maritalStatus) {
        waitForElementClickable(driver, PersonalDetailPageUI.MARITAL_STATUS_DROPDOWN_PARENT);
        selectItemInCustomDropdown(driver, PersonalDetailPageUI.MARITAL_STATUS_DROPDOWN_PARENT, PersonalDetailPageUI.MARITAL_DROPDOWN_CHILD, maritalStatus);
    }

    public void enterToDateOfBirthTextbox(String dateOfBirth) {
        waitForElementVisible(driver, PersonalDetailPageUI.DATE_OF_BIRTH_TEXTBOX);
        sendKeysToElement(driver, PersonalDetailPageUI.DATE_OF_BIRTH_TEXTBOX, dateOfBirth);
    }

    public void selectGenderRadioButton(String gender) {
        clickToElementByJS(driver, PersonalDetailPageUI.GENDER_RADIO_BUTTON, gender);
//        waitForElementClickable(driver, PersonalDetailPageUI.GENDER_RADIO_BUTTON, gender);
        checkToDefaultCheckboxRadio(driver, PersonalDetailPageUI.GENDER_RADIO_BUTTON, gender);
    }

    public void clickToSaveButtonAtPersonalDetailContainer() {
        waitForElementClickable(driver, PersonalDetailPageUI.SAVE_BUTTON_AT_PERSONAL_DETAIL_CONTAINER);
        clickToElement(driver, PersonalDetailPageUI.SAVE_BUTTON_AT_PERSONAL_DETAIL_CONTAINER);
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX);
        return getValue(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX);
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX);
        return getValue(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX);
    }

    public String getEmployeeID() {
        waitForElementVisible(driver, PersonalDetailPageUI.EMPLOYEE_ID);
        return getElementText(driver, PersonalDetailPageUI.EMPLOYEE_ID);
    }

    public String getDriverLicenseTextboxValue() {
        waitForElementVisible(driver, PersonalDetailPageUI.DRIVER_LICENSE_NUMBER_TEXTBOX);
        return getValue(driver, PersonalDetailPageUI.DRIVER_LICENSE_NUMBER_TEXTBOX);
    }

    public String getLicenseExpiryTextboxValue() {
        waitForElementVisible(driver, PersonalDetailPageUI.LICENSE_EXPIRY_DATE_TEXTBOX);
        return getValue(driver, PersonalDetailPageUI.LICENSE_EXPIRY_DATE_TEXTBOX);
    }

    public String getNationalityDropdownValue() {
        waitForElementVisible(driver, PersonalDetailPageUI.NATIONALITY_INPUT_DROPDOWN_ITEM_SELECTED);
        return getElementText(driver, PersonalDetailPageUI.NATIONALITY_INPUT_DROPDOWN_ITEM_SELECTED);
    }

    public String getMaritalStatusDropdownValue() {
        waitForElementVisible(driver, PersonalDetailPageUI.MARITAL_STATUS_DROPDOWN_ITEM_SELECTED);
        return getElementText(driver, PersonalDetailPageUI.MARITAL_STATUS_DROPDOWN_ITEM_SELECTED);
    }

    public String getDateOfBirthTextboxValue() {
        waitForElementVisible(driver, PersonalDetailPageUI.DATE_OF_BIRTH_TEXTBOX);
        return getValue(driver, PersonalDetailPageUI.DATE_OF_BIRTH_TEXTBOX);
    }

    public boolean getGenderRadioButtonSelectedByValue(String gender) {
        waitForElementSelected(driver, PersonalDetailPageUI.GENDER_RADIO_BUTTON, gender);
        return isElementSelected(driver, PersonalDetailPageUI.GENDER_RADIO_BUTTON, gender);
    }
}
