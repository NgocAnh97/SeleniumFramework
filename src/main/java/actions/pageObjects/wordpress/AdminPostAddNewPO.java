package actions.pageObjects.wordpress;

import actions.commons.BasePage;
import interfaces.pageUIs.wordpress.AdminAddNewPostPageUI;
import org.openqa.selenium.WebDriver;

public class AdminPostAddNewPO extends BasePage {
    WebDriver driver;

    public AdminPostAddNewPO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToPostTitle(String postTitleValue) {
        switchToFrameIframe(driver, AdminAddNewPostPageUI.LOADED_IFRAME);
        switchToFrameIframe(driver, AdminAddNewPostPageUI.EDITOR_IFRAME);
        waitForElementVisible(driver, AdminAddNewPostPageUI.POST_TITLE_TEXTBOX);
        sendKeysToElement(driver, AdminAddNewPostPageUI.POST_TITLE_TEXTBOX, postTitleValue);
    }

    public void enterToPostBody(String postBodyValue) {
        waitForElementClickable(driver, AdminAddNewPostPageUI.POST_BODY_BUTTON);
        clickToElement(driver, AdminAddNewPostPageUI.POST_BODY_BUTTON);
        sleepInSeconds(1);
        waitForElementVisible(driver, AdminAddNewPostPageUI.POST_BODY_TEXTBOX);
        sendKeysToElement(driver, AdminAddNewPostPageUI.POST_BODY_TEXTBOX, postBodyValue);
    }

    public void clickToPublishButton() {
        switchToDefaultContent(driver);
        switchToFrameIframe(driver, AdminAddNewPostPageUI.LOADED_IFRAME);
        waitForElementClickable(driver, AdminAddNewPostPageUI.PUBLISH_BUTTON);
        clickToElement(driver, AdminAddNewPostPageUI.PUBLISH_BUTTON);
    }

    public void verifyPostPublishedPopup() {
        waitForElementVisible(driver, AdminAddNewPostPageUI.POST_PUBLISH_POPUP);
    }

    public boolean isPostPublishMessageDisplayed(String publishedMessage) {
        return isElementDisplayed(driver, AdminAddNewPostPageUI.POST_PUBLISH_MESSAGE, publishedMessage);
    }

    public void viewPublishPost() {
        waitForElementClickable(driver, AdminAddNewPostPageUI.VIEW_POST_BUTTON);
        clickToElement(driver, AdminAddNewPostPageUI.VIEW_POST_BUTTON);
    }

    public AdminPostSearchPO clickToHomeLink() {
        waitForElementClickable(driver, AdminAddNewPostPageUI.HOME_ICON);
        clickToElement(driver, AdminAddNewPostPageUI.HOME_ICON);
        return PageGeneratorManager.getAdminPostSearchPage(driver);
    }
}
