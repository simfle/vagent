package com.ahnlab.vagent.base;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class RuntimeProperties extends ConfigProperties {

    private static RuntimeProperties instance = null;

    private RuntimeProperties() {
        super();
        loadProperties();
    }

    private void loadProperties() {
        try {
            FileInputStream inputStream = new FileInputStream(super.getProperty("runtime.properties.path"));
            this.load(new BufferedInputStream(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RuntimeProperties getInstance() {
        if (instance == null) {
            try {
                instance = new RuntimeProperties();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void update() {
        loadProperties();
    }
}
