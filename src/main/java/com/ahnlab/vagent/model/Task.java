package com.ahnlab.vagent.model;

import com.ahnlab.vagent.vo.TaskVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Task {

    protected static final Logger LOGGER = LogManager.getLogger(Task.class);
    private Worker worker;

    private TaskVO reqVO = new TaskVO() {

        @JsonProperty("auth")
        private Agent.Auth auth = new Agent.Auth();

        @JsonProperty("request")
        private Worker.WorkerVO.ActionData actionData = new Worker.WorkerVO.ActionData();
    };

    private TaskVO resVO = new TaskVO() {

        @JsonProperty("error_code")
        private String errorCode;

        @JsonProperty("error_msg")
        private String errorMsg;

        @JsonProperty("revision")
        private String revision;

        @JsonProperty("response")
        private List<Map<String, String>> response = new ArrayList<>();
    };

    private class TaskVO {
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

    public Task(TaskVO taskVO) {
        this.taskVO = taskVO;
    }

    public void execute() {
        if (taskVO.getActionData().getData() != null && taskVO.getActionData().getData().size() > 0) {
            for (Map<String, Object> dataMap : taskVO.getActionData().getData()) {
                dataMap.put("node_id", taskVO.getAuth().getNodeId());
            }
        }

        LOGGER.info("EventLogTaskExecute Execute {}", taskVO.getActionData());
        //ServerUtils.sendServer(String.format("%s%s", hostUrl, taskData.getUrl()), taskActionVO.toJsonString());
    }


}
