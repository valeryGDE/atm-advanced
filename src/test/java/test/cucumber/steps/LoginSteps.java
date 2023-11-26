package test.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.java.businesslogic.areas.LoginBll;
import main.java.businesslogic.manager.AccountManager;
import main.java.core.properties.EnvProperty;
import main.java.core.properties.PropertyReader;
import org.testng.Assert;
import test.cucumber.context.TestContext;

public class LoginSteps {
    TestContext testContext;
    LoginBll loginBll;

    public LoginSteps(TestContext testContext) {
        this.testContext = testContext;
        loginBll = testContext.getLoginBll();
    }

    @Given("the user navigates to Report Portal")
    public void navigateToReportPortal() {
        loginBll.getLoginService().openPage(PropertyReader.getProperty(EnvProperty.LOCAL_BASE_URL.getKey()));
    }

    @When("the user enters Default account login and password")
    public void enterDefaultLoginAndPassword() {
        loginBll.getLoginService().fillPasswordInput(AccountManager.defaultAccount().getPassword());
        loginBll.getLoginService().fillEmailInput(AccountManager.defaultAccount().getEmail());
    }

    @When("the user enters Admin account login and password")
    public void entersAdminLoginAndPassword() {
        loginBll.getLoginService().fillPasswordInput(AccountManager.adminAccount().getPassword());
        loginBll.getLoginService().fillEmailInput(AccountManager.adminAccount().getEmail());
    }

    @And("the user clicks the Login button")
    public void clickLoginButton() {
        loginBll.getLoginService().clickLoginButton();
    }

    @Then("login should be successful")
    public void verifyLoginIsSuccessful() {
        loginBll.getSideBarService().waitPageIsShown();
        Assert.assertTrue(loginBll.getSideBarService().isPageShown(), "Check Sidebar is shown");
    }
}
