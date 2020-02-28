/*
 * endEventIcon.java
 *
 * Created on 2 February 2006, 10:30
 *
 */

package funsoftware.ic.ev;

import funsoftware.ic.*;
import funsoftware.ic.others.*;
import funsoftware.ic.PicButton;
import funsoftware.events.*;

/**
 * This icon ends a sequence of icons within an event procedure
 * @author Thomas Legowo
 */
public class endEventIcon extends endIcon{
    
    // private variable
    private monitorEvent ev;    
    
    /** Creates a new instance of endEventIcon */
    public endEventIcon() {
    }

    /**
     * Creates a new instance of endEventIcon
     * @param filepath Source file of this icon
     */
    public endEventIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        setImage();
    }
    
    /**
     * Sets the event this ending event icon is associated to
     * @param ev the event
     */
    public void setEventMonitor(monitorEvent ev)
    {
        this.ev = ev;
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
    public endEventIcon Clone()
    {
        return new endEventIcon("/icons/closures/ret_val.gif");
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
        algo += "\nendmonitorevent";  
        algo += id_separator;
        algo += ev.getNumId();
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
        NQCCode += indentation+"}";
        NQCCode += "\n\n";
        return NQCCode;
    }    
    
}
