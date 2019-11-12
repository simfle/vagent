package com.ahnlab.vagent.service;


import com.ahnlab.vagent.AgentController;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequiredArgsConstructor
public class BatchService implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(BatchService.class);
    private static boolean runProcessing = true;
    private final AgentController agentController;

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
        agentController.register();
    }

    @Override
    public void run() {
        int period = 1000 * 60;
        while (runProcessing) {
            try {
                Thread.sleep(period);
                agentController.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
