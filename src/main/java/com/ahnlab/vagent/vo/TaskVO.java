package com.ahnlab.vagent.vo;

import com.ahnlab.vagent.model.Agent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class TaskVO {

    @JsonProperty("auth")
    private Agent.Auth auth = new Agent.Auth();

    @JsonProperty("request")
    private TaskDataVO.ActionData actionData = new TaskDataVO.ActionData();

    public String toJsonString(){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
             jsonString = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonString;
    }


}
