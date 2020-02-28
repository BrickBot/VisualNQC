/*
 * BrandomIcon.java
 *
 * Created on 30 January 2006, 16:56
 *
 */

package funsoftware.ic.bran;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.st.*;

/**
 * This icon handles a branch that takes a path depending on the a random number. (Branch randomly)
 * @author Thomas Legowo
 */
public class BrandomIcon extends branchIcon{
        
    // for algorithm representation, represent a branch random icon with an identifier of 6
    private int identifier;
        
    /**
     * Creates a new instance of BrandomIcon
     */
    public BrandomIcon() {
    }
    
    /**
     * Creates a new instance of BrandomIcon
     * @param filepath The source file of this icon's graphic
     */
    public BrandomIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        identifier = 6;
        setImage();
    }    
    
    /**
     * Set the image of this icon
     */
    public void setImage()
    {
        removeAll();
        setLayout(new java.awt.GridBagLayout());
        javax.swing.ImageIcon nic;
        PicButton pic;               
                
        // add le stuffs into le jp
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/branches/random/random.gif"));
        pic = new PicButton(nic);
        add(pic);                        
        
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(40, 40));
    }    
    

    /**
     * Get help title of this icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Branch Random Icon";
        return s;
    }
    
    /**
     * Get help message of this icon
     * @return Help message
     */
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="This icon has no attributes";
        return s;
    }
    
    /**
     * Get help description of this icon
     * @return Help description
     */
    public String getHelpDesc()
    {
        String s = "This icon acts as a branch.\nIt chooses one between two paths\ndepending on a random number\n";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/BrandomIcon.gif"));
        return ii;
    }

    /**
     * To clone this icon 
     * @return New instance of this icon
     */
    public BrandomIcon Clone()
    {
        return new BrandomIcon("/icons/branches/random/random.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        BrandomIcon newWTI = Clone();        
        newWTI.setImage();
        return newWTI;
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
        algo += "branch";
        algo += id_separator;
        algo += identifier;
        algo += id_separator;
        algo += getId();
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
        String input_port = new String();
        
        NQCCode += indentation;
        NQCCode += "// random branch \n";
        NQCCode += indentation;
        NQCCode += "if(Random(1) == 1)\n";
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
