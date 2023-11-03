package test;

import main.java.businesslogic.manager.AccountManager;
import main.java.pagefactory.service.LoginService;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginDefaultUser() {
        LoginService loginService = new LoginService(driver);
        loginService.logIn(AccountManager.defaultAccount());
    }

    @Test
    public void loginToRemoteReportPortal() {
        LoginService loginService = new LoginService(driver);
        loginService.logInEpam();
    }

    @Test
    public void loginAdminUser() {
        LoginService loginService = new LoginService(driver);
        loginService.logIn(AccountManager.adminAccount());
    }
}
