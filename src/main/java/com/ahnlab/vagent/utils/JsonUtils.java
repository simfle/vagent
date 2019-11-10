package com.ahnlab.vagent.utils;

import com.ahnlab.vagent.model.Agent;
import com.ahnlab.vagent.model.Task;
import com.ahnlab.vagent.vo.TaskDataVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final Logger LOGGER = LogManager.getLogger(JsonUtils.class);

    public static List<Agent.Auth> generateBaseDataAgentAuth(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Agent.Auth> agentAuthList = new ArrayList<>();
        try {
            ClassLoader classLoader = JsonUtils.class.getClassLoader();
            agentAuthList = objectMapper.readValue(new File(classLoader.getResource(filePath).getFile()), new TypeReference<List<Agent.Auth>>() {
            });
            /*InputStream inputStream = new FileInputStream(filePath);
            agentAuthList = objectMapper.readValue(inputStream, new TypeReference<List<Agent.AgentAuth>>() {

            });*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agentAuthList;
    }

    public static TaskDataVO generateTaskDataVO(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        TaskDataVO actionData = null;
        try {
            ClassLoader classLoader = JsonUtils.class.getClassLoader();
            actionData = objectMapper.readValue(new File(classLoader.getResource(filePath).getFile()), TaskDataVO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actionData;
    }
}
