package com.ahnlab.vagent.model;

import lombok.Data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Data
public class Product {

    protected static final Logger LOGGER = LogManager.getLogger(Product.class);
    private TaskType taskType;
    private Agent agent;
    private Map<TaskType.TaskData, Worker> workers = new HashMap<>();

    public enum Type {
        AC, HIPS
    }

    public Product(Agent agent, TaskType taskTypes) {
        this.agent = agent;
        this.taskType = taskType;
    }

    public void execute() {
        for (Worker worker : workers.values()) {
            worker.run();
        }
    }
}
