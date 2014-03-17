/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kickassdb;

import java.util.ArrayList;

/**
 *
 * @author Otto
 */
public class Schema {
    
    private ArrayList<Table> schema;
    private int table_count = 0;

    /**
     * @return the schema
     */
    public ArrayList<Table> getSchema() {
        return schema;
    }

    /**
     * @param schema the schema to set
     */
    public void setSchema(ArrayList<Table> schema) {
        this.schema = schema;
    }

    /**
     * @return the table_count
     */
    public int getTable_count() {
        return table_count;
    }

    /**
     * @param table_count the table_count to set
     */
    public void setTable_count(int table_count) {
        this.table_count = table_count;
    }
    
    public void addTable(Table t){
    
        this.schema.add(t);
    
    }
    
    
}
