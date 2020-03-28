/*
 * branchIcon.java
 *
 * Created on 5 September 2005, 01:08
 *
 */

package funsoftware.ic.bran;

import funsoftware.ic.*;
import funsoftware.ic.loo.*;
import funsoftware.wr.*;

/**
 * This method is a branch type of icons.
 * All kinds of branchIcon extends Icon
 * @author Thomas Legowo
 */
public class branchIcon extends Icon{
    
    // the private attributes
    private Icon rightNeighbourTop;
    private Icon rightNeighbourBottom;
    
    private Icon rightNeighbour;   // since a branch is regarded as one entity, the right Neighbour 
                                   // is the right neighbour of its end branch
    private Icon leftNeighbour;
    
    // need to point at the end of the branch
    private Icon endBranch;
    
    private Wire leftWire;
    // branches
    private Wire rightWireTop;
    private Wire rightWireBottom;
    
    // variables for the icon's vertical repositioning
    private int toptopVerticalSize;
    private int topbottomVerticalSize;
    
    private int bottomtopVerticalSize;
    private int bottombottomVerticalSize;
        
    private java.util.Vector<Icon> if_members;   // the icons enclosed within this branch
    private java.util.Vector<Icon> else_members;   // the icons enclosed within this branch
    
    /** Creates a new instance of branchIcon */
    public branchIcon() {
    }
    
    /**
     * Creates a new instance of branchIcon
     * @param filepath Source file for the image of this branch typed icon
     */
    public branchIcon(String filepath) {
        super(filepath);
        toptopVerticalSize = 0;      // initial value = 0
        topbottomVerticalSize = 0;   // initial value = 0
        bottomtopVerticalSize = 0;      // initial value = 0
        bottombottomVerticalSize = 0;   // initial value = 0
        if_members = new java.util.Vector<Icon>();
        else_members = new java.util.Vector<Icon>();
    }
    
    // The get and set methods
    
    /**
     * Gets right neighbour top
     * @return Right neighbour top
     */
    public Icon getRightNeighbourTop()
    {
        return rightNeighbourTop;
    }
        
    /**
     * Gets the right neighbour bottom
     * @return Right neighbour bottom
     */
    public Icon getRightNeighbourBottom()
    {
        return rightNeighbourBottom;
    }
    
    /**
     * Gets the right neighbour of this icon
     * @return Right neighbour
     */
    public Icon getRightNeighbour()
    {
        return rightNeighbour;
    }
    
    /**
     * Gets the left neighbour of this icon
     * @return Left neighbour
     */
    public Icon getLeftNeighbour()
    {
        return leftNeighbour;
    }
    
    /**
     * Gets the left wire of this icon
     * @return Left wire
     */
    public Wire getLeftWire()
    {
        return leftWire;
    }
    
    /**
     * Gets the right wire top of this icon
     * @return Right wire top
     */
    public Wire getRightWireTop()
    {
        return rightWireTop;
    }
    
    /**
     * Gets the right wire bottom of this icon
     * @return Right wire bottom
     */
    public Wire getRightWireBottom()
    {
        return rightWireBottom;
    }
       
    /**
     * Sets the right neighbour icon top
     * @param nicon New right neighbour icon top
     */
    public void setRightNeighbourTop(Icon nicon)
    {
        rightNeighbourTop = nicon;
    }   
    
    /**
     * Sets the right neighbour icon bottom
     * @param nicon New right neighbour icon bottom
     */
    public void setRightNeighbourBottom(Icon nicon)
    {
        rightNeighbourBottom = nicon;
    }
    
    /**
     * Sets the right neighbour of this icon
     * @param nicon Right neighbour
     */
    public void setRightNeighbour(Icon nicon)
    {
        rightNeighbour = nicon;
    }
    
