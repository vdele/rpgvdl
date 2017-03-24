/**
 *
 */
package com.rpgvdl.business;

import java.io.Serializable;
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
public class Board implements Serializable
{

    private Logger log = new Logger(getClass());
    private boolean displayBackground = false;
    private boolean displayCharacter = false;
    private transient boolean inPause = false;

    private transient MessageBox messageBox = null;

    public int SCREEN_WIDTH = 400;

    public int SCREEN_HEIGHT = 300;


    public transient IMenu MENU_PAUSE= null;

    private List<IPerson> persons = null;

    private List<Map> maps = null;


    public boolean DISPLAY_EVENTS = false;


    private int indMainChar = -1;

    /**
     *
     */
    public void activeBackground() {
        displayBackground = !displayBackground;
    }

    public  void addPerson(final IPerson person){
        if(persons == null) {
            persons = new ArrayList<IPerson>();
        }
        persons.add(person);
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


    public List<IPerson> getPersons(){
        return persons;
    }
    /**
     * RORO
     * @return
     */
    public IPerson getMainChar(){

        if(indMainChar==-1){
            for (int i = 0; i < persons.size();i++){
                final IPerson person = persons.get(i);
                    if(person.isMainChar()) {
                        indMainChar = i;
                }
            }
        }
        if(indMainChar !=-1 ){
            return persons.get(indMainChar);
        }
        return null;

    }



    public Map getCurrentMap() {
        // TODO
        return maps.get(0);
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

    public void addMap(final int[][] map) {

        if(this.maps == null){
            this.maps= new ArrayList<Map>();
        }
        Map m = new Map(map);
        this.maps.add(m);
    }

    public Map getMap(int i){
        return maps.get(i);
    }

    public MessageBox getMessageBox(){
        if(messageBox == null){
            messageBox = new MessageBox();
        }
        return messageBox;
    }


}

