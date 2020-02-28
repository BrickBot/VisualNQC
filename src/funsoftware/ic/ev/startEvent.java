/*
 * startEvent.java
 *
 * Created on 1 February 2006, 13:07
 *
 */

package funsoftware.ic.ev;

import funsoftware.ic.obj.*;
import funsoftware.inter.*;
import funsoftware.ic.*;
import funsoftware.ic.others.*;
import funsoftware.ic.tsk.*;
import funsoftware.ic.func.*;
import funsoftware.consts.*;
import funsoftware.struct.*;
import funsoftware.events.*;
import funsoftware.comp.*;

/**
 * This class represents an icon that starts a specified event
 * @author Thomas Legowo
 */
public class startEvent extends objectIcon{
    
    // private variables
    private int num_id;    // id of the event to be started as given by the eventWindow class        
    private String name;   // name of this event as given by the user
    // for algorithm representation, represent a start event icon with an identifier of 15
    private int identifier;
    
    // the eventmonitor this icon is starting
    private monitorEvent the_event;
    
    /** Creates a new instance of startEvent */
    public startEvent() {
    }
    
    /**
     * Creates a new instance of startEvent
     * @param filepath The filepath of the icon's image
     */
    public startEvent(String filepath) {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        identifier = 15;
        name = "";
        setImage();
    }
    
    /**
     * Alters the id of the event this icon is representing
     * @param i New event id
     */
    public void setNumId(int i)
    {
        num_id = i;
    }
    
    /**
     * Returns the id of the event this icon is representing
     * @return event id
     */
    public int getNumId()
    {
        return num_id;
    }
    
   /**
     * To set the event monitor this icon is pointing to
     * @param ev The event monitor this icon is pointing to
     */
    public void setEventMonitor(monitorEvent ev)
    {
        the_event = ev;
        num_id = the_event.getNumId(); 
        name = the_event.getName(); 
    }    
    
    /**
     * Returns the event monitor this icon is connected to
     * @return The event monitor this icon is connected to
     */
    public monitorEvent getEventMonitor()
    {
        return the_event;
    }
    
   /**
     * To set the name of this icon
     * @param n The name of the event this icon is pointing to
     */
    public void setName(String n)
    {
        name = n;
    }  
    
    /**
     * Returns this event icon's name
     * @return event name
     */
    public String getName()
    {
        return name;
    }    
    
    /**
     * To set the image of this event icon
     */
    public void setImage()
    {
        removeAll();
        setLayout(new java.awt.GridBagLayout());                
        java.awt.GridBagConstraints gridBagConstraints;
        
        // creating the top panel
        javax.swing.ImageIcon nic;
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/events/start/start_top.gif"));
        PicButton pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(pic, gridBagConstraints);        
        
        javax.swing.JLabel nameLabel = new javax.swing.JLabel(name);
        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        nameLabel.setPreferredSize(new java.awt.Dimension(40, 16));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(nameLabel, gridBagConstraints);      
        
        setPreferredSize(new java.awt.Dimension(40, 40));
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public startEvent Clone()
    {
        return new startEvent("/icons/events/start/start.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        startEvent newBTI = Clone();
        newBTI.setNumId(num_id);
        newBTI.setEventMonitor(the_event);
        newBTI.setName(name);
        newBTI.setImage();
        return newBTI;
    } 
    
    /**
     * Get the help title of this start event icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Start Event Monitor Icon";
        return s;
    }
    
    /**
     * Get help message of this start event icon
     * @return Help message
     */     
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nThe event started: ";        
        java.util.Vector<auxiliary> al = aux_list.Instance().getAuxiliaries();        
        s+=al.get(num_id).getName();
        
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */     
    public String getHelpDesc()
    {
        String s = "This icon calls and starts\nto monitor a specified event.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */    
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/startEvent.gif"));
        return ii;
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
        algo += "name"+att_value_connector;
        algo += name;
        algo += att_boundary_end;
        return algo;
    }
    
    /**
     * Returns a string format of the NQCCode representation of this object
     * @return The NQC Code
     * @param num_indent The number of indentation
     * @param indentation Indentation
     */
    public String getNQCCode(String indentation, int num_indent)
    {
        String NQCCode = new String();     
        NQCCode += the_event.getNQCEventHeader(indentation);
        NQCCode += Translator.getBodyNQCCode(the_event.getBeginIcon(), num_indent+1);
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
        String[] sp = att.split(att_separator);
        String[] sp2 = new String[2];
        
        // do the first one --> event name
        sp2 = sp[0].split(att_value_connector);
        name = sp2[1];
    }
    
    /**
     * Programming window where this function icon is on
     * @return Programming window
     */
    public ProgWindow getIndividualProgWindow()
    {
        Icon parent = new Icon();
        ProgWindow ret_val = new ProgWindow();
        while(true)
        {
            if(parent instanceof startIcon)
            {
                if(parent instanceof startFunctionIcon)
                {
                    ret_val = ((startFunctionIcon)parent).getProgWindow();
                }
                else if(parent instanceof startTaskIcon)
                {
                    ret_val = ((startTaskIcon)parent).getProgWindow();
                }       
                else if(parent instanceof startEventIcon)
                {
                    ret_val = ((startEventIcon)parent).getProgWindow();
                }                
                break;
            }
            else
            {
                parent = this.getParentIcon();
            }            
        }        
        return ret_val;
    }
    
    /**
     * Returns scroll pane of the individual programming window
     * @return Scroll Pane
     */
    public javax.swing.JScrollPane getIndividualScroller()
    {
        Icon parent = new Icon();
        javax.swing.JScrollPane ret_val = new javax.swing.JScrollPane();
        while(true)
        {
            if(parent instanceof startIcon)
            {
                ret_val = ((startIcon)parent).getScroller();
                break;
            }
            else
            {
                parent = this.getParentIcon();
            }            
        }
        
        return ret_val;
    }
}
