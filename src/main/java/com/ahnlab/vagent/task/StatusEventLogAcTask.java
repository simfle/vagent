package com.ahnlab.vagent.task;

import com.ahnlab.vagent.model.Agent;
import com.ahnlab.vagent.utils.JsonUtils;
import com.ahnlab.vagent.utils.ServerUtils;

public class StatusEventLogAcTask extends AbstractTask {

    private final String requestUrl = "http://ahnlab.com/status/log/ac/task";
    private final String filePath = "product/ac/status_event_log.json";

    @Override
    public void execute(Agent agent) {
        this.requestVO = JsonUtils.generateJsonData(filePath);
        TaskVO taskVO = new TaskVO();
        taskVO.getAuth().setNodeId(agent.getAgentInfo().getNodeId());
        taskVO.getAuth().setSessionKey(agent.getAgentInfo().getSessionKey());
        taskVO.getRequest().setData(requestVO.getData());
        taskVO.getRequest().setAction(requestVO.getAction());
        taskVO.getRequest().setRevision(requestVO.getRevision());
        taskVO.getRequest().setParams(requestVO.getParams());

        ServerUtils.sendServer(requestUrl,taskVO);
    }
}
