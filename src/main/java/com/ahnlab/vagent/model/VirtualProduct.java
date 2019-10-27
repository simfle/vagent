package com.ahnlab.vagent.model;

import com.ahnlab.vagent.task.AbstractTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class VirtualProduct {

    protected static final Logger LOGGER = LogManager.getLogger(VirtualProduct.class);

    protected List<AbstractTask> taskList = new ArrayList<>();

    public void taskRun(Agent agent) {
        for (AbstractTask task : taskList) {
            task.execute(agent);
        }
    }
}
