/*
 * joinEndBranchIcon.java
 *
 * Created on 6 August 2005, 11:53
 */

package funsoftware.ic.others;

import funsoftware.ic.*;
import funsoftware.wr.*;

/**
 * This class is for the special icon joinEndBranchIcon that connects a branch icon with its right neighbour. 
 * @author Thomas Legowo
 * 
 */
public class joinEndBranchIcon extends Icon{
    
    // the private attributes
    private Icon leftNeighbourTop;
    private Icon leftNeighbourBottom;
    private Icon rightNeighbour;
    
    private Icon branchIcon;  // the if icon that this end branch is used for
    
    private Wire leftWireTop;
    private Wire leftWireBottom;
    private Wire rightWire;    
    
    /** Creates a new instance of joinEndBranchIcon */
    public joinEndBranchIcon() {
    }
    
    /**
     * Creates a new instance of joinEndBranchIcon
     * @param filepath Source file of this icon's image
     */
    public joinEndBranchIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); // for startIcon the size of the icon is 26x61
        setImage();
    }
    
    // The get and set methods
    /**
     * Gets Left neighbour top icon
     * @return Left neighbour top icon
     */
    public Icon getLeftNeighbourTop()
    {
        return leftNeighbourTop;
    }
    
    /**
     * Gets Left neighbour bottom icon
     * @return Left neighbour bottom icon
     */
    public Icon getLeftNeighbourBottom()
    {
        return leftNeighbourBottom;
    }
    
    /**
     * Gets the right neighbour icon
     * @return Right neighbour icon
     */
    public Icon getRightNeighbour()
    {
        return rightNeighbour;
    }
    
    /**
     * Gets the branch icon that this icon is acting as the end branch icon for.
     * @return Branch icon
     */
    public Icon getBranchIcon()
    {
        return branchIcon;
    }
    
    /**
     * Gets the left wire top
     * @return Left wire top
     */
    public Wire getLeftWireTop()
    {
        return leftWireTop;
    }
    
    /**
     * Gets the left wire bottom
     * @return Left wire bottom
     */
    public Wire getLeftWireBottom()
    {
        return leftWireBottom;
    }
    
    /**
     * Gets the right wire
     * @return Right wire
     */
    public Wire getRightWire()
    {
        return rightWire;
    }
    
    /**
     * Sets the left neighbour top icon of this icon
     * @param nicon Left neighbour top icon
     */
    public void setLeftNeighbourTop(Icon nicon)
    {
        leftNeighbourTop = nicon;
    }
    
    /**
     * Sets the left neighbour bottom icon of this icon
     * @param nicon Left neighbour bottom
     */
    public void setLeftNeighbourBottom(Icon nicon)
    {
        leftNeighbourBottom = nicon;
    }
    
    /**
     * Sets the right neighbour icon of this icon
     * @param nicon Right neighbour icon
     */
    public void setRightNeighbour(Icon nicon)
    {
        rightNeighbour = nicon;
    }
    
    /**
     * Sets the branch icon that this icon is acting as the end branch icon for.
     * @param nicon Branch icon
     */
    public void setBranchIcon(Icon nicon)
    {
        branchIcon = nicon;
    }
    
    /**
     * Sets the left wire top
     * @param nwire Left wire top
     */
    public void setLeftWireTop(Wire nwire)
    {
        leftWireTop = nwire;
    }
    
    /**
     * Sets the left wire bottom
     * @param nwire Left wire bottom
     */
    public void setLeftWireBottom(Wire nwire)
    {
        leftWireBottom = nwire;
    }
    
    /**
     * Sets the right wire
     * @param nwire Right wire
     */
    public void setRightWire(Wire nwire)
    {
        rightWire = nwire;
    }
    
    /**
     * Set the image of this icon
     */
    public void setImage()
    {
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());
        
        // creating the top panel
        javax.swing.ImageIcon nic;
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/other/join.gif"));
        PicButton pic = new PicButton(nic);
        add(pic);
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public joinEndBranchIcon Clone()
    {
        return new joinEndBranchIcon("/icons/other/join.gif");
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
