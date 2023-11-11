package main.java.pagefactory.page;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class SpinnerPage extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class,'spinningPreloader')]")
    private WebElement loader;

    public SpinnerPage(WebDriver driver) {
        super(driver);
    }
}