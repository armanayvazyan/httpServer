package com.config;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    public static final Integer PORT;
    public static final String HOST;

    private Configuration() {
    }

    static {
        PORT = Integer.parseInt(getProperty("port"));
        HOST = getProperty("host");
    }

    @SneakyThrows(IOException.class)
    private static String getProperty(String key) {
        Properties prop = new Properties();
        InputStream input = new FileInputStream("src/main/resources/config.properties");
        prop.load(input);
        return prop.getProperty(key);
    }
}
