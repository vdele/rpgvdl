package com.rpgvdl.business.map.impl;

import java.io.Serializable;

/**
 * Created by VDE on 20/02/2017.
 */
public class Porte implements Serializable {

    Coord from;

    Coord to;

    int mapNumber;


    public Coord getFrom() {
        return from;
    }

    public void setFrom(Coord from) {
        this.from = from;
    }

    public Coord getTo() {
        return to;
    }

    public void setTo(Coord to) {
        this.to = to;
    }

    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }
}
