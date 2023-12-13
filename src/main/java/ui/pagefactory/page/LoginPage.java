package main.java.ui.pagefactory.page;

import lombok.Getter;
import main.java.ui.core.logger.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class LoginPage extends DefaultPage {

    @FindBy(xpath = "//div[contains(@class,'login-field')]//input")
    private WebElement emailInput;

    @FindBy(xpath = "//div[contains(@class,'password-field')]//input")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(@class,'bigButton')]")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class,'externalLoginBlock')]/button")
    private WebElement loginEpamButton;

    @Override
    public void openPage(String url) {
        Log.info("Open 'Log in' screen");
        super.openPage(url);
    }
}