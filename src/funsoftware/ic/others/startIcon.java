/*
 * startIcon.java
 *
 * Created on 29 December 2005, 16:24
 */

package funsoftware.ic.others;

import funsoftware.ic.*;
import funsoftware.inter.*;
import funsoftware.wr.*;

/**
 * This class caters for all kind of start icons in event monitors, functions and tasks
 * @author Thomas Legowo
 */
public class startIcon extends Icon{
    
    // the icons enclosed within this repeat segment
    private java.util.Vector<Icon> members = new java.util.Vector<Icon>();;

    // for deletions, startIcon needs to know which panel it is on
    // due to all its member icons pointing to this startIcon
    private ProgWindow prog_panel;
    private javax.swing.JScrollPane scroller;
    
    // the private attributes
    private Icon rightNeighbour;
    private Wire rightWire;
    
    /** Creates a new instance of startIcon */
    public startIcon() {
    }    
    
    /**
     * Creates a new instance of startIcon
     * @param filepath Source file of the icon's image
     */
    public startIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this));
        setImage();
        prog_panel = new ProgWindow();
    }
    
    // for getting and setting the programming window and its scroller
    /**
     * Gets the programming window this starting icon is associated to
     * @return Programming window
     */
    public ProgWindow getProgWindow()
    {
        return prog_panel;
    }
    
    /**
     * Sets the programming window this starting icon is associated to
     * @param pw New programming window
     */
    public void setProgWindow(ProgWindow pw)
    {
        prog_panel = pw;
    }
    
    /**
     * Gets scrollers of the programming window
     * @return A scroll pane
     */
    public javax.swing.JScrollPane getScroller()
    {
        return scroller;
    }
    
    /**
     * Sets scrollers of the programming window
     * @param jsp A scroll pane
     */
    public void setScroller(javax.swing.JScrollPane jsp)
    {
        scroller = jsp;
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
     * Gets right wire of this icon
     * @return Right wire 
     */    
    public Wire getRightWire()
    {
        return rightWire;
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
     * Sets right wire of this icon
     * @param nwire New right wire
     */    
    public void setRightWire(Wire nwire)
    {
        rightWire = nwire;
    }
    
    /**
     * Get the members of this start icon
     * @return Set of icons
     */
    public java.util.Vector<Icon> getMembers()
    {
        return members;
    }
    
    /**
     * Adds a new icon to this icon
     * @param new_icon New icon
     * @param right_icon Right icon to the new icon
     */
    public void addMember(Icon new_icon, Icon right_icon)
    {
        int found = 0;
        for(int i=0; i<members.size(); i++)
        {
            Icon c = members.get(i);
            if(c.getId() == right_icon.getId())
            {
                members.add(i, new_icon);
                found = 1;
                break;
            }
        }
        if(found == 0)
        {
            members.addElement(new_icon);
        }
    }
    
    /**
     * Deletes an icon of this icon's member list
     * @param icon Icon to be deleted
     */
    public void delMember(Icon icon)
    {
        for(int i=0; i<members.size(); i++)
        {
            Icon c = members.get(i);
            if(c.getId() == icon.getId())
            {
                members.remove(i);
                break;
            }
        }
    }
    
    /**
     * Sets the image of this icon
     */
    public void setImage()
    {
        PicButton pic = new PicButton(super.getImage());
        setLayout(new java.awt.GridBagLayout());
        add(pic);
        setPreferredSize(new java.awt.Dimension(40, 40));
    }
}
