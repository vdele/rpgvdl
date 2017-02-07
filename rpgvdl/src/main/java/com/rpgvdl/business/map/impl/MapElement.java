package com.rpgvdl.business.map.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import com.rpgvdl.business.map.IMapElement;
import com.rpgvdl.business.Board;
import com.rpgvdl.system.IConfig;
import com.rpgvdl.system.impl.Logger;
import com.rpgvdl.system.manager.RPGVDLManager;

import javax.imageio.ImageIO;


public class MapElement implements IMapElement,Serializable {

    protected Logger log = new Logger(this.getClass());

    private int height = 32;
    private int width = 32;
    private Coord coord = new Coord(60,110);
    private int imgRepresentation = 1;

    private transient BufferedImage[] TILE_CHAR = null;


    protected Board board = null;

    public MapElement() {
        board = RPGVDLManager.getBoard();
    }

    public final int getHeight() {
        return height;
    }


    public Integer getImgRepresentation(){
        return imgRepresentation;
    }

    public final BufferedImage[] getTileChar() {
        if(TILE_CHAR == null)
            TILE_CHAR = constructTileChar();
        return TILE_CHAR;
    }

    public BufferedImage[] constructTileChar(){
        final BufferedImage[] tabCaseImg = new BufferedImage[12];
        try {
            // TODO
            // pour l'instant fonctionne car juste le personnege principal
            // a un fichier tile, chaque MapElement va devoir avoir un nom de fichier
            // tile.
            final BufferedImage img = ImageIO.read(new File(RPGVDLManager.getConfig().getTileCharSet()));
            for (int i = 0; i < 12; i++) {
                final int y = i / 3;
                int x = i % 3 - 1;
                x = x != -1 ? x : 2;
                final BufferedImage subImg = img.getSubimage(x * getWidth(), y * getHeight(), getWidth(), getHeight());
                tabCaseImg[i] = subImg;
            }
        }
        catch(IOException e ){
            log.logError("Unable to get tile file :@",e);
        }
        return tabCaseImg;
    }

    public BufferedImage getTileRepresentation(){
        log.logTrace("Tile_Char[@]", imgRepresentation);
        return TILE_CHAR[imgRepresentation];
    }

    public final int getWidth() {
        return width;
    }


    /* (non-Javadoc)
     * @see entity.person.IEvent#maxX()
     */
    public final int maxX() {
        return coord.getX() + width;
    }


    public final int maxY() {
        return coord.getY() + height;
    }

    /* (non-Javadoc)
     * @see entity.person.IEvent#minX()
     */
    public final int minX() {
        return coord.getX();
    }

    /* (non-Javadoc)
     * @see entity.person.IEvent#minY()
     */
    public final int minY() {
        return coord.getY();
    }


    public final void setHeight(final int height) {
        this.height = height;
    }

    public void setImgRepresentation(final Integer imgRepresentation){
        this.imgRepresentation = imgRepresentation;
    }

    public final void setTileChar(final BufferedImage[] tILE_CHAR) {
        TILE_CHAR = tILE_CHAR;
    }

    public final void setWidth(final int width) {
        this.width = width;
    }

    public final void setCoord(final int x, final int y){
        setX(x);
        setY(y);
    }

    /*
     * (non-Javadoc)
     * @see entity.person.itf.IEvent#setX(int)
     */
    public final void setX(final int x) {
        this.coord.setX(x);
    }

    /*
     * (non-Javadoc)
     * @see entity.person.itf.IEvent#setY(int)
     */
    public final void setY(final int y) {
        this.coord.setY(y);
    }

}
