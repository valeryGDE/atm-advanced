package main.java.ui.core.driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SauceLabsDriverManager {

    private static String SAUCE_LAB_URL = "https://ondemand.eu-central-1.saucelabs.com:443/wd/hub";

    public static RemoteWebDriver getRemoteChromeDriverConfiguration() {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", "oauth-valeriya.anatolyevna-737eb");
        sauceOptions.put("accessKey", System.getenv("SAUCE_TOKEN"));
        sauceOptions.put("build", "selenium-build-RCR0F");
        sauceOptions.put("name", "<your test name>");
        browserOptions.setCapability("sauce:options", sauceOptions);
        try {
            return new RemoteWebDriver(new URL(SAUCE_LAB_URL), browserOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL for Sauce Lab", e);
        }
    }
}
