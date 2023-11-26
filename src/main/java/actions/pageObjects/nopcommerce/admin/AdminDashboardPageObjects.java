package actions.pageObjects.nopcommerce.admin;

import actions.commons.BasePage;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;

public class AdminDashboardPageObjects extends BasePage {
	private WebDriver driver;

	public AdminDashboardPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, GlobalConstants.HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, GlobalConstants.HomePageUI.MY_ACCOUNT_LINK);
	}

	public boolean isDashboardLinkDisplayed() {
		waitForElementVisible(driver, GlobalConstants.DASHBOARD_LINK);
		return isElementDisplayed(driver, GlobalConstants.DASHBOARD_LINK);
	}

	public void clickToLogoutLink(WebDriver driver) {
		waitForElementClickable(driver, GlobalConstants.LOGOUT_LINK);
		clickToElement(driver, GlobalConstants.LOGOUT_LINK);
	}
}
