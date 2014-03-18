package kickassdb;

public class Value 
{
    
    private Object value;
    
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
}
