package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage {

    @FindBy(xpath="//input[@id = 'ptxt']")
    private WebElement searchPhraseInput;

    @FindBy(xpath="//input[@id = 'sbtn']")
    private WebElement submitBtn;

    @FindBy(xpath="//input[@name = 'topt[8][min]']")
    private WebElement minPriceInput;

    @FindBy(xpath="//input[@name = 'topt[8][max]']")
    private WebElement maxPriceInput;


    public SearchPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void fillMinPrice(int minPrice) {
        minPriceInput.clear();
        minPriceInput.sendKeys(String.valueOf(minPrice));
    }

    public void fillMaxPrice(int maxPrice) {
        maxPriceInput.clear();
        maxPriceInput.sendKeys(String.valueOf(maxPrice));
    }

    public BasePage fillAndSubmitSearchPhrase(String phrase) {

        fillSearchPhrase(phrase);
        return clickSubmitBtn();
    }

    public BasePage clickSubmitBtn() {

        submitBtn.click();

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class = 'headtitle']/b[contains(text(), 'Результат поиска')]")));
            return new SearchResultPage();
        } catch (TimeoutException e) {
            return new BasePage();
        }
    }

    public void fillSearchPhrase(String phrase) {
        searchPhraseInput.clear();
        searchPhraseInput.sendKeys(phrase);
        driver.findElement(By.xpath("//div[@id = 'preload_txt']//b[contains(text(), '" + phrase + "')]")).click();
    }

}
