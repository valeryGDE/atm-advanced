package main.java.businesslogic.areas;

import com.google.inject.Inject;
import lombok.Getter;
import main.java.businesslogic.model.Account;
import main.java.core.logger.Log;
import main.java.core.properties.EnvProperty;
import main.java.core.properties.PropertyReader;
import main.java.pagefactory.service.LoginServiceBase;
import main.java.pagefactory.service.SideBarServiceBase;

@Getter
public class LoginBll {

    @Inject
    private LoginServiceBase loginService;
    @Inject
    private SideBarServiceBase sideBarService;

    public void logInEpam() {
        Log.info("Log in using personal Epam account");
        loginService.openPage(PropertyReader.getProperty(EnvProperty.REMOTE_BASE_URL.getKey()));
        loginService.clickLoginEpamButton();
    }

    public void logIn(Account account) {
        Log.info(String.format("Log in using account: '%s'", account.getEmail()));
        loginService.openPage(PropertyReader.getProperty(EnvProperty.LOCAL_BASE_URL.getKey()));
        loginService.fillPasswordInput(account.getPassword());
        loginService.fillEmailInput(account.getEmail());
        loginService.clickLoginButton();
        sideBarService.waitForFiltersButtonIsClickable();
    }
}