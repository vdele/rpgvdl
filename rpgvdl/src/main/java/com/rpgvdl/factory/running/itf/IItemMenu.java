/**
 *
 */
package com.rpgvdl.factory.running.itf;


/**
 * @author 20002845
 * @date 25 sept. 2015
 * 
 */
public interface IItemMenu
{

    public boolean isSelected();
    public void setSelected(boolean selected);
    public String getLabel();
    public void setLabel(String label);
    public String getName();
    public int getPosition();
    public String getAction();
}

