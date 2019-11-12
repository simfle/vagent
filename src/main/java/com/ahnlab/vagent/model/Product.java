package com.ahnlab.vagent.model;

import com.ahnlab.vagent.base.ProductTask;
import lombok.Data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Data
public class Product {

    protected static final Logger LOGGER = LogManager.getLogger(Product.class);
    private ProductTask[] productTasks;
    private Agent agent;
    private Map<String, Worker> workerGroup = new HashMap<>();

    public enum Type {
        AC, HIPS
    }

    public Product(Agent agent, ProductTask[] productTasks) {
        this.agent = agent;
        this.productTasks = productTasks;
        initWorker();
    }

    private void initWorker() {
        for (ProductTask productTask : this.productTasks) {
            Worker worker = new Worker(productTask);
            worker.registerTask(this.agent);
            workerGroup.put(productTask.name(), worker);
        }
    }

    public void execute() {
        for (Worker worker : workerGroup.values()) {
            worker.run();
        }
    }
}
