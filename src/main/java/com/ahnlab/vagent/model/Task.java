package com.ahnlab.vagent.model;

import com.ahnlab.vagent.base.ProductTask;
import com.ahnlab.vagent.utils.JsonUtils;
import com.ahnlab.vagent.vo.TaskVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class Task {

    protected static final Logger LOGGER = LogManager.getLogger(Task.class);

    private Worker worker;
    private TaskVO taskVO;

    private ProductTask productTask;

    public void execute() {

        if (taskVO.getActionData().getData() != null && taskVO.getActionData().getData().size() > 0) {
            for (Map<String, Object> dataMap : taskVO.getActionData().getData()) {
                dataMap.put("node_id", taskVO.getAuth().getNodeId());
            }
        }

        LOGGER.info("EventLogTaskExecute Execute {}", taskVO.getActionData());
        //ServerUtils.sendServer(String.format("%s%s", hostUrl, taskData.getUrl()), taskActionVO.toJsonString());
    }

    public void setProductTask(ProductTask productTask) {
        this.productTask = productTask;
    }

    public List<Agent> executeAgent(List<Agent> agentList) {
        List<Agent.Auth> authList = JsonUtils.generateBaseDataAgentAuth(productTask.getTaskData().getFilePath());
        for (Agent.Auth auth : authList) {
            Agent agent = new Agent(auth);
            agentList.add(agent);
        }
        return agentList;
    }
}
