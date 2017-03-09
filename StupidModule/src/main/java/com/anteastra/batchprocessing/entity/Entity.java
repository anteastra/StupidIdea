package com.anteastra.batchprocessing.entity;

/**
 * Created by anteastra on 09.03.2017.
 */
public class Entity {
    public String justName;
    public int id;

    @Override
    public String toString() {
        return id + ": " + justName;
    }
}
