/*
 * endTaskIcon.java
 *
 * Created on 29 December 2005
 */

package funsoftware.ic.tsk;

import funsoftware.ic.*;
import funsoftware.ic.others.*;
import funsoftware.ic.PicButton;
import funsoftware.tasks.*;

/**
 * This class is for icon ending a program, the opposite of startTaskIcon.java
 * @author Thomas Legowo
 */
public class endTaskIcon extends endIcon{
    
    // private variable
    private task tsk;
    
    /** Creates a new instance of endTaskIcon */
    public endTaskIcon() {
    }
    
    /**
     * Creates a new instance of endTaskIcon
     * @param filepath Source file of this icon
     */
    public endTaskIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        setImage();
    }
    
    /**
     * Sets the task this ending task icon is associated to
     * @param tk the task
     */
    public void setTask(task tk)
    {
        tsk = tk;
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
    public endTaskIcon Clone()
    {
        return new endTaskIcon("/icons/closures/ret_val.gif");
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
        algo += "\nendtask";  
        algo += id_separator;
        algo += tsk.getNumId();
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
