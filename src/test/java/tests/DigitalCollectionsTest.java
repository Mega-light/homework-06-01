package tests;

import data.DigitalCollectionsDP;
import gov.loc.pages.DigitalCollectionsPage;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.listeners.DigitalCollectionsTL;

import static org.testng.Assert.*;

@Listeners({DigitalCollectionsTL.class})
public class DigitalCollectionsTest extends BaseTest {
    private DigitalCollectionsPage digitalCollectionsPage;
    private final String baseUrl = "https://www.loc.gov/";

    @BeforeClass
    public void setUp(){
        digitalCollectionsPage = new DigitalCollectionsPage(driver).goToDigitalCollections();
    }

    @Test(groups = {"collections"}, dataProviderClass = DigitalCollectionsDP.class, dataProvider = "searchFor-provider")
    @Description("This test validates the searching in the Digital Collections page")
    public void searchFor(String search){
        digitalCollectionsPage.searchFor(search);
    }

    @Test(groups = {"collections"})
    @Description("This test validates the content title is Digital Collections")
    public void validateTitle(){
        String contentTitle = digitalCollectionsPage.getContentTitle();

        String expected = "Digital collections";
        assertTrue(contentTitle.toUpperCase().contains(expected.toUpperCase()),
                "The word 'Digital collections' is not present in the page title.");
    }
}