package main.java.ui.pagefactory.page.selenide;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class SelenideCommonComponents extends AbstractSelenidePage {

    public static final String LOADER = "div[class*=spinningPreloader]";
    public static final String NOTIFICATION = "//div[contains(@class,'notificationItem')]";

    @Override
    public boolean isPageShown() {
        return $(LOADER).isDisplayed();
    }

    public void waitForSpinnerIsNotShown() {
        $(LOADER).shouldNotBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void waitForNotificationIsNotShown() {
        $(By.xpath(NOTIFICATION)).shouldNotBe(Condition.visible, Duration.ofSeconds(20));
    }
}