/*
 * Icon.java
 *
 * Created on 14 July 2005, 19:47
 */

package funsoftware.ic;

import funsoftware.gri.*;
import funsoftware.wr.*;
import funsoftware.functs.*;
import funsoftware.tasks.*;
import funsoftware.struct.*;
import funsoftware.events.*;
import funsoftware.ic.func.*;
import funsoftware.ic.tsk.*;
import funsoftware.ic.ev.*;
import funsoftware.var.*;

/**
 * This class represent an icon and its main and auxiliary attributes
 * Other icons would simply extend this class and implement most of this method
 * @author Thomas Legowo *
 * 
 */
public class Icon extends javax.swing.JPanel {
    
    // private attributes or variables
    
    private String id;   // icon's id, the id is a unique identifier, it will be a number that is preceeded by the
                         // letter I
    private int width;   // the width of the icon
    private int height;  // the height of the icon
    private javax.swing.ImageIcon ic; // the image of this icon
    private coord co; // the coordinate of this icon
    
    private int vertical_size;  // the vertical size an icon takes up. For a repeat branch, it depends on the icons inside it
                                // e.g. a branch or a test icon initially takes up a vertical size of 3
    
    private Icon parent_icon;   // the parent icon of this icon
                                // for a branch(or test) icon, the join icons and all icons within them point to the branch icon
                                // as their parent, the branch icon itself however, points to the other icon that is higher
                                // up in the hierarchy
                                // THE SAME will apply to the loop icons
                                
    private auxiliary aux;  // the auxiliary this icon is operating in
    
    /** Creates a new instance of Icon */
    public Icon()
    {
    }

    /**
     * Creates a new instance of Icon
     * @param filepath The source file of this icon's image
     */
    public Icon(String filepath) {
        // when initialising the id, the checking of whether the id is unique is done outside this class
        // that check will go through the icons class containing all active icons on the programming window
        ic = new javax.swing.ImageIcon(getClass().getResource(filepath));
        vertical_size = 0;  // for all icons, the vertical size is 0, only loops and branches have different sizes and
                            // they will need to invoke the method setVerticalSize(int)
        parent_icon = null; // null only for starting and ending icon where parent icon will not be initialised
        aux = new auxiliary();
    }

    /**
     * Sets the id of this icon
     * @param nid New id
     */
    public void setId(String nid)
    {
        id = nid;
    }
    
    /**
     * Returns the id of this icon without the letter "I"
     * @return Id
     */
    public int getId()
    {
        int ii = Integer.parseInt(id.substring(1));  //  get rid of the I from the id
        return ii;
    }

    /**
     * Returns the full id with the letter "I"
     * @return Full Id
     */
    public String getFullId()
    {
        return id;
    }
    
    /**
     * Set width of this icon
     * @param nwidth New width
     */
    public void setWidth(int nwidth)
    {
        width = nwidth;
    }
    
    /**
     * Set height of this icon
     * @param nheight New height
     */
    public void setHeight(int nheight)
    {
        height = nheight;
    }
    
    /**
     * Get the width of this icon
     * @return Width
     */
    public int getWidth()
    {
        return width;
    }
    
    /**
     * Get the height of this icon
     * @return Height
     */
    public int getHeight()
    {
        return height;
    }
    
    /**
     * Set the coordinate of this icon
     * @param nco New coordinate
     */
    public void setCoordinate(coord nco)
    {
        co = nco;
    }
    

    /**
     * Return the coordinate of this icon
     * @return Coordinate
     */
    public coord getCoordinate()
    {
        return co;
    }
    
    // ---- VERTICAL SIZES --> ONLY the BRANCH ICONS have the 2 TOP and 2 BOTTOM vertical sizes !!!!

    /**
     * For branch and loop icons: Return the vertical size
     * @return Vertical size
     */
    public int getVerticalSize()
    {
        return vertical_size;
    }

