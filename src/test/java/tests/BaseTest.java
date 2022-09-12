package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import utils.DriverFactory;
import utils.logs.Log;

public class BaseTest {
    protected WebDriver driver;
    protected SoftAssert softAssert;

    @BeforeClass
    @Parameters({"browserName"})
    public void setUp(String browserName){
        Log.info("Tests are starting!");
        softAssert = new SoftAssert();
        driver = DriverFactory.getDriver(browserName);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        Log.info("Tests are ending!");
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
