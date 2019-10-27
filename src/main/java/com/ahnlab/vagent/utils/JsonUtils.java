package com.ahnlab.vagent.utils;

import com.ahnlab.vagent.model.AgentInfo;
import com.ahnlab.vagent.task.AbstractTask;
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

    public static List<AgentInfo> generateBaseDataAgentInfo(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AgentInfo> agentInfoList = new ArrayList<>();
        try {
            ClassLoader classLoader = JsonUtils.class.getClassLoader();
            agentInfoList = objectMapper.readValue(new File(classLoader.getResource(filePath).getFile()), new TypeReference<List<AgentInfo>>() {
            });
            LOGGER.info("## AgentBaseData : {}", agentInfoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agentInfoList;
    }

    public static AbstractTask.RequestVO generateJsonData(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        AbstractTask.RequestVO agentRequestVO = null;
        try {
            ClassLoader classLoader = JsonUtils.class.getClassLoader();
            agentRequestVO = objectMapper.readValue(new File(classLoader.getResource(filePath).getFile()), AbstractTask.RequestVO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agentRequestVO;
    }
}
