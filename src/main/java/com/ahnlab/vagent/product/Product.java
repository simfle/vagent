package com.ahnlab.vagent.product;

import com.ahnlab.vagent.agent.Agent;
import com.ahnlab.vagent.agent.AgentTask;
import com.ahnlab.vagent.task.EventLogTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class Product {

    protected static final Logger LOGGER = LogManager.getLogger(Product.class);

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
            agentTask.getEventLogTask().execute(agent, agentTask.getTaskData());
        }
    }

    public static Product createAC() {
        ProductAC product = new ProductAC();
        product.productType = ProductType.AC;
        return product;
    }

    public static Product createHips() {
        ProductHips product = new ProductHips();
        product.productType = ProductType.HIPS;
        return product;
    }

    public abstract Product updateMode();
}
