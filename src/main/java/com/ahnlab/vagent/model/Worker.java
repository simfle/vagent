package com.ahnlab.vagent.model;

import com.ahnlab.vagent.base.AgentProperties;
import com.ahnlab.vagent.base.ProductTask;
import com.ahnlab.vagent.utils.JsonUtils;
import com.ahnlab.vagent.vo.WorkerVO;
import com.ahnlab.vagent.vo.TaskVO;
import lombok.Getter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Getter
public class Worker implements Runnable {

    private ProductTask productTask;
    private AgentProperties agentProperties = AgentProperties.getInstance();
    private BlockingQueue<Task> taskGroup = new LinkedBlockingQueue<>();

    public Worker(ProductTask productTask) {
        this.productTask = productTask;
    }

    public void registerTask(Agent agent) {
        String configPath = agentProperties.getProperty("vagent.config.path");
        WorkerVO workerVO = JsonUtils.generateWorkerVO(String.format("%s%s", configPath, productTask.getTaskData().getFilePath()));

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
