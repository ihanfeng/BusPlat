
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.RateLimiter;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.node.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Kyle on 12/9/14.
 */
public class UserThread implements Runnable {
    private Client client;
    private BulkProcessor bulkProcessor;
    private RateLimiter messageRateLimiter;
    private int bulkSize;
    private final long id;
    private boolean stop = false;
    private long ctr = 0;

    private int concurrentBulkRequestsPerUser;
    private int flushSizeInMB;
    private int flushTimeInSeconds;
    private final long startTime = System.currentTimeMillis();

    public UserThread(long id, RateLimiter messageRateLimiter, Client client, int bulkSize, int concurrentBulkRequestsPerUser, int flushSizeInMB, int flushTimeInSeconds) {
        this.id = id;
        this.messageRateLimiter = messageRateLimiter;
        this.client = client;
        this.bulkSize = bulkSize;
        this.concurrentBulkRequestsPerUser = concurrentBulkRequestsPerUser;
        this.flushSizeInMB = flushSizeInMB;
        this.flushTimeInSeconds = flushTimeInSeconds;
    }

    @Override
    public void run() {
        this.bulkProcessor = BulkProcessor.builder(
                client,
                new BulkProcessor.Listener() {

                    @Override
                    public void beforeBulk(long executionId, BulkRequest request) {
                        System.out.printf("Sending bulk request from thread %s with %s docs - %s\n", id, request.numberOfActions(), ctr);
                    }

                    @Override
                    public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {

                    }

                    @Override
                    public void afterBulk(long executionId, BulkRequest request, Throwable failure) {

                    }
                }
        )
                .setBulkActions(bulkSize)
                .setBulkSize(new ByteSizeValue(flushSizeInMB, ByteSizeUnit.MB))
                .setFlushInterval(TimeValue.timeValueSeconds(flushTimeInSeconds))
                .setConcurrentRequests(concurrentBulkRequestsPerUser)
                .build();
        try {
            // read the file of tweets line by line in a loop until the ExecutorService shuts us down
            while (!stop) {
                File file = new File("src/main/resources/1ktwittermsgs");
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null && !stop) {
                    messageRateLimiter.acquire();
                    bulkProcessor.add(new IndexRequest("twitter-" + startTime, "tweet").source(line));
                    ctr++;
                }
            }
        } catch (IOException e) {
            System.out.println(Throwables.getStackTraceAsString(e));
        } finally {
            System.out.printf("Closing node for thread %s\n", id);
            bulkProcessor.flush();
            bulkProcessor.close();
            client.close();
        }
    }

    public void stop() {
        this.stop = true;
    }

    public long getCtr() {
        return ctr;
    }

    public long getId() {
        return id;
    }
}