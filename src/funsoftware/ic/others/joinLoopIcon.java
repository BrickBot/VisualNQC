/*
 * joinLoopIcon.java
 *
 * Created on 7 August 2005, 21:28
 */

package funsoftware.ic.others;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.gri.*;

/**
 * This is a special class similar to joinIcon.java
 * The joinLoopIcon connects current icons horizontally and the loop icon vertically.
 * @author Thomas Legowo
 * 
 */
public class joinLoopIcon extends Icon{
    
    // the private attributes
    private Icon leftNeighbour;
    private Icon rightNeighbour;
    private Icon bottomNeighbour;
    
    private Wire leftWire;
    private Wire rightWire;
    private Wire bottomWire;
    
    /** Creates a new instance of joinLoopIcon */
    public joinLoopIcon() {
    }
    
    /**
     * Creates a new instance of joinLoopIcon
     * @param filepath Source file of this icon's image
     */
    public joinLoopIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); // for startIcon the size of the icon is 26x61
        setImage();
    }
    
    // The get and set methods
    /**
     * Gets the left neighbour icon of this icon
     * @return Left neighbour icon
     */
    public Icon getLeftNeighbour()
    {
        return leftNeighbour;
    }
    
    /**
     * Gets the right neighbour icon of this icon
     * @return Right neighbour icon
     */
    public Icon getRightNeighbour()
    {
        return rightNeighbour;
    }
    
    /**
     * Gets the bottom neighbour icon visually
     * @return Bottom neighbour
     */
    public Icon getBottomNeighbour()
    {
        return bottomNeighbour;
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
     * Gets bottom wire of this icon
     * @return Bottom wire
     */
    public Wire getBottomWire()
    {
        return bottomWire;
    }
    
    /**
     * Sets the left neighbour
     * @param nicon New left neighbour
     */
    public void setLeftNeighbour(Icon nicon)
    {
        leftNeighbour = nicon;
    }
    
    /**
     * Sets the right neighbour
     * @param nicon New right neighbour
     */
    public void setRightNeighbour(Icon nicon)
    {
        rightNeighbour = nicon;
    }
    
    /**
     * Sets the bottom neighbour
     * @param nicon New bottom neighbour
     */
    public void setBottomNeighbour(Icon nicon)
    {
        bottomNeighbour = nicon;
    }
    
    /**
     * Sets the left wire of this icon
     * @param nwire New left wire
     */
    public void setLeftWire(Wire nwire)
    {
        leftWire = nwire;
    }
    
    /**
     * Sets the right wire of this icon
     * @param nwire New right wire
     */
    public void setRightWire(Wire nwire)
    {
        rightWire = nwire;
    }
    
    /**
     * Sets the bottom wire of this icon
     * @param nwire New bottom wire
     */
    public void setBottomWire(Wire nwire)
    {
        bottomWire = nwire;
    }
    
    /**
     * Sets the coordinate of this icon
     * @param nco New coordinate
     */
    public void setCoordinate(coord nco)
    {
        super.setCoordinate(nco);
    }
    
    /**
     * To set the image of this icon
     */
    public void setImage()
    {
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());
        
        javax.swing.ImageIcon nic;
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/other/joinLoop.gif"));
        PicButton pic = new PicButton(nic);
        add(pic);
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public joinLoopIcon Clone()
    {
        return new joinLoopIcon("/icons/other/joinLoop.gif");
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
        algo += "";
        return algo;
    }
    
    /**
     * Returns a string format of the NQCCode representation of this object
     * @param indentation Indentation
     * @return The NQC Code
     */
    public String getNQCCode(String indentation)
    {
        String NQCCode = "";
        return NQCCode;
    }
}
