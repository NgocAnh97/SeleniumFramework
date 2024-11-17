package actions.pageObjects.wordpress.admin;

import actions.commons.BasePage;
import interfaces.pageUIs.wordpress.admin.AdminPostSearchPageUI;
import org.openqa.selenium.WebDriver;

public class AdminPostSearchPO extends BasePage {
    WebDriver driver;

    public AdminPostSearchPO(WebDriver driver) {
        this.driver = driver;
    }

    public AdminPostAddNewPO clickToAddNewPostButton() {
        waitForElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_POST_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_POST_BUTTON);
        return PageGeneratorManager.getAdminPostAddNewPage(driver);
    }

    public void clickToSearchButton() {
        switchToDefaultContent(driver);
        waitForElementClickable(driver, AdminPostSearchPageUI.SEARCH_POST_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.SEARCH_POST_BUTTON);
    }

    public void enterToSearchTextbox(String postTitle) {
        waitForElementVisible(driver, AdminPostSearchPageUI.SEARCH_POST_TEXTBOX);
        sendKeysToElement(driver, AdminPostSearchPageUI.SEARCH_POST_TEXTBOX, postTitle);
    }

    public void verifySearchResult(String postTitle) {
        waitForElementVisible(driver, AdminPostSearchPageUI.SEARCH_ITEM_TITLE, postTitle);
    }

    public AdminPostDetailPO clickToPostTitle(String postTitle) {
        waitForElementClickable(driver, AdminPostSearchPageUI.SEARCH_ITEM_TITLE,postTitle);
        clickToElement(driver, AdminPostSearchPageUI.SEARCH_ITEM_TITLE, postTitle);
        return PageGeneratorManager.getAdminPostDetailPage(driver);
    }
}
