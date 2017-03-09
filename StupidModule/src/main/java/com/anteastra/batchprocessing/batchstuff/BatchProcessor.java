package com.anteastra.batchprocessing.batchstuff;

import com.anteastra.batchprocessing.RandomString;
import com.anteastra.batchprocessing.entity.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anteastra on 09.03.2017.
 */
public class BatchProcessor {

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

        System.out.println("Batch " + batchNumber + " processing:");
        System.out.println(entities);
        processExternal(entities);
        System.out.println("Batch result:");
        System.out.println(entities);
        System.out.println("---------");
        batchNumber++;
        entities.clear();
    }

    private void processExternal(List<Entity> entities) {
        RandomString rs = new RandomString(5, "batch-" + batchNumber + "-", "");

        for (Entity e: entities) {
            e.justName = rs.nextString();
        }
    }

}
