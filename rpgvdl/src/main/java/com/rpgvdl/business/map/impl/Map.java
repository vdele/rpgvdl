package com.rpgvdl.business.map.impl;

import com.rpgvdl.business.map.IEvent;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// TODO pourri comme nom
public class Map implements Serializable{

    private int[][] map = null;

    public transient BufferedImage[] IMG_CASE = null;

    public int CASE_SIZE = 32;

    private List<IEvent> events = null;
    private List<Porte> portes = null;

    public Map(final int[][] map) {
        this.map = map;
    }

    public int getHeight(){
        return map.length;
    }

    public BufferedImage getImageRepresentation(final int col, final int lig) {
        return IMG_CASE[map[col][lig]];
    }

    public int[][] getMap() {
        return map;
    }

    public int getMapLengthInPixels() {
        return map.length*CASE_SIZE;
    }

    public int getMapWidthInPixels() {
        return map[0].length * CASE_SIZE;
    }

    public int getWidth(){
        return map[0].length;
    }

    public void setMap(final int[][] map) {
        this.map = map;
    }


    public  void addEvent(final IEvent evt){
        if(events == null) {
            events = new ArrayList<IEvent>();
        }
        events.add(evt);
    }

    public List<IEvent> getEvents(){
        return events;
    }


    public  void addPorte(final Porte porte){
        if(portes == null) {
            portes = new ArrayList<Porte>();
        }
        portes.add(porte);
    }

    public List<Porte> getPortes(){
        return portes;
    }

}
