package kickassdb;

import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;

public class Table implements Serializable
{
 
    private int table_id;
    private String table_name;
    private Alias table_alias;
    
    private Attribute primary_key;    
    private ArrayList<Attribute> table_domain;
    private ArrayList<Tuple> table_tuples;
    
    private ArrayList<Attribute> table_complete_domain;
    
    private ArrayList<Attribute> indexes;
    
    public Table()
    {
        table_tuples = new ArrayList<>();
        table_alias = new Alias();
        indexes = new ArrayList();
        
        table_complete_domain = new ArrayList<>();
        
    }
    
    public Table(int ID, String name, ArrayList<Attribute> domain)
    {
    
        this.table_id = ID;
        this.table_name = name;
        this.table_domain = domain;        
                
        table_tuples = new ArrayList<>();        
        table_alias = new Alias();
        indexes = new ArrayList();
        table_complete_domain = new ArrayList<>();         
        
    }
    
    public void createCompleteDomain(){
    
        for (Attribute attr : table_domain) {
            
            Attribute nAttr = attr;
            nAttr.setAttribute_name(this.table_name+nAttr.getAttribute_name());
            table_complete_domain.add(nAttr);
            
        }//End for (Attribute attr : table_domain)
    
    }
    
    public void setIndexes(ArrayList<Attribute> i)
    {
        indexes = i;
    }
    
    public ArrayList<Attribute> getIndexes()
    {
        return indexes;
    }
            
    public void addIndex(Attribute i )
    {
        indexes.add(i);
    }
    
    public void addTuple(Tuple tuple)
    {
        table_tuples.add(tuple);
        
        /* Check if it is a real table or temporary table (query relation) */
        if ( table_name != null )
            MainWindow.outputText.append("New record added to " + table_name + " \n");
    }
    
    public Tuple getTuple(int pos) 
    {
        return table_tuples.get(pos);
    }
                                     
    public int getTable_id() 
    {
        return table_id;
    }

    public void setTable_id(int table_id) 
    {
        this.table_id = table_id;
    }
    
    public Alias getTable_alias() 
    {
        return table_alias;
    }    

    public void setTable_alias(Alias alias) 
    {
        table_alias = alias;
    }

    public String getTable_name() 
    {
        return table_name;
    }

    public void setTable_name(String table_name) 
    {
        this.table_name = table_name;
    }

    public ArrayList<Attribute> getTable_domain() 
    {
        return table_domain;
    }

    public void setTable_domain(ArrayList<Attribute> table_domain) 
    {
        this.table_domain = table_domain;
    }
       
    public ArrayList<Tuple> getTable_tuples()
    {
        return table_tuples;
    }

    public void setTable_tuples(ArrayList<Tuple> table_tuples) 
    {
        this.table_tuples = table_tuples;
    }

    public Attribute getPrimary_key() 
    {
        return primary_key;
    }
    
    public void setPrimary_key(Attribute pk)
    {
        primary_key = pk;
    }
    
    public int getFieldPosition(String field_name)
    {
        /* Returns the field's position in the domain if it exists */
        int position = 0;
        
        /* Loop through the domain */
        for ( Attribute a : table_domain )
        {
            if ( field_name.equals(a.getAttribute_name()) )
                return position;
            
            position++;
        }
        
        return -1; // Field does not exist in domain
    }
    
    public int getFieldPositionCompleteDomain(String field_name)
    {
        /* Returns the field's position in the domain if it exists */
        int position = 0;
        
        /* Loop through the domain */
        for ( Attribute a : table_complete_domain )
        {
            if ( field_name.equals(a.getAttribute_name()) )
                return position;
            
            position++;
        }//End for ( Attribute a : table_complete_domain )
        
        return -1; // Field does not exist in domain
    }//End public int getFieldPositionCompleteDomain(String field_name)
    
