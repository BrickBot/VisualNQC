/*
 * loopIcon.java
 *
 * Created on 5 September 2005, 10:43
 *
 */

package funsoftware.ic.loo;

import funsoftware.ic.*;
import funsoftware.ic.bran.*;
import funsoftware.wr.*;

/**
 * This class is the superclass for all kind of repeat or loop icons
 * @author Thomas Legowo
 */
public class loopIcon extends Icon{
    
    // the private attributes
    
    private Wire leftWire;
    private Wire rightWire;
    
    private Icon beginIcon; // the joinLoop icon that starts the loop segment
    private Icon endIcon;   // the joinLoop icon that ends the loop segment 
    
    private Icon leftNeighbour;   
    private Icon rightNeighbour;  
    
    private java.util.Vector<Icon> members;   // the icons enclosed within this repeat segment
    
   // variables for the icon's vertical repositioning
    private int topVerticalSize;
    
    private int bottomVerticalSize;
    
    /** Creates a new instance of loopIcon */
    public loopIcon() {
    }
    
    /**
     * Creates a new instance of loopIcon
     * @param filepath Source file of this icon's image
     */
    public loopIcon(String filepath) {
        super(filepath);
        members = new java.util.Vector<Icon>();
        topVerticalSize = 0;
        bottomVerticalSize = 0;
    }
    
    // the set and get methods for the neighbouring wires
    
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
     * Gets the begin icon of the segment of icons enclosed by this loop icon
     * @return Begin icon
     */
    public Icon getBeginIcon()
    {
        return beginIcon;
    }
    
    /**
     * Gets the end icon of the segment of icons enclosed by this loop icon
     * @return End icon
     */
    public Icon getEndIcon()
    {
        return endIcon;
    }
    
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
    
    /**
     * Sets the begin icon of this loop icon
     * @param nicon New begin icon
     */
    public void setBeginIcon(Icon nicon)
    {
        beginIcon = nicon;
    }
    
    /**
     * Sets the end icon of this loop icon
     * @param nicon New end icon
     */
    public void setEndIcon(Icon nicon)
    {
        endIcon = nicon;
    }
    
    /**
     * Ensure its left neighbour of this icon is the left of begin icon
     */
    public void setLeftNeighbour()
    {
        leftNeighbour = beginIcon.getLeftNeighbour();
    }
    
    /**
     * Ensure its right neighbour of this icon is the right of begin icon
     */    
    public void setRightNeighbour()
    {
        rightNeighbour = endIcon.getRightNeighbour();
    }
    
    // dealing with the elements within the loop
    /**
     * Gets the members of this loop icon
     * @return Set of icons
     */
    public java.util.Vector<Icon> getMembers()
    {
        return members;
    }
    
    // the repositioning variables
    /**
     * Sets the top part of the icon's vertical size
     * @param nvs New top part of vertical size
     */
    public void setTopVerticalSize(int nvs)
    {
        topVerticalSize = nvs;
    }
    
    /**
     * Sets the bottom part of the icon's vertical size
     * @param nvs New bottom part of vertical size
     */
    public void setBottomVerticalSize(int nvs)
    {
        bottomVerticalSize = nvs;
    }
    
    /**
     * Gets the top part of the icon's vertical size
     * @return Top part of the vertical size
     */
    public int getTopVerticalSize()
    {
        return topVerticalSize;
    }
    
    /**
     * Gets the bottom part of the icon's vertical size
     * @return Bottom part of the vertical size
     */
    public int getBottomVerticalSize()
    {
        return bottomVerticalSize;
    }

    
    /**
     * Adds a new icon to this icon member list
     * @param new_icon New icon
     * @param right_icon Right icon of the new icon
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
     * Deletes an icon of this icon
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
     * Deletes all members of this loop's list
     */
    public void delAllMembers()
    {
        members = new java.util.Vector<Icon>();
    }
    
    // a method to determine the tallest bottom member, needed to maintain uniform vertical distance between the icons, branches and repeats
    // NOTE: this method if called by the addMember or delMember compared to when it is called by ProgWindow.expandBranchVertical
    // will not differ in terms of performance since those method will be called whenever an icon is added or deleted anyway
    // --
    // since this method will be invoked after the latest icon is added to the members vector, that latest icon must be excluded
    // from the calculation
    /**
     * Gets the icon with greatest bottom vertical size
     * @return Icon with greatest bottom vertical size
     */
    public Icon tallestBottomMember()
    {
        Icon ret_ic = new Icon();
        int max_ver = -1;
        for(int i = 0; i<members.size(); i++)
        {
            Icon tmp_ic = members.get(i);
            // the vertical size of a branch icon needed for a repeat icon vertical repositioning
            // will be its bottomtopVerticalSize + bottombottomVerticalSize + 1
            int vert = tmp_ic.getVerticalSize();
            if(tmp_ic instanceof branchIcon)
            {
                vert = tmp_ic.getBottomTopVerticalSize()+tmp_ic.getBottomBottomVerticalSize()+1;
            }
            else if(tmp_ic instanceof loopIcon)
            {
                vert = tmp_ic.getBottomVerticalSize();
            }

            if(vert>max_ver)
            {
                max_ver = vert;
                ret_ic = tmp_ic;
            }    
        }        
        return ret_ic;
    }
    
    // the opposite method of the previous method
    /**
     * Gets the icon with greatest top vertical size
     * @return Icon with greatest top vertical size
     */
    public Icon tallestTopMember()
    {
        Icon ret_ic = new Icon();
        int max_ver = -1;
        for(int i = 0; i<members.size(); i++)
        {
            Icon tmp_ic = members.get(i);
            // the vertical size of a branch icon needed for a repeat icon vertical repositioning
            // will be its bottomtopVerticalSize + bottombottomVerticalSize + 1
            int vert = tmp_ic.getVerticalSize();
            if(tmp_ic instanceof branchIcon)
            {
                vert = tmp_ic.getBottomTopVerticalSize()+tmp_ic.getBottomBottomVerticalSize()+1;
            }
            else if(tmp_ic instanceof loopIcon)
            {
                vert = tmp_ic.getTopVerticalSize();
            }
            if(vert>max_ver)
            {
                max_ver = vert;
                ret_ic = tmp_ic;
            }    
        }        
        return ret_ic;
    }
}
