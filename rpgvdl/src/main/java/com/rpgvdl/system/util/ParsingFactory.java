/**
 *
 */
package com.rpgvdl.system.util;

import com.rpgvdl.system.parsing.DataReader;

import java.io.File;


/**
 * @author 20002845
 * @date 28 mai 2015
 * 
 */
public class ParsingFactory
{
    public static String convertClassToDataFileName(final String name, int numOfFile) throws Exception{
        if(name!=null){
            String nameOfClass = name.split("\\.")[name.split("\\.").length-1];

            if(nameOfClass.length()<4 || !nameOfClass.substring( nameOfClass.length()-4,nameOfClass.length()).equals("Data")) {
                throw new Exception("The name of class must end by 'Data' (here : " + name+"Data");
            }

            nameOfClass=nameOfClass.substring(0, nameOfClass.length()-4);

            String finFichier = DataReader.EXTENSION;
            if(numOfFile!=-1){
                finFichier = numOfFile+finFichier;
            }

            return nameOfClass.substring(0, 1).toLowerCase()+nameOfClass.substring(1)+ finFichier;
        } else {
            throw new Exception("Unbelievable error, name of class is null !");
        }
    }

    public static String convertClassToDataFileName(final String name) throws Exception{
        return convertClassToDataFileName(name,-1);
    }


    public static int getNumberofFiles(String name) {

        String nameOfClass = name.split("\\.")[name.split("\\.").length - 1];
        nameOfClass = nameOfClass.substring(0, nameOfClass.length() - 4);

        File f = new File(DataReader.PATH_FILES+nameOfClass+DataReader.EXTENSION);
        if(f.exists()){
            return 1;
        }
        else{
            int n = 1;
            f = new File(DataReader.PATH_FILES+nameOfClass+n+DataReader.EXTENSION);
            while(f.exists()){
                n++;
                f = new File(DataReader.PATH_FILES+nameOfClass+n+DataReader.EXTENSION);
            }
            return n-1;
        }
    }
}

