package com.ahnlab.vagent.base;

import com.ahnlab.vagent.model.Task;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductTask {

    AGENT_REGISTER(new Task(), TaskData.AGENT_REGISTER),

    //AC
    AGENT_EVENT_LOG_AC(new Task(), TaskData.AC_AGENT_EVENT),
    EXECUTE_EVENT_LOG_AC(new Task(), TaskData.AC_EXECUTE_EVENT),
    //ACCESS_EVENT_LOG_AC(new Task(new TaskVO(), "https://172.21.30.219:8804", TaskData.AGENT_REGISTER)),
    //STATUS_EVENT_LOG_AC(new Task(new TaskVO(), "https://172.21.30.219:8804", TaskData.AGENT_REGISTER)),

    //HIPS
    AGENT_EVENT_LOG_HIPS(new Task(), TaskData.HIPS_AGENT_EVENT);
    //STATUS_EVENT_LOG_HIPS(new Task(new TaskVO(), "https://172.21.30.219:8804", TaskData.AGENT_REGISTER));


    @Getter
    @RequiredArgsConstructor
    public enum TaskData {

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

    private final Task eventLogTask;
    private final TaskData taskData;
}
