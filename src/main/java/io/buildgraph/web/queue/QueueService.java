package io.buildgraph.web.queue;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class QueueService {
    private final RBlockingQueue<String> items;
    private final long timeout;

    @Autowired
    QueueService(@Value("${queue.server}") String url, @Value("${workload.poll.timeout}") long timeout) {
        Config config = new Config();
        config.useSingleServer().setAddress(url);
        RedissonClient redisson = Redisson.create(config);
        items = redisson.getBlockingQueue("workload");
        this.timeout = timeout;
    }

    public boolean offer(String item) {
        return items.offer(item);
    }

    public String poll() {
        try {
            return items.poll(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            return null;
        }
    }
}
