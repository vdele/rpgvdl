/**
 *
 */
package com.pconnect.factory.util;

import java.util.Arrays;



/**
 * @author 20002845
 * @date 12 juin 2015
 * 
 */
public class FactoryUtils
{

    /**
     * retourne un lancé de dés entre 1 et nbFace
     * @param i
     * @return
     */
    public static Integer dice(final int faceNumber){
        return dice(faceNumber,1);
    }

    public static Integer dice(final int faceNumber,final int diceNumber){
        int sum = 0;

        for (int i = 0 ; i < diceNumber; i++){
            sum = sum + random(1,faceNumber);
        }
        return sum;
    }


    /**
     * return an integer between mediane-precision and mediane+precision;
     * there is more probability to obtain number the nearest of mediane
     * @param mediane
     * @param precision
     * @return
     */
    public static Integer target(final int mediane, final int precision){
        final int tailTab = Double.valueOf(Math.pow(precision+1,2)).intValue();
        final Integer[] listProb = new Integer[tailTab];
        int cnt = 0;
        for(int i = 0 ; i <= precision ;i++){
            for(int j = 0;j <= i;j++){
                listProb[cnt] = mediane-precision+i;
                if(i!=precision) {
                    cnt++;
                    listProb[cnt] = mediane+precision-i;
                }
                cnt++;
            }
        }

        final int indice =random(0,listProb.length-1);

        return listProb[indice];
    }

    /**
     * Retourne un nombre au hasard entre start et end (bornes incluses)
     * @param start
     * @param end
     * @return
     */
    public static Integer random(final int start,final int end){
        //  
        return Integer.valueOf(new Double(Math.random()*(end-start+1)).intValue())+start;
    }


    /**
     * Retourne un nombre au hasard dans la liste de valeurs passée en argument
     * @param integers
     * @return
     */
    public static Integer random(final Integer... integers){
        if(integers ==null) {
            return null;
        }

        if(integers.length == 0) {
            return null;
        }

        final int index = random(0,integers.length-1);

        return integers[index];
    }

    public static <T>  boolean contains(final T[] array, final T key) {
        return Arrays.asList(array).contains(key);
    }
}

