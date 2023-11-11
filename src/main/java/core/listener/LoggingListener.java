package main.java.core.listener;

import main.java.core.logger.Log;
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
    public void beforeClick(WebElement element) {
        Log.info("Clicking element [" + element + "]");
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        Log.info("Sending keys [" + Arrays.toString(keysToSend) + "] to the element [" + element + "]");
    }
}