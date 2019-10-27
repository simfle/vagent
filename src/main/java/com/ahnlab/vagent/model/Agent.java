package com.ahnlab.vagent.model;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Data
public class Agent implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Agent.class);
    private static boolean runProcessing = true;
    private List<VirtualProduct> virtualProductList = new ArrayList<>();
    private AgentInfo agentInfo = new AgentInfo();

    public Agent() {
        this.virtualProductList.add(new VirtualAc());
        this.virtualProductList.add(new VirtualHips());
    }

    @Override
    public void run() {
        while (runProcessing) {
            try {
                Thread.sleep(9000);
                executeProduct();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void executeProduct() {
        for (VirtualProduct virtualProduct : virtualProductList) {
            virtualProduct.taskRun(this);
        }
    }
}
