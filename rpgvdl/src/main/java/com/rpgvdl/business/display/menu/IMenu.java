/**
 *
 */
package com.rpgvdl.business.display.menu;


import com.rpgvdl.business.display.menu.IItemMenu;

/**
 * @author 20002845
 * @date 25 sept. 2015
 * 
 */
public interface IMenu
{
    public IItemMenu getSelectedMenu();

    public IItemMenu getItemMenu(int i);
    public IItemMenu getItemMenu(String name);

    public int getItemNumber();

    public void addItemMenu(String label, String name,String action);

    public String getTitle();
    public void setTitle(final String title);
    public void setClassName(final String className);

    public boolean isVisible();
    public void setVisible(boolean visible);

    public void selectNext();
    public void selectPrevious();

    /**
     * 
     */
    public void activeSelectedMenu();
}

