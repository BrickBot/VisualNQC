/*
 * joinIcon.java
 *
 * Created on 13 August 2005, 16:08
 */

package funsoftware.ic.others;

import funsoftware.ic.*;
import funsoftware.wr.*;

/**
 * This icon is used as joints between segment of wires that bend 90 degrees.
 * Using this class ensures that the convention of icons enclosed by the other icons on the same y-coordinate is followed.
 * @author Thomas Legowo
 *
 */
public class joinIcon extends Icon{
    
    // the private attributes
    private Icon leftNeighbour;
    private Icon rightNeighbour;
    
    private Wire leftWire;
    private Wire rightWire;
    
    private int type;   // 0 for being in the IF part, 1 for being in the ELSE part
    
    /** Creates a new instance of joinIcon */
    public joinIcon() {
    }
    
    /**
     * Creates a new instance of joinIcon
     * @param filepath Source file of this icon's image
     */
    public joinIcon(String filepath)
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
     * To get left wire of this join icon
     * @return Left wire
     */
    public Wire getLeftWire()
    {
        return leftWire;
    }
    
    /**
     * To get right wire of this join icon
     * @return Right wire
     */
    public Wire getRightWire()
    {
        return rightWire;
    }
    
    /**
     * Sets the left neighbour icon
     * @param nicon Left neighbour icon
     */
    public void setLeftNeighbour(Icon nicon)
    {
        leftNeighbour = nicon;
    }
    
    /**
     * Sets the right neighbour icon
     * @param nicon Right neighbour icon
     */
    public void setRightNeighbour(Icon nicon)
    {
        rightNeighbour = nicon;
    }

    /**
     * Sets the left wire of this join icon
     * @param nwire Left wire
     */
    public void setLeftWire(Wire nwire)
    {
        leftWire = nwire;
    }
    
    /**
     * Sets the right wire of this join icon
     * @param nwire Right wire
     */
    public void setRightWire(Wire nwire)
    {
        rightWire = nwire;
    }
    
    /**
     * Sets the image of this icon
     */
    public void setImage()
    {
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());
        
        javax.swing.ImageIcon nic;
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/other/join.gif"));
        PicButton pic = new PicButton(nic);
        add(pic);
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public joinIcon Clone()
    {
        return new joinIcon("/icons/other/join.gif");
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
