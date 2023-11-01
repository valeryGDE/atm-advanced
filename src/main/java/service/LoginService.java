package main.java.service;

import main.java.businesslogic.model.Account;
import main.java.core.logger.Log;
import main.java.core.properties.EnvProperty;
import main.java.core.properties.PropertyReader;
import main.java.pagefactory.page.LoginPage;

public class LoginService {

    public void logInEpam() {
        Log.info("Log in using personal Epam account");
        LoginPage loginPage = new LoginPage();
        loginPage.openPage(PropertyReader.getProperty(EnvProperty.REMOTE_BASE_URL.getKey()));
        loginPage.clickLoginEpamButton();
    }

    public void logIn(Account account) {
        Log.info(String.format("Log in using account: '%s'", account.getEmail()));
        LoginPage loginPage = new LoginPage();
        loginPage.openPage(PropertyReader.getProperty(EnvProperty.LOCAL_BASE_URL.getKey()));
        loginPage.fillEmailInput(account.getEmail());
        loginPage.fillPasswordInput(account.getPassword());
        loginPage.clickLoginButton();
    }

    public void logOut() {
        Log.info("Log out");
    }
}
