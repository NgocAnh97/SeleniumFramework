package actions.pageObjects.wordpress;

import actions.commons.BasePage;
import interfaces.pageUIs.wordpress.AdminAddNewPostPageUI;
import interfaces.pageUIs.wordpress.AdminPostDetailPageUI;
import org.openqa.selenium.WebDriver;

public class AdminPostDetailPO extends BasePage {
    WebDriver driver;

    public AdminPostDetailPO(WebDriver driver){
        this.driver = driver;
    }

    public void verifyPostDetail(String postTitleValue, String postBodyValue) {
        switchToFrameIframe(driver, AdminAddNewPostPageUI.LOADED_IFRAME);
        switchToFrameIframe(driver, AdminAddNewPostPageUI.EDITOR_IFRAME);
        waitForElementVisible(driver, AdminPostDetailPageUI.POST_TITLE, postTitleValue);
        waitForElementVisible(driver, AdminPostDetailPageUI.POST_BODY, postBodyValue);
    }
}
