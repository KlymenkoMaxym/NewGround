package tests;

import Util.DriverManager;
import Util.PropertiesManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static final String URL = PropertiesManager.getInstance().getResourceByName("URL");

    private WebDriver driver;

    @BeforeTest
    public void beforeClass() {
        driver = DriverManager.getInstance().createWebDriver("chrome").getDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        driver.get(URL);
        if (method.getDeclaringClass().equals(SearchTests.class)) {
            return;
        }
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }
}
