/**
 *
 */
package com.rpgvdl.entity.event.bo;

import com.rpgvdl.entity.event.Event;


/**
 * @author 20002845
 * @date Nov 16, 2015
 *
 */
public class Coffre extends Event
{

    /* (non-Javadoc)
     * @see com.rpgvdl.entity.event.Event#activeEvent()
     *
     */
    @Override
    public void runEvent() {
        board.showMsgBox("Hello I'm a coffre");
    }

}

