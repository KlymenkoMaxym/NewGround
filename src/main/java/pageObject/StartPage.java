package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StartPage extends BasePage {

    public StartPage() {
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath="//span[@class='menu_lang']/a")
    private WebElement switchLanguageLink;

    @FindBy(xpath = "//a[contains(text(), 'Электротехника')]")
    private WebElement electricalEngineeringLinkRu;


    public void switchLanguage() {
        switchLanguageLink.click();
    }

    public String getCurrentLanguage() {
        if (switchLanguageLink.getText().equals("LV")) return "RU";
        return "LV";
    }

    public BasePage goToElectricalEngineeringSection () {

        electricalEngineeringLinkRu.click();

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class = 'headtitle']/a[contains(text(), 'Электротехника')]")));
            return new ElectricalEngineeringPage();
        } catch (TimeoutException e) {
            return new BasePage();
        }

    }

}