    public boolean attributeExistsInDomain(String attrName){
    
        boolean result = false;
        
        //We iterate throughout the table's domain and see if the any of its attributes matches the attrName
        for (Attribute attribute : table_domain) {
            
            //If we find a match we set the result to true
            if ( attribute.getAttribute_name().equals(attrName) ){
                result = true;
                break;
            }//End if ( attribute.getAttribute_name().equals(attrName) )
            
        }//End for (Attribute attribute : table_domain)
        
        return result;
        
    }//End public boolean attributeExistsInDomain()
            
    public String getAttType(String name) {
        String type = null;
        for (Attribute attribute : table_domain) {
            if(attribute.getAttribute_name().equals(name)) {
                type = attribute.getType().name();         
                //System.out.printf("The type of %s is %s.\n", name, type);
                return type;
            }
        }
        return null;
    }
    
    public String getAttTypeByPosition(int position) {
        String type = null;
        
        Attribute selectedAttribute = this.table_domain.get(position);
        return selectedAttribute.getType().toString();
        
    }    
    
    public Attribute getAttributeByName(String name) {

        for (Attribute attribute : table_domain) {
            
            if (attribute.getAttribute_name().equals(name))
                return attribute;
            
        }//End for (Attribute attribute : table_domain)
        
        return null;
        
    }//End public Attribute getAttributeByName(String name)
    
    public Attribute getAttributeByPosition(int i) {

        Attribute attr = table_domain.get(i);        
        
        return attr;
        
    }//End public Attribute getAttributeByName(String name)
    
    public boolean hasIndexes()
    {
        return !indexes.isEmpty();
    }
    
    public void printDomain()
    {
    
        System.out.println(this.table_name);
        System.out.println("=============");
        for (Attribute attribute : table_domain) 
        {            
            System.out.println("Name: " + attribute.getAttribute_name() + ", Type:" + attribute.getType().name() + ", Size: " + attribute.getAttributeSize());   
        }                
        
        System.out.println("Primary Key: " + primary_key.getAttribute_name());
        
        System.out.println("");
                
    }
    
    public void printTuples()
    {
        System.out.println(this.table_name);
        System.out.println("=============");
        
        for ( Tuple tuple : table_tuples ) 
        {            
              for ( Value tuple_value : tuple.getTuple_values() )
              {
                  String value = tuple_value.getValue().toString();                  
                  System.out.print(value + "\t");
              }
              System.out.println();
        }                
        
        System.out.println("");
    }
    
    private static Table indexSearch(BPlusTree tree, Value v, String operation)
    {
        Table result = new Table();
        
        switch ( operation )
        {
            case "=":
                ArrayList res = tree.getEquals(Integer.parseInt(v.getValue().toString()));
                result.setTable_tuples(tree.getEquals(Integer.parseInt(v.getValue().toString())));
                
//                if ( !result.getTable_tuples().isEmpty() )                    
//                    System.out.println("No encontro nada");
//                else
//                    System.out.println("Encontro algo");
                
                break;
            case ">":
                ArrayList res2 = tree.getGreater(Integer.parseInt(v.getValue().toString()));
                result.setTable_tuples(tree.getGreater(Integer.parseInt(v.getValue().toString())));
                break;
            case "<":
                ArrayList res3 = tree.getLess(Integer.parseInt(v.getValue().toString()));
                result.setTable_tuples(tree.getLess(Integer.parseInt(v.getValue().toString())));
                break;
            case "<>":
                ArrayList res4 = tree.getDifferent(Integer.parseInt(v.getValue().toString()));
                result.setTable_tuples(tree.getDifferent(Integer.parseInt(v.getValue().toString())));
                break;                
        }
                        
        return result;
    }
    
