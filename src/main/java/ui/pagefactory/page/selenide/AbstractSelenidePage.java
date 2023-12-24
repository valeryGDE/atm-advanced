package main.java.ui.pagefactory.page.selenide;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import main.java.ui.pagefactory.pagebase.IPageVerifier;

public abstract class AbstractSelenidePage implements IPageVerifier {

    protected void scrollToElementWithJS(SelenideElement selenideElement) {
        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", selenideElement);
    }

    protected void clickElementWithJS(SelenideElement selenideElement) {
        Selenide.executeJavaScript("arguments[0].click();", selenideElement);
    }

    protected boolean isElementInViewPort(SelenideElement selenideElement) {
        return Boolean.TRUE.equals(Selenide.executeJavaScript(
                "var rect = arguments[0].getBoundingClientRect(); " +
                        "return (rect.top >= 0 && rect.left >= 0 && " +
                        "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
                        "rect.right <= (window.innerWidth || document.documentElement.clientWidth));",
                selenideElement));
    }
}