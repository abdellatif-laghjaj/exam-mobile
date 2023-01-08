package com.abdellatif.exemapp.models;

public class Cooperative {
    private String name;
    private String type;
    private int image;

    public Cooperative(String name, String type, int image) {
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public Cooperative() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
