/*
 * ProgWindow.java
 *
 * Created on 26 July 2005, 23:00
 */

package funsoftware.inter;

import funsoftware.consts.*;
import funsoftware.pallette.*;
import funsoftware.wr.*;
import funsoftware.ic.*;
import funsoftware.ic.obj.*;
import funsoftware.ic.bran.*;
import funsoftware.ic.loo.*;
import funsoftware.ic.others.*;
import funsoftware.ic.func.*;
import funsoftware.gri.*;
import funsoftware.functs.*;
import funsoftware.ic.ev.*;
import funsoftware.ic.tsk.*;
import funsoftware.ic.func.*;

/**
 * This class will be the panel of the programming window
 * @author Thomas Legowo
 */
public class ProgWindow extends javax.swing.JPanel implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener{
    
    private wires wire_list; // the wires to be refreshed on the programming window
    private icons_list icon_list; // the icons to be refreshed on the programming window
    private aux_list func_list;   // list of functions
     
    private java.awt.Dimension area;   // the area of the panel
        
    // list containing wires, icons and errorboxes belonging to this window
    private java.util.Vector<Wire> win_wire;
    private java.util.Vector<Icon> win_icon;
    
    
//    private int gridStatus;   // the status of the grid
    
    private TitlePanelLabel jDirectionsLabel;   // the label that provides real time help for the user

    // for the pop up menu
    private javax.swing.JPanel jpm;
    
    // private variables for beginning and end icon of this programming window
    private startIcon start_ic;
    private endIcon end_ic;
    
    // for zooming in and out
    private double zoom_scale;    
    
    /** Creates a new instance of ProgWindow */
    public ProgWindow() {
    }
    
    /**
     * Creates a new instance of ProgWindow
     * @param njDirectionsLabel The real time direction label
     */
    public ProgWindow(TitlePanelLabel njDirectionsLabel)
    {
        super(new java.awt.GridBagLayout());

        area = new java.awt.Dimension(Constants.maxHorizontalProgWindow, Constants.maxVerticalProgWindow);
        super.setPreferredSize(area);
        
        jDirectionsLabel = njDirectionsLabel;

        jpm = new javax.swing.JPanel();
        init_listener();
             
        win_wire = new java.util.Vector<Wire>();
        win_icon = new java.util.Vector<Icon>();
       
        zoom_scale = 1;
        
        // initialise the icons, wires, error boxes and functions lists
        icon_list = icons_list.Instance();
        wire_list = wires.Instance();
        
        func_list = aux_list.Instance();
        setBackground(new java.awt.Color(255,255,255));
    }
    
    /**
     * To paint this programming window
     * @param g Graphics
     */
    protected void paintComponent(java.awt.Graphics g) 
    {
        super.paintComponent(g);
        
        if(Constants.getGridStatus() == 0)
        {
            g.setColor(new java.awt.Color(255,255,255));
        } 
            
        // draw the grid
        paint_grid(g);
            
        // draw the wires            
        for(int i=0; i<win_wire.size();i++)
        {
            Wire tmp_wire = win_wire.get(i);
            tmp_wire.draw(g);
        }            
    }

    /**
     * Change the size of this panel
     * @param nArea New area
     */
    public void setArea(java.awt.Dimension nArea)
    {
        area = nArea;
        setPreferredSize(area);
        setSize(area);
        repaint();
        revalidate();
    }
    
    // to addWires to win_wire list, keep in mind that win_wire list and wire_list are not the same
    /**
     * To add wire to the wire list of this panel
     * @param wire New wire
     */
    public void addWire(Wire wire)
    {
        win_wire.addElement(wire);
    }
    
    /**
     * To delete a piece of wire from this programming window
     * @param wire Deleted wire
     */
    public void deleteWire(Wire wire)
    {
        for(int i=0; i<win_wire.size(); i++)
        {
            if(win_wire.get(i).getId() == wire.getId())
            {
                win_wire.removeElementAt(i);
                break;
            }
        }
    }
    // to get wires in the resizing method within the window class
    /**
     * Get all wires on this programming window
     * @return Set of wires
     */
    public java.util.Vector<Wire> getWires()
    {
        return win_wire;
    }
    
    /**
     * Reset the wiring list of this programming window
     */
    public void resetWires()
    {
        win_wire = new java.util.Vector<Wire>();
    }
    
    // to addIcons to win_icon list
    /**
     * To add an icon to the icon list of this panel
     * @param icon New icon
     */
    public void addIcon(Icon icon)
    {
        win_icon.addElement(icon);
    }
    // to delete icons
    /**
     * To delete an icon off the icon list of this panel
     * @param icon Deleted icon
     */
    public void deleteIcon(Icon icon)
    {
        for(int i=0; i<win_icon.size(); i++)
        {
            if(win_icon.get(i).getId() == icon.getId())
            {
                win_icon.removeElementAt(i);
                break;
            }
        }
    }
    
    /**
     * Get the set of icons within this programming window
     * @return Set of icons
     */
    public java.util.Vector<Icon> getIcons()
    {
        return win_icon;
    }
    
    /**
     * Reset the icon list of this programming window
     */
    public void resetIcons()
    {
        win_icon = new java.util.Vector<Icon>();
    }


    /**
     * Set the start icon of this particular programming window
     * @param ic New start icon
     */
    public void setStartIcon(startIcon ic)
    {
        start_ic = ic;
    }
    /**
     * Get the start icon of this programming window
     * @return Start icon
     */
    public startIcon getStartIcon()
    {
        return start_ic;
    }
    /**
     * Set the end icon of this programming window
     * @param ic End icon
     */
    public void setEndIcon(endIcon ic)
    {
        end_ic = ic;
    }
    /**
     * Get the end icon of this programming window
     * @return End icon
     */
    public endIcon getEndIcon()
    {
        return end_ic;
    }
    
    
    /**
     * Paints the grid of the programming window
     * @param g Graphics
     */
    public void paint_grid(java.awt.Graphics g)
    {               
        // get the width and height of the panel
        int height = (int)area.getHeight();
        int width = (int)area.getWidth(); 
        
        int distanceX = Constants.gridDistanceX;
        int distanceY = Constants.gridDistanceY;
        if(Constants.getGridStatus() == 1)
        {
           //start drawing the lines (the grid lines are separated every 50 pixels)
            g.setColor(new java.awt.Color(0,0,0,30));
        
            for(int i = 0; i < height; i += distanceY)
            {
                g.drawLine(0, i, width, i);
            }
            for(int i = 0; i < width; i += distanceX)
            {
                g.drawLine(i, 0, i, height);
            }
        }
        else if(Constants.getGridStatus() == 0)
        {
            g.clearRect(0,0, width,height);            
        }
    }
    
    /**
     * Initialise the mouse listeners of this programming window
     */
    public void init_listener()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    
    // ----------------------------------
    
    // AUXILIARY METHODS FOR ICON EDITING -- ADDITION, DELETION and EDITION --> Icons and Wires
    
    /**
     * This method splits up a wire as one icon is introduced in between a pair of icons that that wire is connected to
     * @param new_icon Newly added icon
     * @param old_icon Already added icon next to it
     */
    public void splitWire(Icon new_icon, Icon old_icon)
    {
        Wire old_wire = old_icon.getLeftWire();
        
        // Reconnect the new and old icons with the newer piece of wire and the older piece of wire

        new_icon.setLeftWire(old_wire); 
        if(new_icon.getParentIcon() instanceof branchIcon)
        {
            old_wire.setType(1);
        }
        else if(new_icon.getParentIcon() instanceof loopIcon)
        {
            old_wire.setType(2);
        }
        else
        {
            old_wire.setType(0);
        }

        old_wire.setRightIcon(new_icon);
        newWire(new_icon, old_icon, old_wire.getType());
    }
    

    /**
     * Introduces a new wiring between a pair of icons
     * @param left_icon Left icon to this piece of wire
     * @param right_icon Right icon to this piece of wire
     * @param type Type of that wire(for colouring purposes)
     */
    public void newWire(Icon left_icon, Icon right_icon, int type)
    {
        Wire wire = new Wire();
        wire.setLeftIcon(left_icon);
        left_icon.setRightWire(wire);
        wire.setRightIcon(right_icon);
        right_icon.setLeftWire(wire);
        wire.setType(type);
        wire_list.addWire(wire);
        // adding this wire on this window
        addWire(wire);
    }
    

    /**
     * This method sets up an icon with its bounds and coordinates in this programming panel
     * @param new_icon New icon
     * @param xpos X coordinate
     * @param ypos Y coordinate
     */
    public void setUpIcon(Icon new_icon, int xpos, int ypos)
    {
        coord co = gridcalculation.computeCoord(new_icon, new coord(xpos, ypos));
        new_icon.setCoordinate(co);
        new_icon.setBounds(co.getX(), co.getY(), new_icon.getPreferredSize().width, new_icon.getPreferredSize().height);
    }
    
    
    /**
     * This method collapse two pieces of wires into one, as an icon is deleted
     * @param left_icon Left icon
     * @param right_icon Right icon
     */
    public void collapseWire(Icon left_icon, Icon right_icon)
    {
        // get rid of the wire to the right of the deleted icon
        deleteWire(right_icon.getLeftWire());
        wire_list.delWire(right_icon.getLeftWire()); 
        
        Wire tmp_wire = left_icon.getRightWire();
        
        // update the other icon

        // set the wires and icons new connections
        left_icon.setRightWire(tmp_wire);
        right_icon.setLeftWire(tmp_wire);
        tmp_wire.setLeftIcon(left_icon);
        tmp_wire.setRightIcon(right_icon);        
    }
    
    /**
     * Extends the width of a repeat (simply shift the right end of the branch)
     * @param icon Icon
     * @param n_shift Number of shift
     */
    public void expandRepeatHorizontal(Icon icon, int n_shift)
    {
        moveIconHorizontal(icon.getEndIcon(), (n_shift*Constants.gridDistanceX));
    }
    
    /**
     * Collapse the repeat
     * @param icon Icon
     * @param n_shift Number of shift
     */
    public void collapseRepeatHorizontal(Icon icon, int n_shift)
    {
        moveIconHorizontal(icon.getEndIcon(), (n_shift*-Constants.gridDistanceX));
    }
    
    /**
     * Extends the width of a branch, simply shift the right most icons of the branch
     * @param icon Icon
     * @param n_shift Number of shift
     */
    public void expandBranchHorizontal(Icon icon, int n_shift)
    {
        moveIconHorizontal(icon.getEndBranch(), (n_shift*Constants.gridDistanceX));

        moveIconHorizontal(icon.getEndBranch().getLeftNeighbourTop(), (n_shift*Constants.gridDistanceX));

        moveIconHorizontal(icon.getEndBranch().getLeftNeighbourBottom(), (n_shift*Constants.gridDistanceX));        
    }
    
    /**
     * Collapse a branch
     * @param icon Icon 
     * @param n_shift Number of shift
     */
    public void collapseBranchHorizontal(Icon icon, int n_shift)
    {
        moveIconHorizontal(icon.getEndBranch(), (n_shift*-Constants.gridDistanceX));

        moveIconHorizontal(icon.getEndBranch().getLeftNeighbourTop(), (n_shift*-Constants.gridDistanceX));

        moveIconHorizontal(icon.getEndBranch().getLeftNeighbourBottom(), (n_shift*-Constants.gridDistanceX));
    }
    
    /**
     * Move a part of branch partially number of shift grid intersections
     * @param threshold Threshold icon
     * @param n_shift Number of shift
     */
    public void moveBranchPartial(Icon threshold, int n_shift)
    {
        Icon parent = threshold.getParentIcon();
        int pos = parent.determineMember(threshold);
        java.util.Vector<Icon> ics = new java.util.Vector<Icon>();
        if(pos == 0)
        {
            ics = parent.getIfMembers();
        }
        else if(pos == 1)
        {
            ics = parent.getElseMembers();
        }
        
        int dist = n_shift*Constants.gridDistanceX;
        
        int i=0;
        for(i=0; i<ics.size(); i++)
        {
            Icon tmp_ic = ics.get(i);
            if(tmp_ic.getId() == threshold.getId()) // the threshold's position was found
            {
                break;
            }
        }
        // now start moving the rest of the icons
        // keeping in mind to ignore the joinbranch icon of the branch
        i++; // skip the threshold
        for(int i2 = i; i2<(ics.size()-1); i2++)
        {
            Icon tmp_ic = ics.get(i2);
            
            if(tmp_ic instanceof branchIcon)
            {
                moveBranchHorizontal(tmp_ic, dist);
            }
            else if(tmp_ic instanceof loopIcon)
            {
                moveRepeatHorizontal(tmp_ic, dist);
            }
            else
            {
                moveIconHorizontal(tmp_ic, dist);
            }
        }        
    }
    

