/*
 * endFunctionIcon.java
 *
 * Created on 29 December 2005
 */

package funsoftware.ic.func;

import funsoftware.ic.*;
import funsoftware.ic.others.*;
import funsoftware.ic.PicButton;
import funsoftware.functs.*;

/**
 * This class is for icon ending a program, the opposite of startFunctionIcon.java
 * @author Thomas Legowo
 */
public class endFunctionIcon extends endIcon{

    // private variable
    private function func;    
    
    /** Creates a new instance of endFunctionIcon */
    public endFunctionIcon() {
    }
    
    /**
     * Creates a new instance of endFunctionIcon
     * @param filepath Source file of this icon
     */
    public endFunctionIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        setImage();
    }
    
    
    /**
     * Sets the function this ending function icon is associated to
     * @param fu the function
     */
    public void setFunction(function fu)
    {
        func = fu;
    }
    
    /**
     * to set the image of this icon
     */
    public void setImage()
    {
        PicButton pic = new PicButton(super.getImage());
        setLayout(new java.awt.GridBagLayout());
        add(pic);
        setPreferredSize(new java.awt.Dimension(40,40));
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public endFunctionIcon Clone()
    {
        return new endFunctionIcon("/icons/closures/ret_val.gif");
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
        algo += "\nendfunction";  
        algo += id_separator;
        algo += func.getNumId();
        algo += ".";
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
        NQCCode += "}";
        NQCCode += "\n\n";
        return NQCCode;
    }
}