    /**
     * Sets the left neighbour of this icon
     * @param nicon Left neighbour
     */
    public void setLeftNeighbour(Icon nicon)
    {
        leftNeighbour = nicon;
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
     * Sets the right wire top of this icon
     * @param nwire New right wire top
     */
    public void setRightWireTop(Wire nwire)
    {
        rightWireTop = nwire;
    }
    
    /**
     * Sets the right wire bottom of this icon
     * @param nwire New right wire bottom
     */
    public void setRightWireBottom(Wire nwire)
    {
        rightWireBottom = nwire;
    }
    
    // dealing with the joinEndBranchIcon of this icon
    /**
     * Gets the endbranch icon of this branch icon
     * @return End branch icon
     */
    public Icon getEndBranch()
    {
        return endBranch;
    }
    
    /**
     * Sets the end branch icon of this branch icon
     * @param neb New end branch
     */
    public void setEndBranch(Icon neb)
    {
        endBranch = neb;
    }
    
    // the top and bottom vertical size methods
    /**
     * Gets the top of the branch's top part vertical size
     * @return Top of the branch's top part vertical size
     */
    public int getTopTopVerticalSize()
    {
        return toptopVerticalSize;
    }
    
    /**
     * Gets the bottom of the branch's top part vertical size
     * @return Bottom of the branch's top part vertical size
     */
    public int getTopBottomVerticalSize()
    {
        return topbottomVerticalSize;
    }
    
    /**
     * Gets the top of the branch's bottom part vertical size
     * @return Top of the branch's bottom part vertical size
     */
    public int getBottomTopVerticalSize()
    {
        return bottomtopVerticalSize;
    }
    
    /**
     * Gets the bottom of the branch's bottom part vertical size
     * @return Bottom of the branch's bottom part vertical size
     */
    public int getBottomBottomVerticalSize()
    {
        return bottombottomVerticalSize;
    }
    
    /**
     * Sets the top of the branch's top part vertical size
     * @param ns Vertical size
     */
    public void setTopTopVerticalSize(int ns)
    {
        toptopVerticalSize = ns;
    }
    
    /**
     * Sets the bottom of the branch's top part vertical size
     * @param ns Vertical size
     */
    public void setTopBottomVerticalSize(int ns)
    {
        topbottomVerticalSize = ns;
    }
    
    /**
     * Sets the top of the branch's bottom part vertical size
     * @param ns Vertical size
     */
    public void setBottomTopVerticalSize(int ns)
    {
        bottomtopVerticalSize = ns;
    }
    
    /**
     * Sets the bottom of the branch's bottom part vertical size
     * @param ns Vertical size
     */
    public void setBottomBottomVerticalSize(int ns)
    {
        bottombottomVerticalSize = ns;
    }
    
    // this function will never return null (if icon does not exist) since the methods that called this method would
    // have checked whether this icon exists in one of the branches anyway
    /**
     * Determines whether an icon is in the if part of else part of this branch
     * @param icon Icon
     * @return Value = 0 means icon in if part; Value = 1 means icon in else part
     */
    public int determineMember(Icon icon)
    {
        int ret_val = 0;
        Icon tmp_ic;
        // do the if_branch first
        for(int i=0; i<if_members.size(); i++)
        {
            tmp_ic = if_members.get(i);
            if(tmp_ic.getId() == icon.getId())
            {
                ret_val = 0;
                break;
            }
        }
        
        // now do the else branch
        for(int i=0; i<else_members.size(); i++)
        {
            tmp_ic = else_members.get(i);
            if(tmp_ic.getId() == icon.getId())
            {
                ret_val = 1;
                break;
            }
        }
        
        return ret_val;
    }    
    
    // dealing with the elements within the if part and else part
    /**
     * Get icons in the top(if) part of the branch
     * @return Set of icons
     */
    public java.util.Vector<Icon> getIfMembers()
    {
        return if_members;
    }
    
    /**
     * Get icons in the bottom(else) part of the branch
     * @return Set of icons
     */
    public java.util.Vector<Icon> getElseMembers()
    {
        return else_members;
    }
    
    /**
     * Add a new member into this branch icon
     * @param new_icon New icon
     * @param right_icon Icon to the right of the new icon
     */
    public void addMember(Icon new_icon, Icon right_icon)
    {
        int ypos = new_icon.getCoordinate().getY();
        int yb = new_icon.getParentIcon().getCoordinate().getY(); 
        if(ypos > yb)   // add element to else
        {
            addToVect(new_icon, right_icon, 1);
        }
        else            // add element to if
        {
            addToVect(new_icon, right_icon, 0);
        }
    }
    
    /**
     * Delete an icon off the membership list of this branch icon
     * @param icon Icon
     */
    public void delMember(Icon icon)
    {
        int ypos = icon.getCoordinate().getY();
        int yb = icon.getParentIcon().getCoordinate().getY();
        
        if(ypos > yb)   // delete element from else
        {
            for(int i=0; i<else_members.size(); i++)
            {
                Icon c = else_members.get(i);
                if(c.getId() == icon.getId())
                {
                    else_members.remove(i);
                    break;
                }
            }
        }
        else            // delete element from if
        {
            for(int i=0; i<if_members.size(); i++)
            {
                Icon c = if_members.get(i);
                if(c.getId() == icon.getId())
                {
                    if_members.remove(i);
                    break;
                }
            }
        }
    }

    /**
     * Delete all members of this branch's list
     */
    public void delAllMembers()
    {
        if_members = new java.util.Vector<Icon>();
        else_members = new java.util.Vector<Icon>();
    }
    
    // type = 0, add to if, type = 1, add to else
    private void addToVect(Icon new_icon, Icon right_icon, int type)
    {
        int found=0;
        if(type == 0)
        {
            for(int i=0; i<if_members.size(); i++)
            {
                Icon c = if_members.get(i);
                if(c.getId() == right_icon.getId())
                {
                    if_members.add(i, new_icon);
                    found = 1;
                    break;
                }
            }
            if(found == 0)
            {
                if_members.addElement(new_icon);
            }
        }
        else if(type == 1)
        {
            for(int i=0; i<else_members.size(); i++)
            {
                Icon c = else_members.get(i);
                if(c.getId() == right_icon.getId())
                {
                    else_members.add(i, new_icon);
                    found = 1;
                    break;
                }
            }
            if(found == 0)
            {
                else_members.addElement(new_icon);
            }
        }
    }
    
    // the following two methods --> tallestIfMember and tallestElseMember are to be used only to determine
    // whether it is appropriate to collapse a branch inward
    // similar to the ones in repeat, but need to do it twice for if and else part of the branch
    /**
     * Gets the icon with the greatest TopBottom vertical size
     * @return Icon with the greatest TopBottom vertical size
     */
    public Icon tallestIfMemberBottom()
    {
        Icon ret_ic = new Icon();
        int max_ver = -1;
        for(int i = 0; i<if_members.size(); i++)
        {
            Icon tmp_ic = if_members.get(i); 
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
    
    /**
     * Gets the icon with the greatest TopTop vertical size
     * @return Icon with the greatest TopTop vertical size
     */
    public Icon tallestIfMemberTop()
    {
        Icon ret_ic = new Icon();
        int max_ver = -1;
        for(int i = 0; i<if_members.size(); i++)
        {
            Icon tmp_ic = if_members.get(i); 
            int vert = 0;   // repeat is useless here
            if(tmp_ic instanceof branchIcon)
            {
                vert = tmp_ic.getTopTopVerticalSize()+tmp_ic.getTopBottomVerticalSize()+1;
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
    
    /**
     * Gets the icon with the greatest BottomTop vertical size
     * @return Icon with the greatest BottomTop vertical size
     */
    public Icon tallestElseMemberTop()
    {
        Icon ret_ic = new Icon();
        int max_ver = -1;
        // now iterate through the else branch
        for(int i = 0; i<else_members.size(); i++)
        {
            Icon tmp_ic = else_members.get(i);
            
            int vert = 0;   // repeat is useless here
            if(tmp_ic instanceof branchIcon)
            {
                vert = tmp_ic.getTopTopVerticalSize()+tmp_ic.getTopBottomVerticalSize()+1;
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
    
    /**
     * Gets the icon with the greatest BottomBottom vertical size
     * @return Icon with the greatest BottomBottom vertical size
     */
    public Icon tallestElseMemberBottom()
    {
        Icon ret_ic = new Icon();
        int max_ver = -1;
        // now iterate through the else branch
        for(int i = 0; i<else_members.size(); i++)
        {
            Icon tmp_ic = else_members.get(i);
            
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
    
    
}
