package main.java.core.driver;

import main.java.core.element.WrappedElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CustomChromeDriver extends ChromeDriver {
    public CustomChromeDriver(ChromeOptions options) {
        super(options);
    }

    public CustomChromeDriver() {
        super();
    }

    @Override
    public WrappedElement findElement(By by) {
        WebElement webElement = super.findElement(by);
        return new WrappedElement(webElement);
    }
}
