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

    private static boolean runProcessing = true;
    private Properties runtimeProperties = RuntimeProperties.getInstance();
    private final static List<AbstractListener> ABSTRACT_LISTENERS = new ArrayList<>();

    public static void addListener(AbstractListener abstractListener) {
        ABSTRACT_LISTENERS.add(abstractListener);
    }

    public interface AbstractListener {
        void update();
    }

    private void notifyListener() {
        for (AbstractListener abstractListener : ABSTRACT_LISTENERS) {
            abstractListener.update();
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
