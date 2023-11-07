package test;

import main.java.core.driver.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod()
    public void setUp() {
        driver = WebDriverManager.getWebDriverInstance();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        WebDriverManager.closeDriver();
    }
}