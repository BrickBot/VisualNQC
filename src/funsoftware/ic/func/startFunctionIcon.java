/*
 * startFunctionIcon.java
 *
 * Created on 29 December 2005
 */

package funsoftware.ic.func;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.inter.*;
import funsoftware.ic.others.*;
import funsoftware.ic.PicButton;
import funsoftware.functs.*;

/**
 * This class is a subclass from startIcon.java
 * This class defines a starting icon of a function
 * @author Thomas Legowo
 */
public class startFunctionIcon extends startIcon{
    
    private function func;
    
    /** Creates a new instance of startFunctionIcon */
    public startFunctionIcon() {
    }
    
    /**
     * Creates a new instance of startFunctionIcon
     * @param filepath Source file of the icon's image
     */
    public startFunctionIcon(String filepath)
    {
        super(filepath);
    }
    
    /**
     * Sets the function this starting function icon is associated to
     * @param fu the function
     */
    public void setFunction(function fu)
    {
        func = fu;
    }
    
    /**
     * Gets the function this starting function icon is associated to
     * @return The function this starting function icon is associated to
     */
    public function getFunction()
    {
        return func;
    }    
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public startFunctionIcon Clone()
    {
        return new startFunctionIcon("/icons/closures/argument.gif");
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
        algo += "\nstartfunction";  
        algo += id_separator;
        algo += func.getNumId();
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
        NQCCode += "\n{\n";
        NQCCode += func.getNQCLocalVar();
        NQCCode += "\n";
        return NQCCode;
    }
}
