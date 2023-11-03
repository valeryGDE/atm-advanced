package main.java.pagefactory.page;

import lombok.Getter;
import main.java.core.logger.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

@Getter
public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class,'login-field')]//input")
    private WebElement emailInput;

    @FindBy(xpath = "//div[contains(@class,'password-field')]//input")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(@class,'bigButton')]")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class,'externalLoginBlock')]/button")
    private WebElement loginEpamButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openPage(String url) {
        Log.info("Open 'Log in' screen");
        super.openPage(url, emailInput, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public void fillEmailInput(String email) {
        emailInput.sendKeys(email);
    }

    public void fillPasswordInput(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLoginEpamButton() {
        loginEpamButton.click();
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}
