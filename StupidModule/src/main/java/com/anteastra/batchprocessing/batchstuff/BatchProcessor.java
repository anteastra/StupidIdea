package com.anteastra.batchprocessing.batchstuff;

import com.anteastra.batchprocessing.RandomString;
import com.anteastra.batchprocessing.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by anteastra on 09.03.2017.
 */
public class BatchProcessor {

    private static int WAIT_TERMIINATION_SEC = 15;

    private ExecutorService service = Executors.newFixedThreadPool(3);

    private int batchNumber = 1;

    private int threshold = 2;
    private List<Entity> entities = new ArrayList<Entity>();

    public void addToBatchProcess(Entity entity) {
        entities.add(entity);
        if (entities.size() > threshold) {
            processBatch();
        }
    }

    private void processBatch() {
        TaskToExecute task = new TaskToExecute(entities, batchNumber);
        service.submit(task);
        batchNumber++;
        entities.clear();
    }

    public void shutDown() throws InterruptedException {
        service.shutdown();
        service.awaitTermination(WAIT_TERMIINATION_SEC, TimeUnit.SECONDS);
    }

}
