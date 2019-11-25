package com.ahnlab.vagent.service;

import com.ahnlab.vagent.base.RuntimeProperties;
import com.ahnlab.vagent.model.*;
import com.ahnlab.vagent.utils.JsonUtils;
import com.ahnlab.vagent.vo.TaskJsonVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Getter
@Setter
public class AgentService {

    private static final Logger LOGGER = LogManager.getLogger(AgentService.class);
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    private Properties runtimeProperties = RuntimeProperties.getInstance();
    private List<Agent> agents = new ArrayList<>();

    public void register() {

        if (this.agents.size() > 0) {
            this.agents.clear();
        }

        String jsonPath = runtimeProperties.getProperty("config.path");

        List<Agent.Auth> authList = JsonUtils.generateBaseDataAgentAuth(
                String.format("%s%s", runtimeProperties.getProperty("fff"),
                        Task.Action.AGENT_REGISTER.getJsonData()[0].getFilePath()));

        for (Agent.Auth auth : authList) {
            Agent agent = new Agent(auth);
            agent.getProducts().put(Product.Type.AC, ProductFactory.getProduct(agent, Product.Type.AC));
            agent.getProducts().put(Product.Type.HIPS, ProductFactory.getProduct(agent, Product.Type.HIPS));

            for (Task.Action.JsonData jsonData : agent.getProducts().get(Product.Type.AC).getTaskType().getJsonData()) {
                TaskJsonVO workerVO = JsonUtils.generateWorkerVO(String.format("%s%s", jsonPath, jsonData.getFilePath()));
                new Worker(agent.getProducts().get(Product.Type.AC), jsonData, workerVO);
            }

            for (Task.Action.JsonData jsonData : agent.getProducts().get(Product.Type.HIPS).getTaskType().getJsonData()) {
                TaskJsonVO workerVO = JsonUtils.generateWorkerVO(String.format("%s%s", jsonPath, jsonData.getFilePath()));
                new Worker(agent.getProducts().get(Product.Type.HIPS), jsonData, workerVO);
            }

            this.agents.add(agent);
        }
    }

    public void execute() {
        for (Agent agent : this.agents) {
            executorService.execute(agent);
        }
    }
}
