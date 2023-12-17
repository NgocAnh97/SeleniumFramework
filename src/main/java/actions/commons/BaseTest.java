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

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");

    protected WebDriver getBrowser(String browserName) {
        log.info("Run on " + browserName);
        if (browserName.equals("firefox")) {
//            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equals("h_firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("window-size=1920x1080");
            driver = new FirefoxDriver(firefoxOptions);
        } else if (browserName.equals("chrome")) {
//            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equals("h_chrome")) {
            WebDriverManager.chromedriver().setup();
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
            edgeOptions.setPageLoadStrategy("--headless");
            driver = new EdgeDriver(edgeOptions);
        } else {
            throw new RuntimeException("Browser name is invalid.");
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(GlobalConstants.PORTAL_PAGE_URL);
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String environmentName) {
        log.info("Run on " + browserName);
        if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equals("h_firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("window-size=1920x1080");
            driver = new FirefoxDriver(firefoxOptions);
        } else if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equals("h_chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(chromeOptions);
        } else if (browserName.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.equals("h_edge")) {
            WebDriverManager.edgedriver().operatingSystem(OperatingSystem.MAC).setup();
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.setPageLoadStrategy("--headless");
            driver = new EdgeDriver(edgeOptions);
        } else {
            throw new RuntimeException("Browser name is invalid.");
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(getEnvironmentUrl(environmentName));
        return driver;
    }

    private String getEnvironmentUrl(String environmentName) {
        String url = null;
        switch (environmentName) {
            case "staging":
                url = GlobalConstants.PORTAL_PAGE_URL;
                break;
            case "dev":
                url = GlobalConstants.PORTAL_DEV_URL;
                break;
            default:
                break;
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
            log.info("---------------------- Passed -----------------------");
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("---------------------- Failed -----------------------");
        }
        return status;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean status = true;
        try {
            Assert.assertFalse(condition);
            log.info("---------------------- Passed -----------------------");
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("---------------------- Failed -----------------------");
        }
        return status;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean status = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("---------------------- Passed -----------------------");
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("---------------------- Failed -----------------------");
        }
        return status;
    }
}
