package com.ahnlab.vagent.service;

import com.ahnlab.vagent.model.Agent;
import com.ahnlab.vagent.model.AgentInfo;
import com.ahnlab.vagent.utils.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AgentService {


    private final static String filePathAC = "agent/registerAgent.json";
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
        List<AgentInfo> agentInfoList = JsonUtils.generateBaseDataAgentInfo(filePathAC);

        for (AgentInfo agentInfo : agentInfoList) {
            LOGGER.info("## Agent Service Agent Create : {}", agentInfo);
            Agent agent = new Agent();
            agent.setAgentInfo(agentInfo);
            agentService.agentList.add(agent);
        }
        agentService.execute();
    }

    public void startMonitoring() {
        Runnable runnable = new Monitoring();
        Thread taskThread = new Thread(runnable);
        taskThread.start();
    }

    public class Monitoring implements Runnable {
        @Override
        public void run() {
            while (runProcessing) {
                try {
                    Thread.sleep(9000);
                    //executeProduct();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
