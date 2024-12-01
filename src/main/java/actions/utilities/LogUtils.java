package actions.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class LogUtils {
    private static final Logger LOGGER = (Logger) LogManager.getLogger(LogUtils.class);

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void info(Object object) {
        LOGGER.info(object);
    }

    public static void warn(String message) {
        LOGGER.warn(message);
    }

    public static void warn(Object object) {
        LOGGER.warn(object);
    }

    public static void error(String message) {
        LOGGER.error(message);
    }

    public static void error(Object object) {
        LOGGER.error(object);
    }

    public static void fatal(String message) {
        LOGGER.fatal(message);
    }

    public static void fatal(Object object) {
        LOGGER.fatal(object);
    }

    public static void debug(String message) {
        LOGGER.debug(message);
    }

    public static void debug(Object object) {
        LOGGER.debug(object);
    }
}
