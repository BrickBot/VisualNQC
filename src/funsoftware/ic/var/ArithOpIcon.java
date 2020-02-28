/*
 * ArithOpIcon.java
 *
 * Created on 22 December 2005, 15:12
 *
 */

package funsoftware.ic.var;

import funsoftware.ic.*;
import funsoftware.ic.obj.*;
import funsoftware.var.*;
import funsoftware.struct.*;
import funsoftware.consts.*;
import funsoftware.events.*;

/**
 * This class maintains an icon representing an arithmetic operation.
 * @author Thomas Legowo
 */
public class ArithOpIcon extends objectIcon{
    
    // the private variables
    private int identifier;  // identifier of 12
    private ArithmeticOperation AO;
    private auxiliary aux;
    
    /** Creates a new instance of ArithOpIcon */
    public ArithOpIcon() {
    }

    /**
     * Creates a new instance of ArithOpIcon
     * @param filepath The source file of this icon's image
     */
    public ArithOpIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        identifier = 12;
        AO = new ArithmeticOperation();
        setImage();
    }    
    
    /**
     * Returns the arithmetic operation this icon is representing
     * @return The arithmetic operation this icon is representing
     */
    public ArithmeticOperation getAO()
    {
        return AO;
    }
    
    /**
     * Sets the arithmetic operation this icon is representing
     * @param AO The arithmetic operation this icon is representing
     */
    public void setAO(ArithmeticOperation AO)
    {
        this.AO = AO;
    }    

    /**
     * Set the auxiliary that this arithmetic operation icon is in
     * @param a The auxiliary that this arithmetic operation icon is in
     */
    public void setAux(auxiliary a)
    {
        aux = a;
    }    
    
    /**
     * Get the auxiliary that this arithmetic operation icon is in
     * @return Auxiliary (function or task)
     */
    public auxiliary getAux()
    {
        return aux;
    }    
    
    /**
     * To set the image of this icon
     */
    public void setImage()
    {
        removeAll();       
        setLayout(new java.awt.GridBagLayout());
        
        // creating the top panel
        javax.swing.ImageIcon nic;
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/arithmetic/opIcon.gif"));
        PicButton pic = new PicButton(nic);
        add(pic);        
        setPreferredSize(new java.awt.Dimension(40, 40));
    }
    
    
    /**
     * Get the help title of this arithmetic operation icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Arithmetic Operation Icon";
        return s;
    }
    
    /**
     * Get help message of this break icon
     * @return Help message
     */    
    public String getHelpMsg()
    {
        String s = "Arithmetic Operation:\n";
        s+=AO.getNQCCode("");
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */    
    public String getHelpDesc()
    {
        String s = "";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/ArithOpIcon.gif"));
        return ii;
    }    
    
    /**
     * To clone this ArithOpIcon
     * @return New instance of this ArithOpIcon
     */
    public ArithOpIcon Clone()
    {
        return new ArithOpIcon("/icons/arithmetic/opIcon.gif");
    }   
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        ArithOpIcon newAOI = Clone();  
        newAOI.setAux(aux);
        newAOI.setAO(AO);
        newAOI.setImage();
        return newAOI;
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
        algo += "object";
        algo += id_separator;
        algo += identifier;
        algo += id_att_separator;
        algo += AO.getAOTranslation(id_separator, id_att_separator, att_boundary_begin, att_boundary_end, att_value_connector, att_separator);
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
        NQCCode += "// an arithmetic operation\n";
        NQCCode += AO.getNQCCode(indentation);
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
        AO = new ArithmeticOperation();

        // get the first part, the variable return value
        String[] sp = att.split(id_att_separator);
        String value;
        sp = sp[1].split(att_separator);   // skip the res = v
        sp = sp[0].split(att_value_connector); 
        value = sp[1].substring(0, sp[1].length()-1);
        att = att.substring(att.indexOf(id_att_separator)+2);
        att = att.substring(att.indexOf(att_separator)+1);
        variable res = var_list.Instance().getVariable(value);
        if(res == null)  // must be local, not global
        {
                if(aux instanceof monitorEvent)
                {
                    monitorEvent ev = (monitorEvent)aux;
                    res = ev.getAuxPoint().getVariable(value);
                }
                else
                {
                    res = aux.getVariable(value);    
                }
        }
        AO.setVariable(res);     
        // so the first part is done
        
        // now do the first operand of AO
        sp = att.split(id_att_separator);
        sp = sp[0].split(att_value_connector);
        if(sp[1].compareTo("v") == 0)  // a variable
        {
            sp = att.split(id_att_separator);
            sp = sp[1].split(att_separator);  
            sp = sp[0].split(att_value_connector); 
            value = sp[1].substring(0, sp[1].length()-1);
            att = att.substring(att.indexOf(id_att_separator)+2);
            att = att.substring(att.indexOf(att_separator)+1);
            
            res = var_list.Instance().getVariable(value);
            if(res == null)  // must be local, not global
            {
                if(aux instanceof monitorEvent)
                {
                    monitorEvent ev = (monitorEvent)aux;
                    res = ev.getAuxPoint().getVariable(value);
                }
                else
                {
                    res = aux.getVariable(value);    
                } 
            }
            AO.setOperand1(res);
        }
        else if(sp[1].compareTo("c") == 0)  // a constant
        {
            constant c = new constant();
            sp = att.split(id_att_separator);
            sp = sp[1].split(att_separator);  
            sp = sp[0].split(att_value_connector); 
            value = sp[1].substring(0, sp[1].length()-1);
            att = att.substring(att.indexOf(id_att_separator)+2);
            att = att.substring(att.indexOf(att_separator)+1);
            c.setValue(Integer.parseInt(value));
            AO.setOperand1(c);
        }
        else if(sp[1].compareTo("NO") == 0)  // a nested operation
        {
            NestedOperation NO = new NestedOperation();
            att = att.substring(att.indexOf(id_att_separator)+2);
            att = helpSetAtt(att, att_value_connector, att_separator, id_att_separator, NO);
            AO.setOperand1(NO);
        }        
    }   
    
    // a helper method for setAttributes
    private String helpSetAtt(String att, String att_value_connector, String att_separator, String id_att_separator, NestedOperation the_NO)
    {
        String[] sp = att.split(id_att_separator);
        String value;
        variable res;
        sp = sp[0].split(att_value_connector);
        // do the first operand
        if(sp[1].compareTo("v") == 0)  // a variable
        {
            sp = att.split(id_att_separator);
            sp = sp[1].split(att_separator);  
            sp = sp[0].split(att_value_connector); 
            value = sp[1].substring(0, sp[1].length()-1);
            att = att.substring(att.indexOf(id_att_separator)+2);
            att = att.substring(att.indexOf(att_separator)+1);

            res = var_list.Instance().getVariable(value);
            if(res == null)  // must be local, not global
            {
                if(aux instanceof monitorEvent)
                {
                    monitorEvent ev = (monitorEvent)aux;
                    res = ev.getAuxPoint().getVariable(value);
                }
                else
                {
                    res = aux.getVariable(value);    
                } 
            }
            the_NO.setOperand1(res);          
        }
        else if(sp[1].compareTo("c") == 0)  // a constant
        {
            constant c = new constant();
            sp = att.split(id_att_separator);
            sp = sp[1].split(att_separator);  
            sp = sp[0].split(att_value_connector); 
            value = sp[1].substring(0, sp[1].length()-1);
            att = att.substring(att.indexOf(id_att_separator)+2);
            att = att.substring(att.indexOf(att_separator)+1);
            c.setValue(Integer.parseInt(value));
            the_NO.setOperand1(c);
        }
        else if(sp[1].compareTo("NO") == 0)  // a nested operation
        {
            NestedOperation NO = new NestedOperation();
            att = att.substring(att.indexOf(id_att_separator)+2);
            att = helpSetAtt(att, att_value_connector, att_separator, id_att_separator, NO);
            the_NO.setOperand1(NO);
        }
        
        // do the second operand
        sp = att.split(id_att_separator);
        sp = sp[0].split(att_value_connector);
        
        if(sp[1].compareTo("v") == 0)  // a variable
        {
            sp = att.split(id_att_separator);
            sp = sp[1].split(att_separator);  
            sp = sp[0].split(att_value_connector); 
            value = sp[1].substring(0, sp[1].length()-1);
            att = att.substring(att.indexOf(id_att_separator)+2);
            att = att.substring(att.indexOf(att_separator)+1);

            res = var_list.Instance().getVariable(value);
            if(res == null)  // must be local, not global
            {
                if(aux instanceof monitorEvent)
                {
                    monitorEvent ev = (monitorEvent)aux;
                    res = ev.getAuxPoint().getVariable(value);
                }
                else
                {
                    res = aux.getVariable(value);    
                }
            }
            the_NO.setOperand2(res);          
        }
        else if(sp[1].compareTo("c") == 0)  // a constant
        {
            constant c = new constant();
            sp = att.split(id_att_separator);
            sp = sp[1].split(att_separator);  
            sp = sp[0].split(att_value_connector); 
            value = sp[1].substring(0, sp[1].length()-1);
            att = att.substring(att.indexOf(id_att_separator)+2);
            att = att.substring(att.indexOf(att_separator)+1);
            c.setValue(Integer.parseInt(value));
            the_NO.setOperand2(c);
        }
        else if(sp[1].compareTo("NO") == 0)  // a nested operation
        {
            NestedOperation NO = new NestedOperation();
            att = att.substring(att.indexOf(id_att_separator)+2);
            att = helpSetAtt(att, att_value_connector, att_separator, id_att_separator, NO);
            the_NO.setOperand2(NO);
        }
        
        // do the operator
        sp = att.split(id_att_separator);
        sp = sp[0].split(att_value_connector);
        if(sp[1].compareTo("o") == 0)
        {
            Operator O = new Operator();
            sp = att.split(id_att_separator);
            sp = sp[1].split(att_separator);  
            sp = sp[0].split(att_value_connector); 
            value = sp[1].substring(0, sp[1].length()-2);
            O.setOperator(value);
            the_NO.setOperator(O);
            att = att.substring(att.indexOf(id_att_separator)+2);
            att = att.substring(att.indexOf(att_separator)+1);
        }        
        return att;
    }
}
