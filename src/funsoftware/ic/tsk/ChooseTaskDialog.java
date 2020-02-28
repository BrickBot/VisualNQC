/*
 * ChooseTaskDialog.java
 *
 * Created on 8 January 2006, 21:16
 *
 */

package funsoftware.ic.tsk;

import funsoftware.consts.*;
import funsoftware.struct.*;
import funsoftware.tasks.*;

/**
 * This class is a dialog window prompting user for a task to be selected as the task started or stopped
 * An instance of this class will be created as soon as users insert a start or stop task icon onto the programming window
 * @author Thomas Legowo
 */
public class ChooseTaskDialog extends javax.swing.JDialog{
    
    // private variables
    private javax.swing.JComboBox tasks;
        
    java.awt.GridBagConstraints gridBagConstraints;

    private javax.swing.JPanel jp_top;
    private javax.swing.JPanel jp;
    private javax.swing.JLabel jl;    
    private javax.swing.JButton button;
    
    private String msg_top;
    private String guide_top;    
    
    // return value
    private int ret_val;    
    
    /**
     * Creates a new instance of ChooseTaskDialog
     * @param frame The frame
     */
    public ChooseTaskDialog(java.awt.Frame frame) {
        super(frame, true);
        setTitle("Set Task");
        
        ret_val = 1;  // default num_id of the selected task
        
        tasks = new javax.swing.JComboBox();      
        
        // input the tasks
        java.util.Vector<auxiliary> al = aux_list.Instance().getAuxiliaries();
        for(int i=1; i<al.size(); i++)
        {
            auxiliary a = al.get(i);
            if(a != null && a instanceof task)
            {
                task t = (task)a;
                if(t.getName().compareTo("main") != 0)
                {
                    tasks.addItem(t.getName());
                }                
            }
        }
        
        msg_top = "Task";
        guide_top = "Choose a task";     
        
        jp_top = new javax.swing.JPanel();
        jp_top.setLayout(new java.awt.GridBagLayout());
        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        
        jl = new javax.swing.JLabel(msg_top);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(jl, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
        jp.add(tasks, gridBagConstraints);
        
        jl = new javax.swing.JLabel(guide_top);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(jl, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jp, gridBagConstraints);
        
        
        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        button = new javax.swing.JButton("Select");
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
                tasks.requestFocusInWindow();
            }
        });     
    }
 
    // to handle for "cancel" button clicked
    private void cancelClicked()
    {
        ret_val = -1;
        clearAndHide();
    }    
    
    // to handle for "select" button clicked
    private void buttonMouseClicked()
    {
        ret_val = aux_list.Instance().getAuxiliary((String)tasks.getSelectedItem()).getNumId();
        clearAndHide();
    }
    
    /**
     * Return the user input of task name
     * @return User input
     */
    public int getUserInput() {
        return ret_val;
    }

    
    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        tasks.removeAllItems();
        setVisible(false);
    }    
}
