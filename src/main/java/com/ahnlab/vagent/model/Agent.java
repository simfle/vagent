package com.ahnlab.vagent.agent;

import com.ahnlab.vagent.product.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class Agent implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Agent.class);
    private static boolean runProcessing = true;
    private List<Product> productList = new ArrayList<>();
    private AgentAuth agentAuth;
    private Product productAC;
    private Product productHips;

    public Agent(AgentAuth agentAuth) {
        this.agentAuth = agentAuth;
        this.productAC = Product.createAC();
        this.productList.add(productAC);
    }

    @Data
    public static class AgentAuth {
        @JsonProperty("session_key")
        private String sessionKey;
        @JsonProperty("node_id")
        private String nodeId;
    }



    //
    /*@Override
    public void run() {
        while (runProcessing) {
            String reservation = LocalTime.of(16, 48, 00).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            if (reservation.equals(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))) {
                try {
                    int count = 3;
                    Thread.sleep(9000);
                    for (int i = 0; i < count; i++) {
                        executeProduct();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    public void executeProduct() {
        for (Product product : productList) {
            product.taskRun(this);
        }
    }

    @Override
    public void run() {
        while (runProcessing) {
            try {
                Thread.sleep(2000);
                executeProduct();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
