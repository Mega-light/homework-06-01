package gov.loc.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait driverWait;

    protected final int durationOfSeconds = 15;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(durationOfSeconds));
    }

    protected WebElement find(By by){
        return driver.findElement(by);
    }

    protected List<WebElement> findAll(By by){
        return driver.findElements(by);
    }

    /**
     * Wait wrapper method
     */
    protected void waitVisibility(WebElement element){
        driverWait.until(ExpectedConditions.visibilityOf(element));
    }
    
    protected void waitVisibility(List<WebElement> elements){
        driverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    protected void click(WebElement element){
        waitVisibility(element);
        element.click();
    }

    protected void submitForm(WebElement element){
        element.submit();
    }

    protected void typeText(WebElement element, String text){
        waitVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    protected String readText(WebElement element){
        waitVisibility(element);
        return element.getText();
    }

    protected void scrollIntoView(WebElement element){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String scrollIntoView = "arguments[0].scrollIntoView();";
        javascriptExecutor.executeScript(scrollIntoView, element);
    }

    protected boolean isDisplayed(WebElement element){
        boolean result;
        try {
            result = element.isDisplayed();
        } catch (NoSuchElementException e){
            result = false;
        }
        return result;
    }

    protected String getCurrentURL(){
        return driver.getCurrentUrl();
    }


    protected boolean remove(WebElement element){
        boolean result;
        try {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript(
                    "let iframe = arguments[0];"
                            + "iframe.parentNode.removeChild(iframe)",
                    element);
            result = true;
        } catch (NoSuchElementException e){
            result = false;
        }
        return result;
    }

    protected String getAttributeValue(WebElement element){
        return element.getAttribute("value");
    }

    protected String getMessageAlert(){
        String parent = driver.getWindowHandle();
        String text = driver.switchTo().alert().getText();
        driver.switchTo().window(parent);
        return text;
    }

    protected void visit(String url){
        driver.get(url);
    }

}
