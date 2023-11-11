package test.testng;

import main.java.businesslogic.areas.FiltersBll;
import main.java.businesslogic.areas.LoginBll;
import main.java.businesslogic.manager.AccountManager;
import main.java.businesslogic.model.Filter;
import main.java.enums.Condition;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Map;

public class FiltersTest extends BaseTest {

    @Test(dataProvider = "getFiltersNames")
    public void createFilter(String name) {
        FiltersBll filtersBll = new FiltersBll(getDriver());
        LoginBll loginBll = new LoginBll(getDriver());
        Filter filter = new Filter(Collections.singletonList(Map.of(
                Condition.DESCRIPTION, name)), name);
        loginBll.logIn(AccountManager.defaultAccount());
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        filtersBll.createFilter(filter);
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        loginBll.getSideBarService().waitForFiltersButtonIsClickable();
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        Assert.assertTrue(filtersBll.getFiltersService().isFilterNameDisplayed(name), String.format("Filter with name %s is shown", name));
        filtersBll.removeFilter(name);
    }

    @Test
    public void deleteFilter() {
        FiltersBll filtersBll = new FiltersBll(getDriver());
        LoginBll loginBll = new LoginBll(getDriver());
        loginBll.logIn(AccountManager.defaultAccount());
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        filtersBll.removeFilter("Hello");
    }

    @Test
    public void deleteAllFilters() {
        FiltersBll filtersBll = new FiltersBll(getDriver());
        LoginBll loginBll = new LoginBll(getDriver());
        loginBll.logIn(AccountManager.defaultAccount());
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.removeAllFilters();
    }
}
