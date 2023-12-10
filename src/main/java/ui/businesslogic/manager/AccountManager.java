package main.java.ui.businesslogic.manager;

import main.java.ui.businesslogic.model.Account;
import main.java.ui.core.properties.EnvProperty;
import main.java.ui.core.properties.PropertyReader;

public class AccountManager {

    public static Account defaultAccount() {
        Account account = new Account();
        account.setEmail(PropertyReader.getProperty(EnvProperty.DEFAULT_LOGIN_NAME.getKey()));
        account.setPassword(PropertyReader.getProperty(EnvProperty.DEFAULT_PASSWORD.getKey()));
        return account;
    }

    public static Account adminAccount() {
        Account account = new Account();
        account.setEmail(PropertyReader.getProperty(EnvProperty.ADMIN_LOGIN_NAME.getKey()));
        account.setPassword(PropertyReader.getProperty(EnvProperty.ADMIN_PASSWORD.getKey()));
        return account;
    }
}