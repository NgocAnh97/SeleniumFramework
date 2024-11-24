package actions.pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    public static AdminLoginPO getAdminLoginPage(WebDriver driver) {
        return new AdminLoginPO(driver);
    }

    public static AdminPostAddNewPO getAdminPostAddNewPage(WebDriver driver) {
        return new AdminPostAddNewPO(driver);
    }

    public static AdminPostSearchPO getAdminPostSearchPage(WebDriver driver) {
        return new AdminPostSearchPO(driver);
    }

    public static AdminDashboardPO getAdminDashboardPage(WebDriver driver) {
        return new AdminDashboardPO(driver);
    }

    public static AdminPostDetailPO getAdminPostDetailPage(WebDriver driver) {
        return new AdminPostDetailPO(driver);
    }
}
