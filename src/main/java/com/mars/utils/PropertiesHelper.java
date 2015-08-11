package com.mars.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Properties;

/**
 * Created by mars on 2015/3/18.
 */
@Slf4j
public class PropertiesHelper {

    private static final PropertiesHelper helper = new PropertiesHelper();
    private static final String DEFAULT_FILE_NAME = "security-setting";
    private static final String DEFAULT_BASE_NAME = "properties";

    PropertiesHelper() {

    }

    public static String getProperty(String key) {
        return getProperties(DEFAULT_FILE_NAME).getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return getProperties(DEFAULT_FILE_NAME).getProperty(key, defaultValue);
    }

    public static Properties getProperties(String fileName) {
        return getProperties(fileName, "");
    }

    public static Properties getProperties(String fileName, String filePath) {
        return getPropertiesByPath(fileName, filePath);
    }

    public static Properties getPropertiesByPath(String fileName, String filePath) {
        return getPropertiesByPath(fileName, filePath, DEFAULT_BASE_NAME);
    }

    public static Properties getPropertiesByPath(String fileName, String filePath, String baseName) {
        InputStream inStream = null;
        if (filePath != null && !filePath.isEmpty()) {
            File configFile = new File(new File(filePath), fileName + "." + baseName);
            try {
                inStream = new FileInputStream(configFile);
            } catch (FileNotFoundException ex) {
                log.error(fileName + "FileNotFoundException " + ex.getMessage());
            }
        } else {
            final String fileFullName = fileName + "." + baseName;
            inStream = PropertiesHelper.class.getClassLoader().getResourceAsStream(fileFullName);
        }
        try {
            if (inStream != null) {
                Properties properties = new Properties();
                properties.load(inStream);
                return properties;
            }
        } catch (IOException e) {
            log.error(fileName + "IO 錯誤 " + e.getMessage());
        }
        return null;
    }

}
