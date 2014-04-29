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
    
    private String query_filter_type;
        
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
    
    private void determineQueryFilterType(){
            
        this.query_filter_type = this.leftFilter.getFilterType_type().toString() + "_" +this.rightFilter.getFilterType_type().toString();        
    
    }//End private void determineQueryFilterType()
    
    public static Table newFilterTable(Table table, ArrayList<QueryFilter> filters){
    
        //We define the result table
        Table result_table = new Table();
        result_table.setTable_domain(table.getTable_domain());
        result_table.setTable_complete_domain(table.getTable_complete_domain());
        
        //We get the number of filters that the arraylist filters has.
        int numberOfFilters = filters.size();        
        
        //Then we choose what action to take according to the number of filters.
        switch (numberOfFilters) {            
            
            case 1:
                
                //We get the operation
                String operation = filters.get(0).getOperand();
                                
                //We get the first filter
                QueryFilter filter = filters.get(0);
                
                //Now we get the left filter
                FilterPart leftFilterPart  = filter.getLeftFilter();
                //Now we get the right filter
                FilterPart rightFilterPart  = filter.getRightFilter();
                
                //Now we get the filter type
                String filterTypeLeft = leftFilterPart.getFilterType_type().toString();
                String filterTypeRight = rightFilterPart.getFilterType_type().toString();
                
                //Index of the attribute in the merged table
                int indexAttributeLeft = -1;
                //Index of the attribute with the alias in the merged table
                //int indexAliasAttributeLeft = -1;
                                
                //Values
                String valueTypeLeft = "null";
                String stringValueLeft = "null";
                int intValueLeft = -1;
                
                //We decide what to do using the filter type
                switch (filterTypeLeft) {
                    
                    //If it is an attribute then we look for the index in the merged table
                    case "ATTRIBUTE":
                        //We look for the index in the merged table
                        indexAttributeLeft = table.getFieldPosition(leftFilterPart.getFieldName());                       
                        //result_table = attributeLeftOperation(table, leftFilterPart, rightFilterPart);
                        break;
                    
                    //If it is an attribute with alias then we look for the index in the merged table
                    case "ALIAS_ATTRIBUTE":
                        //First we determine if the alias is a table
                        Table tableWithAliasEqualsName = MainWindow.getDefaultSchema().getTable(leftFilterPart.getFieldAlias());
                        
                        //If there's a table that its alias is its name then we look for the index alias + attribute in the merged table
                        if ( tableWithAliasEqualsName != null ){
                        
                            indexAttributeLeft = table.getFieldPositionCompleteDomain(tableWithAliasEqualsName.getTable_name() + leftFilterPart.getFieldName());
                        
                        }//End if ( tableWithAliasEqualsName != null )
                        else {
                        
                            //First we find the table who has that alias
                            Table tableWithAliasName = MainWindow.getDefaultSchema().getTableWithAliasName(leftFilterPart.getFieldAlias());
                            
                            //If we find it we define the indexAliasAttributeRight
                            if (tableWithAliasName != null){
                            
                                indexAttributeLeft = table.getFieldPositionCompleteDomain(tableWithAliasName.getTable_name() + leftFilterPart.getFieldName());
                            
                            }//End if (tableWithAliasName != null)
                        
                        }//End else                        
                        break;
                        
                    //If it is a value then we retrieve it
                    case "VALUE":
                        //We try to find out if the value is an integer
                        try {
                            
                            intValueLeft = Integer.parseInt(leftFilterPart.getValue().toString());
                            valueTypeLeft = "INTEGER";
                            
                        } catch (Exception e) {
                            
                            stringValueLeft = leftFilterPart.getValue().toString();
                            valueTypeLeft = "STRING";
                            
                        }//End catch (Exception e)
                        break;
                                                
                    default:
                        break;
                                                                        
                }//End switch (filterType)
                
                //Now that we have the indexes or values we can compare with the right part
                
                //Index of the attribute in the merged table
                int indexAttributeRight = -1;
                //Index of the attribute with the alias in the merged table
                //int indexAliasAttributeRight = -1;
                                
                //Values
                String valueTypeRight = "null";
                String stringValueRight = "null";
                int intValueRight = -1;
                                                
                //We decide what to do using the filter type on the right side
                switch (filterTypeRight) {
                    
                    //If it is an attribute then we look for the index in the merged table
                    case "ATTRIBUTE":
                        //We look for the index in the merged table
                        indexAttributeRight = table.getFieldPosition(rightFilterPart.getFieldName());                       
                        //result_table = attributeLeftOperation(table, leftFilterPart, rightFilterPart);
                        break;
                    
                    //If it is an attribute with alias then we look for the index in the merged table
                    case "ALIAS_ATTRIBUTE":
                        //First we determine if the alias is a table
                        Table tableWithAliasEqualsName = MainWindow.getDefaultSchema().getTable(rightFilterPart.getFieldAlias());
                        
                        //If there's a table that its alias is its name then we look for the index alias + attribute in the merged table
                        if ( tableWithAliasEqualsName != null ){
                        
                            indexAttributeRight = table.getFieldPositionCompleteDomain(tableWithAliasEqualsName.getTable_name() + rightFilterPart.getFieldName());
                        
                        }//End if ( tableWithAliasEqualsName != null )
                        else {
                        
                            //First we find the table who has that alias
                            Table tableWithAliasName = MainWindow.getDefaultSchema().getTableWithAliasName(rightFilterPart.getFieldAlias());
                            
                            //If we find it we define the indexAliasAttributeRight
                            if (tableWithAliasName != null){
                            
                                indexAttributeRight = table.getFieldPositionCompleteDomain(tableWithAliasName.getTable_name() + rightFilterPart.getFieldName());
                            
                            }//End if (tableWithAliasName != null)
                        
                        }//End else                        
                        break;
                        
                    //If it is a value then we retrieve it
                    case "VALUE":
                        //We try to find out if the value is an integer
                        try {
                            
                            intValueRight = Integer.parseInt(rightFilterPart.getValue().toString());
                            valueTypeRight = "INTEGER";
                            
                        } catch (Exception e) {
                            
                            stringValueRight = rightFilterPart.getValue().toString();
                            valueTypeRight = "STRING";
                            
                        }//End catch (Exception e)
                        break;
                                                                                                                                     
                    default:
                        break;
                                                                        
                }//End switch (filterType)
                
                //Now that we have everything we decide what to do next
                String nextOperation = filterTypeLeft + "_" + filterTypeRight;
                
                switch (nextOperation) {
                    case "ATTRIBUTE_ALIAS_ATTRIBUTE": case "ATTRIBUTE_ATTRIBUTE": case "ALIAS_ATTRIBUTE_ATTRIBUTE":
                        case "ALIAS_ATTRIBUTE_ALIAS_ATTRIBUTE":
                        result_table = Operation2Attributes(table, indexAttributeLeft, indexAttributeRight, operation);
                        break;
                        
                    case "ATTRIBUTE_VALUE":
                        if ( "INTEGER".equals(valueTypeRight))
                            result_table = OperationAttributeValue(table, indexAttributeLeft, intValueRight, operation);
                        else
                            result_table = OperationAttributeValue(table, indexAttributeLeft, stringValueRight, operation);
                        break;                            
                        
                    case "ALIAS_ATTRIBUTE_VALUE":
                        if ("INTEGER".equals(valueTypeRight))
                            result_table = OperationAttributeValue(table, indexAttributeLeft, intValueRight, operation);
                        else
                            result_table = OperationAttributeValue(table, indexAttributeLeft, stringValueRight, operation);                        
                        break;                        
                        
                    case "VALUE_ATTRIBUTE":
                        if ( "INTEGER".equals(valueTypeLeft))
                            result_table = OperationAttributeValue(table, indexAttributeRight, intValueLeft, operation);
                        else
                            result_table = OperationAttributeValue(table, indexAttributeRight, stringValueLeft, operation);
                        break;                            
                        
                    case "VALUE_ALIAS_ATTRIBUTE":
                        if ("INTEGER".equals(valueTypeLeft))
                            result_table = OperationAttributeValue(table, indexAttributeRight, intValueLeft, operation);
                        else
                            result_table = OperationAttributeValue(table, indexAttributeRight, stringValueLeft, operation);                        
                        break;         
                        
                    case "VALUE_VALUE":
                        if ("INTEGER".equals(valueTypeLeft))
                            result_table = Operation2Value(table, intValueLeft, intValueRight, operation);
                        else
                            result_table = Operation2Value(table, stringValueLeft, stringValueRight, operation);                                                
                        break;
                        
                    default:
                        break;
                        
                }//End switch (nextOperation)                           
                
                break;
                
            case 2:
                
                //We will set the values of the filters
                QueryFilter filterLeft = setNewFilter(table, filters.get(1));
                filterLeft.determineQueryFilterType();
                QueryFilter filterRight = setNewFilter(table, filters.get(0));
                filterRight.determineQueryFilterType();
                
                //We get the boolean value
                String boolOperand = filterRight.getBoolValue();
                
                //Now that we have everything set we iterate
                for (Tuple tuple : table.getTable_tuples()) {
                    
                    System.out.println("");
                    
                    boolean resultLeft = isTupleValid(table, tuple, filterLeft);
                    
                    //Now we will take action using the AND or OR
                    switch (boolOperand) {
                        
                        case "OR":
                            if (resultLeft == true)
                                result_table.addTuple(tuple);
                            else {
                            
                                boolean resultRight = isTupleValid(table, tuple, filterRight);
                                
                                if (resultRight == true)
                                    result_table.addTuple(tuple);
                                
                            }//End else                                        
                            break;
                        
                        case "AND":
                            
                            boolean resultRight = isTupleValid(table, tuple, filterRight);                            
                            
                            if ((resultLeft == true) && (resultRight == true))
                                result_table.addTuple(tuple);
                            
                            break;
                            
                        default:
                            break;
                            
                    }//End switch (boolOperand)
                        
                }//End for (Tuple tuple : table.getTable_tuples())                
                break;
                
            default:
                break;
                
        }//End switch (numberOfFilters)
                
        return result_table;
    
    }//End public static Table newFilterTable(Table table, ArrayList<QueryFilter> filters)
    
    private static QueryFilter setNewFilter(Table table, QueryFilter filter){
    
                //Now we get the left filter
                FilterPart leftFilterPart  = filter.getLeftFilter();
                //Now we get the right filter
                FilterPart rightFilterPart  = filter.getRightFilter();
                
                //Now we get the filter type
                String filterTypeLeft = leftFilterPart.getFilterType_type().toString();
                String filterTypeRight = rightFilterPart.getFilterType_type().toString();
                                
                //We will set the attributes of the query Filter
                //We decide what to do using the filter type
                switch (filterTypeLeft) {
                    
                    //If it is an attribute then we look for the index in the merged table
                    case "ATTRIBUTE":
                        //We look for the index in the merged table
                        leftFilterPart.setIndexAttributeLeft(table.getFieldPosition(leftFilterPart.getFieldName()));
                        //indexAttributeLeft = table.getFieldPosition(leftFilterPart.getFieldName());                      
                        break;
                    
                    //If it is an attribute with alias then we look for the index in the merged table
                    case "ALIAS_ATTRIBUTE":
                        //First we determine if the alias is a table
                        Table tableWithAliasEqualsName = MainWindow.getDefaultSchema().getTable(leftFilterPart.getFieldAlias());
                        
                        //If there's a table that its alias is its name then we look for the index alias + attribute in the merged table
                        if ( tableWithAliasEqualsName != null ){
                        
                            
                            leftFilterPart.setIndexAttributeLeft(table.getFieldPositionCompleteDomain(tableWithAliasEqualsName.getTable_name() + leftFilterPart.getFieldName()));
                            //indexAttributeLeft = table.getFieldPositionCompleteDomain(tableWithAliasEqualsName.getTable_name() + leftFilterPart.getFieldName());
                        
                        }//End if ( tableWithAliasEqualsName != null )
                        else {
                        
                            //First we find the table who has that alias
                            Table tableWithAliasName = MainWindow.getDefaultSchema().getTableWithAliasName(leftFilterPart.getFieldAlias());
                            
                            //If we find it we define the indexAliasAttributeRight
                            if (tableWithAliasName != null){
                            
                                leftFilterPart.setIndexAttributeLeft(table.getFieldPositionCompleteDomain(tableWithAliasName.getTable_name() + leftFilterPart.getFieldName()));
                                //indexAttributeLeft = table.getFieldPositionCompleteDomain(tableWithAliasName.getTable_name() + leftFilterPart.getFieldName());
                            
                            }//End if (tableWithAliasName != null)
                        
                        }//End else                        
                        break;
                        
                    //If it is a value then we retrieve it
                    case "VALUE":
                        //We try to find out if the value is an integer
                        try {
                            
                            leftFilterPart.setIntValueLeft(Integer.parseInt(leftFilterPart.getValue().toString()));
                            leftFilterPart.setValueTypeLeft("INTEGER");
                            
//                            intValueLeft = Integer.parseInt(leftFilterPart.getValue().toString());
//                            valueTypeLeft = "INTEGER";
                            
                        } catch (Exception e) {
                            
                            leftFilterPart.setStringValueLeft(leftFilterPart.getValue().toString());
                            leftFilterPart.setValueTypeLeft("STRING");                            
                            
//                            stringValueLeft = leftFilterPart.getValue().toString();
//                            valueTypeLeft = "STRING";
                            
                        }//End catch (Exception e)
                        break;
                                                
                    default:
                        break;
                                                                        
                }//End switch (filterType)
                
                //Now that we have the indexes or values we can compare with the right part
                
//                //Index of the attribute in the merged table
//                int indexAttributeRight = -1;
//                //Index of the attribute with the alias in the merged table
//                //int indexAliasAttributeRight = -1;
//                                
//                //Values
//                String valueTypeRight = "null";
//                String stringValueRight = "null";
//                int intValueRight = -1;
                                                
                //We decide what to do using the filter type on the right side
                switch (filterTypeRight) {
                    
                    //If it is an attribute then we look for the index in the merged table
                    case "ATTRIBUTE":
                        //We look for the index in the merged table
                        rightFilterPart.setIndexAttributeRight(table.getFieldPosition(rightFilterPart.getFieldName()));
                        //indexAttributeRight = table.getFieldPosition(rightFilterPart.getFieldName());                      
                        //result_table = attributeLeftOperation(table, leftFilterPart, rightFilterPart);
                        break;
                    
                    //If it is an attribute with alias then we look for the index in the merged table
                    case "ALIAS_ATTRIBUTE":
                        //First we determine if the alias is a table
                        Table tableWithAliasEqualsName = MainWindow.getDefaultSchema().getTable(rightFilterPart.getFieldAlias());
                        
                        //If there's a table that its alias is its name then we look for the index alias + attribute in the merged table
                        if ( tableWithAliasEqualsName != null ){
                        
                            rightFilterPart.setIndexAttributeRight(table.getFieldPositionCompleteDomain(tableWithAliasEqualsName.getTable_name() + rightFilterPart.getFieldName()));
                            //indexAttributeRight = table.getFieldPositionCompleteDomain(tableWithAliasEqualsName.getTable_name() + rightFilterPart.getFieldName());
                        
                        }//End if ( tableWithAliasEqualsName != null )
                        else {
                        
                            //First we find the table who has that alias
                            Table tableWithAliasName = MainWindow.getDefaultSchema().getTableWithAliasName(rightFilterPart.getFieldAlias());
                            
                            //If we find it we define the indexAliasAttributeRight
                            if (tableWithAliasName != null){
                            
                                rightFilterPart.setIndexAttributeRight(table.getFieldPositionCompleteDomain(tableWithAliasName.getTable_name() + rightFilterPart.getFieldName()));
                                //indexAttributeRight = table.getFieldPositionCompleteDomain(tableWithAliasName.getTable_name() + rightFilterPart.getFieldName());
                            
                            }//End if (tableWithAliasName != null)
                        
                        }//End else                        
                        break;
                        
                    //If it is a value then we retrieve it
                    case "VALUE":
                        //We try to find out if the value is an integer
                        try {
                            
                            
                            rightFilterPart.setIntValueRight(Integer.parseInt(rightFilterPart.getValue().toString()));
                            rightFilterPart.setValueTypeRight("INTEGER");
                            
//                            intValueRight = Integer.parseInt(rightFilterPart.getValue().toString());
//                            valueTypeRight = "INTEGER";
                            
                        } catch (Exception e) {
                            
                            rightFilterPart.setStringValueRight(rightFilterPart.getValue().toString());
                            rightFilterPart.setValueTypeRight("STRING");                            
                            
//                            stringValueRight = rightFilterPart.getValue().toString();
//                            valueTypeRight = "STRING";
                            
                        }//End catch (Exception e)
                        break;
                                                                                                                                     
                    default:
                        break;
                                                                        
                }//End switch (filterType)
                
                filter.setLeftFilter(leftFilterPart);
                filter.setRightFilter(rightFilterPart);
                
                return filter;
        
    }//End private static QueryFilter setNewFilter(Table table, QueryFilter filter)
    
    private static boolean isTupleValid(Table table, Tuple tuple, QueryFilter queryFilter){
                    
        boolean result = false;

        int indexLeft = queryFilter.getLeftFilter().getIndexAttributeLeft();        
        int indexRight = queryFilter.getRightFilter().getIndexAttributeRight();
        
        switch (queryFilter.query_filter_type) {
            case "ATTRIBUTE_ALIAS_ATTRIBUTE": case "ATTRIBUTE_ATTRIBUTE": case "ALIAS_ATTRIBUTE_ATTRIBUTE":
                case "ALIAS_ATTRIBUTE_ALIAS_ATTRIBUTE":
                    String attributeTypeLeft = table.getAttTypeByPosition(queryFilter.leftFilter.getIndexAttributeLeft());
                    String attributeTypeRight = table.getAttTypeByPosition(queryFilter.rightFilter.getIndexAttributeRight());
                    result = TupleOperation2Attributes(tuple, attributeTypeLeft, attributeTypeRight, queryFilter.leftFilter.getIndexAttributeLeft(), queryFilter.rightFilter.getIndexAttributeRight(), queryFilter.getOperand());
                break;

            case "ATTRIBUTE_VALUE":
                if ( "INTEGER".equals(queryFilter.rightFilter.getValueTypeRight()))
                    result = TupleOperationAttributeValue(tuple, "INTEGER", indexLeft ,queryFilter.rightFilter.getIntValueRight(), queryFilter.getOperand());
                else
                    result = TupleOperationAttributeValue(tuple, "VARCHAR", indexLeft, queryFilter.rightFilter.getStringValueRight(), queryFilter.getOperand());
                break;                            

            case "ALIAS_ATTRIBUTE_VALUE":
                if ("INTEGER".equals(queryFilter.rightFilter.getValueTypeRight()))
                    result = TupleOperationAttributeValue(tuple, "INTEGER", indexLeft, queryFilter.rightFilter.getIntValueRight(), queryFilter.getOperand());
                else
                    result = TupleOperationAttributeValue(tuple, "VARCHAR", indexLeft, queryFilter.rightFilter.getStringValueRight(), queryFilter.getOperand());                        
                break;                        

            case "VALUE_ATTRIBUTE":
                if ( "INTEGER".equals(queryFilter.rightFilter.getValueTypeRight()))
                    result = TupleOperationAttributeValue(tuple, "INTEGER", indexRight, queryFilter.rightFilter.getIntValueRight(), queryFilter.getOperand());
                else
                    result = TupleOperationAttributeValue(tuple, "VARCHAR", indexRight, queryFilter.rightFilter.getStringValueRight(), queryFilter.getOperand());
                break;                            

            case "VALUE_ALIAS_ATTRIBUTE":
                if ("INTEGER".equals(queryFilter.rightFilter.getValueTypeRight()))
                    result = TupleOperationAttributeValue(tuple, "INTEGER", indexRight, queryFilter.rightFilter.getIntValueRight(), queryFilter.getOperand());
                else
                    result = TupleOperationAttributeValue(tuple, "VARCHAR", indexRight, queryFilter.rightFilter.getStringValueRight(), queryFilter.getOperand());                        
                break;         

            case "VALUE_VALUE":
                //if ("INTEGER".equals(valueTypeLeft))
                    //result = Operation2Value(table, intValueLeft, intValueRight, operation);
                //else
                    //result = Operation2Value(table, stringValueLeft, stringValueRight, operation);                                                
                break;

            default:
                break;

        }//End switch (nextOperation)
        
        return result;
    }
    
    
    private static Table Operation2Attributes(Table table, int index1, int index2, String operation){
    
        Table result_table = new Table();
        result_table.setTable_domain(table.getTable_domain());
        result_table.setTable_complete_domain(table.getTable_complete_domain());
        
        String attributeTypeLeft = table.getAttTypeByPosition(index1);
        String attributeTypeRight = table.getAttTypeByPosition(index2);
        
        for (Tuple tuple : table.getTable_tuples()) {
                                    
            switch (operation) {
                case "=":
                    if ( attributeTypeLeft.equals("INTEGER") && attributeTypeRight.equals("INTEGER") ){
                    
                        int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());
                        int value2 = Integer.parseInt(tuple.getValue(index2).getValue().toString());
                    
                        if ( value1 == value2 )
                            result_table.addTuple(tuple);
                        
                    } else {
                    
                        String value1 = tuple.getValue(index1).getValue().toString();
                        String value2 = tuple.getValue(index2).getValue().toString();
                    
                        if ( value1.equals(value2) )
                            result_table.addTuple(tuple);                    
                    
                    }//End else                    
                    break;
                    
                case ">":
                    if ( attributeTypeLeft.equals("INTEGER") && attributeTypeRight.equals("INTEGER") ){
                    
                        int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());
                        int value2 = Integer.parseInt(tuple.getValue(index2).getValue().toString());
                    
                        if ( value1 > value2 )
                            result_table.addTuple(tuple);
                        
                    } else {
                    
                        String value1 = tuple.getValue(index1).getValue().toString();
                        String value2 = tuple.getValue(index2).getValue().toString();
                    
                        if ( value1.length() > value2.length() )
                            result_table.addTuple(tuple);                    
                    
                    }//End else               
                    break;

                case "<":
                    if ( attributeTypeLeft.equals("INTEGER") && attributeTypeRight.equals("INTEGER") ){
                    
                        int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());
                        int value2 = Integer.parseInt(tuple.getValue(index2).getValue().toString());
                    
                        if ( value1 < value2 )
                            result_table.addTuple(tuple);
                        
                    } else {
                    
                        String value1 = tuple.getValue(index1).getValue().toString();
                        String value2 = tuple.getValue(index2).getValue().toString();
                    
                        if ( value1.length() < value2.length() )
                            result_table.addTuple(tuple);                    
                    
                    }//End else                                   
                    break;

                case "<>":
                    if ( attributeTypeLeft.equals("INTEGER") && attributeTypeRight.equals("INTEGER") ){
                    
                        int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());
                        int value2 = Integer.parseInt(tuple.getValue(index2).getValue().toString());
                    
                        if ( !(value1 == value2) )
                            result_table.addTuple(tuple);
                        
                    } else {
                    
                        String value1 = tuple.getValue(index1).getValue().toString();
                        String value2 = tuple.getValue(index2).getValue().toString();
                    
                        if ( !value1.equals(value2) )
                            result_table.addTuple(tuple);                    
                    
                    }//End else                     
                    break;                    
                    
                default:
                    break;
            }//End 
            
        }//End for (Tuple tuple : table.getTable_tuples())           
    
        return result_table;
        
    }//End 

    private static Table Operation2Value(Table table, int lValue, int rValue, String operation){
    
        Table result_table = new Table();
        result_table.setTable_domain(table.getTable_domain());
        result_table.setTable_complete_domain(table.getTable_complete_domain());
        
        for (Tuple tuple : table.getTable_tuples()) {
                                    
            switch (operation) {
                case "=":

                    if ( lValue == rValue )
                        result_table.addTuple(tuple);
                                          
                    break;
                    
                case ">":
                    
                    if ( lValue > rValue )
                        result_table.addTuple(tuple);             
                    
                    break;

                case "<":
                    
                    if ( lValue < rValue )
                        result_table.addTuple(tuple);             
                                  
                    break;

                case "<>":
                    
                    if ( !(lValue == rValue) )
                        result_table.addTuple(tuple);             
                   
                    break;                    
                    
                default:
                    break;
            }//End 
            
        }//End for (Tuple tuple : table.getTable_tuples())           
    
        return result_table;
        
    }//End     

    private static Table Operation2Value(Table table, String lValue, String rValue, String operation){
    
        Table result_table = new Table();
        result_table.setTable_domain(table.getTable_domain());
        result_table.setTable_complete_domain(table.getTable_complete_domain());
        
        for (Tuple tuple : table.getTable_tuples()) {
                                    
            switch (operation) {
                case "=":

                    if ( lValue.equals(rValue) )
                        result_table.addTuple(tuple);
                                          
                    break;
                    
                case ">":
                    
                    if ( lValue.length() > rValue.length() )
                        result_table.addTuple(tuple);             
                    
                    break;

                case "<":
                    
                    if ( lValue.length() < rValue.length() )
                        result_table.addTuple(tuple);             
                                  
                    break;

                case "<>":
                    
                    if ( !(lValue.equals(rValue)) )
                        result_table.addTuple(tuple);             
                   
                    break;                    
                    
                default:
                    break;
            }//End 
            
        }//End for (Tuple tuple : table.getTable_tuples())           
    
        return result_table;
        
    }//End         
    
    private static Table OperationAttributeValue(Table table, int index1, int value, String operation){
    
        Table result_table = new Table();
        result_table.setTable_domain(table.getTable_domain());
        result_table.setTable_complete_domain(table.getTable_complete_domain());
        
        String attributeTypeLeft = table.getAttTypeByPosition(index1);
        
        for (Tuple tuple : table.getTable_tuples()) {
                                    
            switch (operation) {
                case "=":
                    if ( attributeTypeLeft.equals("INTEGER") ){
                    
                        int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());
                    
                        if ( value1 == value )
                            result_table.addTuple(tuple);
                        
                    }                   
                    break;
                    
                case ">":
                    if ( attributeTypeLeft.equals("INTEGER") ){
                    
                        int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());
                    
                        if ( value1 > value )
                            result_table.addTuple(tuple);
                        
                    }              
                    break;

                case "<":
                    if ( attributeTypeLeft.equals("INTEGER") ){
                    
                        int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());
                    
                        if ( value1 < value )
                            result_table.addTuple(tuple);
                        
                    }                             
                    break;

                case "<>":
                    if ( attributeTypeLeft.equals("INTEGER") ){
                    
                        int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());
                    
                        if ( !(value1 == value) )
                            result_table.addTuple(tuple);
                        
                    }                    
                    break;                    
                    
                default:
                    break;
            }//End 
            
        }//End for (Tuple tuple : table.getTable_tuples())           
    
        return result_table;
        
    }//End 
    
    private static Table OperationAttributeValue(Table table, int index1, String value, String operation){
    
        Table result_table = new Table();
        result_table.setTable_domain(table.getTable_domain());
        result_table.setTable_complete_domain(table.getTable_complete_domain());
        
        String attributeTypeLeft = table.getAttTypeByPosition(index1);
        
        for (Tuple tuple : table.getTable_tuples()) {
                                    
            switch (operation) {
                case "=":
                    if ( attributeTypeLeft.equals("VARCHAR") ){
                    
                        String value1 = tuple.getValue(index1).getValue().toString();
                    
                        if ( value1.equals(value) )
                            result_table.addTuple(tuple);
                        
                    }                   
                    break;
                    
                case ">":
                    if ( attributeTypeLeft.equals("VARCHAR") ){
                    
                        String value1 = tuple.getValue(index1).getValue().toString();
                    
                        if ( value1.length() > value.length() )
                            result_table.addTuple(tuple);
                        
                    }              
                    break;

                case "<":
                    if ( attributeTypeLeft.equals("VARCHAR") ){
                    
                        String value1 = tuple.getValue(index1).getValue().toString();
                    
                        if ( value1.length() < value.length() )
                            result_table.addTuple(tuple);
                        
                    }                             
                    break;

                case "<>":
                    if ( attributeTypeLeft.equals("VARCHAR") ){
                    
                        String value1 = tuple.getValue(index1).getValue().toString();
                    
                        if ( !(value1.equals(value)) )
                            result_table.addTuple(tuple);
                        
                    }                    
                    break;                    
                    
                default:
                    break;
            }//End 
            
        }//End for (Tuple tuple : table.getTable_tuples())           
    
        return result_table;
        
    }//End         
           
    //Operations Tuple
    private static boolean TupleOperation2Attributes(Tuple tuple, String attributeTypeLeft, String attributeTypeRight, int index1, int index2, String operation){
    
        boolean result = false;        
        
        switch (operation) {
            case "=":
                if ( attributeTypeLeft.equals("INTEGER") && attributeTypeRight.equals("INTEGER") ){

                    int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());
                    int value2 = Integer.parseInt(tuple.getValue(index2).getValue().toString());

                    if ( value1 == value2 )
                        result = true;

                } else {

                    String value1 = tuple.getValue(index1).getValue().toString();
                    String value2 = tuple.getValue(index2).getValue().toString();

                    if ( value1.equals(value2) )
                        result = true;                

                }//End else                    
                break;

            case ">":
                if ( attributeTypeLeft.equals("INTEGER") && attributeTypeRight.equals("INTEGER") ){

                    int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());
                    int value2 = Integer.parseInt(tuple.getValue(index2).getValue().toString());

                    if ( value1 > value2 )
                        result = true;

                } else {

                    String value1 = tuple.getValue(index1).getValue().toString();
                    String value2 = tuple.getValue(index2).getValue().toString();

                    if ( value1.length() > value2.length() )
                        result = true;              

                }//End else               
                break;

            case "<":
                if ( attributeTypeLeft.equals("INTEGER") && attributeTypeRight.equals("INTEGER") ){

                    int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());
                    int value2 = Integer.parseInt(tuple.getValue(index2).getValue().toString());

                    if ( value1 < value2 )
                        result = true;

                } else {

                    String value1 = tuple.getValue(index1).getValue().toString();
                    String value2 = tuple.getValue(index2).getValue().toString();

                    if ( value1.length() < value2.length() )
                        result = true;                  

                }//End else                                   
                break;

            case "<>":
                if ( attributeTypeLeft.equals("INTEGER") && attributeTypeRight.equals("INTEGER") ){

                    int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());
                    int value2 = Integer.parseInt(tuple.getValue(index2).getValue().toString());

                    if ( !(value1 == value2) )
                        result = true;

                } else {

                    String value1 = tuple.getValue(index1).getValue().toString();
                    String value2 = tuple.getValue(index2).getValue().toString();

                    if ( !value1.equals(value2) )
                        result = true;                   

                }//End else                     
                break;                    

            default:
                break;
        }//End         


        return result;
        
    }//End private static boolean TupleOperation2Attributes(Tuple tuple, String attributeTypeLeft, String attributeTypeRight, int index1, int index2, String operation)
        
    private static boolean TupleOperation2Value(Table table, int lValue, int rValue, String operation){
    
        return true;
        
    }
    
    private static boolean TupleOperation2Value(Table table, String lValue, String rValue, String operation){
    
        return true;
        
    }    
    
    private static boolean TupleOperationAttributeValue(Tuple tuple, String attributeType, int index1, String value, String operation){
         
        boolean result = false;
        
        switch (operation) {
            case "=":
                if ( attributeType.equals("VARCHAR") ){

                    String value1 = tuple.getValue(index1).getValue().toString();

                    if ( value1.equals(value) )
                        result = true;

                }                   
                break;

            case ">":
                if ( attributeType.equals("VARCHAR") ){

                    String value1 = tuple.getValue(index1).getValue().toString();

                    if ( value1.length() > value.length() )
                        result = true;

                }              
                break;

            case "<":
                if ( attributeType.equals("VARCHAR") ){

                    String value1 = tuple.getValue(index1).getValue().toString();

                    if ( value1.length() < value.length() )
                        result = true;

                }                             
                break;

            case "<>":
                if ( attributeType.equals("VARCHAR") ){

                    String value1 = tuple.getValue(index1).getValue().toString();

                    if ( !(value1.equals(value)) )
                        result = true;

                }                    
                break;                    

            default:
                break;
                
        }//End         
        
        return result;
    
    }//End private static boolean TupleOperationAttributeValue(Tuple tuple, String attributeType, int index1, String value, String operation)
    
    private static boolean TupleOperationAttributeValue(Tuple tuple, String attributeType, int index1, int value, String operation){    
                                    
        boolean result = false;
        
        switch (operation) {
            case "=":
                if ( attributeType.equals("INTEGER") ){

                    int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());

                    if ( value1 == value )
                        result = true;

                }                   
                break;

            case ">":
                if ( attributeType.equals("INTEGER") ){

                    int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());

                    if ( value1 > value )
                        result = true;

                }              
                break;

            case "<":
                if ( attributeType.equals("INTEGER") ){

                    int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());

                    if ( value1 < value )
                        result = true;

                }                             
                break;

            case "<>":
                if ( attributeType.equals("INTEGER") ){

                    int value1 = Integer.parseInt(tuple.getValue(index1).getValue().toString());

                    if ( !(value1 == value) )
                        result = true;

                }                    
                break;                    

            default:
                break;
        }//End 

        return result;
    
    }    
    
    //Operations
    private static Table attributeLeftOperation(Table table, FilterPart left, FilterPart right){
    
        //First we look up the attribute in the table to get the index
        int indexAttributeLeft = table.getFieldPosition(left.getFieldName());
        
        //We get the filter t
        
        //No
//        switch (var) {
//            case val:
//                
//                break;
//            default:
//                throw new AssertionError();
//        }
    
        return new Table();
        
    }//End private Tuple attributeLeftOperation(Table table, FilterPart left, FilterPart right)
    
    
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
                
                Tuple tempTuple = getApplyingTuple2(t1Count, table, tuple, filter);
                
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
                Tuple tempTuple = getApplyingTuple(t1Count, table, tuple, filter1);
                
                switch (filter1.getBoolValue()) 
                {
                    case "OR":
                        if (tempTuple != null)
                            new_table.addTuple(tempTuple);
                        else
                        {                            
                            tempTuple = getApplyingTuple(t1Count, table, tuple, filter2);
                            
                            if (tempTuple != null)
                                new_table.addTuple(tempTuple);
                            
                        } //End else
                        break;                                                
                    case "AND":
                        if (tempTuple != null)
                        {                        
                            tempTuple = getApplyingTuple(t1Count, table, tuple, filter2);
                            
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
    
    public static Tuple getApplyingTuple(int lastIndexTable1, Table table, Tuple tuple, QueryFilter filter){
    
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
    
    public static Tuple getApplyingTuple2(int lastIndexTable1, Table table, Tuple tuple, QueryFilter filter){

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
                
                //Case 1 Field Name is different from ""
                if ( !leftFieldName.equals("")  ){
                
                
                
                }//End if ( !leftFieldName.equals("")  )                                           
                
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
    
    //Operations Left Side
    private Tuple OperationsLeftSideColumnEqual(int lastIndexTable1, Table table, Tuple tuple, FilterPart leftFilterPart, FilterPart rightFilterPart){
    
        Tuple result_tuple = null;
        
        String rightFieldName = rightFilterPart.getFieldName();
        String rightFieldAlias = rightFilterPart.getFieldAlias();
        Object rightValue = rightFilterPart.getValue();           
        
        //We start with the left part
        String leftFieldName = leftFilterPart.getFieldName();
        String leftFieldAlias = leftFilterPart.getFieldAlias();
        Object leftValue = leftFilterPart.getValue();        
        
        //Left Part
        //Now we determine the indexes of the fields
        int indexLeftEqual = table.getFieldPosition(leftFieldName);

        //There are three cases
        //1.    Right part is a column
        //2.    Right part is a alias + column
        //3.    Right part is a value
                
        //Right part is a column and no alias
        if ( !rightFieldName.equals("") && rightFieldAlias.equals("") ){
                    
            //Index of the position of the column 
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
                                
        }//End if ( !rightFieldName.equals("") && rightFieldAlias.equals("") )
                
        //Right part is a alias + column
        if ( !rightFieldName.equals("") && !rightFieldAlias.equals("") ){            
            
            //Now we look for the value
            Table aliasTable = MainWindow.getDefaultSchema().getTableWithAliasName(rightFieldAlias);
            //We get the position in the table
            int tempIndex = aliasTable.getFieldPosition(rightFieldName);
            //Index of the position of the column
            int indexRightEqual = lastIndexTable1 + tempIndex;            
            
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
            
        }//End if ( !rightFieldName.equals("") && !rightFieldAlias.equals("") )
        
        //Right part is a value
        if ( rightValue != null ){
        
            //We determine if it is a number or a String
            if ( table.getAttType(leftFieldName).equals("VARCHAR")){

                String stringLeft = tuple.getTuple_values().get(indexLeftEqual).getValue().toString();
                String stringRight = rightValue.toString();

                if ( stringLeft.equals(stringRight) )
                    result_tuple = tuple;

            }//End else if (table.getAttType(leftFieldName).equals("VARCHAR"))
            else if (table.getAttType(leftFieldName).equals("INTEGER")) {                

                int intLeft = Integer.parseInt(tuple.getTuple_values().get(indexLeftEqual).getValue().toString());
                int intRight = Integer.parseInt(rightValue.toString());

                if ( intLeft == intRight )
                    result_tuple = tuple;                    

            }//End else if (table.getAttType(leftFieldName).equals("INTEGER"))                       
        
        }//End if ( rightValue != null )
                
        return result_tuple;
    
    }//End private Tuple OperationsLeftSide()
    
    private Tuple OperationsLeftSideColumnGreaterThan(int lastIndexTable1, Table table, Tuple tuple, FilterPart leftFilterPart, FilterPart rightFilterPart){
    
        Tuple result_tuple = null;
        
        String rightFieldName = rightFilterPart.getFieldName();
        String rightFieldAlias = rightFilterPart.getFieldAlias();
        Object rightValue = rightFilterPart.getValue();           
        
        //We start with the left part
        String leftFieldName = leftFilterPart.getFieldName();
        String leftFieldAlias = leftFilterPart.getFieldAlias();
        Object leftValue = leftFilterPart.getValue();        
        
        //Left Part
        //Now we determine the indexes of the fields
        int indexLeftEqual = table.getFieldPosition(leftFieldName);

        //There are three cases
        //1.    Right part is a column
        //2.    Right part is a alias + column
        //3.    Right part is a value
                
        //Right part is a column and no alias
        if ( !rightFieldName.equals("") && rightFieldAlias.equals("") ){
                    
            //Index of the position of the column 
            int indexRightEqual = table.getFieldPosition(rightFieldName);
        
            //We determine if it is a number or a String
            if ( table.getAttType(leftFieldName).equals("VARCHAR")){

//                String stringLeft = tuple.getTuple_values().get(indexLeftEqual).getValue().toString();
//                String stringRight = tuple.getTuple_values().get(indexRightEqual).getValue().toString();
//
//                if ( stringLeft.equals(stringRight) )
//                    result_tuple = tuple;
                
            }//End else if (table.getAttType(leftFieldName).equals("VARCHAR"))
            else if (table.getAttType(leftFieldName).equals("INTEGER")) {                

                int intLeft = Integer.parseInt(tuple.getTuple_values().get(indexLeftEqual).getValue().toString());
                int intRight = Integer.parseInt(tuple.getTuple_values().get(indexRightEqual).getValue().toString());

                if ( intLeft > intRight )
                    result_tuple = tuple;                    

            }//End else if (table.getAttType(leftFieldName).equals("INTEGER"))                
                                
        }//End if ( !rightFieldName.equals("") && rightFieldAlias.equals("") )
                
        //Right part is a alias + column
        if ( !rightFieldName.equals("") && !rightFieldAlias.equals("") ){            
            
            //Now we look for the value
            Table aliasTable = MainWindow.getDefaultSchema().getTableWithAliasName(rightFieldAlias);
            //We get the position in the table
            int tempIndex = aliasTable.getFieldPosition(rightFieldName);
            //Index of the position of the column
            int indexRightEqual = lastIndexTable1 + tempIndex;            
            
            //We determine if it is a number or a String
            if ( table.getAttType(leftFieldName).equals("VARCHAR")){
//
//                String stringLeft = tuple.getTuple_values().get(indexLeftEqual).getValue().toString();
//                String stringRight = tuple.getTuple_values().get(indexRightEqual).getValue().toString();
//
//                if ( stringLeft.equals(stringRight) )
//                    result_tuple = tuple;

            }//End else if (table.getAttType(leftFieldName).equals("VARCHAR"))
            else if (table.getAttType(leftFieldName).equals("INTEGER")) {                

                int intLeft = Integer.parseInt(tuple.getTuple_values().get(indexLeftEqual).getValue().toString());
                int intRight = Integer.parseInt(tuple.getTuple_values().get(indexRightEqual).getValue().toString());

                if ( intLeft > intRight )
                    result_tuple = tuple;                    

            }//End else if (table.getAttType(leftFieldName).equals("INTEGER"))                         
            
        }//End if ( !rightFieldName.equals("") && !rightFieldAlias.equals("") )
        
        //Right part is a value
        if ( rightValue != null ){
        
            //We determine if it is a number or a String
            if ( table.getAttType(leftFieldName).equals("VARCHAR")){

//                String stringLeft = tuple.getTuple_values().get(indexLeftEqual).getValue().toString();
//                String stringRight = rightValue.toString();
//
//                if ( stringLeft.equals(stringRight) )
//                    result_tuple = tuple;

            }//End else if (table.getAttType(leftFieldName).equals("VARCHAR"))
            else if (table.getAttType(leftFieldName).equals("INTEGER")) {                

                int intLeft = Integer.parseInt(tuple.getTuple_values().get(indexLeftEqual).getValue().toString());
                int intRight = Integer.parseInt(rightValue.toString());

                if ( intLeft > intRight )
                    result_tuple = tuple;                    

            }//End else if (table.getAttType(leftFieldName).equals("INTEGER"))                       
        
        }//End if ( rightValue != null )
                
        return result_tuple;
    
    }//End private Tuple OperationsLeftSide()
    
    private Tuple OperationsLeftSideColumnLessThan(int lastIndexTable1, Table table, Tuple tuple, FilterPart leftFilterPart, FilterPart rightFilterPart){
    
        Tuple result_tuple = null;
        
        String rightFieldName = rightFilterPart.getFieldName();
        String rightFieldAlias = rightFilterPart.getFieldAlias();
        Object rightValue = rightFilterPart.getValue();           
        
        //We start with the left part
        String leftFieldName = leftFilterPart.getFieldName();
        String leftFieldAlias = leftFilterPart.getFieldAlias();
        Object leftValue = leftFilterPart.getValue();        
        
        //Left Part
        //Now we determine the indexes of the fields
        int indexLeftEqual = table.getFieldPosition(leftFieldName);

        //There are three cases
        //1.    Right part is a column
        //2.    Right part is a alias + column
        //3.    Right part is a value
                
        //Right part is a column and no alias
        if ( !rightFieldName.equals("") && rightFieldAlias.equals("") ){
                    
            //Index of the position of the column 
            int indexRightEqual = table.getFieldPosition(rightFieldName);
        
            //We determine if it is a number or a String
            if ( table.getAttType(leftFieldName).equals("VARCHAR")){

//                String stringLeft = tuple.getTuple_values().get(indexLeftEqual).getValue().toString();
//                String stringRight = tuple.getTuple_values().get(indexRightEqual).getValue().toString();
//
//                if ( stringLeft.equals(stringRight) )
//                    result_tuple = tuple;
                
            }//End else if (table.getAttType(leftFieldName).equals("VARCHAR"))
            else if (table.getAttType(leftFieldName).equals("INTEGER")) {                

                int intLeft = Integer.parseInt(tuple.getTuple_values().get(indexLeftEqual).getValue().toString());
                int intRight = Integer.parseInt(tuple.getTuple_values().get(indexRightEqual).getValue().toString());

                if ( intLeft < intRight )
                    result_tuple = tuple;                    

            }//End else if (table.getAttType(leftFieldName).equals("INTEGER"))                
                                
        }//End if ( !rightFieldName.equals("") && rightFieldAlias.equals("") )
                
        //Right part is a alias + column
        if ( !rightFieldName.equals("") && !rightFieldAlias.equals("") ){            
            
            //Now we look for the value
            Table aliasTable = MainWindow.getDefaultSchema().getTableWithAliasName(rightFieldAlias);
            //We get the position in the table
            int tempIndex = aliasTable.getFieldPosition(rightFieldName);
            //Index of the position of the column
            int indexRightEqual = lastIndexTable1 + tempIndex;            
            
            //We determine if it is a number or a String
            if ( table.getAttType(leftFieldName).equals("VARCHAR")){
//
//                String stringLeft = tuple.getTuple_values().get(indexLeftEqual).getValue().toString();
//                String stringRight = tuple.getTuple_values().get(indexRightEqual).getValue().toString();
//
//                if ( stringLeft.equals(stringRight) )
//                    result_tuple = tuple;

            }//End else if (table.getAttType(leftFieldName).equals("VARCHAR"))
            else if (table.getAttType(leftFieldName).equals("INTEGER")) {                

                int intLeft = Integer.parseInt(tuple.getTuple_values().get(indexLeftEqual).getValue().toString());
                int intRight = Integer.parseInt(tuple.getTuple_values().get(indexRightEqual).getValue().toString());

                if ( intLeft < intRight )
                    result_tuple = tuple;                    

            }//End else if (table.getAttType(leftFieldName).equals("INTEGER"))                         
            
        }//End if ( !rightFieldName.equals("") && !rightFieldAlias.equals("") )
        
        //Right part is a value
        if ( rightValue != null ){
        
            //We determine if it is a number or a String
            if ( table.getAttType(leftFieldName).equals("VARCHAR")){

//                String stringLeft = tuple.getTuple_values().get(indexLeftEqual).getValue().toString();
//                String stringRight = rightValue.toString();
//
//                if ( stringLeft.equals(stringRight) )
//                    result_tuple = tuple;

            }//End else if (table.getAttType(leftFieldName).equals("VARCHAR"))
            else if (table.getAttType(leftFieldName).equals("INTEGER")) {                

                int intLeft = Integer.parseInt(tuple.getTuple_values().get(indexLeftEqual).getValue().toString());
                int intRight = Integer.parseInt(rightValue.toString());

                if ( intLeft < intRight )
                    result_tuple = tuple;                    

            }//End else if (table.getAttType(leftFieldName).equals("INTEGER"))                       
        
        }//End if ( rightValue != null )
                
        return result_tuple;
    
    }//End private Tuple OperationsLeftSide()    

    /**
     * @return the query_filter_type
     */
    public String getQuery_filter_type() {
        return query_filter_type;
    }

    /**
     * @param query_filter_type the query_filter_type to set
     */
    public void setQuery_filter_type(String query_filter_type) {
        this.query_filter_type = query_filter_type;
    }
    
}
