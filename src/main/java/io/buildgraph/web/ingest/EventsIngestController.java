package io.buildgraph.web.ingest;

import io.buildgraph.web.model.ProjectDependency;
import io.buildgraph.web.queue.QueueService;
import io.buildgraph.web.support.JsonSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ingest")
@CrossOrigin(value = "*")
public class EventsIngestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventsIngestController.class);

    private final QueueService queueService;

    @Autowired
    EventsIngestController(QueueService queueService) {
        this.queueService = queueService;
    }

    @GetMapping("/")
    public Mono<Map<String, Object>> index() {
        return Mono.just(Map.of("up", "Journey starts here!"));
    }

    @PostMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map<String, Object>> add(@RequestBody ProjectDependency projectDependency) {
        LOGGER.info("project={}, version={}, dependencies={}", projectDependency.getProject(), projectDependency.getVersion(),
            projectDependency.getDependencies().size());
        queueService.offer(JsonSupport.getInstance().toJson(projectDependency));
        return Mono.just(new HashMap<>());
    }

}
