package main.java.ui.businesslogic.areas;

import com.google.inject.Inject;
import lombok.Getter;
import main.java.ui.businesslogic.model.Filter;
import main.java.ui.pagefactory.service.FiltersServiceBase;
import main.java.ui.pagefactory.service.LaunchesServiceBase;
import main.java.ui.pagefactory.service.CommonComponentsServiceBase;

@Getter
public class FiltersBll {

    @Inject
    private FiltersServiceBase filtersService;
    @Inject
    private LaunchesServiceBase launchesService;
    @Inject
    private CommonComponentsServiceBase spinnerService;

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
                spinnerService.waitForSpinnerIsNotShown();
            }
            spinnerService.waitForSpinnerIsNotShown();
            launchesService.addFilterConditions(filter);
            launchesService.waitForSaveButtonIsClickable();
            launchesService.clickSaveButton();
            launchesService.waitForInputIsShown();
            launchesService.typeFilterName(filter.getName());
            launchesService.clickAddButton();
            launchesService.waitForInputIsNotShown();
            spinnerService.waitForSpinnerIsNotShown();
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