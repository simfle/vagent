package com.ahnlab.vagent.task;

import com.ahnlab.vagent.model.Agent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTask {

    protected static final Logger LOGGER = LogManager.getLogger(AbstractTask.class);

    protected RequestVO requestVO = new RequestVO();

    @Data
    public static class RequestVO {
        @JsonProperty("action")
        private String action;
        @JsonProperty("revision")
        private String revision;
        @JsonProperty("params")
        private List<String> params = new ArrayList<>();
        @JsonProperty("data")
        private List<String> data = new ArrayList<>();
    }

    public abstract void execute(Agent agent);
    //public abstract RequestVO generateJsonData();
}
