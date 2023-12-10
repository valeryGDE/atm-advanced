package test.ui.testng;

import main.java.ui.businesslogic.manager.AccountManager;
import org.testng.Assert;
import org.testng.annotations.Test;

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