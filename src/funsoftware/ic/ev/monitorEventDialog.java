/*
 * monitorEventDialog.java
 *
 * Created on 22 February 2006, 16:01
 *
 */

package funsoftware.ic.ev;

import funsoftware.consts.*;
import funsoftware.struct.*;
import funsoftware.functs.*;
import funsoftware.tasks.*;
import funsoftware.events.*;
import funsoftware.var.*;

/**
 * This dialog asks for a monitor's name, the events to be monitored and its type whether a local or a global
 * @author Thomas Legowo
 */
public class monitorEventDialog extends javax.swing.JDialog{
    
    // private variables
    private javax.swing.JTextField eventName;    
    private javax.swing.JComboBox type;
    private javax.swing.JComboBox auxiliary_combo;
    private javax.swing.JTextField evMonitorLevel;
    
    java.awt.GridBagConstraints gridBagConstraints;

    private javax.swing.JPanel jp;
    private javax.swing.JPanel events;
    private javax.swing.JPanel jp_top;
    private javax.swing.JPanel jp_bottom;
    private javax.swing.JLabel jl;    
    private javax.swing.JButton button;
    
    private String msg1;
    private String msg2;
    private javax.swing.JLabel msg6;
    private String guide1; 
    private String guide2;
    private javax.swing.JLabel guide6;
    
    private String msg_level;
    private String guide_level;
    
    private java.awt.Frame frame;
    
    // return values
    private String event_name; 
    private int ret_level;
    
    private monitorEvent input1;
    private java.util.Vector<Event> ret;
    
    /**
     * Creates a new instance of monitorEventDialog
     * @param frame The frame
     * @param input1 The event
     * @param edit_or_set To determine whether this is to initialize an event or to edit it
     */
    public monitorEventDialog(java.awt.Frame frame, monitorEvent input1, int edit_or_set) {
        super(frame, true);
        this.frame = frame;
        this.input1 = input1;
        
        ret = new java.util.Vector<Event>();
        
        if(edit_or_set == 0)
        {
            setTitle("Set Event Monitor Properties");
        }
        else
        {
            setTitle("Edit Event Monitor Properties");
        }        
                
        if(input1 != null)
        {
            event_name = input1.getName();            
            auxiliary aux_point = input1.getAuxPoint();      
        }
        else
        {
            event_name = "";
        }
        eventName = new javax.swing.JTextField(event_name, 12); 
        
        evMonitorLevel = new javax.swing.JTextField("", 10);  
                        
        msg_level = "Level";
        guide_level = "Values (minimum = 1)";
        
        type = new javax.swing.JComboBox();
        type.addItem("Global");
        type.addItem("Local");           
        
        auxiliary_combo = new javax.swing.JComboBox();
        java.util.Vector<auxiliary> auxs = aux_list.Instance().getAuxiliaries();
        for(int i=1; i<auxs.size(); i++)
        {
            auxiliary tmp = auxs.get(i);
            if(tmp != null && (tmp instanceof function || tmp instanceof task))
            {
                auxiliary_combo.addItem(tmp.getName());
            }
        }
        
        msg1 = "Name";
        msg2 = "Reach";
        msg6 = new javax.swing.JLabel("Function-Task");      
        guide1 = "At least 1 character";     
        guide2 = "";        
        guide6 = new javax.swing.JLabel("Function or Task this event is associated to");

        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());          
        
        events = new javax.swing.JPanel();
        events.setLayout(new javax.swing.BoxLayout(events, javax.swing.BoxLayout.Y_AXIS));
        
