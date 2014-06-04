package kickassdb;

public class FilterPart 
{
    private String field_alias;
    private String field_name;
    private Table table;
    private int field_position;
    private Object value;

    //New fields
    //Left Side
    //Index of the attribute in the merged table
    private int indexAttributeLeft;
    //Index of the attribute with the alias in the merged table
    //int indexAliasAttributeLeft = -1;

    //Values
    private String valueTypeLeft;
    private String stringValueLeft;
    private int intValueLeft;
    
    //Right Side
    //Index of the attribute in the merged table
    private int indexAttributeRight;
    //Index of the attribute with the alias in the merged table
    //int indexAliasAttributeLeft = -1;

    //Values
    private String valueTypeRight;
    private String stringValueRight;
    private int intValueRight;    

    /**
     * @return the field_alias
     */
    public String getField_alias() {
        return field_alias;
    }

    /**
     * @param field_alias the field_alias to set
     */
    public void setField_alias(String field_alias) {
        this.field_alias = field_alias;
    }

    /**
     * @return the field_name
     */
    public String getField_name() {
        return field_name;
    }

    /**
     * @param field_name the field_name to set
     */
    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public void setTable(Table t)
    {
        table = t;
    }
    
    public void setTable(String tableName)
    {
        for ( Table t : MainWindow.getDefaultSchema().getSchema() )
            if ( t.getTable_name().equals(tableName) )
                table = t;
    }
    
    public Table getTable()
    {
        return table;
    }
    
    /**
     * @return the field_position
     */
    public int getField_position() {
        return field_position;
    }

    /**
     * @param field_position the field_position to set
     */
    public void setField_position(int field_position) {
        this.field_position = field_position;
    }

    /**
     * @return the indexAttributeLeft
     */
    public int getIndexAttributeLeft() {
        return indexAttributeLeft;
    }

    /**
     * @param indexAttributeLeft the indexAttributeLeft to set
     */
    public void setIndexAttributeLeft(int indexAttributeLeft) {
        this.indexAttributeLeft = indexAttributeLeft;
    }

    /**
     * @return the valueTypeLeft
     */
    public String getValueTypeLeft() {
        return valueTypeLeft;
    }

    /**
     * @param valueTypeLeft the valueTypeLeft to set
     */
    public void setValueTypeLeft(String valueTypeLeft) {
        this.valueTypeLeft = valueTypeLeft;
    }

    /**
     * @return the stringValueLeft
     */
    public String getStringValueLeft() {
        return stringValueLeft;
    }

    /**
     * @param stringValueLeft the stringValueLeft to set
     */
    public void setStringValueLeft(String stringValueLeft) {
        this.stringValueLeft = stringValueLeft;
    }

    /**
     * @return the intValueLeft
     */
    public int getIntValueLeft() {
        return intValueLeft;
    }

    /**
     * @param intValueLeft the intValueLeft to set
     */
    public void setIntValueLeft(int intValueLeft) {
        this.intValueLeft = intValueLeft;
    }

    /**
     * @return the indexAttributeRight
     */
    public int getIndexAttributeRight() {
        return indexAttributeRight;
    }

    /**
     * @param indexAttributeRight the indexAttributeRight to set
     */
    public void setIndexAttributeRight(int indexAttributeRight) {
        this.indexAttributeRight = indexAttributeRight;
    }

    /**
     * @return the valueTypeRight
     */
    public String getValueTypeRight() {
        return valueTypeRight;
    }

    /**
     * @param valueTypeRight the valueTypeRight to set
     */
    public void setValueTypeRight(String valueTypeRight) {
        this.valueTypeRight = valueTypeRight;
    }

    /**
     * @return the stringValueRight
     */
    public String getStringValueRight() {
        return stringValueRight;
    }

    /**
     * @param stringValueRight the stringValueRight to set
     */
    public void setStringValueRight(String stringValueRight) {
        this.stringValueRight = stringValueRight;
    }

    /**
     * @return the intValueRight
     */
    public int getIntValueRight() {
        return intValueRight;
    }

    /**
     * @param intValueRight the intValueRight to set
     */
    public void setIntValueRight(int intValueRight) {
        this.intValueRight = intValueRight;
    }
    
    /**
     *The type of the filter part
     */
    public static enum Type { ATTRIBUTE, ALIAS_ATTRIBUTE, VALUE }    
    
    private Type filterType_type;
    
    public FilterPart()
    {
        field_alias = "";
        field_name = "";
        field_position = -1;
        value = null;
    }//End public FilterPart()
    
    public String getFieldAlias()
    {
        return getField_alias();
    }//End public String getFieldAlias()
    
    public void setFieldAlias(String a)
    {
        setField_alias(a);
        
        //Each time it is set we check the type of the filter part
        this.setFilterPartType();
        
    }//End public void setFieldAlias(String a)
    
    public String getFieldName()
    {
        return getField_name();
    }//End public String getFieldName()
    
    public void setFieldName(String n)
    {
        setField_name(n);
        
        //Each time it is set we check the type of the filter part
        this.setFilterPartType();
        
    }//End public void setFieldName(String n)
    
    public int getFieldPosition()
    {
        return getField_position();
    }//End public int getFieldPosition()
    
    public void setFieldPosition(int p)
    {
        setField_position(p);
        
        //Each time it is set we check the type of the filter part
        this.setFilterPartType();        
        
    }//End public void setFieldPosition(int p)
    
    public Object getValue()
    {
        return value;
    }//End public Object getValue()
    
    public void setValue(Object v)
    {
        value = v;
        
        //Each time it is set we check the type of the filter part
        this.setFilterPartType();        
        
    }//End public void setValue(Object v)
    
    /**
     * @return the filterType_type
     */
    public Type getFilterType_type() {
        return filterType_type;
    }//End public Type getFilterType_type()

    /**
     * @param filterType_type the filterType_type to set
     */
    public void setFilterType_type(Type filterType_type) {
        this.filterType_type = filterType_type;
    }//End public void setFilterType_type(Type filterType_type)
    
    private void setFilterPartType (){
    
        //Is type = ALIAS_ATTRIBUTE
        if ( !this.field_name.equals("") && !this.field_alias.equals("")){
        
            this.setFilterType_type(Type.ALIAS_ATTRIBUTE);
            return;
            
        }//End if ( !this.field_name.equals("") )        
                        
        //Is type = ATTRIBUTE
        if ( !this.field_name.equals("") ){
        
            this.setFilterType_type(Type.ATTRIBUTE);
            return;
            
        }//End if ( !this.field_name.equals("") )
        
        //Is type = VALUE\
        if ( this.getValue() != null ){

            this.setFilterType_type(Type.VALUE);

        }//End if ( !this.field_name.equals("") )
    
    }//End private void setFilterPartType ()
    
}//End public void setValue(Object v)
