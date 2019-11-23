package com.ahnlab.vagent.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Getter
@Setter
public class TaskJsonVO extends AbstractVO {

    private List<ActionData> actionDataList = new ArrayList<>();

    @Data
    public static class ActionData {
        private String action;
        private String revision;
        private List<String> params = new ArrayList<>();
        private List<Map<String, Object>> data = new ArrayList<>();
    }
}
