
package kickassdb;

import java.util.ArrayList;


public class Schema 
{
    
    private ArrayList<Table> schema;
    private int table_count = 0;
    private String schema_name;

    public Schema()
    {
        schema = new ArrayList<Table>();
    }
    
    public Schema(String name)
    {
        schema = new ArrayList<Table>();
        schema_name = name;
    }
    
    
    public ArrayList<Table> getSchema() 
    {
        return schema;
    }

    public void setSchema(ArrayList<Table> schema) 
    {
        this.schema = schema;
    }

    public int getTable_count() 
    {
        return table_count;
    }

    public void setTable_count(int table_count) 
    {
        this.table_count = table_count;
    }
    
    public void addTable(Table t)
    {
        this.schema.add(t);
        table_count++;
        
        MainWindow.outputText.append("Table " + t.getTable_name() + " was created. \n");
    }
    
   public void setName(String name)
   {
       schema_name = name;
   }
   
   public String getName()
   {
       return schema_name;
   }
   
   public Table getTable(String table_name)
   {
       for ( Table t : schema )
           if ( t.getTable_name().equals(table_name) )
               return t;
       
       return null;
   }
   
    public boolean aliasExists(String alias)
    {
        /* Loops through all the tables in the Schema and checks if this
           alias exists in one of them */                
        for (Table t : schema)        
            if ( t.getTable_alias().getName().equals(alias))
                return true;
        
        return false;
    }
    
    public boolean validateAliases(ArrayList<String> aliases)
    {
        for ( String s : aliases )
            if ( (!s.equals("")) && (aliasExists(s) == false)  )
                return false;
        
        return true;
    }
}
