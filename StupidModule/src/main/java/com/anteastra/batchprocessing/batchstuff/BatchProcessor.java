package com.anteastra.batchprocessing.batchstuff;

import com.anteastra.batchprocessing.RandomString;
import com.anteastra.batchprocessing.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by anteastra on 09.03.2017.
 */
public class BatchProcessor {

    private static int WAIT_TERMIINATION_SEC = 15;

    private ExecutorService service = Executors.newFixedThreadPool(2);

    private ScheduledThreadPoolExecutor scheduledService = new ScheduledThreadPoolExecutor(1);

    Future<Void> future;

    private int batchNumber = 1;

    public BatchProcessor() {
        scheduledService.setRemoveOnCancelPolicy(true);
    }

    private int threshold = 2;
    private List<Entity> entities = new ArrayList<>();

    public void addToBatchProcess(Entity entity) {
        if (entities.isEmpty()) {
            scheduleProcessBatch();
        }
        entities.add(entity);
        if (entities.size() > threshold) {
            future.cancel(false);
            processBatch("batch");
        }
    }

    private void scheduleProcessBatch() {
        SchedTask task = new SchedTask();
        future = scheduledService.schedule(task, 2, TimeUnit.SECONDS);
    }

    private void processBatch(String initStr) {
        TaskToExecute task = new TaskToExecute(entities, batchNumber, initStr);
        service.submit(task);
        batchNumber++;
        entities.clear();
    }

    public void shutDown() throws InterruptedException {
        service.shutdown();
        service.awaitTermination(WAIT_TERMIINATION_SEC, TimeUnit.SECONDS);
        scheduledService.shutdown();
        scheduledService.awaitTermination(WAIT_TERMIINATION_SEC, TimeUnit.SECONDS);
    }

    private class SchedTask implements Callable<Void> {

        @Override
        public Void call() throws Exception {
            processBatch("scheduled");
            return null;
        }
    }
}
