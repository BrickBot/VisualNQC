/*
 * DeclareVariableDialog.java
 *
 * Created on 10 January 2006, 12:03
 *
 */

package funsoftware.var;

import funsoftware.consts.*;
import funsoftware.struct.*;
import funsoftware.events.*;
import funsoftware.tasks.*;
import funsoftware.functs.*;

/**
 * This class acts as a dialog window to ask the user for a variable name and whether it is a global or local
 * @author Thomas Legowo
 */
public class DeclareVariableDialog extends javax.swing.JDialog{
    
    // private variables
    private javax.swing.JComboBox type_of_var;
    private javax.swing.JComboBox functions;
    private javax.swing.JTextField var_name;    
    private javax.swing.JTextField var_value;
    
    java.awt.GridBagConstraints gridBagConstraints;

    private javax.swing.JPanel jp_top;
    private javax.swing.JPanel jp_bottom;
    private javax.swing.JPanel jp_function;   // for function selection
    private javax.swing.JPanel jp;
    private javax.swing.JLabel jl;    
    private javax.swing.JButton button;
    
    private String msg_top;
    private String msg_bottom;
    private String msg_function;
    private String guide_top;    
    private String guide_bottom;
    private String guide_function;
    
    // return values
    private String[] ret_vals;     
    
    private variable to_edit;
    
    /**
     * Creates a new instance of DeclareVariableDialog, type 0 means new variable, type 1 means to be edited variable
     * @param frame The frame
     * @param type The type
     * @param to_edit The variable to be edited
     */
    public DeclareVariableDialog(java.awt.Frame frame, int type, variable to_edit) {
        super(frame, true);
        if(type == 0)
        {
            setTitle("Declare Variable");    
        }
        else
        {
            setTitle("Edit Variable");    
        }
        
        
        ret_vals = new String[4];  
        ret_vals[0] = "";   // the name of the variable
        ret_vals[1] = "Local";
        ret_vals[2] = "";   // the function's name it is supposed to be in if it is a local variable
        ret_vals[3] = "0";  // the value of the variable (in string, it is suppose to be in integers)
        
        type_of_var = new javax.swing.JComboBox();      
        functions = new javax.swing.JComboBox();            
        var_name = new javax.swing.JTextField(ret_vals[0], 8);  
        var_value = new javax.swing.JTextField(Integer.toString(0), 8);
        
        if(type == 0)
        {
            type_of_var.addItem("Global");   // the default value
            type_of_var.addItem("Local");
            this.to_edit = null;
        }
        else if(type == 1)
        {
            if(to_edit.getType() == 0)
            {
                type_of_var.addItem("Global");
            }
            else
            {
                type_of_var.addItem("Local");
            }
            this.to_edit = to_edit;
        }
        
        msg_top = "Variable Name";
        msg_bottom = "Variable Type";
        msg_function = "Function or Task";
        guide_top = "Input at least 1 valid character"; 
        guide_bottom = "Select One";
        guide_function = "Select One";
                        
        jp = new javax.swing.JPanel();                
        jp.setLayout(new java.awt.GridBagLayout());
        
        // before we do anything set up the jpanel for function selection first
        jp_function = new javax.swing.JPanel();
        jp_function.setLayout(new java.awt.GridBagLayout());
        
        java.util.Vector<auxiliary> al = aux_list.Instance().getAuxiliaries();
        for(int i=1; i<al.size(); i++)
        {
            auxiliary tmp_al = al.get(i);
            if(tmp_al != null && (tmp_al instanceof monitorEvent) == false)
            {
                functions.addItem(tmp_al.getName());    
            }            
        }
  
        if(type == 0)
        {
            jl = new javax.swing.JLabel(msg_function);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp_function.add(jl, gridBagConstraints);        

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp_function.add(functions, gridBagConstraints);        

            jl = new javax.swing.JLabel(guide_function);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp_function.add(jl, gridBagConstraints);
            jp_function.setVisible(false);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp.add(jp_function, gridBagConstraints);
        }
        
        
        jp_top = new javax.swing.JPanel();
        jp_bottom = new javax.swing.JPanel();

        jp_top.setLayout(new java.awt.GridBagLayout());
        jp_bottom.setLayout(new java.awt.GridBagLayout());
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
        
        if(type == 1)
        {
            var_name.setText(to_edit.getName());
        }
        jp_top.add(var_name, gridBagConstraints);
        
        jl = new javax.swing.JLabel(guide_top);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jl, gridBagConstraints);
        
    
        jl = new javax.swing.JLabel("Variable Value");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jl, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
        
        if(type == 1)
        {
            var_value.setText(Integer.toString(to_edit.getValue()));
        }
        jp_top.add(var_value, gridBagConstraints);
        
