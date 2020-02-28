/*
 * startEventIcon.java
 *
 * Created on 2 February 2006, 10:30
 *
 */

package funsoftware.ic.ev;

import funsoftware.ic.*;
import funsoftware.inter.*;
import funsoftware.ic.others.*;
import funsoftware.ic.PicButton;
import funsoftware.events.*;

/**
 * To indicate a start on a sequence of icons to be executed during an event procedure
 * @author Thomas Legowo
 */
public class startEventIcon extends startIcon{
    
    private monitorEvent ev;

    /** Creates a new instance of beginEventIcon */
    public startEventIcon() {
    }
    
    /**
     * Creates a new instance of beginEventIcon
     * @param filepath Source file of the icon's image
     */
    public startEventIcon(String filepath)
    {
        super(filepath);
    }
    
    /**
     * Sets the event monitor this starting event icon is associated to
     * @param ev the event monitor
     */
    public void setEventMonitor(monitorEvent ev)
    {
        this.ev = ev;
    }
    
    /**
     * To return the event monitor
     * @return The event monitor
     */
    public monitorEvent getEventMonitor()
    {
        return ev;
    }    
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public startEventIcon Clone()
    {
        return new startEventIcon("/icons/closures/argument.gif");
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
        algo += "\nstartmonitorevent";  
        algo += id_separator;
        algo += ev.getNumId();
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
        NQCCode += "\n"+indentation+"{\n";          
        return NQCCode;
    }
}
