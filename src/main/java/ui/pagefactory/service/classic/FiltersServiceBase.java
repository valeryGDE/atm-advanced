package main.java.ui.pagefactory.service.classic;

import main.java.ui.core.logger.Log;
import main.java.ui.pagefactory.page.classic.FiltersPage;
import main.java.ui.pagefactory.pagebase.AbstractBaseService;
import main.java.ui.pagefactory.page.classic.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class FiltersServiceBase extends AbstractBaseService<FiltersPage> {

    @Override
    public boolean isPageShown() {
        return getPage().getAddFilterButton().isDisplayed();
    }

    public void clickAddFilterButton() {
        getPage().getAddFilterButton().click();
    }

    public boolean isFilterListContainsFilters(String... filterNames) {
        Log.info("Check that %s are shown in the filter list".formatted(Arrays.toString(filterNames)));
        return getFiltersNamesList().containsAll(Arrays.asList(filterNames));
    }

    public boolean isFilterListContainsOnlyOneEntryWithName(String filterName) {
        Log.info("Check that filter list contains only one element with name %s".formatted(filterName));
        return getFiltersNamesList().stream().filter(name -> name.equals(filterName)).count() == 1;
    }

    public void clickAcceptDeleteButton() {
        getPage().getAcceptDeleteButton().click();
    }

    public void clickDeleteFilter(String filterName) {
        getPage().getDeleteButtonByFilterName(filterName).click();
    }

    public void waitFilterIsNotShown(WebElement filter) {
        getPage().waitFor(ExpectedConditions.invisibilityOf(filter), Duration.ofSeconds(AbstractPage.DEFAULT_TIMEOUT));
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