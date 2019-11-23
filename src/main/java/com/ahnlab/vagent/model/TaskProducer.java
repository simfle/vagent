package com.ahnlab.vagent.model;

import com.ahnlab.vagent.vo.TaskJsonVO;
import com.ahnlab.vagent.vo.TaskReqVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaskProducer implements Runnable {

    protected static final Logger LOGGER = LogManager.getLogger(TaskProducer.class);
    private final Worker worker;
    private final int count = 30;

    public TaskProducer(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        int send = 0;
        while (true) {
            while (count > send) {
                for (TaskJsonVO.ActionData actionData : worker.getTaskJsonVO().getActionDataList()) {
                    TaskReqVO reqVO = new TaskReqVO();
                    reqVO.setAuth(worker.getProduct().getAgent().getAuth());
                    reqVO.setActionData(actionData);
                    Task task = new Task();
                    task.setReqVO(reqVO);
                    task.setTaskData(worker.getTaskData());
                    worker.put(task);
                }
            }
            send++;
        }
    }
}
