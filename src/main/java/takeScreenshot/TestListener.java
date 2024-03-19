//package takeScreenshot;
//
//import com.aventstack.extentreports.Status;
//import org.testng.ITestContext;
import org.testng.ITestListener;
//import org.testng.ITestResult;
//import org.testng.Reporter;
//import reports.ExtentReportManager;
//import reports.ExtentTestManager;
//
//public class TestListener implements ITestListener {
////    private String projectPath = System.getProperty("user.dir") + "/screenshotImg/";
////    WebDriver driver;
////
////    @Override
////    public void onTestFailure(ITestResult result) {
////        Object testClass = result.getInstance();
////
////        driver = ((TakeScreenshotTestFailed) testClass).getWedDriver();
////        TakesScreenshot scrShot = (TakesScreenshot) driver;
////        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
////        File DestFile = new File(projectPath + result.getName() + ".png");
////        try {
////            FileUtils.copyFile(SrcFile, DestFile);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
//
//    public String getTestName(ITestResult result) {
//        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
//    }
//
//    public String getTestDescription(ITestResult result) {
//        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
//    }
//
//    @Override
//    public void onStart(ITestContext result) {
//        PropertiesHelper.loadAllFiles();
//        //Khởi tạo report (Extent và Allure)
//    }
//
//    @Override
//    public void onFinish(ITestContext result) {
//        Reporter.log("End testing " + result.getName());
//
//        //Kết thúc và thực thi Extents Report
//        ExtentReportManager.getExtentReports().flush();
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        Reporter.log("Running test case " + result.getName());
//
//        //Bắt đầu ghi 1 TCs mới vào Extent Report
//        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        Reporter.log("Test case " + result.getName() + " is passed.");
//
//        //Extent Report
//        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        LogUtils.error("Test case " + result.getName() + " is failed.");
//        //Screenshot khi fail
//        CaptureHelper.captureScreenshot(result.getName());
//        LogUtils.error(result.getThrowable().toString());
//
//        //Extent Report
//        ExtentTestManager.addScreenShot(result.getName());
//        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
//        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        LogUtils.error("Test case " + result.getName() + " is skipped.");
//        LogUtils.error(result.getThrowable().toString());
//
//        //Extent Report
//        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//        LogUtils.error("Đây là test case bị Fail nhưng có phần Success: " + result.getName());
//        LogUtils.error(result.getThrowable().toString());
//    }
//}
