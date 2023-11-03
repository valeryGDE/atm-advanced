package main.java.pagefactory.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class SideBarPage extends AbstractPage {
    @FindBy(xpath = "//a[contains(@href, 'filters')]")
    private WebElement filtersButton;

    public SideBarPage(WebDriver driver) {
        super(driver);
    }

    public void clickFiltersButton() {
        filtersButton.click();
    }

    public void waitForFiltersButtonIsShown() {
        waitForElement(ExpectedConditions.visibilityOf(filtersButton), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }
}