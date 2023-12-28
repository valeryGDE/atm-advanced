package test.selenide;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.inject.Guice;
import com.google.inject.Inject;
import main.java.ui.core.driver.SelenideWebDriverManager;
import main.java.ui.core.listener.SelenideListener;
import main.java.ui.pagefactory.page.selenide.*;
import main.java.ui.pagefactory.service.selenide.SelenideFiltersServiceBase;
import main.java.ui.pagefactory.service.selenide.SelenideLoginServiceBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class SelenideBaseTest {

    @Inject
    protected SelenideWebDriverManager selenideWebDriverManager;
    @Inject
    protected SelenideLoginServiceBase selenideLoginServiceBase;
    @Inject
    protected SelenideFiltersServiceBase selenideFiltersServiceBase;
    @Inject
    protected SelenideSideBarPage selenideSideBarPage;
    @Inject
    protected SelenideDashboardPage selenideDashboardPage;
    @Inject
    protected SelenideCommonComponents selenideCommonComponents;
    @Inject
    protected SelenideFiltersPage selenideFiltersPage;
    @Inject
    protected SelenideLaunchesPage selenideLaunchesPage;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        Guice.createInjector().injectMembers(this);
        selenideWebDriverManager.configureRemoteDriver(browser);
        SelenideLogger.addListener("SelenideListener", new SelenideListener());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        SelenideLogger.removeListener("SelenideListener");
        closeWebDriver();
    }
}