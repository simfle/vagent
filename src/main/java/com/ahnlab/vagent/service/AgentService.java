package com.ahnlab.vagent.service;

import com.ahnlab.vagent.base.ProductTask;
import com.ahnlab.vagent.model.Agent;
import com.ahnlab.vagent.model.Task;
import com.ahnlab.vagent.vo.TaskVO;
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
    private final String target = "https://172.21.30.219:8804";

    public AgentService() {
        Task task = new Task();
        task.setProductTask(ProductTask.AGENT_REGISTER);
        task.executeAgent(this.agentList);
    }

    public void execute() {
        for (Agent agent : this.agentList) {
            executorService.execute(agent);
        }
    }

    public static void main(String[] args) {
        AgentService agentService = new AgentService();
        AgentExecutor agentExecutor = new AgentExecutor(agentService);
        Thread thread = new Thread(agentExecutor);
        thread.start();
    }
}
