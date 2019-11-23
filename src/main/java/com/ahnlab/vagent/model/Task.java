package com.ahnlab.vagent.model;

import com.ahnlab.vagent.vo.TaskReqVO;
import com.ahnlab.vagent.vo.TaskResVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Getter
@Setter
public class Task implements Runnable {

    protected static final Logger LOGGER = LogManager.getLogger(Task.class);
    public static volatile int count = 0;
    private Action.JsonData taskData;
    private TaskReqVO reqVO;
    private TaskResVO resVO;


    @Getter
    @RequiredArgsConstructor
    public enum Action {

        AGENT_REGISTER(new JsonData[]{JsonData.AGENT_REGISTER}),
        AC_EVENT_LOG(new JsonData[]{JsonData.AC_AGENT_EVENT, JsonData.AC_EXECUTE_EVENT, JsonData.AC_ACCESS_EVENT, JsonData.AC_STATUS_EVENT}),
        HIPS_EVENT_LOG(new JsonData[]{JsonData.HIPS_AGENT_EVENT, JsonData.HIPS_FW_EVENT, JsonData.HIPS_IPS_DETECT_EVENT, JsonData.HIPS_STATUS_EVENT});

        @Getter
        @RequiredArgsConstructor
        public enum JsonData {

            AGENT_REGISTER("/api/agent/ems/agent/register", "json/agent/registerAgent.json"),

            AC_AGENT_EVENT("/api/agent/ems/ac/agent/log", "json/product/ac/add_ac_agent_event_log.json"),
            AC_EXECUTE_EVENT("/api/agent/ems/ac/exec/log", "json/product/ac/add_ac_exec_event_log.json"),
            AC_ACCESS_EVENT("/api/agent/ems/ac/access/log", "json/product/ac/add_ac_access_event_log.json"),
            AC_STATUS_EVENT("/api/agent/ems/ac/status", "json/product/ac/status_ac_event_log.json"),

            HIPS_AGENT_EVENT("/api/agent/ems/ac/agent/log", "json/product/hips/add_hips_agent_event_log.json"),
            HIPS_FW_EVENT("/api/agent/ems/hips/fw/log", "json/product/hips/add_hips_fw_event_log.json"),
            HIPS_IPS_DETECT_EVENT("/api/agent/ems/hips/detect/log", "json/product/hips/add_hips_detect_event_log.json"),
            HIPS_STATUS_EVENT("/api/agent/ems/hips/status", "json/product/hips/status_hips_event_log.json");

            private final String url;
            private final String filePath;
        }

        private final JsonData[] jsonData;
    }

    @Override
    public void run() {
        if (reqVO.getActionData().getData() != null && reqVO.getActionData().getData().size() > 0) {
            for (Map<String, Object> dataMap : reqVO.getActionData().getData()) {
                dataMap.put("node_id", reqVO.getAuth().getNodeId());
                dataMap.put("hash_value", reqVO.getAuth().getNodeId());
            }

            for (int i = 0; i < reqVO.getActionData().getParams().size(); i++) {
                if ("{node_id}".equals(reqVO.getActionData().getParams().get(i))) {
                    reqVO.getActionData().getParams().set(i, reqVO.getAuth().getNodeId());
                }
            }
        }

        count++;
        //LOGGER.info("EventLogTaskExecute Execute {}", reqVO.getActionData());
        //ServerUtils.sendServer(String.format("%s%s", hostUrl, taskData.getUrl()), taskActionVO.toJsonString());
    }
}
