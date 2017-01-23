/**
 *
 */
package com.pconnect.factory.util;

import com.pconnect.factory.parsing.DataReader;



/**
 * @author 20002845
 * @date 28 mai 2015
 * 
 */
public class ParsingFactory
{
    public static String convertClassToDataFileName(final String name) throws Exception{
        if(name!=null){
            String nameOfClass = name.split("\\.")[name.split("\\.").length-1];

            if(nameOfClass.length()<4 || !nameOfClass.substring( nameOfClass.length()-4,nameOfClass.length()).equals("Data")) {
                throw new Exception("The name of class must end by 'Data' (here : " + name+"Data");
            }

            nameOfClass=nameOfClass.substring(0, nameOfClass.length()-4);
            return nameOfClass.substring(0, 1).toLowerCase()+nameOfClass.substring(1)+DataReader.EXTENSION;
        } else {
            throw new Exception("Unbelievable error, name of class is null !");
        }
    }
}

