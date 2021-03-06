package kickassdb;

import java.util.ArrayList;
import java.io.Serializable;

public class Tuple implements Serializable
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
 
    
    

    public static Tuple mergeTuples(Tuple tuple1, Tuple tuple2)
    {
        /* MERGES TWO TUPLES INTO ONE (ONLY VALUES) */
        Tuple result = new Tuple();
        ArrayList<Value> new_values = new ArrayList<>();
        
        /* Add tuple 1 values */
        for ( Value v : tuple1.getTuple_values() )
            new_values.add(v);
        
        /* Add tuple 2 values */
        for ( Value v : tuple2.getTuple_values() )
            new_values.add(v);
        
        result.setTuple_values(new_values);
        
        return result;
    }
    
    @Override
    public String toString(){
    
        String r = "";
        for ( Value v : tuple_values )
            r = r + v.getValue().toString() + "; ";
    
        return r;
    }
}
