package com.ahnlab.vagent.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TaskType {

    AGENT_REGISTER(new TaskData[]{TaskData.AGENT_REGISTER}),
    AC_EVENT_LOG(new TaskData[]{TaskData.AC_AGENT_EVENT, TaskData.AC_EXECUTE_EVENT, TaskData.AC_ACCESS_EVENT, TaskData.AC_STATUS_EVENT}),
    HIPS_EVENT_LOG(new TaskData[]{TaskData.HIPS_AGENT_EVENT, TaskData.HIPS_FW_EVENT, TaskData.HIPS_IPS_DETECT_EVENT, TaskData.HIPS_STATUS_EVENT});

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

    private final TaskData[] taskData;
}
