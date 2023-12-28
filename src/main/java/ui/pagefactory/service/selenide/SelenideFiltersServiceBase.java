package main.java.ui.pagefactory.service.selenide;

import com.google.inject.Inject;
import main.java.ui.businesslogic.model.Filter;
import main.java.ui.pagefactory.page.selenide.SelenideCommonComponents;
import main.java.ui.pagefactory.page.selenide.SelenideFiltersPage;
import main.java.ui.pagefactory.page.selenide.SelenideLaunchesPage;

public class SelenideFiltersServiceBase {

    @Inject
    private SelenideFiltersPage selenideFiltersPage;
    @Inject
    private SelenideLaunchesPage selenideLaunchesPage;
    @Inject
    private SelenideCommonComponents selenideCommonComponents;

    public void createFilter(Filter... filters) {
        createFilter(true, filters);
    }

    public void createFilter(boolean isFromFiltersPage, Filter... filters) {
        for (int i = 0; i < filters.length; i++) {
            Filter filter = filters[i];
            if (i == 0 && isFromFiltersPage) {
                selenideFiltersPage.clickAddFilterButton();
            } else {
                selenideLaunchesPage.clickAddFilerButton();
            }
            selenideCommonComponents.waitForSpinnerIsNotShown();
            selenideLaunchesPage.addFilterConditions(filter);
            selenideLaunchesPage.clickSaveButton();
            selenideLaunchesPage.typeFilterName(filter.getName());
            selenideLaunchesPage.clickAddButton();
            selenideCommonComponents.waitForSpinnerIsNotShown();
        }
    }

    public void editFilterName(String newName) {
        selenideLaunchesPage.clickEditButton();
        selenideCommonComponents.waitForSpinnerIsNotShown();
        selenideLaunchesPage.typeFilterName(newName);
        selenideLaunchesPage.clickUpdateButton();
        selenideCommonComponents.waitForSpinnerIsNotShown();
    }

    public void cloneFilter(String newName) {
        selenideLaunchesPage.clickCloneButton();
        selenideCommonComponents.waitForSpinnerIsNotShown();
        selenideLaunchesPage.clickSaveButton();
        selenideLaunchesPage.typeFilterName(newName);
        selenideLaunchesPage.clickAddButton();
        selenideCommonComponents.waitForSpinnerIsNotShown();
    }

    public void switchDisplayToggle(String name, boolean switchOn) {
        selenideFiltersPage.switchDisplayToggle(name, switchOn);
    }

    public void removeFilter(String name) {
        var filterRow = selenideFiltersPage.getFilterRowByName(name);
        selenideFiltersPage.clickDeleteFilter(name);
        selenideFiltersPage.clickAcceptDeleteButton();
        selenideFiltersPage.waitFilterIsNotShown(filterRow);
    }
}
