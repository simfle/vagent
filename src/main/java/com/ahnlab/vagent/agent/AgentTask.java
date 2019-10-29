package com.ahnlab.vagent.agent;

import com.ahnlab.vagent.base.TaskData;
import com.ahnlab.vagent.base.TaskDataAC;
import com.ahnlab.vagent.base.TaskDataHips;
import com.ahnlab.vagent.task.EventLogTask;
import com.ahnlab.vagent.task.EventLogTaskExecute;
import com.ahnlab.vagent.task.EventLogTaskStatus;

public enum AgentTask {
    //AC
    EXECUTE_EVENT_LOG_AC(new EventLogTaskExecute(), TaskDataAC.EXECUTE_EVENT),
    STATUS_EVENT_LOG_AC(new EventLogTaskStatus(), TaskDataAC.STATUS_EVENT),

    //HIPS
    STATUS_EVENT_LOG_HIPS(new EventLogTaskStatus(), TaskDataHips.STATUS_EVENT);

    private final EventLogTask eventLogTask;
    private final TaskData taskData;

    AgentTask(EventLogTask eventLogTask, TaskData taskData) {
        this.eventLogTask = eventLogTask;
        this.taskData = taskData;
    }

    public TaskData getTaskData() {
        return taskData;
    }

    public EventLogTask getEventLogTask() {
        return eventLogTask;
    }
}
