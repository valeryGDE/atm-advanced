package test.cucumber.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import main.java.ui.businesslogic.areas.FiltersBll;
import main.java.ui.businesslogic.areas.LoginBll;
import main.java.ui.businesslogic.model.Filter;
import main.java.ui.enums.Condition;
import org.testng.Assert;
import test.cucumber.context.TestContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FiltersSteps {

    TestContext testContext;
    FiltersBll filtersBll;
    LoginBll loginBll;

    public FiltersSteps(TestContext testContext) {
        this.testContext = testContext;
        filtersBll = testContext.getFiltersBll();
        loginBll = testContext.getLoginBll();
    }

    @Given("the user navigates to Filters tab")
    public void navigateToFiltersTab() {
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
    }

    @When("the user creates new filter with {string} and {string} and {string}")
    public void createFilter(String condition, String conditionName, String filterName) {
        filtersBll.createFilter(new Filter(Map.of(Condition.valueOf(condition), conditionName), filterName));
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        loginBll.getSideBarService().waitForFiltersButtonIsClickable();
    }

    @When("the user creates new filter with {string} and {string} and {string} from Launches page")
    public void createFilterFromLaunchesPage(String condition, String conditionName, String filterName) {
        filtersBll.createFilter(false, new Filter(Map.of(Condition.valueOf(condition), conditionName), filterName));
    }

    @When("the user creates new filters")
    public void createFilters(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        List<Filter> filtersList = new ArrayList<>();
        for (Map<String, String> form : data) {
            String condition = form.get("Condition");
            String conditionName = form.get("Condition name");
            String filterName = form.get("Filter name");
            filtersList.add(new Filter(Map.of(Condition.valueOf(condition), conditionName), filterName));
        }
        filtersBll.createFilter(filtersList.toArray(new Filter[0]));
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        loginBll.getSideBarService().waitForFiltersButtonIsClickable();
    }

    @When("the user creates new filter")
    public void createFilter(DataTable list) {
        List<String> details = list.asList(String.class);
        String condition = details.get(0);
        String conditionName = details.get(1);
        String filterName = details.get(2);
        filtersBll.createFilter(new Filter(Map.of(Condition.valueOf(condition), conditionName), filterName));
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        loginBll.getSideBarService().waitForFiltersButtonIsClickable();

    }

    @Then("filter with name {string} is displayed on Filters page")
    public void verifyFilterIsDisplayedOnFiltersPage(String filterName) {
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        Assert.assertTrue(filtersBll.getFiltersService().isFilterListContainsFilters(filterName),
                String.format("Filter with name %s is shown", filterName));

    }

    @But("filter with name {string} is not displayed on Filters page")
    public void verifyFilterIsNotDisplayedOnFiltersPage(String filterName) {
        loginBll.getSideBarService().clickFiltersButton();
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        Assert.assertFalse(filtersBll.getFiltersService().isFilterListContainsFilters(filterName),
                String.format("Filter with name %s is shown", filterName));
    }

    @But("filter cannot be saved")
    public void verifyFilterCannotBeSaved() {
        Assert.assertTrue(filtersBll.getLaunchesService().isDescriptionInputShown(), "Description input is shown");
        filtersBll.getLaunchesService().clickCancelButton();
        loginBll.getSideBarService().waitForFiltersButtonIsClickable();
    }

    @Then("there should be only one filter with the name {string} on the Filters page")
    public void verifyFilterIsOnlyOneOnFiltersPage(String filterName) {
        Assert.assertTrue(filtersBll.getFiltersService().isFilterListContainsOnlyOneEntryWithName(filterName),
                String.format("Filter with name %s is only one", filterName));
    }

    @When("the user deletes the filter with name {string}")
    public void deleteFilter(String filterName) {
        filtersBll.removeFilter(filterName);
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
    }

    @When("the user deletes the filter")
    public void deletesFilter(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        List<Filter> filtersList = new ArrayList<>();
        for (Map<String, String> form : data) {
            String condition = form.get("Condition");
            String conditionName = form.get("Condition name");
            String filterName = form.get("Filter name");
            filtersList.add(new Filter(Map.of(Condition.valueOf(condition), conditionName), filterName));
        }
        filtersBll.createFilter(filtersList.toArray(new Filter[0]));
        filtersBll.getSpinnerService().waitForSpinnerIsNotShown();
        loginBll.getSideBarService().waitForFiltersButtonIsClickable();
    }
}