    /**
     * Move a part of repeat partially
     * @param threshold Threshold icon
     * @param n_shift Number of shift
     */
    public void moveRepeatPartial(Icon threshold, int n_shift)
    {
        Icon parent = threshold.getParentIcon();
        java.util.Vector<Icon> ics = parent.getMembers();
        
        int dist = (n_shift*Constants.gridDistanceX);
                
        int i=0;
        for(i=0; i<ics.size(); i++)
        {
            Icon tmp_ic = ics.get(i); 
            if(tmp_ic.getId() == threshold.getId()) // the threshold's position was found
            {
                break;
            }
        }
        i++;  // skip the threshold

        // now start moving the rest of the icons
        for(int i2 = i; i2<(ics.size()-1); i2++)
        {
            Icon tmp_ic = ics.get(i2);

            if(tmp_ic instanceof branchIcon)
            {
                moveBranchHorizontal(tmp_ic, dist);
            }
            else if(tmp_ic instanceof loopIcon)
            {
                moveRepeatHorizontal(tmp_ic, dist);
            }
            else
            {
                moveIconHorizontal(tmp_ic, dist);
            }
        }
    }
    
    
    /**
     * Move all the required segments to the right(method that combine the use of branch, repeat repositioning and expand branch and repeat)
     * @param icon Newly inserted icon
     * @param n_shift Number of shift
     */
    public void recursiveShiftIconHorizontalRight(Icon icon, int n_shift)
    {
        Icon parent = icon.getParentIcon(); 
        if(parent instanceof branchIcon)
        {
            if(getFreeSegment(parent, parent.determineMember(icon)) == -2)  // no more space in the parent
            {          
                expandBranchHorizontal(parent, n_shift);
                moveBranchPartial(icon, n_shift);
                recursiveShiftIconHorizontalRight(parent, n_shift);
            }
            else if(getFreeSegment(parent, parent.determineMember(icon)) == -1)
            {
                expandBranchHorizontal(parent, 1);               
                moveBranchPartial(icon, 1);
                recursiveShiftIconHorizontalRight(parent, 1);                    
            }
            else
            {     
                moveBranchPartial(icon, n_shift);
            }
        }
        else if(parent instanceof loopIcon)
        { 
            expandRepeatHorizontal(parent, n_shift);
            moveRepeatPartial(icon, n_shift);
            recursiveShiftIconHorizontalRight(parent, n_shift);            
        }
        else
        {
            if(icon instanceof branchIcon)
            {
                if(icon.getEndBranch().getRightNeighbour() instanceof joinLoopIcon)   // the special case
                {
                    repositionIcons(icon.getEndBranch().getRightNeighbour().getBottomNeighbour(), n_shift);
                }
                else
                {
                    repositionIcons(icon.getEndBranch().getRightNeighbour(), n_shift); 
                }
            }
            else if(icon instanceof loopIcon)
            {
                if(icon.getEndIcon().getRightNeighbour() instanceof joinLoopIcon)   // the special case
                {
                    repositionIcons(icon.getEndIcon().getRightNeighbour().getBottomNeighbour(), n_shift);
                }
                else
                {
                    repositionIcons(icon.getEndIcon().getRightNeighbour(), n_shift); 
                } 
            }
            else
            {
                if(icon.getRightNeighbour() instanceof joinLoopIcon)   // the special case
                {
                    repositionIcons(icon.getRightNeighbour().getBottomNeighbour(), n_shift);
                }
                else
                {
                    repositionIcons(icon.getRightNeighbour(), n_shift); 
                }                
            }            
        }
    }
    
    /**
     * Shift all icons to the left, will be called by a delete function
     * @param icon Icon just deleted
     * @param n_shift Number of shift
     */
    public void recursiveShiftIconHorizontalLeft(Icon icon, int n_shift)
    {
        Icon parent = icon.getParentIcon(); 
        if(parent instanceof branchIcon)
        {
            // decide of one of the branches is having more icons than the branch that just lost a member
            // if so, then do not collapse the branch
            int b_loc = parent.determineMember(icon);  // the location of the member
            int otherb_loc = 0;
            // now determine the amount of free segments from the other branch
            if(b_loc == 0)
            {
                otherb_loc = 1;
            }
            else if(b_loc == 1)
            {
                otherb_loc = 0;
            } 
            if(getFreeSegment(parent, otherb_loc) > 0)  // no more space in the parent
            {         
                if(getFreeSegment(parent, otherb_loc) < n_shift)
                {
                    n_shift = getFreeSegment(parent, otherb_loc);
                }
                collapseBranchHorizontal(parent, n_shift);
                moveBranchPartial(icon, -n_shift);
                recursiveShiftIconHorizontalLeft(parent, n_shift);
            }
            else if(getFreeSegment(parent, otherb_loc) <= 0)
            {
                moveBranchPartial(icon, -n_shift);                   
            }
        }
        else if(parent instanceof loopIcon)
        {
            collapseRepeatHorizontal(parent, n_shift);
            moveRepeatPartial(icon, -n_shift);
            recursiveShiftIconHorizontalLeft(parent, n_shift);            
        }
        else
        {
            if(icon instanceof branchIcon)
            {
                if(icon.getEndBranch().getRightNeighbour() instanceof joinLoopIcon)   // the special case
                {
                    repositionIcons(icon.getEndBranch().getRightNeighbour().getBottomNeighbour(), -n_shift);
                }
                else
                {
                    repositionIcons(icon.getEndBranch().getRightNeighbour(), -n_shift); 
                }
            }
            else if(icon instanceof loopIcon)
            {
                if(icon.getEndIcon().getRightNeighbour() instanceof joinLoopIcon)   // the special case
                {
                    repositionIcons(icon.getEndIcon().getRightNeighbour().getBottomNeighbour(), -n_shift);
                }
                else
                {
                    repositionIcons(icon.getEndIcon().getRightNeighbour(), -n_shift); 
                } 
            }
            else
            {
                if(icon.getRightNeighbour() instanceof joinLoopIcon)   // the special case
                {
                    repositionIcons(icon.getRightNeighbour().getBottomNeighbour(), -n_shift);
                }
                else
                {
                    repositionIcons(icon.getRightNeighbour(), -n_shift); 
                }                
            }            
        }
    }
    
    
    // the normal updates involving vertical size will be done in the expandRepeatVertical and expandBranchVertical
    // for both repeats and branches respectively
    /**
     * Recursively shift icons vertically according to the newly added icon
     * This method also validates the top and bottom vertical size of any of the branches
     * @param icon Newly added icon
     * @param n_shift Number of shift
     */
    public void recursiveShiftIconVerticalDown(Icon icon, int n_shift)
    {
        Icon parent = icon.getParentIcon(); 
        // also updates the top and bottom vertical size of any branch icon involved
        // need to have methods to return bottom half of a branch icon if section and top half of a branch icon else section
        // in the tallestmember function
        if(icon instanceof branchIcon)
        {
            if(parent instanceof loopIcon)
            {
                // if there are no children within this branch yet, then 
                // bottomtopVerticalSize + bottombottomVerticalSize = 0
                int b_vsize = icon.getBottomTopVerticalSize() + icon.getBottomBottomVerticalSize() + 1;  // always + 1
                int cur_size = parent.getBottomVerticalSize(); 
                if(cur_size <= b_vsize)
                {
                    n_shift = b_vsize - cur_size + 1;
                    expandRepeatVertical(parent, n_shift); 
                    int tmp_top = icon.getTopTopVerticalSize()+icon.getTopBottomVerticalSize()+1;
                    if(tmp_top > parent.getTopVerticalSize())
                    {
                        parent.setTopVerticalSize(tmp_top);
                    }                    
                    recursiveShiftIconVerticalDown(parent, n_shift);  
                }                    
            }
            else if(parent instanceof branchIcon)
            {
                if(parent.determineMember(icon) == 0)   // icon at the top (IF) part
                {
                    int b_vsize = icon.getBottomTopVerticalSize() + icon.getBottomBottomVerticalSize() + 1;   // always + 1
                    
                    int cur_tb_vsize = parent.getTopBottomVerticalSize();
                    
                    if(cur_tb_vsize < b_vsize)
                    {
                        n_shift = b_vsize - cur_tb_vsize; 
                        expandBranchVertical(parent, n_shift);                        
                        parent.setTopBottomVerticalSize(parent.getTopBottomVerticalSize()+n_shift);
                        parent.setBottomTopVerticalSize(parent.getBottomTopVerticalSize()+n_shift);
                    }

                    n_shift = Math.abs((icon.getTopTopVerticalSize()+icon.getTopBottomVerticalSize()+1)-parent.getTopTopVerticalSize());
                    
                        if(parent.getTopTopVerticalSize() < (icon.getTopTopVerticalSize() + icon.getTopBottomVerticalSize() + 1))
                        {
                            parent.setTopTopVerticalSize(parent.getTopTopVerticalSize()+n_shift);
                        }       
                    recursiveShiftIconVerticalDown(parent, n_shift);
                }
                else if(parent.determineMember(icon) == 1)    // icon at the bottom (ELSE) part
                {
                    int b_vsize = icon.getTopTopVerticalSize() + icon.getTopBottomVerticalSize() + 1;  // always + 1
                    int cur_tb_vsize = parent.getBottomTopVerticalSize();  
                    
                    if(cur_tb_vsize < b_vsize)
                    {
                        n_shift = b_vsize - cur_tb_vsize;
                        expandBranchVertical(parent, n_shift);
                        parent.setTopBottomVerticalSize(parent.getTopBottomVerticalSize()+n_shift);
                        parent.setBottomTopVerticalSize(parent.getBottomTopVerticalSize()+n_shift);                        
                    }

                    n_shift = Math.abs((icon.getBottomTopVerticalSize()+icon.getBottomBottomVerticalSize()+1)-parent.getBottomBottomVerticalSize());
                        if(parent.getBottomBottomVerticalSize() < (icon.getBottomTopVerticalSize() + icon.getBottomBottomVerticalSize() + 1))
                        {
                            parent.setBottomBottomVerticalSize(parent.getBottomBottomVerticalSize()+n_shift);
                        }    
                    
                    recursiveShiftIconVerticalDown(parent, n_shift);
                }
                
            }
        }
        else if(icon instanceof loopIcon)
        {
            if(parent instanceof loopIcon)
            {
                n_shift = parent.getBottomVerticalSize() - icon.getBottomVerticalSize();
                if(n_shift < 1)
                {
                    n_shift = Math.abs(n_shift);
                    n_shift++;
                    expandRepeatVertical(parent, n_shift);
                    int tmp_top = icon.getTopVerticalSize();
                    if(tmp_top > parent.getTopVerticalSize())
                    {
                        parent.setTopVerticalSize(tmp_top);
                    }  
                    recursiveShiftIconVerticalDown(parent, n_shift);
                }
            }
            else if(parent instanceof branchIcon)
            {           
                // then update the top and bottom vertical size
                if(parent.determineMember(icon) == 0)   // icon at the top (IF) part
                {
                    int b_vsize = icon.getBottomVerticalSize();
                    int cur_tb_vsize = parent.getTopBottomVerticalSize();
                    if(cur_tb_vsize < b_vsize)
                    {
                        n_shift = b_vsize - cur_tb_vsize; 
                        expandBranchVertical(parent, n_shift);                        
                        parent.setTopBottomVerticalSize(parent.getTopBottomVerticalSize()+n_shift);
                        parent.setBottomTopVerticalSize(parent.getBottomTopVerticalSize()+n_shift);
                        
                        if(icon.getTopVerticalSize() > parent.getTopTopVerticalSize())
                        {
                            parent.setTopTopVerticalSize(icon.getTopVerticalSize());
                        }
                    }      
                    recursiveShiftIconVerticalDown(parent, n_shift);
                }
                else if(parent.determineMember(icon) == 1)    // icon at the bottom (ELSE) part
                {
                        int b_vsize = icon.getTopVerticalSize();
                        int cur_tb_vsize = parent.getBottomTopVerticalSize();
                        if(cur_tb_vsize < b_vsize)
                        {
                            n_shift = b_vsize - cur_tb_vsize; 
                            expandBranchVertical(parent, n_shift);                        
                            parent.setTopBottomVerticalSize(parent.getTopBottomVerticalSize()+n_shift);
                            parent.setBottomTopVerticalSize(parent.getBottomTopVerticalSize()+n_shift);
                        }
                   
                        if(parent.getBottomBottomVerticalSize() < icon.getBottomVerticalSize())
                        {
                            parent.setBottomBottomVerticalSize(icon.getBottomVerticalSize());
                        }   
                        if(icon.getTopVerticalSize() > parent.getBottomTopVerticalSize())
                        {
                            parent.setBottomTopVerticalSize(icon.getTopVerticalSize());
                        }
                    recursiveShiftIconVerticalDown(parent, n_shift);
                }                
            }
        }        
    }

