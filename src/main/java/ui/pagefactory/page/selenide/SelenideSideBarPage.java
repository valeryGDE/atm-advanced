package main.java.ui.pagefactory.page.selenide;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class SelenideSideBarPage extends AbstractSelenidePage{

    private static final String SIDE_BAR_CONTAINER = "//div[contains(@class, 'sidebar-container')]";

    @Override
    public boolean isPageShown() {
        return getSideBarContainer().isDisplayed();
    }

    public void clickFiltersButton() {
        $(By.xpath("//a[contains(@href, 'filters')]")).click();
    }

    public void clickLaunchesButton() {
        $(By.xpath("//a[contains(@href, 'launches')]")).click();
    }

    public void clickDashboardsButton() {
        $(By.xpath("//a[contains(@href, 'dashboard')]")).click();
    }

    public SelenideElement getSideBarContainer() {
        return $(By.xpath(SIDE_BAR_CONTAINER));
    }
}