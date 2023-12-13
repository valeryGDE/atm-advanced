package test.ui.junit;

import main.java.ui.businesslogic.manager.AccountManager;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

class LoginTest extends BaseTest {

    @Test
    void loginDefaultUser() {
        loginBll.logIn(AccountManager.defaultAccount());
        loginBll.getSideBarService().waitPageIsShown();
        Assert.assertTrue(loginBll.getSideBarService().isPageShown(), "Check Sidebar is shown");
    }

    @Test
    void loginToRemoteReportPortal() {
        loginBll.logInEpam();
    }

    @Test
    void loginAdminUser() {
        loginBll.logIn(AccountManager.adminAccount());
        loginBll.getSideBarService().waitPageIsShown();
        Assert.assertTrue(loginBll.getSideBarService().isPageShown(), "Check Sidebar is shown");
    }
}