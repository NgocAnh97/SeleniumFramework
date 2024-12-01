package actions.pageObjects.jquery;

import actions.commons.BasePage;
import interfaces.pageUIs.jquery.HomePageUI;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePO extends BasePage {
    WebDriver driver;

    public HomePO(WebDriver driver) {
        this.driver = driver;
    }

    public void openPageByNumber(String pageNumber) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageNumber);
        clickToElement(driver, String.format(HomePageUI.DYNAMIC_PAGE_LINK, pageNumber));
        sleepInSeconds(2);
    }

    public boolean isPageNumberActive(String pageNumber) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageNumber);
        return getElementAttributeValue(driver, HomePageUI.DYNAMIC_PAGE_LINK, "class", pageNumber).endsWith("active");
    }

    public void enterTextboxByHeaderName(String headerName, String value) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, headerName);
        sendKeysToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, value, headerName);
        pressKeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, Keys.ENTER, headerName);
        sleepInSeconds(2);
    }

    public boolean isRowDataValueDisplayed(String females, String country, String males, String total) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_ROW, females, country, males, total);
        return isElementDisplayed(driver, HomePageUI.DYNAMIC_ROW, females, country, males, total);
    }

    public void deleteRowByCountryValue(String countryValue) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_REMOVE_BUTTON_BY_COUNTRY_VALUE, countryValue);
        clickToElement(driver, HomePageUI.DYNAMIC_REMOVE_BUTTON_BY_COUNTRY_VALUE, countryValue);
    }

    public void editRowByCountryValue(String countryValue) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_REMOVE_BUTTON_BY_COUNTRY_VALUE, countryValue);
        clickToElement(driver, HomePageUI.DYNAMIC_REMOVE_BUTTON_BY_COUNTRY_VALUE, countryValue);
    }

    public boolean isRowDataValueNotDisplayed(String country) {
        waitForElementInvisible(driver, HomePageUI.DYNAMIC_ROW_BY_COUNTRY_VALUE, country);
        return isElementDisplayed(driver, HomePageUI.DYNAMIC_ROW_BY_COUNTRY_VALUE, country);
    }

    public void clickToLoadDataButton() {
        waitForElementClickable(driver, HomePageUI.LOAD_DATA_BUTTON);
        clickToElement(driver, HomePageUI.LOAD_DATA_BUTTON);
    }

    public void enterToTextboxByIndex(String rowIndex, String columnName, String value) {
        int columnIndexNumber = getListElementSize(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_NUMBER, columnName) + 1;

        String columnIndex = String.valueOf(columnIndexNumber);
        waitForElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_AND_COLUMN_INDEX, rowIndex, columnIndex);
        sendKeysToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_AND_COLUMN_INDEX, value, rowIndex, columnIndex);
    }

    public void selectToDropdownByIndex(String rowIndex, String columnName, String valueToSelect) {
        int columnIndexNumber = getListElementSize(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_NUMBER, columnName) + 1;

        String columnIndex = String.valueOf(columnIndexNumber);
        waitForElementClickable(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_AND_COLUMN_INDEX, rowIndex, columnIndex);
        selectItemInDropdown(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_AND_COLUMN_INDEX, valueToSelect, rowIndex, columnIndex);
    }

    public void checkToCheckboxByIndex(String rowIndex, String columnName, boolean expectStatus) throws Exception {
        int columnIndexNumber = getListElementSize(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_NUMBER, columnName) + 1;

        String columnIndex = String.valueOf(columnIndexNumber);

        waitForElementClickable(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_AND_COLUMN_INDEX, rowIndex, columnIndex);
        checkToCheckboxByStatus(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_AND_COLUMN_INDEX, expectStatus, rowIndex, columnIndex);
    }

    public void clickToIconByIndex(String rowIndex, String actionName) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_ICON_BY_ROW_AND_COLUMN_INDEX, rowIndex, actionName);
        clickToElement(driver, HomePageUI.DYNAMIC_ICON_BY_ROW_AND_COLUMN_INDEX, rowIndex, actionName);
    }
}