    /**
     * For branch and loop icons: Set the vertical size
     * @param nvs New vertical size
     */
    public void setVerticalSize(int nvs)
    {
        vertical_size = nvs;
    }
    
    /**
     * Get the top part of branch top's vertical size
     * @return Top part of branch top's vertical size
     */
    public int getTopTopVerticalSize()
    {
        return 0;
    }
    
    /**
     * Get the top part of branch bottom's vertical size
     * @return Top part of branch bottom's vertical size
     */
    public int getTopBottomVerticalSize()
    {
        return 0;
    }
    
    /**
     * Get the bottom part of branch top's vertical size
     * @return Bottom part of branch top's vertical size
     */
    public int getBottomTopVerticalSize()
    {
        return 0;
    }
    
    /**
     * Get the bottom part of branch bottom's vertical size
     * @return Bottom part of branch bottom's vertical size 
     */
    public int getBottomBottomVerticalSize()
    {
        return 0;
    }
    
    /**
     * Set the top part of branch top's vertical size
     * @param ns New vertical size
     */
    public void setTopTopVerticalSize(int ns)
    {
        
    }
    
    /**
     * Set the top part of branch bottom's vertical size
     * @param ns New vertical size
     */
    public void setTopBottomVerticalSize(int ns)
    {
        
    }
    
    /**
     * Set the bottom part of branch top's vertical size
     * @param ns New vertical size
     */
    public void setBottomTopVerticalSize(int ns)
    {
        
    }
    
    /**
     * Set the bottom part of branch bottom's vertical size
     * @param ns New vertical size
     */
    public void setBottomBottomVerticalSize(int ns)
    {
        
    }
    
    // the methods for vertical repositioning of a repeat
    /**
     * Set top part of a loop icon's vertical size
     * @param nvs New vertical size
     */
    public void setTopVerticalSize(int nvs)
    {
        
    }
    
    /**
     * Set bottom part of a loop icon's vertical size
     * @param nvs New vertical size
     */
    public void setBottomVerticalSize(int nvs)
    {
        
    }
    
    /**
     * Get top part of loop's vertical size
     * @return Top part of loop's vertical size
     */
    public int getTopVerticalSize()
    {
        return 0;
    }
    
    /**
     * Get bottom part of loop's vertical size
     * @return Bottom part of loop's vertical size
     */
    public int getBottomVerticalSize()
    {
        return 0;
    }
    
    // ----------------------
    
    // dealing with the parent icons
    /**
     * Get the parent icon of this icon
     * @return Parent icon
     */
    public Icon getParentIcon()
    {
        return parent_icon;
    }
    
    /**
     * Set the parent icon of this icon
     * @param npi New parent icon
     */
    public void setParentIcon(Icon npi)
    {
        parent_icon = npi;
    }

    /**
     * Get the image of this icon
     * @return Image of this icon
     */
    public javax.swing.ImageIcon getImage()
    {
        return ic;
    }
    
    /**
     * Set the image of this icon
     */
    public void setImage()
    {
        
    }

    /**
     * Set the left neighbour of this icon
     * @param nicon New neighbour
     */
    public void setLeftNeighbour(Icon nicon)
    {
        
    }
    
    /**
     * Set the right neighbour of this icon
     * @param nicon New neighbour
     */
    public void setRightNeighbour(Icon nicon)
    {
        
    }

    /**
     * Set the left wire of this icon
     * @param nwire New wire
     */
    public void setLeftWire(Wire nwire)
    {

    }
    
    /**
     * Set the right wire of this icon
     * @param nwire New wire
     */
    public void setRightWire(Wire nwire)
    {

    }
    
    /**
     * Get left icon neighbour of this icon
     * @return Left icon
     */
    public Icon getLeftNeighbour()
    {
        return null;
    }
    
    /**
     * Get right icon neighbour of this icon
     * @return Right icon
     */
    public Icon getRightNeighbour()
    {
        return null;
    }
    
