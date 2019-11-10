package com.ahnlab.vagent.service;


import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequiredArgsConstructor
public class AgentExecutor implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(AgentExecutor.class);
    private static boolean runProcessing = true;
    private final AgentService agentService;

    @Override
    public void run() {
        int period = 1000 * 60;
        while(runProcessing){
            try{
                Thread.sleep(period);
                if(this.agentService != null){
                    agentService.execute();
                }else{
                    LOGGER.info("## Agent Service Not Register Agent");
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
