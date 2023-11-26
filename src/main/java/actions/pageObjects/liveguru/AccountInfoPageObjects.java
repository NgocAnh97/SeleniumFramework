package actions.pageObjects.liveguru;

import actions.commons.BasePage;
import actions.commons.LiveGuruConstants;
import org.openqa.selenium.WebDriver;

public class AccountInfoPageObjects extends BasePage {

    private WebDriver driver;

    public AccountInfoPageObjects(WebDriver driver){
        this.driver = driver;
    }

    public String getTitleAccountPage() {
        return getTitle(driver);
    }

    public void clickToAccountLink() {
        waitForElementClickable(driver, LiveGuruConstants.AccountPageUI.ACCOUNT_LINK);
        clickToElement(driver, LiveGuruConstants.AccountPageUI.ACCOUNT_LINK);
    }

    public HomePageObjects clickToLogoutLink() {
        waitForElementClickable(driver, LiveGuruConstants.AccountPageUI.LOGOUT_LINK);
        clickToElement(driver, LiveGuruConstants.AccountPageUI.LOGOUT_LINK);
        return PageGeneratorManager.getHomePage(driver);
    }
}
