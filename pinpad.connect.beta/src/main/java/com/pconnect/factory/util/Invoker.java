/**
 *
 */
package com.pconnect.factory.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.pconnect.factory.running.Logger;


/**
 * @author 20002845
 * @date 7 oct. 2015
 * 
 */
public class Invoker
{
    static Logger log = new Logger(Invoker.class);

    public static void invokeMethod(final String className, final String methodName) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
        Invoker.log.logInfo("call method @.@",className,methodName);
        final Class<?> c = Class.forName(className);

        final Method m = c.getMethod(methodName, new Class[] {});
        m.invoke(c.newInstance(), new Object[] {});
    }

    public static Object createInstance(final String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Invoker.log.logInfo("creating instance @",className);
        final Class<?> c = Class.forName(className);
        return c.newInstance();
    }



    public void test(){
        Invoker.log.logInfo("invoker test");
    }
}

