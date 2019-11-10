package com.ahnlab.vagent.model;

import com.ahnlab.vagent.service.WorkerService;
import com.ahnlab.vagent.base.ProductTask;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Product {

    protected static final Logger LOGGER = LogManager.getLogger(Product.class);

    @Getter
    private Agent agent;

    @Getter
    private ProductTask[] productTasks;

    private WorkerService workerService;

    public enum Type {
        AC, HIPS
    }

    public Product(Agent agent, ProductTask[] productTasks) {
        this.agent = agent;
        this.productTasks = productTasks;
        this.workerService = new WorkerService(this);
    }

    public void execute() {
        workerService.execute();
    }
}
