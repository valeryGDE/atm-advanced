package test;

import main.java.businesslogic.manager.AccountManager;
import main.java.pagefactory.page.LoginPage;
import main.java.service.LoginService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void loginToRemoteReportPortal() throws InterruptedException {
        LoginService loginService = new LoginService();
        Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(5));
        loginService.logIn(AccountManager.defaultAccount());
        Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(5));
    }

    @Test
    public void loginDefaultUser() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));
        textBox.sendKeys("Selenium");
        submitButton.click();
    }
}
