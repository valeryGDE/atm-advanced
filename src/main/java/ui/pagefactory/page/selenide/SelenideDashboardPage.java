package main.java.ui.pagefactory.page.selenide;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class SelenideDashboardPage extends AbstractSelenidePage {

    private static final String WIDGET = "//div[text()='%s']/ancestor::div[contains(@class,'react-grid-item')]";
    private static final String ADD_DASHBOARD_BUTTON = "div[class*=addDashboardButton]";
    private static final String DASHBOARD_LINK = "//div[contains(@class,'gridRow')]/a[text()='%s']";

    @Override
    public boolean isPageShown() {
        return $(ADD_DASHBOARD_BUTTON).isDisplayed();
    }

    public void openDashboardByNameWithJS(String name) {
        clickElementWithJS($(byXpath(DASHBOARD_LINK.formatted(name))));
    }

    public void scrollToWidgetWithJS(String name) {
        scrollToElementWithJS($(byXpath(WIDGET.formatted(name))));
    }

    public boolean isWidgetInViewPortUsingJS(String name) {
        return isElementInViewPort($(byXpath(WIDGET.formatted(name))));
    }

    public void resizeElement(String name, int x, int y) {
        var resize = $(byXpath(WIDGET.formatted(name) + "//span[contains(@class,'react-resizable')]"));
        actions().clickAndHold(resize)
                .moveByOffset(x, y)
                .release()
                .perform();
    }

    public void dragAndDropElement(String name, int x, int y) {
        var resize = $(byXpath(WIDGET.formatted(name) + "//div[contains(@class,'header')]"));
        actions().dragAndDropBy(resize, x, y).perform();
    }

    public Dimension getSizeOfWidgetByName(String name) {
        return $(byXpath(WIDGET.formatted(name))).getSize();
    }

    public Point getLocationOfWidgetByName(String name) {
        return $(byXpath(WIDGET.formatted(name))).getLocation();
    }
}
