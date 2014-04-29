package kickassdb;

import java.io.Serializable;

public class Value implements Serializable
{
    
    private Object value;
    private Attribute a;
    
    public Value()
    {
        
    }

    public Value(Object v)
    {
        value = v;
    }
    
    public Value(String v)
    {
        value = v;
    }
    
    public Value(Integer v)
    {
        value = v;
    }
        
    public Object getValue() 
    {
        return value;
    }

    public void setValue(Object value) 
    {
        this.value = value;
    }
    
    public void setValue(String value)
    {
        this.value = value;
    }
    
    public void setValue(int value)
    {
        this.value = value;
    }
    
    public void setAttribute(Attribute at)
    {
        a = at;
    }
    
    public Attribute getAttribute()
    {
        return a;
    }
}
