package com.ahnlab.vagent.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskVO {

    @JsonProperty("auth")
    private AuthData auth = new AuthData();

    @JsonProperty("request")
    private ActionData request = new ActionData();

    @Data
    public class AuthData {
        private String sessionKey;
        private String nodeId;
    }

    @Data
    public class ActionData {
        private String action;
        private String revision;
        private List<String> params = new ArrayList<String>();
        private List<String> data = new ArrayList<String>();
    }
}
