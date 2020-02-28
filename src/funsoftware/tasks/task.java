/*
 * task.java
 *
 * Created on 24 September 2005, 22:03
 *
 */

package funsoftware.tasks;

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
import funsoftware.ic.tsk.*;
import funsoftware.pallette.*;
import funsoftware.struct.*;
import funsoftware.var.*;


/**
 * This class represent a task. Each task contains icons and wires.
 * The purpose of having tasks is to enable the feature of having parallel tasks
 * @author Thomas Legowo 
 * 
 */
public class task extends auxiliary{
    
    private String name;   // name of this task as given by the user
    private int num_id;    // id as given by the TaskWindow class
    
    // special private attribute (contains the beginning and end icon so that for the future purposes of tasks
    // it will be easily integrated)
    private startTaskIcon start_icon;
    private endTaskIcon end_icon;
    
    // list to keep the local variables of this function
    private local_var_list list_var;    
    
    // the vector that contains the icons
    private java.util.Vector<Icon> ics;
    
    // a vector that is there to preserve the positions of the icons
    private java.util.Vector<coord> backup_coord;
    private ProgWindow prog_panel;
    private javax.swing.JScrollPane scroller;
    private javax.swing.JPanel panel;
    
    // the task window
    private TaskWindow tskwin;
    
    /** Creates a new instance of task */
    public task()
    {
        super();
        num_id = 0;   // not assigned originally, this id will be assigned by the task_list (called from ProgWindow class)
        name = "task";
        ics = new java.util.Vector<Icon>();
        backup_coord = new java.util.Vector<coord>();
        start_icon = (new startTaskIcon()).Clone();
        end_icon = (new endTaskIcon()).Clone();
        list_var = new local_var_list();     
    }
    
    /**
     * Sets up the beginning, ending and the member icons to be together. Programming window and its connected panels are also set up here
     * @param njDirectionsLabel The real time direction label
     * @param njPanel17 The task window
     * @return The panel on top of the programming window to be
     */
    public javax.swing.JPanel setUpAllIcons(TitlePanelLabel njDirectionsLabel, TaskWindow njPanel17)
    {
        int ndx = Constants.gridDistanceX;
        int ndy = Constants.gridDistanceY;
        // configure the parent panel
        javax.swing.JPanel jp = new javax.swing.JPanel();
        panel = jp;
        panel.setLayout(new java.awt.GridBagLayout());
        // configure the scrollpane
        javax.swing.JScrollPane jsp = new javax.swing.JScrollPane();

        scroller = jsp;

        jsp.setBorder(null);

        panel.add(jsp);
        panel.setPreferredSize(new java.awt.Dimension(Constants.maxHorizontalProgWindow, Constants.maxVerticalProgWindow));
        panel.setMinimumSize(new java.awt.Dimension(Constants.maxHorizontalProgWindow, Constants.maxVerticalProgWindow));
        panel.setOpaque(false);
        panel.setBackground(new java.awt.Color(255,255,255));
        // configure the programming panel within the JScrollPane
        ProgWindow jP2 = new ProgWindow(njDirectionsLabel);
        jsp.setBackground(new java.awt.Color(255,255,255));
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
        start_icon.setTask(this);
        jP2.add(start_icon);
        jP2.addIcon(start_icon);
        jP2.setStartIcon(start_icon);
        
        // add the end icon
        icon_list.addIcon(end_icon);        
                
        // finish up the end icon construction
        xpos = ndx*2;
        ypos = ndy;
        
        // update the y coordinate of start_ic now that all members have been read
        
        tmp_co = new coord(xpos, ypos);
        co = new coord();
        co = gridcalculation.computeCoord(start_icon, tmp_co); 
        end_icon.setCoordinate(co); 
        end_icon.setBounds(co.getX(), co.getY(), end_icon.getPreferredSize().width, end_icon.getPreferredSize().height);
        end_icon.setTask(this);
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
        
        // assign the task window
        tskwin = njPanel17;
        
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
    
    // for the task icon
    /**
     * Returns the task window associated to this task
     * @return The task window
     */
    public TaskWindow getTaskWindow()
    {
        return tskwin;
    }
    
    // for reinsertion, need to place the panel, jscrollpane and progwindow

    /**
     * Returns the panel this task's member are on, not the individual task's icon itself
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
     * Alters the id of the task this icon is representing
     * @param i New task id
     */
    public void setNumId(int i)
    {
        num_id = i;
    }
    
    /**
     * Returns the id of the task this icon is representing
     * @return task id
     */
    public int getNumId()
    {
        return num_id;
    }
    
    /**
     * Sets the name of this task
     * @param n New task name 
     */
    public void setName(String n)
    {
        name = n;
    }
    /**
     * Returns this task icon's name
     * @return task name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns the list of icons this task contains
     * @return Set of icons
     */
    public java.util.Vector<Icon> getMembers()
    {
        ics = start_icon.getMembers();
        return ics;
    }
    /**
     * Sets the members of this task icon
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
    
    // for task duplication
    /**
     * Sets the begin icon
     * @param ic Begin icon
     */
    public void setBeginIcon(startTaskIcon ic)
    {
        start_icon = ic;
    }
    /**
     * Sets the end icon
     * @param ic End icon
     */
    public void setEndIcon(endTaskIcon ic)
    {
        end_icon = ic;
    }
    
    /**
     * Set the panels of this task
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
        algo += "task";
        algo += id_separator;
        algo += num_id;        
        algo += id_att_separator;
        algo += att_boundary_begin;
        algo += "name"+att_value_connector;
        algo += name;
        algo += att_boundary_end;

        return algo;
    }
    
    /**
     * Returns the task header of this task
     * @return The NQC Code tasks
     */
    public String getNQCTaskHeader()
    {
        String header = new String();
        if(num_id != 1)
        {
            header += "\n/*\n     a sub task\n*/";
        }    
        else if(num_id == 1)
        {
            header += "\n/*\n     the main task\n*/";
        }
        header += "\ntask "+name+"()";
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
                if(tmp_v != null)
                {
                    ret_val += "\t";
                    ret_val += tmp_v.getNQCDeclaration();                
                }
            }
        }
        return ret_val;
    }    
    
    /**
     * To clone this task infrastructure
     * @return New instance of this task
     */
    public task Clone()
    {
        return new task();
    }
    
    /**
     * To add a local variable onto the list
     * @param nv The variable to be added
     */
    public void addVariable(variable nv)
    {
        list_var.addVariable(nv);
    }
    
    /**
     * To delete a local variable from this function
     * @param nv The variable to be deleted
     */
    public void delVariable(variable nv)
    {
        list_var.delVariable(nv.getNumId());
    }   
    
    /**
     * To return the list of the local variables that this task owns
     * @return The list of the local variables
     */
    public java.util.Vector<variable> getVariables()
    {
        return list_var.getVariables();
    }  
    
    /**
     * To return the list of the local variables list manager that this task owns
     * @return The list of the local variables list manager
     */
    public local_var_list getLocalVarList()
    {
        return list_var;
    }    
    
    /**
     * To return an individual local variable that this task has
     * @param name The variable name
     * @return The local variable
     */
    public variable getVariable(String name)
    {
        variable tmp_var = list_var.getVariable(name);
        return tmp_var;
    }
}
