package kickassdb;

import java.util.ArrayList;
import java.util.Collections;

public class Table 
{
 
    private int table_id;
    private String table_name;
    
    private Attribute primary_key;    
    private ArrayList<Attribute> table_domain;
    private ArrayList<Tuple> table_tuples;
    

    public Table()
    {
        table_tuples = new ArrayList<>();
    }
    
    public Table(int ID, String name, ArrayList<Attribute> domain)
    {
    
        this.table_id = ID;
        this.table_name = name;
        this.table_domain = domain;        
                
        table_tuples = new ArrayList<>();        
    
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
    
    public void setPrimary_key(Attribute pk)
    {
        primary_key = pk;
    }
    
    public int getFieldPosition(String field_name)
    {
        /* Returns the field's position in the domain if it exists */
        int position = 0;
        
        /* Loop through the domain */
        for ( Attribute a : table_domain )
        {
            if ( field_name.equals(a.getAttribute_name()) )
                return position;
            
            position++;
        }
        
        return -1; // Field does not exist in domain
    }
            
    public String getAttType(String name) {
        String type = null;
        for (Attribute attribute : table_domain) {
            if(attribute.getAttribute_name().equals(name)) {
                type = attribute.getType().name();         
                //System.out.printf("The type of %s is %s.\n", name, type);
                return type;
            }
        }
        return null;
    }
    
    public void printDomain()
    {
    
        System.out.println(this.table_name);
        System.out.println("=============");
        for (Attribute attribute : table_domain) 
        {            
            System.out.println("Name: " + attribute.getAttribute_name() + ", Type:" + attribute.getType().name() + ", Size: " + attribute.getAttributeSize());   
        }                
        
        System.out.println("Primary Key: " + primary_key.getAttribute_name());
        
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
                  String value = tuple_value.getValue().toString();                  
                  System.out.print(value + "\t");
              }
              System.out.println();
        }                
        
        System.out.println("");
    }
    
    public static Table mergeTables(Table table1, Table table2)
    {
        /* Merges the recieved tables into one single table. Merging all the domains
           and data
        */        
        
        /* Merge all the data */
        Table crossproduct = new Table();
        
        ArrayList<Attribute> table1_domain = table1.getTable_domain();
        ArrayList<Attribute> table2_domain = table2.getTable_domain();
        ArrayList<Attribute> d = new ArrayList();
        
        for (Attribute attr : table1_domain) {
            d.add(attr);
        }
        
        for (Attribute attr : table2_domain) {
            d.add(attr);
        }
                        
        crossproduct.setTable_domain(d);
        
        for ( Tuple tuple : table1.getTable_tuples() )
        {

            for ( Tuple tuple2 : table2.getTable_tuples() )
            {
                Tuple new_tuple = new Tuple();

                for (Value v : tuple.getTuple_values()) {
                    new_tuple.addValue(v);
                }
                
                for (Value v : tuple2.getTuple_values()) {
                    new_tuple.addValue(v);
                }
                
                //We add the tuple to the table crossproduct
                crossproduct.addTuple(new_tuple);                
                
            }        
        }
                                       
        return crossproduct;
    }

}
