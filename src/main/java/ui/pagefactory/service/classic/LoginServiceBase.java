package main.java.ui.pagefactory.service.classic;

import main.java.ui.pagefactory.page.classic.LoginPage;
import main.java.ui.pagefactory.pagebase.AbstractBaseService;

public class LoginServiceBase extends AbstractBaseService<LoginPage> {

    @Override
    public boolean isPageShown() {
        return getPage().getEmailInput().isDisplayed()
                && getPage().getPasswordInput().isDisplayed()
                && getPage().getLoginButton().isDisplayed();
    }

    public void openPage(String url) {
        getPage().openPage(url);
        waitPageIsShown();
    }

    public void fillEmailInput(String email) {
        getPage().getEmailInput().click();
        getPage().getEmailInput().sendKeys(email);
    }

    public void fillPasswordInput(String password) {
        getPage().getPasswordInput().click();
        getPage().getPasswordInput().sendKeys(password);
    }

    public void clickLoginEpamButton() {
        getPage().getLoginEpamButton().click();
    }

    public void clickLoginButton() {
        getPage().getLoginButton().click();
    }
}