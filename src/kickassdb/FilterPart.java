package kickassdb;

public class FilterPart 
{
    private String field_alias;
    private String field_name;
    private int field_position;
    private Object value;

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
        return field_alias;
    }//End public String getFieldAlias()
    
    public void setFieldAlias(String a)
    {
        field_alias = a;
        
        //Each time it is set we check the type of the filter part
        this.setFilterPartType();
        
    }//End public void setFieldAlias(String a)
    
    public String getFieldName()
    {
        return field_name;
    }//End public String getFieldName()
    
    public void setFieldName(String n)
    {
        field_name = n;
        
        //Each time it is set we check the type of the filter part
        this.setFilterPartType();
        
    }//End public void setFieldName(String n)
    
    public int getFieldPosition()
    {
        return field_position;
    }//End public int getFieldPosition()
    
    public void setFieldPosition(int p)
    {
        field_position = p;
        
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
        
            this.filterType_type = Type.ALIAS_ATTRIBUTE;
            return;
            
        }//End if ( !this.field_name.equals("") )        
                        
        //Is type = ATTRIBUTE
        if ( !this.field_name.equals("") ){
        
            this.filterType_type = Type.ATTRIBUTE;
            return;
            
        }//End if ( !this.field_name.equals("") )
        
        //Is type = VALUE\
        if ( this.value != null ){

            this.filterType_type = Type.VALUE;

        }//End if ( !this.field_name.equals("") )
    
    }//End private void setFilterPartType ()
    
}//End public void setValue(Object v)
