package main.java.pagefactory.service;

import main.java.core.logger.Log;
import main.java.pagefactory.page.FiltersPage;
import main.java.pagefactory.pagebase.AbstractBaseService;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static main.java.pagefactory.page.AbstractPage.DEFAULT_TIMEOUT;

public class FiltersServiceBase extends AbstractBaseService<FiltersPage> {

    @Override
    public boolean isPageShown() {
        return getPage().getAddFilterButton().isDisplayed();
    }

    public void clickAddFilterButton() {
        getPage().getAddFilterButton().click();
    }

    public boolean isFilterListContainsFilters(String... filterNames) {
        Log.info(String.format("Check that %s are shown in the filter list", Arrays.toString(filterNames)));
        return getFiltersNamesList().containsAll(Arrays.asList(filterNames));
    }

    public void clickAcceptDeleteButton() {
        getPage().getAcceptDeleteButton().click();
    }

    public void clickDeleteFilter(String filterName) {
        getPage().getDeleteButtonByFilterName(filterName).click();
    }

    public void waitFiltersListIsShown() {
        getPage().waitFor(ExpectedConditions.visibilityOf(getPage().getFilterRows().get(0)), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public void waitAddFilterButtonClickable() {
        getPage().waitFor(ExpectedConditions.elementToBeClickable(getPage().getAddFilterButton()), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public void waitFilterIsNotShown(WebElement filter) {
        getPage().waitFor(ExpectedConditions.invisibilityOf(filter), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public List<String> getFiltersNamesList() {
        return getPage().getFilterRows()
                .stream()
                .map(getPage()::getFilterRowName)
                .toList();
    }

    public WebElement getFilterRowByName(String filterName) {
        return getPage().getFilterRows()
                .stream()
                .filter(row -> getPage().getFilterRowName(row).equals(filterName))
                .findFirst()
                .orElse(null);
    }
}