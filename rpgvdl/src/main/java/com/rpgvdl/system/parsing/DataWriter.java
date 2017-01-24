/**
 *
 */
package com.rpgvdl.system.parsing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



/**
 * @author 20002845
 * @date 27 mai 2015
 * 
 */
public abstract class DataWriter extends DataReader
{

    /**
     * @throws Exception
     */
    public DataWriter() throws Exception {
        super();
    }

    protected void writeDatasInFile(final String datas) {
        try{
            final PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(getFilePath(), true)));
            out.println(datas);
            out.close();
        }
        catch(final IOException e){
            log.logError("Unable to write in file @" , getFileName());
            e.printStackTrace();
        }
    }

}

