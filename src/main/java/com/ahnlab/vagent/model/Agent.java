package com.ahnlab.vagent.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Data
public class Agent implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Agent.class);
    private Auth auth;
    private Map<Product.Type, Product> productMap = new HashMap<>();


    public Agent(Auth auth) {
        this.auth = auth;
        this.productMap.put(Product.Type.AC, ProductFactory.getProduct(this, Product.Type.AC));
        this.productMap.put(Product.Type.HIPS, ProductFactory.getProduct(this, Product.Type.HIPS));
    }

    @Data
    public static class Auth {

        @JsonProperty("session_key")
        private String sessionKey;

        @JsonProperty("node_id")
        private String nodeId;
    }

    @Override
    public void run() {
        for (Product product : productMap.values()) {
            product.execute();
        }
    }
}
