package actions.pageObjects.nopcommerce.user;

import actions.commons.BasePage;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.nopcommerce.admin.AdminLoginPageObjects;
import org.openqa.selenium.WebDriver;

public class UserHomePageObjects extends BasePage {
    private WebDriver driver;

    public UserHomePageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public UserLoginPageObjects openLoginPage() {
        waitForElementClickable(driver, GlobalConstants.HomePageUI.LOGIN_LINK);
        clickToElement(driver, GlobalConstants.HomePageUI.LOGIN_LINK);
        return PageGeneratorManager.getUserLoginPage(driver);
    }

    public AdminLoginPageObjects openAdminLoginPage() {
        openPageURL(driver, GlobalConstants.ADMIN_PAGE_URL);
        return new AdminLoginPageObjects(driver);
    }

    public UserRegisterPageObjects openRegisterPage() {
        waitForElementClickable(driver, GlobalConstants.HomePageUI.REGISTER_LINK);
        clickToElement(driver, GlobalConstants.HomePageUI.REGISTER_LINK);
        return PageGeneratorManager.getUserRegisterPage(driver);
    }

    public UserAccountPageObjects openAccountPage() {
        waitForElementClickable(driver, GlobalConstants.HomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, GlobalConstants.HomePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getUserMyAccountPage(driver);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, GlobalConstants.HomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, GlobalConstants.HomePageUI.MY_ACCOUNT_LINK);
    }
}
