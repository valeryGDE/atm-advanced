package main.java.pagefactory.service;

import main.java.pagefactory.page.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginService extends AbstractService {

    private final LoginPage loginPage = new LoginPage(driver);

    public LoginService(WebDriver driver) {
        super(driver);
    }

    public void openPage(String url) {
        loginPage.openPage(url);
    }

    public void fillEmailInput(String email) {
        loginPage.getEmailInput().sendKeys(email);
    }

    public void fillPasswordInput(String password) {
        loginPage.getPasswordInput().sendKeys(password);
    }

    public void clickLoginEpamButton() {
        loginPage.getLoginEpamButton().click();
    }

    public void clickLoginButton() {
        loginPage.getLoginButton().click();
    }
}