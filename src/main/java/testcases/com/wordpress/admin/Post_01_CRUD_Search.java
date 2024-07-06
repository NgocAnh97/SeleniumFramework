package testcases.com.wordpress.admin;

import actions.commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class Post_01_CRUD_Search extends BaseTest {
    WebDriver driver;

    @Parameters({"browser", "urlAdmin"})
    @BeforeClass
    public void beforeClass(String browserName, String urlAdmin) {
        driver = getBrowserUrl(browserName, urlAdmin);
    }

    @Test
    public void Search_Yoona() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            driver.get("https://google.com");
            driver.findElement(By.name("q")).sendKeys("Im Yoona", Keys.ENTER);

            Thread.sleep(1000);
            driver.switchTo().newWindow(WindowType.TAB);

            driver.get("https://www.youtube.com/");

            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@id='search-input']/input")).click();
            driver.findElement(By.name("search_query")).sendKeys("Yoona", Keys.ENTER);

            Thread.sleep(2000);
        }

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
    }

    //    @Test
    public void Post_01_Create_New_Post() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(1));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.name("na"))).build().perform();
        log.info("Register - Step 01: Navigate to 'Register' page");
    }

    //    @Test
    public void Post_02_Search_Post() {
        log.info("Register - Step 01: Navigate to 'Register' page");
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
