package com.ahnlab.vagent.product;

import com.ahnlab.vagent.agent.Agent;
import com.ahnlab.vagent.agent.AgentTask;
import com.ahnlab.vagent.service.WorkerService;
import com.ahnlab.vagent.task.ProductTask;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Product {

    protected static final Logger LOGGER = LogManager.getLogger(Product.class);

    @Getter
    private  Agent agent;

    @Getter
    private ProductTask[]  productTasks;

    private WorkerService workerService;

    public enum Type {
        AC, HIPS
    }
    public Product(Agent agent, ProductTask[] productTasks){
        this.agent = agent;
        this.productTasks = productTasks;
        this.workerService = new WorkerService(this);
    }

    public void execute() {
        workerService.execute();
    }


    protected ProductType productType;
    private Mode mode;

    public enum Mode {}

    public enum ProductType {
        AC(new AgentTask[]{AgentTask.STATUS_EVENT_LOG_AC}),
        HIPS(new AgentTask[]{AgentTask.STATUS_EVENT_LOG_HIPS});

        private final AgentTask[] agentTasks;

        ProductType(AgentTask[] agentTasks) {
            this.agentTasks = agentTasks;
        }

        public AgentTask[] getAgentTasks() {
            return agentTasks;
        }
    }

    public void taskRun(Agent agent) {
        for (AgentTask agentTask : this.productType.getAgentTasks()) {
            agentTask.getTask().execute(agent, agentTask.getTaskData());
        }
    }
}