        jp_top = new javax.swing.JPanel();      
        jp_top.setLayout(new java.awt.GridBagLayout());
        jl = new javax.swing.JLabel(msg1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jl, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
        jp_top.add(eventName, gridBagConstraints);
        
        jl = new javax.swing.JLabel(guide1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jl, gridBagConstraints);
        
        if(edit_or_set == 0)
        {
            jl = new javax.swing.JLabel(msg_level);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp_top.add(jl, gridBagConstraints);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
            jp_top.add(evMonitorLevel, gridBagConstraints);

            jl = new javax.swing.JLabel(guide_level);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp_top.add(jl, gridBagConstraints);
        } 
        else
        {
            // for the sake of it to get rid of event monitor level changes
            evMonitorLevel.setText("1");
        }
        
        jl = new javax.swing.JLabel(msg2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jl, gridBagConstraints);
        if(edit_or_set == 1)
        {
            jl.setVisible(false);
        }
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
        jp_top.add(type, gridBagConstraints);
        if(edit_or_set == 1)
        {
            type.setVisible(false);
        }
        
        jl = new javax.swing.JLabel(guide2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jl, gridBagConstraints);
        if(edit_or_set == 1)
        {
            jl.setVisible(false);
        }        

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(msg6, gridBagConstraints);        
        msg6.setVisible(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
        jp_top.add(auxiliary_combo, gridBagConstraints);
        auxiliary_combo.setVisible(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(guide6, gridBagConstraints);
        guide6.setVisible(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(jp_top, gridBagConstraints);    
    
        javax.swing.JSeparator line = new javax.swing.JSeparator();
        line.setForeground(new java.awt.Color(0, 0, 0));
        line.setPreferredSize(new java.awt.Dimension(250, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(line, gridBagConstraints); 
        
        jl = new javax.swing.JLabel("Events to be monitored");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(jl, gridBagConstraints); 
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(events, gridBagConstraints);            
        
        javax.swing.JPanel middle = new javax.swing.JPanel();
        middle.setLayout(new java.awt.GridBagLayout());    
        button = new javax.swing.JButton("Add Event");
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });       
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        middle.add(button, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(middle, gridBagConstraints);        
        
        
        jp_bottom = new javax.swing.JPanel();
        jp_bottom.setLayout(new java.awt.GridBagLayout());
        button = new javax.swing.JButton("Enter");
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_bottom.add(button, gridBagConstraints);
        
        button = new javax.swing.JButton("Cancel");
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_bottom.add(button, gridBagConstraints);        
        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(jp_bottom, gridBagConstraints);        
        
        setContentPane(jp);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent we) {
                /*
                 * Instead of directly closing the window,
                 * we're going to change the JOptionPane's
                 * value property.
                 */
                    //optionPane.setValue(new Integer(
                                      // javax.swing.JOptionPane.CLOSED_OPTION));
            }
        });
        
         //Ensure the text field always gets the first focus.
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent ce) {
                eventName.requestFocusInWindow();
            }
        });  
        
        type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeType();
            }
        });       
        
        if(edit_or_set == 1 && input1 != null)
        {
            java.util.Vector<Event> input_list = input1.getMonitoredEvents();
            for(int i=0; i<input_list.size(); i++)
            {
                insertEvent(input_list.get(i));
            }
        }
    }
    
    /**
     * To get the combo box of the available events
     */
    private javax.swing.JComboBox getComboBox()
    {
        javax.swing.JComboBox ret_val = new javax.swing.JComboBox();
        
        java.util.Vector<Event> ev_list = event_number_tracker.Instance().getEvents();
        for(int i=1; i<ev_list.size(); i++)
        {
            Event tmp = ev_list.get(i);
            if(tmp != null)
            {
                ret_val.addItem(tmp.getName());
            }
        }
        
        return ret_val;
    }
    
    /**
     * To add more events to be monitored
     */
    private void addMouseClicked(java.awt.event.MouseEvent e)
    {
        insertEvent(null);
    }        
    
    /**
     * Robust method for inserting events either manually or automatically
     */
    private void insertEvent(Event ev)
    {
        javax.swing.JComboBox jcb = getComboBox();
        if(ev != null)
        {
            jcb.setSelectedItem(ev.getName());
        }
        
        javax.swing.JPanel tmp = new javax.swing.JPanel();
        java.awt.GridBagConstraints gridBagConstraints;        
        tmp.setLayout(new java.awt.GridBagLayout());
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        tmp.add(jcb, gridBagConstraints);
        
        javax.swing.JLabel jl = new javax.swing.JLabel("Event Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        tmp.add(jl, gridBagConstraints);     
        
        javax.swing.JButton jb = new javax.swing.JButton("Delete Event");
        final javax.swing.JPanel tmp_jp = tmp; 
        jb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delEvMouseClicked(evt, tmp_jp);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        tmp.add(jb, gridBagConstraints);  
        
        events.add(tmp);
        pack();
        repaint();    
    }
    
    /**
     * To handle for the delete argument mouse button clicked
     */
    private void delEvMouseClicked(java.awt.event.MouseEvent e, javax.swing.JPanel jp)
    {
        events.remove(jp);
        pack();
        repaint();
    }          
    
    /**
     * This method reacts to state changes in the combo box containing the event types(local or global).
     */
    public void changeType() 
    {
        String selected = (String)type.getSelectedItem();
        if(selected.compareTo("Local") == 0)
        {
            auxiliary_combo.setVisible(true);
            auxiliary_combo.setSelectedItem("main");
            msg6.setVisible(true);
            guide6.setVisible(true);
        }
        else
        {
            auxiliary_combo.setVisible(false);
            msg6.setVisible(false);
            guide6.setVisible(false);   
        }
        
        pack();
        setLocationRelativeTo(frame);
    }  
    
    // to handle for mouse click
    private void buttonMouseClicked(java.awt.event.MouseEvent e)
    {
        ret = new java.util.Vector<Event>();
        
        // check the validity for the input name first           
        if(eventName.getText().compareTo(event_name) == 0 && eventName.getText().compareTo("") != 0)  // must be valid already
        {         
            event_name = eventName.getText();
            int check = checkEvMonitorLevel();
            if(check == 0)
            {
                checkEventsMonitored(); 
            }                       
        }
        else
        {
            String tmp = eventName.getText();
            if(tmp.compareTo("") == 0)
            {
                    javax.swing.JOptionPane.showMessageDialog(null,
                        "Please input at least 1 character.",
                        "Input Invalid", javax.swing.JOptionPane.WARNING_MESSAGE);              
            }
            else
            {
                if(aux_list.Instance().checkName(tmp) == 0)
                {
                    event_name = eventName.getText();
                    int check = checkEvMonitorLevel();
                    if(check == 0)
                    {
                        checkEventsMonitored(); 
                    }
                }
                else if(aux_list.Instance().checkName(tmp) == 1)
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                        "Input value is invalid.\nThe name has been used.\nPlease Try Again.",
                        "Input Invalid", javax.swing.JOptionPane.WARNING_MESSAGE);            
                }
                else if(aux_list.Instance().checkName(tmp) == 2)
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                        "Input value is invalid.\nPlease enter string and numbers only.\nFirst character has to be a letter.\nPlease Try Again.",
                        "Input Invalid", javax.swing.JOptionPane.WARNING_MESSAGE);              
                }        
            }
        }
    }   
    
    /**
     * Check whether the value entered by the user is valid (i.e. Integer and greater than 1)
     */
    private int checkEvMonitorLevel()
    {
        try
        {
            int level = Integer.parseInt(evMonitorLevel.getText());

            if(level < 1)
            {
                javax.swing.JOptionPane.showMessageDialog(null,
                    "Input value for event monitor level is invalid.\nPlease enter numbers greater than one only.\nPlease Try Again.",
                    "Input Invalid",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                evMonitorLevel.requestFocusInWindow();   
                return 1;
            }
            else
            {
                ret_level = Integer.parseInt(evMonitorLevel.getText());
                return 0;                   
            }
        }
        catch(RuntimeException re)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Input value for event monitor level is invalid.\nPlease enter numbers greater than one only.\nPlease Try Again.",
                "Input Invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            evMonitorLevel.requestFocusInWindow();
            return 1;
        } 
    }
    
    /**
     * Check and fills in the events to be monitored list
     */
    private void checkEventsMonitored()
    {
        int lim = events.getComponentCount(); 
        int can_close = 0;
        if(lim == 0)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Please enter at least one event to be monitored.",
                "Input Invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            can_close = 1;
        }
        else
        {
            for(int i=0; i<lim; i++)
            {
                javax.swing.JPanel jp = (javax.swing.JPanel)events.getComponent(i);
                javax.swing.JComboBox jcb = (javax.swing.JComboBox)jp.getComponent(0);
                Event ev = event_number_tracker.Instance().getEvent((String)jcb.getSelectedItem());
                if(checkEventsInserted(ev.getEventId()) == 0)
                {
                    ret.addElement(ev);
                    continue;
                }
                else
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                        "You cannot enter the same event more than once.",
                        "Input Invalid",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    can_close = 1;
                    break;
                }
            }            
        }
        if(can_close == 0)
        {
            clearAndHide();
        }
    }
    
    /**
     * Check whether an event of this id has already been inserted into the events monitored list
     */
    private int checkEventsInserted(int num_id)
    {
        int status = 0;  // originally zero -- no error. 1 -- there is an error
        
        for(int i=0; i<ret.size(); i++)
        {
            Event tmp = ret.get(i);
            if(tmp.getEventId() == num_id)
            {
                return 1;
            }
        }        
        return status;
    }
    
    /**
     * To handle for the cancel mouse button clicked
     */
    private void cancelMouseClicked(java.awt.event.MouseEvent e)
    {
        event_name = "";
        eventName.setText(null);
        setVisible(false);
    }
    
    /**
     * Return the user input of event monitor name
     * @return Event monitor name
     */
    public String getEventMonitorName() {
        return event_name;
    }         
    
    /**
     * Return the user input of the type (global or local)
     * @return Type
     */
    public String getEventType() {
        String selected = (String)type.getSelectedItem();
        return selected;
    }   
    
    /**
     * Return the user input of the auxiliary connected to this event
     * @return Auxiliary
     */
    public String getAuxiliary() {
        String selected = (String)auxiliary_combo.getSelectedItem();
        
        if(auxiliary_combo.isVisible() == false)
        {
            return null;
        }
        
        return selected;
    }   
    
    /**
     * Return the user input of this event monitor's level
     * @return Event monitor level
     */
    public int getLevel() {        
        return ret_level;
    }
    
    /**
     * Return the events to be monitored
     * @return Events
     */
    public java.util.Vector<Event> getEventsMonitored() {        
        return ret;
    }      
    
    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        eventName.setText(null);
        setVisible(false);
    }
    
    
}
