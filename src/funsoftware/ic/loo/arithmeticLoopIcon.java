/*
 * arithmeticLoopIcon.java
 *
 * Created on 1 February 2006, 13:37
 *
 */

package funsoftware.ic.loo;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.st.*;
import funsoftware.var.*;
import funsoftware.events.*;
import funsoftware.consts.*;
import funsoftware.struct.*;


/**
 * This icon loops until a condition is met. The condition uses variables and constants instead of a sensor's reading.
 * @author Thomas Legowo
 */
public class arithmeticLoopIcon extends loopIcon{
    
    // the local variables
    private NestedOperation NO;      
    
    // for algorithm representation, this infinite loop icon has an identifier of 8
    private int identifier;    
    
    /** Creates a new instance of arithmeticLoopIcon */
    public arithmeticLoopIcon() {
    }
       
    /**
     * Creates a new instance of arithmeticLoopIcon
     * @param filepath The source file of this icon's image
     */
    public arithmeticLoopIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        identifier = 8;
        NO = new NestedOperation();
        setImage();
    }
    
    /**
     * To set the expression
     * @param nno The nested operation of the expression
     */
    public void setExpression(NestedOperation nno)
    {
        NO = nno;
    }
    
    /**
     * To get the expression
     * @return The nested operation of the expression
     */
    public NestedOperation getExpression()
    {
        return NO;
    }    
    
    /**
     * Sets the images and labels for this icon
     */
    public void setImage()
    {
        removeAll();
        setLayout(new java.awt.GridBagLayout());
        
        // creating the top panel
        javax.swing.ImageIcon nic;
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/arithmetic/arithmetic.gif"));
        PicButton pic = new PicButton(nic);
        add(pic);
        setPreferredSize(new java.awt.Dimension(40, 40));
    }
    
    /**
     * Get the help title of this arithmetic icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Arithmetic Repeat Icon";
        return s;
    }
    
    /**
     * Get help message of this arithmetic loop icon
     * @return Help message
     */     
    public String getHelpMsg()
    {
        String s = "DETAILS:\n\n";
        s += "Expression: ";
        Operand tmp_op = NO.getOperand1();
        if(tmp_op instanceof constant)
        {
            constant c = (constant) tmp_op;
            s += c.getValue();
        }
        else if(tmp_op instanceof variable)
        {
            variable v = (variable) tmp_op;
            s += v.getName();            
        }
        s += " ";
        s += NO.getOperator().getOperator()+" ";

        tmp_op = NO.getOperand2();
        if(tmp_op instanceof constant)
        {
            constant c = (constant) tmp_op;
            s += c.getValue();
        }
        else if(tmp_op instanceof variable)
        {
            variable v = (variable) tmp_op;
            s += v.getName();            
        }
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */     
    public String getHelpDesc()
    {
        String s = "This icon repeats a sequence of icons\nas long as the provided arithmetic expression\nis satisfied.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */    
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/arithmeticLoopIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public arithmeticLoopIcon Clone()
    {
        return new arithmeticLoopIcon("/icons/loops/arithmetic/arithmetic.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        arithmeticLoopIcon newRI = Clone();  
        newRI.setExpression(NO);
        newRI.setImage();
        return newRI;
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
        algo += "loop";
        algo += id_separator;
        algo += identifier;
        algo += id_separator;
        algo += getId();
        algo += id_att_separator;
        algo += att_boundary_begin;
        algo += NO.getAOTranslation(id_separator, id_att_separator, att_boundary_begin, att_boundary_end, att_value_connector, att_separator);
        algo += att_boundary_end;
        return algo;
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
        NQCCode += "// arithmetic loop\n";
        NQCCode += indentation+"while(";
        
        Operand tmp_op = NO.getOperand1();
        if(tmp_op instanceof constant)
        {
            constant c = (constant) tmp_op;
            NQCCode += c.getValue();
        }
        else if(tmp_op instanceof variable)
        {
            variable v = (variable) tmp_op;
            NQCCode += v.getName();            
        }
        NQCCode += " ";
        NQCCode += NO.getOperator().getOperator()+" ";
        
        tmp_op = NO.getOperand2();
        if(tmp_op instanceof constant)
        {
            constant c = (constant) tmp_op;
            NQCCode += c.getValue();
        }
        else if(tmp_op instanceof variable)
        {
            variable v = (variable) tmp_op;
            NQCCode += v.getName();            
        }
        NQCCode += ")\n";
        
        return NQCCode;
    }
    
    /**
     * Set its own attributes given a string from a text representation of it
     * @param id_att_separator The id and attribute separator
     * @param att Attributes
     * @param att_value_connector Attribute connector
     * @param att_separator Attribute separator
     */
    public void setAttributes(String att, String att_value_connector, String att_separator, String id_att_separator)
    {   
        String[] sp = att.split(id_att_separator);
        String[] sp2 = sp[1].split(att_value_connector);
        auxiliary tmp_aux;
        
        String type = sp2[1]; 
        String value;
        
        att = att.substring(att.indexOf(id_att_separator)+2);
        att = att.substring(att.indexOf(att_value_connector)+1);
        
        sp = att.split(id_att_separator);
        sp2 = sp[1].split(att_value_connector);
        sp = sp2[1].split(att_separator);
        value = sp[0].substring(0, sp[0].length()-1);
        
        if(type.compareTo("v") == 0)  // a variable
        {            
            variable v = var_list.Instance().getVariable(value);
            if(v == null)  // must be local, not global
            {
                tmp_aux = getAux();
                if(tmp_aux instanceof monitorEvent)
                {
                    monitorEvent ev = (monitorEvent)tmp_aux;
                    v = ev.getAuxPoint().getVariable(value);
                }
                else
                {
                    v = tmp_aux.getVariable(value);    
                }
            }  
            NO.setOperand1(v);
        }
        else if(type.compareTo("c") == 0)  // a constant
        {
            constant c = new constant();
            c.setValue(Integer.parseInt(value));
            NO.setOperand1(c);
        }
        
        att = att.substring(att.indexOf(id_att_separator)+2);
        att = att.substring(att.indexOf(att_separator)+1);
        
        sp = att.split(att_value_connector);
        sp2 = sp[1].split(id_att_separator);
        
        type = sp2[0];
        
        sp = att.split(id_att_separator);
        sp2 = sp[1].split(att_value_connector);
        sp = sp2[1].split(att_separator);
        value = sp[0].substring(0, sp[0].length()-1);        
        
        if(type.compareTo("v") == 0)  // a variable
        {            
            variable v = var_list.Instance().getVariable(value);
            if(v == null)  // must be local, not global
            {
                tmp_aux = getAux();
                if(tmp_aux instanceof monitorEvent)
                {
                    monitorEvent ev = (monitorEvent)tmp_aux;
                    v = ev.getAuxPoint().getVariable(value);
                }
                else
                {
                    v = tmp_aux.getVariable(value);    
                }
            }  
            NO.setOperand2(v);
        }
        else if(type.compareTo("c") == 0)  // a constant
        {
            constant c = new constant();
            c.setValue(Integer.parseInt(value));
            NO.setOperand2(c);
        }       
        
        att = att.substring(att.indexOf(id_att_separator)+2);
        att = att.substring(att.indexOf(att_separator)+1);

        sp = att.split(id_att_separator); 
        sp2 = sp[1].split(att_value_connector);
        value = sp2[1].substring(0, sp2[1].length()-3);    
        
        Operator opt = new Operator();
        opt.setOperator(value);
        NO.setOperator(opt);        
    }    
    
    /**
     * To indicate whether this icon uses variable(s) in its expression
     * @param var The variable to be searched
     * @return True or false
     */
    public Boolean useAVariable(variable var)
    {
        if(NO != null)
        {
            if(NO.getOperand1() instanceof variable)
            {
                variable tmp_v = (variable)NO.getOperand1();
                if(tmp_v.getType() == var.getType() && tmp_v.getName() == var.getName())
                {
                    return true;     
                }                   
            }       
            if(NO.getOperand2() instanceof variable)
            {
                variable tmp_v = (variable)NO.getOperand2();
                if(tmp_v.getType() == var.getType() && tmp_v.getName() == var.getName())
                {
                    return true;     
                }                   
            }            
        }
        return false;
    }    
}
