package utils;

import org.jetbrains.annotations.NotNull;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int count = 0;
    private static final int maxTry = 5;
    @Override
    public boolean retry(@NotNull ITestResult iTestResult) {
        boolean rerun = false;
        if (!iTestResult.isSuccess()){
            if (count < maxTry){
                count++;
                rerun = true;
            }
        }
        int status = iTestResult.isSuccess()? ITestResult.SUCCESS: ITestResult.FAILURE;
        iTestResult.setStatus(status);
        return rerun;
    }
}
