package com.anteastra.batchprocessing.batchstuff;

import com.anteastra.batchprocessing.RandomString;
import com.anteastra.batchprocessing.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by anteastra on 09.03.2017.
 */
public class TaskToExecute implements Callable<Void> {

    private List<Entity> entities;
    private int number;
    private String initStr;

    public TaskToExecute(List<Entity> entities, int number, String initStr) {
        this.entities = new ArrayList<>();
        for (Entity e: entities) {
            this.entities.add(e);
        }
        this.number = number;
        this.initStr = initStr;
    }

    @Override
    public Void call() throws Exception {

        Thread.sleep(1000);
        System.out.println("---------");
        System.out.println("Batch " + number + " processing:");
        System.out.println(entities);
        processExternal(entities);
        System.out.println("Batch result:");
        System.out.println(entities);
        System.out.println("---------");
        return null;
    }


    private void processExternal(List<Entity> entities) {
        RandomString rs = new RandomString(5, initStr +"-" + number + "-", "");

        for (Entity e: entities) {
            e.justName = rs.nextString();
        }
    }

}
