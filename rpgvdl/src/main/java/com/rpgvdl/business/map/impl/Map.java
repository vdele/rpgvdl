package com.rpgvdl.business.map.impl;

import com.rpgvdl.business.map.IEvent;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// TODO pourri comme nom
public class Map implements Serializable{

    // TODO ajouter la liste d'evenement ici

    private int[][] map = null;

    public transient BufferedImage[] IMG_CASE = null;

    public int CASE_SIZE = 32;

    // TODO
    // Il doit s'agir d'une liste de personnages
    // car il faut faire la différence avec
    // les evenements associés à la carte
    private List<IEvent> events = null;

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

}
