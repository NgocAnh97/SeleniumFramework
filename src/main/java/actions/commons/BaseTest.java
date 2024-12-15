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
        log.info("Run on " + browserName);
        if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().clearDriverCache().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equals("h_firefox")) {
            WebDriverManager.firefoxdriver().clearDriverCache().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("window-size=1920x1080");
            driver = new FirefoxDriver(firefoxOptions);
        } else if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().clearDriverCache().setup();
            driver = new ChromeDriver();
        } else if (browserName.equals("h_chrome")) {
            WebDriverManager.chromedriver().clearDriverCache().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);
        } else if (browserName.equals("edge")) {
//            System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/edgedriver_mac64/msedgedriver");
            WebDriverManager.edgedriver();
            driver = new EdgeDriver();
        } else if (browserName.equals("h_edge")) {
            WebDriverManager.edgedriver().operatingSystem(OperatingSystem.MAC).setup();
            EdgeOptions edgeOptions = new EdgeOptions();
//            edgeOptions.setPageLoadStrategy("--headless");
            driver = new EdgeDriver(edgeOptions);
        } else {
            throw new RuntimeException("Browser name is invalid.");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.get(GlobalConstants.PAGE_URL);
        return driver;
    }

    protected WebDriver getBrowserUrl(String browserName, String appUrl) {
        if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().clearDriverCache().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equals("h_firefox")) {
            WebDriverManager.firefoxdriver().clearDriverCache().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("window-size=1920x1080");
            driver = new FirefoxDriver(firefoxOptions);
        } else if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equals("h_chrome")) {
            WebDriverManager.chromedriver().clearDriverCache().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);
        } else if (browserName.equals("edge")) {
            WebDriverManager.edgedriver();
            driver = new EdgeDriver();
        } else if (browserName.equals("h_edge")) {
            WebDriverManager.edgedriver().operatingSystem(OperatingSystem.MAC).setup();
            EdgeOptions edgeOptions = new EdgeOptions();
//            edgeOptions.setPageLoadStrategy("--headless");
            driver = new EdgeDriver(edgeOptions);
        } else {
            throw new RuntimeException("Browser name is invalid.");
        }

        driver.manage().window().maximize();
        driver.get(appUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String environmentName) {
        log.info("Run on " + browserName);
        switch (browserName.toUpperCase()) {
            case "FIREFOX":
                WebDriverManager.firefoxdriver().clearDriverCache().setup();
                driver = new FirefoxDriver();
                break;
            case "H_FIREFOX":
                WebDriverManager.firefoxdriver().clearDriverCache().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("window-size=1920x1080");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "H_CHROME":
                WebDriverManager.chromedriver().clearDriverCache().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("window-size=1920x1080");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "H_EDGE":
                WebDriverManager.edgedriver().operatingSystem(OperatingSystem.MAC).setup();
                EdgeOptions edgeOptions = new EdgeOptions();
//            edgeOptions.setPageLoadStrategy("--headless");
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new RuntimeException("Browser name is invalid.");
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
        String url = null;
        switch (environmentName.toUpperCase()) {
            case "STAGING":
                url = GlobalConstants.PAGE_URL;
                break;
            case "DEV":
                url = GlobalConstants.DEV_URL;
                break;
            default:
                throw new RuntimeException("Please input the correct environment name");
        }
        return url;
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
