/*
 * EventNameDialog.java
 *
 * Created on 2 February 2006, 10:19
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
 * To ask for an event's details (name, port, reach, type, mode)
 * @author Thomas Legowo
 */
public class EventNameDialog extends javax.swing.JDialog{
    
    // private variables
    private javax.swing.JTextField eventName;    
    private javax.swing.JTextField threshold_c;
    private javax.swing.JComboBox event_source;
    private javax.swing.JComboBox event_type1;
    private javax.swing.JComboBox event_type2;
    private javax.swing.JComboBox port_number;
    private javax.swing.JComboBox timer_number;
    
    java.awt.GridBagConstraints gridBagConstraints;

    private javax.swing.JPanel jp;
    private javax.swing.JPanel jp_top;
    private javax.swing.JPanel jp_bottom;
    private javax.swing.JLabel jl;    
    private javax.swing.JButton button;
    
    private String msg1;
    private javax.swing.JLabel msg2;
    private javax.swing.JLabel msg3;
    private String msg4;
    private String msg5;
    private javax.swing.JLabel msg7;
    private String guide1; 
    private javax.swing.JLabel guide2;
    private javax.swing.JLabel guide3;
    private String guide4;
    private String guide5;
    private javax.swing.JLabel guide7;
    
    private java.awt.Frame frame;
    
    // return values
    private String event_name; 
    private Operand threshold_value;
    
    private Event input1;
    
