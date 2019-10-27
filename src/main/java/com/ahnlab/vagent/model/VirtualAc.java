package com.ahnlab.vagent.model;

import com.ahnlab.vagent.task.ExecuteEventLogAcTask;
import com.ahnlab.vagent.task.StatusEventLogAcTask;
import lombok.Data;

@Data
public class VirtualAc extends VirtualProduct {
    public VirtualAc() {
        taskList.add(new ExecuteEventLogAcTask());
        taskList.add(new StatusEventLogAcTask());
    }
}
