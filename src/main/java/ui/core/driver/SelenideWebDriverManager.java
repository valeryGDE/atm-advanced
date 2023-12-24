package main.java.ui.core.driver;

import com.codeborne.selenide.Configuration;
import main.java.ui.core.logger.Log;

public class SelenideWebDriverManager {

    public void configureRemoteDriver(String browser) {
        Log.info("Current browser: '%s'".formatted(browser));
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
        Configuration.webdriverLogsEnabled = true;
        Configuration.screenshots = true;
        Configuration.browser = browser;
        Configuration.remote = "http://localhost:4444/wd/hub";
    }
}