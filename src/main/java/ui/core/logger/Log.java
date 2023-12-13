package main.java.ui.core.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    private static final ThreadLocal<Logger> LOGGER = ThreadLocal.withInitial(() -> LogManager.getLogger(Log.class.getName()));

    public static void info(String message) {
        LOGGER.get().info(message);
    }

    public static void info(Object message) {
        LOGGER.get().info(message);
    }

    public static void warn(String message) {
        LOGGER.get().warn(message);
    }

    public static void error(String message) {
        LOGGER.get().error(message);
    }

    public static void fatal(String message) {
        LOGGER.get().fatal(message);
    }

    public static void debug(String message) {
        LOGGER.get().debug(message);
    }
}