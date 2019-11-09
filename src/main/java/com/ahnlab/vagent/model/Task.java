package com.ahnlab.vagent.task;

import com.ahnlab.vagent.agent.Agent;
import com.ahnlab.vagent.base.TaskData;
import com.ahnlab.vagent.model.Worker;
import com.ahnlab.vagent.product.Product;
import com.ahnlab.vagent.product.ProductAC;
import com.ahnlab.vagent.utils.JsonUtils;
import com.ahnlab.vagent.utils.ServerUtils;
import com.ahnlab.vagent.vo.TaskVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public class Task {

    protected static final Logger LOGGER = LogManager.getLogger(Task.class);
    private Worker worker;
    private TaskVO taskVO;

    private ProductTask productTask;
    public void execute(){

        if(taskVO.getActionData().getData() != null && taskVO.getActionData().getData().size() > 0){
            for(Map<String, Object> dataMap : taskVO.getActionData().getData()){
                dataMap.put("node_id", taskVO.getAuth().getNodeId());
            }
        }

        LOGGER.info("EventLogTaskExecute Execute {}", taskActionVO);
        ServerUtils.sendServer(String.format("%s%s", hostUrl, taskData.getUrl()), taskActionVO.toJsonString());
    }

    public void setProductTask(ProductTask productTask) {
        this.productTask = productTask;
    }

    public List<Agent> executeAgent(List<Agent> agentList){
        List<Agent.AgentAuth> authList = JsonUtils.generateBaseDataAgentAuth(productTask.getTaskData().getFilePath());
        for(Agent.AgentAuth auth : authList){
            Agent agent = new Agent(auth);
            authList.add(agent);
        }
        return agentList;
    }
}
