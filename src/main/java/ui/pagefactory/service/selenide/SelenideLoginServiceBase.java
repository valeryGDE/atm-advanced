package main.java.ui.pagefactory.service.selenide;

import com.google.inject.Inject;
import main.java.ui.businesslogic.model.Account;
import main.java.ui.core.logger.Log;
import main.java.ui.core.properties.EnvProperty;
import main.java.ui.core.properties.PropertyReader;
import main.java.ui.pagefactory.page.selenide.SelenideCommonComponents;
import main.java.ui.pagefactory.page.selenide.SelenideLoginPage;
import main.java.ui.pagefactory.page.selenide.SelenideSideBarPage;

public class SelenideLoginServiceBase {

    @Inject
    private SelenideLoginPage selenideLoginPage;
    @Inject
    private SelenideSideBarPage selenideSideBarPage;
    @Inject
    private SelenideCommonComponents selenideCommonComponents;

    public void logIn(Account account) {
        Log.info("Log in using account: '%s'".formatted(account.getEmail()));
        selenideLoginPage.openPage(PropertyReader.getProperty(EnvProperty.LOCAL_BASE_URL.getKey()));
        selenideLoginPage.fillPasswordInput(account.getPassword());
        selenideLoginPage.fillEmailInput(account.getEmail());
        selenideLoginPage.clickLoginButton();
        selenideSideBarPage.waitPageIsShown();
    }
}