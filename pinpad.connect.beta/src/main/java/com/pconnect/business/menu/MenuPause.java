/**
 *
 */
package com.pconnect.business.menu;

import com.pconnect.factory.gui.Board;
import com.pconnect.factory.running.InstanceManager;
import com.pconnect.factory.running.Logger;
import com.pconnect.factory.running.itf.IInstanceManager;


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

