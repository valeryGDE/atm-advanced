package main.java.pagefactory.service;

import main.java.core.logger.Log;
import main.java.pagefactory.page.FiltersPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static main.java.pagefactory.page.AbstractPage.DEFAULT_TIMEOUT;

public class FiltersService extends AbstractService {

    private final FiltersPage filtersPage = new FiltersPage(driver);

    public FiltersService(WebDriver driver) {
        super(driver);
    }

    public void clickAddFilterButton() {
        filtersPage.getAddFilterButton().click();
    }

    public boolean isFilterListContainsFilters(String... filterNames) {
        Log.info(String.format("Check that %s are shown in the filter list", Arrays.toString(filterNames)));
        return getFiltersNamesList().containsAll(Arrays.asList(filterNames));
    }

    public void clickAcceptDeleteButton() {
        filtersPage.getAcceptDeleteButton().click();
    }

    public void clickDeleteFilter(String filterName) {
        filtersPage.getDeleteButtonByFilterName(filterName).click();
    }

    public void waitFiltersListIsShown() {
        filtersPage.waitForElement(ExpectedConditions.visibilityOf(filtersPage.getFilterRows().get(0)), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public void waitAddFilterButtonClickable() {
        filtersPage.waitForElement(ExpectedConditions.elementToBeClickable(filtersPage.getAddFilterButton()), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public void waitFilterIsNotShown(WebElement filter) {
        filtersPage.waitForElement(ExpectedConditions.invisibilityOf(filter), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public List<String> getFiltersNamesList() {
        return filtersPage.getFilterRows()
                .stream()
                .map(filtersPage::getFilterRowName)
                .toList();
    }

    public WebElement getFilterRowByName(String filterName) {
        return filtersPage.getFilterRows()
                .stream()
                .filter(row -> filtersPage.getFilterRowName(row).equals(filterName))
                .findFirst()
                .orElse(null);
    }
}