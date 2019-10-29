package com.ahnlab.vagent.base;

public enum TaskDataHips implements TaskData {

    AGENT_EVENT("/api/agent/ems/ac/agent/log", "json/product/hips/add_hips_agent_event_log.json"),
    FW_EVENT("/api/agent/ems/hips/fw/log", "json/product/hips/add_hips_fw_event_log.json"),
    IPS_DETECT_EVENT("/api/agent/ems/hips/detect/log", "json/product/hips/add_hips_detect_event_log.json"),
    STATUS_EVENT("/api/agent/ems/hips/status", "json/product/hips/status_hips_event_log.json");

    private final String url;
    private final String filePath;

    TaskDataHips(String url, String filePath) {
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
