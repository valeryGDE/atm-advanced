package main.java.businesslogic.areas;

import lombok.Getter;
import main.java.businesslogic.model.Account;
import main.java.core.logger.Log;
import main.java.core.properties.EnvProperty;
import main.java.core.properties.PropertyReader;
import main.java.pagefactory.service.LoginService;
import main.java.pagefactory.service.SideBarService;
import org.openqa.selenium.WebDriver;

@Getter
public class LoginBll {

    private final WebDriver driver;
    private final LoginService loginService;
    private final SideBarService sideBarService;

    public LoginBll(WebDriver driver) {
        this.driver = driver;
        loginService = new LoginService(driver);
        sideBarService = new SideBarService(driver);
    }

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