    /**
     * Recursively moves icons in the up direction (occurs if a branch or repeat within another branch or repeat has been deleted)
     * @param icon Removed icon
     * @param n_shift Number of shift
     * @param member_pos Position of icon (if in a branch)
     */
    public void recursiveShiftIconVerticalUp(Icon icon, int n_shift, int member_pos)
    {
        Icon parent = icon.getParentIcon();
        if(parent instanceof branchIcon)
        {
            Icon tallest;
            int tallest_vert_size = 0;
            // get the tallest in the opposite branch too
            Icon tallest2;
            int tallest_vert_size2 = 0;
            if(icon instanceof loopIcon)
            {                
                if(member_pos == -1)
                {
                    member_pos = parent.determineMember(icon);
                }
                if(member_pos == 0)   // if icon is within the IF part of the branch
                {
                    tallest = parent.tallestIfMemberBottom();
                    if(tallest instanceof branchIcon)
                    {                    
                        tallest_vert_size = tallest.getBottomBottomVerticalSize()
                                            +tallest.getBottomTopVerticalSize()+1;
                    }
                    else if(tallest instanceof loopIcon)
                    {
                        tallest_vert_size = tallest.getBottomVerticalSize();
                    }
                    else
                    {
                        tallest_vert_size = tallest.getVerticalSize();
                    }
                    
                    tallest2 = parent.tallestElseMemberTop();
                    if(tallest2 instanceof branchIcon)
                    {                    
                        tallest_vert_size2 = tallest2.getTopBottomVerticalSize()
                                            +tallest2.getTopTopVerticalSize()+1;
                    }
                    else if(tallest2 instanceof loopIcon)
                    {
                        tallest_vert_size2 = tallest2.getTopVerticalSize();
                    }
                    else
                    {
                        tallest_vert_size2 = tallest2.getVerticalSize();
                    } 
                    
                    // now determine the n_shift
                    int shift1 = parent.getTopBottomVerticalSize()-tallest_vert_size;
                    int shift2 = parent.getBottomTopVerticalSize()-tallest_vert_size2;
                    
                    if(shift2 > 0)
                    {
                        n_shift = Math.min(Math.abs(shift1), Math.abs(shift2));
                        
                        collapseBranchVertical(parent, n_shift);
                        parent.setTopBottomVerticalSize(parent.getTopBottomVerticalSize()-n_shift);
                        parent.setBottomTopVerticalSize(parent.getBottomTopVerticalSize()-n_shift);                   
                    }      
                    // need to update the top top vertical size too
                    int t = 0;
                    if(parent.tallestIfMemberTop() instanceof branchIcon)
                    {
                        t = parent.tallestIfMemberTop().getTopTopVerticalSize() + parent.tallestIfMemberTop().getTopBottomVerticalSize() + 1;
                    }
                    else if(parent.tallestIfMemberTop() instanceof loopIcon)
                    {
                        t = parent.tallestIfMemberTop().getTopVerticalSize();
                    }
                    else
                    {
                        t = parent.tallestIfMemberTop().getVerticalSize();
                    }
                    parent.setTopTopVerticalSize(t);        
                    member_pos = -1;
                    recursiveShiftIconVerticalUp(parent, n_shift, member_pos);
                }
                else if(member_pos == 1)   // if icon is within the ELSE part of the branch
                {
                    tallest = parent.tallestElseMemberTop();
                    if(tallest instanceof branchIcon)
                    {                    
                        tallest_vert_size = tallest.getTopBottomVerticalSize()
                                            +tallest.getTopTopVerticalSize()+1;
                    }
                    else if(tallest instanceof loopIcon)
                    {
                        tallest_vert_size = tallest.getTopVerticalSize();
                    }
                    else
                    {
                        tallest_vert_size = tallest.getVerticalSize();
                    }
                    
                    tallest2 = parent.tallestIfMemberBottom();
                    if(tallest2 instanceof branchIcon)
                    {                    
                        tallest_vert_size2 = tallest2.getBottomBottomVerticalSize()
                                            +tallest2.getBottomTopVerticalSize()+1;
                    }
                    else if(tallest2 instanceof loopIcon)
                    {
                        tallest_vert_size2 = tallest2.getBottomVerticalSize();
                    }
                    else
                    {
                        tallest_vert_size2 = tallest2.getVerticalSize();
                    }
                    // now determine the n_shift
                    int shift1 = parent.getBottomTopVerticalSize()-tallest_vert_size;
                    int shift2 = parent.getTopBottomVerticalSize()-tallest_vert_size2;
                    
                    if(shift2 > 0)
                    {
                        n_shift = Math.min(Math.abs(shift1), Math.abs(shift2));
                    
                        collapseBranchVertical(parent, n_shift);
                        parent.setTopBottomVerticalSize(parent.getTopBottomVerticalSize()-n_shift);
                        parent.setBottomTopVerticalSize(parent.getBottomTopVerticalSize()-n_shift);                                
                    }
                    member_pos = -1;
                    // need to update the bottom bottom vertical size too
                    int t = 0;
                    if(parent.tallestElseMemberBottom() instanceof branchIcon)
                    {
                        t = parent.tallestElseMemberBottom().getBottomTopVerticalSize() + parent.tallestElseMemberBottom().getBottomBottomVerticalSize() + 1;
                    }
                    else if(parent.tallestElseMemberBottom() instanceof loopIcon)
                    {
                        t = parent.tallestElseMemberBottom().getBottomVerticalSize();
                    }
                    else
                    {
                        t = parent.tallestElseMemberBottom().getVerticalSize();
                    }
                            
                    parent.setBottomBottomVerticalSize(t);    
                    recursiveShiftIconVerticalUp(parent, n_shift, member_pos); 
                }
            }
            else if(icon instanceof branchIcon)
            {
                if(member_pos == -1)
                {
                    member_pos = parent.determineMember(icon);
                }
                if(member_pos == 0)
                {
                    tallest = parent.tallestIfMemberBottom();
                    if(tallest instanceof branchIcon)
                    {                    
                        tallest_vert_size = tallest.getBottomBottomVerticalSize()
                                            +tallest.getBottomTopVerticalSize()+1;
                    }
                    else if(tallest instanceof loopIcon)
                    {
                        tallest_vert_size = tallest.getBottomVerticalSize();
                    }
                    else
                    {
                        tallest_vert_size = tallest.getVerticalSize();
                    }
                    
                    tallest2 = parent.tallestElseMemberTop();
                    if(tallest2 instanceof branchIcon)
                    {                    
                        tallest_vert_size2 = tallest2.getTopBottomVerticalSize()
                                            +tallest2.getTopTopVerticalSize()+1;
                    }
                    else if(tallest2 instanceof loopIcon)
                    {
                        tallest_vert_size2 = tallest2.getTopVerticalSize();
                    }
                    else
                    {
                        tallest_vert_size2 = tallest2.getVerticalSize();
                    } 
                    
                    // now determine the n_shift
                    int shift1 = parent.getTopBottomVerticalSize()-tallest_vert_size;
                    int shift2 = parent.getBottomTopVerticalSize()-tallest_vert_size2;
                   
                    if(shift2 > 0)
                    {
                        n_shift = Math.min(Math.abs(shift1), Math.abs(shift2));
                    
                        collapseBranchVertical(parent, n_shift);
                        parent.setTopBottomVerticalSize(parent.getTopBottomVerticalSize()-n_shift);
                        parent.setBottomTopVerticalSize(parent.getBottomTopVerticalSize()-n_shift);                                                    
                    }
                    // need to update the top top vertical size too
                    int t = 0;
                    if(parent.tallestIfMemberTop() instanceof branchIcon)
                    {
                        t = parent.tallestIfMemberTop().getTopTopVerticalSize() + parent.tallestIfMemberTop().getTopBottomVerticalSize() + 1;
                    }
                    else if(parent.tallestIfMemberTop() instanceof loopIcon)
                    {
                        t = parent.tallestIfMemberTop().getTopVerticalSize();
                    }
                    else
                    {
                        t = parent.tallestIfMemberTop().getVerticalSize();
                    }
                    parent.setTopTopVerticalSize(t); 
                    member_pos = -1;
                    recursiveShiftIconVerticalUp(parent, n_shift, member_pos); 
                }
                else if(member_pos == 1)
                {
                    tallest = parent.tallestElseMemberBottom();
                    if(tallest instanceof branchIcon)
                    {                    
                        tallest_vert_size = tallest.getTopBottomVerticalSize()
                                            +tallest.getTopTopVerticalSize()+1;
                    }
                    else if(tallest instanceof loopIcon)
                    {
                        tallest_vert_size = tallest.getTopVerticalSize();
                    }
                    else
                    {
                        tallest_vert_size = tallest.getVerticalSize();
                    }
                    
                    tallest2 = parent.tallestIfMemberBottom();
                    if(tallest2 instanceof branchIcon)
                    {                    
                        tallest_vert_size2 = tallest2.getBottomBottomVerticalSize()
                                            +tallest2.getBottomTopVerticalSize()+1;
                    }
                    else if(tallest2 instanceof loopIcon)
                    {
                        tallest_vert_size2 = tallest2.getBottomVerticalSize();
                    }
                    else
                    {
                        tallest_vert_size2 = tallest2.getVerticalSize();
                    }
                    // now determine the n_shift
                    int shift1 = parent.getBottomTopVerticalSize()-tallest_vert_size;
                    int shift2 = parent.getTopBottomVerticalSize()-tallest_vert_size2;
                    
                    if(shift2 > 0)
                    {
                        n_shift = Math.min(Math.abs(shift1), Math.abs(shift2));
                    
                        collapseBranchVertical(parent, n_shift);
                        parent.setTopBottomVerticalSize(parent.getTopBottomVerticalSize()-n_shift);
                        parent.setBottomTopVerticalSize(parent.getBottomTopVerticalSize()-n_shift);   
                    }
                    // need to update the bottom bottom vertical size too
                    int t = 0;
                    if(parent.tallestElseMemberBottom() instanceof branchIcon)
                    {
                        t = parent.tallestElseMemberBottom().getBottomTopVerticalSize() + parent.tallestElseMemberBottom().getBottomBottomVerticalSize() + 1;
                    }
                    else if(parent.tallestElseMemberBottom() instanceof loopIcon)
                    {
                        t = parent.tallestElseMemberBottom().getBottomVerticalSize();
                    }
                    else
                    {
                        t = parent.tallestElseMemberBottom().getVerticalSize();
                    }
                    parent.setBottomBottomVerticalSize(t);     
                    member_pos = -1;
                    recursiveShiftIconVerticalUp(parent, n_shift, member_pos);
                }
            }
        }
        else if(parent instanceof loopIcon)
        {
            if(icon instanceof loopIcon || icon instanceof branchIcon)
            {
                int tallest_vert_size = 0;
                Icon tallest = parent.tallestBottomMember();
                if(tallest instanceof branchIcon)
                {                  
                    tallest_vert_size = tallest.getBottomBottomVerticalSize()
                                        +tallest.getBottomTopVerticalSize()+1;
                }
                else if(tallest instanceof loopIcon)
                {
                    tallest_vert_size = tallest.getBottomVerticalSize();
                }
                else
                {
                    tallest_vert_size = tallest.getVerticalSize();
                }
                n_shift = parent.getBottomVerticalSize() - tallest_vert_size - 1;
                
                if(n_shift > 0)
                {
                    collapseRepeatVertical(parent, n_shift); 
                }
                    int t = 0;
                    if(parent.tallestTopMember() instanceof branchIcon)
                    {
                        t = parent.tallestTopMember().getTopTopVerticalSize() + parent.tallestTopMember().getTopBottomVerticalSize() + 1;
                    }
                    else if(parent.tallestTopMember() instanceof loopIcon)
                    {
                        t = parent.tallestTopMember().getTopVerticalSize();
                    }
                    else
                    {
                        t = parent.tallestTopMember().getVerticalSize();
                    }
                        
                    parent.setTopVerticalSize(t);
                member_pos = -1;
                recursiveShiftIconVerticalUp(parent, n_shift, member_pos);
            }
        }
    }
    
    
    // other as equally important method
    // will never be called to delete members of a starting icon, so just need to cater for two cases
    // 1. members of a branch icon
    // 2. members of a repeat icon
    /**
     * Recursively deletes all icons within a particular icon
     * This method is not located in class icons due its nature involving all wiring and icons and the programming window,
     * so it is only suitable for the method to reside in this class
     * @param icon Initially to be deleted icon
     */
    public void delAllIcons(Icon icon)
    {
        if(icon instanceof loopIcon)
        {
            deleteIcon(icon);
            icon_list.delIcon(icon);
            remove(icon);
            deleteWire(icon.getRightWire());
            wire_list.delWire(icon.getRightWire());
            deleteWire(icon.getLeftWire());
            wire_list.delWire(icon.getLeftWire());  
            deleteWire(icon.getEndIcon().getRightWire());
            wire_list.delWire(icon.getEndIcon().getRightWire());
            java.util.Vector<Icon> ics = icon.getMembers();
            
            for(int i=0; i<ics.size(); i++)
            {
                Icon tmp_ic = ics.get(i);

                if(tmp_ic instanceof branchIcon)
                {
                    delAllIcons(tmp_ic);
                }
                else if(tmp_ic instanceof loopIcon)
                {
                    delAllIcons(tmp_ic);
                }
                else
                {
                    deleteIcon(tmp_ic);
                    icon_list.delIcon(tmp_ic);
                    remove(tmp_ic);
                    if(icon.getEndIcon().getId() != tmp_ic.getId())
                    {
                        deleteWire(tmp_ic.getRightWire());
                        wire_list.delWire(tmp_ic.getRightWire());
                    }  
                }
            }
        }
        else if(icon instanceof branchIcon)
        {
            deleteIcon(icon);
            icon_list.delIcon(icon);
            remove(icon);
            deleteIcon(icon.getEndBranch());
            icon_list.delIcon(icon.getEndBranch());
            remove(icon.getEndBranch());
            
            // get rid of the wiring of this branch
            deleteWire(icon.getRightWireTop());
            wire_list.delWire(icon.getRightWireTop());
            deleteWire(icon.getRightWireBottom());
            wire_list.delWire(icon.getRightWireBottom());
            deleteWire(icon.getEndBranch().getLeftWireTop());
            wire_list.delWire(icon.getEndBranch().getLeftWireTop());
            deleteWire(icon.getEndBranch().getLeftWireBottom());
            wire_list.delWire(icon.getEndBranch().getLeftWireBottom());       
            deleteWire(icon.getEndBranch().getRightWire());      
            wire_list.delWire(icon.getEndBranch().getRightWire());  
            
            java.util.Vector<Icon> ics = icon.getIfMembers(); 
            for(int i=0; i<ics.size(); i++)
            {
                Icon tmp_ic = ics.get(i);               
                if(tmp_ic instanceof branchIcon)
                {
                    delAllIcons(tmp_ic);
                }
                else if(tmp_ic instanceof loopIcon)
                {
                    delAllIcons(tmp_ic);
                }
                else
                {
                    deleteIcon(tmp_ic);
                    icon_list.delIcon(tmp_ic);
                    remove(tmp_ic);
                    if(icon.getEndBranch().getLeftNeighbourTop().getId() != tmp_ic.getId())
                    {
                        deleteWire(tmp_ic.getRightWire());
                        wire_list.delWire(tmp_ic.getRightWire());
                    }                    
                }                
            }
            // --- Now do the else part
            
            ics = icon.getElseMembers();
            for(int i=0; i<ics.size(); i++)
            {
                Icon tmp_ic = ics.get(i);                
                if(tmp_ic instanceof branchIcon)
                {
                    delAllIcons(tmp_ic);
                }
                else if(tmp_ic instanceof loopIcon)
                {
                    delAllIcons(tmp_ic);
                }
                else
                {
                    deleteIcon(tmp_ic);
                    icon_list.delIcon(tmp_ic);
                    
                    remove(tmp_ic);
                    if(icon.getEndBranch().getLeftNeighbourBottom().getId() != tmp_ic.getId())
                    {
                        deleteWire(tmp_ic.getRightWire());
                        wire_list.delWire(tmp_ic.getRightWire());
                    } 
                }
            }
        }        
    }
    

