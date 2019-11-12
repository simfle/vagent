package com.ahnlab.vagent.base;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class AgentProperties extends java.util.Properties {

    private static AgentProperties instance = null;
    private static String PROPERTIES_NAME = "config.properties";

    private AgentProperties() {
        super();
        try {
            InputStream inputStream = AgentProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_NAME);
            Properties configProperties = new Properties();
            configProperties.load(inputStream);
            String vagentFilePath = configProperties.getProperty("vagent.properties.path");
            FileInputStream vis = new FileInputStream(vagentFilePath);
            this.load(new BufferedInputStream(vis));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AgentProperties getInstance() {
        if (instance == null) {
            try {
                instance = new AgentProperties();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
