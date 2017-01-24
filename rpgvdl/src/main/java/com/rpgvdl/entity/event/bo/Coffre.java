/**
 *
 */
package com.rpgvdl.entity.event.bo;

import com.rpgvdl.business.map.impl.Event;


/**
 * @author 20002845
 * @date Nov 16, 2015
 *
 */
public class Coffre extends Event
{

    /* (non-Javadoc)
     * @see com.rpgvdl.business.map.impl.Event#activeEvent()
     *
     */
    @Override
    public void runEvent() {
        board.showMsgBox("Hello I'm a coffre");
    }

}

