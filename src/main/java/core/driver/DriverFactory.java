package main.java.core.driver;

import main.java.core.listener.LoggingListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class DriverFactory {

    public static WebDriver createBrowser(BrowserType browser) {
        WebDriver driver;
        switch (browser) {
            case FIREFOX -> driver = new FirefoxDriver();
            case SAFARI -> driver = new SafariDriver();
            case EDGE -> driver = new EdgeDriver();
            default -> driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        EventFiringDecorator<WebDriver> eventFiringDecorator = new EventFiringDecorator<>(new LoggingListener());

        return eventFiringDecorator.decorate(driver);
    }
}