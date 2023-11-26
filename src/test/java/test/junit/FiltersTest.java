package test.junit;

import main.java.businesslogic.manager.AccountManager;
import main.java.businesslogic.model.Filter;
import main.java.enums.Condition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.testng.Assert;
import test.testng.BaseTest;

import java.util.Map;

public class FiltersTest extends BaseTest {

    @ParameterizedTest
    @MethodSource("getFiltersNamesForCreation")
    public void createFilter(String name) {
        Filter filter = new Filter(Map.of(Condition.LAUNCH_NAME, name), name);
        loginBll.logIn(AccountManager.defaultAccount());
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        filtersBll.createFilter(filter);
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        loginBll.getSideBarService().waitForFiltersButtonIsClickable();
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        filtersBll.getFiltersService().waitPageIsShown();
        Assert.assertTrue(filtersBll.getFiltersService().isFilterListContainsFilters(name), String.format("Filter with name %s is shown", name));
    }

    @ParameterizedTest
    @MethodSource("getFiltersList")
    public void deleteFilter(String name) {
        Filter filter = new Filter(Map.of(Condition.DESCRIPTION, name), name);
        loginBll.logIn(AccountManager.defaultAccount());
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        filtersBll.createFilter(filter);
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        loginBll.getSideBarService().waitForFiltersButtonIsClickable();
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        filtersBll.getFiltersService().waitPageIsShown();
        filtersBll.removeFilter(name);
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        Assert.assertFalse(filtersBll.getFiltersService().isFilterListContainsFilters(name), String.format("Filter with name %s is shown", name));
    }

    @ParameterizedTest
    @MethodSource("getFiltersNamesForSaving")
    public void impossibleToSaveFilterWithTheSameName(String name) {
        Filter filter = new Filter(Map.of(Condition.LAUNCH_NAME, name), name);
        loginBll.logIn(AccountManager.defaultAccount());
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        filtersBll.createFilter(filter);
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        Filter filterTwo = new Filter(Map.of(Condition.DESCRIPTION, name), name);
        filtersBll.createFilter(false, filterTwo);
        Assert.assertTrue(filtersBll.getLaunchesService().isDescriptionInputShown(), "Description input is shown");
        filtersBll.getLaunchesService().clickCancelButton();
        loginBll.getSideBarService().waitForFiltersButtonIsClickable();
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        filtersBll.getFiltersService().waitPageIsShown();
        Assert.assertTrue(filtersBll.getFiltersService().isFilterListContainsOnlyOneEntryWithName(name), String.format("Filter with name %s is only one", name));
    }
}
