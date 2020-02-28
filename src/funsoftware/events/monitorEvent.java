/*
 * monitorEvent.java
 *
 * Created on 22 February 2006, 16:13
 *
 */

package funsoftware.events;

import funsoftware.ic.*;
import funsoftware.ic.bran.*;
import funsoftware.ic.loo.*;
import funsoftware.ic.others.*;
import funsoftware.gri.*;
import funsoftware.inter.*;
import funsoftware.consts.*;
import funsoftware.ic.var.*;
import funsoftware.pallette.*;
import funsoftware.st.*;
import funsoftware.ic.ev.*;
import funsoftware.struct.*;
import funsoftware.var.*;
import funsoftware.wr.*;

/**
 * This class represents a monitor event instance at the string of icons it executes if the monitored event is
 * not yet satisfied.
 * @author Thomas Legowo
 */
public class monitorEvent extends auxiliary{
    
    private String name;   // name of this event as given by the user
    private int num_id;    // id as given by the eventWindow class
    
    // special private attribute (contains the beginning and end icon so that for the future purposes of events, 
    // it will be easily integrated)
    private startEventIcon start_icon;
    private endEventIcon end_icon;
        
    // the vector that contains the icons
    private java.util.Vector<Icon> ics;
    
    // a vector that is there to preserve the positions of the icons
    private java.util.Vector<coord> backup_coord;
    private ProgWindow prog_panel;
    private javax.swing.JScrollPane scroller;
    private javax.swing.JPanel panel;
    
    // the event monitor window
    private monitorEventWindow ev_win;
    
    // for the NQC
    private String type;   // Local or global
    private auxiliary aux_point;  // if local, this is not null
    
    // a list of events to be monitored by an instance of this class
    private java.util.Vector<Event> tobe_monitored;
    
    // the level of the event monitor
    private int level;   // the level starts from 1
    
    /** Creates a new instance of monitorEvent */
    public monitorEvent()
    {
        super();
        num_id = 0;   // not assigned originally, this id will be assigned by the auxiliary list
        name = "em";
        ics = new java.util.Vector<Icon>();
        backup_coord = new java.util.Vector<coord>();
        start_icon = (new startEventIcon()).Clone();
        end_icon = (new endEventIcon()).Clone(); 
        tobe_monitored = new java.util.Vector<Event>();
        type = "Global";
        level = 1;  // by default
        aux_point = null;
    }
    
    /**
     * Sets up the beginning, ending and the member icons to be together. Programming window and its connected panels are also set up here
     * @param njDirectionsLabel The real time direction label
     * @param njPanel17 The event monitor window
     * @return The panel on top of the programming window to be
     */
    public javax.swing.JPanel setUpAllIcons(TitlePanelLabel njDirectionsLabel, monitorEventWindow njPanel17)
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
        start_icon.setEventMonitor(this);
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
        end_icon.setEventMonitor(this);
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
        
        // assign the event monitor body window
        ev_win = njPanel17;
        
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
    
    // for reinsertion, need to place the panel, jscrollpane and progwindow    

    /**
     * Get the programming window displaying this Event monitor body
     * @return The programming window
     */
    public monitorEventWindow getEventMonitorWindow()
    {
        return ev_win;
    }
    
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
     * Alters the id of the event this icon is representing
     * @param i New task id
     */
    public void setNumId(int i)
    {
        num_id = i;
    }
    
    /**
     * Returns the id of the event this icon is representing
     * @return task id
     */
    public int getNumId()
    {
        return num_id;
    }
    
    
    /**
     * Sets the major elements of this event
     * @param type The type (global or local)
     * @param aux The auxiliary related to this event
     */    
    public void setEventElements(String type, auxiliary aux) 
    {
        this.type = type;
        this.aux_point = aux;
    }
    
    /**
     * Alters the level of this event monitor. Only a function with equal or lower level can be called from this event monitor
     * (To avoid recursion in function calls through an event monitor call)
     * @param l The level of the event monitor
     */
    public void setLevel(int l)
    {
        level = l;
    }
    
    /**
     * Returns the level of the event monitor representing
     * @return event monitor level
     */
    public int getLevel()
    {
        return level;
    }
    
    /**
     * Returns this event's type
     * @return event type
     */    
    public String getType()
    {
        return type;
    }    
    
