package com.rpgvdl.business.display.messageBox;

import com.rpgvdl.business.Board;
import com.rpgvdl.system.IInstanceManager;
import com.rpgvdl.system.impl.InstanceManager;
import com.rpgvdl.system.impl.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VDE on 30/01/2017.
 */
public class MessageBox {

    protected Logger log = new Logger(this.getClass());

    private List<String> messageText = new ArrayList<String>();

    public MessageBox() {
    }

    public  void disableMsgBox(){
        messageText.remove(0);
    }

    public  String getMsgBoxText(){
        if(messageText.size()>0)
            return messageText.get(0);
        else return null;
    }

    /**
     * @param text
     */
    public  void showMsgBox(final String text) {
        Board board = getBoard();
        if(!board.gameInPause()){
            log.logInfo("Displaying message : @", text);
            messageText.add(text);
        }
    }

    private Board board = null;

    private Board getBoard(){
        if(board == null)
            board = (Board) InstanceManager.getInstance(IInstanceManager.BOARD);

        return board;
    }

    public  boolean messageIsDisplayed(){
        return messageText.size()>0;
    }



}
