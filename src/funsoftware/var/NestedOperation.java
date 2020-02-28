/*
 * NestedOperation.java
 *
 * Created on 11 January 2006, 20:34
 *
 */

package funsoftware.var;

/**
 * This class represents an operation that can be nested and it contains other operands and an operator
 * @author Thomas Legowo
 */
public class NestedOperation extends Operand{

    // private variables
    private Operand op1;
    private Operand op2;
    private Operator operator;    
    
    /** Creates a new instance of NestedOperation */
    public NestedOperation() {
    }

    /**
     * Sets the first operand
     * @param o1 The new first operand
     */
    public void setOperand1(Operand o1)
    {
        op1 = o1;
    }
    
    /**
     * Sets the second operand
     * @param o2 The second operand
     */
    public void setOperand2(Operand o2)
    {
        op2 = o2;
    }  

    /**
     * Sets the operator
     * @param ot The operator
     */
    public void setOperator(Operator ot)
    {
        operator = ot;
    }   
    
    /**
     * Returns the first operand
     * @return The first operand
     */
    public Operand getOperand1()
    {
        return op1;
    }
    
    /**
     * Returns the second operand
     * @return The second operand
     */
    public Operand getOperand2()
    {
        return op2;
    }
    
    /**
     * Returns the operator
     * @return The operator
     */
    public Operator getOperator()
    {
        return operator;
    }   
    
    /**
     * Returns a string format of the NQCCode representation of this object
     * @return The NQC Code
     */
    public String getNQCCode()
    {
        String NQCCode = new String();
        NQCCode += "("+op1.getNQCCode()+" "+operator.getNQCCode()+" "+op2.getNQCCode()+")";
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
        algo += "NO";    
        algo += id_att_separator;
        algo += att_boundary_begin;
        algo += "op1"+att_value_connector;
        algo += op1.getAOTranslation(id_separator, id_att_separator, att_boundary_begin, att_boundary_end, att_value_connector, att_separator);
        algo += att_separator;
        algo += "op2"+att_value_connector;
        algo += op2.getAOTranslation(id_separator, id_att_separator, att_boundary_begin, att_boundary_end, att_value_connector, att_separator);
        algo += att_separator;
        algo += "operator"+att_value_connector;
        algo += operator.getAOTranslation(id_separator, id_att_separator, att_boundary_begin, att_boundary_end, att_value_connector, att_separator);
        algo += att_boundary_end;        
        return algo;
    }    
}
