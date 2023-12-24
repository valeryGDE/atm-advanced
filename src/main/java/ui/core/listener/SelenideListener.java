package main.java.ui.core.listener;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import main.java.ui.core.logger.Log;

public class SelenideListener implements LogEventListener {
    @Override
    public void afterEvent(LogEvent logEvent) {
        Log.info("After event " + logEvent);
    }

    @Override
    public void beforeEvent(LogEvent logEvent) {
        Log.info("Before event " + logEvent);
    }
}
