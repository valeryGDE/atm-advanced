package main.java.pagefactory.page;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class CommonComponents extends DefaultPage {

    @FindBy(xpath = "//div[contains(@class,'spinningPreloader')]")
    private WebElement loader;

    @FindBy(xpath = "//div[contains(@class,'notificationItem')]")
    private WebElement notification;
}