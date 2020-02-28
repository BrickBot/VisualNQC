/*
 * auxiliary.java
 *
 * Created on 30 December 2005, 14:54
 *
 */

package funsoftware.struct;

import funsoftware.ic.*;
import funsoftware.var.*;
import funsoftware.inter.*;

/**
 * This class is a superclass for all auxiliary elements that require new programming windows. They are tasks, event monitors and functions.
 * @author Thomas Legowo
 */
public class auxiliary {
    
    /** Creates a new instance of auxiliary */
    public auxiliary() {
    }
    
    /**
     * To return the auxiliary's name
     * @return The name
     */
    public String getName()
    {
        return null;
    }
        
    /**
     * To set the numId of this auxiliary
     * @param i The new numId
     */
    public void setNumId(int i)
    {
    }
    
    /**
     * To return the NQC function header
     * @return The NQC function header 
     */
    public String getNQCFunctionHeader()
    {
        return null;    
    }
    
    /**
     * The return the begin icon
     * @return The begin icon
     */
    public Icon getBeginIcon()
    {
        return null;
    }
    
    /**
     * To return the numId of the auxiliary
     * @return The numId
     */
    public int getNumId()
    {
        return 0;
    }
    
    /**
     * To return the translation of this auxiliary
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
        return null;
    }
    
    /**
     * Returns the NQC task header
     * @return The NQC task header
     */
    public String getNQCTaskHeader()
    {
        return null;
    }
    
    /**
     * To add a local variable onto the list
     * @param nv A local variable
     */
    public void addVariable(variable nv)
    {

    }
    
    /**
     * To delete a local variable from this auxiliary
     * @param nv A local variable to be deleted
     */
    public void delVariable(variable nv)
    {

    }
    
    /**
     * To return the list of the local variables that this auxiliary owns
     * @return The list of the local variables
     */
    public java.util.Vector<variable> getVariables()
    {
        java.util.Vector<variable> tmp = new java.util.Vector<variable>();
        return tmp;
    }   
    
    /**
     * To return the list of the local variables list manager that this auxiliary owns
     * @return The list of the local variables list manager
     */
    public local_var_list getLocalVarList()
    {
        local_var_list lvl = new local_var_list();  // for events linking to auxiliaries using their local variables
        return lvl;
    }
    
    /**
     * To return an individual local variable that this auxiliary structure has
     * @param name The name of the variable
     * @return The variable
     */
    public variable getVariable(String name)
    {
        return null;
    }
    
    /**
     * Returns the panel this task's member are on, not the individual task's icon itself
     * @return The programming window
     */
    public ProgWindow getProgWindow()
    {
        return null;
    }
    /**
     * Returns the scroll pane of the programming window
     * @return Programming window's scroller
     */
    public javax.swing.JScrollPane getScroller()
    {
        return null;
    }
    /**
     * Returns the panel on top of the programming window
     * @return Programming window's parent panel
     */
    public javax.swing.JPanel getPanel()
    {
        return null;
    }    
}
