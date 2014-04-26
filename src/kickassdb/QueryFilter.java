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
        return getField_name();
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
    
    public static Table filterTable(Table table, ArrayList<QueryFilter> filters)
    {
        /* This helper method recieves a talbe and removes the tuples that
           do not match the filters specified, returning a new filtered Table
        */
        
        Table new_table = new Table();
        new_table.setTable_domain(table.getTable_domain());
        
        /* Check quantity of filters */
        if ( filters.size() == 1 )
        {
            QueryFilter filter = filters.get(0);            
                       
            /* Loop through the whole table and check the tuples */
            for ( Tuple tuple : table.getTable_tuples() )
            {               
                              
                Tuple tempTuple = getApplyingTuple(table, tuple, filter);
                
                if (tempTuple != null)
                    new_table.addTuple(tuple);
                
            }//End for ( Tuple tuple : table.getTable_tuples() )
            
        }//End if ( filters.size() == 1 )
        else if ( filters.size() == 2 )
        {
         
            //First we will get the indexes of the columns which are part of the where clause
            QueryFilter filter1 = filters.get(0);
            QueryFilter filter2 = filters.get(1);                                  
            
            /* Loop through the whole table and check the tuples */
            for ( Tuple tuple : table.getTable_tuples() )
            {               
                              
                Tuple tempTuple = getApplyingTuple(table, tuple, filter1);
                
                switch (filter1.getBool_value()) {
                    case "OR":
                        if (tempTuple != null)
                            new_table.addTuple(tempTuple);
                        else{
                            
                            tempTuple = getApplyingTuple(table, tuple, filter2);
                            
                            if (tempTuple != null)
                                new_table.addTuple(tempTuple);
                            
                        }//End else
                        break;                        
                        
                    case "AND":
                        if (tempTuple != null){
                        
                            tempTuple = getApplyingTuple(table, tuple, filter2);
                            
                            if (tempTuple != null)
                                new_table.addTuple(tempTuple);
                        
                        }//End if (tempTuple != null)
                        break;
                        
                    default:
                        break;
                        
                }//End switch (filter1.getBool_value())                
                
            }//End for ( Tuple tuple : table.getTable_tuples() )            
            
        }//End else if ( filters.size() == 2 )
        
        return new_table;              
    }
    
    public static Tuple getApplyingTuple(Table table, Tuple tuple, QueryFilter filter){
    
        Tuple new_tuple = null;
        
        String f_name = filter.getFieldName();
        String operator = filter.getOperand();
        Object filter_value = filter.getFilter_value();
        
        int index = table.getFieldPosition(f_name);
        // Now we get the value in the position of the tuple
        Value val = tuple.getValue(index);                

        //Integers
        int num = 0, numFilterValue = 0;
        //Strings
        String stringValue = val.getValue().toString();

        if ( Attribute.Type.INTEGER == table.getAttributeByName(f_name).getType() )
        {                                                        

            String tempVal = val.getValue().toString();
            num = Integer.parseInt(tempVal);

            String tempFilterValue = filter_value.toString();
            numFilterValue = Integer.parseInt(tempFilterValue);

        }//End if ( Attribute.Type.INTEGER == table.getAttributeByName(f_name).getType() )

        switch (operator) 
        {                    
            case "=":                       
                if ( Attribute.Type.VARCHAR == table.getAttributeByName(f_name).getType() )
                {                                                        
                    if (stringValue.equals(filter_value.toString()))
                        new_tuple = tuple;                          
                }
                else if ( Attribute.Type.INTEGER == table.getAttributeByName(f_name).getType() )
                {                   

                    if (num == numFilterValue)
                        new_tuple =  tuple;                                       
                }
                break;

            case "<>":
                if ( Attribute.Type.VARCHAR == table.getAttributeByName(f_name).getType() )
                {                            
                    if (!stringValue.equals(filter_value.toString()))
                        new_tuple = tuple;                          
                }
                else if ( Attribute.Type.INTEGER == table.getAttributeByName(f_name).getType() )
                {                    

                    if ((num < numFilterValue) || (num > numFilterValue))
                        new_tuple = tuple;                                    
                }       
                break;

            case ">":      
                if ( Attribute.Type.INTEGER == table.getAttributeByName(f_name).getType() )
                {                    

                    if (num > numFilterValue)
                        new_tuple = tuple;                                       
                }                 
                break;

            case "<":
                if ( Attribute.Type.INTEGER == table.getAttributeByName(f_name).getType() )
                {                                              

                    if (num < numFilterValue)
                        new_tuple = tuple;

                }                         
                break;                      

            default:
                break;

        }// End switch (operator)        
        
        return new_tuple;
        
    }//End public Tuple getApplyingTuples()

    public Tuple getContainedTuple() {
   
        return new Tuple();
   
    }
    
    /**
     * @return the field_name
     */
    public String getField_name() {
        return field_name;
    }

    /**
     * @return the field_position
     */
    public int getField_position() {
        return field_position;
    }

    /**
     * @return the operand
     */
    public String getOperand() {
        return operand;
    }

    /**
     * @return the filter_value
     */
    public Object getFilter_value() {
        return filter_value;
    }

    /**
     * @return the bool_value
     */
    public String getBool_value() {
        return bool_value;
    }
}
