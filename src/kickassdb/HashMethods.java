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
        ArrayList<Tuple> result = new ArrayList<Tuple>();
        
        Iterator<Integer> keySetIterator = hm.keySet().iterator();
        
        Tuple t = new Tuple();
        
        while( keySetIterator.hasNext() )
        {
            Integer hkey = keySetIterator.next();
            
            if ( hkey < key )
            {
                t = (Tuple) hm.get(hkey);
                result.add(t);
            }
            else
                break;
        }
        
        return result;
    }
    
    public static ArrayList<Tuple> searchGreater(HashMap hm, int key)
    {
        /* In Order for this search to work, the HashTable NEEDS to be
           ORDERED
        */
        ArrayList<Tuple> result = new ArrayList<Tuple>();
        
        Iterator<Integer> keySetIterator = hm.keySet().iterator();
               
        Tuple t = new Tuple();
        
        while( keySetIterator.hasNext() )
        {
            Integer hkey = keySetIterator.next();
                                                
            if ( hkey > key )
            {
                t = (Tuple) hm.get(hkey);
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
        ArrayList<Tuple> result = new ArrayList<Tuple>();
        
        Iterator<Integer> keySetIterator = hm.keySet().iterator();
        
        Tuple t = new Tuple();
        while( keySetIterator.hasNext() )
        {
            Integer hkey = keySetIterator.next();
            if ( hkey != key )
            {
                t = (Tuple) hm.get(hkey);
                result.add(t);
            }
        }
        
        return result;
    }
}
