package main.java.ui.pagefactory.service.classic;

import main.java.ui.pagefactory.page.classic.CommonComponents;
import main.java.ui.pagefactory.pagebase.AbstractBaseService;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static main.java.ui.pagefactory.page.classic.AbstractPage.DEFAULT_TIMEOUT;

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