package test.testng;

import main.java.core.data.DataProviders;
import main.java.core.driver.BrowserType;
import main.java.core.driver.WebDriverManager;
import main.java.core.logger.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends DataProviders {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriverManager.setDriver(BrowserType.CHROME);
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        Log.info("Stop driver: " + WebDriverManager.getDriver().toString());
        WebDriverManager.closeDriver();
    }

    protected WebDriver getDriver() {
        return WebDriverManager.getDriver();
    }
}