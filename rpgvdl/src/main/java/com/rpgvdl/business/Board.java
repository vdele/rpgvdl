/**
 *
 */
package com.rpgvdl.business;

import java.util.ArrayList;
import java.util.List;

import com.rpgvdl.business.display.messageBox.MessageBox;
import com.rpgvdl.business.map.impl.Map;
import com.rpgvdl.business.map.IEvent;
import com.rpgvdl.entity.event.itf.IPerson;
import com.rpgvdl.system.impl.Logger;
import com.rpgvdl.business.display.menu.IMenu;


/**
 * @author 20002845
 * @date 30 juin 2015
 *
 */
public class Board
{

    Logger log = new Logger(getClass());
    private boolean displayBackground = false;
    private boolean displayCharacter = false;
    private boolean inPause = false;

    private MessageBox messageBox = null;

    public int SCREEN_WIDTH = 400;

    public int SCREEN_HEIGHT = 300;


    public IMenu MENU_PAUSE= null;

    private List<IEvent> events = null;

    private Map map = null;


    public boolean DISPLAY_EVENTS = false;


    private int indMainChar = -1;

    /**
     *
     */
    public void activeBackground() {
        displayBackground = !displayBackground;
    }

    public  void addEvent(final IEvent evt){
        if(events == null) {
            events = new ArrayList<IEvent>();
        }
        events.add(evt);
    }

    public boolean areCharactersDisplayed() {
        return displayCharacter;
    }


    /**
     *
     */
    public void displayCharacter() {
        displayCharacter = !displayCharacter;
    }

    /**
     *
     */
    public  void eventManagement() {
        final IEvent facedEvent = getMainChar().getFacedEvent();
        if(facedEvent !=null){
            facedEvent.activeEvent();
        }
    }

    public  boolean gameInPause(){
        return inPause;
    }

    public  boolean gameIsStopped(){
        return gameInPause() || getMessageBox().messageIsDisplayed();
    }

    public List<IEvent> getEvents(){
        return events;
    }
    /**
     * RORO
     * @return
     */
    public IPerson getMainChar(){

        if(indMainChar==-1){
            for (int i = 0; i < events.size();i++){
                final IEvent evt = events.get(i);
                if(evt instanceof IPerson) {
                    if(((IPerson)evt).isMainChar()) {
                        indMainChar = i;
                    }
                }
            }
        }

        if(indMainChar !=-1 ){
            return (IPerson)events.get(indMainChar);
        }
        return null;

    }



    public Map getMap() {
        return map;
    }


    public boolean isBackgroundDisplayed(){
        return displayBackground;
    }



    /**
     *
     */
    public  void pause() {
        if(!getMessageBox().messageIsDisplayed()){
            inPause = !inPause;
            log.logInfo(inPause ? "Starts pause" : "Ends pause");
            if (inPause) {
                MENU_PAUSE.selectNext();
            }
        }
    }

    public void setMap(final int[][] map) {
        this.map=new Map(map);
    }

    public MessageBox getMessageBox(){
        if(messageBox == null){
            messageBox = new MessageBox();
        }
        return messageBox;
    }


}

