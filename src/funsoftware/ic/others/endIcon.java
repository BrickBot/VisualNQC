/*
 * endIcon.java
 *
 * Created on 29 December 2005, 16:24
 *
 */

package funsoftware.ic.others;

import funsoftware.ic.*;
import funsoftware.wr.*;

/**
 * This class is the superclass for all kind of ending icons of event monitor, tasks and functions
 * @author Thomas Legowo
 */
public class endIcon extends Icon{
        
    // the private attributes
    private Icon leftNeighbour;
    private Wire leftWire;
    
    /** Creates a new instance of endIcon */
    public endIcon() {
    }    
    
    /**
     * Creates a new instance of endIcon
     * @param filepath Source file of this icon
     */
    public endIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); 
    }
    
    // The get and set methods
    /**
     * Gets the left icon neighbour of this end icon
     * @return Left icon neighbour
     */
    public Icon getLeftNeighbour()
    {
        return leftNeighbour;
    }
    
    /**
     * Gets the left wire of this end icon
     * @return Left wire
     */
    public Wire getLeftWire()
    {
        return leftWire;
    }
    
    /**
     * Sets the left icon neighbour of this end icon
     * @param nicon New left icon neighbour
     */
    public void setLeftNeighbour(Icon nicon)
    {
        leftNeighbour = nicon;
    }
    
    /**
     * Sets the left wire of this end icon
     * @param nwire New left wire
     */
    public void setLeftWire(Wire nwire)
    {
        leftWire = nwire;
    }
}
