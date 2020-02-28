/*
 * function.java
 *
 * Created on 24 September 2005, 22:03
 *
 */

package funsoftware.functs;

import funsoftware.ic.*;
import funsoftware.ic.bran.*;
import funsoftware.ic.loo.*;
import funsoftware.ic.others.*;
import funsoftware.wr.*;
import funsoftware.gri.*;
import funsoftware.inter.*;
import funsoftware.consts.*;
import funsoftware.ic.var.*;
import funsoftware.st.*;
import funsoftware.ic.func.*;
import funsoftware.pallette.*;
import funsoftware.struct.*;
import funsoftware.var.*;


/**
 * This class represent each function. Each function contains icons and wires.
 * The purpose of having functions is to increase the visibility of fUNSoftWare programs
 * @author Thomas Legowo
 * 
 */
public class function extends auxiliary{
    
    private String name;   // name of this function as given by the user
    private int num_id;    // id as given by the FunctionWindow class
    
    // list to keep the local variables of this function
    private local_var_list list_var;
    
    // special private attribute (contains the beginning and end icon so that for the future purposes of functions
    // it will be easily integrated)
    private startFunctionIcon start_icon;
    private endFunctionIcon end_icon;
    
    // the vector that contains the icons
    private java.util.Vector<Icon> ics;
    
    // a vector that is there to preserve the positions of the icons
    private java.util.Vector<coord> backup_coord;
    private ProgWindow prog_panel;
    private javax.swing.JScrollPane scroller;
    private javax.swing.JPanel panel;
    
    // the function window
    private FunctionWindow funcwin;
    
    // each function cannot have a return value, but it can have argument(s)
    private java.util.Vector<variable> arguments;
    
    // the level of the function
    private int level;   // the level starts from 1
    
    /** Creates a new instance of function */
    public function()
    {
        super();
        num_id = 0;   // not assigned originally, this id will be assigned by the function_list (called from ProgWindow class)
        name = "func";
        ics = new java.util.Vector<Icon>();
        backup_coord = new java.util.Vector<coord>();
        start_icon = (new startFunctionIcon()).Clone();
        end_icon = (new endFunctionIcon()).Clone();
        arguments = new java.util.Vector<variable>();   
        list_var = new local_var_list();
        level = 1;  // by default
    }
    
