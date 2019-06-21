package com.example.aakarsh.truvel.model;

public class CityDataModel {

    public String title;
    public int id,image;

    public CityDataModel(String title, int id, int image) {
        this.title = title;
        this.id = id;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }
}
