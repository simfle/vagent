package com.ahnlab.vagent.listener;

import com.ahnlab.vagent.base.RuntimeProperties;
import lombok.RequiredArgsConstructor;

import java.io.IOException;


import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RequiredArgsConstructor
public class ResourceManager {

    private List<ResourceListener> resourceListeners = new ArrayList<>();
    private static boolean runProcession = true;
    private Properties runtimeProperties = RuntimeProperties.getInstance();
    private boolean runProcessing = true;

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

    /*private void registerRecursive(final Path path, final WatchService watchService) throws IOException {
        Files.walkFileTree(path, (SimpleFileVisitor) preVisitDirectory(dir, attrs) ->{
            dir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            return FileVisitResult.CONTINUE;
        });
    }*/

    public void start() {
        new Thread(() -> {
            try {
                WatchService watchService = FileSystems.getDefault().newWatchService();
                String configPath = runtimeProperties.getProperty("config.path");
                Path path = Paths.get(configPath);
                path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
                //registerRecursive(path, watchService);
                while (runProcessing) {
                    WatchKey watchKey = watchService.take();
                    Thread.sleep(10000);
                    notifyListener();
                    if (!watchKey.reset()) {
                        try {
                            watchService.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
