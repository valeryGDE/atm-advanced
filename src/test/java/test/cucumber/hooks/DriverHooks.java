package test.cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import main.java.core.driver.BrowserType;
import main.java.core.driver.WebDriverManager;
import main.java.core.logger.Log;

public class DriverHooks {

    @Before
    public void setupDriver() {
        WebDriverManager.setDriver(BrowserType.CHROME);
    }

    @After
    public void quitDriver() {
        Log.info("Stop driver: " + WebDriverManager.getDriver().toString());
        WebDriverManager.closeDriver();
    }
}
