package utils.listeners;

import io.qameta.allure.Attachment;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import utils.logs.Log;


public class TestListener extends BaseTest implements ITestListener {

    private static String getTestMethodName(@NotNull ITestResult iTestResult){
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message){
        return message;
    }

    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html){
        return html;
    }

    @Override
    public void onStart(@NotNull ITestContext context) {
        Log.info("onStart method " + context.getName());
        context.setAttribute("WebDriver", driver);
    }

    @Override
    public void onFinish(@NotNull ITestContext context) {
        Log.info("onFinish method " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        Log.info(getTestMethodName(result) + " test is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info(getTestMethodName(result) + " test is failed.");

        BaseTest instance = (BaseTest) result.getInstance();
        WebDriver driver = instance.getDriver();

        // Allure ScreenShotRobot and SaveTestLog
        if (driver != null) {
            System.out.println("Screenshot captured for test case: " + getTestMethodName(result));
            saveScreenshotPNG(driver);
        }

        // Save a log on allure.
        saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.info(getTestMethodName(result) + " test is failed.");

        BaseTest instance = (BaseTest) result.getInstance();
        WebDriver driver = instance.getDriver();

        // Allure ScreenShotRobot and SaveTestLog
        if (driver != null) {
            System.out.println("Screenshot captured for test case: " + getTestMethodName(result));
            saveScreenshotPNG(driver);
        }

        // Save a log on allure.
        saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.info(getTestMethodName(result) + " test is skipped.");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(result));
    }
}
