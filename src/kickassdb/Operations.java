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
    
    public static void selectSum(ArrayList<String> field_names, ArrayList<Table> tables)            
    {
        int sum = 0;
                
        /* Check how many fields where specified for the SUM */
        if ( field_names.size() > 1 )
        {
            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "SUM() operation can only contain one argument", "Error", JOptionPane.ERROR_MESSAGE);                                         
            return;
        }

        /* Get the field to perform the SUM, since SUM only operates with one
           field, we can extract it directly from the arraylist */
        String field = field_names.get(0);
        int field_position = tables.get(0).getFieldPosition(field);

        /* Check if the attribute exists in the table domain */
        if ( field_position == -1 )
        {
            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "Attribute does not exist in the table domain", "Error", JOptionPane.ERROR_MESSAGE);                                         
            return;
        }

        /* Loop through this column obtaining the total sum */
        for ( Tuple tuple : tables.get(0).getTable_tuples() )                
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
    
    public static void selectFields(ArrayList<String> field_names, ArrayList<Table> tables)
    {
        // Result table
        Table new_table = new Table();
        
        /* Check how many tables are in the query */
        if ( tables.size() == 1 )
        {                       
            Table table1 = tables.get(0);
                        
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
            
            Table table1 = Table.mergeTables(t1, t2);
                        
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
            
            for (Attribute attr : t1.getTable_domain()) 
            {                
                // If the find attribute is contained inside field_names then we add the index to indexes
                if ( field_names.contains(attr.getAttribute_name()) )                
                    indexes.add(t1.getTable_domain().lastIndexOf(attr));                    
                
            }
            
            int lastIndex = t1.getTable_domain().size();
            
            for (Attribute attr : t2.getTable_domain()) 
            {                
                // If the find attribute is contained inside field_names then we add the index to indexes
                if ( field_names.contains(attr.getAttribute_name()) )
                    indexes.add(lastIndex + t2.getTable_domain().lastIndexOf(attr));
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
}
