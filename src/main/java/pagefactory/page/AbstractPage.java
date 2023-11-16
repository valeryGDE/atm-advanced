package main.java.pagefactory.page;

import main.java.core.logger.Log;
import main.java.utils.Screenshoter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {

    public static final int DEFAULT_TIMEOUT = 10;
    protected WebDriver driver;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void openPage(String url, WebElement element, Duration timeout) {
        driver.get(url);
        waitForElement(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)), timeout);
    }

    public void waitForElement(ExpectedCondition<?> condition, Duration timeout) {
        try {
            new WebDriverWait(driver, timeout).until(condition);
        } catch (TimeoutException e) {
            Log.info("Element not found due to timeout");
            new Screenshoter().makeScreenshot();
        }
    }
}