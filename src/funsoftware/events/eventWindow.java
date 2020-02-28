/*
 * eventWindow.java
 *
 * Created on 1 February 2006, 13:08
 *
 */

package funsoftware.events;

import funsoftware.ic.*;
import funsoftware.ic.ev.*;
import funsoftware.inter.*;
import funsoftware.consts.*;
import funsoftware.struct.*;
import funsoftware.st.*;
import funsoftware.var.*;
import funsoftware.functs.*;
import funsoftware.tasks.*;

/**
 * Just like VarWindow, this class displays labels of defined events. Events are useless if there are no
 * monitorEvent defined to watch them.
 * @author Thomas Legowo
 */
public class eventWindow extends javax.swing.JPanel{
    
    // the private variables    
    private java.util.Vector<Event> Events;  // the members, each being an event 
    
    private java.awt.Dimension area;   // the area of the panel
        
    private javax.swing.JFrame jFrame;
    java.awt.GridBagConstraints gridBagConstraints;
    
    private auxiliary current_aux;
    
    /**
     * Creates a new instance of eventWindow
     * @param frame The frame that this panel points to
     */
    public eventWindow(javax.swing.JFrame frame) 
    {
        super();
        super.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
        jFrame = frame; 
        Events = new java.util.Vector<Event>();
        setBackground(new java.awt.Color(247, 247, 247));
    }
    
    /**
     * To paint this event window
     * @param g Graphics
     */
    protected void paintComponent(java.awt.Graphics g) 
    {
        // update the radio buttons texts
        int lim = getComponentCount(); 
        for(int i=0; i<lim; i++)
        {
            javax.swing.JLabel jrb = (javax.swing.JLabel)getComponent(i);
            jrb.setText(Events.get(i).getName()); 
        }          
        super.paintComponent(g); 
    }
    
    /**
     * Reset this window, occurs if user wanna start fresh, e.g. from the menu bar, File --> New
     */
    public void reset()
    {
        removeAll();
        Events = new java.util.Vector<Event>();
        
        refreshContainer();
    }
    
    /**
     * Insert a new event label onto this event window
     * @param evt The event to be inserted
     */
    public void insertEvent(Event evt)
    {
        javax.swing.JLabel elb = new javax.swing.JLabel(evt.getName()); 
        elb.setBackground(new java.awt.Color(247, 247, 247));
            
        Events.addElement(evt);  
        this.add(elb);        
        
        // to set up the component pop up menu
        javax.swing.JPopupMenu jpm = new javax.swing.JPopupMenu();
        javax.swing.JMenuItem jm = new javax.swing.JMenuItem();
        jm = new javax.swing.JMenuItem("Delete Event");
        final int num_id = evt.getEventId();
        final Event evt_tmp = evt;

        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEventPerformed(evt_tmp, num_id);
            }
        });
        jpm.add(jm);
        jm = new javax.swing.JMenuItem("Edit Event");
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameEventPerformed(num_id);
            }
        });
        jpm.add(jm);
        jm = new javax.swing.JMenuItem("Event Properties");
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                javax.swing.JOptionPane.showMessageDialog(null,
                        evt_tmp.getHelpMsg(),
                        evt_tmp.getHelpTitle(),
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        });
        jpm.add(jm);

        elb.setComponentPopupMenu(jpm);
        
        refreshContainer();
    }

    // to edit the event
    private void renameEventPerformed(int num_id)
    {
        EventNameDialog evt_dialog = new EventNameDialog(jFrame, (Event)event_number_tracker.Instance().getEvents().get(num_id), 1);
        evt_dialog.pack();
        evt_dialog.setLocationRelativeTo(jFrame);
        evt_dialog.setVisible(true);    
        
        String tmpName = event_number_tracker.Instance().getEvents().get(num_id).getName();
        String eventName = evt_dialog.getEventName();
                    
        Event tmp = (Event)event_number_tracker.Instance().getEvents().get(num_id);
        if(eventName.compareTo("") != 0 && eventName.compareTo(tmpName) != 0)  // not the current value and not an empty string
        {
            // change the names for this event and all of its associated event begin and stop icons   

            tmp.setName(eventName);
            
            java.util.Vector<Icon> ics = (icons_list.Instance()).getIcons();
            for(int i=1; i<ics.size(); i++)   // check for icons that has the same numid with this one,
                                              // if so, update its name
            {
                Icon tmp_icon = ics.get(i);
                if(tmp_icon != null)
                {
                    if(tmp_icon instanceof startEvent)
                    {
                        startEvent tmp_sub = (startEvent)tmp_icon;
                        if(tmp_sub.getNumId() == num_id)
                        {
                            tmp_sub.setName(eventName);
                            tmp_sub.setImage();
                            tmp_sub.repaint();tmp_sub.validate();
                        }
                    }
                }
            }
        }
        
        // user didn't press cancel
        if(eventName.compareTo("") != 0)
        {
            // after the names, edit the rest of the event's attributes                        
            Operand threshold_to_pass = evt_dialog.getThreshold();

            // everything but the localglobal type and auxiliary pointers, are allowed to be changed.
            tmp.setEventElements(evt_dialog.getSource(), evt_dialog.getEventSource(), 
                                 evt_dialog.getEventType(), threshold_to_pass);

            UndoRedo.update_state();    
        }
        
        refreshContainer();
    }    
     
    
    // the action handler for the radio button's pop up menu
    private void deleteEventPerformed(Event to_be_deleted, int num_id)
    {
        int to_delete = 0;  // all is good for delete
        // also delete all related icons
        java.util.Vector<auxiliary> ics = aux_list.Instance().getAuxiliaries();
        for(int i=1; i<ics.size(); i++)
        {
            auxiliary tmp_aux = ics.get(i);
            
            if(tmp_aux != null && tmp_aux instanceof monitorEvent)
            {
                monitorEvent ev = (monitorEvent)tmp_aux;
                if(ev.checkEventUsage(num_id) == true)  // an event monitor uses this event
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                                    "One or more event monitors to this event exist.\nPlease delete them first.",
                                    "Delete Forbidden",
                                    javax.swing.JOptionPane.WARNING_MESSAGE);                
                    to_delete = 1;
                    break;
                }                
            } 
        }
        if(to_delete == 0)
        {
            deleteEvent(to_be_deleted);               
            UndoRedo.update_state();                  
        }   
    }
    
    /**
     * Delete a selected event radio button 
     * @param to_delete The event to be deleted
     */
    public void deleteEvent(Event to_delete)
    {
        int event_id = to_delete.getEventId();
        event_number_tracker.Instance().delEvent(event_id);
            
        int del_pos = 0;

        Event tmp;
        for(int i=0; i<Events.size(); i++)
        {
            tmp = Events.get(i);
            if(event_id == tmp.getEventId())
            {
                del_pos = i;
                break;
            }
        }   
        Events.removeElementAt(del_pos);   
        remove(del_pos);    
        
        refreshContainer();
    }
    
    /**
     * Refresh the UI for this component and its parent
     */
    public void refreshContainer()
    {
        this.repaint();
        this.revalidate();
        
        if (this.getParent() != null)
        {
            this.getParent().revalidate();
        }
    }
}
