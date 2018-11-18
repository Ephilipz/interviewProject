package com.eesaaphilips.interviewproject1.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "product_table")
public class Product {

    @PrimaryKey
    private int id;
    private String name;
    private String description;
    private int price;
    private String imageUrl;
    private int width;
    private int height;

    public Product(int id, String name, String description, int price, String imageUrl, int width, int height) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.width = width;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
