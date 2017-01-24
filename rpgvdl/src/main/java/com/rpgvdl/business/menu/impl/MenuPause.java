/**
 *
 */
package com.rpgvdl.business.menu.impl;

import com.rpgvdl.business.Board;
import com.rpgvdl.system.impl.InstanceManager;
import com.rpgvdl.system.impl.Logger;
import com.rpgvdl.system.IInstanceManager;


/**
 * @author 20002845
 * @date 8 oct. 2015
 * 
 */
public class MenuPause
{

    private Board board = null;
    Logger log = new Logger(this.getClass());

    public MenuPause(){
        board = (Board)InstanceManager.getInstance(IInstanceManager.BOARD);
    }

    public void cancel(){
        board.pause();
    }    
    public void quit(){
        log.logInfo("End of program");
        System.exit(0);
    }    
    public void save(){
        log.logInfo("method save is not implemented");
        throw new UnsupportedOperationException("");
    }
}

