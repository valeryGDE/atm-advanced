package test.ui.junit;

import com.google.inject.Guice;
import com.google.inject.Inject;
import main.java.ui.businesslogic.areas.FiltersBll;
import main.java.ui.businesslogic.areas.LoginBll;
import main.java.ui.core.data.DataProviders;
import main.java.ui.core.driver.BrowserType;
import main.java.ui.core.driver.WebDriverManager;
import main.java.ui.core.logger.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTestJunit extends DataProviders {

    @Inject
    protected LoginBll loginBll;
    @Inject
    protected FiltersBll filtersBll;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriverManager.setDriver(BrowserType.CHROME);
        Guice.createInjector().injectMembers(this);
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