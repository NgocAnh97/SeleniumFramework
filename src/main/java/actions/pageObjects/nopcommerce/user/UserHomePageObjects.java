package actions.pageObjects.nopcommerce.user;

import actions.commons.BasePage;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import actions.pageObjects.nopcommerce.admin.AdminLoginPageObjects;
import interfaces.pageUIs.nopcommerce.HomePageUI;

public class UserHomePageObjects extends BasePage {
    private WebDriver driver;

    public UserHomePageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public UserLoginPageObjects openLoginPage() {
//        waitForElementClickable(driver, GlobalConstants.HomePageUI.LOGIN_LINK);
        waitForElementClickable(driver, GlobalConstants.HomePageUI.LOGIN_LINK);
        clickToElement(driver, GlobalConstants.HomePageUI.LOGIN_LINK);
        return PageGeneratorManager.getUserLoginPage(driver);
    }

    public AdminLoginPageObjects openAdminLoginPage(){
        openPageURL(driver, GlobalConstants.ADMIN_PAGE_URL);
        return new AdminLoginPageObjects(driver);
    }

    public UserRegisterPageObjects openRegisterPage() {
        waitForElementClickable(driver, GlobalConstants.HomePageUI.REGISTER_LINK);
        clickToElement(driver, GlobalConstants.HomePageUI.REGISTER_LINK);
        //2
//        return new UserRegisterPageObjects(driver);
        //3
        return PageGeneratorManager.getUserRegisterPage(driver);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, GlobalConstants.HomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver,GlobalConstants.HomePageUI.MY_ACCOUNT_LINK);
    }
}