    private static Table indexSearch(HashMap hm, Value v, String operation)
    {
        Table result = new Table();
        
        switch ( operation )
        {
            case "=":
                result.setTable_tuples(HashMethods.search(hm, Integer.parseInt(v.getValue().toString())));
                break;
            case ">":
                result.setTable_tuples(HashMethods.searchGreater(hm, Integer.parseInt(v.getValue().toString())));
                break;
            case "<":
                result.setTable_tuples(HashMethods.searchLess(hm, Integer.parseInt(v.getValue().toString())));
                break;
            case "<>":
                result.setTable_tuples(HashMethods.searchDifferent(hm, Integer.parseInt(v.getValue().toString())));
                break;                
        }
                        
        return result;
    }
            
    public static Table filterAndMerge(Table table1, Table table2, ArrayList<QueryFilter> filters)
    {
        /* Obtain the Filter 1 data */
        QueryFilter filter1 = filters.get(0);
        Table result_left = new Table();
        
        FilterPart lPart1 = filter1.getLeftFilter();
        FilterPart rPart1 = filter1.getRightFilter();        
        
        Table left_dataset = new Table();
        Table right_dataset = new Table();
        
        boolean left_attribute_exists = false;
        boolean right_attribute_exists = false;
        
        if ( !lPart1.getFieldName().equals(""))
            left_attribute_exists = true;
        if ( !rPart1.getFieldName().equals(""))
            right_attribute_exists = true;
        
        /* CHECK LEFT PART */
        if ( left_attribute_exists == true )
        {
            /* Check if this attribute is indexed */
            if ( Operations.getAttributeIndexType(lPart1.getTable(), lPart1) == Attribute.IndexType.NULL )
            {
                /* Attribute is not indexed */
                /* Data set is the whole table */
                left_dataset = lPart1.getTable();                
            }
            else if ( Operations.getAttributeIndexType(lPart1.getTable(), lPart1) == Attribute.IndexType.TREE_TYPE_INDEXING )
            {
                /* Tree Type Indexing */                
                left_dataset = lPart1.getTable();       
            }
            else if ( Operations.getAttributeIndexType(lPart1.getTable(), lPart1) == Attribute.IndexType.HASH_TYPE_INDEXING )
            {
                /* Hash Type Indexing */
                left_dataset = lPart1.getTable();
            }
        }
        else
        {
            /* There is a fixed value, no index */
            left_dataset = right_dataset;
        }
        
        /* Check RIGHT PART */
        if ( right_attribute_exists == true )
        {            
            /* Check if this attribute is indexed */
            if ( Operations.getAttributeIndexType(rPart1.getTable(), rPart1) == Attribute.IndexType.NULL )
            {
                /* Attribute is not indexed */
                /* Data set is the whole table */
                right_dataset = rPart1.getTable();                
            }
            else if ( Operations.getAttributeIndexType(rPart1.getTable(), rPart1) == Attribute.IndexType.TREE_TYPE_INDEXING )
            {
                /* Tree Type Indexing */
            }
            else if ( Operations.getAttributeIndexType(rPart1.getTable(), rPart1) == Attribute.IndexType.HASH_TYPE_INDEXING )
            {
                /* Hash Type Indexing */
                
            }
        }
        else
        {
            /* Fixed value, no index */
            right_dataset = left_dataset;
        }
        
        /* FILTER both datasets */
        for ( Tuple t : left_dataset.getTable_tuples() )
        {                        
            Value lv1;
            if ( left_attribute_exists == true )
                lv1 = t.getValue(lPart1.getFieldPosition());
            else
                lv1 = new Value(lPart1.getValue());
                     
            if ( Operations.getAttributeIndexType(rPart1.getTable(), rPart1) == Attribute.IndexType.TREE_TYPE_INDEXING )
            {
                BPlusTree tree = rPart1.getTable().getAttributeByName(rPart1.getFieldName()).getBPlusTree();
                right_dataset = indexSearch(tree, lv1, filter1.getOperand());
            }
            else if ( Operations.getAttributeIndexType(rPart1.getTable(), rPart1) == Attribute.IndexType.HASH_TYPE_INDEXING )
            {
                HashMap hm = rPart1.getTable().getAttributeByName(rPart1.getFieldName()).getHashTable();                
                right_dataset = indexSearch(hm, lv1, filter1.getOperand());
            } 
                        
            for ( Tuple t2 : right_dataset.getTable_tuples() )
            {
                Value rv1;
                if ( right_attribute_exists == true )
                    rv1 = t2.getValue(rPart1.getFieldPosition());
                else
                    rv1 = new Value(rPart1.getValue());
                
                if ( compareValues(lv1, rv1, filter1.getOperand()) == true )
                {
                    /* Merge both tuples into one */ 
                    Tuple merged_tuple = Tuple.mergeTuples(t, t2);
                    
                    /* Add this tuple to a result table */
                    result_left.addTuple(merged_tuple);
                }
            }
        }
                               
        return result_left;
    }
            