    /**
     * Sets up the beginning, ending and the member icons to be together. Programming window and its connected panels are also set up here
     * @param njDirectionsLabel The real time direction label
     * @param njPanel17 The function window
     * @return The panel on top of the programming window to be
     */
    public javax.swing.JPanel setUpAllIcons(TitlePanelLabel njDirectionsLabel, FunctionWindow njPanel17)
    {
        int ndx = Constants.gridDistanceX;
        int ndy = Constants.gridDistanceY;
        // configure the parent panel
        javax.swing.JPanel jp = new javax.swing.JPanel();
        panel = jp;
        panel.setBackground(new java.awt.Color(255,255,255));
        jp.setLayout(new java.awt.GridBagLayout());
        
        // configure the scrollpane
        javax.swing.JScrollPane jsp = new javax.swing.JScrollPane();
        scroller = jsp;
        jsp.setBackground(new java.awt.Color(255,255,255));
        jsp.setBorder(null);
        jp.add(jsp);
        jp.setPreferredSize(new java.awt.Dimension(Constants.maxHorizontalProgWindow, Constants.maxVerticalProgWindow));
        jp.setMinimumSize(new java.awt.Dimension(Constants.maxHorizontalProgWindow, Constants.maxVerticalProgWindow));
        jp.setOpaque(false);
        
        // configure the programming panel within the JScrollPane
        ProgWindow jP2 = new ProgWindow(njDirectionsLabel);
        jP2.setLayout(null);
        jP2.setArea(new java.awt.Dimension(ndx*4, ndy*2));
        prog_panel = jP2;
        
        int p_width = (int)jP2.getPreferredSize().getWidth();
        int p_height = (int)jP2.getPreferredSize().getHeight();
                
        jsp.setPreferredSize(new java.awt.Dimension(p_width, p_height));
        jsp.setMinimumSize(new java.awt.Dimension(p_width, p_height));
        jP2.setBackground(new java.awt.Color(255,255,255));        
        javax.swing.JViewport viewport = new javax.swing.JViewport();
        viewport.setView(jP2);
        jsp.setViewport(viewport);        
        
        icons_list icon_list = icons_list.Instance();
        wires wire_list = wires.Instance();
        
        // add the start icon
        
        icon_list.addIcon(start_icon);
        
        int xpos = gridcalculation.calculateXleft(ndx); 
        int ypos = gridcalculation.calculateY(ndy);
       
        coord tmp_co = new coord(xpos, ypos);
        
        coord co = gridcalculation.computeCoord(start_icon, tmp_co); 
        start_icon.setCoordinate(co);
        start_icon.setBounds(co.getX(), co.getY(), start_icon.getPreferredSize().width,start_icon.getPreferredSize().height);
        start_icon.setProgWindow(jP2);
        start_icon.setScroller(jsp);
        start_icon.setFunction(this);
        jP2.add(start_icon);
        jP2.addIcon(start_icon);
        jP2.setStartIcon(start_icon);
        
        // add the end icon
        icon_list.addIcon(end_icon);        
                
        // finish up the end icon construction
        xpos = ndx * 2;
        ypos = ndy;
        
        // update the y coordinate of start_ic now that all members have been read
        
        tmp_co = new coord(xpos, ypos);
        co = new coord();
        co = gridcalculation.computeCoord(start_icon, tmp_co); 
        end_icon.setCoordinate(co); 
        end_icon.setBounds(co.getX(), co.getY(), end_icon.getPreferredSize().width, end_icon.getPreferredSize().height);
        end_icon.setFunction(this);
        jP2.addIcon(end_icon);
        jP2.setEndIcon(end_icon);
        
        jP2.add(end_icon);
        
        // add this ending icon as the starting icon's first member
        start_icon.setParentIcon(null); 
        end_icon.setParentIcon(start_icon);   // will be the beginning icon --> instanceof startIcon
        end_icon.getParentIcon().addMember(end_icon, start_icon);
        
        // connect the two initial starting and ending icons
        start_icon.setRightNeighbour(end_icon);
        end_icon.setLeftNeighbour(start_icon);
        
        Wire tmp_wire = new Wire();
        tmp_wire.setLeftIcon(start_icon);
        tmp_wire.setRightIcon(end_icon);
        start_icon.setRightWire(tmp_wire);
        end_icon.setLeftWire(tmp_wire);
        
        wire_list.addWire(tmp_wire);
        jP2.addWire(tmp_wire);
        
        jP2.resizeProgWindow(jsp);
        
        // assign the function window
        funcwin = njPanel17;
        
        // complete the members
        for(int i=0; i<ics.size(); i++)
        {
            Icon tmp_icon = ics.get(i);
            // set the neighbouring icon, left and right
            Icon left;
            if(i > 0)
            {
                left = ics.get(i-1);
                if(left instanceof loopIcon)
                {
                    left = left.getEndIcon();
                }
                else if(left instanceof branchIcon)
                {
                    left = left.getEndBranch();
                }
            }
            else
            {
                left = start_icon;
            }
            Icon right = end_icon;
           
            if(tmp_icon instanceof branchIcon)
            {
                jP2.insertRecursiveBranchLoopIcon(tmp_icon, right, left, jsp);
            }
            else if(tmp_icon instanceof loopIcon)
            {
                jP2.insertRecursiveBranchLoopIcon(tmp_icon, right, left, jsp);
            }
            else
            {
                 // then set the coordinate 
                xpos = gridcalculation.midPoint(left.getCoordinate(), right.getCoordinate());
                ypos = left.getCoordinate().getY()+(left.getHeight()/2);
                tmp_co = new coord(xpos, ypos);
                co = gridcalculation.computeCoord(tmp_icon, tmp_co);
                tmp_icon.setCoordinate(co); 
                tmp_icon.setBounds(co.getX(), co.getY(), tmp_icon.getWidth(), tmp_icon.getHeight());
                jP2.insertObjectIcon(tmp_icon, right, left, jsp);
            }
        }
        return jp;
    }
    
    // for the function icon
    /**
     * Returns the function window
     * @return The function window
     */
    public FunctionWindow getFunctionWindow()
    {
        return funcwin;
    }
    
    // for reinsertion, need to place the panel, jscrollpane and progwindow

    /**
     * Returns the panel this function's member are on, not the individual function's icon itself
     * @return The programming window
     */
    public ProgWindow getProgWindow()
    {
        return prog_panel;
    }
    /**
     * Returns the scroll pane of the programming window
     * @return Programming window's scroller
     */
    public javax.swing.JScrollPane getScroller()
    {
        return scroller;
    }
    /**
     * Returns the panel on top of the programming window
     * @return Programming window's parent panel
     */
    public javax.swing.JPanel getPanel()
    {
        return panel;
    }
    
    /**
     * Alters the level of this function. A function with equal or lower level cannot be called from this function
     * @param l The level of the function
     */
    public void setLevel(int l)
    {
        level = l;
    }
    
    /**
     * Returns the level of the function representing
     * @return function level
     */
    public int getLevel()
    {
        return level;
    }    
    
    
    /**
     * Alters the id of this function
     * @param i New function id
     */
    public void setNumId(int i)
    {
        num_id = i;
    }
    
    /**
     * Returns the id of this function
     * @return function id
     */
    public int getNumId()
    {
        return num_id;
    }
    
    /**
     * Sets the name of this function
     * @param n New function name 
     */
    public void setName(String n)
    {
        name = n;
    }
    /**
     * Returns this function icon's name
     * @return function name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns the list of icons this function contains
     * @return Set of icons
     */
    public java.util.Vector<Icon> getMembers()
    {
        ics = start_icon.getMembers();
        return ics;
    }
    /**
     * Sets the members of this function
     * @param nics Set of icons
     */
    public void setMembers(java.util.Vector<Icon> nics)
    {
        ics = nics;
        for(int i=0; i<ics.size(); i++)
        {
            Icon tmp = ics.get(i);
            backup_coord.addElement(new coord(tmp.getCoordinate().getX(), tmp.getCoordinate().getY()));
        }
    }
    
