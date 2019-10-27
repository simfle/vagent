package com.ahnlab.vagent.model;

import com.ahnlab.vagent.task.ExecuteEventLogHipsTask;

public class VirtualHips extends VirtualProduct {
    public VirtualHips() {
        taskList.add(new ExecuteEventLogHipsTask());
    }
}
