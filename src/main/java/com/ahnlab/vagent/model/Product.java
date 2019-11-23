package com.ahnlab.vagent.model;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Data
public class Product {

    protected static final Logger LOGGER = LogManager.getLogger(Product.class);
    private Task.Action taskData;
    private Agent agent;
    private Map<Task.Action.JsonData, Worker> workers = new HashMap<>();

    public enum Type {
        AC, HIPS
    }

    public Product(Agent agent, Task.Action taskData) {
        this.agent = agent;
        this.taskData = taskData;
    }

    public void execute() {
        for (Worker worker : workers.values()) {
            worker.run();
        }
    }
}
