package test.testng;

import main.java.businesslogic.areas.FiltersBll;
import main.java.businesslogic.areas.LoginBll;
import main.java.businesslogic.manager.AccountManager;
import main.java.businesslogic.model.Filter;
import main.java.enums.Condition;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FiltersTest extends BaseTest {

    @Test(dataProvider = "getFiltersNames")
    public void createFilter(String name) {
        Filter filter = new Filter(Collections.singletonList(Map.of(Condition.LAUNCH_NAME, name)), name);
        FiltersBll filtersBll = new FiltersBll(getDriver());
        LoginBll loginBll = new LoginBll(getDriver());
        loginBll.logIn(AccountManager.defaultAccount());
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        filtersBll.createFilter(filter);
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        loginBll.getSideBarService().waitForFiltersButtonIsClickable();
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        Assert.assertTrue(filtersBll.getFiltersService().isFilterListContainsFilters(name), String.format("Filter with name %s is shown", name));
        filtersBll.removeFilter(name);
    }

    @Test(dataProvider = "getFiltersList")
    public void deleteFilter(String nameOne, String nameTwo) {
        List<Filter> filtersList = Arrays.asList(
                new Filter(Collections.singletonList(Map.of(Condition.LAUNCH_NAME, nameOne)), nameOne),
                new Filter(Collections.singletonList(Map.of(Condition.LAUNCH_NAME, nameTwo)), nameTwo)
        );
        FiltersBll filtersBll = new FiltersBll(getDriver());
        LoginBll loginBll = new LoginBll(getDriver());
        loginBll.logIn(AccountManager.defaultAccount());
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        filtersBll.createFilter(filtersList.toArray(new Filter[0]));
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        loginBll.getSideBarService().waitForFiltersButtonIsClickable();
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        filtersBll.removeFilter(nameTwo);
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        Assert.assertTrue(filtersBll.getFiltersService().isFilterListContainsFilters(nameOne), String.format("Filter with name %s is shown", nameOne));
        filtersBll.removeFilter(nameOne);
    }

    @Test(dataProvider = "getFiltersNames")
    public void impossibleToSaveFilterWithTheSameName(String name) {
        FiltersBll filtersBll = new FiltersBll(getDriver());
        LoginBll loginBll = new LoginBll(getDriver());
        loginBll.logIn(AccountManager.defaultAccount());
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        filtersBll.createFilter(new Filter(Collections.singletonList(Map.of(Condition.LAUNCH_NAME, name)), name));
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        filtersBll.createFilter(false, new Filter(Collections.singletonList(Map.of(Condition.LAUNCH_NAME, name)), name));
        Assert.assertTrue(filtersBll.getLaunchesService().isDescriptionInputShown(), "Description input is shown");
        filtersBll.getLaunchesService().clickCancelButton();
        loginBll.getSideBarService().waitForFiltersButtonIsClickable();
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        filtersBll.removeFilter(name);
    }
}
