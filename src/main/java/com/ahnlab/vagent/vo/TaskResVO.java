package com.ahnlab.vagent.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class TaskResVO extends AbstractVO {

    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("error_msg")
    private String errorMsg;

    @JsonProperty("revision")
    private String revision;

    @JsonProperty("response")
    private List<Map<String, String>> response = new ArrayList<>();
}
