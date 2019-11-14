package com.ahnlab.vagent.listener;

public class TaskDataListener implements ResourceManager.ResourceListener {

    private ResourceManager resourceManager;

    public TaskDataListener(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        resourceManager.addResourceListener(this);
    }

    @Override
    public void update() {

    }
}
