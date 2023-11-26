package main.java.pagefactory.service;

import main.java.pagefactory.page.CommonComponents;
import main.java.pagefactory.pagebase.AbstractBaseService;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static main.java.pagefactory.page.AbstractPage.DEFAULT_TIMEOUT;

public class CommonComponentsServiceBase extends AbstractBaseService<CommonComponents> {

    @Override
    public boolean isPageShown() {
        return getPage().getLoader().isDisplayed();
    }

    public void waitForSpinnerIsNotShown() {
        getPage().waitFor(ExpectedConditions.invisibilityOf(getPage().getLoader()), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public void waitForNotificationIsNotShown() {
        getPage().waitFor(ExpectedConditions.invisibilityOf(getPage().getNotification()), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }
}
