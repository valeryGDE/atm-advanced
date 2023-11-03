package main.java.core.driver;

import org.openqa.selenium.WebDriver;

public class WebDriverManager {
    private static WebDriver driver;

    private WebDriverManager() {
    }

    public static WebDriver getWebDriverInstance() {
        if (driver == null) {
            driver = DriverFactory.createBrowser(BrowserType.CHROME);
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}