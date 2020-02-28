/*
 * ChooseFunctionDialog.java
 *
 * Created on 8 January 2006, 21:16
 *
 */

package funsoftware.ic.func;

import funsoftware.consts.*;
import funsoftware.struct.*;
import funsoftware.functs.*;

/**
 * This class is a dialog window prompting user for a function to be called
 * An instance of this class will be created as soon as users insert a function icon onto the programming window
 * @author Thomas Legowo
 */
public class ChooseFunctionDialog extends javax.swing.JDialog{
    
    // private variables
    private javax.swing.JComboBox functions;
        
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
     * Creates a new instance of ChooseFunctionDialog
     * @param frame The frame
     * @param f The function
     */
    public ChooseFunctionDialog(java.awt.Frame frame, function f) {
        super(frame, true);
        setTitle("Set Function");
        
        ret_val = 1;  // default num_id of the selected function
        
        functions = new javax.swing.JComboBox();      
        
        // input the tasks
        java.util.Vector<auxiliary> al = aux_list.Instance().getAuxiliaries();
        for(int i=1; i<al.size(); i++)
        {
            auxiliary a = al.get(i);
            if(a != null && a instanceof function)
            {
                function t = (function)a;
                if(f != null && t.getLevel() < f.getLevel())
                {
                    functions.addItem(t.getName());    
                }   
                else if(f == null)
                {
                    functions.addItem(t.getName());  
                }
            }
        }
        
        msg_top = "Function";
        guide_top = "Choose a function";     
        
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
        jp.add(functions, gridBagConstraints);
        
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
                buttonMouseClicked(evt);
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
                cancelMouseClicked(evt);
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
                functions.requestFocusInWindow();
            }
        });     
    }
  
    // If the "Cancel" button was pressed
    private void cancelMouseClicked(java.awt.event.MouseEvent e)
    {
        ret_val = -1;
        clearAndHide();
    }    
    
    // If the "Select" button was pressed
    private void buttonMouseClicked(java.awt.event.MouseEvent e)
    {
        ret_val = aux_list.Instance().getAuxiliary((String)functions.getSelectedItem()).getNumId();
        clearAndHide();
    }
    
    /**
     * Return the user input of function name
     * @return User input
     */
    public int getUserInput() {
        return ret_val;
    }

    
    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        functions.removeAllItems();
        setVisible(false);
    }    
}
