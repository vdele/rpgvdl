package com.rpgvdl.system.util;

import com.rpgvdl.system.impl.Logger;

import java.io.*;

/**
 * Created by VDE on 02/02/2017.
 */
public class FileUtils {

    private static String savingName = "save/board.sav";

    protected static Logger log = new Logger(FileUtils.class);

    protected static void writeObject(Object o){
        ObjectOutputStream oos = null;

        try {
            final FileOutputStream fichier = new FileOutputStream(savingName);
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(o);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                log.logError("Unable to write object : @",ex);
            }
        }
    }

    protected static Object readFile(){
        ObjectInputStream ois = null;
        Object o = null;
        try {
            if(new File(savingName).exists()) {
                final FileInputStream fichier = new FileInputStream(savingName);
                ois = new ObjectInputStream(fichier);
                o = ois.readObject();
            }
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                log.logError("Unable to read object : @",ex);
            }
        }
        return o;
    }


}
