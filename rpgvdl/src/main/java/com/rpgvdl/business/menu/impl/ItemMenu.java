/**
 *
 */
package com.rpgvdl.business.menu.impl;

import com.rpgvdl.business.menu.IItemMenu;


/**
 * @author 20002845
 * @date 25 sept. 2015
 * 
 */
public class ItemMenu implements IItemMenu
{
    private String label;
    private boolean selected;
    private String name;
    private final int position;
    private String action;


    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }



    public int getPosition() {
        return position;
    }



    /**
     * @param label
     * @param name
     */
    public ItemMenu(final String label, final String name, final int position,final String action) {
        this.label = label;
        this.name = name;
        this.position = position;
        this.action = action;
    }


    public String getName() {
        return name;
    }


    public void setName(final String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(final boolean selected) {
        this.selected = selected;
    }
}

