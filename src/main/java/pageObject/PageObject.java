package pageObject;

import Util.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

    protected WebDriver driver = DriverManager.getInstance().getDriver();
    protected WebDriverWait wait;

    public PageObject() {
        wait = new WebDriverWait(driver, 15);
    }
}
