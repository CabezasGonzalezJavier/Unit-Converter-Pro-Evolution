package com.thedeveloperworldisyours.unitconverterpro.sqlite.area;

/**
 * Created by javierg on 14/10/2016.
 */

public class Area {
    private long id;
    private String name;
    private long value;
    private int position;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
