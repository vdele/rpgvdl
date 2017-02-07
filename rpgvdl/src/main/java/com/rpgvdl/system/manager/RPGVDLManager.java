package com.rpgvdl.system.manager;

import com.rpgvdl.business.Board;
import com.rpgvdl.business.map.IEventHelper;
import com.rpgvdl.business.map.impl.EventHelper;
import com.rpgvdl.system.IConfig;
import com.rpgvdl.system.config.Config;

/**
 * Created by VDE on 02/02/2017.
 */
public class RPGVDLManager {

    private static Board board = null;
    private static IEventHelper eventHelper = null;
    private static IConfig config = null;

    public static Board getBoard(){
        if(board == null)
            board = (Board) InstanceManager.getInstance(IInstanceManager.BOARD);
        return board;
    }

    public static void setNewBoard(){
        InstanceManager.addInstance(IInstanceManager.BOARD, Board.class.getName());
    }

    public static void setExistingBoard(Board board){
        InstanceManager.addInstance(IInstanceManager.BOARD, board);
    }


    public static void setNewConfig(){
        InstanceManager.addInstance(IInstanceManager.CONFIG, Config.class.getName());
    }

    public static IConfig getConfig(){
        if(config == null)
            config = (IConfig) InstanceManager.getInstance(IInstanceManager.CONFIG);
        return config;
    }

    public static void setNewEventHelper() {
        InstanceManager.addInstance(IInstanceManager.EVENT_HELPER, EventHelper.class.getName());
    }

    public static IEventHelper getEventHelper(){
        if( eventHelper == null)
            eventHelper = (IEventHelper) InstanceManager.getInstance(IInstanceManager.EVENT_HELPER);
        return eventHelper;
    }

}