    /**
     * Get the left wire of this icon
     * @return Left wire
     */
    public Wire getLeftWire()
    {
        return null;
    }
    
    /**
     * Get the right wire of this icon
     * @return Right wire
     */
    public Wire getRightWire()
    {
        return null;
    }
    
    // extra methods for the branching icons
    /**
     * For joinEndBranchIcon : Get left top neighbour of this icon
     * @return Left neighbour top
     */
    public Icon getLeftNeighbourTop()
    {
        return null;
    }
    
    /**
     * For joinEndBranchIcon : Get left bottom neighbour of this icon
     * @return Left neighbour bottom
     */
    public Icon getLeftNeighbourBottom()
    {
        return null;
    }
    
    /**
     * For branch icons : Get right top neighbour of this icon
     * @return Right top neighbour
     */
    public Icon getRightNeighbourTop()
    {
        return null;
    }
    
    /**
     * For branch icons : Get right bottom neighbour of this icon
     * @return Right bottom neighbour
     */
    public Icon getRightNeighbourBottom()
    {
        return null;
    }
    
     /**
      * For joinEndBranchIcon : Get left top wire of this icon
      * @return Left top wire
      */
     public Wire getLeftWireTop()
    {
        return null;
    }
    
    /**
      * For joinEndBranchIcon : Get left bottom wire of this icon
      * @return Left bottom wire
     */
    public Wire getLeftWireBottom()
    {
        return null;
    }
    
    /**
      * For branch icons : Get right top wire of this icon
      * @return Right top wire
     */
    public Wire getRightWireTop()
    {
        return null;
    }
    
    /**
      * For branch icons : Get right bottom wire of this icon
      * @return Right bottom wire
     */
    public Wire getRightWireBottom()
    {
        return null;
    }
    
    /**
     * For joinEndBranchIcon : Set the left neighbour top of this icon
     * @param nicon Left neighbour top
     */
    public void setLeftNeighbourTop(Icon nicon)
    {
        
    }
    
    /**
     * For joinEndBranchIcon : Set the left neighbour bottom of this icon
     * @param nicon Left neighbour bottom
     */
    public void setLeftNeighbourBottom(Icon nicon)
    {
        
    }
    
    /**
     * For branch icons : Set the right neighbour top of this icon
     * @param nicon Right neighbour top
     */
    public void setRightNeighbourTop(Icon nicon)
    {
        
    }
    
    /**
     * For branch icons : Set the right neighbour bottom of this icon
     * @param nicon Right neighbour bottom
     */
    public void setRightNeighbourBottom(Icon nicon)
    {
        
    }
    
     /**
      * For joinEndBranchIcon : Set left wire top of this icon
      * @param nwire Left wire top
      */
     public void setLeftWireTop(Wire nwire)
    {
       
    }
    
    /**
      * For joinEndBranchIcon : Set left wire bottom of this icon
      * @param nwire Left wire bottom
     */
    public void setLeftWireBottom(Wire nwire)
    {
        
    }
    
    /**
      * For branch icons : Set right wire top of this icon
      * @param nwire Right wire top
     */
    public void setRightWireTop(Wire nwire)
    {
        
    }
    
    /**
      * For branch icons : Set right wire bottom of this icon
      * @param nwire Right wire bottom
     */
    public void setRightWireBottom(Wire nwire)
    {
        
    }
    
    // methods for the joinLoopIcon
    /**
     * For joinLoopIcon : Get the loop icon (bottom of this icon visually)
     * @return Bottom neighbour
     */
    public Icon getBottomNeighbour()
    {
        return null;
    }
    
    /**
     * For joinLoopIcon : Set the loop icon (bottom of this icon visually)
     * @param nicon New bottom neighbour
     */
    public void setBottomNeighbour(Icon nicon)
    {
        
    }
    
