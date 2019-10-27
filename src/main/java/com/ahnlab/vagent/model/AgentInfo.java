package com.ahnlab.vagent.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AgentInfo {
    @JsonProperty("session_key")
    private String sessionKey;
    @JsonProperty("node_id")
    private String nodeId;
}
