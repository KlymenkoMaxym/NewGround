package pageObject;

import org.openqa.selenium.support.PageFactory;

public class BasePage extends PageObject{


    public BasePage() {
        PageFactory.initElements(this.driver, this);
    }
}
