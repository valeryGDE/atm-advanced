package main.java.ui.pagefactory.page;

import main.java.ui.core.driver.WebDriverManager;
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
