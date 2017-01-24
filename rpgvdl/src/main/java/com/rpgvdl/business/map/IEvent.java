/**
 *
 */

package com.rpgvdl.business.map;

/**
 * @author 20002845
 * @date 6 oct. 2015
 *
 */
public abstract interface IEvent extends IMapElement
{

    public abstract void activeEvent();

    public abstract boolean hasBeenConsumed();

    public void runEvent();


}
