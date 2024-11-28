package com.cruiser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logging {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void logInfo(String string){
        logger.info(string);
    }

    public static void logWarn(String string){
        logger.warn(string);
    }

    public static void logError(String string){
        logger.error(string);
    }

}
