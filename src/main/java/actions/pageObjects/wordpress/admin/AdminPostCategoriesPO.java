package actions.pageObjects.wordpress.admin;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;

public class AdminPostCategoriesPO extends BasePage {
    WebDriver driver;

    public AdminPostCategoriesPO (WebDriver driver){
        this.driver = driver;
    }
}
