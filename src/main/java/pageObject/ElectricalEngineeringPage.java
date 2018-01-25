package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ElectricalEngineeringPage extends BasePage{

    @FindBy(xpath="//a[contains(text(), 'Поиск')]")
    private WebElement searchLink;

    public BasePage goToSeachPage() {

        searchLink.click();

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class = 'headtitle']/b[contains(text(), 'Поиск')]")));
            return new SearchPage();
        } catch (TimeoutException e) {
            return new BasePage();
        }
    }
}
