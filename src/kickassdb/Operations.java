package kickassdb;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Operations
{
    
    /* This class contains the code for all the operations the DBMS does
       with the dables (SELECT, INSERT, etc.) to reduce code on the CUP file
    */
        
    public static void selectCount(ArrayList<Table> tables )
    {
        int count = 1;

        /* Calculate the count */
        for ( Table t : tables )
        {            
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
    
    public static void selectCount(ArrayList<String> fields, ArrayList<Table> tables)
    {
        String field = fields.get(0);
        int count = 0;
        
        Table query_table = new Table();
        
        /* Calculate the count */
        if ( tables.size() == 1)
        {
            query_table = tables.get(0);
        }
        else if ( tables.size() == 2 )
        {
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
    
    public static void selectSum(ArrayList<String> field_names, ArrayList<Table> tables)            
    {                
        /* Check how many fields where specified for the SUM */
        if ( field_names.size() > 1 )
        {
            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "SUM() operation can only contain one argument", "Error", JOptionPane.ERROR_MESSAGE);                                         
            return;
        }
                
        Table query_table = new Table();
        int sum = 0;
        
        /* Check how many tables are in the query */
        if ( tables.size() == 1 )
        {
            query_table = tables.get(0); // Only one table
        }
        else if ( tables.size() == 2 )
        {
            Table table1 = tables.get(0);
            Table table2 = tables.get(1);
                                                
            /* Merge both tables */
            query_table = Table.mergeTables(table1, table2);
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
    
//    public static void selectFields(ArrayList<String> field_names, ArrayList<Table> tables, ArrayList<QueryFilter> filters, ArrayList<String> aliases)
//    {        
//        /* First check if the fields exist on the tables */
//        int i = 0; // Table counter iterator
//        for ( String f : field_names )
//        {            
//            i = 0;
//            for ( Table t : tables )
//            {
//                if ( Validations.isFieldInDomain(f, t) == true )                                    
//                    break;
//                else
//                    i++;
//               
//                if ( i == tables.size() )
//                {
//                    /* Looped through all tables without finding the field */
//                    JOptionPane.showMessageDialog(KickAssDB.mainwindow, "One of the selected fields does not exist in any of the table's domain.", "Error", JOptionPane.ERROR_MESSAGE);                                         
//                    return;
//                }//End if ( i == tables.size() )
//            }//End for ( Table t : tables )
//        }//End for ( String f : field_names )
//
//        // Result table
//        Table new_table = new Table();
//        
//        /* Check how many tables are in the query */
//        if ( tables.size() == 1 )
//        {                       
//            Table table1 = tables.get(0);
//            
//            /* Apply filters before selecting, if there are any */
//            if ( filters.size() > 0 ){
//                //table1 = QueryFilter.filterTable(0, table1, filters);
//                table1 = QueryFilter.newFilterTable(table1, filters);
//            }
//            /* Get the domain for the table */
//            ArrayList<Attribute> d = new ArrayList<>();
//        
//            for ( Attribute a : table1.getTable_domain() )        
//                for ( String s : field_names )                
//                    if ( a.getAttribute_name().equals(s) )
//                        d.add(a);
//                
//            new_table.setTable_domain(d);
//            
//            /* We'll get the indexes of the requested attributes */
//            ArrayList indexes = new ArrayList();
//            for (Attribute attr : table1.getTable_domain())
//            {                
//                // If the find attribute is contained inside field_names then we add the index to indexes
//                if ( field_names.contains(attr.getAttribute_name()) )
//                    indexes.add(table1.getTable_domain().lastIndexOf(attr));
//            }//End for (Attribute attr : table1.getTable_domain())
//            
//            // Now we add the tuples containing the selected attributes to new_table
//            for (Tuple t : table1.getTable_tuples())
//            {                
//                // We create a tuple where we will add the values
//                Tuple temp_Tuple =  new Tuple();
//                for (Object index : indexes)
//                {                   
//                    // We cast the index to integer
//                    int currentIndex = (int)index;
//                    
//                    temp_Tuple.addValue(t.getValue(currentIndex));                    
//                }//End for (Object index : indexes)
//                new_table.addTuple(temp_Tuple);                
//            }//End for (Tuple t : table1.getTable_tuples())
//        }//End if ( tables.size() == 1 )
//        else if ( tables.size() == 2)
//        {
//            Table t1 = tables.get(0);
//            Table t2 = tables.get(1);
//            
//            //We will retrieve the attributes from each table according to their aliases
//            ArrayList<String> attributesTable1 = new ArrayList();
//            ArrayList<String> attributesTable2 = new ArrayList();            
//            
//            //We have a temporal counter of the field names
//            int tempFieldNameCounter = 0;
//            
//            //We iterate the alias list and put the required attributes in the table that corresponds
//            for (String alias : aliases) {                
//                
//                //Table 1
//                //First we assk if the alias matches the tables alias name
//                //We ask if the alias matches the name of the table
//                //We ask if the value exists in t1
//                if ( (!("".equals(alias)) && alias.equals(t1.getTable_alias().getName())) ||
//                     (alias.equals(t1.getTable_name()) ) ||  
//                     ("".equals(alias) && t1.attributeExistsInDomain(field_names.get(tempFieldNameCounter)))){
//                    
//                    attributesTable1.add(field_names.get(tempFieldNameCounter));
//                    tempFieldNameCounter++;
//                    
//                }//End if ( (alias.equals(t1.getTable_alias().getName())) || (alias.equals(t1.getTable_name()) ) ||  ("".equals(alias) && t1.attributeExistsInDomain(field_names.get(tempFieldNameCounter))))
//                //Table2
//                //First we assk if the alias matches the tables alias name
//                //We ask if the alias matches the name of the table
//                //We ask if the value exists in t2                
//                else if ( (!("".equals(alias)) && alias.equals(t2.getTable_alias().getName())) ||
//                     (alias.equals(t2.getTable_name()) ) ||  
//                     ("".equals(alias) && t2.attributeExistsInDomain(field_names.get(tempFieldNameCounter)))){
//                
//                    attributesTable2.add(field_names.get(tempFieldNameCounter));
//                    tempFieldNameCounter++;                    
//                    
//                }//End else if ( (alias.equals(t2.getTable_alias().getName())) || (alias.equals(t2.getTable_name()) ) || ("".equals(alias) && t2.attributeExistsInDomain(field_names.get(tempFieldNameCounter))))
//                                
//            }//End for (String string : attributesTable2)
//            
//            /* Check if fields are ambiguous */
//            i = 0;
//            
//            for ( String f : field_names )
//            {
//                if ( (Validations.isFieldAmbiguous(f, t1, t2) == true) && (aliases.get(i).equals("")) )
//                {
//                    /* There is ambiguity */
//                    JOptionPane.showMessageDialog(KickAssDB.mainwindow, "There is ambiguity with one or more of the selected fields. Please use table aliases to indicate which fields from which tables to select.", "Error", JOptionPane.ERROR_MESSAGE);                                         
//                    return;
//                }//End if ( (Validations.isFieldAmbiguous(f, t1, t2) == true) && (aliases.get(i).equals("")) )
//                i++;
//            }//End for ( String f : field_names )
//            
//            //We merge the table
//            Table mergedTable = Table.mergeTables(t1, t2);                      
//            
//            /* Get the domain for the table */
//            ArrayList<Attribute> new_tableDomain = new ArrayList<>();
//            
//            for ( String s : field_names )
//            {
//                Attribute a = new Attribute();
//                a.setAttribute_name(s);
//                new_tableDomain.add(a);
//                
//            }//End for ( String s : field_names )
//            
//            //We set the domain of the new table that will the addition of both tables' domains
//            new_table.setTable_domain(new_tableDomain);
//            int firstTableDomainCount = t1.getTable_domain().size();
//            
//            //Now we perform the query filter if there is any filter
//            if ( filters.size() > 0  ){
//            
//                //new_table = QueryFilter.filterTable(firstTableDomainCount, new_table, filters);
//                new_table = QueryFilter.newFilterTable(new_table, filters);
//                
//            }//End if ( filters.size() > 0  )
//            
//            /* We'll get the indexes of the requested attributes */
//            ArrayList indexes = new ArrayList();            
//
//            //We iterate the attributes of t1's domain
//            for (Attribute attr : t1.getTable_domain()) 
//            {                
//
//                if ( attributesTable1.contains(attr.getAttribute_name()) )
//                    indexes.add(t1.getTable_domain().lastIndexOf(attr));                                
//                
//            }//End for (Attribute attr : t1.getTable_domain())
//            
//            //We get the last index of t1's domain
//            int lastIndex = t1.getTable_domain().size();
//            
//            //We iterate the attributes of t2's domain
//            for (Attribute attr : t2.getTable_domain())
//            {
//                
//                if ( attributesTable2.contains(attr.getAttribute_name()) )
//                    indexes.add(lastIndex + t2.getTable_domain().lastIndexOf(attr));                
//
//            }//End for (Attribute attr : t2.getTable_domain())            
//            
//            // Now we add the tuples containing the selected attributes to new_table
//            for (Tuple t : mergedTable.getTable_tuples())
//            {                
//                // We create a tuple where we will add the values
//                Tuple temp_Tuple =  new Tuple();
//                for (Object index : indexes) 
//                {                   
//                    // We cast the index to integer
//                    int currentIndex = (int)index;
//                    
//                    temp_Tuple.addValue(t.getValue(currentIndex));                    
//                }//End for (Tuple t : mergedTable.getTable_tuples())
//                new_table.addTuple(temp_Tuple);
//            }//End for (Tuple t : mergedTable.getTable_tuples())
//                       
//        }
//        else
//        {
//            /* More than 2 tables, displasy error and exit */
//            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "FROM Clause cannot contain more than two tables.", "Error", JOptionPane.ERROR_MESSAGE);                                         
//            return;
//        }
//                              
//        /* Output the query */
//        new_table.printTuples();
//        MainWindow.showQueryOutput(new_table);
//    }
    
    public static void select(ArrayList<Table> tables, ArrayList<QueryFilter> filters, ArrayList<String> field_names, ArrayList<String> field_aliases)
    {
        
        //Table where we will store the result of the query
        Table new_table = new Table();
        
        //We get the number of filters
        int numberFilters = filters.size();
        //We get the number of tables        
        int numberTables = tables.size();
        
        switch (numberTables) {
            
            case 1:
                new_table = tables.get(0); // Only one table

                //If we have filters we apply them
                //if ( numberFilters > 0 )
                new_table = QueryFilter.newFilterTable(new_table, filters);                
                break;
                
            case 2:
                //We get the first and the second table
                Table table1, table2;
                table1 = tables.get(0);
                table2 = tables.get(1);

                //We merge two tables into 1
                new_table = Table.mergeTables(table1, table2); // More than 1 table

                // If we have filters we apply them
                //if ( numberFilters > 0 )
                new_table = QueryFilter.newFilterTable(new_table, filters);                
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
                        
            for (String alias : field_aliases) {
                
                if ( visiblesColumnsIndexes.size() == field_names.size() ){
                    System.out.println("");
                    break;
                }
                
                //Now we define the field names we want to see                
                Table tableWithAliasEqualsName = MainWindow.getDefaultSchema().getTable(alias);               
                
                for (String name : field_names) {
                    
                    if ( alias.equals("") ){

                        int idx = new_table.getFieldPosition(name);

                        visiblesColumnsIndexes.add(idx);                        

                    }else {

                        int attributeIndex = -1;                    

                        //If there's a table that its alias is its name then we look for the index alias + attribute in the merged table
                        if ( tableWithAliasEqualsName != null ){

                            attributeIndex = new_table.getFieldPositionCompleteDomain(tableWithAliasEqualsName.getTable_name() + name);

                        }//End if ( tableWithAliasEqualsName != null )
                        else {

                            //First we find the table who has that alias
                            Table tableWithAliasName = MainWindow.getDefaultSchema().getTableWithAliasName(alias);

                            //If we find it we define the indexAliasAttributeRight
                            if (tableWithAliasName != null){

                                attributeIndex = new_table.getFieldPositionCompleteDomain(tableWithAliasName.getTable_name() + name);

                            }//End if (tableWithAliasName != null)

                        }//End else

                        if (!(attributeIndex == -1))
                            visiblesColumnsIndexes.add(attributeIndex);                        

                    }//End else                                  
                                       
                }//End for (String name : field_names)                                                                
                
            }//End for (String string : field_aliases)                
                     
            Table tempTable = new Table();
            tempTable.setTable_domain(new ArrayList());
//            tempTable.setTable_domain(new_table.getTable_domain());
//            tempTable.setTable_complete_domain(new_table.getTable_complete_domain());
            
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
            int counter = 0;
            for (Attribute attr : new_table.getTable_domain()) {
                
                if ( visiblesColumnsIndexes.contains(counter) )
                    tempTable.getTable_domain().add(attr);
                
                counter++;
                
            }
            
            new_table = tempTable;
            
        }//End if ( numberFieldNames > 0 )                                 
        
        /* Display output */
        MainWindow.showQueryOutput(new_table);
        
    }//End public static void selectAll(ArrayList<Table> tables, ArrayList<QueryFilter> filters)
    
}//End public class Operations

