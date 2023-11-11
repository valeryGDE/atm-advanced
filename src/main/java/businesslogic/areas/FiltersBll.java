package main.java.businesslogic.areas;

import lombok.Data;
import main.java.businesslogic.model.Filter;
import main.java.pagefactory.service.FiltersService;
import main.java.pagefactory.service.LaunchesService;
import main.java.pagefactory.service.SpinnerService;
import org.openqa.selenium.WebDriver;

@Data
public class FiltersBll {

    private WebDriver driver;

    private FiltersService filtersService;
    private LaunchesService launchesService;
    private SpinnerService spinnerService;

    public FiltersBll(WebDriver driver) {
        this.driver = driver;
        filtersService = new FiltersService(driver);
        launchesService = new LaunchesService(driver);
        spinnerService = new SpinnerService(driver);
    }

    public void createFilter(Filter... filters) {
        createFilter(true, filters);
    }

    public void createFilter(boolean isFromFiltersPage, Filter... filters) {
        for (int i = 0; i < filters.length; i++) {
            Filter filter = filters[i];
            if (i == 0 && isFromFiltersPage) {
                filtersService.clickAddFilterButton();
            } else {
                launchesService.clickAddFilerButton();
            }
            launchesService.addFilterConditions(filter);
            launchesService.clickSaveButton();
            launchesService.typeFilterName(filter.getName());
            launchesService.clickAddButton();
            launchesService.waitForInputIsNotShown();
        }
    }

    public void removeFilters(String... filterNames) {
        for (String filterName : filterNames) {
            removeFilter(filterName);
        }
    }

    public void removeFilter(String name) {
        var filterRow = filtersService.getFilterRowByName(name);
        filtersService.clickDeleteFilter(name);
        filtersService.clickAcceptDeleteButton();
        filtersService.waitFilterIsNotShown(filterRow);
    }

    public void removeAllFilters() {
        for (String filterName : filtersService.getFiltersNamesList()) {
            removeFilter(filterName);
        }
    }
}