    // methods for joinEndBranchIcon
    /**
     * For joinEndBranchIcon : Get branch icon of this icon
     * @return Branch icon
     */
    public Icon getBranchIcon()
    {
        return null;
    }
    
    /**
     * For joinEndBranchIcon : Set branch icon of this icon
     * @param nicon Branch icon
     */
    public void setBranchIcon(Icon nicon)
    {
        
    }
    
    // methods for the branch or test icon
    /**
     * For branch icons : Get end branch icon of this icon
     * @return End branch icon
     */
    public Icon getEndBranch()
    {
        return null;
    }
    
    /**
     * For branch icons : Set end branch icon of this icon
     * @param neb New end branch icon
     */
    public void setEndBranch(Icon neb)
    {

    }
    
    // method for test/branch icons to deal with its members
    
    /**
     * For branch icons : Determine whether a member of this icon is in top or bottom part of this icon
     * @param icon Member icon
     * @return Determiner
     */
    public int determineMember(Icon icon)
    {
        return 0;
    }
    /**
     * For branch icons : Get the members in the top part of this branch icon
     * @return Set of icons
     */
    public java.util.Vector<Icon> getIfMembers()
    {
        return null;
    }
    
    /**
     * For branch icons : Get the members in the bottom part of this branch icon
     * @return Set of icons
     */
    public java.util.Vector<Icon> getElseMembers()
    {
        return null;
    }
    
    /**
     * For branch icons : Get the icon with the tallest vertical size in top of branch's top part
     * @return Tallest member in top part of branch's top part
     */
    public Icon tallestIfMemberTop()
    {
        return null;
    }
    
    /**
     * For branch icons : Get the icon with the tallest vertical size in bottom of branch's top part
     * @return Tallest member in bottom part of branch's top part
     */
    public Icon tallestIfMemberBottom()
    {
        return null;
    }
        
    /**
     * For branch icons : Get the icon with the tallest vertical size in top of branch's bottom part
     * @return Tallest member in top part of branch's bottom part
     */
    public Icon tallestElseMemberTop()
    {
        return null;
    }
    
    /**
     * For branch icons : Get the icon with the tallest vertical size in bottom of branch's bottom part
     * @return Tallest member in bottom part of branch's bottom part
     */
    public Icon tallestElseMemberBottom()
    {
        return null;
    }
    
    // now methods for the loopIcons that deals with its members and neighbours
    
    /**
     * Special for loopIcons : To set left neighbour
     */
    public void setLeftNeighbour()
    {
        
    }
    
    /**
     * Special for loopIcons : To set right neighbour
     */
    public void setRightNeighbour()
    {
        
    }
    
    /**
     * For loop icons : Get begin icon of this icon
     * @return Begin icon
     */
    public Icon getBeginIcon()
    {
        return null;
    }
    
    /**
     * For loop icons : Get end icon of this icon
     * @return End icon
     */
    public Icon getEndIcon()
    {
        return null;
    }
    
    /**
     * For loop icons : Set the begin icon of this icon
     * @param nicon New begin icon
     */
    public void setBeginIcon(Icon nicon)
    {
       
    }
    
    /**
     * For loop icons : Set the end icon of this icon
     * @param nicon New end icon
     */
    public void setEndIcon(Icon nicon)
    {
        
    }
    
    /**
     * For startIcon and loop icons : Get members of this icon
     * @return Set of icons
     */
    public java.util.Vector<Icon> getMembers()
    {
        return null;
    }
    
    // methods by the repeat Icons needed for the automatic vertical repositioning
    /**
     * For loop icons : Get the member with the tallest vertical size in the bottom part of this icon
     * @return Icon
     */
    public Icon tallestBottomMember()
    {
        return null;
    }
    
    /**
     * For loop icons : Get the member with the tallest vertical size in the top part of this icon
     * @return Icon
     */
    public Icon tallestTopMember()
    {
        return null;
    }
    
    // common methods for repeat and branches
    
