/**
 *
 */
package com.pconnect.factory.running;

import java.util.Hashtable;

import com.pconnect.factory.running.itf.IInstanceManager;
import com.pconnect.factory.util.Invoker;


/**
 * @author 20002845
 * @date Jan 29, 2016
 * 
 */
public class InstanceManager implements IInstanceManager
{
    private static Hashtable<String, Object> allInstances;



    public static Object getInstance(final String key){
        if(InstanceManager.allInstances!=null) {
            return InstanceManager.allInstances.get(key);
        } else {
            return null;
        }
    }



    /* (non-Javadoc)
     * @see com.pconnect.factory.running.itf.IInstanceManager#addInstance(java.lang.String, java.lang.String)
     */
    public static void addInstance(final String key, final String className) {
        Object o=null;
        try {
            o = Invoker.createInstance(className);
        } catch (final Exception e) {

            e.printStackTrace();
        } 
        if(o!=null){
            if(InstanceManager.allInstances == null) {
                InstanceManager.allInstances = new Hashtable<String, Object>();
            }
            InstanceManager.allInstances.put(key, o);
        }

    }
}

