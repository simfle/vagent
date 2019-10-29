package com.ahnlab.vagent.task;

import com.ahnlab.vagent.agent.Agent;
import com.ahnlab.vagent.base.TaskData;
import com.ahnlab.vagent.product.Product;
import com.ahnlab.vagent.product.ProductAC;
import com.ahnlab.vagent.utils.ServerUtils;

import java.time.ZonedDateTime;
import java.util.Map;

public class EventLogTaskExecute extends EventLogTask {
    @Override
    public void execute(Agent agent, TaskData taskData) {
        EventLogTask.TaskActionVO taskActionVO = getTaskActionVO(agent, taskData.getFilePath());
        Product product = (agent.getProductAC().updateMode());
        if (taskActionVO.getActionData().getData() != null && taskActionVO.getActionData().getData().size() > 0) {
            for (Map<String, Object> dataMap : taskActionVO.getActionData().getData()) {
                dataMap.put("client_time", ZonedDateTime.now().toInstant().toString());
                dataMap.put("mode", ((ProductAC) product).getMode().toString());
            }
        }
        LOGGER.info("EventLogTaskExecute Execute {}", taskActionVO);
        ServerUtils.sendServer(String.format("%s%s", hostUrl, taskData.getUrl()), taskActionVO.toJsonString());
    }
}



