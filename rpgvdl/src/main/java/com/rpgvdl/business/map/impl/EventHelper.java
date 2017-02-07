package com.rpgvdl.business.map.impl;

import java.util.List;

import com.rpgvdl.business.map.IEvent;
import com.rpgvdl.business.map.IEventHelper;
import com.rpgvdl.business.map.IMobileEvent;
import com.rpgvdl.entity.event.itf.IPerson;
import com.rpgvdl.business.Board;
import com.rpgvdl.system.manager.RPGVDLManager;

public class EventHelper implements IEventHelper {

    protected Board board = null;

    public EventHelper() {
        board = RPGVDLManager.getBoard();
    }

    public int calculateNewValue(final IMobileEvent evt, final boolean horizontalMovement, final int increaseMov, final int movement) {
        if (horizontalMovement) {
            return evt.minX() + increaseMov * movement;
        } else {
            return evt.minY() + increaseMov * movement;
        }
    }

    /**
     * Extract all event excepted main character
     *
     * @param xPix
     * @param yPix
     * @return
     */
    public IEvent getEvent(final IMobileEvent currentEvt, final int xPix, final int yPix) {
        final List<IEvent> listEvt = board.getMap().getEvents();
        if (listEvt != null) {
            for (final IEvent evt : listEvt) {
                if (evt != null && (!(evt instanceof IPerson) || evt instanceof IPerson && !((IPerson) evt).isMainChar())) {
                    if (evt.minX() < xPix + currentEvt.getWidth() && evt.maxY() > yPix && evt.minY() < yPix + currentEvt.getHeight()
                    && evt.maxX() > xPix) {
                        return evt;
                    }
                }

            }
        }
        return null;
    }

    /**
     * TODO can be extracted in another helper class
     *
     * @param horizontalMovement
     * @param newValue
     * @return
     */
    public boolean isInTheWall(final IMobileEvent evt, final boolean horizontalMovement, final int newValue) {
        int xPix;
        int yPix;
        if (horizontalMovement) {
            xPix = newValue;
            yPix = evt.minY();
        } else {
            xPix = evt.minX();
            yPix = newValue;
        }
        final Map map = board.getMap();
        if (yPix + evt.getHeight() >= map.getMapLengthInPixels() || xPix + evt.getWidth() >= map.getMapWidthInPixels() || xPix < 0 || yPix < 0) {
            return true;
        } else if (getEvent(evt, xPix, yPix) != null) {
            return true;
        } else {
            return false;
        }

    }

}
