package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.*;

public class SearchTests extends BaseTest{

    private StartPage startPage;
    private BasePage basePage;
    private ElectricalEngineeringPage electricalEngineeringPage;
    private SearchPage searchPage;
    private SearchResultPage searchResultPage;

    private String searchPhrase = "Computer";
    private Integer selectCount = 3;
    private Integer minPrice = 160;
    private Integer maxPrice = 300;

    @BeforeMethod
    public void homePagePrepare() {
        startPage = new StartPage();
    }

    @Test
    public void testMemo () {
        startPage.switchLanguage();
        Assert.assertEquals(startPage.getCurrentLanguage(), "RU");

        basePage = startPage.goToElectricalEngineeringSection();
        Assert.assertTrue(basePage instanceof ElectricalEngineeringPage);
        electricalEngineeringPage = (ElectricalEngineeringPage)basePage;

        basePage = electricalEngineeringPage.goToSeachPage();
        Assert.assertTrue(basePage instanceof SearchPage);
        searchPage = (SearchPage)basePage;

        basePage = searchPage.fillAndSubmitSearchPhrase(searchPhrase);
        Assert.assertTrue(basePage instanceof SearchResultPage);
        searchResultPage = (SearchResultPage)basePage;

        searchResultPage.sortByPrice();
        searchResultPage.setTypeOfTransactionToSell();

        basePage =  searchResultPage.goToAdvancedSearch();
        Assert.assertTrue(basePage instanceof SearchPage);
        searchPage = (SearchPage)basePage;

        searchPage.fillMinPrice(minPrice);
        searchPage.fillMaxPrice(maxPrice);
        basePage = searchPage.clickSubmitBtn();
        Assert.assertTrue(basePage instanceof SearchResultPage);
        searchResultPage = (SearchResultPage)basePage;

        searchResultPage.chooseRandomProducts(selectCount);
        Assert.assertEquals(searchResultPage.getCountOfCheckedProducts(), selectCount);

    }
}
