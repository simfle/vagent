package com.ahnlab.vagent.listener;

public class PropertiesListener implements ResourceManager.ResourceListener {

    private ResourceManager resourceManager;

    public PropertiesListener(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        resourceManager.addResourceListener(this);
    }

    @Override
    public void update() {

    }
}
