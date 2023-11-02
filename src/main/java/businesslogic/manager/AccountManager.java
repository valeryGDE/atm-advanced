package main.java.businesslogic.manager;

import main.java.businesslogic.model.Account;
import main.java.core.properties.EnvProperty;
import main.java.core.properties.PropertyReader;

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
