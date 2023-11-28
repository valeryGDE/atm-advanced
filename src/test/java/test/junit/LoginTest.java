package test.junit;

import main.java.businesslogic.manager.AccountManager;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class LoginTest extends BaseTest {

    @Test
    public void loginDefaultUser() {
        loginBll.logIn(AccountManager.defaultAccount());
        loginBll.getSideBarService().waitPageIsShown();
        Assert.assertTrue(loginBll.getSideBarService().isPageShown(), "Check Sidebar is shown");
    }

    @Test
    public void loginToRemoteReportPortal() {
        loginBll.logInEpam();
    }

    @Test
    public void loginAdminUser() {
        loginBll.logIn(AccountManager.adminAccount());
        loginBll.getSideBarService().waitPageIsShown();
        Assert.assertTrue(loginBll.getSideBarService().isPageShown(), "Check Sidebar is shown");
    }
}