package actions.pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

    public static HomePageObjects getHomePage(WebDriver driver) {
        return new HomePageObjects(driver);
    }
    public static RegisterPageObjects getRegisterPage(WebDriver driver) {
        return new RegisterPageObjects(driver);
    }

    public static LoginPageObjects getLoginPage(WebDriver driver) {
        return new LoginPageObjects(driver);
    }

    public static AccountInfoPageObjects getAccountPage(WebDriver driver) {
        return new AccountInfoPageObjects(driver);
    }
}
