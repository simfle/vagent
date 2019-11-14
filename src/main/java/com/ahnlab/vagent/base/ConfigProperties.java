package com.ahnlab.vagent.base;

import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties extends Properties {

    private static final String PROPERTIES_NAME = "config.properties";

    ConfigProperties() {
        super();
        try {
            InputStream inputStream = RuntimeProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_NAME);
            this.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
