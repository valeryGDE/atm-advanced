package main.java.utils;

import com.epam.reportportal.service.ReportPortal;
import main.java.core.driver.WebDriverManager;
import main.java.core.logger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Screenshoter {

    public void makeScreenshot() {
        File screenCapture = ((TakesScreenshot) WebDriverManager.getWebDriverInstance()).getScreenshotAs(OutputType.FILE);
        try {
            var pathToFile = ".//target/screenshots/" + getCurrentTimeAsString() + ".png";
            File outputFile = new File(pathToFile);
            FileUtils.copyFile(screenCapture, outputFile);
            ReportPortal.emitLog("Screenshot captured", "INFO", Calendar.getInstance().getTime(), outputFile);
        } catch (IOException e) {
            Log.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(dateTimeFormatter);
    }
}
