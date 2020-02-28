/*
 * variable.java
 *
 * Created on 10 January 2006, 15:13
 *
 */

package funsoftware.var;

import funsoftware.struct.*;

/**
 * This class represents an individual variable
 * @author Thomas Legowo
 */
public class variable extends Operand{
    
    // private variables
    private int num_id;   // id can be the same but in different functions
    private String name;
    private int value;   // the value this variable holds
    private auxiliary aux;
    
    // the type
    private int type;  // 0 if global, 1 if local, 2 if an argument
    
    /** Creates a new instance of variable */
    public variable() {
        name = new String();
        type = 0;
        value = 0;
        aux = new auxiliary();
    }
    
    /**
     * To set the id of this variable
     * @param nid the new id
     */
    public void setNumId(int nid)
    {
        num_id = nid;
    }
 
    /**
     * To get the id of this variable
     * @return The numId
     */
    public int getNumId()
    {
        return num_id;
    }    
    
    /**
     * To set the name of this variable
     * @param n the new name
     */
    public void setName(String n)
    {
        name = n;
    }
    
    /**
     * To get the name of this variable
     * @return The variable's name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * To set the type of this variable
     * @param t The type of this variable
     */
    public void setType(int t)
    {
        type = t;
    }
    
    /**
     * To get the current value of this variable
     * @return The current value of the variable
     */
    public int getValue()
    {
        return value;
    }   
 
    /**
     * To set the current value of this variable
     * @param t The current value of this variable
     */
    public void setValue(int t)
    {
        value = t;
    }
    
    /**
     * To get the type of this variable
     * @return The type of this variable
     */
    public int getType()
    {
        return type;
    }
    
    /**
     * To set the auxiliary that owns this variable
     * @param a The auxiliary that owns this variable
     */
    public void setAux(auxiliary a)
    {
        aux = a;
    }
    
    /**
     * To get the auxiliary that owns this variable
     * @return The auxiliary that owns this variable
     */
    public auxiliary getAux()
    {
        return aux;
    }    
    
    /**
     * Algorithm translation for the Translator object to create a text representation of this icon
     * @param id_separator To separate ids
     * @param id_att_separator To separate id with attribute
     * @param att_boundary_begin To start attributes
     * @param att_boundary_end To end attributes
     * @param att_value_connector To separate attributes and its values
     * @param att_separator To separate attributes
     * @return Translation
     */
    public String getTranslation(String id_separator, String id_att_separator, 
                                 String att_boundary_begin, String att_boundary_end, String att_value_connector, String att_separator)
    {
        String algo = new String();
        algo += "\n";  // to begin with
        algo += "variable";
        algo += id_separator;
        algo += num_id;        
        algo += id_att_separator;
        algo += att_boundary_begin;
        algo += "name"+att_value_connector;
        algo += name;
        algo += att_separator;
        algo += "value"+att_value_connector;
        algo += value;
        algo += att_separator;
        algo += "type"+att_value_connector;
        algo += type;
        algo += att_boundary_end;        
        // add the arguments where possible
        return algo;
    }    
    
    
    /**
     * To get a translation
     * @param id_separator To separate ids
     * @param id_att_separator To separate id with attribute
     * @param att_boundary_begin To start attributes
     * @param att_boundary_end To end attributes
     * @param att_value_connector To separate attributes and its values
     * @param att_separator To separate attributes
     * @return Translation
     */
    public String getAOTranslation(String id_separator, String id_att_separator, 
                                 String att_boundary_begin, String att_boundary_end, String att_value_connector, String att_separator)
    {
        String algo = new String();
        algo += "v";    
        algo += id_att_separator;
        algo += att_boundary_begin;
        algo += "name"+att_value_connector;
        algo += name;
        algo += att_boundary_end;        
        return algo;
    }    
    
    /**
     * For variable declaration in NQC translated version
     * @return The NQC declaration
     */
    public String getNQCDeclaration()
    {
        return ("int "+name+" = "+value+";\n");
    }
    
    /**
     * To be incorporated with other operands in an arithmetic operation
     * @return The NQC code
     */
    public String getNQCCode()
    {
        return name;
    }
    
    /**
     * To clone this variable
     * @return New instance of this variable
     */
    public variable Clone()
    {
        return new variable();
    }
    
    /**
     * Set its own attributes given a string from a text representation of it
     * @param att Attributes
     * @param att_value_connector Attribute connector
     * @param att_separator Attribute separator
     */
    public void setAttributes(String att, String att_value_connector, String att_separator)
    {
        String[] sp = att.split(att_separator);
        String[] sp2 = new String[2];
        
        // do the first one --> name
        sp2 = sp[0].split(att_value_connector);
        name = sp2[1];
        
        // do the second one --> value
        sp2 = sp[1].split(att_value_connector);
        value = Integer.parseInt(sp2[1]);
        
        // do the third one --> type
        sp2 = sp[2].split(att_value_connector);
        type = Integer.parseInt(sp2[1]);
    }    
}
