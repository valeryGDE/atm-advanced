package main.java.pagefactory.page;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class FiltersPage extends DefaultPage {

    @FindBy(xpath = "//button[normalize-space(.)='Add Filter']")
    private WebElement addFilterButton;

    @FindBy(xpath = "//div[@data-id]")
    private List<WebElement> filterRows;

    @FindBy(xpath = "//button[text()='Delete']")
    private WebElement acceptDeleteButton;

    public String getFilterRowName(WebElement webElement) {
        return webElement.getText().lines().findFirst().orElse("");
    }

    public WebElement getDeleteButtonByFilterName(String name) {
        return driver.findElement(By.xpath("//div[contains(@class,'deleteFilterButton') and ancestor::div[@data-id and contains(., '" + name + "')]]"));
    }
}