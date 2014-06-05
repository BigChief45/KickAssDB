package kickassdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HashMethods 
{
    public static ArrayList<Tuple> search(HashMap hm, int key)
    {
        ArrayList<Tuple> result;
        result = (ArrayList<Tuple>) hm.get(key);
        
        if ( result == null )
            result = new ArrayList<>();
        
        return result;
    }
    
    public static ArrayList<Tuple> searchLess(HashMap hm, int key)
    {
        /* In Order for this search to work, the HashTable NEEDS to be
           ORDERED
        */
        ArrayList<Tuple> result = new ArrayList<>();                
        Iterator<Integer> keySetIterator = hm.keySet().iterator();
        ArrayList<Tuple> current_list;
        
        while( keySetIterator.hasNext() )
        {
            Integer current_key = keySetIterator.next();
            
            if ( key < current_key )
            {
                current_list = (ArrayList<Tuple>) hm.get(current_key);                
                /* Iterate through current list to add to the result */
                for ( Tuple t : current_list )
                    result.add(t);
            }            
        }
        
        return result;
    }
    
    public static ArrayList<Tuple> searchGreater(HashMap hm, int key)
    {
        /* In Order for this search to work, the HashTable NEEDS to be
           ORDERED
        */
        ArrayList<Tuple> result = new ArrayList<>();                
        Iterator<Integer> keySetIterator = hm.keySet().iterator();        
        ArrayList<Tuple> current_list;
        
        while( keySetIterator.hasNext() )
        {
            Integer current_key = keySetIterator.next();
            
            if ( key > current_key )
            {
                current_list = (ArrayList<Tuple>) hm.get(current_key);                
                /* Iterate through current list to add to the result */
                for ( Tuple t : current_list )
                    result.add(t);
            }            
        }
        
        return result;
    }
    
    public static ArrayList<Tuple> searchDifferent(HashMap hm, int key)
    {
        /* In Order for this search to work, the HashTable NEEDS to be
           ORDERED
        */
        ArrayList<Tuple> result = new ArrayList<>();                
        Iterator<Integer> keySetIterator = hm.keySet().iterator();        
        ArrayList<Tuple> current_list;
        
        while( keySetIterator.hasNext() )
        {
            Integer current_key = keySetIterator.next();
            
            if ( key != current_key )
            {
                current_list = (ArrayList<Tuple>) hm.get(current_key);                
                /* Iterate through current list to add to the result */
                for ( Tuple t : current_list )
                    result.add(t);
            }            
        }
        
        return result;
    }
}
