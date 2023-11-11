package main.java.pagefactory.service;

import main.java.pagefactory.page.SideBarPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static main.java.pagefactory.page.AbstractPage.DEFAULT_TIMEOUT;

public class SideBarService extends AbstractService {

    private final SideBarPage sideBarPage = new SideBarPage(driver);

    public SideBarService(WebDriver driver) {
        super(driver);
    }

    public void clickFiltersButton() {
        sideBarPage.getFiltersButton().click();
    }

    public void waitForFiltersButtonIsClickable() {
        sideBarPage.waitForElement(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(sideBarPage.getFiltersButton())), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }
}