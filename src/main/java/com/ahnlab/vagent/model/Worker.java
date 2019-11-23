package com.ahnlab.vagent.model;

import com.ahnlab.vagent.vo.TaskJsonVO;
import lombok.Getter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Getter
public class Worker implements Runnable {

    private final Product product;
    private final Task.Action.JsonData taskData;
    private final TaskJsonVO taskJsonVO;
    private BlockingQueue<Task> tasks = new LinkedBlockingQueue<>();

    public Worker(Product product, Task.Action.JsonData taskData, TaskJsonVO taskJsonVO) {
        this.product = product;
        this.taskData = taskData;
        this.taskJsonVO = taskJsonVO;
        product.getWorkers().put(taskData, this);
    }

    @Override
    public void run() {
        TaskProducer taskProducer = new TaskProducer(this);
        TaskConsumer taskConsumer = new TaskConsumer(this);
        Thread threadProducer = new Thread(taskProducer);
        Thread threadConsumer = new Thread(taskConsumer);
        threadProducer.start();
        threadConsumer.start();
    }

    public void put(Task task) {
        try {
            tasks.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Task take() {
        Task task = null;
        try {
            task = tasks.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return task;
    }
}
