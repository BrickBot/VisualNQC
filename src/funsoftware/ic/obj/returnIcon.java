/*
 * returnIcon.java
 *
 * Created on 1 February 2006, 13:36
 *
 */

package funsoftware.ic.obj;

import funsoftware.ic.*;

/**
 * This icon simply breaks off early from a function. Can be used in event monitor, function or task.
 * @author Thomas Legowo
 */
public class returnIcon extends objectIcon{
    
    // for algorithm representation, represent a break icon with an identifier of 16
    private int identifier;    
    
    /** Creates a new instance of returnIcon */
    public returnIcon() {
    }
    
    /**
     * Creates a new instance of returnIcon
     * @param filepath The source file of this icon's image
     */
    public returnIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        identifier = 16;
        setImage();
    }    

    /**
     * To set the image of this icon
     */
    public void setImage()
    {
        removeAll();       
        setLayout(new java.awt.GridBagLayout());
        
        // creating the top panel
        javax.swing.ImageIcon nic;
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/structure/return/return.gif"));
        PicButton pic = new PicButton(nic);
        add(pic);        
        setPreferredSize(new java.awt.Dimension(40, 40));
    }
    
    
    /**
     * Get the help title of this return icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Return Icon";
        return s;
    }
    
    /**
     * Get help message of this return icon
     * @return Help message
     */    
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="This icon has no attributes";
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */    
    public String getHelpDesc()
    {
        String s = "";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/returnIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public returnIcon Clone()
    {
        return new returnIcon("/icons/structure/return/return.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        returnIcon newRI = Clone();        
        newRI.setImage();
        return newRI;
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
        algo += "\n";  // to begin with
        algo += "object";
        algo += id_separator;
        algo += identifier;
        algo += id_att_separator;
        algo += att_boundary_begin;
        algo += att_boundary_end;
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

        NQCCode += indentation+"// return from this function\n";
        NQCCode += indentation+"return;\n\n";
        return NQCCode;
    }
    
    /**
     * Set its own attributes given a string from a text representation of it
     * @param att Attributes
     * @param att_value_connector Attribute connector
     * @param att_separator Attribute separator
     */
    public void setAttributes(String att, String att_value_connector, String att_separator)
    {
    }    
}
