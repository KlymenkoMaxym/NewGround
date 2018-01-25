package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchResultPage extends BasePage {

    @FindBy(xpath="//a[contains(text(), 'Цена')]")
    private WebElement priceSortLink;

    @FindBy(xpath="//select[@class = 'filter_sel']")
    private WebElement typeOfTransactionDropdown;

    @FindBy(xpath="//select[@class = 'filter_sel']/option[contains(text(), 'Продажа')]")
    private WebElement sellOption;

    @FindBy(xpath="//a[contains(text(),'Расширенный поиск')]")
    private WebElement advancedSearchLink;

    @FindBy(xpath="//input[@type = 'checkbox']")
    private List<WebElement> searchResult;


    final Random random = new Random();

    public void chooseRandomProducts(int count) {
        List<Integer> numbersGenerated = getRandomNumber(count, searchResult.size());

        for (Integer i: numbersGenerated) {
            searchResult.get(i).click();
        }
    }

    public Integer getCountOfCheckedProducts() {
        Integer count = 0;

        for (WebElement element: searchResult) {
            if (element.isSelected()) count++;
        }

        return count;
    }

    private List<Integer> getRandomNumber(int count, int bound) {
        ArrayList<Integer> numbersGenerated = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Random randNumber = new Random();
            int iNumber = randNumber.nextInt(bound);

            if (!numbersGenerated.contains(iNumber)) {
                numbersGenerated.add(iNumber);
            } else {
                i--;
            }
        }
        return numbersGenerated;
    }

    public BasePage goToAdvancedSearch() {

        advancedSearchLink.click();

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class = 'headtitle']/b[contains(text(), 'Поиск')]")));
            return new SearchPage();
        } catch (TimeoutException e) {
            return new BasePage();
        }
    }

    public void setTypeOfTransactionToSell() {
        typeOfTransactionDropdown.click();
        sellOption.click();
    }

    public void sortByPrice() {
        priceSortLink.click();
    }
}
