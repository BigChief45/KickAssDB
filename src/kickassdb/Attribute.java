package kickassdb;

public class Attribute 
{

    private Type type;
    private String attribute_name;
    private int attribute_size;
        
    public enum Type 
    {
        INTEGER, VARCHAR
    }
    
    public Attribute()
    {
       
    }
    
    public Attribute(String n, Type t, int size)
    {     
        this.attribute_name = n;
        this.type = t;
        this.attribute_size = size;
    }
    
    public Type getType() 
    {
        return type;
    }

    public void setType(Type type) 
    {
        this.type = type;
    }

    public String getAttribute_name() 
    {
        return attribute_name;
    }

    public void setAttribute_name(String attribute_name) 
    {
        this.attribute_name = attribute_name;
    }
            
    public int getAttributeSize()
    {
        return attribute_size;
    }
    
    public void setAttributeSize(int v)
    {
        attribute_size = v;
    }
    
}
