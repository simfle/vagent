package com.ahnlab.vagent.utils;

import com.ahnlab.vagent.model.Agent;
import com.ahnlab.vagent.vo.TaskJsonVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final Logger LOGGER = LogManager.getLogger(JsonUtils.class);

    public static List<Agent.Auth> generateBaseDataAgentAuth(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Agent.Auth> agentAuthList = new ArrayList<>();
        try {
            InputStream inputStream = new FileInputStream(filePath);
            agentAuthList = objectMapper.readValue(inputStream, new TypeReference<List<Agent.Auth>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agentAuthList;
    }

    public static TaskJsonVO generateWorkerVO(String filePath) {
        TaskJsonVO workerVO = new TaskJsonVO();
        ObjectMapper objectMapper = new ObjectMapper();
        List<TaskJsonVO.ActionData> authList = new ArrayList<>();
        try {
            InputStream inputStream = new FileInputStream(filePath);
            authList = objectMapper.readValue(inputStream, new TypeReference<List<TaskJsonVO.ActionData>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        workerVO.setActionDataList(authList);
        return workerVO;
    }
}
