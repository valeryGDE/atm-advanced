package main.java.pagefactory.service;

import main.java.pagefactory.page.SpinnerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static main.java.pagefactory.page.AbstractPage.DEFAULT_TIMEOUT;

public class SpinnerService extends AbstractService {

    private final SpinnerPage spinnerPage = new SpinnerPage(driver);

    public SpinnerService(WebDriver driver) {
        super(driver);
    }

    public void waitForSpinnerIsNotShown() {
        spinnerPage.waitForElement(ExpectedConditions.invisibilityOf(spinnerPage.getLoader()), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }
}
