package com.ahnlab.vagent.vo;

import com.ahnlab.vagent.model.Agent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskReqVO extends AbstractVO {

    @JsonProperty("auth")
    private Agent.Auth auth = new Agent.Auth();

    @JsonProperty("request")
    private TaskJsonVO.ActionData actionData = new TaskJsonVO.ActionData();
}
