package org.example.mvc.util;


import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    private static final Properties props = new Properties();

    static {
        try (InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(in);
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Failed to load config.properties: " + e);
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}

