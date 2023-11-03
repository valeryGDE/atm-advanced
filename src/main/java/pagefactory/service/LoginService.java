package main.java.pagefactory.service;

import main.java.businesslogic.model.Account;
import main.java.core.logger.Log;
import main.java.core.properties.EnvProperty;
import main.java.core.properties.PropertyReader;
import main.java.pagefactory.page.LoginPage;
import main.java.pagefactory.page.SideBarPage;
import org.openqa.selenium.WebDriver;

public class LoginService extends AbstractService {

    private final LoginPage loginPage = new LoginPage(driver);
    private final SideBarPage sideBarPage = new SideBarPage(driver);

    public LoginService(WebDriver driver) {
        super(driver);
    }

    public void logInEpam() {
        Log.info("Log in using personal Epam account");
        loginPage.openPage(PropertyReader.getProperty(EnvProperty.REMOTE_BASE_URL.getKey()));
        loginPage.clickLoginEpamButton();
        sideBarPage.waitForFiltersButtonIsShown();
    }

    public void logIn(Account account) {
        Log.info(String.format("Log in using account: '%s'", account.getEmail()));
        loginPage.openPage(PropertyReader.getProperty(EnvProperty.LOCAL_BASE_URL.getKey()));
        loginPage.fillEmailInput(account.getEmail());
        loginPage.fillPasswordInput(account.getPassword());
        loginPage.clickLoginButton();
        sideBarPage.waitForFiltersButtonIsShown();
    }
}