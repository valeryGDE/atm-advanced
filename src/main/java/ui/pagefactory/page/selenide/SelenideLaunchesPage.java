package main.java.ui.pagefactory.page.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import main.java.ui.businesslogic.model.Filter;
import main.java.ui.core.logger.Log;
import main.java.ui.enums.FilterCondition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class SelenideLaunchesPage extends AbstractSelenidePage {

    @Override
    public boolean isPageShown() {
        return $(By.xpath("//button[text()='Add']")).isDisplayed();
    }

    public SelenideElement getInputFieldByConditionName(String name) {
        return $(By.xpath("//div[contains(@class,'entitiesGroup__entity-item') and contains(., '" + name + "')]//input"));
    }

    public void clickAddButton() {
        $(By.xpath("//button[text()='Add']")).click();
    }

    public void clickEditButton() {
        $("button[title='Edit']").click();
    }

    public void clickCloneButton() {
        $("button[title='Clone']").click();
    }

    public void clickUpdateButton() {
        $(By.xpath("//button[text()='Update']")).click();
    }

    public void clickAddFilerButton() {
        $(By.xpath("//button[normalize-space(.)='Add filter']")).click();
    }

    public void clickSaveButton() {
        $(By.xpath("//button[@title='Save']")).click();
    }

    public void clickMoreButton() {
        $(By.xpath("//div[text()='More']")).click();
    }

    public boolean isFilterDisplayed(String name) {
        return $$("div[class*='filterList__item']").texts().contains(name);
    }

    public void waitDescriptionInputIsNotShown() {
        $(By.xpath("//div[contains(@class,'CodeMirror-wrap')]")).shouldNotBe(Condition.visible);
    }

    public void addFilterConditions(Filter filter) {
        filter.getConditionStringMap().forEach((condition, value) -> {
            getConditionWithName(condition);
            SelenideElement inputField = getInputFieldByConditionName(condition.getKey());
            if (inputField != null) {
                inputField.setValue(value);
            } else {
                Log.error("Input field not found for condition: %s".formatted(condition.getKey()));
            }
        });
    }

    public void typeFilterName(String text) {
        $(By.xpath("//form//input")).setValue(text);
    }

    private void getConditionWithName(FilterCondition filterCondition) {
        SelenideElement entity = findConditionEntity(filterCondition);
        if (entity == null) {
            addCondition(filterCondition);
        }
    }

    private void addCondition(FilterCondition filterCondition) {
        clickMoreButton();
        SelenideElement checkbox = $$(By.xpath("//div[contains(@class, 'entity-item')]//span"))
                .filter(text(filterCondition.getKey()))
                .first();
        checkbox.click();

    }

    private SelenideElement findConditionEntity(FilterCondition filterCondition) {
        return $$(By.xpath("//div[contains(@class,'entitiesGroup__entity-item')]"))
                .asFixedIterable()
                .stream()
                .filter(entity -> getConditionEntityName(entity).equals(filterCondition.getKey()))
                .findFirst()
                .orElse(null);
    }

    private String getConditionEntityName(SelenideElement element) {
        return element.text().lines().findFirst().orElse("");
    }
}