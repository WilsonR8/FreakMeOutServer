package com.will.freakmeoutserver.Model;

public class Coord {
    private float x;
    private float y;
    private String type = "Coord";

    public Coord(int x,int y) {
        this.x=x;
        this.y=y;
    }

    public Coord(float x, float y){

    };

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


