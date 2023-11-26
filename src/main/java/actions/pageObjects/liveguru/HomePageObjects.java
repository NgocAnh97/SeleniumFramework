package actions.pageObjects.liveguru;

import actions.commons.BasePage;
import actions.commons.LiveGuruConstants;
import org.openqa.selenium.WebDriver;

public class HomePageObjects extends BasePage {
    private WebDriver driver;

    public HomePageObjects(WebDriver driver){
        this.driver = driver;
    }

    public HomePageObjects clickToMyAccountLink() {
        waitForElementClickable(driver, LiveGuruConstants.HomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, LiveGuruConstants.HomePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getHomePage(driver);
    }

    public RegisterPageObjects clickToRegisterLink() {
        waitForElementClickable(driver, LiveGuruConstants.HomePageUI.REGISTER_LINK);
        clickToElement(driver, LiveGuruConstants.HomePageUI.REGISTER_LINK);
        return PageGeneratorManager.getRegisterPage(driver);
    }

    public LoginPageObjects clickToLoginLink() {
        waitForElementClickable(driver, LiveGuruConstants.HomePageUI.LOGIN_LINK);
        clickToElement(driver, LiveGuruConstants.HomePageUI.LOGIN_LINK);
        return PageGeneratorManager.getLoginPage(driver);
    }

    public String getTitleHomePage() {
        return getTitle(driver);
    }
}
