package test.junit;

import main.java.businesslogic.areas.LoginBll;
import main.java.businesslogic.manager.AccountManager;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    LoginBll loginBll = new LoginBll(getDriver());

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