package tests;

import gov.loc.pages.DigitalCollectionsPage;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import gov.loc.pages.HomePage;
import utils.Retry;
import utils.logs.Log;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class HomeTest extends BaseTest{
    private HomePage homePage;
    private final String baseUrl = "https://www.loc.gov/";

    @BeforeClass
    public void setUp(){
        homePage = new HomePage(driver);
    }

    @Test(priority = 1, description = "Validate the HomePage URL", groups = {"homepage"})
    @Description("This test validates that the url belongs to the HomePage")
    public void validateUrl() {
        HomePage page = homePage.goToHomePage();
        assertEquals(page.getURL(), baseUrl);
        Log.info("Successfully validated URL");
    }

    @Test(priority = 2, description = "Validate the carousel element", retryAnalyzer = Retry.class, groups = {"homepage"})
    @Description("This test validates the carousel item show is 5/5")
    public void validateCarousel() {
        String pagingInfo = homePage.nextPagingInfo();
        String expectedPaging = "5/5";
        assertEquals(pagingInfo, expectedPaging);
        Log.info("Successfully validated carousel");
    }

    @Test(priority = 2, groups = {"homepage"})
    @Description("This test validates the trending top searches")
    public void validateTrendingTopSearches(){
        List<String[]> collect = Arrays.stream(homePage.getTopSearchesResourceWithText()).map(s -> {
            String link = s[0].replace("%20", " ");
            String text = s[1];
            s = new String[]{link, text};
            return s;
        }).toList();
        collect.forEach(e-> softAssert.assertTrue(e[0].toLowerCase().contains(e[1].toLowerCase())));
        softAssert.assertAll();
    }
    @Test(dependsOnMethods = {"validateCarousel", "validateTrendingTopSearches"}, groups = {"homepage"})
    @Description("This test validates the link to the Digital Collections page")
    public void validateGoToDigitalCollectionsPage(){
        DigitalCollectionsPage digitalCollectionsPage = homePage.goToDigitalCollectionsPage();
        String expectedUrl = "https://www.loc.gov/collections/";
        assertNotNull(digitalCollectionsPage);
        assertEquals(homePage.getURL(), expectedUrl, "Link does not redirect to expected URL");
    }
}
