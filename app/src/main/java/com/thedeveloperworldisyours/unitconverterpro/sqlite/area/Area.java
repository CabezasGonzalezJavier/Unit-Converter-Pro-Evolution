package com.thedeveloperworldisyours.unitconverterpro.sqlite.area;

/**
 * Created by javierg on 14/10/2016.
 */

public class Area {
    private long id;
    private String name;
    private Double value;
    private long position;

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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }
}
