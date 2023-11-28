package main.java.pagefactory.page;

import main.java.core.driver.WebDriverManager;
import org.apache.commons.lang3.StringUtils;

public class DefaultPage extends AbstractPage{

    public DefaultPage() {
        super(WebDriverManager.getDriver());
    }

    public String getPageName() {
        var simpleName = this.getClass().getSimpleName();
        var arraySimpleName = StringUtils.splitByCharacterTypeCamelCase(simpleName);
        return String.join(" ", arraySimpleName);
    }
}
