package com.tienlam.apporderfood.Models;

public class Food {
    private int id;
    private String name;
    private int price;

    public Food(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Food() {
        // Constructor Không đối
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
