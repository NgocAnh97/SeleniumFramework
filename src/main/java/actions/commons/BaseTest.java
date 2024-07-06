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
    private String projectPath = System.getProperty("user.dir");

    protected WebDriver getBrowser(String browserName) {
        log.info("Run on " + browserName);
        if (browserName.equals("firefox")) {
//            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(GlobalConstants.PORTAL_PAGE_URL);
        return driver;
    }

    protected WebDriver getBrowserUrl(String browserName, String url) {
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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(url);
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String environmentName) {
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
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equals("h_chrome")) {
            WebDriverManager.chromedriver().clearDriverCache().setup();
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
//            edgeOptions.setPageLoadStrategy("--headless");
            driver = new EdgeDriver(edgeOptions);
        } else {
            throw new RuntimeException("Browser name is invalid.");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(getEnvironmentUrl(environmentName));
        return driver;
    }

//    protected WebDriver getBrowserDriver2(String browserName){
//        BrowserName browser = BrowserName.valueOf(browserName.toUpperCase());
//        switch (browser){
//            case FIREFOX:
//                driver = WebDriverManager.firefoxdriver().create();
//                break;
//            default:
//                throw new RuntimeException("Please enter the correct Browser name!");
//        }
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//        return driver;
//    }

    public WebDriver getWedDriver() {
        return this.driver;
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
