/**
 *
 */
package com.rpgvdl.system.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.rpgvdl.business.map.IEvent;
import com.rpgvdl.entity.event.itf.IPerson;
import com.rpgvdl.business.Board;
import com.rpgvdl.business.map.impl.Map;
import com.rpgvdl.system.listener.PadListener;
import com.rpgvdl.system.Engine;
import com.rpgvdl.system.impl.Logger;
import com.rpgvdl.system.IConfig;
import com.rpgvdl.business.display.menu.IItemMenu;
import com.rpgvdl.system.manager.RPGVDLManager;


/**
 * @author 20002845
 * @date 24 juin 2015
 *
 */
public class Displayer extends JPanel implements ActionListener
{
    class DisplayingDimension{
        int boundX,boundY,width,height;

        DisplayingDimension(final int boundX,final int boundY,final int width,final int height){
            this.boundX = boundX;
            this.boundY = boundY;
            this.width = width;
            this.height = height;
        }
    }

    /**
     *
     */
    private static final long serialVersionUID = 4552272332881935593L;

    Logger log = new Logger(getClass());

    Timer timer=new Timer(60, this);
    private Board board = null;

    private IConfig config = null;
    public Displayer(){
        setBackground(Color.gray);
        new Engine().start();
        timer.start();
        setFocusable(true);
        addKeyListener(new PadListener());
        board =  RPGVDLManager.getBoard();
        config = RPGVDLManager.getConfig();

    }

    public void actionPerformed(final ActionEvent ev){
        if(ev.getSource()==timer){
            repaint();// Screen will be repainted every X milliseconds (defined in instanciation of timer object)
        }
    }
    /**
     * @param g
     * @param dim
     */
    private void drawBackground(final Graphics g, final DisplayingDimension dim) {
        setBounds(dim.boundX, dim.boundY,dim.width,dim.height);

        final Map map = board.getMap();
        if (board.isBackgroundDisplayed() && map != null) {
            for (int col = 0; col < map.getHeight(); col++) {
                for (int lig = 0; lig < map.getWidth(); lig++) {
                    g.drawImage(map.getImageRepresentation(col, lig), lig * map.CASE_SIZE, col * map.CASE_SIZE, map.CASE_SIZE, map.CASE_SIZE, null);
                }
            }
        }
    }


    /**
     * @param g
     */
    private void drawCharacters(final Graphics g) {
        if (board.areCharactersDisplayed()) {
            g.drawImage(board.getMainChar().getTileRepresentation(), board.getMainChar().minX(), board.getMainChar().minY(), board.getMainChar().getWidth(), board.getMainChar().getHeight(),null);
        } else {
            g.fillOval( board.getMainChar().minX(), board.getMainChar().minY(), board.getMainChar().getWidth(), board.getMainChar().getHeight());
        }
    }

    /**
     * @param g
     */
    private void drawEvents(final Graphics g) {
        final List<IEvent> lstevt = board.getMap().getEvents();
        if(lstevt!=null){
            for (final IEvent evt : lstevt) {
                if(!board.DISPLAY_EVENTS && evt!=null && !(evt instanceof IPerson)){
                    g.fillRect( evt.minX(), evt.minY(), evt.getWidth(), evt.getHeight());
                }
            }
        }



    }
    /**
     * @param g
     */
    private void drawMenu(final Graphics g) {
        if(board.gameInPause()){
            g.setColor(new Color(.3f, .4f, .5f, .6f));
            final int boxPosX = board.SCREEN_WIDTH*2/3-getBounds().x;
            final int boxPosY = -getBounds().y;
            final int boxWidth = board.SCREEN_WIDTH*1/3;
            final int boxHeight = board.SCREEN_HEIGHT;

            log.logTrace("g.fillRect(@,@,@,@)", boxPosX,  boxPosY, boxWidth, boxHeight);
            g.fillRect(boxPosX,  boxPosY, boxWidth, boxHeight);
            g.setFont(config.getFontMenuTitle());
            g.setColor(Color.black);
            g.drawString(board.MENU_PAUSE.getTitle(), boxPosX+10, boxPosY+20);
            int posYItem = boxPosY+40;
            for(int i = 0 ; i < board.MENU_PAUSE.getItemNumber();i++){
                final IItemMenu item = board.MENU_PAUSE.getItemMenu(i);
                if(item.isSelected()) {
                    g.setColor(Color.white);
                } else {
                    g.setColor(Color.black);
                }
                g.drawString(board.MENU_PAUSE.getItemMenu(i).getLabel(), boxPosX+10, posYItem);
                posYItem=posYItem+20;
            }
        }
    }
    /**
     * @param g
     */
    private void drawMessageBox(final Graphics g) {
        if(board.getMessageBox().messageIsDisplayed()){
            final Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(new BasicStroke( 5.0f ));

            final int boxPosX = -getBounds().x+2;
            final int boxPosY = board.SCREEN_HEIGHT*2/3-getBounds().y;
            final int boxWidth = board.SCREEN_WIDTH-10;
            final int boxHeight = board.SCREEN_HEIGHT*1/3-30;

            log.logTrace("g.fillRect(@,@,@,@)", boxPosX,  boxPosY, boxWidth, boxHeight);
            g2.setColor(new Color(.3f, .4f, .5f, .6f));
            g2.fillRect(boxPosX,  boxPosY, boxWidth, boxHeight);
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawRect(boxPosX,  boxPosY, boxWidth, boxHeight);

            g.setFont(config.getFontMessage());
            g.drawString(board.getMessageBox().getMsgBoxText(), boxPosX+20, boxPosY+20);
        }
    }
    /**
     * @return
     */
    private DisplayingDimension getDisplayingDimension() {
        int boundX = board.SCREEN_WIDTH/2-board.getMainChar().minX();
        int boundY = board.SCREEN_HEIGHT/2-board.getMainChar().minY();

        boundY = boundY>0?0:boundY;
        boundX = boundX>0?0:boundX;

        final Map map = board.getMap();

        int width = -boundX + board.SCREEN_WIDTH;
        int height = -boundY + board.SCREEN_HEIGHT;
        if (width > map.getMapWidthInPixels() + 5) {
            boundX = board.SCREEN_WIDTH - map.getMapWidthInPixels() - 5;
            width = map.getMapWidthInPixels() + 5;
        }

        // 28 to adjust with header of program window
        if (height > map.getMapLengthInPixels() + 28) {
            boundY = board.SCREEN_HEIGHT - map.getMapLengthInPixels() - 28;
            height = map.getMapLengthInPixels() + 28;
        }

        final DisplayingDimension dim = new DisplayingDimension(boundX,boundY,width,height+30);
        return dim;
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(board.SCREEN_WIDTH,board.SCREEN_HEIGHT);
    }


    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final DisplayingDimension dim = getDisplayingDimension();
        drawBackground(g,dim);
        drawCharacters(g);
        drawEvents(g);
        drawMenu(g);
        drawMessageBox(g);

    }

}

