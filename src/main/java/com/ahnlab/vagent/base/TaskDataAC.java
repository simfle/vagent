package com.ahnlab.vagent.base;

public enum TaskDataAC implements TaskData {

    AGENT_EVENT("/api/agent/ems/ac/agent/log", "json/product/ac/add_ac_agent_event_log.json"),
    EXECUTE_EVENT("/api/agent/ems/ac/exec/log", "json/product/ac/add_ac_exec_event_log.json"),
    ACCESS_EVENT("/api/agent/ems/ac/access/log", "json/product/ac/add_ac_access_event_log.json"),
    STATUS_EVENT("/api/agent/ems/ac/status", "json/product/ac/status_ac_event_log.json");

    private final String url;
    private final String filePath;

    TaskDataAC(String url, String filePath) {
        this.url = url;
        this.filePath = filePath;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }
}
