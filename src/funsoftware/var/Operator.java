/*
 * Operator.java
 *
 * Created on 12 January 2006, 12:21
 *
 */

package funsoftware.var;

/**
 * This class represents an operator of an arithmetic operation
 * @author Thomas Legowo
 */
public class Operator {
    
    private String the_op;
    
    /** Creates a new instance of Operator */
    public Operator() {
        the_op = new String();
    }
    
    
    /**
     * Sets the operator
     * @param s The operator
     */
    public void setOperator(String s)
    {
        the_op = s;
    }
    
    /**
     * Returns the operator
     * @return The operator
     */
    public String getOperator()
    {
        return the_op;
    }
    
    /**
     * Returns the NQC representation of this
     * @return The NQC code
     */
    public String getNQCCode()
    {
        return the_op;
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
        if(the_op.compareTo("") != 0)  // not empty
        {
            algo += "o";    
            algo += id_att_separator;
            algo += att_boundary_begin;
            algo += "theop"+att_value_connector;
            algo += the_op;
            algo += att_boundary_end;  
        }      
        return algo;
    }
}
