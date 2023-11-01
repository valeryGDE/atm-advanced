package main.java.pagefactory.page;

import main.java.core.driver.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    protected WebDriver driver = WebDriverManager.getWebDriverInstance();

    protected AbstractPage() {
        PageFactory.initElements(driver, this);
    }

    protected void openPage(String url) {
        driver.get(url);
    }
}
