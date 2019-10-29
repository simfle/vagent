package com.ahnlab.vagent.task;

import com.ahnlab.vagent.agent.Agent;
import com.ahnlab.vagent.base.TaskData;
import com.ahnlab.vagent.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class EventLogTask {

    protected static final Logger LOGGER = LogManager.getLogger(EventLogTask.class);

    private TaskActionVO taskActionVO = new TaskActionVO();
    protected final String hostUrl = "http://172.20.9.130:8809";

    @Data
    public static class TaskActionVO {

        @JsonProperty
        private Agent.AgentAuth auth = new Agent.AgentAuth();

        @JsonProperty
        private TaskActionVO.ActionData actionData = new TaskActionVO.ActionData();

        @Data
        public static class ActionData {
            private String action;
            private String revision;
            private List<String> params = new ArrayList<>();
            private List<Map<String, Object>> data = new ArrayList<>();
        }

        public TaskActionVO getInstance(Agent agent, String filePath) {
            this.actionData = JsonUtils.generateJsonData(filePath);
            this.auth = agent.getAgentAuth();
            return this;
        }

        public String toJsonString() {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = null;
            try {
                jsonString = objectMapper.writeValueAsString(this);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return jsonString;
        }
    }

    public TaskActionVO getTaskActionVO(Agent agent, String filePath) {
        return taskActionVO.getInstance(agent, filePath);
    }

    public abstract void execute(Agent agent, TaskData taskData);
}
