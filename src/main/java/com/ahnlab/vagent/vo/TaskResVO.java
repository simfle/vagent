package com.ahnlab.vagent.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
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

    public static TaskResVO generateResVO(String result) {
        TaskResVO resVO = new TaskResVO();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            resVO = objectMapper.readValue(result, TaskResVO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resVO;
    }
}
