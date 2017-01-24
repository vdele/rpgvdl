package com.rpgvdl.business.map.impl;



public class Coord {

    private int X;
    private int Y;

    public Coord() {
        this(0, 0);
    }

    public Coord(final int x, final int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public String toString() {
        return X + "." + Y;
    }
}
