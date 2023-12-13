package main.java.ui.pagefactory.pagebase;

import main.java.ui.core.logger.Log;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public interface IPageVerifier {

    boolean isPageShown();

    default void waitPageIsShown() {
        waitPageIsShown(Duration.ofSeconds(30));
    }

    default void waitPageIsShown(Duration duration) {
        try {
            new FluentWait<>(Boolean.TRUE)
                    .withTimeout(duration)
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(Exception.class)
                    .until(page -> this.isPageShown());
        } catch (TimeoutException timeoutException) {
            Log.warn(String.format("Timeout %s exceeded", duration));
        }
    }
}
