package actions.reportConfig;

import actions.commons.BaseTest;
import actions.helpers.SystemsHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportNGListener implements ITestListener {
    private final String projectPath = SystemsHelper.getCurrentDir() + "test-output/reportNGScreenshot/";
    WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        System.out.printf("---------- %s STARTED ----------%n", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.printf("---------- %s FINISHED ----------%n", context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.printf("---------- %s STARTED test ----------%n", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.printf("---------- %s SUCCESS test ----------%n", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.printf("---------- %s FAILED test ----------%n", result.getName());
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        WebDriver wedDriver = ((BaseTest) result.getInstance()).getWedDriver();
        String screenshotPath = captureScreenshotImage(wedDriver, result.getName());

        Reporter.getCurrentTestResult();
        Reporter.log("<br><a target=\"_blank\" href=\"file:///" + screenshotPath + "\">" + "<img src=\"file:///"
                + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
        Reporter.setCurrentTestResult(null);

//        try {
//        WebDriver wedDriver = ((BaseTest) result.getInstance()).getWedDriver();
//        String screenshotPath = captureScreenshotBase64(wedDriver, result.getName());
//            System.setProperty("org.uncommons.reportng.escape-output", "false");
//            Reporter.getCurrentTestResult();
//            Reporter.log("<br><a target=\"_blank\" href=\"data:image/png;base64," + screenshotPath + "\">" + "<img src=\"data:image/png;base64,"
//                    + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
//            Reporter.setCurrentTestResult(null);
//        } catch (WebDriverException e) {
//            e.printStackTrace();
//        }
    }

    public String captureScreenshotBase64(WebDriver driver, String screenshotName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    public String captureScreenshotImage(WebDriver driver, String screenshotName) {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
            String screenshotPath = projectPath + screenshotName + "_" + formatter.format(calendar.getTime()) + ".png";
            FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File(screenshotPath));
            return screenshotPath;
        } catch (IOException e) {
            System.out.printf("Exception while taking screenshot: %s%n", e.getMessage());
            return e.getMessage();
        }
    }
}