    // by this time the checking to ensure that left_icon and right_icon should have the same parent, would have been
    // done
    /**
     * Returns a set of icons that will have their parent pointers changed to the new parent
     * @param left_icon Left icon
     * @param right_icon Right icon
     * @return Set of icons
     */
    public java.util.Vector<Icon> getIconsToChange(Icon left_icon, Icon right_icon)
    {
        // Obtain the members of the parent
        java.util.Vector<Icon> ics = new java.util.Vector<Icon>();
        Icon parent = right_icon.getParentIcon(); 
        if(parent instanceof branchIcon)
        {
            if(parent.determineMember(right_icon) == 0)
            {
                ics = parent.getIfMembers();
            }
            else if(parent.determineMember(right_icon) == 1)
            {
                ics = parent.getElseMembers();
            }
        }
        else
        {
            ics = parent.getMembers();
        }
        
        int left_index = 0;
        int right_index = 0;
        int found = 0;
        // Determine the indexes of the icons in the members vector
        
        for(int i=0; i<ics.size(); i++)
        {
            Icon tmp_icon = ics.get(i); 
            if(tmp_icon.getId() == left_icon.getId())
            {
                left_index = i;
                found = 1;  // the left icon could not be found, then left_index = 0
                break;
            }
        }
        if(found == 0)
        {
            left_index = -1;
        }
        for(int i=0; i<ics.size(); i++)
        {
            Icon tmp_icon = ics.get(i);
            if(tmp_icon.getId() == right_icon.getId())
            {
                right_index = i;   // there wouldn't be a scenario where the right icon is not found
                break;
            }
        }
        left_index++;   // so if left icon is not a member, then left index would start at 0
        
        // Then trim the vector
        java.util.Vector<Icon> tmp_ics = new java.util.Vector<Icon>();
        
        for(int i=left_index; i<right_index; i++)
        {
            tmp_ics.addElement(ics.get(i));
        }
        
        return tmp_ics;
    }
    
    /**
     * To ensure that all icons are 'squeezed together' in the repeat segment
     * i.e. no empty grid intersections in between
     * @param tmp_icon Icon
     */
    public void squeezeRepeat(Icon tmp_icon)
    {
        Icon jlc_right = tmp_icon.getEndIcon();
                            
        Icon jlc_left = tmp_icon.getBeginIcon();
        
        java.util.Vector<Icon> ics = new java.util.Vector<Icon>();
        ics = tmp_icon.getMembers();
                
        if(ics.size()>2)  // there are other icons within this repeat
        {
            Icon tmp_ic = ics.get(1);
            int nx;
            int ny = jlc_left.getCoordinate().getY();
            if(tmp_ic instanceof loopIcon)
            {
                nx = tmp_ic.getBeginIcon().getCoordinate().getX(); 
                nx = gridcalculation.calculateXright(nx) - Constants.gridDistanceX; 
            }
            else
            {
                nx = tmp_ic.getCoordinate().getX();
                nx = gridcalculation.calculateXright(nx) - Constants.gridDistanceX;
            }
            ny = gridcalculation.calculateY(ny);
            coord co = new coord(nx, ny);                
            co = gridcalculation.computeCoord(jlc_left, co);
            jlc_left.setCoordinate(co);
            jlc_left.setBounds(co.getX(), co.getY(), jlc_left.getPreferredSize().width, jlc_left.getPreferredSize().height);

            tmp_ic = ics.get(ics.size()-2);                
            ny = jlc_right.getCoordinate().getY();
            if(tmp_ic instanceof branchIcon)
            {
                nx = tmp_ic.getEndBranch().getCoordinate().getX();
                nx = gridcalculation.calculateXright(nx) + Constants.gridDistanceX;
            }
            else if(tmp_ic instanceof loopIcon)
            {
                nx = tmp_ic.getEndIcon().getCoordinate().getX();
                nx = gridcalculation.calculateXright(nx) + Constants.gridDistanceX;
            }
            else
            {
                nx = tmp_ic.getCoordinate().getX();
                nx = gridcalculation.calculateXright(nx) + Constants.gridDistanceX;
            }

            ny = gridcalculation.calculateY(ny);
            co = new coord(nx, ny);                
            co = gridcalculation.computeCoord(jlc_right, co);
            jlc_right.setCoordinate(co);
            jlc_right.setBounds(co.getX(), co.getY(), jlc_right.getPreferredSize().width, jlc_right.getPreferredSize().height);
        }
    }
    

    /**
     * Reposition a repeat icon due to the increase or decrease the number of icons within the repeat segment
     * Reposition implies centering the repeat icon relative to the width of the repeat segment
     * @param tmp_icon Icon
     */
    public void repositionRepeatHorizontal(Icon tmp_icon)
    {
        Icon jlc_right = tmp_icon.getEndIcon();
                            
        Icon jlc_left = tmp_icon.getBeginIcon();
        
        // now repositioning the repeat icon in the middle
        
        int new_x;
        int new_y;      
                           
        int left_width = jlc_left.getCoordinate().getX()+ jlc_left.getWidth();              
        new_x = left_width + ((jlc_right.getCoordinate().getX()-left_width)/2);
                            
        new_y = tmp_icon.getCoordinate().getY() + (tmp_icon.getHeight()/2); 
                            
        coord tmp_co = gridcalculation.computeCoord(tmp_icon, new coord(new_x, new_y));
        tmp_icon.setCoordinate(tmp_co);
        tmp_icon.setBounds(tmp_co.getX(), tmp_co.getY(), tmp_icon.getPreferredSize().width, tmp_icon.getPreferredSize().height);
    }
    
    /**
     * Call repositionRepeat repeatedly for all repeat icons
     */
    public void repositionAllRepeat()
    {
        java.util.Vector<Icon> icons = win_icon;
        for(int i=0; i<icons.size(); i++)
        {
            Icon ti = icons.get(i);
            if(ti instanceof loopIcon)
            {
                repositionRepeatHorizontal(ti);
            }
        }
    }
    
    
    /**
     * Updates the neighbouring icons of a loop
     */
    public void updateAllRepeatNeighbours()
    {
        java.util.Vector<Icon> icons = win_icon;
        for(int i=0; i<icons.size(); i++)
        {
            Icon ti = icons.get(i);
            if(ti instanceof loopIcon)
            {
                ti.setLeftNeighbour();
                ti.setRightNeighbour();
            }
        }        
    }

    /**
     * To find out number of segment used
     * Number of member and segments used up will differ since a branch or a loop is regarded as one icon or one entity
     * @param members Set of icons
     * @return Number of used segments
     */
    public int getNumUsedSegment(java.util.Vector<Icon> members)
    {
        int ic_count=0;
        for(int i=1; i<(members.size()-1); i++)
        {
            Icon tmp_ic = (Icon) members.get(i);  
            if(tmp_ic instanceof branchIcon)
            {
                int end_co = gridcalculation.calculateXright(tmp_ic.getEndBranch().getCoordinate().getX());
                int begin_co = gridcalculation.calculateXright(tmp_ic.getCoordinate().getX());
                ic_count += (end_co - begin_co)/Constants.gridDistanceX;
            }
            else if(tmp_ic instanceof loopIcon)
            {
                int end_co = gridcalculation.calculateXright(tmp_ic.getEndIcon().getCoordinate().getX());
                int begin_co = gridcalculation.calculateXright(tmp_ic.getBeginIcon().getCoordinate().getX());
                ic_count += (end_co - begin_co)/Constants.gridDistanceX; 
            }
            ic_count ++;
        }
        return ic_count;
    }
 
    /**
     * Finds out how many of the segments enclosed within a branch or a repeat are free to be used
     * This function will only be needed by a branch not a loop and nothing else
     * @param icon Icon
     * @param type Type = 0 for the top part (if); type = 1 for the bottom part (else)
     * @return Number of free segments
     */
    public int getFreeSegment(Icon icon, int type)
    {
        int free_count = 0;
        java.util.Vector<Icon> members = new java.util.Vector<Icon>();
        if(type == 0)
        {
            members = icon.getIfMembers();
        }
        else if(type == 1)
        {
            members = icon.getElseMembers();
        }
        
        Icon lefty = icon.getRightNeighbourTop();
        Icon righty = icon.getEndBranch().getLeftNeighbourTop();
        
        int x_begin = gridcalculation.calculateXright(lefty.getCoordinate().getX());
        int x_end = gridcalculation.calculateXright(righty.getCoordinate().getX());
        
        int num_segment = ((x_end - x_begin)/Constants.gridDistanceX)-1;
        
        int num_used = getNumUsedSegment(members); 
        free_count = num_segment - num_used; 
        return free_count; 
    }
    
   
    /**
     * Make sure every set of icons within a branch segment in the middle takes two major steps
     * 1. Come up with the available coordinates that the icons can take within the specified segment
     * 2. Place the icons on those coordinates
     * @param begin Begin coordinate
     * @param end End coordinate
     * @param members Set of icons
     * @return Set of coordinates
     */
    public java.util.Vector<coord> returnCoords(coord begin, coord end, java.util.Vector<Icon> members)
    {
        java.util.Vector<coord> cos = new java.util.Vector<coord>();
        int x_begin = begin.getX();
        int x_end = end.getX(); 
        int seg_count = (x_end-x_begin)/Constants.gridDistanceX+1;
        // get the available coordinates
        for(int i=0; i<seg_count; i++)
        {
            cos.add(new coord(x_begin, begin.getY()));
            x_begin += Constants.gridDistanceX;
        }
        
        int ic_count = 0;    // the number of segments to be filled by all icons
                             // Keep in mind that a branch treat another branch as one entity (one icon), so each branches within
                             // branch must have its width taken into consideration in this calculation
        
        ic_count = getNumUsedSegment(members);
        
        // now get rid of the coordinates that does not help the icons to be in the middle
        int num_emptyseg = seg_count-ic_count;  // empty segments that have to be emptied so that these icons would appear in the middle
        int front_limit = num_emptyseg/2;
        int end_limit = num_emptyseg-front_limit;
        for(int i=0; i<front_limit; i++)
        {
            cos.remove(0);
        }
        int size = cos.size();
        for(int i=0; i<end_limit; i++)
        {
            size --;
            cos.remove(size);            
        } 
        return cos;
    }
    
    /**
     * Move an icon in a required distance horizontally
     * @param tmp_ic Icon
     * @param dist Distance
     */
    public void moveIconHorizontal(Icon tmp_ic, int dist)
    {
        int old_xpos = tmp_ic.getCoordinate().getX();
        int old_ypos = tmp_ic.getCoordinate().getY();
        coord new_co = new coord(old_xpos+dist, old_ypos);
        tmp_ic.setCoordinate(new_co); 
        tmp_ic.setBounds(new_co.getX(), new_co.getY(), tmp_ic.getPreferredSize().width, tmp_ic.getPreferredSize().height);
    }
    
    /**
     * Vove an icon in a required distance vertically
     * @param tmp_ic Icon
     * @param dist Distance 
     */
    public void moveIconVertical(Icon tmp_ic, int dist)
    {
        int old_xpos = tmp_ic.getCoordinate().getX();
        int old_ypos = tmp_ic.getCoordinate().getY();
        coord new_co = new coord(old_xpos, old_ypos+dist);
        tmp_ic.setCoordinate(new_co);
        tmp_ic.setBounds(new_co.getX(), new_co.getY(), tmp_ic.getPreferredSize().width, tmp_ic.getPreferredSize().height);
    }
       
    /**
     * move a whole loop segment by a specified distance horizontally
     * @param tmp_icon Icon
     * @param dist Distance
     */
    public void moveRepeatHorizontal(Icon tmp_icon, int dist)
    {
        // THE local variables
        java.util.Vector<Icon> ics = new java.util.Vector<Icon>();
        Icon tmp_ic;
        if(dist != 0)  // only if needed to move
        {
            ics = tmp_icon.getMembers();
            for(int i = 0; i<ics.size(); i++)
            {
                tmp_ic = ics.get(i);
                
                if(tmp_ic instanceof branchIcon)
                {
                    moveBranchHorizontal(tmp_ic, dist);
                }
                else if(tmp_ic instanceof loopIcon)
                {
                    moveRepeatHorizontal(tmp_ic, dist);
                }
                else
                {
                    moveIconHorizontal(tmp_ic, dist);
                }
            }
        }
        // move the repeat icon itself
        moveIconHorizontal(tmp_icon, dist);
    }
    
    /**
     * Move a whole loop segment by a specified distance vertically
     * @param tmp_icon Icon
     * @param dist Distance
     */
    public void moveRepeatVertical(Icon tmp_icon, int dist)
    {
        // THE local variables
        java.util.Vector<Icon> ics = new java.util.Vector<Icon>();
        Icon tmp_ic;
        if(dist != 0)  // only if needed to move
        {
            ics = tmp_icon.getMembers();
            for(int i = 0; i<ics.size(); i++)
            {
                tmp_ic = ics.get(i);
                
                if(tmp_ic instanceof branchIcon)
                {
                    moveBranchVertical(tmp_ic, dist);
                }
                else if(tmp_ic instanceof loopIcon)
                {
                    moveRepeatVertical(tmp_ic, dist);
                }
                else
                {
                    moveIconVertical(tmp_ic, dist);
                }
            }
        }
        // move the repeat icon itself
        moveIconVertical(tmp_icon, dist);
    }
    
