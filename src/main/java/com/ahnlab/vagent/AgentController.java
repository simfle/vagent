package com.ahnlab.vagent;

import com.ahnlab.vagent.base.RuntimeProperties;
import com.ahnlab.vagent.service.AgentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;
    private Properties runtimeProperties = RuntimeProperties.getInstance();

    private void execute() {
        this.agentService.execute();
    }

    public static void main(String[] args) {
        AgentController agentController = new AgentController(new AgentService());
        AgentController.ControllerExecutor controllerExecutor = agentController.new ControllerExecutor();
        controllerExecutor.start();

//        ResourceManager resourceManager = new ResourceManager(agentController);
//        Thread thread = new Thread(resourceManager);
//        thread.start();
    }

    @Data
    @RequiredArgsConstructor
    private class ControllerExecutor {
        private final Logger LOGGER = LogManager.getLogger(ControllerExecutor.class);
        private boolean runProcessing = true;

        void start() {
            new Thread(() -> {
                while (runProcessing) {
                    int period = Integer.parseInt(runtimeProperties.getProperty("controller.executor.period"));
                    try {
                        Thread.sleep(period);
                        execute();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
