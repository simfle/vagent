package com.ahnlab.vagent.model;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
public class Product {

    private Agent agent;
    private Task.Action taskType;
    private static final Logger LOGGER = LogManager.getLogger(Product.class);
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private Map<Task.Action.JsonData, Worker> workers = new HashMap<>();

    public enum Type {
        AC, HIPS
    }

    public Product(Agent agent, Task.Action taskType) {
        this.agent = agent;
        this.taskType = taskType;
    }

    public void execute() {
        for (Worker worker : workers.values()) {
            executorService.execute(worker);
        }
    }
}
