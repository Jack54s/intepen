package com.jack.intepen.entity;

import org.springframework.stereotype.Component;

import java.util.Observable;

/**
 * Created by 11407 on 24/024.
 */
@Component
public class Threshold extends Observable{

    private Integer id;
    private String item;
    private String threshold;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
        this.setChanged();
        this.notifyObservers();
    }
}