    /**
     * Returns this event's auxiliary points
     * @return event auxiliary point
     */    
    public auxiliary getAuxPoint()
    {
        return aux_point;
    }
 
    /**
     * Returns this monitor's events
     * @param n The new list of monitored events
     */    
    public void setMonitoredEvents(java.util.Vector<Event> n)
    {
        tobe_monitored = n;
    }    
    
    /**
     * Returns this monitor's events
     * @return event this monitor's events
     */    
    public java.util.Vector<Event> getMonitoredEvents()
    {
        return tobe_monitored;
    }    
    
    /**
     * Sets the name of this event monitor
     * @param n New event monitor name 
     */
    public void setName(String n)
    {
        name = n;
    }
    /**
     * Returns this event monitor's name
     * @return event monitor name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns the list of icons this event contains
     * @return Set of icons
     */
    public java.util.Vector<Icon> getMembers()
    {
        ics = start_icon.getMembers();
        return ics;
    }
    /**
     * Sets the members of this event monitor icon
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
    
    // for event duplication
    /**
     * Sets the begin icon
     * @param ic Begin icon
     */
    public void setBeginIcon(startEventIcon ic)
    {
        start_icon = ic;
    }
    /**
     * Sets the end icon
     * @param ic End icon
     */
    public void setEndIcon(endEventIcon ic)
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
     * Get the help title of this event
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Event";
        return s;
    }
    
    /**
     * Get help message of this event
     * @return Help message
     */    
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nName: ";
        s+=name;
        s+="\nType: ";
        s+=type;
        s+="\nLevel: ";
        s+=level;        
        if(type.compareTo("Local") == 0)
        {
            s+="\nFunction or Task: ";
            s+=aux_point.getName();
        }   
        s+="\n\nEvents monitored: ";
        for(int i=0; i<tobe_monitored.size(); i++)
        {
            s+="\n"+tobe_monitored.get(i).getName();
        }
        return s;
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
        algo += "monitorevent";
        algo += id_separator;
        algo += num_id;        
        algo += id_att_separator;
        algo += att_boundary_begin;
        algo += "name"+att_value_connector;
        algo += name;
        algo += att_separator;        
        algo += "type"+att_value_connector;
        algo += type;    
        algo += att_separator;
        algo += "level"+att_value_connector;
        algo += level;  
        algo += att_separator;        
        algo += "auxiliary"+att_value_connector;
        if(aux_point == null)
        {
            algo += ".";  // means it is empty
        }
        else
        {
            algo += aux_point.getName();       
        }   
        algo += att_separator;   
        algo += "numbermonitored"+att_value_connector;
        algo += tobe_monitored.size();
        // put the list of events to be monitored here
        for(int i=0; i<tobe_monitored.size(); i++)
        {
            algo += att_separator;        
            algo += "monitored"+att_value_connector;
            algo += tobe_monitored.get(i).getName();
        }
        
        algo += att_boundary_end;
        return algo;
    }
    
    /**
     * Returns the event header of this event
     * @return The NQC Code events
     * @param indentation How many indentations
     */
    public String getNQCEventHeader(String indentation)
    {
        // due to the id from the event tracker list to start from zero, for the purpose of translation it will be substracted 1
        String header = new String();
        
        for(int i=0; i<tobe_monitored.size(); i++)
        {
            header+=tobe_monitored.get(i).getNQCCode(indentation);
        }
        header += indentation+"// an event monitor\n";
        header += indentation+"monitor(";
        
        for(int i=0; i<tobe_monitored.size(); i++)
        {
            header += "EVENT_MASK("+(tobe_monitored.get(i).getEventId()-1)+")";
            if(i == tobe_monitored.size()-1)
            {
                header += ")";
            }
            else
            {
                header += " | ";
            }
        }        
        return header;
    }
    
    /**
     * To clone this event infrastructure
     * @return New instance of this event
     */
    public monitorEvent Clone()
    {
        return new monitorEvent();
    }

    
    /**
     * Checks whether this event monitor monitors an event that corresponds to a given id
     * @param id The event id to be checked
     * @return True or false
     */
    public Boolean checkEventUsage(int id)
    {
        for(int i=0; i<tobe_monitored.size(); i++)
        {
            Event tmp = tobe_monitored.get(i);
            if(tmp.getEventId() == id)
            {
                return true;
            }
        }
        return false;
    }
}
