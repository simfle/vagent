package com.ahnlab.vagent.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskConsumer implements Runnable {

    protected static final Logger LOGGER = LogManager.getLogger(TaskConsumer.class);
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private Worker worker;

    public TaskConsumer(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        while (true) {
            while (!worker.getTasks().isEmpty()) {
                Task task = worker.take();
                executorService.execute(task);
            }
        }
    }
}
