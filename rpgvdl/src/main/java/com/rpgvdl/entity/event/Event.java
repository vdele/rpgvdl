/**
 *
 */
package com.rpgvdl.entity.event;

import com.rpgvdl.entity.event.itf.IEvent;
import com.rpgvdl.factory.running.Logger;

/**
 * @author 20002845
 * @date 22 sept. 2015
 *
 */
public abstract class Event extends MapElement implements IEvent {

    protected Logger log = new Logger(this.getClass());

    private boolean hasBeenConsumed = false;

    public Event(){
        super();
    }

    /* (non-Javadoc)
     * @see com.rpgvdl.entity.event.itf.IEvent#activeEvent()
     */
    public final void activeEvent() {
        consumeEvent();
        runEvent();
    }

    private void consumeEvent() {
        hasBeenConsumed = true;
    }

    public final boolean hasBeenConsumed(){
        return hasBeenConsumed;
    }

    public abstract void runEvent();

}

