/**
 *
 */
package com.rpgvdl.business.display.menu.impl;

import com.rpgvdl.business.Board;
import com.rpgvdl.system.impl.Logger;
import com.rpgvdl.system.manager.RPGVDLManager;
import com.rpgvdl.system.util.SaveManager;


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
        board = RPGVDLManager.getBoard();
    }

    public void cancel(){
        board.pause();
    }    
    public void quit(){
        log.logInfo("End of program");
        System.exit(0);
    }    
    public void save(){
        log.logInfo("Saving the game ...");
        SaveManager.saveGame();
    }
}

