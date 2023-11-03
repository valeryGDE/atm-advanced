package main.java.core.listener;

import main.java.core.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.util.Arrays;

public class LoggingListener implements WebDriverListener {
    @Override
    public void beforeGet(WebDriver driver, String url) {
        Log.info("Navigating to [" + url + "]");
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        Log.info("Trying to locate element using locator [" + locator + "]");
    }

    @Override
    public void beforeClick(WebElement element) {
        Log.info("Clicking element [" + element.getTagName() + " " + element.getAccessibleName() + "]");
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        Log.info("Sending keys [" + Arrays.toString(keysToSend) + "] to the element [" + element.getTagName() + " " + element.getAccessibleName() + "]");
    }
}
