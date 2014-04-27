package kickassdb;

import java.util.ArrayList;

public class QueryFilter
{
    /* This class describes a WHERE query filter, specifying the field name
       operation, and filter contraint in a single object to be used later.
    */
    
    FilterPart leftFilter;
    FilterPart rightFilter;
    
    private String operand;    
    private String bool_value;
    
    
    public QueryFilter()
    {
        
    }
    
    public QueryFilter(FilterPart f1, FilterPart f2)
    {
        leftFilter = f1;
        rightFilter = f2;
    }
            
    public void setLeftFilter(FilterPart f)
    {
        leftFilter = f;
    }
    
    public FilterPart getLeftFilter()
    {
        return leftFilter;
    }
    
    public void setRightFilter(FilterPart f)
    {
        rightFilter = f;
    }
    
    public FilterPart getRightFilter()
    {
        return rightFilter;
    }
    
    public void setOperand(String o)
    {
        operand = o;
    }
    
    public String getOperand()
    {
        return operand;
    }
        
    public void setBoolValue(String b)
    {
        bool_value = b;
    }
    
    public String getBoolValue()
    {
        return bool_value;
    }
    
    public static Table filterTable(int t1Count, Table table, ArrayList<QueryFilter> filters)
    {
        /* This helper method recieves a table and removes the tuples that
           do not match the filters specified, returning a new filtered Table
        */
        
        Table new_table = new Table();
        new_table.setTable_domain(table.getTable_domain());
        
        /* Check quantity of filters */
        if ( filters.size() == 1 )
        {
            QueryFilter filter = filters.get(0);
            
            //We get the information of the filter
            //Left Side
            //FilterPart leftFilterPart = filter.leftFilter;
            //leftFilterPart.
                       
            /* Loop through the whole table and check the tuples */
            for ( Tuple tuple : table.getTable_tuples() )
            {                                             
                //Tuple tempTuple = getApplyingTuple(table, tuple, filter);
                
                Tuple tempTuple = getApplyingTuple2(table, tuple, filter);
                
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
                
                switch (filter1.getBoolValue()) 
                {
                    case "OR":
                        if (tempTuple != null)
                            new_table.addTuple(tempTuple);
                        else
                        {                            
                            tempTuple = getApplyingTuple(table, tuple, filter2);
                            
                            if (tempTuple != null)
                                new_table.addTuple(tempTuple);
                            
                        } //End else
                        break;                                                
                    case "AND":
                        if (tempTuple != null)
                        {                        
                            tempTuple = getApplyingTuple(table, tuple, filter2);
                            
                            if (tempTuple != null)
                                new_table.addTuple(tempTuple);
                        
                        } //End if (tempTuple != null)
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
        
        String f_name = "";
        Object filter_value = null;
        
        //String f_name = filter.getLeftFieldName();
        //Object filter_value = filter.getRightValue();
        String operator = filter.getOperand();
        
        //We get the left part All Filters have FieldName
        FilterPart leftFilterPart = filter.getLeftFilter();
        String leftFieldName = leftFilterPart.getFieldName();
        String leftFieldAlias = leftFilterPart.getFieldAlias();
        Object leftValue = leftFilterPart.getValue();
        
        //We get the right part
        FilterPart rightFilterPart = filter.getRightFilter();
        String rightFieldName = rightFilterPart.getFieldName();
        String rightFieldAlias = rightFilterPart.getFieldAlias();
        Object rightValue = rightFilterPart.getValue();        
        
        //Case 1 Has no alias
        switch (leftFieldName) {
            
            case "":
                //if (  )
                break;
                
            default:
                
                //We see if the field exists in the table
                //First we get the table
                Table tableWithAttributeLeft = MainWindow.getDefaultSchema().getTableWithAttributeName(leftFieldName);
                
                //Now we get Table 2 
                if (!rightFieldName.equals("")){
                
                    Table tableWithAttributeRight = MainWindow.getDefaultSchema().getTableWithAttributeName(rightFieldName);
                
                    for (Tuple tup1 : tableWithAttributeLeft.getTable_tuples()) {
                        
                        for (Tuple tup2 : tableWithAttributeRight.getTable_tuples()) {
                         
                            
                            
                        }                        
                        
                    }//End for (Tuple tuple : tableWithAttributeLeft.getTable_tuples())
                    
                }//End if (!rightFieldName.equals(""))
                
                
                
                break;
        }
        
        
        
        
        
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
    
    public static Tuple getApplyingTuple2(Table table, Tuple tuple, QueryFilter filter){

        //The result tuple
        Tuple result_tuple = null;
        
        //First we get the data from the filter
        //We start with the operator
        String operand = filter.getOperand();        
        
        //Now we get the left and right parts of the filter
        //We start with the left part
        FilterPart leftFilterPart = filter.getLeftFilter();
        String leftFieldName = leftFilterPart.getFieldName();
        String leftFieldAlias = leftFilterPart.getFieldAlias();
        Object leftValue = leftFilterPart.getValue();
        
        //We get the right part
        FilterPart rightFilterPart = filter.getRightFilter();
        String rightFieldName = rightFilterPart.getFieldName();
        String rightFieldAlias = rightFilterPart.getFieldAlias();
        Object rightValue = rightFilterPart.getValue();                 
        
        //We decide with operand it is
        switch (operand) {
            
            //Equals
            case "=":
                //Left Part
                //Now we determine the indexes of the fields
                int indexLeftEqual = table.getFieldPosition(leftFieldName);
                int indexRightEqual = table.getFieldPosition(rightFieldName);
                
                //We determine if it is a number or a String
                if ( table.getAttType(leftFieldName).equals("VARCHAR")){
                
                    String stringLeft = tuple.getTuple_values().get(indexLeftEqual).getValue().toString();
                    String stringRight = tuple.getTuple_values().get(indexRightEqual).getValue().toString();
                    
                    if ( stringLeft.equals(stringRight) )
                        result_tuple = tuple;
                    
                }//End else if (table.getAttType(leftFieldName).equals("VARCHAR"))
                else if (table.getAttType(leftFieldName).equals("INTEGER")) {                
                    
                    int intLeft = Integer.parseInt(tuple.getTuple_values().get(indexLeftEqual).getValue().toString());
                    int intRight = Integer.parseInt(tuple.getTuple_values().get(indexRightEqual).getValue().toString());
                    
                    if ( intLeft == intRight )
                        result_tuple = tuple;                    
                    
                }//End else if (table.getAttType(leftFieldName).equals("INTEGER"))                     
                break;
                
            //Greater than
            case ">":
                //Left Part
                //Now we determine the indexes of the fields
                int indexLeftG = table.getFieldPosition(leftFieldName);
                int indexRightG = table.getFieldPosition(rightFieldName);
                
                //We determine if it is a number or a String
                if ( table.getAttType(leftFieldName).equals("VARCHAR")){
                    
                }//End else if (table.getAttType(leftFieldName).equals("VARCHAR"))
                else if (table.getAttType(leftFieldName).equals("INTEGER")) {                
                    
                    int intLeft = Integer.parseInt(tuple.getTuple_values().get(indexLeftG).getValue().toString());
                    int intRight = Integer.parseInt(tuple.getTuple_values().get(indexRightG).getValue().toString());
                    
                    if ( intLeft > intRight )
                        result_tuple = tuple;                    
                    
                }//End else if (table.getAttType(leftFieldName).equals("INTEGER"))                  
                break;

            //Less than
            case "<":
                //Left Part
                //Now we determine the indexes of the fields
                int indexLeftL = table.getFieldPosition(leftFieldName);
                int indexRightL = table.getFieldPosition(rightFieldName);
                
                //We determine if it is a number or a String
                if ( table.getAttType(leftFieldName).equals("VARCHAR")){                
                    
                }//End else if (table.getAttType(leftFieldName).equals("VARCHAR"))
                else if (table.getAttType(leftFieldName).equals("INTEGER")) {                
                    
                    int intLeft = Integer.parseInt(tuple.getTuple_values().get(indexLeftL).getValue().toString());
                    int intRight = Integer.parseInt(tuple.getTuple_values().get(indexRightL).getValue().toString());
                    
                    if ( intLeft < intRight )
                        result_tuple = tuple;                    
                    
                }//End else if (table.getAttType(leftFieldName).equals("INTEGER"))                 
                break;
            
            //Different
            case "<>":
                //Left Part
                //Now we determine the indexes of the fields
                int indexLeftD = table.getFieldPosition(leftFieldName);
                int indexRightD = table.getFieldPosition(rightFieldName);
                
                //We determine if it is a number or a String
                if ( table.getAttType(leftFieldName).equals("VARCHAR")){
                
                    String stringLeft = tuple.getTuple_values().get(indexLeftD).getValue().toString();
                    String stringRight = tuple.getTuple_values().get(indexRightD).getValue().toString();
                    
                    if ( !stringLeft.equals(stringRight) )
                        result_tuple = tuple;
                    
                }//End else if (table.getAttType(leftFieldName).equals("VARCHAR"))
                else if (table.getAttType(leftFieldName).equals("INTEGER")) {                
                    
                    int intLeft = Integer.parseInt(tuple.getTuple_values().get(indexLeftD).getValue().toString());
                    int intRight = Integer.parseInt(tuple.getTuple_values().get(indexRightD).getValue().toString());
                    
                    if ( !(intLeft == intRight) )
                        result_tuple = tuple;                    
                    
                }//End else if (table.getAttType(leftFieldName).equals("INTEGER"))                 
                break;
                
            default:
                break;
                
        }//End switch (operand)
        
        return result_tuple;

    }
    
}
