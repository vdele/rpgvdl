/**
 *
 */
package com.rpgvdl.business.map.impl;

import com.rpgvdl.business.map.IEvent;
import com.rpgvdl.system.impl.Logger;

/**
 * @author 20002845
 * @date 22 sept. 2015
 *
 */
public abstract class Event extends MapElement implements IEvent {

    private boolean hasBeenConsumed = false;

    public Event(){
        super();
    }

    /* (non-Javadoc)
     * @see com.rpgvdl.business.map.IEvent#activeEvent()
     */
    public final void activeEvent() {
        runEvent();
        consumeEvent();
    }

    private void consumeEvent() {
        hasBeenConsumed = true;
    }

    public final boolean hasBeenConsumed(){
        return hasBeenConsumed;
    }

    public abstract void runEvent();

}

