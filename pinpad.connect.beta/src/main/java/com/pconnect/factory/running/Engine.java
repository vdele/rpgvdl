/**
 *
 */
package com.pconnect.factory.running;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import com.pconnect.factory.gui.Board;
import com.pconnect.factory.running.itf.IInstanceManager;


/**
 * @author 20002845
 * @date 23 juil. 2015
 * 
 */
public class Engine extends Thread{

    Logger log = new Logger(getClass());
    // Set of currently pressed keys
    public static Set<Integer> movement = new HashSet<Integer>();

    public static final int TIME_FRAME = 60; 

    Board board = null;

    public Engine(){
        board = (Board) InstanceManager.getInstance(IInstanceManager.BOARD);
    }

    @Override
    public void run(){
        while(true){
            try{
                if(!board.gameIsStopped()) {
                    moveManaging();
                    log.logTrace("Img representation : @", board.getMainChar().getImgRepresentation()); 

                }
                Thread.sleep(Engine.TIME_FRAME);
            }
            catch(final Exception e ){
                e.printStackTrace();
            }

        }
    }


    /**
     * 
     */
    void moveManaging() {
        log.logTrace("Movement key pressed : @",Engine.movement.toString());
        if(Engine.movement.contains(new Integer(KeyEvent.VK_DOWN))){
            board.getMainChar().down();
        }
        else if(Engine.movement.contains(new Integer(KeyEvent.VK_LEFT))){
            board.getMainChar().left();
        }
        else if(Engine.movement.contains(new Integer(KeyEvent.VK_RIGHT))){
            board.getMainChar().right();
        }
        else if(Engine.movement.contains(new Integer(KeyEvent.VK_UP))){
            board.getMainChar().up();
        }
        if(Engine.movement.size()>0) {
            board.getMainChar().modifyIndRepresentationVariation();
        }
    }

}
