package main.java.ui.pagefactory.page.selenide;

import lombok.Getter;
import main.java.ui.core.logger.Log;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Getter
public class SelenideLoginPage extends AbstractSelenidePage {

    private static final String EMAIL_INPUT = "//div[contains(@class,'login-field')]//input";
    private static final String PASSWORD_INPUT = "//div[contains(@class,'password-field')]//input";
    private static final String LOGIN_BUTTON = "//button[contains(@class,'bigButton')]";

    @Override
    public boolean isPageShown() {
        return $(By.xpath(EMAIL_INPUT)).isDisplayed()
                && $(By.xpath(PASSWORD_INPUT)).isDisplayed()
                && $(By.xpath(LOGIN_BUTTON)).isDisplayed();
    }

    public void openPage(String url) {
        Log.info("Open 'Log in' screen");
        open(url, getClass());
        waitPageIsShown();
    }

    public void fillEmailInput(String email) {
        $(By.xpath(EMAIL_INPUT)).setValue(email).pressEnter();
    }

    public void fillPasswordInput(String password) {
        $(By.xpath(PASSWORD_INPUT)).setValue(password).pressEnter();
    }

    public void clickLoginButton() {
        $(By.xpath(LOGIN_BUTTON)).click();
    }
}