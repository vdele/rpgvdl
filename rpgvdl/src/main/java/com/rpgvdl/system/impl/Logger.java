/**
 *
 */
package com.rpgvdl.system.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.rpgvdl.system.IConfig;
import com.rpgvdl.system.ILogger;
import com.rpgvdl.system.manager.RPGVDLManager;


/**
 * @author 20002845
 * @date 31 août 2015
 *
 */
public class Logger implements ILogger,Serializable {

    String className = null;

    public Logger(final Class<?> clazz){
        className = clazz.getCanonicalName();

    }

    /**
     * @param message
     * @param args
     * @return
     */
    private String formatMsgWithArgs(final String message, final Object[] args) {
        String formattedMessage = message;
        // Not useful to replace '@' if string doesn't contain it
        if(formattedMessage!=null && formattedMessage.contains("@")) {
            for(final Object arg :args){
                if(arg!=null) {
                    formattedMessage = formattedMessage.replaceFirst("@", arg.toString());
                }
            }
        }
        return formattedMessage;
    }

    private String getHeader(final int lvl){
        final Date date = new Date();   // given date
        final Calendar calendar = Calendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date

        String hour = "";
        hour+=String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY))+":"; // gets hour in 24h format
        hour+=String.format("%02d",calendar.get(Calendar.MINUTE))+":"; // gets minute
        hour+=String.format("%02d",calendar.get(Calendar.SECOND))+"."; // gets second
        hour+=String.format("%03d",calendar.get(Calendar.MILLISECOND)); // gets ms

        return "["+hour +" " +getLabelLevelLogger(lvl)+ "] "+className + " ";
    }

    private String getLabelLevelLogger(final int lvl){
        switch (lvl) {
            case LOG_LEVEL_TRACE:
                return "TRACE";
            case LOG_LEVEL_DEBUG:
                return "DEBUG";
            case LOG_LEVEL_INFO:
                return "INFO";
            case LOG_LEVEL_WARN:
                return "WARN";
            case LOG_LEVEL_ERROR:
                return "ERROR";
            case LOG_LEVEL_FATAL:
                return "FATAL";
            default:
                return null;
        }
    }

    private void log(final String msg, final int lvl, final  Object... args){
        IConfig config = RPGVDLManager.getConfig();
        if(config!=null && config.getLogLevel()<=lvl) {
            writeLog(msg,lvl,args);
        }
    }


    public void logError(final String msg, final  Object... args){
        log(msg,LOG_LEVEL_ERROR,args);
    }


    public void logFatal(final String msg, final  Object... args){
        log(msg,LOG_LEVEL_FATAL,args);
    }


    public void logInfo(final String msg, final  Object... args){
        log(msg,LOG_LEVEL_INFO,args);
    }

    public void logTrace(final String msg, final  Object... args){
        log(msg,LOG_LEVEL_TRACE,args);
    }

    public void logWarn(final String msg, final  Object... args){
        log(msg,LOG_LEVEL_WARN,args);
    }

    private void writeLog(final String message,final int lvl,final  Object... args){
        System.out.println(getHeader(lvl) + formatMsgWithArgs(message,args));
    }
}

