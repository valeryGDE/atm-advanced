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

    public void createFilter(Filter filter) {
        filtersService.clickAddFilterButton();
        launchesService.addFilterConditions(filter);
        launchesService.clickSaveButton();
        launchesService.typeFilterName(filter.getName());
        launchesService.clickAddButton();
        launchesService.waitForInputIsNotShown();
    }

    public void removeFilter(Filter filter) {
        filtersService.clickDeleteFilter(filter.getName());
        filtersService.clickAcceptDeleteButton();
    }

    public void removeFilter(String name) {
        filtersService.clickDeleteFilter(name);
        filtersService.clickAcceptDeleteButton();
    }

    public void removeAllFilters() {
        filtersService.performActionOnFilters(filterRow -> {
            filterRow.click();
            filtersService.clickAcceptDeleteButton();
            filtersService.waitFilterIsNotShown(filterRow);
        });
    }
}