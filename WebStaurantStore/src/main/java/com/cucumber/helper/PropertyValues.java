package com.cucumber.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyValues {
    static InputStream inputStream;
    static Properties prop = new Properties();

    public static String getProperty(String property) {

        if (prop.size() == 0) {
            try {
                String propFileName = "config.properties";
                inputStream = PropertyValues.class.getClassLoader().getResourceAsStream(propFileName);
                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return prop.getProperty(property);
    }

    public static String getProperty(Property property){
        return getProperty(String.valueOf(property).toLowerCase());
    }
}