package main.java.pagefactory.service;

import main.java.businesslogic.model.Filter;
import main.java.core.logger.Log;
import main.java.enums.Condition;
import main.java.pagefactory.page.LaunchesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

import static main.java.pagefactory.page.AbstractPage.DEFAULT_TIMEOUT;

public class LaunchesService extends AbstractService {

    private final LaunchesPage launchesPage = new LaunchesPage(driver);

    public LaunchesService(WebDriver driver) {
        super(driver);
    }

    public boolean isDescriptionInputShown() {
        Log.info("Check that Description Input is shown");
        return launchesPage.getDescriptionInput().isDisplayed();
    }

    public List<String> getCheckboxesNames() {
        return launchesPage.getConditionCheckboxes().stream().map(WebElement::getText).toList();
    }

    public void clickAddButton() {
        launchesPage.getAddButton().click();
    }

    public void clickCancelButton() {
        launchesPage.getCancelButton().click();
    }

    public void clickAddFilerButton() {
        launchesPage.getAddFilterButton().click();
    }

    public void clickSaveButton() {
        launchesPage.getSaveButton().click();
    }

    public void typeFilterName(String text) {
        launchesPage.getNameInput().clear();
        launchesPage.getNameInput().sendKeys(text);
    }

    public void clickMoreButton() {
        launchesPage.getMoreButton().click();
    }

    public void typeDescriptionText(String text) {
        new Actions(driver)
                .sendKeys(launchesPage.getDescriptionInput(), text)
                .perform();
    }

    public void addFilterConditions(Filter filter) {
        filter.getConditionStringList().forEach(conditionMap ->
                conditionMap.forEach((condition, value) -> {
                            getConditionWithName(condition);
                            launchesPage.getInputFieldByConditionName(condition.getKey()).sendKeys(value);
                        }
                )
        );
    }

    public void waitForInputIsNotShown() {
        launchesPage.waitForElement(ExpectedConditions.invisibilityOf(launchesPage.getDescriptionInput()), Duration.ofSeconds(DEFAULT_TIMEOUT));

    }

    private void addCondition(Condition condition) {
        clickMoreButton();
        launchesPage.getConditionCheckboxes().stream()
                .filter(checkbox -> checkbox.getText().equals(condition.getKey()))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    private WebElement getConditionWithName(Condition condition) {
        WebElement entity = findConditionEntity(condition);
        if (entity == null) {
            addCondition(condition);
            entity = findConditionEntity(condition);
        }
        return entity;
    }

    private WebElement findConditionEntity(Condition condition) {
        return launchesPage.getFilterEntities()
                .stream()
                .filter(entity -> launchesPage.getConditionEntityName(entity).equals(condition.getKey()))
                .findFirst()
                .orElse(null);
    }
}