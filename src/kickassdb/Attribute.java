/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kickassdb;

/**
 *
 * @author Otto
 */
public class Attribute {

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return the attribute_name
     */
    public String getAttribute_name() {
        return attribute_name;
    }

    /**
     * @param attribute_name the attribute_name to set
     */
    public void setAttribute_name(String attribute_name) {
        this.attribute_name = attribute_name;
    }
    
    public enum Type {
        INTEGER, VARCHAR
    }    
    
    private Type type;
    private String attribute_name;    
       
    public Attribute(String n, int v){
                
        this.attribute_name = n;
        this.type = Type.INTEGER;
    
    }
    
    public Attribute(String n, char[] v){
                
        this.attribute_name = n;
        this.type = Type.VARCHAR;
    
    }
        
}
