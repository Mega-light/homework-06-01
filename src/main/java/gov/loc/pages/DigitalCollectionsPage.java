package gov.loc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.loc.gov/collections/
public class DigitalCollectionsPage extends BasePage {
    private final String baseUrl = "https://www.loc.gov/collections/";
    @FindBy(id = "search")
    private WebElement txtSearch;

    @FindBy(css = "form[role='search']")
    private WebElement frmSearch;

    @FindBy(css = "div#page-title > h1 > span")
    private WebElement spnTitle;

    public DigitalCollectionsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public DigitalCollectionsPage goToDigitalCollections(){
        visit(baseUrl);
        return new DigitalCollectionsPage(driver);
    }

    public DigitalCollectionsPage searchFor(String text){
        typeText(txtSearch, text);
        submitForm(frmSearch);
        return this;
    }

    public String getContentTitle(){
        return readText(spnTitle);
    }
}