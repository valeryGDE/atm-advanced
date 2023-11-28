package main.java.pagefactory.service;

import main.java.pagefactory.page.SideBarPage;
import main.java.pagefactory.pagebase.AbstractBaseService;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static main.java.pagefactory.page.AbstractPage.DEFAULT_TIMEOUT;

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