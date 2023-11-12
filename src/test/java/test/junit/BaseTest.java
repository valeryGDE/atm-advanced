package test.junit;

import main.java.core.driver.BrowserType;
import main.java.core.driver.WebDriverManager;
import main.java.core.logger.Log;
import main.java.core.data.DataProviders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest extends DataProviders {

    @BeforeEach
    public void setUp() {
        WebDriverManager.setDriver(BrowserType.CHROME);
    }

    @AfterEach
    public void stopBrowser() {
        Log.info("Stop driver: " + WebDriverManager.getDriver().toString());
        WebDriverManager.closeDriver();
    }

    protected WebDriver getDriver() {
        return WebDriverManager.getDriver();
    }
}