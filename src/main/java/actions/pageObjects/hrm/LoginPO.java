package actions.pageObjects.hrm;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPO extends BasePage {
    private WebDriver driver;

    public LoginPO(WebDriver driver) {
        this.driver = driver;
    }
}