    /**
     * Creates a new instance of EventNameDialog
     * @param frame The frame
     * @param input1 The event
     * @param edit_or_set To determine whether this is to initialise an event or to edit it
     */
    public EventNameDialog(java.awt.Frame frame, Event input1, int edit_or_set) {
        super(frame, true);
        this.frame = frame;
        this.input1 = input1;
        
        if(edit_or_set == 0)
        {
            setTitle("Set Event Properties");
        }
        else
        {
            setTitle("Edit Event Properties");
        }
        
        threshold_c = new javax.swing.JTextField("", 12);     
        threshold_c.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        if(input1 != null)
        {
            event_name = input1.getName();
            threshold_value = input1.getThreshold();
        }
        else
        {
            event_name = "";
            threshold_value = null;
        }
        eventName = new javax.swing.JTextField(event_name, 12);  
        port_number = new javax.swing.JComboBox();
        port_number.addItem("1");
        port_number.addItem("2");
        port_number.addItem("3");
        
        timer_number = new javax.swing.JComboBox();
        timer_number.addItem("0");
        timer_number.addItem("1");
        timer_number.addItem("2");
        timer_number.addItem("3");
        
        event_source = new javax.swing.JComboBox();
        int size = typeEventsCoordinator.event_source.length;
        for(int i=0; i<size; i++)
        {
            event_source.addItem(typeEventsCoordinator.event_source[i]);
        }
        
        event_type1 = new javax.swing.JComboBox();
        size = typeEventsCoordinator.event_type1.length;
        for(int i=0; i<size; i++)
        {
            event_type1.addItem(typeEventsCoordinator.event_type1[i]);
        }
        
        event_type2 = new javax.swing.JComboBox();
        size = typeEventsCoordinator.event_type2.length;
        for(int i=0; i<size; i++)
        {
            event_type2.addItem(typeEventsCoordinator.event_type2[i]);
        }        
        
        msg1 = "Name";
        msg2 = new javax.swing.JLabel("Timer");
        msg3 = new javax.swing.JLabel("Port");
        msg4 = "Event-Source";
        msg5 = "Event-Type";
        msg7 = new javax.swing.JLabel("Threshold");    
        guide1 = "At least 1 character";    
        guide2 = new javax.swing.JLabel("");
        guide3 = new javax.swing.JLabel("");
        guide4 = "Type of Sensor";
        guide5 = "Triggering Event";
        guide7 = new javax.swing.JLabel("Integer only");

        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());          
        
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
        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(msg3, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
        jp_top.add(port_number, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(guide3, gridBagConstraints);
        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(msg2, gridBagConstraints);        
        msg2.setVisible(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
        jp_top.add(timer_number, gridBagConstraints);
        timer_number.setVisible(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(guide2, gridBagConstraints);
        guide2.setVisible(false);
        
        
        jl = new javax.swing.JLabel(msg4);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jl, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
        jp_top.add(event_source, gridBagConstraints);
        
        jl = new javax.swing.JLabel(guide4);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jl, gridBagConstraints);   
        
        
        jl = new javax.swing.JLabel(msg5);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jl, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
        jp_top.add(event_type1, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
        jp_top.add(event_type2, gridBagConstraints);      
        event_type2.setVisible(false);
        
        jl = new javax.swing.JLabel(guide5);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jl, gridBagConstraints);         
        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
        jp_top.add(msg7, gridBagConstraints);
        msg7.setVisible(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
        jp_top.add(threshold_c, gridBagConstraints);   
        threshold_c.setVisible(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(guide7, gridBagConstraints);   
        guide7.setVisible(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(jp_top, gridBagConstraints);    
        
        
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
        gridBagConstraints.gridy = 1;
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
                
        event_source.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSourceAction();
            }
        }); 
        
        // fill in the rest of the fields if this is in an edit mode
        if(edit_or_set == 1)
        {
            String source = input1.getSource();
            if(source.substring(0, 1).compareTo("S") == 0)  // sensors not timers
            {                
                port_number.setSelectedItem(source.substring(source.length()-1));
            }
            else
            {
                timer_number.setSelectedItem(source.substring(source.length()-2, source.length()-1)); 
            }
            
            event_source.setSelectedItem(input1.getEventSource());
            if(input1.getEventSource().compareTo("SENSOR_TOUCH") == 0)
            {
                event_type1.setSelectedItem(input1.getEventType());
            }
            else
            {
                event_type2.setSelectedItem(input1.getEventType());
            }
            
            Operand op_thres = input1.getThreshold();
            if(op_thres instanceof constant)
            {
                constant c = (constant) op_thres;
                threshold_c.setText(Integer.toString(c.getValue()));
            }            
        }         
    }
    
    
    /**
     * This method reacts to state changes in the combo box containing the event sources.
     */
    public void changeSourceAction() 
    {
        String selected = (String)event_source.getSelectedItem();
        if(selected.compareTo("SENSOR_TOUCH") == 0)
        {
            event_type1.setVisible(true);
            event_type2.setVisible(false);
            threshold_c.setVisible(false);            
            msg7.setVisible(false);
            guide7.setVisible(false);            
        }
        else
        {
            event_type2.setVisible(true);
            msg7.setVisible(true);
            guide7.setVisible(true);
            threshold_c.setVisible(true);
            event_type1.setVisible(false);  
        }
        
        if(selected.compareTo("TIMER") == 0)
        {
            msg2.setVisible(true);
            timer_number.setVisible(true);
            guide2.setVisible(true);
            msg3.setVisible(false);
            port_number.setVisible(false);
            guide3.setVisible(false);
        }
        else
        {
            msg3.setVisible(true);
            port_number.setVisible(true);
            guide3.setVisible(true);
            msg2.setVisible(false);
            timer_number.setVisible(false);
            guide2.setVisible(false);
        }
        pack();
        setLocationRelativeTo(frame);
    }    
        
    // to handle for mouse click
    private void buttonMouseClicked(java.awt.event.MouseEvent e)
    {
        // check the validity for the input name first           
        if(eventName.getText().compareTo(event_name) == 0 && eventName.getText().compareTo("") != 0)  // must be valid already
        {
            if(checkThreshold() == 1)
            {
                event_name = eventName.getText();
                clearAndHide();                
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
                    if(checkThreshold() == 1)
                    {
                        event_name = eventName.getText();
                        clearAndHide();                
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
     * To check whether a the threshold input is valid
     */
    private int checkThreshold()
    {
        int valid = 1;  // valid, not valid = 0;
        if(threshold_c.isVisible() == true)
        {
            try
            {
                int tmp = Integer.parseInt(threshold_c.getText());
            }
            catch(RuntimeException re)
            {
                javax.swing.JOptionPane.showMessageDialog(null,
                    "Input value for the constant in threshold is invalid.\nPlease enter numbers only.\nPlease Try Again.",
                    "Input Invalid",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                threshold_c.requestFocusInWindow();
                return 0;
            }
        }
        return valid;
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
     * Return the user input of event name
     * @return User input
     */
    public String getEventName() {
        return event_name;
    }
    
    /**
     * Return the user input of the port number
     * @return User input
     */
    public String getSource() {
        String selected;
        if(port_number.isVisible() == true)
        {
            selected = "SENSOR_"+(String)port_number.getSelectedItem();
        }
        else
        {
            selected = "Timer("+(String)timer_number.getSelectedItem()+")";
        }
        return selected;
    }    
    
    /**
     * Return the user input of the event source
     * @return User input
     */
    public String getEventSource() {
        String selected = (String)event_source.getSelectedItem();
        return selected;
    }     
    
    /**
     * Return the user input of the event type
     * @return User input
     */
    public String getEventType() {
        String selected;
        
        if(event_type1.isVisible() == true)
        {
            selected = (String)event_type1.getSelectedItem();
        }
        else
        {
            selected = (String)event_type2.getSelectedItem();
        }
        
        return selected;
    }     
    
    
    /**
     * Return the user input of the threshold value, can be a variable or a constant
     * @return User input
     */
    public Operand getThreshold() {
        Operand tt = null;
        
        if(threshold_c.isVisible() == true)
        {
            constant cc = new constant();
            cc.setValue(Integer.parseInt(threshold_c.getText())); 
            tt = cc;
        }        
        return tt;
    }     

    
    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        eventName.setText(null);
        setVisible(false);
    }
    
}
