/**
 *
 */
package com.rpgvdl.main;

import javax.swing.JFrame;

import com.rpgvdl.business.Board;
import com.rpgvdl.system.gui.Displayer;
import com.rpgvdl.system.impl.InstanceManager;
import com.rpgvdl.system.IInstanceManager;


/**
 * @author 20002845
 * @date Jan 29, 2016
 * 
 */
public class AppViewer
{
    final public JFrame p = new JFrame();


    private Board board = null;
    public AppViewer(){
        board = (Board)InstanceManager.getInstance(IInstanceManager.BOARD);
    }


    public void display(){

        p.setSize(board.SCREEN_WIDTH,board.SCREEN_HEIGHT);
        p.setResizable(false);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Displayer thepan = new Displayer();

        p.add(thepan);

        p.setVisible(true);
    }
}