        jl = new javax.swing.JLabel("Enter integer only");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jl, gridBagConstraints);        
        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(jp_top, gridBagConstraints);
                
        if(type == 0)
        {
            jl = new javax.swing.JLabel(msg_bottom);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp_bottom.add(jl, gridBagConstraints);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
            jp_bottom.add(type_of_var, gridBagConstraints);

            jl = new javax.swing.JLabel(guide_bottom);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp_bottom.add(jl, gridBagConstraints);   

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp.add(jp_bottom, gridBagConstraints);
        }
        
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
        gridBagConstraints.gridy = 3;
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
                   // optionPane.setValue(new Integer(
                                     //   javax.swing.JOptionPane.CLOSED_OPTION));
            }
        });
        
         //Ensure the combo box always gets the first focus.
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent ce) {
                var_name.requestFocusInWindow();
            }
        });    
        
        type_of_var.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeVarAction(evt);
            }
        });
    }
    
    // to set up the variable
    private void buttonMouseClicked(java.awt.event.MouseEvent e)
    {
        // check for the values' validity first
        String tmp = var_name.getText();
        if(tmp.compareTo("") == 0)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Please input at least 1 character.",
                "Input Invalid", javax.swing.JOptionPane.WARNING_MESSAGE);              
        }
        else
        {
            int check_res = 0;
            String s = (String)type_of_var.getSelectedItem();
            if(s.compareTo("Local") == 0)
            {
                if(to_edit == null)
                {
                    String check = (String)functions.getSelectedItem();
                    auxiliary a = aux_list.Instance().getAuxiliary(check);
                    check_res = a.getLocalVarList().checkName(tmp, 0);  
                    if(check_res == 0)
                    {
                        check_res = var_list.Instance().checkName(tmp, 0);  
                    }
                }
                else
                {
                    check_res = to_edit.getAux().getLocalVarList().checkName(tmp, to_edit.getNumId());
                    if(check_res == 0)
                    {
                        check_res = var_list.Instance().checkName(tmp, to_edit.getNumId());
                    }
                }
            }
            else
            {
                if(to_edit == null)
                {
                    check_res = var_list.Instance().checkName(tmp, 0);                    
                }
                else
                {
                    check_res = var_list.Instance().checkName(tmp, to_edit.getNumId());                
                }
                if(check_res == 0)
                {
                    // for a global variable, check for all local variables to ensure a name of that global variable is unique
                    java.util.Vector<auxiliary> a_list = aux_list.Instance().getAuxiliaries();
                    for(int i=1; i<a_list.size(); i++)
                    {
                        auxiliary tmp_aux = a_list.get(i);
                        if(tmp_aux != null && (tmp_aux instanceof function || tmp_aux instanceof task))
                        {
                            check_res = tmp_aux.getLocalVarList().checkName(tmp, 0);
                            if(check_res != 0)
                            {
                                break;
                            }
                        }
                    }
                }                
            }
            if(check_res == 0)
            {
                // the name os valid, so check the value input
                try
                {
                    ret_vals[0] = tmp;
                    ret_vals[1] = (String)type_of_var.getSelectedItem();
                    int i = Integer.parseInt(var_value.getText());
                    ret_vals[3] = Integer.toString(i);
                    String selected = (String)type_of_var.getSelectedItem();
                    if(selected.compareTo("Local") == 0)
                    {
                        ret_vals[2] = (String)functions.getSelectedItem();
                    }
                    clearAndHide();
                }
                catch(RuntimeException re)
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                    "Input value for the variable is invalid.\nPlease enter numbers only.\nPlease Try Again.",
                    "Input Invalid",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                    var_value.requestFocusInWindow();
                    var_value.setBackground(new java.awt.Color(255, 190, 190));
                }
            }
            else if(check_res == 1)
            {
                javax.swing.JOptionPane.showMessageDialog(null,
                    "Input value is invalid.\nThe name has been used.\nPlease Try Again.",
                    "Input Invalid", javax.swing.JOptionPane.WARNING_MESSAGE);            
            }
            else if(check_res == 2)
            {
                javax.swing.JOptionPane.showMessageDialog(null,
                    "Input value is invalid.\nPlease enter string and numbers only.\nFirst character has to be a letter.\nPlease Try Again.",
                    "Input Invalid", javax.swing.JOptionPane.WARNING_MESSAGE);              
            }        
        }
    }
 
    // to cancel the variable declaration
    private void cancelMouseClicked(java.awt.event.MouseEvent e)
    {
        ret_vals[0] = "";   // the name of the variable
        clearAndHide();
    }    
    
    /**
     * This method reacts to state changes in the combo box.
     * @param e PropertyChangeEvent
     */
    public void typeVarAction(java.awt.event.ActionEvent e) {
        String selected = (String)type_of_var.getSelectedItem();
        if(selected.compareTo("Local") == 0)   // the user has to select which function or task will this variable resides in
        {
            jp_function.setVisible(true);
            pack();
            repaint();
            validate();            
        }
        else
        {
            if(jp_function.isVisible() == true)
            {
                jp_function.setVisible(false);
                pack();
                repaint();
                validate();
            }
        }
    }
    
    /**
     * Return the user input of task name
     * @return User input
     */
    public String[] getUserInput() {
        return ret_vals;
    }

    
    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        var_name.setText(null);
        setVisible(false);
    }
    
}
