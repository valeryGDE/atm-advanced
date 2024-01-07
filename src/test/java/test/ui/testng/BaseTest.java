package test.ui.testng;

import com.google.inject.Guice;
import com.google.inject.Inject;
import main.java.api.integration.JiraService;
import main.java.api.integration.SlackService;
import main.java.ui.businesslogic.areas.FiltersBll;
import main.java.ui.businesslogic.areas.LoginBll;
import main.java.ui.core.data.DataProviders;
import main.java.ui.core.driver.BrowserType;
import main.java.ui.core.driver.WebDriverManager;
import main.java.ui.core.logger.Log;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static main.java.ui.enums.JiraStatuses.*;

public class BaseTest extends DataProviders {

    private String testName;
    private String issueName;

    @Inject
    protected LoginBll loginBll;
    @Inject
    protected FiltersBll filtersBll;
    @Inject
    protected SlackService slackService;
    @Inject
    protected JiraService jiraService;

    @BeforeMethod(alwaysRun = true)
    public void setUp(ITestResult result) {
        WebDriverManager.setDriver(BrowserType.CHROME);
//        WebDriverManager.setRemoteDriver();
        Guice.createInjector().injectMembers(this);
        testName = result.getMethod().getMethodName();
        issueName = result.getMethod().getDescription();
        jiraService.addStatusToIssue(IN_PROGRESS.getStatus(), issueName);
        slackService.postNotificationToSlack("Test '%s' started".formatted(testName));
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser(ITestResult result) {
        Log.info("Stop driver: " + WebDriverManager.getDriver().toString());
        slackService.postNotificationToSlack("Test '%s' finished".formatted(testName));
        if (result.isSuccess()) {
            jiraService.addStatusToIssue(PASSED.getStatus(), issueName);
        } else {
            jiraService.addStatusToIssue(FAILED.getStatus(), issueName);
        }
        WebDriverManager.closeDriver();
    }
}