/**
 *
 */
package com.pconnect.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;

import com.pconnect.entity.event.bo.Person;
import com.pconnect.entity.event.itf.IEvent;
import com.pconnect.entity.event.itf.IPerson;
import com.pconnect.factory.gui.Board;
import com.pconnect.factory.gui.Map;
import com.pconnect.factory.parsing.ConfigData;
import com.pconnect.factory.parsing.MapData;
import com.pconnect.factory.running.InstanceManager;
import com.pconnect.factory.running.Logger;
import com.pconnect.factory.running.Menu;
import com.pconnect.factory.running.itf.IConfig;
import com.pconnect.factory.running.itf.IInstanceManager;
import com.pconnect.factory.util.Invoker;


/**
 * @author 20002845
 * @date Jan 29, 2016
 *
 */
public class AppLoader
{
    private static Logger log = new Logger(AppLoader.class);

    /**
     * TODO define instance in file and read it to create instance
     */
    private static void loadSupport(){
        InstanceManager.addInstance(IInstanceManager.CONFIG, "com.pconnect.factory.running.Config");

        AppLoader.log.logInfo("Config class has been instanciated");
        InstanceManager.addInstance("Board", "com.pconnect.factory.gui.Board");
        AppLoader.log.logInfo("Board class has been instanciated");
        InstanceManager.addInstance("EventHelper", "com.pconnect.entity.event.EventHelper");
        AppLoader.log.logInfo("Board class has been instanciated");
    }

    private Board board = null;;

    private IConfig config = null;

    public AppLoader(){
        loadSupport();
        board = (Board) InstanceManager.getInstance(IInstanceManager.BOARD);
        config = (IConfig) InstanceManager.getInstance(IInstanceManager.CONFIG);
        // log need config to be usable

    }

    public void defineMenuPause(final List<String> menuPause){
        if(menuPause!=null && menuPause.size()>0){
            board.MENU_PAUSE = new Menu();
            for(final String menuItemLabel : menuPause){
                final String[] info = menuItemLabel.split("=");
                final String name=info[0];
                if(name.equals("menu")) {
                    final String[] partToParse = info[1].split("\\|");
                    final String label = partToParse[0].split(":")[1];
                    final String className = partToParse[1].split(":")[1];
                    board.MENU_PAUSE.setTitle(label);
                    board.MENU_PAUSE.setClassName(className);
                } else {
                    final String[] partToParse = info[1].split("\\|");
                    final String label = partToParse[0].split(":")[1];
                    final String action = partToParse[1].split(":")[1];
                    board.MENU_PAUSE.addItemMenu(label,name,action);
                }
            }
        }
    }

    /**
     * @throws IOException
     *
     */
    private void setupCharacter() throws IOException {
        final IPerson person = new Person("Dupont", "Gerard",100);
        person.setMainChar(true);
        final Hashtable<String, String> applicationParameters = config.getApplicationParameters();

        // TODO : parametrer
        // TODO : Secure : Beware of nullPointerException
        person.setHeight(Integer.parseInt(applicationParameters.get(IConfig.HEIGHT_CHAR)));
        person.setWidth(Integer.parseInt(applicationParameters.get(IConfig.WIDTH_CHAR)));
        final BufferedImage img = ImageIO.read(new File(config.getTileCharSet()));
        final BufferedImage[] tabCaseImg = new BufferedImage[12];
        for(int i = 0 ; i < 12;i++){
            final int y = i/3;
            int x = i%3-1;
            x=x!=-1?x:2;
            final BufferedImage subImg = img.getSubimage(x*person.getWidth(), y*person.getHeight(),person.getWidth(), person.getHeight());
            tabCaseImg[i] = subImg;
        }

        person.setTileChar( tabCaseImg);
        board.addEvent(person);
    }

    /**
     * @throws Exception
     *
     */
    private void setupConfig() throws Exception {
        final ConfigData cfgData = new ConfigData();
        config.defineLogLevel(cfgData.getConfigValue("logLevel"));
        //        tileCharFile=tiles/tilechars.png
        config.setTileCharSet(cfgData.getConfigValue("tileCharFile"));
        config.setApplicationParameters(cfgData.getAllParams());
        defineMenuPause(cfgData.getMenuPauseInformation());
        AppLoader.log.logInfo("Configuration has succesfully been set.");

    }

    /**
     * @param mapData
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    private void setupEventsMap(final MapData mapData) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        final List<Hashtable<String, String>> events = mapData.getEvents();

        final Map map = board.getMap();
        if(events!= null && events.size()>0){
            for(final Hashtable<String, String> evt : events){
                final IEvent newEvt = (IEvent) Invoker.createInstance(evt.get("TypeEvent"));
                newEvt.setHeight(map.CASE_SIZE);
                newEvt.setWidth(map.CASE_SIZE);
                final int x = Integer.parseInt(evt.get("CoordX"));
                final int y = Integer.parseInt(evt.get("CoordY"));
                newEvt.setX(map.CASE_SIZE * x);
                newEvt.setY(map.CASE_SIZE * y);
                board.addEvent(newEvt);
            }
        }

    }

    /**
     * @throws Exception
     *
     */
    private void setupMap() throws Exception {
        final MapData mapData = new MapData();
        board.setMap(mapData.getMap());

        final Map map = board.getMap();
        final BufferedImage img = ImageIO.read(new File(mapData.getNameOfTile()));
        map.CASE_SIZE = mapData.getCaseSize();
        final int nbcar = img.getHeight() * img.getWidth() / (map.CASE_SIZE * map.CASE_SIZE);
        final BufferedImage[] tabCaseImg = new BufferedImage[nbcar];
        for(int i = 0 ; i < tabCaseImg.length;i++){
            final int y = i/4;

            int x = i%4-1;
            x=x!=-1?x:3;
            final BufferedImage subImg = img.getSubimage(x * map.CASE_SIZE, y * map.CASE_SIZE, map.CASE_SIZE, map.CASE_SIZE);

            tabCaseImg[i] = subImg;
        }

        map.IMG_CASE = tabCaseImg;

        setupEventsMap(mapData);

        AppLoader.log.logInfo("Map has succesfully been set.");


    }


    public void start() throws Exception{
        setupConfig();
        setupMap();
        setupCharacter();

    }
}

