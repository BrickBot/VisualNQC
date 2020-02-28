/*
 * ArithmeticOperation.java
 *
 * Created on 11 January 2006, 20:33
 *
 */

package funsoftware.var;

/**
 * This class controls an arithmetic operations that contain the three operands and the lone operator.
 * @author Thomas Legowo
 */
public class ArithmeticOperation {
    
    // private variables
    private Operand op1;
    
    private variable res;
    
    /** Creates a new instance of ArithmeticOperation */
    public ArithmeticOperation() {
    }
    
    
    /**
     * Sets the operand
     * @param o1 The operand
     */
    public void setOperand1(Operand o1)
    {
        op1 = o1;
    }    
    
    /**
     * Sets the variable on the left hand side of the operation
     * @param v The variable
     */
    public void setVariable(variable v)
    {
        res = v; 
    }
    
    /**
     * Returns the operand
     * @return The operand
     */
    public Operand getOperand1()
    {
        return op1;
    }   
    
    /**
     * Returns the variable on the left hand side of the operation
     * @return The variable
     */
    public variable getVariable()
    {
        return res;
    }
    
    /**
     * Returns a string format of the NQCCode representation of this object
     * @param indentation Indentation
     * @return The NQC Code
     */
    public String getNQCCode(String indentation)
    {
        String NQCCode = new String();
        NQCCode += indentation;
        NQCCode += res.getNQCCode()+" = "+op1.getNQCCode()+";\n";
        return NQCCode;
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
        algo += att_boundary_begin;
        algo += "res"+att_value_connector;
        algo += res.getAOTranslation(id_separator, id_att_separator, att_boundary_begin, att_boundary_end, att_value_connector, att_separator);
        algo += att_separator;  
        algo += "op1"+att_value_connector;
        algo += op1.getAOTranslation(id_separator, id_att_separator, att_boundary_begin, att_boundary_end, att_value_connector, att_separator); 
        return algo;
    }    
}
