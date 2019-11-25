package com.ahnlab.vagent.listener;

import com.ahnlab.vagent.base.RuntimeProperties;

public class PropertiesListener implements ResourceManager.AbstractListener {

    private RuntimeProperties runtimeProperties = RuntimeProperties.getInstance();

    public PropertiesListener() { ResourceManager.addListener(this);}

    @Override
    public void update() {
        runtimeProperties.update();
    }
}
