package io.buildgraph.web.builder;


import io.buildgraph.web.queue.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BuilderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BuilderService.class);

    private final QueueService queueService;

    @Autowired
    BuilderService(QueueService queueService) {
        this.queueService = queueService;
    }

    void handleData(String data) {
        LOGGER.info("handle data");
    }

    @PostConstruct
    public void onStartup() {
        Thread th = new Thread(() -> {
            while (true) {
                String data = this.queueService.poll();
                if (data != null) {
                    this.handleData(data);
                }
            }
        });
        th.start();
    }
}
