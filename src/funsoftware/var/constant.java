/*
 * constant.java
 *
 * Created on 11 January 2006, 20:33
 *
 */

package funsoftware.var;

/**
 * This class represent a contant in an arithmetic operation
 * @author Thomas Legowo
 */
public class constant extends Operand{
    
    private int value;
    
    /** Creates a new instance of constant */
    public constant() {
        value = 0;
    }
    
    /**
     * Sets the value of this constant
     * @param v The new value
     */
    public void setValue(int v)
    {
        value = v;
    }
    
    /**
     * Returns the value of this constant
     * @return The value
     */
    public int getValue()
    {
        return value;
    }
        
    /**
     * To get an NQCCode translation of this constant
     * @return The NQC code
     */
    public String getNQCCode()
    {
        return (Integer.toString(value));
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
        algo += "c";    
        algo += id_att_separator;
        algo += att_boundary_begin;
        algo += "value"+att_value_connector;
        algo += value;
        algo += att_boundary_end;        
        return algo;
    }
}
