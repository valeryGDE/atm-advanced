package main.java.core.properties;

import main.java.core.logger.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";
    private static final Properties configuration = loadConfiguration();

    private static Properties loadConfiguration() {
        Properties properties = new Properties();
        File configFile = new File(CONFIG_FILE_PATH);
        try (FileInputStream fileInputStream = new FileInputStream(configFile)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            Log.error("Unable to load provided config. Exception: " + e.getMessage());
        }
        return properties;
    }

    public static String getProperty(String name) {
        return configuration.getProperty(name);
    }
}
