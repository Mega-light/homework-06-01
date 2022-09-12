package utils.listeners;

import org.testng.*;
import tests.BaseTest;

public class DigitalCollectionsTL extends BaseTest implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println("This method is invoked after " + method.getTestMethod().getMethodName());
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println("This method is invoked before " + method.getTestMethod().getMethodName());
    }
}