    /**
     * Move all icons within branches to a specified distance horizontally
     * @param tmp_icon Icon 
     * @param dist Distance
     */
    public void moveBranchHorizontal(Icon tmp_icon, int dist)
    {
        java.util.Vector<Icon> ics = new java.util.Vector<Icon>();
        Icon tmp_ic;
        if(dist != 0)  // only if needed to move
        {
            ics = tmp_icon.getIfMembers();
            for(int i=0; i<ics.size(); i++)
            {
                tmp_ic = ics.get(i);
                if(tmp_ic instanceof branchIcon)
                {
                    moveBranchHorizontal(tmp_ic, dist);
                }
                else if(tmp_ic instanceof loopIcon)
                {
                    moveRepeatHorizontal(tmp_ic, dist);
                }
                else
                {
                    moveIconHorizontal(tmp_ic, dist);
                }
            }
            // Now the else
            ics = tmp_icon.getElseMembers();
            for(int i=0; i<ics.size(); i++)
            {
                tmp_ic = ics.get(i);
                if(tmp_ic instanceof branchIcon)
                {
                    moveBranchHorizontal(tmp_ic, dist);
                }
                else if(tmp_ic instanceof loopIcon)
                {
                    moveRepeatHorizontal(tmp_ic, dist);
                }
                else
                {
                    moveIconHorizontal(tmp_ic, dist);
                }
            }
            // move the branch
            moveIconHorizontal(tmp_icon, dist);
            
            tmp_ic = tmp_icon.getEndBranch();
            moveIconHorizontal(tmp_ic, dist);
        }
    }
    

    /**
     * Move all icons within branches to a specified distance vertically
     * @param tmp_icon Icon
     * @param dist Distance
     */
    public void moveBranchVertical(Icon tmp_icon, int dist)
    {
        java.util.Vector<Icon> ics = new java.util.Vector<Icon>();
        Icon tmp_ic;
        if(dist != 0)  // only if needed to move
        {
            ics = tmp_icon.getIfMembers();
            for(int i=0; i<ics.size(); i++)
            {
                tmp_ic = ics.get(i);
                if(tmp_ic instanceof branchIcon)
                {
                    moveBranchVertical(tmp_ic, dist);
                }
                else if(tmp_ic instanceof loopIcon)
                {
                    moveRepeatVertical(tmp_ic, dist);
                }
                else
                {
                    moveIconVertical(tmp_ic, dist);
                }
            }
            // Now the else
            ics = tmp_icon.getElseMembers();
            for(int i=0; i<ics.size(); i++)
            {
                tmp_ic = ics.get(i);
                if(tmp_ic instanceof branchIcon)
                {
                    moveBranchVertical(tmp_ic, dist);
                }
                else if(tmp_ic instanceof loopIcon)
                {
                    moveRepeatVertical(tmp_ic, dist);
                }
                else
                {
                    moveIconVertical(tmp_ic, dist);
                }
            }
            // move the branch
            moveIconVertical(tmp_icon, dist);
            
            // move the end branch icon
            tmp_ic = tmp_icon.getEndBranch();
            moveIconVertical(tmp_ic, dist);
        }
    }
    
   /**
     * Reposition a branch icon due to the increase or decrease the number of icons within the branch segment
     * This method keeps all icons in the middle relative to the beginning and end icon of a branch
     * @param tmp_icon Icon
     */
    public void repositionBranch(Icon tmp_icon)
    {
        int x_begin;
        int x_end;
        java.util.Vector<coord> cos = new java.util.Vector<coord>();
        
        Icon start_ic;
        Icon end_ic;
        coord begin;
        coord end;
        coord tmp_co;
        java.awt.Component c;
         
        // now the real repositioning  -- 
        java.util.Vector<Icon> ics = tmp_icon.getIfMembers();
        
        // 1. Do the if statement
        start_ic = tmp_icon.getRightNeighbourTop();
        end_ic = tmp_icon.getEndBranch().getLeftNeighbourTop();
        begin = new coord((gridcalculation.calculateXright(start_ic.getCoordinate().getX())+Constants.gridDistanceX), 
                           gridcalculation.calculateY(start_ic.getCoordinate().getY()));
        end = new coord((gridcalculation.calculateXright(end_ic.getCoordinate().getX())-Constants.gridDistanceX),
                        gridcalculation.calculateY(end_ic.getCoordinate().getY()));
        x_begin = begin.getX();
        x_end = end.getX();
        cos = returnCoords(begin, end, tmp_icon.getIfMembers());        

        int i2 = 0;
        for(int i=1; i<(ics.size()-1); i++)
        {
            Icon tmp_ic = ics.get(i);
            tmp_co = new coord();
            if((tmp_ic instanceof loopIcon) == false)
            {
                tmp_co = gridcalculation.computeCoord(tmp_ic, cos.get(i2));
            }
            else
            {
                tmp_co = gridcalculation.computeCoord(tmp_ic.getBeginIcon(), cos.get(i2));
            }
            
           
            if(tmp_ic instanceof branchIcon)
            {
                // Recursively move this whole branch segment to the required coordinates
                moveBranchHorizontal(tmp_ic, (tmp_co.getX()-tmp_ic.getCoordinate().getX()));
                // then need to skip the coordinates accordingly
                
                i2+=((gridcalculation.calculateXright(tmp_ic.getEndBranch().getCoordinate().getX())-
                   gridcalculation.calculateXright(tmp_ic.getCoordinate().getX()))/Constants.gridDistanceX);
            }
            else if(tmp_ic instanceof loopIcon)   // trickier since the repeat icon is not actually aligned with the branch icon
                                                    // and its member
            {                
                // Recursively move this whole repeat segment to the required coordinates
                moveRepeatHorizontal(tmp_ic, (tmp_co.getX()-tmp_ic.getBeginIcon().getCoordinate().getX()));
                // then need to skip the coordinates accordingly
                
                i2+=((gridcalculation.calculateXright(tmp_ic.getEndIcon().getCoordinate().getX())-
                   gridcalculation.calculateXright(tmp_ic.getBeginIcon().getCoordinate().getX()))/Constants.gridDistanceX);
                tmp_co.setY(tmp_ic.getCoordinate().getY());
            } 
            
            tmp_ic.setCoordinate(tmp_co);
            tmp_ic.setBounds(tmp_co.getX(), tmp_co.getY(), tmp_ic.getPreferredSize().width, tmp_ic.getPreferredSize().height);
            i2++;
        }
        
        // 2. Do the else statement

        start_ic = tmp_icon.getRightNeighbourBottom();
        end_ic = tmp_icon.getEndBranch().getLeftNeighbourBottom();
        begin = new coord((gridcalculation.calculateXright(start_ic.getCoordinate().getX())+Constants.gridDistanceX), 
                           gridcalculation.calculateY(start_ic.getCoordinate().getY()));
        end = new coord((gridcalculation.calculateXright(end_ic.getCoordinate().getX())-Constants.gridDistanceX),
                        gridcalculation.calculateY(end_ic.getCoordinate().getY()));
        x_begin = begin.getX();
        x_end = end.getX();
        cos = returnCoords(begin, end, tmp_icon.getElseMembers());        
       
        ics = tmp_icon.getElseMembers();
        i2 = 0;
        // don't need to reposition the join icons
        for(int i=1; i<(ics.size()-1); i++)
        {
            Icon tmp_ic = ics.get(i);
            tmp_co = new coord();
            if((tmp_ic instanceof loopIcon) == false)
            {
                tmp_co = gridcalculation.computeCoord(tmp_ic, cos.get(i2));
            }
            else
            {
                tmp_co = gridcalculation.computeCoord(tmp_ic.getBeginIcon(), cos.get(i2));
            }
            
            if(tmp_ic instanceof branchIcon)
            {
                // Recursively move this whole branch segment to the required coordinates
                moveBranchHorizontal(tmp_ic, (tmp_co.getX()-tmp_ic.getCoordinate().getX()));
                i2+=((gridcalculation.calculateXright(tmp_ic.getEndBranch().getCoordinate().getX())-
                   gridcalculation.calculateXright(tmp_ic.getCoordinate().getX()))/Constants.gridDistanceX);
            }
            else if(tmp_ic instanceof loopIcon)   // trickier since the repeat icon is not actually aligned with the branch icon
                                                    // and its member
            {
                // Recursively move this whole repeat segment to the required coordinates
                moveRepeatHorizontal(tmp_ic, (tmp_co.getX()-tmp_ic.getBeginIcon().getCoordinate().getX()));
                // then need to skip the coordinates accordingly
                
                i2+=((gridcalculation.calculateXright(tmp_ic.getEndIcon().getCoordinate().getX())-
                   gridcalculation.calculateXright(tmp_ic.getBeginIcon().getCoordinate().getX()))/Constants.gridDistanceX);  
                tmp_co.setY(tmp_ic.getCoordinate().getY());
            }            
            
            tmp_ic.setCoordinate(tmp_co);
            tmp_ic.setBounds(tmp_co.getX(), tmp_co.getY(), tmp_ic.getPreferredSize().width, tmp_ic.getPreferredSize().height);
            i2++;
        } 
    }
    
    /**
     * Call repositionBranch repeatedly for all branch icons
     */
    public void repositionAllBranches()
    {
        java.util.Vector<Icon> icons = win_icon;
        for(int i=0; i<icons.size(); i++)
        {
            Icon ti = icons.get(i);
            if(ti instanceof branchIcon)
            {
                repositionBranch(ti);
            }
        }
    }
    
    /**
     * Finds the next to the right icon, given the coordinate
     * @param xpos X coordinate
     * @param ypos Y coordinate
     * @param type Type == 0, icon to the left; Type == 1, icon to the right
     * @return Icon
     */
    public Icon findIcon(int xpos, int ypos, int type)
    {
        // xpos and ypos are the clicked coordinates
        Icon tmp_icon = null;
        java.awt.Component c;
        
        
        int max_xpos = end_ic.getCoordinate().getX()+end_ic.getWidth();
        int min_xpos = start_ic.getCoordinate().getX();
        
        int cur_xpos = xpos;
        ypos = gridcalculation.calculateY(ypos);
        if(type == 0)
        {
            cur_xpos = gridcalculation.calculateXleft(cur_xpos);
            while(cur_xpos > min_xpos)
            {
                c = getComponentAt(new java.awt.Point(cur_xpos, ypos));
                if((c instanceof Icon) == false)
                {
                    cur_xpos -= Constants.gridDistanceX;            
                    continue;
                }
                else
                {
                    tmp_icon = (Icon) c;
                    return tmp_icon;
                }            
            }
        }
        else if(type == 1)
        {
            cur_xpos = gridcalculation.calculateXright(cur_xpos); 
            while(cur_xpos < max_xpos)
            {
                c = getComponentAt(new java.awt.Point(cur_xpos, ypos));
                if((c instanceof Icon) == false)
                {
                    cur_xpos += Constants.gridDistanceX;            
                    continue;
                }
                else
                {
                    tmp_icon = (Icon) c;
                    return tmp_icon;
                }            
            }
        }

        // iterate through the x axis
        return tmp_icon;
    }
 
    
    // function that recalculate the whole programming window for uniform distance between icons
    // horizontally
    
    // THERE SHOULD BE TWO PARTS OF REPOSITIONING
    // 1. Horizontally, always be done for the whole program to maintain uniform distances between icons and making
    //    sure that they are always in the middle relative to both its neighbours
    // 2. Vertically, only do the affected segments (e.g. the loop segment where an icon is added to)
    // The repositioning functions should be called whenever an addition, editing or deletion of an icon occurs
    
    // this method only expand the branch if necessary
    // if the argument member requires the parent to do so, i.e. the vertical size of member is equal to the current size
    // of this branch. A PARENT's VERTICAL SIZE ALWAYS HAS TO BE GREATER THAN ANY OF ITS MEMBER
    /**
     * Expand a branch's vertical size
     * @param icon Icon
     * @param n_expand Number of expansion
     */
    public void expandBranchVertical(Icon icon, int n_expand)
    {
        int i_size = icon.getVerticalSize();

        i_size = i_size + 2*n_expand; // the new size, usually number of grid expansion is one
        icon.setVerticalSize(i_size);
        // then move the members
        // first the top part of the branch
        java.util.Vector<Icon> ics = icon.getIfMembers();
        for(int i=0; i<ics.size(); i++)
        {
            Icon tmp_ic = ics.get(i);
            if(tmp_ic instanceof branchIcon)
            {
                moveBranchVertical(tmp_ic, -(n_expand*Constants.gridDistanceY));
            }
            else if(tmp_ic instanceof loopIcon)
            {
                moveRepeatVertical(tmp_ic, -(n_expand*Constants.gridDistanceY));
            }
            else
            {
                moveIconVertical(tmp_ic, -(n_expand*Constants.gridDistanceY));
            }
        }
        // second the bottom part of the branch
        ics = icon.getElseMembers();
        for(int i=0; i<ics.size(); i++)
        {
            Icon tmp_ic = ics.get(i);
            if(tmp_ic instanceof branchIcon)
            {
                moveBranchVertical(tmp_ic, (n_expand*Constants.gridDistanceY));
            }
            else if(tmp_ic instanceof loopIcon)
            {
                moveRepeatVertical(tmp_ic, (n_expand*Constants.gridDistanceY));
            }
            else
            {
                moveIconVertical(tmp_ic, (n_expand*Constants.gridDistanceY));
            }
        }
    }
    
