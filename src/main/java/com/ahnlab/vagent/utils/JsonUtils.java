package com.ahnlab.vagent.utils;

import com.ahnlab.vagent.agent.Agent;
import com.ahnlab.vagent.task.EventLogTask;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final Logger LOGGER = LogManager.getLogger(JsonUtils.class);

    public static List<Agent.AgentAuth> generateBaseDataAgentAuth(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Agent.AgentAuth> agentAuthList = new ArrayList<>();
        try {
            ClassLoader classLoader = JsonUtils.class.getClassLoader();
            agentAuthList = objectMapper.readValue(new File(classLoader.getResource(filePath).getFile()), new TypeReference<List<Agent.AgentAuth>>() {
            });
            /*InputStream inputStream = new FileInputStream(filePath);
            agentAuthList = objectMapper.readValue(inputStream, new TypeReference<List<Agent.AgentAuth>>() {

            });*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agentAuthList;
    }

    public static EventLogTask.TaskActionVO.ActionData generateJsonData(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        EventLogTask.TaskActionVO.ActionData actionData = null;
        try {
            ClassLoader classLoader = JsonUtils.class.getClassLoader();
            actionData = objectMapper.readValue(new File(classLoader.getResource(filePath).getFile()), EventLogTask.TaskActionVO.ActionData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actionData;
    }
}
