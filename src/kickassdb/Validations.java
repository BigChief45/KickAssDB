package kickassdb;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/* Special helper class for data validations. This class will help
   reduce the code in the CUP file by calling methods declared here.
*/
public class Validations 
{
    public static boolean validateInsertingTuple(ArrayList<String> attType, ArrayList<String> valType)
    {
        int index = 0;
                
        if(attType.size() != valType.size()) 
        {
            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "The number of values does not match the number of attributes in the table domain.", "Error", JOptionPane.ERROR_MESSAGE);                                          
            return false;
        }
        
        for( String s : attType) 
        {
            if(valType.get(index).equals(s))
                index ++;
            else 
            {
                JOptionPane.showMessageDialog(KickAssDB.mainwindow, "One of the values to be inserted is of invalid data type.", "Error", JOptionPane.ERROR_MESSAGE);                                          
                return false;
            }
        }
        return true;
    }
    
    public static boolean validateColumnSize(Tuple tuple) 
    {
        for(Value value : tuple.getTuple_values()) 
        {
            int len = value.getValue().toString().length();
            System.out.println("len: " + len);
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
//        System.out.println("~~~~~~~~validating Existed primary key");
        int pk_index = 0;
        Attribute tmp_pk = t.getPrimary_key();
        
        //find out the index of the primary key in the table
        for ( Attribute att : t.getTable_domain() ) 
        {
            if (att.getAttribute_name().equals(tmp_pk.getAttribute_name()))
                break;
            else
                pk_index++;
        }
        //pk_index = t.getTable_domain().size() - 1 - pk_index;
        
        //contrast the tuple with the existed tuples to see if the primary key already existed
        Object cur_pk = tuple.getValue(pk_index).getValue();
        if (cur_pk.equals("") ) {
            JOptionPane.showMessageDialog(KickAssDB.mainwindow, "Primary key can't be null!", "Error", JOptionPane.ERROR_MESSAGE);                                          
            return false;
        }
        for ( Tuple tmp_tuple : t.getTable_tuples() ) {
            
            Object existed_pk = tmp_tuple.getValue(pk_index).getValue();
            
            if (existed_pk.equals(cur_pk)) {
                JOptionPane.showMessageDialog(KickAssDB.mainwindow, "primary key already existed: " + existed_pk, "Error", JOptionPane.ERROR_MESSAGE);                                          
                //System.out.println("pk already existed: " + cur_pk + " " + existed_pk + " and pk_index is: " + pk_index);
                return false;
            }
//            else {
//                System.out.println("pk_index is: " + pk_index + " " + existed_pk + " " + cur_pk);
//            }
//            
            
        }

        return true;
    }
}
