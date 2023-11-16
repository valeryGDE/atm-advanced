package main.java.pagefactory.page;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class SideBarPage extends AbstractPage {

    @FindBy(xpath = "//a[contains(@href, 'filters')]")
    private WebElement filtersButton;

    public SideBarPage(WebDriver driver) {
        super(driver);
    }
}