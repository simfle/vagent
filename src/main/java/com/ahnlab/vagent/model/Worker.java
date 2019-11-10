package com.ahnlab.vagent.model;

import com.ahnlab.vagent.base.ProductTask;
import com.ahnlab.vagent.utils.JsonUtils;
import com.ahnlab.vagent.vo.TaskDataVO;
import com.ahnlab.vagent.vo.TaskVO;
import lombok.Getter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Worker implements Runnable {

    @Getter
    private ProductTask productTask;

    private final String target = "https://172.21.30.219:8804";

    private BlockingQueue<Task> taskGroup = new LinkedBlockingQueue<>();

    public Worker(ProductTask productTask) {
        this.productTask = productTask;
    }

    public void initWorker(Agent agent) {
        TaskDataVO taskDataVO = JsonUtils.generateTaskDataVO(productTask.getTaskData().getFilePath());

        for (TaskDataVO.ActionData actionData : taskDataVO.getActionDataList()) {
            TaskVO taskVO = new TaskVO();
            taskVO.setAuth(agent.getAuth());
            taskVO.setActionData(actionData);
            Task task = new Task();
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
