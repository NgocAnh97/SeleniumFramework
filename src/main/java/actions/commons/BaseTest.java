package actions.commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;

import java.time.Duration;

public class BaseTest {
    private WebDriver driver;

    protected WebDriver getBrowser(String browserName) {
        log.info("Open browser {}", browserName);
        switch (browserName.toLowerCase()) {
            case "firefox" -> driver = new FirefoxDriver();
            case "h_firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("window-size=1920x1080");
                driver = new FirefoxDriver(firefoxOptions);
            }
            case "chrome" -> driver = new ChromeDriver();
            case "h_chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("window-size=1920x1080");
                driver = new ChromeDriver(options);
            }
            case "edge" -> {
                WebDriverManager.edgedriver();
                driver = new EdgeDriver();
            }
            case "h_edge" -> {
                WebDriverManager.edgedriver().operatingSystem(OperatingSystem.MAC).setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
            }
            default -> throw new RuntimeException("Browser name is invalid.");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.get(GlobalConstants.PAGE_URL);
        return driver;
    }

    protected WebDriver getBrowserUrl(String browserName, String appUrl) {
        log.info("Pre-condition: Open browser '{}' and navigate to url '{}'", browserName, appUrl);
        switch (browserName.toUpperCase()) {
            case "FIREFOX" -> driver = new FirefoxDriver();
            case "H_FIREFOX" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("window-size=1920x1080");
                driver = new FirefoxDriver(firefoxOptions);
            }
            case "CHROME" -> driver = new ChromeDriver();
            case "H_CHROME" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("window-size=1920x1080");
                driver = new ChromeDriver(options);
            }
            case "EDGE" -> driver = new EdgeDriver();
            case "H_EDGE" -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
            }
            default -> throw new RuntimeException("Browser name is invalid.");
        }

        driver.manage().window().maximize();
        driver.get(appUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String environmentName) {
        log.info("Pre-condition: Open browser '{}' and environment '{}'", browserName, environmentName);
        switch (browserName.toUpperCase()) {
            case "FIREFOX" -> driver = new FirefoxDriver();
            case "H_FIREFOX" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("window-size=1920x1080");
                driver = new FirefoxDriver(firefoxOptions);
            }
            case "CHROME" -> driver = new ChromeDriver();
            case "H_CHROME" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("window-size=1920x1080");
                driver = new ChromeDriver(chromeOptions);
            }
            case "EDGE" -> driver = new EdgeDriver();
            case "H_EDGE" -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
            }
            default -> throw new RuntimeException("Browser name is invalid.");
        }

        driver.manage().window().maximize();
        driver.get(getEnvironmentUrl(environmentName));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    public WebDriver getWedDriver() {
        return this.driver;
    }

    private String getEnvironmentUrl(String environmentName) {
        return switch (environmentName.toUpperCase()) {
            case "PRODUCTION" -> GlobalConstants.PAGE_URL;
            case "STAGING" -> GlobalConstants.STAGING_PAGE_URL;
            case "DEV" -> GlobalConstants.DEV_URL;
            default -> throw new RuntimeException("Please input the correct environment name");
        };
    }

    protected final Logger log;

    protected BaseTest() {
        log = LogManager.getLogger(getClass());
    }

    protected boolean verifyTrue(boolean condition) {
        boolean status = true;
        try {
            Assert.assertTrue(condition);
            log.info("---------------------- PASSED -----------------------");
        } catch (Throwable e) {
            status = false;
            log.info("---------------------- FAILED -----------------------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean status = true;
        try {
            Assert.assertFalse(condition);
            log.info("---------------------- PASSED -----------------------");
        } catch (Throwable e) {
            status = false;
            log.info("---------------------- FAILED -----------------------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean status = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("---------------------- PASSED -----------------------");
        } catch (Throwable e) {
            status = false;
            log.info("---------------------- FAILED -----------------------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }
}
