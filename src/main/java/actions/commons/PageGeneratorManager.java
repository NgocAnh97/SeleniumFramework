package actions.commons;

import actions.pageObjects.nopcommerce.admin.AdminDashboardPageObjects;
import actions.pageObjects.nopcommerce.admin.AdminLoginPageObjects;
import actions.pageObjects.nopcommerce.user.*;
import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    public static UserHomePageObjects getUserHomePage(WebDriver driver) {
        return new UserHomePageObjects(driver);
    }

    public static UserRegisterPageObjects getUserRegisterPage(WebDriver driver) {
        return new UserRegisterPageObjects(driver);
    }

    public static UserLoginPageObjects getUserLoginPage(WebDriver driver) {
        return new UserLoginPageObjects(driver);
    }

    public static UserAccountPageObjects getUserMyAccountPage(WebDriver driver) {
        return new UserAccountPageObjects(driver);
    }

    public static UserAddressPageObjects getUserAddressPage(WebDriver driver) {
        return new UserAddressPageObjects(driver);
    }

    public static UserMyProductReviewObjects getUserMyProductReviewPage(WebDriver driver) {
        return new UserMyProductReviewObjects(driver);
    }

    public static UserRewardPointObjects getUserRewardPointPage(WebDriver driver) {
        return new UserRewardPointObjects(driver);
    }

    public static AdminLoginPageObjects getAdminLoginPage(WebDriver driver) {
        return new AdminLoginPageObjects(driver);
    }

    public static AdminDashboardPageObjects getAdminDashboardPage(WebDriver driver) {
        return new AdminDashboardPageObjects(driver);
    }
}
