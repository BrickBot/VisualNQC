/*
 * ChooseEventDialog.java
 *
 * Created on 3 February 2006, 09:26
 *
 */

package funsoftware.ic.ev;

import funsoftware.events.*;
import funsoftware.functs.*;
import funsoftware.struct.*;
import funsoftware.consts.*;

/**
 * This class is a dialog window prompting user for a event to be selected as the event-monitor-to-start.
 * An instance of this class will be created as soon as users insert a start event icon onto the programming window
 * @author Thomas Legowo
 */
public class ChooseEventDialog extends javax.swing.JDialog{
    
    // private variables
    private javax.swing.JComboBox events;
    private javax.swing.JLabel instead_of_events;
        
    java.awt.GridBagConstraints gridBagConstraints;

    private javax.swing.JPanel jp_top;
    private javax.swing.JPanel jp;
    private javax.swing.JLabel jl;    
    private javax.swing.JButton button;
    
    private String msg_top;
    private String guide_top;    
    
    // return value
    private monitorEvent ret_val;    
    
    /**
     * Creates a new instance of ChooseEventDialog
     * @param frame The frame
     * @param tobe_compared The auxiliary of the event
     */
    public ChooseEventDialog(java.awt.Frame frame, auxiliary tobe_compared) 
    {
        super(frame, true);
        setTitle("Set Event");
        
        ret_val = null;
        
        events = new javax.swing.JComboBox();      
        
        // input the tasks
        java.util.Vector<auxiliary> al = aux_list.Instance().getAuxiliaries();
        
        if(tobe_compared instanceof function)
        {
            function tmp_func = (function)tobe_compared;
            int level = tmp_func.getLevel();
            for(int i=1; i<al.size(); i++)
            {
                auxiliary a = al.get(i);
                if(a != null && a instanceof monitorEvent)
                {
                    monitorEvent t = (monitorEvent)a;
                    if(t.getLevel() < level)
                    {
                        if(t.getType().compareTo("Global") == 0)
                        {
                            events.addItem(t.getName());    
                        }   
                        else
                        {
                            if(t.getAuxPoint().getNumId() == tobe_compared.getNumId())
                            {
                                events.addItem(t.getName()); 
                            }                    
                        }    
                    }                    
                }
            }
        }
        else
        {
            for(int i=1; i<al.size(); i++)
            {
                auxiliary a = al.get(i);
                if(a != null && a instanceof monitorEvent)
                {
                    monitorEvent t = (monitorEvent)a;
                    if(t.getType().compareTo("Global") == 0)
                    {
                        events.addItem(t.getName());    
                    }   
                    else
                    {
                        if(t.getAuxPoint().getNumId() == tobe_compared.getNumId())
                        {
                            events.addItem(t.getName()); 
                        }                    
                    }
                }
            }        
        }
        
        if(events.getItemCount() == 0)
        {
            instead_of_events = new javax.swing.JLabel("No events can be used in this task or function.");
        }
        
        msg_top = "Event Monitor";
        guide_top = "Choose an event monitor";     
        
        jp_top = new javax.swing.JPanel();
        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        jp_top.setLayout(new java.awt.GridBagLayout());
        jl = new javax.swing.JLabel(msg_top);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(jl, gridBagConstraints);
        if(events.getItemCount() == 0)
        {
            jl.setVisible(false);  
        }
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
        jp.add(events, gridBagConstraints);
        if(events.getItemCount() == 0)
        {
            events.setVisible(false);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
            jp.add(instead_of_events, gridBagConstraints);
            instead_of_events.setVisible(true);
        }
        
        jl = new javax.swing.JLabel(guide_top);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(jl, gridBagConstraints);
        if(events.getItemCount() == 0)
        {
            jl.setVisible(false);  
        }
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jp, gridBagConstraints);
        
        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
                
        if(events.getItemCount() == 0)
        {
            button = new javax.swing.JButton("Ok");    
        }
        else
        {
            button = new javax.swing.JButton("Select");    
        }
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonMouseClicked();
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(button, gridBagConstraints);
        
        button = new javax.swing.JButton("Cancel");
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelClicked();
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(button, gridBagConstraints);       
        if(events.getItemCount() == 0)
        {
            button.setVisible(false);  
        }
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jp, gridBagConstraints);
        

        setContentPane(jp_top);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent we) {
                /*
                 * Instead of directly closing the window,
                 * we're going to change the JOptionPane's
                 * value property.
                 */
                   // optionPane.setValue(new Integer(
                                     //   javax.swing.JOptionPane.CLOSED_OPTION));
            }
        });
        
         //Ensure the combo box always gets the first focus.
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent ce) {
                events.requestFocusInWindow();
            }
        });     
    }
    
    // to handle for "cancel" button clicked
    private void cancelClicked()
    {
        ret_val = null;
        clearAndHide();
    }    
    
    // to handle for "select" button clicked    
    private void buttonMouseClicked()
    {
        if(events.isVisible() == false)
        {
            ret_val = null;
        }
        else
        {
            ret_val = (monitorEvent)aux_list.Instance().getAuxiliary((String)events.getSelectedItem());    
        }        
        clearAndHide();
    }
    
    /**
     * Return the user input of event monitor name
     * @return User input
     */
    public monitorEvent getUserInput() {
        return ret_val;
    }

    
    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        events.removeAllItems();
        setVisible(false);
    }
    
}
