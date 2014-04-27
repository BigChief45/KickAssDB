package kickassdb;

public class FilterPart 
{
    private String field_alias;
    private String field_name;
    private int field_position;
    private Object value;
    
    public FilterPart()
    {
        field_alias = "";
        field_name = "";
        field_position = -1;
        value = null;
    }
    
    public String getFieldAlias()
    {
        return field_alias;
    }
    
    public void setFieldAlias(String a)
    {
        field_alias = a;
    }
    
    public String getFieldName()
    {
        return field_name;
    }
    
    public void setFieldName(String n)
    {
        field_name = n;
    }
    
    public int getFieldPosition()
    {
        return field_position;
    }
    
    public void setFieldPosition(int p)
    {
        field_position = p;
    }
    
    public Object getValue()
    {
        return value;
    }
    
    public void setValue(Object v)
    {
        value = v;
    }
}
