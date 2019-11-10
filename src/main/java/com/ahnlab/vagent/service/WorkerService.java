package com.ahnlab.vagent.service;

import com.ahnlab.vagent.base.ProductTask;
import com.ahnlab.vagent.model.Product;
import com.ahnlab.vagent.model.Worker;



import java.util.HashMap;
import java.util.Map;

public class WorkerService {

    private Product product;
    private Map<String, Worker> workerGroup = new HashMap<>();

    public WorkerService(Product product) {
        this.product = product;
        initWorker();
    }

    private void initWorker() {
        for (ProductTask productTask : this.product.getProductTasks()) {
            Worker worker = new Worker(productTask);
            worker.initWorker(this.product.getAgent());
            workerGroup.put(productTask.name(), worker);
        }
    }

    public void execute() {
        for (Worker worker : workerGroup.values()) {
            worker.run();
        }
    }
}