    /**
     * Decrease a branch's vertical size by collapsing it vertically
     * Should be called whenever one of the branch's member is deleted to ensure uniform vertical distance between the branches
     * @param icon Icon
     * @param n_collapse Number of grid to collapse this branch by
     */
    public void collapseBranchVertical(Icon icon, int n_collapse)
    {        
        int i_size = icon.getVerticalSize();

        i_size = i_size - 2*n_collapse; // the new size, usually number of grid expansion is one
        icon.setVerticalSize(i_size);
        // then move the members
        // first the top part of the branch
        java.util.Vector<Icon> ics = icon.getIfMembers();
        for(int i=0; i<ics.size(); i++)
        {
            Icon tmp_ic = ics.get(i); 
            if(tmp_ic instanceof branchIcon)
            {
                moveBranchVertical(tmp_ic, (n_collapse*Constants.gridDistanceY));
            }
            else if(tmp_ic instanceof loopIcon)
            {
                moveRepeatVertical(tmp_ic, (n_collapse*Constants.gridDistanceY));
            }
            else
            {
                moveIconVertical(tmp_ic, (n_collapse*Constants.gridDistanceY));
            }
        }
        // second the bottom part of the branch
        ics = icon.getElseMembers();
        for(int i=0; i<ics.size(); i++)
        {
            Icon tmp_ic = ics.get(i); 
            if(tmp_ic instanceof branchIcon)
            {
                moveBranchVertical(tmp_ic, -(n_collapse*Constants.gridDistanceY));
            }
            else if(tmp_ic instanceof loopIcon)
            {
                moveRepeatVertical(tmp_ic, -(n_collapse*Constants.gridDistanceY));
            }
            else
            {
                moveIconVertical(tmp_ic, -(n_collapse*Constants.gridDistanceY));
            }
        }   
    }
    
    // it is best here not to use loopIcon.tallestMember, this will save computation by not needing to iterate through
    // the members of icon. 
    /**
     * Shit a repeat icon down
     * This method only shift the repeat icon if the member of this repeat icon force it to do so
     * @param icon Icon
     * @param n_expand Number of grid to expand by
     */
    public void expandRepeatVertical(Icon icon, int n_expand)
    {
        int i_size = icon.getBottomVerticalSize();
        i_size = i_size + n_expand; // the new size
        icon.setBottomVerticalSize(i_size);
        moveIconVertical(icon, (n_expand*Constants.gridDistanceY));
    }
    
    /**
     * Collapses the specified repeat if one of its member is deleted
     * @param icon Icon
     * @param n_collapse Number of grid to collapse by
     */
    public void collapseRepeatVertical(Icon icon, int n_collapse)
    {
        int i_size = icon.getBottomVerticalSize();
        i_size = i_size - n_collapse; // the new size
        icon.setBottomVerticalSize(i_size);
        moveIconVertical(icon, -(n_collapse*Constants.gridDistanceY));
    }
    
    /**
     * Iterates through a set of icons horizontally from left to right and returns the greatest bottom vertical
     * size of one of the icons within the set, since repeat icons are always underneath their members, so they only need
     * to care about the bottom vertical size of any of its members
     * @param ics Set of icons
     * @return Greatest bottom vertical size
     */
    public int greatestBottomVerticalSize(java.util.Vector<Icon> ics)
    {     
        int vsize = 0;
        for(int i=0; i<ics.size(); i++)
        {
            Icon ic = ics.get(i);
            int tmp_vs = 0;
            if(ic instanceof branchIcon)
            {
                // for a test or branch icon, we need only half of the vertical size since the loop icon using this function
                // would only take up a space below half of the branch
                tmp_vs = ic.getBottomTopVerticalSize() + ic.getBottomBottomVerticalSize() + 1;  // always + 1
            }
            else if(ic instanceof loopIcon)
            {
                tmp_vs = ic.getBottomVerticalSize();
            }
            else
            {
                tmp_vs = ic.getVerticalSize();
            }
            if(tmp_vs > vsize)
            {
                vsize = tmp_vs;
            }
        }
        return vsize;
    }
    
    
    /**
     * Either shift the entire icon down or up # of grid intersection determine by this function's argument
     * @param n_shift Number of shift
     */
    public void shiftIconsVertical(int n_shift)
    {
        java.util.Vector<Icon> icons = win_icon;
        Icon tmp_icon;
        
        int xpos;
        int ypos;
        coord co;
        
        for(int i = 0; i<icons.size(); i++)
        {
            tmp_icon = icons.get(i);
            if(tmp_icon != null)
            {
                xpos = tmp_icon.getCoordinate().getX();
                ypos = tmp_icon.getCoordinate().getY();
                ypos += (n_shift*Constants.gridDistanceY);
                
                co = new coord(xpos,ypos);

                tmp_icon.setCoordinate(co);   // store the new coordinate into the corresponding icon
                tmp_icon.setBounds(co.getX(), co.getY(), tmp_icon.getPreferredSize().width, tmp_icon.getPreferredSize().height);
            }
            else
            {
                continue;
            }
        }
    }
    
    /**
     * Moves every single kind of icon to the direction specified
     * @param threshold Threshold icon
     * @param n_shift Number of shifts
     */
    public void repositionIcons(Icon threshold, int n_shift)
    {
       // java.util.Vector<Icon> icons = icon_list.getIcons().get(1).getMembers();
        java.util.Vector<Icon> icons = start_ic.getMembers();
        Icon tmp_icon = new Icon();        

        int dist = 0;  // distance the icons need to be moved

        int i = 0;
        // first of all find the threshold icon's relative position first
        
        for(i = 0; i<icons.size(); i++)
        {            
            tmp_icon = icons.get(i);  
            if(tmp_icon.getId() == threshold.getId())
            {
                break;
            }
        }
        
        // now for all icons to the right of the threshold icon, they will be moved
        for(int i2 = i; i2<icons.size(); i2++)
        {
            tmp_icon = icons.get(i2);
            if(tmp_icon != null)
            {
                dist = (n_shift*Constants.gridDistanceX);     
                
                if(tmp_icon instanceof branchIcon)
                {
                    moveBranchHorizontal(tmp_icon, dist);
                }
                else if(tmp_icon instanceof loopIcon)
                {
                    moveRepeatHorizontal(tmp_icon, dist);
                }
                else
                {
                    moveIconHorizontal(tmp_icon, dist);
                }
            }
            else
            {
                continue;
            }
        }
    }
    
    
    /**
     * Checks whether an icon is actually its own parent if a relocation happens
     * This is needed for branch relocation
     * @param icon Icon
     * @param tic Icon to the right of icon
     * @param tic2 Icon to the left of icon
     * @return Boolean value
     */
    public Boolean checkParent(Icon icon, Icon tic, Icon tic2)
    {
        Boolean ret_val = true;
        Icon parent = new Icon();
        if(nestedParent(tic2, tic) == 0)
        {
            parent = tic2;
        }
        else if(nestedParent(tic2, tic) == 1)
        {
            parent = tic;
        }
        else if(nestedParent(tic2, tic) == 2)
        {
            parent = tic.getParentIcon();
        }
        // to start with, if the icon is the parent itself, then go up one level
        if(icon.getId() == parent.getId())
        {
            parent = parent.getParentIcon();
        }
        while(true)
        {
            if(icon.getId() == parent.getId())
            {
                ret_val = false;
                break;
            }
            if(parent.getParentIcon() != null)
            {
                parent = parent.getParentIcon();
            }  
            else
            {
                break;
            }
        }
        return ret_val;
    }
    
    /**
     * Decides which parent of these two icons should be used
     * @param left_icon Left icon
     * @param right_icon Right icon
     * @return If ret_val = 0, then use left_icon's parent, ret_val = 1, use otherwise, ret_val = 2 both icon's parent
     */
    public int nestedParent(Icon left_icon, Icon right_icon)
    {
        int ret_val = -1;
        
        java.util.Vector<Icon> ics = new java.util.Vector<Icon>();
        
        Icon parent1 = left_icon.getParentIcon();
        Icon parent2 = right_icon.getParentIcon();
        
        if(parent1 != null)
        {
            if(parent1.getId() != parent2.getId())   // different parent, then decide which is higher up in the hierarchy
                // IF left does not contain right and nor right contain left, then
                // the parent should be the two's parent
            {
                
                // check whether left contain right
                
                if(parent1 instanceof branchIcon)
                {
                    ics = parent1.getIfMembers();
                    for(int i=0; i<ics.size(); i++)
                    {
                        Icon tm = ics.get(i);
                        if(tm.getId() == parent2.getId())
                        {
                            ret_val = 0;
                            break;
                        }
                    }
                    if(ret_val == -1)  // not yet found within the if branch
                    {
                        ics = parent1.getElseMembers();
                        for(int i=0; i<ics.size(); i++)
                        {
                            Icon tm = ics.get(i);
                            if(tm.getId() == parent2.getId())
                            {
                                ret_val = 0;
                                break;
                            }
                        }
                    }
                }
                else
                {
                    ics = parent1.getMembers();
                    for(int i=0; i<ics.size(); i++)
                    {
                        Icon tm = ics.get(i);
                        if(tm.getId() == parent2.getId())
                        {
                            ret_val = 0;
                            break;
                        }
                    }    
                }
                
                // still not decided
                if(ret_val == -1)
                {
                    // then check for right containing left
                    if(parent2 instanceof branchIcon)
                    {
                        ics = parent2.getIfMembers();
                        for(int i=0; i<ics.size(); i++)
                        {
                            Icon tm = ics.get(i);
                            if(tm.getId() == parent1.getId())
                            {
                                ret_val = 1;
                                break;
                            }
                        }
                        if(ret_val == -1)  // not yet found within the if branch
                        {
                            ics = parent2.getElseMembers();
                            for(int i=0; i<ics.size(); i++)
                            {
                                Icon tm = ics.get(i);
                                if(tm.getId() == parent1.getId())
                                {
                                    ret_val = 1;
                                    break;
                                }
                            }
                        }
                    }
                    else
                    {
                        ics = parent2.getMembers();
                        for(int i=0; i<ics.size(); i++)
                        {
                            Icon tm = ics.get(i);
                            if(tm.getId() == parent1.getId())
                            {
                                ret_val = 1;
                                break;
                            }
                        }    
                    }
                }              
                
                
                // if still not found which one contain which, then the parent must be both's parent
                if(ret_val == -1)
                {
                    ret_val = 2;
                }                
            }
        }
        if(ret_val == -1)
        {
            ret_val = 0;
        }
        return ret_val;
    }
    
    /**
     * Set up a relationship of an icon to its parent
     * @param icon Icon
     * @param tic2 Icon to the left of icon
     * @param tic Icon to the right of icon
     */
    public void setUpIconParent(Icon icon, Icon tic2, Icon tic)
    {
        Icon tmp_son = new Icon();
        if(nestedParent(tic2, tic) == 0)
        {
            tmp_son = tic2;
        }
        else if(nestedParent(tic2, tic) == 1)
        {
            tmp_son = tic;
        }
        else if(nestedParent(tic2, tic) == 2)
        {
            tmp_son = tic.getParentIcon();
        }
                   
        if(tmp_son.getParentIcon() == null)  // then tic2 is the starting icon
        {
            icon.setParentIcon(tmp_son);   // will be the beginning icon --> instanceof startIcon
            if(tic instanceof joinLoopIcon && tic.getParentIcon().getId() != icon.getParentIcon().getId())
            {
                icon.getParentIcon().addMember(icon, tic.getBottomNeighbour());
            }
            else
            {
                icon.getParentIcon().addMember(icon, tic);
            }                    
        }
        else
        {
            icon.setParentIcon(tmp_son.getParentIcon());
            if(tic instanceof joinLoopIcon && tic.getParentIcon().getId() != icon.getParentIcon().getId())
            {
                icon.getParentIcon().addMember(icon, tic.getBottomNeighbour());
            }
            else
            {
                icon.getParentIcon().addMember(icon, tic);
            }                          
        }
    }
    
