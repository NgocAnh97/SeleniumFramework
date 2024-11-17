package actions.pageObjects.wordpress;

import actions.commons.BasePage;
import interfaces.pageUIs.wordpress.AdminDashboardPageUI;
import org.openqa.selenium.WebDriver;

public class AdminDashboardPO extends BasePage {
    WebDriver driver;

    public AdminDashboardPO(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToHomeLink() {
        waitForElementClickable(driver, AdminDashboardPageUI.HOME_MENU_LINK);
        clickToElement(driver, AdminDashboardPageUI.HOME_MENU_LINK);
    }

    public AdminPostSearchPO clickToPostsLink() {
        waitForElementClickable(driver, AdminDashboardPageUI.POSTS_MENU_LINK);
        clickToElement(driver, AdminDashboardPageUI.POSTS_MENU_LINK);
        return PageGeneratorManager.getAdminPostSearchPage(driver);
    }
}
