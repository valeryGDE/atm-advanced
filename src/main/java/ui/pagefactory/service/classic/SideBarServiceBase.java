package main.java.ui.pagefactory.service.classic;

import main.java.ui.pagefactory.page.classic.SideBarPage;
import main.java.ui.pagefactory.pagebase.AbstractBaseService;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static main.java.ui.pagefactory.page.classic.AbstractPage.DEFAULT_TIMEOUT;

public class SideBarServiceBase extends AbstractBaseService<SideBarPage> {

    @Override
    public boolean isPageShown() {
        return getPage().getSideBarContainer().isDisplayed();
    }

    public void clickFiltersButton() {
        getPage().getFiltersButton().click();
    }

    public void waitForFiltersButtonIsClickable() {
        getPage().waitFor(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(getPage().getFiltersButton())), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }
}