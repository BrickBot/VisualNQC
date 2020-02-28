/*
 * TaskNameDialog.java
 *
 * Created on 30 December 2005, 16:52
 *
 */

package funsoftware.ic.tsk;

import funsoftware.consts.*;

/**
 * This class acts as a pop up window prompting the user for a name for the newly-to-be introduced task.
 * This class also ensures that the inserted name is unique and the variables indicated as arguments exist and are valid.
 * @author Thomas Legowo
 */
public class TaskNameDialog extends javax.swing.JDialog{
    
    // private variables
    private javax.swing.JTextField taskName;    
    
    java.awt.GridBagConstraints gridBagConstraints;

    private javax.swing.JPanel jp;
    private javax.swing.JPanel jp_top;
    private javax.swing.JPanel jp_bottom;
    private javax.swing.JLabel jl;    
    private javax.swing.JButton button;
    
    private String msg_top;
    private String guide_top;    
    private String input;
    
    // return values
    private String ret_val;    
    
    /**
     * Creates a new instance of TaskNameDialog
     * @param frame The frame
     * @param input1 The task name
     * @param type Whether it is to set or edit the task's name
     */
    public TaskNameDialog(java.awt.Frame frame, String input1, int type) {
        super(frame, true);
        if(type == 0)
        {
            setTitle("Set Task Name");
        }
        else
        {
            setTitle("Edit Task Name");
        }
        
        ret_val = input1;
        input = input1;
        taskName = new javax.swing.JTextField(input1, 10);        
        
        msg_top = "Name";
        guide_top = "At least 1 character";     
        
        jp_top = new javax.swing.JPanel();
        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());        
        jp_top.setLayout(new java.awt.GridBagLayout());
        jl = new javax.swing.JLabel(msg_top);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jl, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
        jp_top.add(taskName, gridBagConstraints);
        
        jl = new javax.swing.JLabel(guide_top);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jl, gridBagConstraints);
        
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
                taskName.requestFocusInWindow();
            }
        });  
    }

    
    // to handle for mouse click
    private void buttonMouseClicked(java.awt.event.MouseEvent e)
    {
        // check the validity for the input name first
        if(taskName.getText().compareTo(input) == 0 && taskName.getText().compareTo("") != 0)  // must be valid already
        {
            ret_val = taskName.getText();
            clearAndHide();
        }
        else
        {
            String tmp = taskName.getText();
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
                    ret_val = taskName.getText();
                    clearAndHide();
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
     * To handle for the cancel mouse button clicked
     */
    private void cancelMouseClicked(java.awt.event.MouseEvent e)
    {
        ret_val = "";
        taskName.setText(null);
        setVisible(false);
    }
    
    /**
     * Return the user input of task name
     * @return User input
     */
    public String getUserInput() {
        return ret_val;
    }

    
    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        taskName.setText(null);
        setVisible(false);
    }    
}
