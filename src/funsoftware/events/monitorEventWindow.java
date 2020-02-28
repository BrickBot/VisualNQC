/*
 * monitorEventWindow.java
 *
 * Created on 22 February 2006, 16:15
 *
 */

package funsoftware.events;

import funsoftware.ic.*;
import funsoftware.ic.ev.*;
import funsoftware.inter.*;
import funsoftware.consts.*;
import funsoftware.pallette.*;
import funsoftware.struct.*;
import funsoftware.st.*;
import funsoftware.var.*;
import funsoftware.functs.*;
import funsoftware.tasks.*;

/**
 * Just like functionwindow and taskwindow, this class displays a set of radio buttons. 
 * The radio buttons are the names of the event monitor.
 * Once one is selected, the corresponding ProgWindow is displayed.
 * @author Thomas Legowo
 */
public class monitorEventWindow extends javax.swing.JPanel implements java.awt.event.ActionListener{
    
    // the private variables    
    private java.util.Vector<monitorEvent> GlobalEvents;  // the members, each being an event 
    private java.util.Vector<monitorEvent> LocalEvents;   // the members, each being an event 
    
    private java.awt.Dimension area;   // the area of the panel
    
    // the four temporary variables needed for the construction of a ProgWindow
    private TitlePanelLabel jDirectionsLabel;
    
    private javax.swing.JFrame jFrame;
    private javax.swing.JPanel GlobalPane;
    private javax.swing.JPanel LocalPane;
    private javax.swing.JSeparator line;
    
    // the tabbed window manager
    private TabbedWindowManager twm;   
    
    private auxiliary current_aux;
    
