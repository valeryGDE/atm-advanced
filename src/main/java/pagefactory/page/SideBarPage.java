package main.java.pagefactory.page;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class SideBarPage extends DefaultPage {

    @FindBy(xpath = "//a[contains(@href, 'filters')]")
    private WebElement filtersButton;

    @FindBy(xpath = "//div[contains(@class, 'sidebar-container')]")
    private WebElement sideBarContainer;
}