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
    
    public int getTupleSize()
    {
        return tuple_values.size();
    }
    
    /* set the temp_tuple in the right order */
    public static Tuple setTuple_order(ArrayList<String> attName, ArrayList<Attribute> domain, Tuple temp_tuple) {
        
        if(attName.size() == 0)
            return temp_tuple;
        
        if(attName.size() != temp_tuple.tuple_values.size())
            return null;
        
        Tuple new_tuple = new Tuple();
        int valIndex = 0, pos = 0;
        
        for( int tmp = 0; tmp < domain.size(); tmp++) {
            new_tuple.tuple_values.add(new Value(""));
            //System.out.println("new added");
        }
        
        for( String name : attName) {
            pos = 0;
            for( Attribute att : domain) {
                if(name.equals(att.getAttribute_name())) {
                    new_tuple.tuple_values.set(pos, temp_tuple.getValue(valIndex));
                }
                pos++;
            }
            valIndex++;
            
        }
        
       
        
        
        return new_tuple;
    }
    
}
