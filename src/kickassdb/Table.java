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
public class Table {
 
    private int table_id;
    private String table_name;
    private ArrayList<Attribute> table_domain;
    private ArrayList<Tuple> table_tuples;
    private Attribute primary_key;

    public Table(int ID, String name, ArrayList<Attribute> domain, int prIndex){
    
        this.table_id = ID;
        this.table_name = name;
        this.table_domain = domain;        
        this.primary_key = this.table_domain.get(prIndex);
    
    }//End
    
    
    
    
    
    
    
    
    
    /**
     * @return the table_id
     */
    public int getTable_id() {
        return table_id;
    }

    /**
     * @param table_id the table_id to set
     */
    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    /**
     * @return the table_name
     */
    public String getTable_name() {
        return table_name;
    }

    /**
     * @param table_name the table_name to set
     */
    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    /**
     * @return the table_domain
     */
    public ArrayList<Attribute> getTable_domain() {
        return table_domain;
    }

    /**
     * @param table_domain the table_domain to set
     */
    public void setTable_domain(ArrayList<Attribute> table_domain) {
        this.table_domain = table_domain;
    }

    /**
     * @return the table_tuples
     */
    public ArrayList<Tuple> getTable_tuples() {
        return table_tuples;
    }

    /**
     * @param table_tuples the table_tuples to set
     */
    public void setTable_tuples(ArrayList<Tuple> table_tuples) {
        this.table_tuples = table_tuples;
    }

    /**
     * @return the primary_key
     */
    public Attribute getPrimary_key() {
        return primary_key;
    }

    /**
     * @param primary_key the primary_key to set
     */
    public void setPrimary_key(Attribute primary_key) {
        this.primary_key = primary_key;
    }
    
    
    
}//End Table
