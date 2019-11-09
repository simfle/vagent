package com.ahnlab.vagent.product;

import com.ahnlab.vagent.agent.Agent;
import com.ahnlab.vagent.task.ProductTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductFactory {

    protected static final Logger LOGGER = LogManager.getLogger(ProductFactory.class);

    private Product product;

    private ProductFactory(Agent agent, Product.Type productType){
        if(Product.Type.AC == productType){
            product = new Product(agent, new ProductTask[]{ProductTask.AGENT_EVENT_LOG_AC, ProductTask.EXECUTE_EVENT_LOG_AC});
        } else if(Product.Type.HIPS == productType){
            product = new Product(agent, new ProductTask[]{ProductTask.AGENT_EVENT_LOG_HIPS});
        }
    }

    public static Product getProduct(Agent agent, Product.Type productType){
        return new ProductFactory(agent, productType).product;
    }
}
