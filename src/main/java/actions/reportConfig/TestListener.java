package actions.reportConfig;

import actions.utilities.LogUtils;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import reports.ExtentReportManager;
import reports.ExtentTestManager;

public class TestListener implements ITestListener {
    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
//        PropertiesHelper.loadAllFiles();
        //Khởi tạo report (Extent và Allure)
        System.out.println("---------- " + result.getName() + " STARTED test ----------");
    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("End testing " + result.getName());

        //Kết thúc và thực thi Extents Report
        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("Running test case " + result.getName());

        //Bắt đầu ghi 1 TCs mới vào Extent Report
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("Test case " + result.getName() + " is passed.");

        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");
    }

//    @Override
//    public void onTestFailure(ITestResult result) {
//        LogUtils.error("Test case " + result.getName() + " is failed.");
//        //Screenshot khi fail
//        captureScreenshot_Base64(driver, result.getName());
//        LogUtils.error(result.getThrowable().toString());
//
//        //Extent Report
//        ExtentTestManager.addScreenShot(result.getName());
//        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
//        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");
//    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Reporter.log(("Test case " + result.getName() + " is skipped."));
        LogUtils.error(result.getThrowable().toString());

        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LogUtils.error("Đây là test case bị Fail nhưng có phần Success: " + result.getName());
        LogUtils.error(result.getThrowable().toString());
    }

    public String captureScreenshot_Base64(WebDriver driver, String screenshotName) {
        String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        return screenshotBase64;
    }
}
