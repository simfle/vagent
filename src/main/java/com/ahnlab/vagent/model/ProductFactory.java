package com.ahnlab.vagent.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductFactory {

    protected static final Logger LOGGER = LogManager.getLogger(ProductFactory.class);

    private Product product;

    private ProductFactory(Agent agent, Product.Type productType) {
        if (Product.Type.AC == productType) {
            product = new Product(agent, Task.Action.AC_EVENT_LOG);
        } else if (Product.Type.HIPS == productType) {
            product = new Product(agent, Task.Action.HIPS_EVENT_LOG);
        }

    }
    public static Product getProduct(Agent agent, Product.Type productType) {
        return new ProductFactory(agent, productType).product;
    }
}
