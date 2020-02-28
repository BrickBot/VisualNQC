/*
 * startTaskIcon.java
 *
 * Created on 29 December 2005
 */

package funsoftware.ic.tsk;

import funsoftware.ic.*;
import funsoftware.inter.*;
import funsoftware.ic.others.*;
import funsoftware.ic.PicButton;
import funsoftware.tasks.*;

/**
 * This class is a subclass from startIcon.java
 * This class defines a starting icon of a task
 * @author Thomas Legowo
 */
public class startTaskIcon extends startIcon{
    
    private task tsk;

    /** Creates a new instance of startTaskIcon */
    public startTaskIcon() {
    }
    
    /**
     * Creates a new instance of startTaskIcon
     * @param filepath Source file of the icon's image
     */
    public startTaskIcon(String filepath)
    {
        super(filepath);
    }
    
    /**
     * Sets the task this starting task icon is associated to
     * @param tk the task
     */
    public void setTask(task tk)
    {
        tsk = tk;
    }
    
    /**
     * To return the task
     * @return The task
     */
    public task getTask()
    {
        return tsk;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public startTaskIcon Clone()
    {
        return new startTaskIcon("/icons/closures/argument.gif");
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
        algo += "\nstarttask";  
        algo += id_separator;
        algo += tsk.getNumId();
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
        NQCCode += tsk.getNQCLocalVar();
        NQCCode += "\n";
        return NQCCode;
    }
}
