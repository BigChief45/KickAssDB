package kickassdb;

import java.io.Serializable;

public class Alias implements Serializable
{
    private String alias_name;
    private Table alias_table;
    private String alias_field;
    
    public Alias()
    {
        alias_name = "";
    }
    
    public Alias(String name, Table table)
    {
        alias_name = name;
        alias_table = table;
    }
    
    public String getName()
    {
        return alias_name;
    }
    
    public void setName(String n)
    {
        alias_name = n;
    }
    
    public Table getTable()
    {
        return alias_table;
    }
    
    public void setTable(Table t)
    {
        alias_table = t;
    }
    
    public String getFieldName()
    {
        return alias_field;
    }
    
    public void setFieldName(String s)
    {
        alias_field = s;
    }
}
