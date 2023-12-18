package takeScreenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {
    private String projectPath = System.getProperty("user.dir") + "/screenshotImg/";
    WebDriver driver;

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();

        driver = ((TakeScreenshotTestFailed) testClass).getWeDriver();
        TakesScreenshot scrShot = (TakesScreenshot) driver;
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(projectPath + result.getName() + ".png");
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
