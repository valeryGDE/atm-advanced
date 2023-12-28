package test.selenide;

import main.java.ui.businesslogic.manager.AccountManager;
import main.java.ui.businesslogic.model.Filter;
import main.java.ui.enums.FilterCondition;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class SelenideFiltersTest extends SelenideBaseTest {

    @Test
    public void createFilter() {
        var name = "Create";
        Filter filter = new Filter(Map.of(FilterCondition.LAUNCH_NAME, name), name);
        selenideLoginServiceBase.logIn(AccountManager.defaultAccount());
        selenideSideBarPage.clickFiltersButton();
        selenideCommonComponents.waitForSpinnerIsNotShown();
        selenideFiltersServiceBase.createFilter(filter);
        selenideCommonComponents.waitForNotificationIsNotShown();
        selenideSideBarPage.clickFiltersButton();
        selenideCommonComponents.waitForSpinnerIsNotShown();
        Assert.assertTrue(selenideFiltersPage.isFilterListContainsFilters(name), ("Filter with name %s is shown".formatted(name)));
    }

    @Test
    public void deleteFilter() {
        var name = "Delete";
        Filter filter = new Filter(Map.of(FilterCondition.DESCRIPTION, name), name);
        selenideLoginServiceBase.logIn(AccountManager.defaultAccount());
        selenideSideBarPage.clickFiltersButton();
        selenideCommonComponents.waitForSpinnerIsNotShown();
        selenideFiltersServiceBase.createFilter(filter);
        selenideCommonComponents.waitForSpinnerIsNotShown();
        selenideSideBarPage.clickFiltersButton();
        selenideCommonComponents.waitForSpinnerIsNotShown();
        selenideFiltersServiceBase.removeFilter(name);
        selenideCommonComponents.waitForSpinnerIsNotShown();
        Assert.assertFalse(selenideFiltersPage.isFilterListContainsFilters(name), ("Filter with name %s is shown".formatted(name)));
    }

    @Test
    public void editFilter() {
        var name = "Filter";
        Filter filter = new Filter(Map.of(FilterCondition.LAUNCH_NAME, name), name);
        selenideLoginServiceBase.logIn(AccountManager.defaultAccount());
        selenideSideBarPage.clickFiltersButton();
        selenideCommonComponents.waitForSpinnerIsNotShown();
        selenideFiltersServiceBase.createFilter(filter);
        selenideCommonComponents.waitForSpinnerIsNotShown();
        var newName = "Edited";
        selenideFiltersServiceBase.editFilterName(newName);
        selenideSideBarPage.clickFiltersButton();
        selenideCommonComponents.waitForSpinnerIsNotShown();
        Assert.assertTrue(selenideFiltersPage.isFilterListContainsFilters(newName), ("Filter with name %s is shown".formatted(newName)));
    }

    @Test
    public void cloneFilter() {
        var name = "Monster";
        Filter filter = new Filter(Map.of(FilterCondition.LAUNCH_NAME, name), name);
        selenideLoginServiceBase.logIn(AccountManager.defaultAccount());
        selenideSideBarPage.clickFiltersButton();
        selenideCommonComponents.waitForSpinnerIsNotShown();
        selenideFiltersServiceBase.createFilter(filter);
        selenideCommonComponents.waitForSpinnerIsNotShown();
        var newName = "Cloned";
        selenideFiltersServiceBase.cloneFilter(newName);
        selenideSideBarPage.clickFiltersButton();
        selenideCommonComponents.waitForSpinnerIsNotShown();
        Assert.assertTrue(selenideFiltersPage.isFilterListContainsFilters(name, newName), ("Filter with names: %s, %s are shown".formatted(name, newName)));
    }

    @Test
    public void disableDisplayingFilter() {
        var name = "DisplayToggle";
        Filter filter = new Filter(Map.of(FilterCondition.LAUNCH_NAME, name), name);
        selenideLoginServiceBase.logIn(AccountManager.defaultAccount());
        selenideSideBarPage.clickFiltersButton();
        selenideCommonComponents.waitForSpinnerIsNotShown();
        selenideFiltersServiceBase.createFilter(filter);
        selenideLaunchesPage.waitDescriptionInputIsNotShown();
        Assert.assertTrue(selenideLaunchesPage.isFilterDisplayed(name), ("Filter with name %s is displayed".formatted(name)));
        selenideSideBarPage.clickFiltersButton();
        selenideFiltersServiceBase.switchDisplayToggle(name, false);
        selenideCommonComponents.waitForSpinnerIsNotShown();
        selenideSideBarPage.clickLaunchesButton();
        selenideCommonComponents.waitForSpinnerIsNotShown();
        Assert.assertFalse(selenideLaunchesPage.isFilterDisplayed(name), ("Filter with name %s is displayed".formatted(name)));
    }
}