    // necessary to return start and end icon
    /**
     * Gets the begin icon
     * @return Begin icon
     */
    public Icon getBeginIcon()
    {
        return start_icon;
    }
    
    /**
     * Gets the end icon
     * @return End icon
     */
    public Icon getEndIcon()
    {
        return end_icon;
    }
    
    // for function duplication
    /**
     * Sets the begin icon
     * @param ic Begin icon
     */
    public void setBeginIcon(startFunctionIcon ic)
    {
        start_icon = ic;
    }
    /**
     * Sets the end icon
     * @param ic End icon
     */
    public void setEndIcon(endFunctionIcon ic)
    {
        end_icon = ic;
    }
    
    /**
     * Set the panels of this function
     * @param p Programming window
     * @param s Scroll pane on top of the programming window
     * @param pan A panel on top of the programming window
     */
    public void setPanels(ProgWindow p, javax.swing.JScrollPane s, javax.swing.JPanel pan)
    {
        prog_panel = p;
        scroller = s;
        panel = pan;
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
        algo += "function";
        algo += id_separator;
        algo += num_id;        
        algo += id_att_separator;
        algo += att_boundary_begin;
        algo += "name"+att_value_connector;
        algo += name;
        algo += att_separator;
        algo += "level"+att_value_connector;
        algo += level;       
        algo += att_boundary_end;
        return algo;
    }
    
    /**
     * Returns the function header of this function
     * @return The NQC Code functions
     */
    public String getNQCFunctionHeader()
    {
        String header = new String();
        header += "\n/*\n     an auxiliary function\n*/\n";
        header += "void "+name;
        if(arguments.size() == 0)
        {
            header += "()";
        }
        else
        {
            header += "(";
            for(int i=0; i<arguments.size(); i++)
            {
                header += "int ";
                header += arguments.get(i).getName();
                if(i != arguments.size()-1)  // not the last one
                {
                    header += ", ";    
                }                
            }
            header += ")";
        }
        return header;
    }
    
    /**
     * To get the local variable declarations in NQC format
     * @return String representation of the variable declarations
     */
    public String getNQCLocalVar()
    {
        String ret_val = new String();
               
        if(list_var.isEmpty() == false)
        {
            ret_val += "\n\t// The local variable declarations\n";

            java.util.Vector<variable> tmp_list_var = list_var.getVariables();
            for(int i=1; i<tmp_list_var.size(); i++)
            {
                variable tmp_v = tmp_list_var.get(i);
                if(tmp_v != null && tmp_v.getType() == 1)
                {
                    ret_val += "\t";
                    ret_val += tmp_v.getNQCDeclaration();    
                }            
            }            
        }
        return ret_val;
    }
    
    /**
     * To clone this function infrastructure
     * @return New instance of this function
     */
    public function Clone()
    {
        return new function();
    }
    
    /**
     * To add a local variable for this function
     * @param nv The variable to be added
     */
    public void addVariable(variable nv)
    {
        list_var.addVariable(nv);
    }
    
    /**
     * To delete a local variable from this function
     * @param nv Variable to be deleted
     */
    public void delVariable(variable nv)
    {
        list_var.delVariable(nv.getNumId());
    }
    
    /**
     * To return the list of the local variables that this function owns
     * @return The list
     */
    public java.util.Vector<variable> getVariables()
    {
        return list_var.getVariables();
    }   
  
    /**
     * To set the list of the local variables list manager that this function owns
     * @param lvl The local variable list manager
     */
    public void setLocalVarList(local_var_list lvl)
    {
        list_var = lvl;
    }    
    
    /**
     * To return the list of the local variables list manager that this function owns
     * @return Local variable list
     */
    public local_var_list getLocalVarList()
    {
        return list_var;
    }    
    
    /**
     * To add an argument onto the list
     * @param nv Argument
     */
    public void addArgument(variable nv)
    {
        arguments.addElement(nv);
    }
    
    /**
     * To delete an argument from this function's argument list
     * @param nv Argument to be deleted
     */
    public void delArgument(variable nv)
    {
        int del_pos = 0;
        for(int i=0; i<arguments.size(); i++)
        {
            variable tmp = arguments.get(i);
            if(tmp.getNumId() == nv.getNumId())
            {
                del_pos = i;
                break;
            }
        }
        arguments.removeElementAt(del_pos);
    }
    
    /**
     * To return the list of the local variables that this function owns as its arguments
     * @return List of arguments
     */
    public java.util.Vector<variable> getArguments()
    {
        return arguments;
    }    
        
    /**
     * To return an individual local variable that this auxiliary structure has
     * @param name The name of the variable
     * @return The variable to be returned
     */
    public variable getVariable(String name)
    {
        variable tmp_var = list_var.getVariable(name);
        return tmp_var;
    }    
}
