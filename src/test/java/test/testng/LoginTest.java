package test.testng;

import main.java.businesslogic.manager.AccountManager;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginDefaultUser() {
        loginBll.logIn(AccountManager.defaultAccount());
    }

    @Test
    public void loginToRemoteReportPortal() {
        loginBll.logInEpam();
    }

    @Test
    public void loginAdminUser() {
        loginBll.logIn(AccountManager.adminAccount());
    }
}