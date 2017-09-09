package com.example.materialdesigntest;

/**
 * Created by KunGe on 2017/9/10.
 */

public class Fruit {
    private String name;
    private int imageId;

    public Fruit(String name, int imageId){
        this.imageId = imageId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public Fruit(String name) {
        this.name = name;
    }
}
