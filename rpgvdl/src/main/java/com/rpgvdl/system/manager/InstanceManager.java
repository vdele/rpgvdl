/**
 *
 */
package com.rpgvdl.system.manager;

import java.util.Hashtable;

import com.rpgvdl.system.util.Invoker;


/**
 * @author 20002845
 * @date Jan 29, 2016
 * 
 */
public class InstanceManager implements IInstanceManager
{
    private static Hashtable<String, Object> allInstances;



    protected static Object getInstance(final String key){
        if(InstanceManager.allInstances!=null) {
            return InstanceManager.allInstances.get(key);
        } else {
            return null;
        }
    }



    /* (non-Javadoc)
     * @see com.rpgvdl.system.manager.IInstanceManager#addInstance(java.lang.String, java.lang.String)
     */
    protected static void addInstance(final String key, final String className) {
        Object o=null;
        try {
            o = Invoker.createInstance(className);
        } catch (final Exception e) {
            o = null;
            e.printStackTrace();
        } 
        addInstance(key,o);

    }

    public static void addInstance(final String key,final Object o){
        if(o!=null){
            if(InstanceManager.allInstances == null) {
                InstanceManager.allInstances = new Hashtable<String, Object>();
            }
            InstanceManager.allInstances.put(key, o);
        }
    }
}

