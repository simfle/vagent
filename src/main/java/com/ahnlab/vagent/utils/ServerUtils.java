package com.ahnlab.vagent.utils;

import com.ahnlab.vagent.task.TaskVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerUtils {

    private static final Logger LOGGER = LogManager.getLogger(ServerUtils.class);

    public static void sendServer(String requestUrl, TaskVO taskVO) {
        LOGGER.info("## sendServer sendUrl {} ====> taskVO : {}", requestUrl, taskVO);
    }
}
