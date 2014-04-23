package kickassdb;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Operations 
{
    /* This class contains the code for all the operations the DBMS does
       with the dables (SELECT, INSERT, etc.) to reduce code on the CUP file
    */
    
    protected static void operationSelect(int operationType, ArrayList<String> field_names, ArrayList<Table> tables, ArrayList<QueryFilter> filters)
    {
        /* Check if this query has WHERE filters */
        boolean has_filters = false;
        
        if ( filters.size() > 0 )
            has_filters = true;
        
        /* Determine what kind of SELECT operation is to be performed */
        switch ( operationType )
        {
            /* SELECT COUNT(*) */
            case 2:
                int count = 1;

                /* Calculate the count */
                for ( Table t : tables )
                {
                    if ( has_filters == false )
                        count = count * t.getTable_tuples().size(); 
                    else
                    {
                        /* We need to count only the tuples that match the filter */
                        
                    }
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

                break;
            
            /* SELECT SUM() */
            case 3:
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
                output = new Table();
                output_d = new ArrayList<Attribute>();
                output_a = new Attribute("Sum", Attribute.Type.INTEGER, 0);
                output_d.add(output_a);
                output_v = new Value(sum);
                output_t = new Tuple();
                output_t.addValue(output_v);
                output.setTable_domain(output_d);
                output.addTuple(output_t);
                
                MainWindow.showQueryOutput(output);
                
                break;
        }
    }
    
    public static void selectFields()
    {
        
    }
}
