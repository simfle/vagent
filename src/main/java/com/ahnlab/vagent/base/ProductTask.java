package com.ahnlab.vagent.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ProductTask {


    @Getter
    @RequiredArgsConstructor
    public enum TaskData {
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
}