    /**
     * Inserts an object icon
     * @param icon Icon
     * @param tic Icon to the right of icon
     * @param tic2 Icon to the left of icon
     * @param jsp A scrollpane
     */
    public void insertObjectIcon(Icon icon, Icon tic, Icon tic2, javax.swing.JScrollPane jsp)
    {
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        setUpIconParent(icon, tic2, tic);  
        
        // connecting the newly added icon with its neighbouring icons
        icon.setRightNeighbour(tic);
        tic.setLeftNeighbour(icon);

        icon.setLeftNeighbour(tic2);
        tic2.setRightNeighbour(icon);
                                
        coord co = icon.getCoordinate();
        icon.setBounds(co.getX(), co.getY(), icon.getPreferredSize().width, icon.getPreferredSize().height);
                
        icon_list.addIcon(icon);
        addIcon(icon);
        add(icon);
                                           
        recursiveShiftIconHorizontalRight(icon, 1);

        splitWire(icon, icon_list.getIcons().get(tic.getId()));                
                
        jDirectionsLabel.setText("Icon successfully placed");
        repositionAllBranches();
        repositionAllRepeat();
        updateAllRepeatNeighbours();
                        
        if(icon instanceof functionIcon || icon instanceof beginTaskIcon || icon instanceof stopTaskIcon || icon instanceof startEvent)
        {            
            icon.setImage();   // refresh the icon's id display
        }
        
        // resize the programming window
        resizeProgWindow(jsp);
        // viewport.setViewPosition(new java.awt.Point(icon.getCoordinate().getX()-(gridDistanceX*2), icon.getCoordinate().getY()-(gridDistanceY*2)));
                
        // object_init_replace_listener(icon);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }
    
    /**
     * Inserts an branch icon and loop icons
     * @param icon Icon
     * @param tic Icon to the right of icon
     * @param tic2 Icon to the left of icon
     * @param jsp Scroll pane
     * @param jic Join end branch icon of the inserted branch
     * @param jic1 Top left join icon of the inserted icon
     * @param jic2 Bottom left join icon of the inserted icon
     * @param jic3 Bottom right join icon of the inserted icon
     * @param jic4 Top right join icon of the inserted icon
     */
    public void insertBranchIcon(Icon icon, Icon tic, Icon tic2, javax.swing.JScrollPane jsp, joinEndBranchIcon jic, joinIcon jic1, joinIcon jic2, joinIcon jic3, joinIcon jic4)
    {
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        int xpos = 0;
        if(tic2 != null && tic != null)
        {
            xpos = gridcalculation.midPoint(tic2.getCoordinate(), tic.getCoordinate());
        }
        
        int ypos = gridcalculation.calculateY(icon.getCoordinate().getY()+(icon.getHeight()/2));        
        
        setUpIconParent(icon, tic2, tic);  
        // introduce the join icon to reconnect split wires at the end of the branching segment of the code    
                
        coord co = icon.getCoordinate();
                
        // 1. Set up the Branch Icon                
        icon.setBounds(co.getX(), co.getY(), icon.getPreferredSize().width, icon.getPreferredSize().height);
        icon.setVerticalSize(3);  // initially a branch have a vertical size of 3
                
        // 2. Set up the top left joinIcon
        ypos -= Constants.gridDistanceY;   // going up one grid segment for the IF
        setUpIcon(jic1, xpos, ypos);
                
        // 3. Set up the bottom left joinIcon
        ypos += (Constants.gridDistanceY*2);   // going down one grid segment for the ELSE
        setUpIcon(jic2, xpos, ypos);

        // 4. Set up the bottom right joinIcon
        xpos += Constants.gridDistanceX;   
        setUpIcon(jic3, xpos, ypos);

        // 5. Set up the top right joinIcon
        ypos -= (Constants.gridDistanceY*2);   
        setUpIcon(jic4, xpos, ypos);

        // 6. Set up the joinEndBranchIcon

        // the coordinate of this joinEndBranchIcon will be 1 grid intersections away from the branch icon
        int x = xpos;
        int y = ypos + Constants.gridDistanceY;
        setUpIcon(jic, x, y);

        // connecting the newly added icon with its neighbouring icons
        icon.setRightNeighbourTop(jic1);
        icon.setRightNeighbourBottom(jic2);
        jic1.setLeftNeighbour(icon);
        jic2.setLeftNeighbour(icon);

        jic1.setRightNeighbour(jic4);
        jic2.setRightNeighbour(jic3);

        jic3.setLeftNeighbour(jic2);
        jic4.setLeftNeighbour(jic1);

        jic3.setRightNeighbour(jic);
        jic4.setRightNeighbour(jic);

        jic.setRightNeighbour(tic);
        tic.setLeftNeighbour(jic);
        jic.setLeftNeighbourTop(jic4);
        jic.setLeftNeighbourBottom(jic3);

        icon.setEndBranch(jic);   // connect the beginning and end icons of this branch segment
        // introduce the wiring and connect the wires with the icons
        /*
         *              ji1--------jic4|
         *              |              |
         *  tic2----icon|              jic----tic
         *              |              |
         *              jic2-------jic3|   
         *
         */

        Wire wire = new Wire();
        wire.setLeftIcon(icon);
        icon.setRightWireTop(wire);
        wire.setRightIcon(jic1);
        jic1.setLeftWire(wire);
        wire.setType(1);
        wire_list.addWire(wire);
        addWire(wire);

        wire = new Wire();
        wire.setLeftIcon(icon);
        icon.setRightWireBottom(wire);
        wire.setRightIcon(jic2);
        jic2.setLeftWire(wire);
        wire.setType(1);
        wire_list.addWire(wire);
        addWire(wire);

        newWire(jic1, jic4, 1);

        newWire(jic2, jic3, 1);

        wire = new Wire();
        wire.setLeftIcon(jic4);
        jic4.setRightWire(wire);
        wire.setRightIcon(jic);
        jic.setLeftWireTop(wire);
        wire.setType(1);
        wire_list.addWire(wire);
        addWire(wire);

        wire = new Wire();
        wire.setLeftIcon(jic3);
        jic3.setRightWire(wire);
        wire.setRightIcon(jic);
        jic.setLeftWireBottom(wire);
        wire.setType(1);
        wire_list.addWire(wire);
        addWire(wire);

        icon.setLeftNeighbour(tic2);
        tic2.setRightNeighbour(icon);

        tic2.getRightWire().setRightIcon(icon);
        icon.setLeftWire(tic2.getRightWire());

        if(icon.getParentIcon() instanceof branchIcon)
        {
            newWire(jic, tic, 1);
        }
        else if(icon.getParentIcon() instanceof loopIcon)
        {
            newWire(jic, tic, 2);
        }
        else
        {
            newWire(jic, tic, 0);
        }                

        // setting the parent icons
        icon_list.addIcon(icon);
        icon_list.addIcon(jic);
        icon_list.addIcon(jic1);
        icon_list.addIcon(jic2);
        icon_list.addIcon(jic3);
        icon_list.addIcon(jic4);  

        addIcon(icon);
        addIcon(jic);
        addIcon(jic1);
        addIcon(jic2);
        addIcon(jic3);
        addIcon(jic4);

        jic.setParentIcon(icon);   // jlc will not be included as a member of the branch icon
        jic.setBranchIcon(icon);
        jic1.setParentIcon(icon);
        // use the starting icon as dummy to put jic1 at the front

        icon.addMember(jic1, getStartIcon()); 
        jic2.setParentIcon(icon);
        icon.addMember(jic2, getStartIcon());
        jic3.setParentIcon(icon);
        icon.addMember(jic3, getStartIcon());
        jic4.setParentIcon(icon);
        icon.addMember(jic4, getStartIcon());                


        add(icon);
        add(jic);
        add(jic1);
        add(jic2);
        add(jic3);
        add(jic4);             

        // recursively reposition all affected icons
        int shift = gridcalculation.calculateXright(icon.getEndBranch().getCoordinate().getX())
                    - gridcalculation.calculateXright(icon.getCoordinate().getX()) + Constants.gridDistanceX;
        recursiveShiftIconHorizontalRight(icon, (shift/Constants.gridDistanceX));

        jDirectionsLabel.setText("Icon successfully placed");
        repositionAllBranches();
        repositionAllRepeat();
        recursiveShiftIconVerticalDown(icon, 1);
        updateAllRepeatNeighbours();
        
        // resize the programming window
        resizeProgWindow(jsp); 
        // viewport.setViewPosition(new java.awt.Point(evt.getX()-(gridDistanceX*2), evt.getY()-(gridDistanceY*2)));
        //branch_init_replace_listener(icon);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }
    
    /**
     * Inserts a loop icons onto the programming window
     * @param lic The to be inserted loop icon
     * @param tic Icon to the right of the loop's begin icon
     * @param tic2 Icon to the left of the loop's begin icon
     * @param tic3 Icon to the right of the loop's end icon
     * @param tic4 Icon to the left of the loop's end icon
     * @param jsp Scroll Pane
     * @param jlc The begin icon of this loop icon
     * @param jlc2 The end icon of this loop icon
     */
    public void insertLoopIcon(Icon lic, Icon tic, Icon tic2, Icon tic3, Icon tic4, javax.swing.JScrollPane jsp, joinLoopIcon jlc, joinLoopIcon jlc2)
    {
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        int xpos = 0;
        if(tic2 != null && tic != null)
        {
            xpos = gridcalculation.midPoint(tic2.getCoordinate(), tic.getCoordinate());
        }

        // all tic, tic2, tic3 and tic4 gotta have the same y coordinate
        int ypos = gridcalculation.calculateY(tic.getCoordinate().getY()+(tic.getHeight()/2));      

      //  xpos = gridcalculation.calculateXright(xpos);
        ypos = gridcalculation.calculateY(ypos);

        int xpos2 = gridcalculation.calculateXright(gridcalculation.midPoint(tic4.getCoordinate(), tic3.getCoordinate()));  
        int ypos2 = gridcalculation.calculateY(tic3.getCoordinate().getY()+(tic3.getHeight()/2));

        int vertical_size = 0;            

        icon_list.addIcon(lic); 
        addIcon(lic);
        setUpIconParent(lic, tic2, tic3);                

        java.util.Vector<Icon> ics = new java.util.Vector<Icon>();

        setUpIcon(jlc, xpos, ypos);

        // now put the beginning icon

        jlc.setRightNeighbour(tic);
        tic.setLeftNeighbour(jlc);

        jlc.setLeftNeighbour(tic2);
        tic2.setRightNeighbour(jlc);

        icon_list.addIcon(jlc);
        addIcon(jlc);
        add(jlc);

        jlc.setParentIcon(lic.getParentIcon());
        if(tic instanceof joinLoopIcon && tic.getParentIcon().getId() != lic.getParentIcon().getId())
        {
            lic.getParentIcon().addMember(jlc, tic.getBottomNeighbour()); 
        }
        else
        {
            lic.getParentIcon().addMember(jlc, tic);
        }

        // temporarily removes the lic from the its parent
        lic.getParentIcon().delMember(lic);

        recursiveShiftIconHorizontalRight(jlc, 1);                    


        splitWire(jlc, icon_list.getIcons().get(tic.getId()));

        if(xpos == xpos2)
        {
            // then introduce the join icon at the end of the loop
            xpos2 += Constants.gridDistanceX;// by this time the clicked position would have been
                           // moved by 1 grid segment   
        }  

        setUpIcon(jlc2, xpos2, ypos2);

        // now put the beginning icon

        jlc2.setRightNeighbour(tic3);
        tic3.setLeftNeighbour(jlc2);

        jlc2.setLeftNeighbour(tic4);
        tic4.setRightNeighbour(jlc2);              

        icon_list.addIcon(jlc2);
        addIcon(jlc2);
        add(jlc2);

        jlc2.setParentIcon(lic.getParentIcon());
        if(tic3 instanceof joinLoopIcon && tic3.getParentIcon().getId() != lic.getParentIcon().getId())
        {
            lic.getParentIcon().addMember(jlc2, tic3.getBottomNeighbour()); 
        }
        else
        {
            lic.getParentIcon().addMember(jlc2, tic3);
        }

        recursiveShiftIconHorizontalRight(jlc2, 1);


        splitWire(jlc2, icon_list.getIcons().get(tic3.getId()));


        lic.getParentIcon().delMember(jlc);
        lic.getParentIcon().delMember(jlc2);
        jlc.setParentIcon(lic);
        jlc2.setParentIcon(lic);
        lic.addMember(jlc, getStartIcon());
        lic.addMember(jlc2, getStartIcon());

        setUpIconParent(lic, tic2, tic3);

        // establish the new links between the old members, new members, old parent and new parent
        Icon left_icon = new Icon();
        Icon right_icon = new Icon();
        left_icon = tic2;
        right_icon = tic3;
        if(tic2 instanceof joinLoopIcon)
        {
            if(tic2.getParentIcon().getId() != lic.getParentIcon().getId())
            {
               left_icon = tic2.getBottomNeighbour(); 
            }
        }
        else if(tic2 instanceof joinEndBranchIcon)
        {
            left_icon = tic2.getBranchIcon();
        }
        if(tic3 instanceof joinLoopIcon && tic3.getParentIcon().getId() != lic.getParentIcon().getId())
        {

            right_icon = tic3.getBottomNeighbour();
        }

        ics = getIconsToChange(left_icon, right_icon);

        // reset the parent pointers and also update the wiring colours
        int size = ics.size();  
        for(int i=0; i<size; i++)
        {
            if(lic.getId() != ics.get(i).getId())  // this repeat icon should stays with its existing parent
            {
                lic.getParentIcon().delMember(ics.get(i));
                ics.get(i).setParentIcon(lic);
                lic.addMember(ics.get(i), jlc2);
                // update the wiring colour
                if(ics.get(i) instanceof branchIcon)
                {
                    ics.get(i).getLeftWire().setType(2);
                    ics.get(i).getEndBranch().getRightWire().setType(2);    
                }
                else if(ics.get(i) instanceof loopIcon)
                {
                    ics.get(i).getBeginIcon().getLeftWire().setType(2);
                    ics.get(i).getEndIcon().getRightWire().setType(2);    
                }
                else
                {
                    ics.get(i).getLeftWire().setType(2);
                    ics.get(i).getRightWire().setType(2);
                } 
            }                
        }

        if(lic.getMembers().size() == 2)
        {
            tic3.setLeftNeighbour(jlc2);
            jlc2.setRightNeighbour(tic3);
            jlc2.setLeftNeighbour(jlc);
            jlc.setRightNeighbour(jlc2);
            tic2.setRightNeighbour(jlc);
            jlc.setLeftNeighbour(tic2);
        }

        // then introduce the loop icon in between the beginning and the end of the loop

        //OR

        xpos = xpos+((xpos2-xpos)/2);
        vertical_size = greatestBottomVerticalSize(lic.getMembers())+1;
        ypos += vertical_size*Constants.gridDistanceY;
        setUpIcon(lic, xpos, ypos);
        lic.setBottomVerticalSize(vertical_size);   // the initial vertical size of a loop icon is 2
        int tvs=0;
        if(lic.tallestTopMember() instanceof branchIcon)
        {
            tvs = lic.tallestTopMember().getTopTopVerticalSize()+lic.tallestTopMember().getTopBottomVerticalSize()+1;
        }
        else if(lic.tallestTopMember() instanceof loopIcon)
        {
            tvs = lic.tallestTopMember().getTopVerticalSize();
        }
        else
        {
            tvs = lic.tallestTopMember().getVerticalSize();
        }


        // doing the override for special case where this loop icon does not have a proper member but its begin and end icon
        jlc.getRightWire().setType(2);

        lic.setTopVerticalSize(tvs);
        // the wires for this loop icon
        Wire left_wire = new Wire();
        left_wire.setType(3);
        left_wire.setLeftIcon(jlc);
        left_wire.setRightIcon(lic);
        jlc.setBottomWire(left_wire);
        jlc.setBottomNeighbour(lic);

        Wire right_wire = new Wire();
        right_wire.setType(4);
        right_wire.setLeftIcon(lic); 
        right_wire.setRightIcon(jlc2);
        jlc2.setBottomWire(right_wire);
        jlc2.setBottomNeighbour(lic);

        lic.setBeginIcon(jlc);
        lic.setEndIcon(jlc2);
        lic.setLeftNeighbour(jlc.getLeftNeighbour());
        lic.setRightNeighbour(jlc2.getRightNeighbour()); 
        lic.setLeftWire(left_wire);
        lic.setRightWire(right_wire);

        // setting up the parents
        jlc.setParentIcon(lic);
        jlc2.setParentIcon(lic);

        add(lic);

        wire_list.addWire(left_wire);
        addWire(left_wire);
        wire_list.addWire(right_wire);
        addWire(right_wire);

        jDirectionsLabel.setText("Icon successfully placed");
        squeezeRepeat(lic);
        repositionAllBranches();
        repositionAllRepeat();
        recursiveShiftIconVerticalDown(lic, vertical_size);
        updateAllRepeatNeighbours();
        
        // finally,
        resizeProgWindow(jsp); 

        //repaint();
       // revalidate();    
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }
    
    
    // WARNING : THIS METHOD THAT SHOULD ONLY BE USED WHEN THE ICONS WITHIN THE BRANCH AND THE BRANCH ITSELF HAS BEEN INSERTED ONTO
    // THE PROGRAMMING WINDOW PREVIOUSLY
    // This one is different to the one in window class. This is to be used by the functions where the insertion of icons
    // does not require popup menus for the icons since they are already equipped at this stage
    /**
     * Recursively inserts a branch or a loop and its members
     * @param icon Icon
     * @param tic Icon to the right of icon
     * @param tic2 Icon to the left of icon
     * @param jsp Scroll Pane of this programming window
     */
    public void insertRecursiveBranchLoopIcon(Icon icon, Icon tic, Icon tic2, javax.swing.JScrollPane jsp)
    {
        // temporary variables for the member vectors
        // tmp_members1 for member in loop and if_member in branch
        // tmp_members2 for else_member in branch
                    
        java.util.Vector<Icon> tmp_members1;              
        java.util.Vector<Icon> tmp_members2;            
        
        // calculate the length of the shifting of this icon, vertically and horizontally, and all of its members
        // will follow
            
        int xpos = 0;
        if(tic2 != null && tic != null)
        {
            xpos = gridcalculation.midPoint(tic2.getCoordinate(), tic.getCoordinate());
        }
        int ypos = tic2.getCoordinate().getY()+(tic2.getHeight()/2);  // tic2 and tic should have the same coordinate                        
        
        coord tmp_co = new coord(xpos, ypos);
        coord co = gridcalculation.computeCoord(icon, tmp_co);
        icon.setCoordinate(co);
        
        if(icon instanceof branchIcon)
        {
            // before we continue the icon has to start fresh, i.e. its members vector has to be cleaned and restarted
            // now remove all of this icon's members
            // also restart the vertical sizes
            icon.setTopTopVerticalSize(0);
            icon.setTopBottomVerticalSize(0);
            icon.setBottomTopVerticalSize(0);
            icon.setBottomBottomVerticalSize(0);
            tmp_members1 = icon.getIfMembers();
            tmp_members2 = icon.getElseMembers();
            icon.delAllMembers();
            
            // prepare the join icons
            joinEndBranchIcon jic = (new joinEndBranchIcon()).Clone();
            joinIcon jic1 = (new joinIcon()).Clone();
            joinIcon jic2 = (new joinIcon()).Clone();
            joinIcon jic3 = (new joinIcon()).Clone();
            joinIcon jic4 = (new joinIcon()).Clone();
            
            insertBranchIcon(icon, tic, tic2, jsp, jic, jic1, jic2, jic3, jic4);           
            
            // now insert all of the existing members of this branch
            
            // do the if_part
            for(int i=1; i<(tmp_members1.size()-1); i++)
            {
                Icon tmp_icon = tmp_members1.get(i);
                if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
                {                    
                    insertRecursiveBranchLoopIcon(tmp_icon, icon.getEndBranch().getLeftNeighbourTop(), icon.getEndBranch().getLeftNeighbourTop().getLeftNeighbour(), jsp);
                }
                else
                {
                    int xpos2 = gridcalculation.calculateXright(icon.getEndBranch().getLeftNeighbourTop().getCoordinate().getX());
                    int ypos2 = gridcalculation.calculateY(icon.getEndBranch().getLeftNeighbourTop().getCoordinate().getY());
                    setUpIcon(tmp_icon, xpos2, ypos2);
                    
                    insertObjectIcon(tmp_icon, icon.getEndBranch().getLeftNeighbourTop(), icon.getEndBranch().getLeftNeighbourTop().getLeftNeighbour(), jsp);
                }
            }
            // then do the else_part
            for(int i=1; i<(tmp_members2.size()-1); i++)
            {
                Icon tmp_icon = tmp_members2.get(i);
                if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
                {                    
                    insertRecursiveBranchLoopIcon(tmp_icon, icon.getEndBranch().getLeftNeighbourBottom(), icon.getEndBranch().getLeftNeighbourBottom().getLeftNeighbour(), jsp);
                }
                else
                {
                    int xpos2 = gridcalculation.calculateXright(icon.getEndBranch().getLeftNeighbourBottom().getCoordinate().getX());
                    int ypos2 = gridcalculation.calculateY(icon.getEndBranch().getLeftNeighbourBottom().getCoordinate().getY());
                    setUpIcon(tmp_icon, xpos2, ypos2);
                    insertObjectIcon(tmp_icon, icon.getEndBranch().getLeftNeighbourBottom(), icon.getEndBranch().getLeftNeighbourBottom().getLeftNeighbour(), jsp);
                }    
            }
        }
        else if(icon instanceof loopIcon)
        {
            tmp_members1 = icon.getMembers();
            icon.setTopVerticalSize(0);
            icon.setBottomVerticalSize(0);
            icon.delAllMembers();
            
            // prepare the joinLoopIcons
            joinLoopIcon jlc = (new joinLoopIcon()).Clone();
            joinLoopIcon jlc2 = (new joinLoopIcon()).Clone();
            insertLoopIcon(icon, tic, tic2, tic, tic2, jsp, jlc, jlc2);    // re-insert the loop as if it was empty
            
            // now insert all of the existing members of this loop
            
            for(int i=1; i<(tmp_members1.size()-1); i++)
            {
                Icon tmp_icon = tmp_members1.get(i);
                if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
                {                    
                    insertRecursiveBranchLoopIcon(tmp_icon, icon.getEndIcon(), icon.getEndIcon().getLeftNeighbour(), jsp);
                }
                else
                {
                    int xpos2 = gridcalculation.calculateXright(icon.getEndIcon().getCoordinate().getX());
                    int ypos2 = gridcalculation.calculateY(icon.getEndIcon().getCoordinate().getY());
                    setUpIcon(tmp_icon, xpos2, ypos2);
                    
                    insertObjectIcon(tmp_icon, icon.getEndIcon(), icon.getEndIcon().getLeftNeighbour(), jsp);
                }
            }
        }        
    }
    
    /**
     * Special deletion of functions as called by the the function window
     * @param icon Function icon to be deleted
     * @param jScrollPane1 Scroll Pane of the programming window
     */
    public void deleteFunctionIcon(Icon icon, javax.swing.JScrollPane jScrollPane1)
    {
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));

        // reconnect the neighbouring icons
        icon.getLeftNeighbour().setRightNeighbour(icon.getRightNeighbour());

        icon.getRightNeighbour().setLeftNeighbour(icon.getLeftNeighbour());

        // recursively shift the icons
        recursiveShiftIconHorizontalLeft(icon, 1);
        // collapse the wires
        collapseWire(icon.getLeftNeighbour(), icon.getRightNeighbour());

        // remove this icon from its parent's vector of member icons                                  

        deleteIcon(icon);

        icon.getParentIcon().delMember(icon);
        icon_list.delIcon(icon);
        remove(icon);
        repositionAllBranches();
        repositionAllRepeat();

        jDirectionsLabel.setText("Icon successfully deleted");

        // resize the programming window
        resizeProgWindow(jScrollPane1); 
        updateAllRepeatNeighbours();
        repaint();                
        revalidate();
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }
    
