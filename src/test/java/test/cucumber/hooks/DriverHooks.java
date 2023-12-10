package test.cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import main.java.ui.core.driver.BrowserType;
import main.java.ui.core.driver.WebDriverManager;
import main.java.ui.core.logger.Log;

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
