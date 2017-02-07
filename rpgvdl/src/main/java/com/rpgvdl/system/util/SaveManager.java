package com.rpgvdl.system.util;

import com.rpgvdl.business.Board;
import com.rpgvdl.system.manager.RPGVDLManager;

/**
 * Created by VDE on 02/02/2017.
 */
public class SaveManager {

    /**
     * Save the board
     */
    public static void saveGame(){
        Board board = RPGVDLManager.getBoard();
        if(board!=null){
            FileUtils.writeObject(board);
        }
    }


    public static Board loadGame(){
        return (Board) FileUtils.readFile();
    }

}
