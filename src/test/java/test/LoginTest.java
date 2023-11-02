package test;

import main.java.businesslogic.manager.AccountManager;
import main.java.service.LoginService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginToLocalReportPortal() throws InterruptedException {
        LoginService loginService = new LoginService();
        loginService.logIn(AccountManager.defaultAccount());
        Assert.assertEquals(3, 4);
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
