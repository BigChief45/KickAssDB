package kickassdb;

import java.util.ArrayList;

public class QueryFilter 
{
    /* This class describes a WHERE query filter, specifying the field name
       operation, and filter contraint in a single object to be used later.
    */
    
    private String field_name;
    private int field_position; // Field position in Table domain
    private String operand;
    private Object filter_value;
    private String bool_value;
    
    public QueryFilter()
    {
        
    }
    
    public QueryFilter(String n, String o, Object f, String b)
    {
        field_name = n;
        operand = o;
        filter_value = f;
        bool_value = b;
                
    }
    
    public void setFieldName(String n)
    {
        field_name = n;
    }
    
    public String getFieldName()
    {
        return field_name;
    }
    
    public void setOperand(String o)
    {
        operand = o;
    }
    
    public void setFilterValue(Object f)
    {
        filter_value = f;
    }
    
    public void setBoolValue(String b)
    {
        bool_value = b;
    }
    
    public static boolean TupleMatchesFilter(Tuple tuple, ArrayList<Table> tables, ArrayList<QueryFilter> filters)
    {
        /* This helper method recieves a tuple and checks if it matches
           with filters specified
        */
        boolean ret = true;
        
        for ( QueryFilter qf : filters )
        {
            for ( Table t : tables)
            {
                /* Get the field position in the current table's domain */
                String field_name = qf.getFieldName();
                int field_position = t.getFieldPosition(field_name);
                
                /* Check if the value in the tuple meets the filter */
                
            }
        }
        
        return ret;
    }
}
