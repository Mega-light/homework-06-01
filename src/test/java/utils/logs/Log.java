package utils.logs;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class Log {
    private static final Logger log = LoggerFactory.getLogger(Log.class);

    public static void info(String message){
        log.info(message);
    }

    public static void warn(String message){
        log.warn(message);
    }

    public static void error(String message){
        log.error(message);
    }

    public static void debug(String message){
        log.debug(message);
    }
}
