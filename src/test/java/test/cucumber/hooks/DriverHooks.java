package test.cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import main.java.core.driver.BrowserType;
import main.java.core.driver.WebDriverManager;
import main.java.core.logger.Log;

public class DriverHooks {

//    @Inject
//    protected LoginBll loginBll;
//    @Inject
//    protected FiltersBll filtersBll;

    @Before
    public void setupDriver() {
        WebDriverManager.setDriver(BrowserType.CHROME);
//        Guice.createInjector().injectMembers(this);
    }

    @After
    public void quitDriver() {
        Log.info("Stop driver: " + WebDriverManager.getDriver().toString());
        WebDriverManager.closeDriver();
    }
}
