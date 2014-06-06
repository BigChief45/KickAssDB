package kickassdb;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Operations
{
    
    /* This class contains the code for all the operations the DBMS does
       with the dables (SELECT, INSERT, etc.) to reduce code on the CUP file
    */
        
    public static void selectCount(ArrayList<Table> tables, ArrayList<QueryFilter> filters )
    {
        /* Call the select method to apply the filters recieved */
        int count = 1;
        Table result = new Table();
        if ( !filters.isEmpty() )
        {
            /* If the query has filters, get a filtered relation first, then count */
            result = select(tables, filters);            
            count = result.getTable_tuples().size();            
        }
        else
        {
                
            /* No filters, Calculate the count */
            for ( Table t : tables )               
                count = count * t.getTable_tuples().size();                         
        }
        
        /* Prepare output */
        Table output = new Table();
        ArrayList<Attribute> output_d = new ArrayList<Attribute>();
        Attribute output_a = new Attribute("Count", Attribute.Type.INTEGER, 0);
        output_d.add(output_a);
        Value output_v = new Value(count);
        Tuple output_t = new Tuple();
        output_t.addValue(output_v);
        output.setTable_domain(output_d);
        output.addTuple(output_t);

        MainWindow.showQueryOutput(output);
    }
    
    public static void selectCount(ArrayList<String> fields, ArrayList<Table> tables, ArrayList<QueryFilter> filters)
    {
        String field = fields.get(0);
        Table query_table = new Table();
        int count = 0;
        
        /* Check if the query has filters */
        if ( !filters.isEmpty() )        
            query_table = select(tables, filters);        
        else if ( tables.size() == 1)
        {
            /* There are no filters, and just one table */
            query_table = tables.get(0);
        }
        else if ( tables.size() == 2 )
        {
            /* There are no filters, 2 tables */
            Table t1 = tables.get(0);
            Table t2 = tables.get(1);
            
            query_table = Table.mergeTables(t1, t2);
        }

        int fieldPosition = query_table.getFieldPosition(field);
        
        /* Check if the attribute exists in the table domain */
        if ( fieldPosition == -1 )
        {
            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "Attribute does not exist in the table domain", "Error", JOptionPane.ERROR_MESSAGE);                                         
            return;
        }
        
        /* Loop through the whole table and count the non NULL values */
        for ( Tuple t : query_table.getTable_tuples() )
        {            
            if ( t.getValue(fieldPosition).getValue() != null && !(t.getValue(fieldPosition).getValue().equals("")) )
                count = count + 1;
        }
        
        /* Prepare output */
        Table output = new Table();
        ArrayList<Attribute> output_d = new ArrayList<Attribute>();
        Attribute output_a = new Attribute("Count", Attribute.Type.INTEGER, 0);
        output_d.add(output_a);
        Value output_v = new Value(count);
        Tuple output_t = new Tuple();
        output_t.addValue(output_v);
        output.setTable_domain(output_d);
        output.addTuple(output_t);

        MainWindow.showQueryOutput(output);
    }
    
    public static void selectSum(ArrayList<String> field_names, ArrayList<Table> tables, ArrayList<QueryFilter> filters)            
    {                
        /* Check how many fields where specified for the SUM */
        if ( field_names.size() > 1 )
        {
            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "SUM() operation can only contain one argument", "Error", JOptionPane.ERROR_MESSAGE);                                         
            return;
        }
                
        Table query_table = new Table();
        int sum = 0;
        
        /* Check if there are any filters */
        if ( !filters.isEmpty() )        
            query_table = select(tables, filters);        
        
        /* Check how many tables are in the query */
        else if ( tables.size() == 1 )
        {
            query_table = tables.get(0); // Only one table, no filters
        }
        else if ( tables.size() == 2 )
        {
            /* No filters, 2 tables */
            Table table1 = tables.get(0);
            Table table2 = tables.get(1);
                                                
            /* Merge both tables */
            //query_table = Table.mergeTables(table1, table2);
            query_table = Table.filterAndMerge(table1, table2, filters);
        }
        
        /* Get the field to perform the SUM, since SUM only operates with one
           field, we can extract it directly from the arraylist */
        String field = field_names.get(0);
        int field_position = query_table.getFieldPosition(field);

        /* Check if the attribute exists in the table domain */
        if ( field_position == -1 )
        {
            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "Attribute does not exist in the table domain", "Error", JOptionPane.ERROR_MESSAGE);                                         
            return;
        }

        /* Check the attribute type for this field, only INTS are accepted for this SUM function */
        Attribute this_attribute;
        this_attribute = query_table.getTable_domain().get(field_position);

        if ( this_attribute.getType() != Attribute.Type.INTEGER )
        {
            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "SUM function can only recieve an INT type field", "Error", JOptionPane.ERROR_MESSAGE);                                         
            return;
        }

        /* Loop through this column obtaining the total sum */
        for ( Tuple tuple : query_table.getTable_tuples() )                
           sum = sum + ( Integer.parseInt(tuple.getValue(field_position).getValue().toString()) );

        /* Prepare output */
        Table output = new Table();
        ArrayList<Attribute> output_d = new ArrayList<Attribute>();
        Attribute output_a = new Attribute("Sum", Attribute.Type.INTEGER, 0);
        output_d.add(output_a);
        Value output_v = new Value(sum);
        Tuple output_t = new Tuple();
        output_t.addValue(output_v);
        output.setTable_domain(output_d);
        output.addTuple(output_t);

        MainWindow.showQueryOutput(output);
               
    }   
    
    public static void select(ArrayList<Table> tables, ArrayList<QueryFilter> filters, ArrayList<String> field_names, ArrayList<String> field_aliases)
    {                        
        //Table where we will store the result of the query
        Table new_table = tables.get(0);
        Table new_table2 = new Table();
        
        //We get the number of filters
        int numberFilters = filters.size();
        
        //We get the number of tables        
        int numberTables = tables.size();
        
        QueryFilter filterR = new QueryFilter();
        QueryFilter filterL = new QueryFilter();               
        
        //We get table 1
        if ( tables.size() > 1 )
        {
            new_table2 = tables.get(1);
        }//End if ( filters.size() > 1 )        
                
        Attribute.IndexType indexType = Attribute.IndexType.NULL;
        if ( filters.size() >= 1 )
        {
            filterR = filters.get(0);
            
            if ( filters.size() > 1 )
                filterL = filters.get(1); 
            
        }//End if ( filters.size() > 1 )        
        
        //indexType = getAttributeIndexType(new_table, filterR);        
        
        Attribute attr = new Attribute();
        
//        if ( !"NULL".equals(indexType.toString()))                   
//            attr = new_table.getAttributeByName(filterR.getLeftFilter().getFieldName());
            
        int value  = 0;
        //Integer.parseInt(filterR.getRightFilter().getValue().toString());        
        
        //We initialize the arraylist and tables when we execute an index search
        ArrayList results = new ArrayList();
        Table resultTable = new Table();
        
        switch ( numberTables ) 
        {            
            case 1:
                new_table = tables.get(0); // Only one table                
                
                //We create the complete domain if it exists
//                if ( (field_aliases.size() > 0) ){
//                    if ( !((field_aliases.size() == 1) &&  ("".equals(field_aliases.get(0))) ))
//                        new_table.createCompleteDomain();                
//                }
                                    
                //We initialize the operand
                String operand;

                switch (indexType.toString()) 
                {                                        
                    case "HASH_TYPE_INDEXING":
                        operand = filterR.getOperand();
                        results = new ArrayList();
                        
                        switch ( operand )
                        {
                            case "=":
                                results.add( HashMethods.search(attr.getHashTable(), value));
                                break;
                                
                            case "<":
                                results = HashMethods.searchLess(attr.getHashTable(), value);
                                break;
                                
                            case ">":
                                results = HashMethods.searchGreater(attr.getHashTable(), value);
                                break;
                                
                            case "<>":
                                results = HashMethods.searchDifferent(attr.getHashTable(), value);
                                break;
                                
                            default:
                                break;
                        }
                        
                        resultTable = new Table();
                        resultTable.setTable_tuples(results);
                        resultTable.setTable_domain(new_table.getTable_domain());
                        resultTable.setTable_complete_domain(new_table.getTable_complete_domain());
                        
                        new_table = QueryFilter.newFilterTable(resultTable, filters);
                                                
                        break;
                        
                    case "TREE_TYPE_INDEXING":                        
                        operand = filterR.getOperand();                        
                        results = new ArrayList();
                        
                        switch (operand) 
                        {
                            case "=":
                                BPlusTree t = attr.getBPlusTree();
                                results = (ArrayList<Tuple>)attr.getBPlusTree().getEquals(value);
                                break;
                                
                            case "<":
                                results = (ArrayList<Tuple>)attr.getBPlusTree().getLess(value);
                                break;

                            case ">":
                                results = (ArrayList<Tuple>)attr.getBPlusTree().getGreater(value);
                                break;

                            case "<>":
                                results = (ArrayList<Tuple>)attr.getBPlusTree().getDifferent(value);
                                break;
                                
                            default:
                                break;
                        }//End switch (operand)
                        
                        resultTable = new Table();
                        resultTable.setTable_tuples(results);
                        resultTable.setTable_domain(new_table.getTable_domain());
                        resultTable.setTable_complete_domain(new_table.getTable_complete_domain());
                        
                        new_table = QueryFilter.newFilterTable(resultTable, filters);
                        
                        break;
                        
                    default:
                        new_table = QueryFilter.newFilterTable(new_table, filters);
                        break;
                        
                }//End switch (indexType.toString())
                break;
                
            case 2:                
                //We get the first and the second table
                Table table1, table2;
                table1 = tables.get(0);
                table2 = tables.get(1);

                /* Check if there are ambiguous fields */
                int i = 0;
                for ( String s : field_names )
                {
                    if ( (field_aliases.get(i).equals("")) && Validations.isFieldAmbiguous(s, table1, table2) == true)
                    {
                        JOptionPane.showMessageDialog(KickAssDB.mainwindow, "One or more fields are ambiguous. Please use table aliases to specify the field.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    i++;
                }
                i = 0;
                
                //Now we obtain the bool_type
                String boolType = filterL.getBoolValue();
                
                switch (indexType.toString()) 
                {                                        
                    case "HASH_TYPE_INDEXING":
                        operand = filterL.getOperand();
                        results = new ArrayList();
                        
                        switch ( operand )
                        {
                            case "=":
                                results.add( HashMethods.search(attr.getHashTable(), value));
                                break;
                                
                            case "<":
                                results = HashMethods.searchLess(attr.getHashTable(), value);
                                break;
                                
                            case ">":
                                results = HashMethods.searchGreater(attr.getHashTable(), value);
                                break;
                                
                            case "<>":
                                results = HashMethods.searchDifferent(attr.getHashTable(), value);
                                break;
                                
                            default:
                                break;
                        }
                        
                        resultTable = new Table();
                        resultTable.setTable_tuples(results);
                        resultTable.setTable_domain(new_table.getTable_domain());
                        resultTable.setTable_complete_domain(new_table.getTable_complete_domain());
                        
                        new_table = QueryFilter.newFilterTable(resultTable, filters);
                                                
                        break;
                        
                    case "TREE_TYPE_INDEXING":                        
                        operand = filterL.getOperand();                        
                        results = new ArrayList();
                        
                        switch (operand) 
                        {
                            case "=":
                                results.add((Tuple)attr.getBPlusTree().search(value));
                                break;
                                
                            case "<":
                                results = (ArrayList<Tuple>)attr.getBPlusTree().getLess(value);
                                break;

                            case ">":
                                results = (ArrayList<Tuple>)attr.getBPlusTree().getGreater(value);
                                break;

                            case "<>":
                                results = (ArrayList<Tuple>)attr.getBPlusTree().getDifferent(value);
                                break;
                                
                            default:
                                break;
                        }//End switch (operand)
                        
                        resultTable = new Table();
                        resultTable.setTable_tuples(results);
                        resultTable.setTable_domain(new_table.getTable_domain());
                        resultTable.setTable_complete_domain(new_table.getTable_complete_domain());
                        
                        //new_table = QueryFilter.newFilterTable(resultTable, filters);
                        
                        break;
                        
                    default:
                        new_table = Table.mergeTables(table1, table2); // More than 1 table

                        // If we have filters we apply them
                        //if ( numberFilters > 0 )                            
                        new_table = QueryFilter.newFilterTable(new_table, filters);                         
                        break;
                        
                }//End switch (indexType.toString())
               
//                if ( !"NULL".equals(indexType.toString())){                                
//                                                            
//                    switch (boolType) {
//                        case "AND":
//                            //We merge two tables into 1
//                            new_table = Table.mergeTables(resultTable, table2); // More than 1 table
//
//                            // If we have filters we apply them
//                            //if ( numberFilters > 0 )                            
//                            new_table = QueryFilter.newFilterTable(new_table, filters);                            
//                            break;
//
//                        case "OR":                            
//                            break;
//                                                        
//                        default:
//                            break;
//                            
//                    }//End switch (boolType)
//                
//                }//End if ( !"NULL".equals(indexType.toString()))                    
//                else {
//
//                    //We merge two tables into 1
//                    new_table = Table.mergeTables(table1, table2); // More than 1 table
//
//                    // If we have filters we apply them
//                    //if ( numberFilters > 0 )
//                    new_table = QueryFilter.newFilterTable(new_table, filters);
//                                    
//                }//End else                                
                break;                
                
            default:
                break;
                
        }//End switch (numberFilters)
                                      
        //We get the number of filters
        int numberFieldNames = field_names.size();        
                               
        //If there are field names we execute this code
        if ( numberFieldNames > 0 ){
        
            ArrayList<Integer> visiblesColumnsIndexes;
            visiblesColumnsIndexes = new ArrayList();
                        
            ArrayList<String> columnNames;
            columnNames = new ArrayList();            
            
            int counter = 0;
            for (String alias : field_aliases) {
                
                if (alias.equals("")){
                   
                    int num = new_table.getFieldPosition(field_names.get(counter));
                    
                    if (num > -1)
                        visiblesColumnsIndexes.add(num);
                
                }
                //We find the name of the table
                else{
                
                    //If the alias is the table's name
                    Table tableWithAliasEqualsName = MainWindow.getDefaultSchema().getTable(alias);
                    
                    if ( tableWithAliasEqualsName != null ){

                        int num = new_table.getFieldPositionCompleteDomain(tableWithAliasEqualsName.getTable_name()+field_names.get(counter));
                        
                        if (num > -1)
                            visiblesColumnsIndexes.add(num);

                    }//End if ( tableWithAliasEqualsName != null )
                    else {

                        //First we find the table who has that alias                           
                        Table tableWithAliasName = MainWindow.getDefaultSchema().getTableWithAliasName(alias);

                        //If we find it we define the indexAliasAttributeRight
                        if (tableWithAliasName != null){

                            int num = new_table.getFieldPositionCompleteDomain(tableWithAliasName.getTable_name()+field_names.get(counter));

                            if (num > -1)
                                visiblesColumnsIndexes.add(num);

                        }//End if (tableWithAliasName != null)                    
                
                    }//End else
                
                }//End else
                
                counter++;
                
            }//End else
            
            Table tempTable = new Table();
            tempTable.setTable_domain(new ArrayList());
            
            for (Tuple tuple : new_table.getTable_tuples()) {
                
                // We create a tuple where we will add the values
                Tuple temp_Tuple =  new Tuple();
                for (Integer index : visiblesColumnsIndexes) 
                {                   
                    // We cast the index to integer
                    int currentIndex = index;
                    
                    temp_Tuple.addValue(tuple.getValue(currentIndex));                    
                    
                }//End for (Tuple t : mergedTable.getTable_tuples())
                tempTable.addTuple(temp_Tuple);                
                
            }//End for (Tuple tuple : new_table.getTable_tuples())                                    
            
            //And we set the domain
            int counter2 = 0;
            for (Attribute tempAttr : new_table.getTable_domain()) {
                
                if ( visiblesColumnsIndexes.contains(counter2) )
                    tempTable.getTable_domain().add(tempAttr);
                
                counter2++;
                
            }
            
            new_table = tempTable;
            
        }//End if ( numberFieldNames > 0 )                                 
        
        /* Display output */
        MainWindow.showQueryOutput(new_table);
        
    }//End public static void selectAll(ArrayList<Table> tables, ArrayList<QueryFilter> filters)

    public static Table select(ArrayList<Table> tables, ArrayList<QueryFilter> filters)
    {        
        //Table where we will store the result of the query
        Table new_table = new Table();
        
        //We get the number of tables        
        int numberTables = tables.size();
        
        switch ( numberTables ) 
        {            
            case 1:
                new_table = tables.get(0); // Only one table

                //If we have filters we apply them                
                new_table = QueryFilter.newFilterTable(new_table, filters);             
                break;
                
            case 2:
                //We get the first and the second table
                Table table1, table2;
                table1 = tables.get(0);
                table2 = tables.get(1);

                new_table = Table.filterAndMerge(table1, table2, filters);
                
                //pendejada(tables, filters);
                
                //pendejada2(tables, filters);
                //System.exit(0);
                
                //We merge two tables into 1
                //new_table = Table.mergeTables(table1, table2); // More than 1 table

                // If we have filters we apply them
                //new_table = QueryFilter.newFilterTable(new_table, filters);                
                break;                
                
            default:
                break;
                
        }//End switch (numberFilters)
                                                              
        return new_table;
        
    }//End public static void selectAll(ArrayList<Table> tables, ArrayList<QueryFilter> filters)    
    
    public static Attribute.IndexType getAttributeIndexType(Table table, QueryFilter filter){    
        
        String tableName = table.getTable_name();
                
        String filterTypeL = filter.leftFilter.getFilterType_type().toString();
        
        String filterTypeR = filter.rightFilter.getFilterType_type().toString();
        
        Attribute attr = new Attribute();
        if ( "ALIAS_ATTRIBUTE".equals(filterTypeL) || "ATTRIBUTE".equals(filterTypeL) )
            attr = table.getAttributeByName(filter.leftFilter.getFieldName());
            
        if ( "ALIAS_ATTRIBUTE".equals(filterTypeR) || "ATTRIBUTE".equals(filterTypeR) )
            attr = table.getAttributeByName(filter.rightFilter.getFieldName());
        
        Attribute.IndexType indexType = attr.getIndexType();                
        
        return indexType;
        
    }//End private void hasAttributeAnIndex()

    public static Attribute.IndexType getAttributeIndexType(Table table, FilterPart filter){    
        
        String filterType = filter.getFilterType_type().toString();
                
        Attribute attr = new Attribute();
        if ( "ALIAS_ATTRIBUTE".equals(filterType) || "ATTRIBUTE".equals(filterType) )
            attr = table.getAttributeByName(filter.getFieldName());            
        
        Attribute.IndexType indexType = attr.getIndexType();                
        
        return indexType;
        
    }//End private void hasAttributeAnIndex()   
    
    private static void pendejada(ArrayList<Table> tables, ArrayList<QueryFilter> filters)
    {
        Table t1 = tables.get(0);
        Table t2 = tables.get(1);
        
        Table r1 = new Table();
        r1.setTable_domain(t1.getTable_domain());
        Table r2 = new Table();
        r2.setTable_domain(t2.getTable_domain());
        
        Table r = new Table();
        
        /* Loop through table 1 */
        for ( Tuple tp : t1.getTable_tuples() )
        {
            Value v1 = tp.getValue(0);
            for ( Tuple tp2 : t2.getTable_tuples() )
            {                
                Value v2 = tp2.getValue(1);
                
                if ( Integer.parseInt(v1.getValue().toString()) == Integer.parseInt(v2.getValue().toString()) )
                {
                    r1.addTuple(tp);
                    break;
                }
            }
        }
        
        for ( Tuple tp : t1.getTable_tuples() )
        {
            if ( Integer.parseInt(tp.getValue(4).getValue().toString()) > 50000 )
                r2.addTuple(tp);
        }
        
        /* Merge Tuples */
        r = Table.mergeTables(r1, r2);
        
        r.printTuples();
    }
    
    private static void pendejada2(ArrayList<Table> tables, ArrayList<QueryFilter> filters){
        
        Table crossproduct = new Table();
        Table crossproduct2 = new Table();
        
        Table table1 = tables.get(0);
        Table table2 = tables.get(1);
        
        //First we set the table domain
        ArrayList<Attribute> table1_domain = table1.getTable_domain();
        ArrayList<Attribute> table2_domain = table2.getTable_domain();
        ArrayList<Attribute> domain = new ArrayList();
        ArrayList<Attribute> completeDomain = new ArrayList();        
        
        for (Attribute attr : table1_domain) {
            domain.add(attr);
            
            //Now we will add the name before the attribute for table1
            Attribute new_attribute = new Attribute();
            
            new_attribute.setAttributeSize(attr.getAttributeSize());
            new_attribute.setAttribute_name(attr.getAttribute_name());
            new_attribute.setType(attr.getType());            
            
            String attrName = new_attribute.getAttribute_name();
            new_attribute.setAttribute_name(table1.getTable_name()+attrName);
            
            //We add the attribute to the complete domain
            completeDomain.add(new_attribute);
            
        }//End for (Attribute attr : table1_domain)
        
        for (Attribute attr : table2_domain) {
            domain.add(attr);
            
            //Now we will add the name before the attribute for table1
            Attribute new_attribute = new Attribute();
            
            new_attribute.setAttributeSize(attr.getAttributeSize());
            new_attribute.setAttribute_name(attr.getAttribute_name());
            new_attribute.setType(attr.getType());  
            
            String attrName = new_attribute.getAttribute_name();
            new_attribute.setAttribute_name(table2.getTable_name()+attrName);
            
            //We add the attribute to the complete domain
            completeDomain.add(new_attribute);            
            
        }//End for (Attribute attr : table2_domain)
                        
        crossproduct.setTable_domain(domain);
        crossproduct.setTable_complete_domain(completeDomain);
        
        for ( Tuple tuple : table1.getTable_tuples() )
        {
            Value v1 = tuple.getValue(0);
            for ( Tuple tuple2 : table2.getTable_tuples() )
            {
                
                Value v2 = tuple2.getValue(1);
                
                if ( Integer.parseInt(v1.getValue().toString()) == Integer.parseInt(v2.getValue().toString()) )
                {
                    Tuple new_tuple = new Tuple();

                    for (Value v : tuple.getTuple_values()) {
                        new_tuple.addValue(v);
                    }//End for (Value v : tuple.getTuple_values())

                    for (Value v : tuple2.getTuple_values()) {
                        new_tuple.addValue(v);
                    }//End for (Value v : tuple2.getTuple_values())

                    //We add the tuple to the table crossproduct
                    crossproduct.addTuple(new_tuple); 
                }                                                   
            }//End for ( Tuple tuple2 : table2.getTable_tuples() )            
        }//End for ( Tuple tuple : table1.getTable_tuples() )        
        
        for ( Tuple tp : crossproduct.getTable_tuples() )
        {                        
            if ( Integer.parseInt(tp.getValue(4).getValue().toString()) > 15 )
                crossproduct2.addTuple(tp);
        }
                
        //crossproduct2.printTuples();
        
    }
    
}//End public class Operations

