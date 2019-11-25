package com.ahnlab.vagent.listener;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TaskDataListener implements ResourceManager.AbstractListener {

    private static final Logger LOGGER = LogManager.getLogger(TaskDataListener.class);

    public TaskDataListener() {
        ResourceManager.addListener(this);
    }

    @Override
    public void update() {
        LOGGER.info("**** JsonDataListener File Change ****");
    }
}
