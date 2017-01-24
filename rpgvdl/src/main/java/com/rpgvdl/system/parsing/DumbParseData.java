/**
 *
 */
package com.rpgvdl.system.parsing;


/**
 * @author 20002845
 * @date 28 mai 2015
 * 
 */
public class DumbParseData extends DataWriter
{
    /**
     * @throws Exception
     */
    public DumbParseData() throws Exception {
        super();
    }

    public void addDumbDatas(final String dumbData){
        writeDatasInFile(dumbData);
    }
}

