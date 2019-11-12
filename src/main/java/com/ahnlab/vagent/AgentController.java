package com.ahnlab.vagent;

import com.ahnlab.vagent.base.PropertyManager;
import com.ahnlab.vagent.service.AgentService;
import com.ahnlab.vagent.service.BatchService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AgentController {
    private final AgentService agentService;

    public void register() {
        this.agentService.register();
    }

    public void execute() {
        this.agentService.execute();
    }

    public static void main(String[] args) {
        AgentController agentController = new AgentController(new AgentService());
        BatchService batchService = new BatchService(agentController);
        batchService.start();

        PropertyManager propertyManager = new PropertyManager(agentController);
        Thread thread = new Thread(propertyManager);
        thread.start();
    }
}
