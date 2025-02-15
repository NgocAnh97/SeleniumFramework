package actions.commons;

import actions.pageObjects.nopcommerce.user.UserAddressPageObjects;
import actions.pageObjects.nopcommerce.user.UserMyProductReviewObjects;
import actions.pageObjects.nopcommerce.user.UserRewardPointObjects;
import interfaces.pageUIs.nopcommerce.BasePageUI;
import interfaces.pageUIs.orangehrm.pim.EmployeeListPageUI;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

@Log4j2
public class BasePage {
    public static BasePage getBasePageObject() {
        return new BasePage();
    }

    public void openPageURL(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.of(GlobalConstants.LONG_TIMEOUT, ChronoUnit.SECONDS)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    public String getTextAlert(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    public void sendKeyToAlert(WebDriver driver, String textValue) {
        waitForAlertPresence(driver).sendKeys(textValue);
    }

    public void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    public void closeAllTabWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!parentID.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentID);
        }
    }

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    private By getByXpath(String locator, String... params) {
        return By.xpath(String.format(locator, (Object[]) params));
    }

    private WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    private List<WebElement> getListWebElement(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    private By getByLocator(String prefixLocator) {
        By by;
        if (prefixLocator.toLowerCase().startsWith("id=")) {
            by = By.id(prefixLocator.substring(3));
        } else if (prefixLocator.toLowerCase().startsWith("css=")) {
            by = By.cssSelector(prefixLocator.substring(4));
        } else if (prefixLocator.toLowerCase().startsWith("class=")) {
            by = By.className(prefixLocator.substring(6));
        } else if (prefixLocator.toLowerCase().startsWith("tagname=")) {
            by = By.tagName(prefixLocator.substring(8));
        } else if (prefixLocator.toLowerCase().startsWith("name=")) {
            by = By.name(prefixLocator.substring(5));
        } else if (prefixLocator.toLowerCase().startsWith("xpath=")) {
            by = By.xpath(prefixLocator.substring(6));
        } else {
            throw new RuntimeException("Locator type is not supported!!");
        }
        return by;
    }

    public By getByLocator(String prefixLocator, String... params) {
        By by;
        if (prefixLocator.toLowerCase().startsWith("id")) {
            by = By.id(getDynamicLocator(prefixLocator.substring(3), params));
        } else if (prefixLocator.toLowerCase().startsWith("css")) {
            by = By.cssSelector(getDynamicLocator(prefixLocator.substring(5), params));
        } else if (prefixLocator.toLowerCase().startsWith("class")) {
            by = By.className(getDynamicLocator(prefixLocator.substring(6), params));
        } else if (prefixLocator.toLowerCase().startsWith("tagname")) {
            by = By.tagName(getDynamicLocator(prefixLocator.substring(8), params));
        } else if (prefixLocator.toLowerCase().startsWith("name")) {
            by = By.name(getDynamicLocator(prefixLocator.substring(5), params));
        } else if (prefixLocator.toLowerCase().startsWith("xpath")) {
            by = By.xpath(getDynamicLocator(prefixLocator.substring(6), params));
        } else {
            throw new RuntimeException("Locator type is not supported!!");
        }
        return by;
    }

    public void clickToElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        element.click();
    }

    public void clickToElement(WebDriver driver, String locator, String... params) {
        WebElement element = getWebElement(driver, getDynamicLocator(locator, params));
        element.click();
    }

    public void sendKeysToElement(WebDriver driver, String locator, String textToSend) {
        WebElement element = getWebElement(driver, locator);
        element.sendKeys(Keys.chord(ctrlKey(), "a", Keys.BACK_SPACE));
        element.sendKeys(textToSend);
    }

    public void sendKeysToElementWithoutClear(WebDriver driver, String locator, String textToSend) {
        WebElement element = getWebElement(driver, locator);
        element.sendKeys(textToSend);
    }

    private Keys ctrlKey() {
        Keys key = null;
        if (System.getProperty("os.name").equalsIgnoreCase("WINDOWS")) {
            key = Keys.CONTROL;
        } else {
            key = Keys.COMMAND;
        }
        return key;
    }

    public void sendKeysToElement(WebDriver driver, String locator, String textValue, String... params) {
        WebElement element = getWebElement(driver, getDynamicLocator(locator, params));
        element.sendKeys(Keys.chord(ctrlKey(), "a", Keys.BACK_SPACE));
        element.sendKeys(textValue);
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
        new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... params) {
        new Actions(driver).sendKeys(getWebElement(driver, getDynamicLocator(locator, params)), key).perform();
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String textItem) {
        Select select = new Select(getWebElement(driver, locator));
        select.selectByValue(textItem);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String textItem, String... params) {
        Select select = new Select(getWebElement(driver, getDynamicLocator(locator, params)));
        select.selectByValue(textItem);
    }

    public String getSelectedItemDefaultDropdown(WebDriver driver, String locator) {
        Select select = new Select(getWebElement(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        Select select = new Select(getWebElement(driver, locator));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectItemText) {
//        waitForElementClickable(driver, parentLocator);
//        scrollToElement(driver, parentLocator);
        getWebElement(driver, parentLocator).click();
        sleepInSeconds(1);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.of(GlobalConstants.LONG_TIMEOUT, ChronoUnit.SECONDS)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectItemText)) {
                item.click();
                break;
            }
        }
    }

    public void sleepInSeconds(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getElementAttributeValue(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementAttributeValue(WebDriver driver, String locator, String attributeName, String... params) {
        return getWebElement(driver, getDynamicLocator(locator, params)).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getValue(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getAttribute("value");
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyValue) {
        return getWebElement(driver, locator).getCssValue(propertyValue);
    }

    public String getHexColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    public int getListElementSize(WebDriver driver, String locator) {
        return getListWebElement(driver, locator).size();
    }

    public Dimension getElementSize(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getSize();
    }

    public int getListElementSize(WebDriver driver, String locator, String... params) {
        return getListWebElement(driver, getDynamicLocator(locator, params)).size();
    }

    public void checkToDefaultCheckboxRadio(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void checkToDefaultCheckboxRadio(WebDriver driver, String locator, String... params) {
        WebElement element = getWebElement(driver, getDynamicLocator(locator, params));
        if (!element.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
        }
    }

    public void unCheckToDefaultCheckbox(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void checkToCheckboxByStatus(WebDriver driver, String locator, Boolean status) throws Exception {
        WebElement element = getWebElement(driver, locator);
        if (status) {
            if (!element.isSelected()) {
                element.click();
            }
        } else if (!status) {
            if (element.isSelected()) {
                element.click();
            }
        } else {
            throw new Exception(String.format("Invalid boolean status: %s", status));
        }
    }

    public void checkToCheckboxByStatus(WebDriver driver, String locator, Boolean status, String... params) throws Exception {
        WebElement element = getWebElement(driver, getDynamicLocator(locator, params));
        if (status) {
            if (!element.isSelected()) {
                element.click();
            }
        } else if (!status) {
            if (element.isSelected()) {
                element.click();
            }
        } else {
            throw new Exception(String.format("Invalid boolean status: %s", status));
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... params) {
        return getWebElement(driver, getDynamicLocator(locator, params)).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... params) {
        return getWebElement(driver, getDynamicLocator(locator, params)).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    public void switchToFrameIframe(WebDriver driver, String locator) {
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void doubleClickElement(WebDriver driver, String locator) {
        Actions action = new Actions(driver);
        action.doubleClick(getWebElement(driver, locator)).perform();
    }

    public void hoverMouseToElement(WebDriver driver, String locator) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, locator)).perform();
    }

    public void rightClick(WebDriver driver, String locator) {
        Actions action = new Actions(driver);
        action.contextClick(getWebElement(driver, locator)).perform();
    }

    public void dragAndDrop(WebDriver driver, String locator, String targetXpathLocator) {
        Actions action = new Actions(driver);
        action.dragAndDrop(getWebElement(driver, locator), getWebElement(driver, targetXpathLocator)).perform();
    }

    public void senKeyboardToElement(WebDriver driver, String locator, String keyValueAction) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, locator), keyValueAction);
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void highlightElementByJS(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    public void clickToElementByJS(WebDriver driver, String locator, String... params) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, getDynamicLocator(locator, params)));
    }

    public void scrollToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void removeAttributeInDOMByJS(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public boolean areJQueryAndJSLoadedSuccessByJS(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.of(GlobalConstants.LONG_TIMEOUT, ChronoUnit.SECONDS));

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getElementValidationMessageByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoadedByJS(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " + "&& typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.of(GlobalConstants.LONG_TIMEOUT, ChronoUnit.SECONDS)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForAllElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.of(GlobalConstants.LONG_TIMEOUT, ChronoUnit.SECONDS)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... params) {
        new WebDriverWait(driver, Duration.of(GlobalConstants.LONG_TIMEOUT, ChronoUnit.SECONDS)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator, params)));
    }

    public void waitForElementSelected(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.of(GlobalConstants.LONG_TIMEOUT, ChronoUnit.SECONDS)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public void waitForElementSelected(WebDriver driver, String locator, String... params) {
        new WebDriverWait(driver, Duration.of(GlobalConstants.LONG_TIMEOUT, ChronoUnit.SECONDS)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator, params)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.of(GlobalConstants.LONG_TIMEOUT, ChronoUnit.SECONDS)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator, String... params) {
        new WebDriverWait(driver, Duration.of(GlobalConstants.LONG_TIMEOUT, ChronoUnit.SECONDS)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator, params)));
    }

    public void waitForAllElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.of(GlobalConstants.LONG_TIMEOUT, ChronoUnit.SECONDS)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.of(GlobalConstants.LONG_TIMEOUT, ChronoUnit.SECONDS)).until(ExpectedConditions.elementToBeClickable(getWebElement(driver, locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... params) {
        new WebDriverWait(driver, Duration.of(GlobalConstants.LONG_TIMEOUT, ChronoUnit.SECONDS)).until(ExpectedConditions.elementToBeClickable(getWebElement(driver, getDynamicLocator(locator, params))));
    }

    private String getDynamicLocator(String locator, String... params) {
        return String.format(locator, (Object[]) params);
    }

    public List<WebElement> getListElement(WebDriver driver, String locator) {
        List<WebElement> elements = driver.findElements(getByLocator(locator));
        if (elements.isEmpty()) {
            throw new NoSuchElementException(String.format("No element found by locator: %s", locator));
        }
        return elements;
    }

    public List<WebElement> getListElement(WebDriver driver, String locator, String... params) {
        List<WebElement> elements = driver.findElements(getByLocator(locator));
        if (elements.isEmpty()) {
            throw new NoSuchElementException(String.format("No element found by locator: %s", locator));
        }
        return elements;
    }

    public UserAddressPageObjects openAddressPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
        clickToElement(driver, BasePageUI.ADDRESS_LINK);
        return PageGeneratorManager.getUserAddressPage(driver);
    }

    public UserRewardPointObjects openRewardPointsPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.REWARD_POINTS_LINK);
        clickToElement(driver, BasePageUI.REWARD_POINTS_LINK);
        return PageGeneratorManager.getUserRewardPointPage(driver);
    }

    public UserMyProductReviewObjects openMyProductReviewsPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEWS_LINK);
        clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEWS_LINK);
        return PageGeneratorManager.getUserMyProductReviewPage(driver);
    }

    public String getHeaderTitle(WebDriver driver) {
        waitForElementVisible(driver, GlobalConstants.AccountPageUI.HEADER_TITLE);
        return getElementText(driver, GlobalConstants.AccountPageUI.HEADER_TITLE);
    }

    public boolean isHeaderDisplayed(WebDriver driver, String pageHeader) {
        waitForElementVisible(driver, GlobalConstants.AccountPageUI.HEADER_TITLE + "[contains(text(),'" + pageHeader + "')]");
        return isElementDisplayed(driver, GlobalConstants.AccountPageUI.HEADER_TITLE + "[contains(text(),'" + pageHeader + "')]");
    }

    // HRM - Menu
    public void openMenuPage(WebDriver driver, String menuPageName) {
        waitForElementClickable(driver, interfaces.pageUIs.orangehrm.BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
        clickToElement(driver, interfaces.pageUIs.orangehrm.BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
    }

    public void openSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName) {
        waitForElementClickable(driver, interfaces.pageUIs.orangehrm.BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
        clickToElement(driver, interfaces.pageUIs.orangehrm.BasePageUI.MENU_BY_PAGE_NAME, menuPageName);

        waitForElementClickable(driver, interfaces.pageUIs.orangehrm.BasePageUI.CHILD_MENU_BY_NAME, subMenuPageName);
        clickToElement(driver, interfaces.pageUIs.orangehrm.BasePageUI.CHILD_MENU_BY_NAME, subMenuPageName);
    }

    public void openChildSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName, String childSubMenuPageName) {
        waitForElementClickable(driver, interfaces.pageUIs.orangehrm.BasePageUI.CHILD_MENU_BY_NAME, menuPageName);
        clickToElement(driver, interfaces.pageUIs.orangehrm.BasePageUI.CHILD_MENU_BY_NAME, menuPageName);

        waitForElementClickable(driver, interfaces.pageUIs.orangehrm.BasePageUI.CHILD_MENU_BY_NAME, subMenuPageName);
        clickToElement(driver, interfaces.pageUIs.orangehrm.BasePageUI.CHILD_MENU_BY_NAME, subMenuPageName);

        waitForElementClickable(driver, interfaces.pageUIs.orangehrm.BasePageUI.CHILD_MENU_BY_NAME, childSubMenuPageName);
        clickToElement(driver, interfaces.pageUIs.orangehrm.BasePageUI.CHILD_MENU_BY_NAME, childSubMenuPageName);
    }

    public void clickToButtonByType(WebDriver driver, String buttonTypeName) {
        waitForElementClickable(driver, interfaces.pageUIs.orangehrm.BasePageUI.BUTTON_BY_TYPE, buttonTypeName);
        clickToElement(driver, interfaces.pageUIs.orangehrm.BasePageUI.BUTTON_BY_TYPE, buttonTypeName);
    }

    public void enterToTextboxByName(WebDriver driver, String value, String textBoxName) {
        waitForElementVisible(driver, interfaces.pageUIs.orangehrm.BasePageUI.TEXTBOX_BY_NAME, textBoxName);
        sendKeysToElement(driver, interfaces.pageUIs.orangehrm.BasePageUI.TEXTBOX_BY_NAME, value, textBoxName);
    }

    /* Only use for Orange HRM website */
    public void waitAllLoadingIconInvisible(WebDriver driver) {
        waitForAllElementInvisible(driver, interfaces.pageUIs.orangehrm.BasePageUI.LOADING_ICON);
    }

    public boolean isSuccessMessageDisplayed(WebDriver driver) {
        waitForElementVisible(driver, EmployeeListPageUI.UPDATE_EMPLOYEE_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, EmployeeListPageUI.UPDATE_EMPLOYEE_SUCCESS_MESSAGE);
    }

    public boolean isAddInfoSuccessMessageDisplayed(WebDriver driver) {
        waitForElementVisible(driver, EmployeeListPageUI.ADD_INFO_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, EmployeeListPageUI.ADD_INFO_SUCCESS_MESSAGE);
    }

    protected BasePage() {
    }
}
