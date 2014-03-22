package kickassdb;

import java.util.ArrayList;

/* Special helper class for data validations. This class will help
   reduce the code in the CUP file by calling methods declared here.
*/
public class Validations 
{
    public static boolean validateInsertingTuple(ArrayList<String> attType, ArrayList<String> valType)
    {
//        System.out.println("~~~~~~~validating Inserting Tuple");
        int index = 0;
        for( String s : attType) {
            if(valType.get(index).equals(s))
                index ++;
            else
                return false;
        }
        return true;
    }
    
    public static boolean validatePrimaryKey(Tuple tuple, Table t)
    {
//        System.out.println("~~~~~~~~validating Existed primary key");
        int pk_index = 0;
        Attribute tmp_pk = t.getPrimary_key();
        
        //find out the index of the primary key in the table
        for ( Attribute att : t.getTable_domain() ) {
            if (att.getAttribute_name().equals(tmp_pk.getAttribute_name()))
                break;
            else
                pk_index++;
        }
        pk_index = t.getTable_domain().size() - 1 - pk_index;
        
        //contrast the tuple with the existed tuples to see if the primary key already existed
        Object cur_pk = tuple.getValue(pk_index).getValue();
        for ( Tuple tmp_tuple : t.getTable_tuples() ) {
            
            Object existed_pk = tmp_tuple.getValue(pk_index).getValue();
            
            if (existed_pk.equals(cur_pk)) {
                System.out.println("pk already existed: " + cur_pk + " " + existed_pk + " and pk_index is: " + pk_index);
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