    /**
     * To add a new member icon to this icon
     * @param new_icon Icon to be added
     * @param right_icon Icon to the right of the to be added icon
     */
    public void addMember(Icon new_icon, Icon right_icon)
    {
        
    }
    
    // to delete a particular member
    /**
     * To delete a particular member of this icon
     * @param icon Icon to be deleted
     */
    public void delMember(Icon icon)
    {
        
    }

    // to delete all members
    /**
     * To delete all member icons within thi sicon
     */
    public void delAllMembers()
    {
        
    }
    
    /**
     * Turns off mouse listerners for this icon
     */
    public void turnoffListeners()
    {
        
    }

    
    /**
     * Turns on mouse listerners for this icon
     */
    public void turnonListeners()
    {
        
    }

    /**
     * Get the help title of this icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        return null;
    }
    
    /**
     * Get help message of this icon
     * @return Help message
     */
    public String getHelpMsg()
    {
        return null;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */
    public String getHelpDesc()
    {
        return null;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */
    public javax.swing.Icon getHelpIcon()
    {
        return null;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public Icon Clone()
    {
        return null;
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        return null;
    }    
    
    /**
     * Set the auxiliary that this arithmetic operation icon is in
     * @param a Auxiliary to be set
     */
    public void setAux(auxiliary a)
    {
        aux = a;
    }
        
    /**
     * Get the auxiliary that this arithmetic operation icon is in
     * @return Auxiliary (function or task)
     */
    public auxiliary getAux()
    {
        if(aux instanceof monitorEvent)
        {
            monitorEvent tmp = (monitorEvent)aux;
            aux = tmp.getAuxPoint();
            if(aux == null)
            {
                return new auxiliary();
            }
        }
        return aux;
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
        return null;
    }
    
    // for the NQCCode
    /**
     * Returns a string format of the NQCCode representation of this object
     * @param indentation Indentation
     * @return The NQC Code
     */
    public String getNQCCode(String indentation)
    {
        return null;
    }
    
    // for subroutine icons
    /**
     * For subroutine icons : Get the subroutine id of this icon
     * @return Subroutine id
     */
    public int getNumId()
    {
        return 0;
    }
    
    /**
     * For function icons, to set the function this icon is pointing to
     * @param f The function
     */
    public void setFunction(function f)
    {
    }
    
    /**
     * For task icons, to set the task this icon is pointing to
     * @param t The task
     */
    public void setTask(task t)
    {        
    }
    
    /**
     * For event icons, to set the event this icon is pointing to
     * @param ev The event
     */
    public void setEventMonitor(monitorEvent ev)
    {        
    }    
    
    /**
     * To return a panel prompting users to input a variable into one of the icon's attributes
     * @return The label of the panel
     */
    public javax.swing.JLabel getVarLabel()
    {
        return null;
    }  
    
    /**
     * Find the current auxiliary this icon is on
     * @return The auxiliary
     */
    public auxiliary findCurrentAux()
    {
        Icon parent = this.getParentIcon();
        auxiliary ret = new auxiliary();
        while(true)
        {
            if(parent instanceof startFunctionIcon)
            {
                startFunctionIcon sfi = (startFunctionIcon)parent;
                ret = sfi.getFunction();
                break;
            }
            else if(parent instanceof startTaskIcon)
            {
                startTaskIcon sti = (startTaskIcon)parent;
                ret = sti.getTask();
                break;
            }  
            else if(parent instanceof startEventIcon)
            {
                startEventIcon sti = (startEventIcon)parent;
                ret = sti.getEventMonitor().getAuxPoint();
                if(ret == null)
                {
                    ret = new auxiliary();
                }
                break;
            }             
            else
            {
                parent = parent.getParentIcon();
            }            
        } 
        return ret;
    }
    
    /**
     * To indicate whether this icon uses a variable
     * @param var The variable used
     * @return True or false
     */
    public Boolean useAVariable(variable var)
    {
        return false;
    }    
}
