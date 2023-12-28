package main.java.ui.businesslogic.areas;

import com.google.inject.Inject;
import lombok.Getter;
import main.java.ui.businesslogic.model.Account;
import main.java.ui.core.logger.Log;
import main.java.ui.core.properties.EnvProperty;
import main.java.ui.core.properties.PropertyReader;
import main.java.ui.pagefactory.service.classic.CommonComponentsServiceBase;
import main.java.ui.pagefactory.service.classic.LoginServiceBase;
import main.java.ui.pagefactory.service.classic.SideBarServiceBase;

@Getter
public class LoginBll {

    @Inject
    private LoginServiceBase loginService;
    @Inject
    private CommonComponentsServiceBase commonComponentsService;
    @Inject
    private SideBarServiceBase sideBarService;

    public void logInEpam() {
        Log.info("Log in using personal Epam account");
        loginService.openPage(PropertyReader.getProperty(EnvProperty.REMOTE_BASE_URL.getKey()));
        loginService.clickLoginEpamButton();
    }

    public void logIn(Account account) {
        Log.info("Log in using account: '%s'".formatted(account.getEmail()));
        loginService.openPage(PropertyReader.getProperty(EnvProperty.LOCAL_BASE_URL.getKey()));
        loginService.fillPasswordInput(account.getPassword());
        loginService.fillEmailInput(account.getEmail());
        loginService.clickLoginButton();
        sideBarService.waitForFiltersButtonIsClickable();
        commonComponentsService.waitForNotificationIsNotShown();
    }
}