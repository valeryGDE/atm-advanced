package main.java.pagefactory.page;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

@Getter
public class SpinnerPage extends DefaultPage {

    @FindBy(xpath = "//div[contains(@class,'spinningPreloader')]")
    private WebElement loader;

    public void waitForSpinnerIsNotShown() {
        waitFor(ExpectedConditions.invisibilityOf(getLoader()), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }
}