package actions.commons;

import actions.pageObjects.nopcommerce.user.UserAddressPageObjects;
import actions.pageObjects.nopcommerce.user.UserMyProductReviewObjects;
import actions.pageObjects.nopcommerce.user.UserRewardPointObjects;
import interfaces.pageUIs.nopcommerce.BasePageUI;
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
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.of(longTimeout, ChronoUnit.SECONDS));
        return explicitWait.until(ExpectedConditions.alertIsPresent());
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

    private By getByXpath(String xpathLocator) {
        return By.xpath(xpathLocator);
    }

    private By getByXpath(String xpathLocator, String... params) {
        return By.xpath(String.format(xpathLocator, (Object[]) params));
    }

    private WebElement getWebElement(WebDriver driver, String xpathLocator) {
        return driver.findElement(getByLocator(xpathLocator));
    }

    private List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
        return driver.findElements(getByLocator(xpathLocator));
    }

    public By getByLocator(String prefixLocator) {
        By by = null;
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
        By by = null;
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

    public void clickToElement(WebDriver driver, String xpathLocator) {
        WebElement element = getWebElement(driver, xpathLocator);
        element.click();
    }

    public void clickToElement(WebDriver driver, String xpathLocator, String... params) {
        WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, params));
        element.click();
    }

    public void sendKeysToElement(WebDriver driver, String xpathLocator, String textValue) {
        WebElement element = getWebElement(driver, xpathLocator);
        element.clear();
        element.sendKeys(textValue);
    }

    public void sendKeysToElement(WebDriver driver, String xpathLocator, String textValue, String... params) {
        WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, params));
        element.clear();
        element.sendKeys(textValue);
    }

    public void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key) {
        new Actions(driver).sendKeys(getWebElement(driver, xpathLocator), key).perform();
    }

    public void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key, String... params) {
        new Actions(driver).sendKeys(getWebElement(driver, getDynamicLocator(xpathLocator, params)), key).perform();
    }

    public void selectItemInDropdown(WebDriver driver, String xpathLocator, String textItem) {
        Select select = new Select(getWebElement(driver, xpathLocator));
        select.selectByValue(textItem);
    }

    public void selectItemInDropdown(WebDriver driver, String xpathLocator, String textItem, String... params) {
        Select select = new Select(getWebElement(driver, getDynamicLocator(xpathLocator, params)));
        select.selectByValue(textItem);
    }

    public String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
        Select select = new Select(getWebElement(driver, xpathLocator));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
        Select select = new Select(getWebElement(driver, xpathLocator));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectItemText) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.of(longTimeout, ChronoUnit.SECONDS));
        getWebElement(driver, parentXpath).click();
        sleepInSeconds(1);

        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectItemText)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSeconds(1);
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

    public String getElementAttributeValue(WebDriver driver, String xpathLocator, String attributeName) {
        return getWebElement(driver, xpathLocator).getAttribute(attributeName);
    }

    public String getElementAttributeValue(WebDriver driver, String xpathLocator, String attributeName, String... params) {
        return getWebElement(driver, getDynamicLocator(xpathLocator, params)).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver, String xpathLocator) {
        return getWebElement(driver, xpathLocator).getText();
    }

    public String getValue(WebDriver driver, String xpathLocator) {
        return getWebElement(driver, xpathLocator).getAttribute("value");
    }

    public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyValue) {
        return getWebElement(driver, xpathLocator).getCssValue(propertyValue);
    }

    public String getHexColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    public int getElementSize(WebDriver driver, String xpathLocator) {
        return getListWebElement(driver, xpathLocator).size();
    }

    public int getElementSize(WebDriver driver, String xpathLocator, String... params) {
        return getListWebElement(driver, getDynamicLocator(xpathLocator, params)).size();
    }

    public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
        WebElement element = getWebElement(driver, xpathLocator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void unCheckToDefaultCheckbox(WebDriver driver, String xpathLocator) {
        WebElement element = getWebElement(driver, xpathLocator);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void checkToCheckboxByStatus(WebDriver driver, String xpathLocator, Boolean status) throws Exception {
        WebElement element = getWebElement(driver, xpathLocator);

        if (status == true) {
            if (!element.isSelected()) {
                element.click();
            }
        } else if (status == false) {
            if (element.isSelected()) {
                element.click();
            }
        } else {
            throw new Exception("Invalid boolean status: " + status);
        }
    }

    public void checkToCheckboxByStatus(WebDriver driver, String xpathLocator, Boolean status, String... params) throws Exception {
        WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, params));

        if (status == true) {
            if (!element.isSelected()) {
                element.click();
            }
        } else if (status == false) {
            if (element.isSelected()) {
                element.click();
            }
        } else {
            throw new Exception("Invalid boolean status: " + status);
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
        return getWebElement(driver, xpathLocator).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String xpathLocator, String... params) {
        return getWebElement(driver, getDynamicLocator(xpathLocator, params)).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String xpathLocator) {
        return getWebElement(driver, xpathLocator).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String xpathLocator) {
        return getWebElement(driver, xpathLocator).isEnabled();
    }

    public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
        driver.switchTo().frame(getWebElement(driver, xpathLocator));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void doubleClickElement(WebDriver driver, String xpathLocator) {
        Actions action = new Actions(driver);
        action.doubleClick(getWebElement(driver, xpathLocator)).perform();
    }

    public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, xpathLocator)).perform();
    }

    /*
     * public void rightClick(WebDriver driver, String xpathLocator) { Actions
     * action = new Actions(driver); action.contextClick(getWebElement(driver,
     * xpathLocator)).perform(); }
     *
     * public void dragAndDrop(WebDriver driver, String xpathLocator, String
     * targetXpathLocator) { Actions action = new Actions(driver);
     * action.dragAndDrop(getWebElement(driver, xpathLocator), getWebElement(driver,
     * targetXpathLocator)).perform(); }
     *
     * public void senKeyboardToElement(WebDriver driver, String xpathLocator,
     * String keyValueAction){ Actions action = new Actions(driver);
     * action.sendKeys(getWebElement(driver, xpathLocator), keyValueAction); }
     */

    public void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void highlightElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    public void scrollToElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.of(longTimeout, ChronoUnit.SECONDS));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public void waitForElementVisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.of(longTimeout, ChronoUnit.SECONDS));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(xpathLocator)));
    }

    public void waitForElementVisible(WebDriver driver, String xpathLocator, String... params) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.of(longTimeout, ChronoUnit.SECONDS));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(xpathLocator, params)));
    }

    public void waitForAllElementVisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.of(longTimeout, ChronoUnit.SECONDS));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(xpathLocator)));
    }

    public void waitForElementInvisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.of(longTimeout, ChronoUnit.SECONDS));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(xpathLocator)));
    }

    public void waitForElementInvisible(WebDriver driver, String xpathLocator, String... params) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.of(longTimeout, ChronoUnit.SECONDS));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(xpathLocator, params)));
    }

    public void waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.of(longTimeout, ChronoUnit.SECONDS));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
    }

    public void waitForElementClickable(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.of(longTimeout, ChronoUnit.SECONDS));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getWebElement(driver, xpathLocator)));
    }

    public void waitForElementClickable(WebDriver driver, String xpathLocator, String... params) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.of(longTimeout, ChronoUnit.SECONDS));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getWebElement(driver, getDynamicLocator(xpathLocator, params))));
    }

    private String getDynamicLocator(String xpathLocator, String... params) {
        return String.format(xpathLocator, (Object[]) params);
    }

    public List<WebElement> getListElement(WebDriver driver, String xpathLocator) {
        List<WebElement> elements = driver.findElements(getByLocator(xpathLocator));
        if (elements.isEmpty()) {
            throw new NoSuchElementException("No element found by locator: " + xpathLocator);
        }
        return elements;
    }

    public List<WebElement> getListElement(WebDriver driver, String xpathLocator, String... params) {
        List<WebElement> elements = driver.findElements(getByLocator(xpathLocator));
        if (elements.isEmpty()) {
            throw new NoSuchElementException("No element found by locator: " + xpathLocator);
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
        waitForElementClickable(driver, interfaces.pageUIs.hrm.BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
        clickToElement(driver, interfaces.pageUIs.hrm.BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
    }

    public void openSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName) {
        waitForElementClickable(driver, interfaces.pageUIs.hrm.BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
        clickToElement(driver, interfaces.pageUIs.hrm.BasePageUI.MENU_BY_PAGE_NAME, menuPageName);

        waitForElementClickable(driver, interfaces.pageUIs.hrm.BasePageUI.CHILD_MENU_BY_NAME, subMenuPageName);
        clickToElement(driver, interfaces.pageUIs.hrm.BasePageUI.CHILD_MENU_BY_NAME, subMenuPageName);
    }

    public void openChildSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName, String childSubMenuPageName) {
        waitForElementClickable(driver, interfaces.pageUIs.hrm.BasePageUI.CHILD_MENU_BY_NAME, menuPageName);
        clickToElement(driver, interfaces.pageUIs.hrm.BasePageUI.CHILD_MENU_BY_NAME, menuPageName);

        waitForElementClickable(driver, interfaces.pageUIs.hrm.BasePageUI.CHILD_MENU_BY_NAME, subMenuPageName);
        clickToElement(driver, interfaces.pageUIs.hrm.BasePageUI.CHILD_MENU_BY_NAME, subMenuPageName);

        waitForElementClickable(driver, interfaces.pageUIs.hrm.BasePageUI.CHILD_MENU_BY_NAME, childSubMenuPageName);
        clickToElement(driver, interfaces.pageUIs.hrm.BasePageUI.CHILD_MENU_BY_NAME, childSubMenuPageName);
    }

    public void clickToButtonByType(WebDriver driver, String buttonTypeName) {
        waitForElementClickable(driver, interfaces.pageUIs.hrm.BasePageUI.BUTTON_BY_TYPE, buttonTypeName);
        clickToElement(driver, interfaces.pageUIs.hrm.BasePageUI.BUTTON_BY_TYPE, buttonTypeName);
    }

    public void enterToTextboxByName(WebDriver driver, String value, String textBoxName) {
        waitForElementVisible(driver, interfaces.pageUIs.hrm.BasePageUI.TEXTBOX_BY_NAME, textBoxName);
        sendKeysToElement(driver, interfaces.pageUIs.hrm.BasePageUI.TEXTBOX_BY_NAME, value, textBoxName);
    }

    public String getTextboxValueByID(WebDriver driver, String value, String textBoxName) {
        waitForElementVisible(driver, interfaces.pageUIs.hrm.BasePageUI.TEXTBOX_BY_NAME, textBoxName);
        return getElementAttributeValue(driver, interfaces.pageUIs.hrm.BasePageUI.TEXTBOX_BY_NAME, "value", textBoxName);
    }

    protected BasePage() {
    }

    private long longTimeout = 30;
}
