/**
 *
 */
package com.rpgvdl.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;

import com.rpgvdl.business.map.impl.Coord;
import com.rpgvdl.business.map.impl.Porte;
import com.rpgvdl.entity.event.bo.Person;
import com.rpgvdl.business.map.IEvent;
import com.rpgvdl.entity.event.itf.IPerson;
import com.rpgvdl.business.Board;
import com.rpgvdl.business.map.impl.Map;
import com.rpgvdl.system.parsing.ConfigData;
import com.rpgvdl.system.parsing.MapData;
import com.rpgvdl.system.impl.Logger;
import com.rpgvdl.business.display.menu.impl.Menu;
import com.rpgvdl.system.IConfig;
import com.rpgvdl.system.util.Invoker;
import com.rpgvdl.system.manager.RPGVDLManager;
import com.rpgvdl.system.util.ParsingFactory;
import com.rpgvdl.system.util.SaveManager;


/**
 * @author 20002845
 * @date Jan 29, 2016
 *
 */
public class AppLoader
{
    private Logger log = new Logger(this.getClass());

    /**
     * TODO define instance in file and read it to create instance
     */
    private void loadSupport(){
        RPGVDLManager.setNewConfig();
        log.logInfo("Config class has been instanciated");
        addBoardInstance();
        RPGVDLManager.setNewEventHelper();
        log.logInfo("Board class has been instanciated");
    }

    private Board board = null;;

    private IConfig config = null;

    public AppLoader(){
        loadSupport();
        board = RPGVDLManager.getBoard();
        config = RPGVDLManager.getConfig();
        // log need config to be usable

    }


    private void addBoardInstance(){
        Board board = SaveManager.loadGame();
        if(board == null) {
            RPGVDLManager.setNewBoard();
        }
        else{
            RPGVDLManager.setExistingBoard(board);
        }
        log.logInfo("Board class has been instanciated");
    }

    /**
     * @throws IOException
     *
     */
    private void setupCharacter() throws IOException {
        if(board.getPersons()== null || board.getPersons().size()==0) {
            final IPerson person = new Person("Dupont", "Gerard", 100);
            person.setMainChar(true);
            final Hashtable<String, String> applicationParameters = config.getApplicationParameters();

            // TODO : parametrer
            // TODO : Secure : Beware of nullPointerException
            person.setHeight(Integer.parseInt(applicationParameters.get(IConfig.HEIGHT_CHAR)));
            person.setWidth(Integer.parseInt(applicationParameters.get(IConfig.WIDTH_CHAR)));
            board.addPerson(person);
        }

    }



    /**
     * @param mapData
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    private void setupEventsMap(final MapData mapData,Map map) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        if(map.getEvents() == null || map.getEvents().size()==0) {
            final List<Hashtable<String, String>> events = mapData.getEvents();

            if (events != null && events.size() > 0) {
                for (final Hashtable<String, String> evt : events) {
                    final IEvent newEvt = (IEvent) Invoker.createInstance(evt.get("TypeEvent"));
                    newEvt.setHeight(map.CASE_SIZE);
                    newEvt.setWidth(map.CASE_SIZE);
                    final int x = Integer.parseInt(evt.get("CoordX"));
                    final int y = Integer.parseInt(evt.get("CoordY"));
                    newEvt.setX(map.CASE_SIZE * x);
                    newEvt.setY(map.CASE_SIZE * y);
                    map.addEvent(newEvt);
                }
            }
        }
    }

    /**
     * @param mapData
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    private void setupPortesMap(final MapData mapData,Map map) {

        if(map.getPortes() == null || map.getPortes().size()==0) {
            final List<Hashtable<String, String>> portes = mapData.getPortes();

            if (portes != null && portes.size() > 0) {
                for (final Hashtable<String, String> porte : portes) {
                    final Porte newPorte = new Porte();
                    final int fromx = Integer.parseInt(porte.get("FromX"));
                    final int fromy = Integer.parseInt(porte.get("FromY"));
                    newPorte.setFrom(new Coord(fromx,fromy));
                    final int tox = Integer.parseInt(porte.get("ToX"));
                    final int toy = Integer.parseInt(porte.get("ToY"));
                    newPorte.setTo(new Coord(tox,toy));
                    newPorte.setMapNumber(Integer.parseInt(porte.get("ToMap")));
                    map.addPorte(newPorte);
                }
            }
        }
    }



    public void start() throws Exception{
        setupConfig();
        setupMap();
        setupCharacter();

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
        log.logInfo("Configuration has succesfully been set.");

    }

    private void defineMenuPause(final List<String> menuPause){
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
     * @throws Exception
     *
     */
    private void setupMap() throws Exception {
        int nbCarte = ParsingFactory.getNumberofFiles(MapData.class.getName());

        for(int ind = 1; ind <=nbCarte;ind++ ){

            final MapData mapData = new MapData(ind);
            board.addMap(mapData.getMap());

            final Map map = board.getMap(ind-1);
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

            setupEventsMap(mapData,map);

            setupPortesMap(mapData,map);

        }

        log.logInfo("Map has succesfully been set.");


    }

}

