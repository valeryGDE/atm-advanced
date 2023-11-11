package main.java.pagefactory.page;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class LaunchesPage extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class,'entitiesGroup__entity-item')]")
    private List<WebElement> filterEntities;

    @FindBy(xpath = "//button[@title='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[contains(@class,'CodeMirror-wrap')]")
    private WebElement descriptionInput;

    @FindBy(xpath = "//form//input")
    private WebElement nameInput;

    @FindBy(xpath = "//button[text()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//button[text()='Cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//button[normalize-space(.)='Add filter']")
    private WebElement addFilterButton;

    @FindBy(xpath = "//div[text()='More']")
    private WebElement moreButton;

    @FindBy(xpath = "//div[contains(@class, 'entity-item')]//span")
    private List<WebElement> conditionCheckboxes;

    public LaunchesPage(WebDriver driver) {
        super(driver);
    }

    public String getConditionEntityName(WebElement webElement) {
        return webElement.getText().lines().findFirst().orElse("");
    }

    public WebElement getInputFieldByConditionName(String name) {
        return driver.findElement(By.xpath("//input[ancestor::div[contains(@class,'entitiesGroup__entity-item') and contains(., '" + name + "')]]"));
    }
}
