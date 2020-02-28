/*
 * objectIcon.java
 *
 * Created on 28 December 2005, 14:51
 *
 */

package funsoftware.ic.obj;

import funsoftware.ic.*;
import funsoftware.wr.*;

/**
 * This method is for object type icons (2 neighbours and 2 enclosing wires)
 * @author Thomas Legowo
 */
public class objectIcon extends Icon{
    
    // private attributes
    private Icon leftNeighbour;
    private Icon rightNeighbour;
    
    private Wire leftWire;
    private Wire rightWire;
    
    /** Creates a new instance of objectIcon */
    public objectIcon() {
    }
    
   /**
     * Creates a new instance of objectIcon
     * @param filepath Source file for the image of this object typed icon
     */
    public objectIcon(String filepath) {
        super(filepath);
    }
    
    
   // The get and set methods
    /**
     * Gets left neighbour of this icon
     * @return Left neighbour
     */
    public Icon getLeftNeighbour()
    {
        return leftNeighbour;
    }
    
    /**
     * Gets right neighbour of this icon
     * @return Right neighbour
     */
    public Icon getRightNeighbour()
    {
        return rightNeighbour;
    }
    
    /**
     * Gets left wire of this icon
     * @return Left wire 
     */
    public Wire getLeftWire()
    {
        return leftWire;
    }
    
    /**
     * Gets right wire of this icon
     * @return Right wire 
     */
    public Wire getRightWire()
    {
        return rightWire;
    }
    
    /**
     * Sets left neighbour of this icon
     * @param nicon New left icon
     */
    public void setLeftNeighbour(Icon nicon)
    {
        leftNeighbour = nicon;
    }
    
    /**
     * Sets right neighbour of this icon
     * @param nicon New right icon
     */
    public void setRightNeighbour(Icon nicon)
    {
        rightNeighbour = nicon;
    }
    
    /**
     * Sets left wire of this icon
     * @param nwire New left wire
     */
    public void setLeftWire(Wire nwire)
    {
        leftWire = nwire;
    }
    
    /**
     * Sets right wire of this icon
     * @param nwire New right wire
     */
    public void setRightWire(Wire nwire)
    {
        rightWire = nwire;
    } 
}
