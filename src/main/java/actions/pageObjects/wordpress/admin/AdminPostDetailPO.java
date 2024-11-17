package actions.pageObjects.wordpress.admin;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;

public class AdminPostDetailPO extends BasePage {
    WebDriver driver;

    public AdminPostDetailPO(WebDriver driver){
        this.driver = driver;
    }
}
