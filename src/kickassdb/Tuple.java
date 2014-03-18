package kickassdb;

import java.util.ArrayList;

public class Tuple 
{
    
    private ArrayList<Value> tuple_values;

    public Tuple()
    {
        tuple_values = new ArrayList<Value>();
    }
    
    public ArrayList<Value> getTuple_values() {
        return tuple_values;
    }

    public void setTuple_values(ArrayList<Value> tuple_values) {
        this.tuple_values = tuple_values;
    }
    
    public void addValue(Value v)
    {
        tuple_values.add(v);
    }
    
    public Value getValue(int pos)
    {
        return tuple_values.get(pos);
    }
    
}
