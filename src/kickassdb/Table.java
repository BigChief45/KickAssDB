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
    private Attribute primary_key;
    
    private ArrayList<Attribute> table_domain;
    private ArrayList<Tuple> table_tuples;
    

    public Table()
    {
        
    }
    
    public Table(int ID, String name, ArrayList<Attribute> domain, int prIndex)
    {
    
        this.table_id = ID;
        this.table_name = name;
        this.table_domain = domain;        
        this.primary_key = this.table_domain.get(prIndex);
                
        table_tuples = new ArrayList<Tuple>();
    
    }
    
    public void addTuple(Tuple tuple)
    {
        table_tuples.add(tuple);
        MainWindow.outputText.append("New record added to " + table_name + " \n");
    }
    
    public Tuple getTuple(int pos) 
    {
        return table_tuples.get(pos);
    }
                                     
    public int getTable_id() 
    {
        return table_id;
    }

    public void setTable_id(int table_id) 
    {
        this.table_id = table_id;
    }

    public String getTable_name() 
    {
        return table_name;
    }

    public void setTable_name(String table_name) 
    {
        this.table_name = table_name;
    }

    public ArrayList<Attribute> getTable_domain() 
    {
        return table_domain;
    }

    public void setTable_domain(ArrayList<Attribute> table_domain) 
    {
        this.table_domain = table_domain;
    }

    public ArrayList<Tuple> getTable_tuples() 
    {
        return table_tuples;
    }

    public void setTable_tuples(ArrayList<Tuple> table_tuples) 
    {
        this.table_tuples = table_tuples;
    }

    public Attribute getPrimary_key() 
    {
        return primary_key;
    }

    public void setPrimary_key(Attribute primary_key) 
    {
        this.primary_key = primary_key;
    }
    
    public void printDomain()
    {
    
        System.out.println(this.table_name);
        System.out.println("=============");
        for (Attribute attribute : table_domain) 
        {            
            System.out.println("Name: " + attribute.getAttribute_name() + ", Type:" + attribute.getType().name());   
        }                
        
        System.out.println("");
    }
    
    public void printTuples()
    {
        System.out.println(this.table_name);
        System.out.println("=============");
        
        for ( Tuple tuple : table_tuples ) 
        {            
              for ( Value tuple_value : tuple.getTuple_values() )
              {
                  System.out.print(tuple_value.getValue() + "\t");
              }
              System.out.println("");
        }                
        
        System.out.println("");
    }
    
}//End Table
