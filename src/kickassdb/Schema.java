
package kickassdb;

import java.util.ArrayList;


public class Schema 
{
    
    private ArrayList<Table> schema;
    private int table_count = 0;

    public Schema()
    {
        schema = new ArrayList<Table>();
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
    
   
}
