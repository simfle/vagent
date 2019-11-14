package com.ahnlab.vagent.model;

import com.ahnlab.vagent.base.RuntimeProperties;
import com.ahnlab.vagent.utils.JsonUtils;
import com.ahnlab.vagent.vo.TaskVO;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Getter
@RequiredArgsConstructor
public class Worker implements Runnable {

    private final Product product;
    private final TaskType.TaskData taskData;
    private final WorkerVO workerVO;
    private RuntimeProperties runtimeProperties = RuntimeProperties.getInstance();
    private BlockingQueue<Task> taskGroup = new LinkedBlockingQueue<>();

    @Data
    public static class WorkerVO {

        private List<ActionData> actionDataList = new ArrayList<>();

        @Data
        public static class ActionData {
            private String action;
            private String revision;
            private List<String> params = new ArrayList<>();
            private List<Map<String, Object>> data = new ArrayList<>();
        }
    }

    public void registerTask(Agent agent) {
        String configPath = runtimeProperties.getProperty("vagent.config.path");
        WorkerVO workerVO = JsonUtils.generateWorkerVO(String.format("%s%s", configPath, taskData.getFilePath()));

        for (WorkerVO.ActionData actionData : workerVO.getActionDataList()) {
            TaskVO taskVO = new TaskVO();
            taskVO.setAuth(agent.getAuth());
            taskVO.setActionData(actionData);
            Task task = new Task(taskVO);
            task.setWorker(this);
            this.taskGroup.add(task);
        }
    }

    @Override
    public void run() {
        for (Task task : this.taskGroup) {
            task.execute();
        }
    }
}
