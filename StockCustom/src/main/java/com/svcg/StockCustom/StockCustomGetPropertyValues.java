package com.svcg.StockCustom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StockCustomGetPropertyValues {

    private static final Logger logger = LoggerFactory
            .getLogger(StockCustomGetPropertyValues.class);

    InputStream inputStream;

    public Properties getPropValues() throws IOException {
        Properties prop = new Properties();
        try {
            String propFileName = "application.yml";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

        } catch (Exception e) {
            logger.error("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return prop;
    }
}
