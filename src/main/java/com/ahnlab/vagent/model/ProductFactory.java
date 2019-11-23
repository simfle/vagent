package com.ahnlab.vagent.model;

import com.ahnlab.vagent.base.RuntimeProperties;
import com.ahnlab.vagent.utils.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ProductFactory {

    protected static final Logger LOGGER = LogManager.getLogger(ProductFactory.class);

    private Product product;
    private Properties runtimePropertoes = RuntimeProperties.getInstance();

    private ProductFactory(Agent agent, Product.Type productType) {
        if (Product.Type.AC == productType) {
            product = new Product(agent, Task.Action.AC_EVENT_LOG);
        } else if (Product.Type.HIPS == productType) {
            product = new Product(agent, Task.Action.HIPS_EVENT_LOG);
        }
        createWorker();
    }

    private void createWorker() {
        String jsonPath = runtimePropertoes.getProperty("config.path");
        Map<TaskType.TaskData, Worker> workers = new HashMap<>();
        for (TaskType.TaskData taskData : product.getTaskType().getTaskData()) {
            Worker.WorkerVO workerVO = JsonUtils.generateWorkerVO(String.format("%s%s", jsonPath, taskData.getFilePath()));
            workers.put(TaskType.TaskData, new Worker(product, taskData, workerVO));
        }
        product.setWorkers(workers);
    }

    public static Product getProduct(Agent agent, Product.Type productType) {
        return new ProductFactory(agent, productType).product;
    }
}
