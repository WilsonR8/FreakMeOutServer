package com.will.freakmeoutserver.Model;

public class Grab{

    private int g;
    private String type ="Grab";



    public Grab( int g) {
        super();
        this.g=g;
    }

    public Grab() {}

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    };

    public String getType(){return type;}

    public void setType(String type){this.type = type;}

}