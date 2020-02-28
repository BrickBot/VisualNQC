/*
 * Operand.java
 *
 * Created on 11 January 2006, 20:33
 *
 */

package funsoftware.var;

/**
 * Superclass to variables, operators, constants and nestedoperations
 * @author Thomas Legowo
 */
public class Operand {
    
    /** Creates a new instance of Operand */
    public Operand() {
    }
    
    /**
     * To implement an NQCCode translation
     * @return The NQCCode of the operand
     */
    public String getNQCCode()
    {
        return "";
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
        return "";
    }
}
