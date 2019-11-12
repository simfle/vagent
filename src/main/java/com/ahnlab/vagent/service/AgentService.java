package com.ahnlab.vagent.service;

import com.ahnlab.vagent.base.AgentProperties;
import com.ahnlab.vagent.base.ProductTask;
import com.ahnlab.vagent.model.Agent;
import com.ahnlab.vagent.utils.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AgentService {

    private static final Logger LOGGER = LogManager.getLogger(AgentService.class);
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    private List<Agent> agentList = new ArrayList<>();
    private Properties agentProperties = AgentProperties.getInstance();

    public void register() {
        if (this.agentList.size() > 0) {
            this.agentList.clear();
        }
        String configPath = agentProperties.getProperty("vagent.config.path");
        List<Agent.Auth> authList = JsonUtils.generateBaseDataAgentAuth(String.format("%s%s", configPath, ProductTask.AGENT_REGISTER.getTaskData().getFilePath()));
        for (Agent.Auth auth : authList) {
            Agent agent = new Agent(auth);
            this.agentList.add(agent);
        }
    }

    public void execute() {
        for (Agent agent : this.agentList) {
            executorService.execute(agent);
        }
    }
}
