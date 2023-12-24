package test.selenide;

import main.java.ui.businesslogic.manager.AccountManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTest extends SelenideBaseTest {

    @Test
    public void dragAndDropWidget() {
        selenideLoginServiceBase.logIn(AccountManager.adminAccount());
        selenideSideBarPage.clickDashboardsButton();
        selenideDashboardPage.openDashboardByNameWithJS("DEMO DASHBOARD");
        selenideCommonComponents.waitForSpinnerIsNotShown();
        var widgetName = "OVERALL STATISTICS DONUT";
        selenideDashboardPage.scrollToWidgetWithJS(widgetName);
        Assert.assertTrue(selenideDashboardPage.isWidgetInViewPortUsingJS(widgetName), "Widget with name: '%s' should be in view".formatted(widgetName));
        var beforeMove = selenideDashboardPage.getLocationOfWidgetByName(widgetName);
        selenideDashboardPage.dragAndDropElement(widgetName, 100, 0);
        var afterMove = selenideDashboardPage.getLocationOfWidgetByName(widgetName);
        Assert.assertNotEquals(afterMove, beforeMove, "Element location should be different");
    }
}