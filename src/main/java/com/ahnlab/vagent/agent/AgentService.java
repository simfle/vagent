package com.ahnlab.vagent.agent;

import com.ahnlab.vagent.utils.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AgentService {

    private static final Logger LOGGER = LogManager.getLogger(AgentService.class);
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    private static boolean runProcessing = true;
    private List<Agent> agentList = new ArrayList<>();

    public void execute() {
        for (Agent agent : agentList) {
            executorService.execute(agent);
        }
    }

    public static void main(String[] args) {
        AgentService agentService = new AgentService();
        agentService.createAgent();
        agentService.execute();
    }

    public void createAgent() {
        String filePath = "json/agent/registerAgent.json";
        List<Agent.AgentAuth> agentAuthList = JsonUtils.generateBaseDataAgentAuth(filePath);
        for (Agent.AgentAuth agentAuth : agentAuthList) {
            Agent agent = new Agent(agentAuth);
            this.agentList.add(agent);
        }
    }
}