    /**
     * Ensure that the programming window is in the appropriate size for any kind of icons layout or configuration
     * @param jScrollPane1 Scroll pane of the programming window
     */
    public void resizeProgWindow(javax.swing.JScrollPane jScrollPane1)
    {
        Icon tmp_icon = end_ic;  // the ending icon always provide the rightmost icon (index = 2)
        
//        int maxX = gridcalculation.calculateXright(tmp_icon.getCoordinate().getX()) + (Constants.gridDistanceX * 2 ); 
        int maxX = gridcalculation.calculateXright(tmp_icon.getCoordinate().getX()) + Constants.gridDistanceX; 
        int maxY = gridcalculation.calculateY(tmp_icon.getCoordinate().getY()) + Constants.gridDistanceY;
        // check for the bottom most icons or piece of wire to find the maxY
        // no need to take the top most icons into consideration, since before this method is called upon,
        // the icons will always be moved down one grid segment.
        
        java.util.Vector<Icon> tmp_ic_list;
       // tmp_ic_list = icon_list.getIcons();
        tmp_ic_list = getIcons();
        for(int i = 0; i < tmp_ic_list.size(); i++)
        {
            tmp_icon  = tmp_ic_list.get(i);
            if(tmp_icon != null)
            {
                int y = gridcalculation.calculateY(tmp_icon.getCoordinate().getY());
                y += Constants.gridDistanceY;
                if(y > maxY)
                {
                    maxY = y;
                }
            }            
        }
                
        int minY = 0;  // needed for the vertical repositioning of all icons
        // now check the wiring (only the branches wiring, the normal wiring's y values would not be bigger than any icon's y values)
        java.util.Vector<Wire> tmp_wr_list;
       // tmp_wr_list = wire_list.getWires();
        tmp_wr_list = win_wire;
        Wire topmostWire = null;
        int firsttime = 0;  // minY is not assigned yet
        for(int i = 0; i < tmp_wr_list.size(); i++)
        {
            Wire tmp_wire = tmp_wr_list.get(i);
            if(tmp_wire != null)
            {
                int y = gridcalculation.calculateY(tmp_wire.getBeginCoordinate().getY());
                if(firsttime == 0)
                {
                    firsttime = 1;
                    topmostWire = tmp_wire;
                    minY = y;
                }
                else
                {
                    if(y < minY)
                    {
                        topmostWire = tmp_wire;
                        minY = y; 
                    }                   
                }

                y += Constants.gridDistanceY;
                if(y > maxY)
                {
                    maxY = y;
                }
                
                // ---
                
                y = gridcalculation.calculateY(tmp_wire.getEndCoordinate().getY());
                if(firsttime == 0)
                {
                    firsttime = 1;
                    topmostWire = tmp_wire;
                    minY = y;
                }
                else
                {
                    if(y < minY)
                    {
                        topmostWire = tmp_wire;
                        minY = y;
                    }                   
                }
                y += Constants.gridDistanceY;
                if(y > maxY)
                {
                    maxY = y;
                }
            }            
        }
        
        // check whether it is necessary to shift all icons up or down
        int n_shift = minY; 
        if(n_shift < Constants.gridDistanceY)
        {
            n_shift = (0 - minY) / Constants.gridDistanceY + 1;
            shiftIconsVertical(n_shift);
            maxY += n_shift * Constants.gridDistanceY;
        }
        else if(n_shift > Constants.gridDistanceY)
        {
            n_shift = minY / Constants.gridDistanceY - 1;
            shiftIconsVertical(-n_shift);
            maxY -= n_shift * Constants.gridDistanceY;
        }
        
        // LATER GOTTA CHECK FOR TOPMOST ICON DELETION TO ENSURE THE TOP MOST PIECES WIRE & ICONS ARE A DISTANCE-Y FROM THE TOP
        
        int scrollMaxX = 0;
        int scrollMaxY = 0;
        if((maxX + Constants.scrollbarWidth) > Constants.maxHorizontalProgWindow)
        {
            scrollMaxX = Constants.maxHorizontalProgWindow;
        }
        else
        {
            scrollMaxX = maxX + Constants.scrollbarWidth;
        }
        if((maxY + Constants.scrollbarWidth) > Constants.maxVerticalProgWindow)
        {
            scrollMaxY = Constants.maxVerticalProgWindow;
        }
        else
        {
            scrollMaxY = maxY + Constants.scrollbarWidth;
        }

        jScrollPane1.setMinimumSize(new java.awt.Dimension(scrollMaxX, scrollMaxY));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(scrollMaxX, scrollMaxY));
        jScrollPane1.setSize(new java.awt.Dimension(scrollMaxX, scrollMaxY)); 

        setArea(new java.awt.Dimension(maxX, maxY)); 
        repaint();
        revalidate();
    }
    
    
    // ---------------------------------
    
    // methods that overrides the MouseListener and MouseMotionListener
    // might be needed for future development
    
    /**
     * Handles mouse clicks
     * @param e MouseEvent
     */
    public void mouseClicked(java.awt.event.MouseEvent e) {
    }
    /**
     * Handles mouse over enter
     * @param e MouseEvent
     */
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }
    /**
     * Handles mouse over exit
     * @param e MouseEvent
     */
    public void mouseExited(java.awt.event.MouseEvent e) {
    }    
    /**
     * Handles mouse press
     * @param e MouseEvent
     */
    public void mousePressed(java.awt.event.MouseEvent e) {
    }    
    /**
     * Handles mouse release
     * @param e MouseEvent
     */
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }
    /**
     * Handle mouse drag
     * @param e MouseEvent
     */
    public void mouseDragged(java.awt.event.MouseEvent e) {        
    }
    /**
     * Handle mouse movements
     * @param e MouseEvent
     */
    public void mouseMoved(java.awt.event.MouseEvent e) {
    }
}