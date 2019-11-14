package com.ahnlab.vagent.listener;

import com.ahnlab.vagent.base.RuntimeProperties;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RequiredArgsConstructor
public class ResourceManager {

    private List<ResourceListener> resourceListeners = new ArrayList<>();
    private static boolean runProcession = true;
    private Properties runtimeProperties = RuntimeProperties.getInstance();

    public interface ResourceListener {
        void update();
    }

    public void addResourceListener(ResourceListener resourceListener) {
        this.resourceListeners.add(resourceListener);
    }

    private void notifyListener() {
        for (ResourceListener resourceListener : resourceListeners) {
            resourceListener.update();
        }
    }

    public void start() {
        new Thread(() -> {
            System.out.println("ResourceManager start");
        }).start();
    }
}
