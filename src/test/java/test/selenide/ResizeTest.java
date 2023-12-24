package test.selenide;

import main.java.ui.businesslogic.manager.AccountManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResizeTest extends SelenideBaseTest {

    @Test
    public void resizeWidget() {
        selenideLoginServiceBase.logIn(AccountManager.adminAccount());
        selenideSideBarPage.clickDashboardsButton();
        selenideDashboardPage.openDashboardByNameWithJS("DEMO DASHBOARD");
        selenideCommonComponents.waitForSpinnerIsNotShown();
        var widgetName = "OVERALL STATISTICS DONUT";
        selenideDashboardPage.scrollToWidgetWithJS(widgetName);
        Assert.assertTrue(selenideDashboardPage.isWidgetInViewPortUsingJS(widgetName), "Widget with name: '%s' should be in view".formatted(widgetName));
        var beforeResize = selenideDashboardPage.getSizeOfWidgetByName(widgetName);
        selenideDashboardPage.resizeElement(widgetName, 70, 0);
        var afterResize = selenideDashboardPage.getSizeOfWidgetByName(widgetName);
        Assert.assertTrue(beforeResize.getWidth() < afterResize.getWidth(), "Element sizes should be equal");
    }
}