/**
 *
 */
package com.rpgvdl.factory.running;

import java.util.ArrayList;
import java.util.List;

import com.rpgvdl.factory.running.itf.IItemMenu;
import com.rpgvdl.factory.running.itf.IMenu;
import com.rpgvdl.factory.util.Invoker;


/**
 * @author 20002845
 * @date 25 sept. 2015
 * 
 */
public class Menu implements IMenu
{
    protected Logger log = new Logger(this.getClass());

    private String title;

    private String className;

    private List<IItemMenu> items = null;

    public void setClassName(final String className) {
        this.className = className;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    private boolean isVisible=false;

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(final boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * @param titleMenu
     */
    public Menu(final String titleMenu) {
        super();
        title = titleMenu;
    }
    /**
     * @param titleMenu
     */
    public Menu() {
        super();
    }

    public void selectNext(){
        final IItemMenu selectedItem = getSelectedMenu();
        if(selectedItem == null) {
            getItemMenu(0).setSelected(true);
        } else{
            unselectAllItems();
            int posSelected = selectedItem.getPosition();
            posSelected = getItemNumber()>posSelected+1?posSelected+1:posSelected;
            getItemMenu(posSelected).setSelected(true);
        }

    }

    public void selectPrevious(){
        final IItemMenu selectedItem = getSelectedMenu();
        if(selectedItem == null) {
            getItemMenu(0).setSelected(true);
        } else{
            unselectAllItems();
            int posSelected = selectedItem.getPosition();
            posSelected = 0<=posSelected-1?posSelected-1:posSelected;
            getItemMenu(posSelected).setSelected(true);
        }

    }

    /**
     * 
     */
    private void unselectAllItems() {
        for(final IItemMenu item:items){
            item.setSelected(false);
        }

    }

    /* (non-Javadoc)
     * @see factory.running.itf.IMenu#getSelectedMenu()
     */
    public IItemMenu getSelectedMenu() {
        if(items!=null) {
            for(final IItemMenu item : items){
                if(item.isSelected()) {
                    return item;
                }
            }
        }
        return null;
    }

    /* (non-Javadoc)
     * @see factory.running.itf.IMenu#getItemMenu(int)
     */
    public IItemMenu getItemMenu(final int i) {
        return items.get(i);
    }

    /* (non-Javadoc)
     * @see factory.running.itf.IMenu#getItemMenu(java.lang.String)
     */
    public IItemMenu getItemMenu(final String name) {
        if(items!=null) {
            for(final IItemMenu item : items){
                if(name.equals(item.getName())) {
                    return item;
                }
            }
        }
        return null;
    }


    /* (non-Javadoc)
     * @see factory.running.itf.IMenu#getItemNumber()
     */
    public int getItemNumber() {
        return items.size();
    }

    /* (non-Javadoc)
     * @see factory.running.itf.IMenu#addItemMenu(factory.running.itf.IItemMenu)
     */
    public void addItemMenu(final String label,final String name,final String action) {
        if(items==null){
            items = new ArrayList<IItemMenu>();
        }
        items.add(new ItemMenu(label, name,items.size(),action));

    }

    public void activeSelectedMenu(){
        final IItemMenu item = getSelectedMenu();
        if(item!=null){
            log.logInfo("Menu @ - @ has been selected", getTitle(),item.getLabel());
            try{
                Invoker.invokeMethod(className, item.getAction());
            }
            catch(final Exception e){
                log.logError("Error occured during method invocation : @", e.getMessage());
                e.printStackTrace();
            }
        }
        else{
            log.logInfo("No item menu selected");
        }
    }

}

