package actions.reportConfig;

import actions.commons.BaseTest;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ReportNGListener implements ITestListener {
    private String projectPath = System.getProperty("user.dir") + "/reportNGScreenshot/";
    WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("---------- " + context.getName() + " STARTED test ----------");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("---------- " + context.getName() + " FINISHED test ----------");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("---------- " + result.getName() + " STARTED test ----------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("---------- " + result.getName() + " SUCCESS test ----------");
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        Object testClass = result.getInstance();
//
//        driver = ((TakeScreenshotTestFailed) testClass).getWedDriver();
//        TakesScreenshot scrShot = (TakesScreenshot) driver;
//        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
//        File DestFile = new File(projectPath + result.getName() + ".png");
//        try {
//            FileUtils.copyFile(SrcFile, DestFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //Case capture image
//        System.out.println("---------- " + result.getName() + " FAILED test ----------");
//        System.setProperty("org.uncommons.reportng.escape-output", "false");
//
//        Object testClass = result.getInstance();
//        WebDriver webDriver = ((BaseTest) testClass).getWedDriver();
//
//        String screenshotPath = captureScreenshot_Image(webDriver, result.getName());
//        Reporter.getCurrentTestResult();
//        Reporter.log("<br><a target=\"_blank\" href=\"file:///" + screenshotPath + "\">" + "<img src=\"file:///"
//                + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
//        Reporter.setCurrentTestResult(null);

        try {
            System.setProperty("org.uncommons.reportng.escape-output", "false");

            Object testClass = result.getInstance();
            WebDriver driver = ((BaseTest) testClass).getWedDriver();

            String screenshotPath = captureScreenshot_Base64(driver, result.getName());
            Reporter.getCurrentTestResult();

            Reporter.log("<br><a target=\"_blank\" href=\"data:image/png;base64," + screenshotPath + "\">"
                    + "<img src=\"data:image/png;base64," + screenshotPath + "\" " + "height='100' width='150'/> "
                    + "</a></br>");
            Reporter.setCurrentTestResult(null);
        } catch (NoSuchSessionException e) {
            e.printStackTrace();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    public String captureScreenshot_Base64(WebDriver driver, String screenshotName) {
        String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        return screenshotBase64;
    }

//    public String captureScreenshot_Image(WebDriver driver, String screenshotName) {
//        try {
//            Calendar calendar = Calendar.getInstance();
//            SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
//            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            String screenPath = System.getProperty("user.dir") + "/ReportNGScreenShots/"
//                    + screenshotName + "_" + formater.format(calendar.getTime()) + ".png";
//            FileUtils.copyFile(source, new File(screenPath));
//            return screenPath;
//        } catch (IOException e) {
//            System.out.println("Exception while taking screenshot: " + e.getMessage());
//            return e.getMessage();
//        }
//    }
}
