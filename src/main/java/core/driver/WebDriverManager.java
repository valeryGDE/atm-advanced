package main.java.core.driver;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class WebDriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(BrowserType browserType) {
        WebDriver webDriver = DriverFactory.createBrowser(browserType);
        driver.set(Objects.requireNonNull(webDriver));
    }

    public static WebDriver getDriver() {
        return Objects.requireNonNull(driver.get());
    }

    public static void closeDriver() {
        getDriver().quit();
        driver.remove();
    }
}