package kickassdb;

public class Attribute 
{

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
    
    public enum Type 
    {
        INTEGER, VARCHAR
    }    
    
    private Type type;
    private String attribute_name;    
       
    public Attribute()
    {
       
    }
    
    public Attribute(String n, Type t)
    {     
        this.attribute_name = n;
        this.type = t;
    }
    
            
}
