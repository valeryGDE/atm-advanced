package main.java.pagefactory.service;

import main.java.businesslogic.model.Filter;
import main.java.core.logger.Log;
import main.java.enums.Condition;
import main.java.pagefactory.page.LaunchesPage;
import main.java.pagefactory.pagebase.AbstractBaseService;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

import static main.java.pagefactory.page.AbstractPage.DEFAULT_TIMEOUT;

public class LaunchesServiceBase extends AbstractBaseService<LaunchesPage> {

    @Override
    public boolean isPageShown() {
        return getPage().getAddFilterButton().isDisplayed()
                && getPage().getMoreButton().isDisplayed();
    }

    public boolean isDescriptionInputShown() {
        Log.info("Check that Description Input is shown");
        return getPage().getDescriptionInput().isDisplayed();
    }

    public List<String> getCheckboxesNames() {
        return getPage().getConditionCheckboxes().stream().map(WebElement::getText).toList();
    }

    public void clickAddButton() {
        getPage().getAddButton().click();
    }

    public void clickCancelButton() {
        getPage().getCancelButton().click();
    }

    public void clickAddFilerButton() {
        getPage().getAddFilterButton().click();
    }

    public void clickSaveButton() {
        getPage().getSaveButton().click();
    }

    public void typeFilterName(String text) {
        getPage().getNameInput().click();
        getPage().getNameInput().clear();
        getPage().getNameInput().sendKeys(text);
    }

    public void clickMoreButton() {
        getPage().getMoreButton().click();
    }

    public void addFilterConditions(Filter filter) {
        filter.getConditionStringMap().forEach((condition, value) -> {
            getConditionWithName(condition);
            getPage().getInputFieldByConditionName(condition.getKey()).sendKeys(value);
        });
    }

    public void waitForSaveButtonIsClickable() {
        getPage().waitFor(ExpectedConditions.elementToBeClickable(getPage().getSaveButton()), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public void waitForInputIsShown() {
        getPage().waitFor(ExpectedConditions.visibilityOf(getPage().getDescriptionInput()), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public void waitForInputIsNotShown() {
        getPage().waitFor(ExpectedConditions.invisibilityOf(getPage().getDescriptionInput()), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }


    private void addCondition(Condition condition) {
        clickMoreButton();
        getPage().getConditionCheckboxes().stream()
                .filter(checkbox -> checkbox.getText().equals(condition.getKey()))
                .findFirst()
                .ifPresent(WebElement::click);

    }

    private void getConditionWithName(Condition condition) {
        WebElement entity = findConditionEntity(condition);
        if (entity == null) {
            addCondition(condition);
        }
    }

    private WebElement findConditionEntity(Condition condition) {
        return getPage().getFilterEntities()
                .stream()
                .filter(entity -> getPage().getConditionEntityName(entity).equals(condition.getKey()))
                .findFirst()
                .orElse(null);
    }
}