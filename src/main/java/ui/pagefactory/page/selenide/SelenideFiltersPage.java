package main.java.ui.pagefactory.page.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import main.java.ui.core.logger.Log;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class SelenideFiltersPage {

    public void clickAddFilterButton() {
        $(By.xpath("//button[normalize-space(.)='Add Filter']")).click();
    }

    public void clickAcceptDeleteButton() {
        $(By.xpath("//button[text()='Delete']")).click();
    }

    public void clickDeleteFilter(String filterName) {
        getDeleteButtonByFilterName(filterName).click();
    }

    public void switchDisplayToggle(String filterName, boolean switchOn) {
        var toggle = getDisplayToggleByFilterName(filterName);
        boolean isChecked = toggle.text().equals("ON");
        if (isChecked != switchOn) {
            toggle.click();
        }
    }

    public void waitFilterIsNotShown(SelenideElement filter) {
        $(filter).shouldNotBe(Condition.visible);
    }

    public boolean isFilterListContainsFilters(String... filterNames) {
        Log.info("Check that %s are shown in the filter list".formatted(Arrays.toString(filterNames)));
        return getFiltersNamesList().containsAll(Arrays.asList(filterNames));
    }

    public List<String> getFiltersNamesList() {
        return getFilterRows()
                .texts()
                .stream()
                .map(this::getFilterRowName)
                .toList();

    }

    public SelenideElement getFilterRowByName(String filterName) {
        return getFilterRows()
                .filterBy(text(filterName))
                .first();
    }

    private ElementsCollection getFilterRows() {
        return $$(By.xpath("//div[@data-id]"));
    }

    private String getFilterRowName(String rowText) {
        return rowText.lines().findFirst().orElse("");
    }

    private SelenideElement getDeleteButtonByFilterName(String name) {
        return $(By.xpath("//div[contains(@class,'deleteFilterButton') and ancestor::div[@data-id and contains(., '" + name + "')]]"));
    }

    private SelenideElement getDisplayToggleByFilterName(String name) {
        return $(By.xpath("//div[contains(@class,'displayFilter__switcher') and ancestor::div[@data-id and contains(., '" + name + "')]]"));
    }
}