    private static boolean compareValues(Value lv, Value rv, String operator)
    {
        /* Compares 2 values using the specified operator */
        switch ( operator )
        {
            case "=":
                if ( Integer.parseInt(lv.getValue().toString()) == Integer.parseInt(rv.getValue().toString()) )
                    return true;
                break;
            case ">":
                if ( Integer.parseInt(lv.getValue().toString()) > Integer.parseInt(rv.getValue().toString()) )
                    return true;
                break;
            case "<":
                if ( Integer.parseInt(lv.getValue().toString()) < Integer.parseInt(rv.getValue().toString()) )
                    return true;
                break;
            case "<>":
                if ( Integer.parseInt(lv.getValue().toString()) != Integer.parseInt(rv.getValue().toString()) )
                    return true;
                break;                
        }
        
        return false;
    }
    
    public static Table mergeTables(Table table1, Table table2)
    {
        /* Merges the recieved tables into one single table. Merging all the domains
           and data
        */        
        
        /* Merge all the data */
        Table crossproduct = new Table();
        
        //First we set the table domain
        ArrayList<Attribute> table1_domain = table1.getTable_domain();
        ArrayList<Attribute> table2_domain = table2.getTable_domain();
        ArrayList<Attribute> domain = new ArrayList();
        ArrayList<Attribute> completeDomain = new ArrayList();        
        
        for (Attribute attr : table1_domain) 
        {
            domain.add(attr);
            
            //Now we will add the name before the attribute for table1
            Attribute new_attribute = new Attribute();
            
            new_attribute.setAttributeSize(attr.getAttributeSize());
            new_attribute.setAttribute_name(attr.getAttribute_name());
            new_attribute.setType(attr.getType());            
            
            String attrName = new_attribute.getAttribute_name();
            new_attribute.setAttribute_name(table1.table_name+attrName);
            
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
            new_attribute.setAttribute_name(table2.table_name+attrName);
            
            //We add the attribute to the complete domain
            completeDomain.add(new_attribute);            
            
        }//End for (Attribute attr : table2_domain)
                        
        crossproduct.setTable_domain(domain);
        crossproduct.setTable_complete_domain(completeDomain);
        
        for ( Tuple tuple : table1.getTable_tuples() )
        {
            for ( Tuple tuple2 : table2.getTable_tuples() )
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
                
            }//End for ( Tuple tuple2 : table2.getTable_tuples() )
            
        }//End for ( Tuple tuple : table1.getTable_tuples() )
                                       
        return crossproduct;
        
    }//End public static Table mergeTables(Table table1, Table table2)

    /**
     * @return the table_complete_domain
     */
    public ArrayList<Attribute> getTable_complete_domain() {
        return table_complete_domain;
    }

    /**
     * @param table_complete_domain the table_complete_domain to set
     */
    public void setTable_complete_domain(ArrayList<Attribute> table_complete_domain) {
        this.table_complete_domain = table_complete_domain;
    }
    
    @Override
    public String toString(){
    
        return this.table_name;
    
    }
    
}
