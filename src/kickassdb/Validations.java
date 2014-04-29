package kickassdb;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/* Special helper class for data validations. This class will help
   reduce the code in the CUP file by calling methods declared here.
*/
public class Validations
{
    public static boolean validateInsertingTuple(ArrayList<String> attName, ArrayList<String> valType, Table t)
    {
        ArrayList<String> attType = new ArrayList<String>();
        int index = 0;

        /* get the types of the attributes */
        for ( String name : attName )
            attType.add(t.getAttType(name));
       
        if( attType.size() == 0) 
        {
            for( Attribute att : t.getTable_domain() )
                attType.add(att.getType().toString());
        }

        /* check if the attribute names are valid */
        boolean flag = false;       //if flag is set to false, it means the attribute name doesn't exist in the table
        for( String  name : attName) 
        {
            flag = false;
            for(Attribute att : t.getTable_domain()) 
            {
                if (att.getAttribute_name().equals(name)) 
                {
                    flag = true;
                    break;
                }
            }
            
            if(!flag) 
            {
                JOptionPane.showMessageDialog(KickAssDB.mainwindow, "Attribute " + name + " doesn't exist in the table!", "Error", JOptionPane.ERROR_MESSAGE);                                         
                return false;
            }
        }

 
        /* check if the two array sizes match */
        
        if( attType.size() != valType.size()) 
        {
            //System.out.println("~~~~~~~~~" + attType.size() + " " + valType.size());
            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "The number of the records doesn't match with the attributes!", "Error", JOptionPane.ERROR_MESSAGE);                                         
            return false;
        }
       
        /* check if the data types are valid */
        for( String s : attType) 
        {
            if(valType.get(index).equals(s))
                index ++;
            else 
            {
                JOptionPane.showMessageDialog(KickAssDB.mainwindow, "Invalid data type!", "Error", JOptionPane.ERROR_MESSAGE);                                         
                return false;
            }
        }
        return true;
    }
   
    public static boolean validateColumnSize(Tuple tuple) 
    {
        for( Value value : tuple.getTuple_values()) 
        {
            int len = value.getValue().toString().length();
            //System.out.println("len: " + len);
            if (len > 40) 
            {
                JOptionPane.showMessageDialog(KickAssDB.mainwindow, "Can't exceed the max size 40!", "Error", JOptionPane.ERROR_MESSAGE);                                         
                return false;
            }
        }
        return true;
    }
   
    public static boolean validatePrimaryKey(Tuple tuple, Table t)
    {
        int pk_index = 0;
        Attribute tmp_pk = t.getPrimary_key();
       
        /* find out the index of the primary key in the table */
        for ( Attribute att : t.getTable_domain() ) 
        {
            if ( att.getAttribute_name().equals(tmp_pk.getAttribute_name()))
                break;
            else
                pk_index++;
        }
       
        /* contrast the tuple with the existed tuples to see if the primary key already existed */
        Object cur_pk = tuple.getValue(pk_index).getValue();
        if ( cur_pk.equals("") || cur_pk.equals("''")) 
        {
            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "Primary Key cannot be null.", "Error", JOptionPane.ERROR_MESSAGE);                                         
            return false;
        }
        for ( Tuple tmp_tuple : t.getTable_tuples() ) 
        {
           
            Object existed_pk = tmp_tuple.getValue(pk_index).getValue();
           
            if (existed_pk.equals(cur_pk)) 
            {
                JOptionPane.showMessageDialog(KickAssDB.mainwindow, "Primary Key already exists: " + existed_pk, "Error", JOptionPane.ERROR_MESSAGE);                                         
                return false;
            }
        }

        return true;
    }
    
    
    public static boolean validateVarcharSize(Tuple tuple, ArrayList<Attribute> table_domain)
    {
        /* Run through the whole tuple */
        for ( int i = 0; i < table_domain.size(); i++ )
        {
            /* Compare each String value of the tuple to the size specifized in the domain
               Assuming the the tuple column size is the same as the domain column size (which
               indeed should be)
            */
            
            if ( table_domain.get(i).getType() == Attribute.Type.VARCHAR)
            {
                /* If the Attribute type is VARCHAR, Proceed to check the size */
                int value_size = tuple.getValue(i).getValue().toString().length();                
                
                if ( value_size > table_domain.get(i).getAttributeSize() )
                {
                    /* Value to be inserted in tuple exceeds the size specified in the domain,
                       display error
                    */
                    JOptionPane.showMessageDialog(KickAssDB.mainwindow, "One of the values to be inserted exceeds the length specified in the table domain.", "Error", JOptionPane.ERROR_MESSAGE);                                         
                    return false;
                }
            }
        }
        
        return true;                
    }
    
    public static boolean isFieldInDomain(String field_name, Table table)
    {        
        for ( Attribute a : table.getTable_domain() )        
            if ( a.getAttribute_name().equals(field_name) )
                return true;
            
        return false;
    }
    
    public static boolean isFieldAmbiguous(String field_name, Table table1, Table table2 )
    {               
        int counter = 0;
        for ( Attribute a : table1.getTable_domain() )
        {
            if ( a.getAttribute_name().equals(field_name) )
            {    
                counter++;
                break;
            }
        }
        
        for ( Attribute a : table2.getTable_domain() )
        {
            if ( a.getAttribute_name().equals(field_name) )
            {    
                counter++;
                break;                          
            }
        }        
        
        if ( counter > 1 )
           return  true; 
        else
            return false;
   
    }
        
    
    public boolean tableExists(String name)
    {
        /* Checks if there is a table with the specified name */
        for ( Table t : MainWindow.getDefaultSchema().getSchema() )
            if ( t.getTable_name().equals(name) )
                return true;
        
        return false;
    }
}