package testcases.com.nopcommerce.user;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_01_Register extends BasePage {
    WebDriver driver;
    String emailAddress, password;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        emailAddress = "test" + generateFakeNumber() + "@mail.com";
        password = "123456aA@@";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void TC_Register_Valid_Data() {
        waitForElementClickable(driver, "class=ico-register");
        clickToElement(driver, "class=ico-register");

        sendKeysToElement(driver, "id=FirstName", "testFirstName");
        sendKeysToElement(driver, "id=LastName", "testLastName");
        sendKeysToElement(driver, "id=Email", emailAddress);
        sendKeysToElement(driver, "id=Password", password);
        sendKeysToElement(driver, "id=ConfirmPassword", password);

        waitForElementClickable(driver, "id=register-button");
        clickToElement(driver, "id=register-button");

        Assert.assertEquals(getElementText(driver, "class=result"),
                "Your registration completed");

        waitForElementClickable(driver, "class=ico-logout");
        clickToElement(driver, "class=ico-logout");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int generateFakeNumber() {
        Random random = new Random();
        return random.nextInt(500);
    }
}
