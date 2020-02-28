/*
 * VarInputDialog.java
 *
 * Created on 23 January 2006, 11:27
 *
 */

package funsoftware.ic;

import funsoftware.var.*;
import funsoftware.consts.*;
import funsoftware.struct.*;

/**
 * This dialog displays a panel asking for variable input into an icon's attribute
 * @author Thomas Legowo
 */
public class VarInputDialog extends javax.swing.JDialog{
    
    // private variable
    private javax.swing.JPanel big_panel;
    private javax.swing.JPanel jp_bottom;
    private javax.swing.JButton button;
    private javax.swing.JComboBox jcb;
    private java.awt.GridBagConstraints gridBagConstraints;
    
    private variable ret_val;
    private auxiliary current_aux;
    
    /**
     * Creates a new instance of FunctionNameDialog
     * @param frame The frame
     * @param icon The icon
     * @param current_aux The auxiliary the icon is on
     */
    public VarInputDialog(java.awt.Frame frame, Icon icon, auxiliary current_aux) {
        super(frame, true);
        ret_val = new variable();
        this.current_aux = current_aux;
        setTitle("Set Variable to Use");
        
        big_panel = new javax.swing.JPanel();
        big_panel.setLayout(new java.awt.GridBagLayout());
        jp_bottom = new javax.swing.JPanel();
        jp_bottom.setLayout(new java.awt.GridBagLayout());        
        
        javax.swing.JPanel jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        jcb = new javax.swing.JComboBox();
        java.awt.GridBagConstraints gridBagConstraints;
        
        java.util.Vector<variable> vars = var_list.Instance().getVariables();
        for(int i=1; i<vars.size(); i++)
        {
            variable tmp_var = vars.get(i);
            if(tmp_var != null)
            {
                if(tmp_var.getNumId() > 3)
                {
                    jcb.addItem(tmp_var.getName());                
                }   
            }
        }
        
        vars = current_aux.getVariables();
        for(int i=1; i<vars.size(); i++)
        {
            variable tmp_var = vars.get(i);
            if(tmp_var != null)
            {
                jcb.addItem(tmp_var.getName());      
            }
        }
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(icon.getVarLabel(), gridBagConstraints);        
                
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(jcb, gridBagConstraints);  
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        big_panel.add(jp, gridBagConstraints);        
        
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
        big_panel.add(jp_bottom, gridBagConstraints);       

        setContentPane(big_panel);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent we) {
                /*
                 * Instead of directly closing the window,
                 * we're going to change the JOptionPane's
                 * value property.
                 */
            }
        });
        
         //Ensure the text field always gets the first focus.
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent ce) {
            }
        });    
    }    
    
    // to handle for mouse click
    private void buttonMouseClicked(java.awt.event.MouseEvent e)
    {
        String tmps = (String)jcb.getSelectedItem();
        if(tmps == null)
        {
            ret_val = null;
        }
        else
        {
            ret_val = var_list.Instance().getVariable(tmps);
            if(ret_val == null)
            {
                ret_val = current_aux.getVariable(tmps);
            }           
        }

        setVisible(false);
    }    
 
    /**
     * To handle for the cancel mouse button clicked
     */
    private void cancelMouseClicked(java.awt.event.MouseEvent e)
    {
        ret_val = null;
        setVisible(false);
    }
    
    /**
     * Return the user input of function name
     * @return User input
     */
    public variable getUserInput() {
        return ret_val;
    }

    
    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        setVisible(false);
    }   
    
}
