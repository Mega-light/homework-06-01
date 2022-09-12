package gov.loc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// page_url = https://www.loc.gov/
public class HomePage extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(HomePage.class);
    private final String baseUrl = "https://www.loc.gov/";
    @FindBy(css = "a[title*='survey']")
    private WebElement lnkSurvey;

    @FindBy(css = "a[href='/collections/']")
    private WebElement lnkDigitalCollections;

    @FindAll({@FindBy(css = "a[id^='top_searches_']")})
    private List<WebElement> lnkTopSearches;

    @FindBy(css = ".slick-next.slick-arrow")
    private WebElement btnNext;

    private final By divPagingInfo = By.cssSelector("div.featured-slider-slide:nth-child(2)" +
            " > a:nth-child(2) > div:nth-child(1) > div:nth-child(3)");

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Go to Homepage
     * @return homepage
     */
    public HomePage goToHomePage(){
        log.info("Opening Loc website...");
        visit(baseUrl);
        return this;
    }

    public SurveyPage goToSurveyPage(){
        log.info("Going to Survey Page...");
        click(lnkSurvey);
        return new SurveyPage(driver);
    }

    public DigitalCollectionsPage goToDigitalCollectionsPage(){
        log.info("Going to Digital Collections Page...");
        click(lnkDigitalCollections);
        return new DigitalCollectionsPage(driver);
    }

    public String getURL() {
        return getCurrentURL();
    }


    /**
     * Go to next featured item in carrousel.
     * @return paging-info
     */
    public HomePage clickNextFeaturedItem(){
        click(btnNext);
//        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//        javascriptExecutor.executeScript("arguments[0].click();",
//                find(divPagingInfo));
        return this;
    }

    private int currentPage = 1;

    public String getPagingInfo() throws InterruptedException {
        if (currentPage >= 5){
            currentPage = 1;
        }
        btnNext.click();
        WebElement element = find(divPagingInfo);
        String innerHTML = element.getAttribute("innerHTML");
        System.out.println(innerHTML);
        return innerHTML;
    }

    public String[][] getTopSearchesResourceWithText(){
        String[][] listTextToLink = new String[lnkTopSearches.size()][2];
        for (int i = 0; i < lnkTopSearches.size(); i++) {
            WebElement element = lnkTopSearches.get(i);
            String resource = element.getAttribute("href");
            String text = readText(element);
            listTextToLink[i][0] = resource;
            listTextToLink[i][1] = text;
        }
        return listTextToLink;
    }

    public HomePage scrollToSurvey(){
        scrollIntoView(lnkSurvey);
        return this;
    }
}