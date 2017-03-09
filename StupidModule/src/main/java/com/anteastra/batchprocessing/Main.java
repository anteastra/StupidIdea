package com.anteastra.batchprocessing;

import com.anteastra.batchprocessing.batchstuff.BatchProcessor;
import com.anteastra.batchprocessing.entity.Entity;

import java.util.*;

/**
 * Created by anteastra on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) {

        List<Entity> list = new ArrayList<Entity>();
        createObjects(8, list);
        System.out.println("Initial list:");
        System.out.println(list);
        System.out.println();

        BatchProcessor processor = new BatchProcessor();

        for (Entity e: list) {
            processor.addToBatchProcess(e);
        }

        System.out.println("---------");
        System.out.println("Total result:");
        System.out.println(list);

//        processObjects();
    }

    private static void createObjects(int count, List<Entity> list) {
        RandomString rs = new RandomString(5, "ini-", "");

        for (int i = 0; i < count; i++) {
            Entity newEntity = new Entity();
            newEntity.id = i;
            newEntity.justName = rs.nextString();
            list.add(newEntity);
        }
    }

}