    /**
     * Creates a new instance of eventWindow
     * @param jf The frame
     * @param jPanel13 Panel on top of programming window
     * @param njDirectionsLabel Real time direction palette
     * @param varw The variable window
     */
    public monitorEventWindow(javax.swing.JFrame jf, javax.swing.JPanel jPanel13,
                              TitlePanelLabel njDirectionsLabel, VarWindow varw) 
    {
        super();
        super.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
        jFrame = jf; 
        GlobalEvents = new java.util.Vector<monitorEvent>();
        LocalEvents = new java.util.Vector<monitorEvent>();
        twm = TabbedWindowManager.Instance(jPanel13, varw, this);
        jDirectionsLabel = njDirectionsLabel;
        
        GlobalPane = new javax.swing.JPanel();
        GlobalPane.setLayout(new javax.swing.BoxLayout(GlobalPane, javax.swing.BoxLayout.PAGE_AXIS));
        LocalPane = new javax.swing.JPanel();
        LocalPane.setLayout(new javax.swing.BoxLayout(LocalPane, javax.swing.BoxLayout.PAGE_AXIS));

        GlobalPane.setOpaque(false);
        LocalPane.setOpaque(false);
        
        line = new javax.swing.JSeparator();
        line.setForeground(new java.awt.Color(0, 0, 0));
        line.setAlignmentX(0.5f);
        line.setAlignmentY(0.5f);
        line.setPreferredSize(new java.awt.Dimension(0, 1));
        line.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 1));
        
        add(GlobalPane);
  
        add(javax.swing.Box.createVerticalStrut(2));
        add(line);      
        add(javax.swing.Box.createVerticalStrut(2));
        
        add(LocalPane); 
        
        setBackground(new java.awt.Color(247, 247, 247));
    }
    
    /**
     * To paint this event monitor window
     * @param g Graphics
     */
    protected void paintComponent(java.awt.Graphics g) 
    {
        // update the radio buttons texts
        int lim = GlobalPane.getComponentCount(); 
        for(int i=0; i<lim; i++)
        {
            javax.swing.JRadioButton jrb = (javax.swing.JRadioButton)GlobalPane.getComponent(i);
            jrb.setText(GlobalEvents.get(i).getName()); 
        }                
        lim = LocalPane.getComponentCount(); 
        for(int i=0; i<lim; i++)
        {
            javax.swing.JRadioButton jrb = (javax.swing.JRadioButton)LocalPane.getComponent(i);
            jrb.setText(LocalEvents.get(i).getName());                     
        }      
//        update_panel();
        super.paintComponent(g); 
    }
    
    /**
     * Reset this window, occurs if user wanna start fresh, e.g. from the menu bar, File --> New
     */
    public void reset()
    {
        GlobalPane.removeAll();
        LocalPane.removeAll();
        GlobalEvents = new java.util.Vector<monitorEvent>();
        LocalEvents = new java.util.Vector<monitorEvent>();

        refreshContainer();
    }
    
    /**
     * Insert a new event monitor radio button onto this event monitor window
     * @param evt The event monitor radio button to be inserted
     */
    public void insertEvent(monitorEvent evt)
    {
        javax.swing.JRadioButton tskrb = new javax.swing.JRadioButton(evt.getName()); 
        tskrb.setBackground(new java.awt.Color(247, 247, 247));
        tskrb.setActionCommand(Integer.toString(evt.getNumId()));  
        tskrb.addActionListener(this);
        radiobuttons.Instance().getButtonGroup().add(tskrb);
        
        String type = evt.getType();
        if(type.compareTo("Local") == 0)
        {
            LocalEvents.addElement(evt);  
            LocalPane.add(tskrb);   
            if(evt.getAuxPoint().getNumId() != current_aux.getNumId())
            {
                tskrb.setVisible(false);
            }
        }
        else if(type.compareTo("Global") == 0)
        {
            GlobalEvents.addElement(evt);    
            GlobalPane.add(tskrb);
        }
        
        // to set up the component pop up menu
        javax.swing.JPopupMenu jpm = new javax.swing.JPopupMenu();
        javax.swing.JMenuItem jm = new javax.swing.JMenuItem();
        jm = new javax.swing.JMenuItem("Delete Event");
        final int num_id = evt.getNumId();
        final monitorEvent evt_tmp = evt;

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

        tskrb.setComponentPopupMenu(jpm); 

        javax.swing.JPanel jp = evt.setUpAllIcons(jDirectionsLabel, this);
        twm.addPanel(jp, evt.getNumId());
        
        refreshContainer();
    }

    // to edit some attributes of this event monitor
    private void renameEventPerformed(int num_id)
    {
        monitorEventDialog me_dialog = new monitorEventDialog(jFrame, (monitorEvent)aux_list.Instance().getAuxiliaries().get(num_id), 1);
        me_dialog.pack();
        me_dialog.setLocationRelativeTo(jFrame);
        me_dialog.setVisible(true);    
        
        String tmpName = aux_list.Instance().getAuxiliaries().get(num_id).getName();
        String eventName = me_dialog.getEventMonitorName();
                    
        monitorEvent tmp = (monitorEvent)aux_list.Instance().getAuxiliaries().get(num_id);
        if(eventName.compareTo("") != 0 && eventName.compareTo(tmpName) != 0)  // not the current value and not an empty string
        {
            // change the names for this event monitor and all of its associated event begin and stop icons   

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
            tmp.setMonitoredEvents(me_dialog.getEventsMonitored());    
            UndoRedo.update_state();
        }
                
        refreshContainer();
    }    
     
    
    // the action handler for the radio button's pop up menu
    private void deleteEventPerformed(monitorEvent to_be_deleted, int num_id)
    {
        int to_delete = 0;  // all is good for delete
        // also delete all related icons
        icons_list icon_list = icons_list.Instance();
        java.util.Vector<Icon> ics = icon_list.getIcons();
        for(int i=1; i<ics.size(); i++)
        {
            Icon tmp_icon = ics.get(i);
            
            if(tmp_icon != null && tmp_icon instanceof startEvent)
            {
                startEvent func = (startEvent)tmp_icon;
                if(func.getNumId() == num_id)
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                                    "One or more event calls to this event exist.\nPlease delete them first.",
                                    "Delete Forbidden",
                                    javax.swing.JOptionPane.WARNING_MESSAGE);                
                    to_delete = 1;
                    break;
                }                
            } 
        }
                
        if(to_delete == 0)
        {   
            if(isEventSelected(num_id))
            {
                twm.displayPanel("1");
                java.util.Enumeration<javax.swing.AbstractButton> enum_ab = radiobuttons.Instance().getButtonGroup().getElements();
                while(enum_ab.hasMoreElements())
                {
                    javax.swing.JRadioButton jrb = (javax.swing.JRadioButton)enum_ab.nextElement();
                    if(jrb.getActionCommand().compareTo("1") == 0)
                    {
                        jrb.setSelected(true); 
                       // break;
                    }
                }             
            }        
            deleteEvent(to_be_deleted, num_id);               
            UndoRedo.update_state();            
        }
    }
    
    // to determine whether the to-be-deleted event monitor window is currently-selected
    private Boolean isEventSelected(int id_del)
    {
        String rad_butt_command = new String();
        java.util.Enumeration<javax.swing.AbstractButton> enum_ab = radiobuttons.Instance().getButtonGroup().getElements();
        javax.swing.JRadioButton jrb;
        int sel_id = 1;
        while(enum_ab.hasMoreElements())
        {
            jrb = (javax.swing.JRadioButton)enum_ab.nextElement();
            if(jrb.isSelected() == true)
            {
                rad_butt_command = jrb.getActionCommand();
                sel_id = Integer.parseInt(rad_butt_command);
                break;
            }
        }
        
        return (sel_id == id_del);
    }    
    
    /**
     * Delete a selected event monitor radio button
     * @param to_delete The event monitor to be deleted
     * @param num_id Event id
     */
    public void deleteEvent(monitorEvent to_delete, int num_id)
    {
        aux_list func_list = aux_list.Instance();
        monitorEvent tmp = (monitorEvent)aux_list.Instance().getAuxiliaries().get(num_id);
        func_list.delAuxiliary(num_id);
            
        int del_pos = 0;
        String type = to_delete.getType();
        
        if(type.compareTo("Local") == 0)
        {
            for(int i=0; i<LocalEvents.size(); i++)
            {
                tmp = LocalEvents.get(i);
                if(num_id == tmp.getNumId())
                {
                    del_pos = i;
                    break;
                }
            }   
            LocalEvents.removeElementAt(del_pos);
            radiobuttons.Instance().getButtonGroup().remove((javax.swing.JRadioButton)LocalPane.getComponent(del_pos));    
            LocalPane.remove(del_pos);    
        }
        else if(type.compareTo("Global") == 0)
        {
            for(int i=0; i<GlobalEvents.size(); i++)
            {
                tmp = GlobalEvents.get(i);
                if(num_id == tmp.getNumId())
                {
                    del_pos = i;
                    break;
                }
            }   
            GlobalEvents.removeElementAt(del_pos);
            radiobuttons.Instance().getButtonGroup().remove((javax.swing.JRadioButton)GlobalPane.getComponent(del_pos));    
            GlobalPane.remove(del_pos);            
        }  
        
        twm.delPanel(num_id);
        
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
    
    /**
     * Return the window manager that manages the card laid out panels as controlled by the radio button group
     * @return Window manager
     */
    public TabbedWindowManager getTabbedWinMan()
    {
        return twm;
    }
    
    /**
     * Return the radio button group
     * @return Radio button group
     */
    public javax.swing.ButtonGroup getButtonGroup()
    {
        return radiobuttons.Instance().getButtonGroup();
    }
   
    
    // ---------------------------------
    
    /**
     * Listens to the radio buttons.
     * @param e ActionEvent
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
        twm.displayPanel(e.getActionCommand());
    }
    
    /**
     * To switch the display, more efficient to repaint the panel rather than have multiple panels
     * @param a The auxiliary the programming window is currently displaying
     */
    public void switchDisplay(auxiliary a)
    {
        current_aux = a;
        if(a instanceof function || a instanceof task)  // display the relevant event monitors
        {
            int lim = LocalPane.getComponentCount();
            for(int i=0; i<lim; i++)
            {
                javax.swing.JRadioButton tskrb = (javax.swing.JRadioButton)LocalPane.getComponent(i);
                monitorEvent evt = (monitorEvent)aux_list.Instance().getAuxiliary(tskrb.getText());
                if(evt.getType().compareTo("Local") == 0 && evt.getAuxPoint().getNumId() == a.getNumId())
                {
                    tskrb.setVisible(true);
                }
                else
                {
                    tskrb.setVisible(false);
                }
            }
        }
        else   // must be of a monitorEvent instance, so display all event monitors
        {
            int lim = LocalPane.getComponentCount();
            for(int i=0; i<lim; i++)
            {
                javax.swing.JRadioButton tskrb = (javax.swing.JRadioButton)LocalPane.getComponent(i);
                tskrb.setVisible(true);
            }
        }
//        update_panel();
        refreshContainer();
    }    
    
    // to update the width of the panels
//    private void update_panel() {}
//    {
//        GlobalPane.revalidate();
//        LocalPane.revalidate();
//        int widest = 0;
//        int height_global = 22*GlobalPane.getComponentCount();
//        int height_local = 22*LocalPane.getComponentCount();
//
//        int lim = GlobalPane.getComponentCount(); 
//        for(int i=0; i<lim; i++)
//        {
//            javax.swing.JRadioButton jrb = (javax.swing.JRadioButton)GlobalPane.getComponent(i);
//            if(jrb.getPreferredSize().getWidth() > widest)
//            {
//                widest = (int)jrb.getPreferredSize().getWidth();
//            }
//        }  
//        
//        lim = LocalPane.getComponentCount(); 
//        for(int i=0; i<lim; i++)
//        {
//            javax.swing.JRadioButton jrb = (javax.swing.JRadioButton)LocalPane.getComponent(i);  
//            if(jrb.getPreferredSize().getWidth() > widest)
//            {
//                widest = (int)jrb.getPreferredSize().getWidth();
//            }            
//        }        
//        
//        if(widest < 120)
//        {
//            widest = 120;
//        }
//        
//        height_local += 5;
//        height_global += 5;
//        
//        LocalPane.setPreferredSize(new java.awt.Dimension(widest, height_local));
//        LocalPane.setMinimumSize(new java.awt.Dimension(widest, height_local));
//        GlobalPane.setPreferredSize(new java.awt.Dimension(widest, height_global));
//        GlobalPane.setMinimumSize(new java.awt.Dimension(widest, height_global));
//        line.setPreferredSize(new java.awt.Dimension(widest, 1));        
//    }            
}
