package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public static WebDriver getDriver(@NotNull String browserName){
        return WebDriverManager.getInstance(browserName).create();
    }
}
