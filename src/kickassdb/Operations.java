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
        int count = 1;
        
        /* Calculate the count */
        

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
    
    public static void selectFields(ArrayList<String> field_names, ArrayList<Table> tables, ArrayList<QueryFilter> filters, ArrayList<String> aliases)
    {        
        /* First check if the fields exist on the tables */
        int i = 0; // Table counter iterator
        for ( String f : field_names )
        {            
            i = 0;
            for ( Table t : tables )
            {
                if ( Validations.isFieldInDomain(f, t) == true )                                    
                    break;
                else
                    i++;
               
                if ( i == tables.size() )
                {
                    /* Looped through all tables without finding the field */
                    JOptionPane.showMessageDialog(KickAssDB.mainwindow, "One of the selected fields does not exist in any of the table's domain.", "Error", JOptionPane.ERROR_MESSAGE);                                         
                    return;
                }                
            }
        }

        // Result table
        Table new_table = new Table();
        
        /* Check how many tables are in the query */
        if ( tables.size() == 1 )
        {                       
            Table table1 = tables.get(0);
            
            /* Apply filters before selecting, if there are any */
            if ( filters.size() > 0 )
                table1 = QueryFilter.filterTable(table1, filters);
                                    
            /* Get the domain for the table */
            ArrayList<Attribute> d = new ArrayList<Attribute>();
        
            for ( Attribute a : table1.getTable_domain() )        
                for ( String s : field_names )                
                    if ( a.getAttribute_name().equals(s) )
                        d.add(a);
                
            new_table.setTable_domain(d);
            
            /* We'll get the indexes of the requested attributes */
            ArrayList indexes = new ArrayList();
            
            for (Attribute attr : table1.getTable_domain()) 
            {                
                // If the find attribute is contained inside field_names then we add the index to indexes
                if ( field_names.contains(attr.getAttribute_name()) )
                    indexes.add(table1.getTable_domain().lastIndexOf(attr));
            }
            
            // Now we add the tuples containing the selected attributes to new_table
            for (Tuple t : table1.getTable_tuples()) 
            {                
                // We create a tuple where we will add the values
                Tuple temp_Tuple =  new Tuple();
                for (Object index : indexes) 
                {                   
                    // We cast the index to integer
                    int currentIndex = (int)index;
                    
                    temp_Tuple.addValue(t.getValue(currentIndex));                    
                }                
                new_table.addTuple(temp_Tuple);                
            }                        
        }
        else if ( tables.size() == 2)
        {
            Table t1 = tables.get(0);
            Table t2 = tables.get(1);
            
            /* Check if fields are ambiguous */
            i = 0;
            for ( String f : field_names )
            {
                if ( (Validations.isFieldAmbiguous(f, t1, t2) == true) && (aliases.get(i).equals("")) )
                {
                    /* There is ambiguity */
                    JOptionPane.showMessageDialog(KickAssDB.mainwindow, "There is ambiguity with one or more of the selected fields. Please use table aliases to indicate which fields from which tables to select.", "Error", JOptionPane.ERROR_MESSAGE);                                         
                    return;
                }
                i++;
            }
            
            Table mergedTable = Table.mergeTables(t1, t2);                      
            
            /* Get the domain for the table */
            ArrayList<Attribute> d = new ArrayList<Attribute>();
            
            for ( String s : field_names )
            {
                Attribute a = new Attribute();
                a.setAttribute_name(s);
                d.add(a);
            }
            
            new_table.setTable_domain(d);
            
            /* We'll get the indexes of the requested attributes */
            ArrayList indexes = new ArrayList();
            
            //To match the aliases with the correct table we add a counter for it
            int counterAliases = 0;
            int counterFieldNames = 0; 
            
            for (Attribute attr : t1.getTable_domain()) 
            {                
                // If the find attribute is contained inside field_names then we add the index to indexes
                if ( counterAliases < aliases.size() ){
                
                    if ( (field_names.contains(attr.getAttribute_name()) && aliases.get(counterAliases).equals(t1.getTable_alias().getName()))){
                        
                        indexes.add(t1.getTable_domain().lastIndexOf(attr));
                        field_names.remove(counterFieldNames);
                        counterFieldNames++;
                        counterAliases++;             
                    }
                    else if (field_names.contains(attr.getAttribute_name()) && "".equals(aliases.get(counterAliases)))
                    {            
                        indexes.add(t1.getTable_domain().lastIndexOf(attr));
                        field_names.remove(counterFieldNames);
                        counterFieldNames++;
                        counterAliases++;  
                    }                    
                
                } 
//                else if (field_names.contains(attr.getAttribute_name()))
//                {
//                    indexes.add(t1.getTable_domain().lastIndexOf(attr));
//                }//End else
                
            }//End for (Attribute attr : t1.getTable_domain())
            
            int lastIndex = t1.getTable_domain().size();
            
            for (Attribute attr : t2.getTable_domain())
            {                
                if ( counterAliases < aliases.size() ){

                    // If the find attribute is contained inside field_names then we add the index to indexes
                    if ( (field_names.contains(attr.getAttribute_name()) && aliases.get(counterAliases).equals(t2.getTable_alias().getName()))){
                        indexes.add(lastIndex + t2.getTable_domain().lastIndexOf(attr));
                        field_names.remove(counterFieldNames);
                        counterFieldNames++;
                        counterAliases++;                
                    }
                    else if (field_names.contains(attr.getAttribute_name()) && "".equals(aliases.get(counterAliases)))
                    {            
                        indexes.add(lastIndex + t2.getTable_domain().lastIndexOf(attr));
                        field_names.remove(counterFieldNames);
                        counterFieldNames++;
                        counterAliases++;  
                    }

                } 
//                else if (field_names.contains(attr.getAttribute_name()))
//                {
//                    indexes.add(t2.getTable_domain().lastIndexOf(attr));
//                }//End else

            }//End for (Attribute attr : t2.getTable_domain())            
            
            // Now we add the tuples containing the selected attributes to new_table
            for (Tuple t : mergedTable.getTable_tuples()) 
            {                
                // We create a tuple where we will add the values
                Tuple temp_Tuple =  new Tuple();
                for (Object index : indexes) 
                {                   
                    // We cast the index to integer
                    int currentIndex = (int)index;
                    
                    temp_Tuple.addValue(t.getValue(currentIndex));                    
                }                
                new_table.addTuple(temp_Tuple);
            }            
                       
        }
        else
        {
            /* More than 2 tables, displasy error and exit */
            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "FROM Clause cannot contain more than two tables.", "Error", JOptionPane.ERROR_MESSAGE);                                         
            return;
        }
                              
        /* Output the query */
        new_table.printTuples();
        MainWindow.showQueryOutput(new_table);
    }
    
    public static void selectAll(ArrayList<Table> tables, ArrayList<QueryFilter> filters)
    {
        Table new_table = new Table();
        
        if ( tables.size() > 1 )
        {
            Table table1, table2;
            table1 = tables.get(0);
            table2 = tables.get(1);
            
            new_table = Table.mergeTables(table1, table2); // More than 1 table
            
            //If we have filters we apply them
            if ( filters.size() > 0 )
                new_table = QueryFilter.filterTable(new_table, filters);
                        
        }
        else
        {            
            new_table = tables.get(0); // Only one table
                        
            //If we have filters we apply them
            if ( filters.size() > 0 )
                new_table = QueryFilter.filterTable(new_table, filters);
            
        }
        
        /* Display output */
        MainWindow.showQueryOutput(new_table);
        
    }
}

