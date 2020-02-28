/*
 * FunctionArgumentDialog.java
 *
 * Created on 19 January 2006, 17:24
 *
 */

package funsoftware.ic.func;

import funsoftware.consts.*;
import funsoftware.struct.*;
import funsoftware.functs.*;
import funsoftware.var.*;

/**
 * This class caters for the user inputs of the function call arguments
 * @author Thomas Legowo
 */
public class FunctionArgumentDialog extends javax.swing.JDialog{

    // private variables
    private java.awt.Frame frame;
    private auxiliary current_aux;
        
    java.awt.GridBagConstraints gridBagConstraints;

    private javax.swing.JPanel jp;
    private javax.swing.JPanel arguments;
    private javax.swing.JPanel individual_arg;
    private javax.swing.JPanel jp_bottom;
    private javax.swing.JLabel jl;    
    private javax.swing.JButton button;  
    
    private String msg_top;
    
    // return values, the arguments for function call
    private java.util.Vector<Operand> ret_val;    
    
    /**
     * Creates a new instance of FunctionArgumentDialog
     * @param frame The frame
     * @param fu The function
     * @param current_aux Current auxiliary
     * @param fic The function icon
     */
    public FunctionArgumentDialog(java.awt.Frame frame, function fu, auxiliary current_aux, functionIcon fic) {
        super(frame, true);
        this.frame = frame;
        this.current_aux = current_aux;
        ret_val = new java.util.Vector<Operand>();
        
        setTitle("Set Function Call and Arguments");
        
        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        arguments = new javax.swing.JPanel();
        arguments.setLayout(new javax.swing.BoxLayout(arguments, javax.swing.BoxLayout.Y_AXIS));
        jp_bottom = new javax.swing.JPanel();
        jp_bottom.setLayout(new java.awt.GridBagLayout());        
  
        // put in the arguments display first
        int total = fu.getArguments().size();
        for(int i=0; i<total; i++)
        {
            individual_arg = new javax.swing.JPanel();
            individual_arg.setLayout(new java.awt.GridBagLayout());
            javax.swing.JTextField arg = new javax.swing.JTextField("", 10);  
            arg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
   
            javax.swing.JLabel arg_name = new javax.swing.JLabel(fu.getArguments().get(i).getName());
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            individual_arg.add(arg_name, gridBagConstraints);              
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            individual_arg.add(arg, gridBagConstraints);    
            
            javax.swing.JComboBox jcb = new javax.swing.JComboBox();
            jcb.addItem("Constant");
                            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            individual_arg.add(jcb, gridBagConstraints);  
            
            final int row = arguments.getComponentCount();
            jcb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeOperAction(evt, row);
            }
            });
            
            javax.swing.JComboBox variables = new javax.swing.JComboBox();
            java.util.Vector<variable> list_var = current_aux.getVariables();
            if(list_var.size() > 1)
            {
                jcb.addItem("Variable");
                for(int i2=1; i2<list_var.size(); i2++)
                {
                    variable tmp = list_var.get(i2);
                    if(tmp != null)
                    {
                        variables.addItem(tmp.getName());  
                    }
                }
            }          
            variables.setVisible(false);
                
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            individual_arg.add(variables, gridBagConstraints);  
            arguments.add(individual_arg);            
        }
        
        
        // put in the buttons
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(arguments, gridBagConstraints);            
        
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
            }
        });
        
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent ce) {

            }
        });     
        
        if(fic != null)
        {
            ret_val = fic.getArguments();
            for(int i=0; i<ret_val.size(); i++)
            {            
                javax.swing.JPanel tmp = (javax.swing.JPanel)arguments.getComponent(i);
                javax.swing.JTextField jtf = (javax.swing.JTextField)tmp.getComponent(1);
                javax.swing.JComboBox selection = (javax.swing.JComboBox)tmp.getComponent(2);            
                javax.swing.JComboBox jcb = (javax.swing.JComboBox)tmp.getComponent(3);
                
                if(ret_val.get(i) instanceof constant)
                {
                    constant c = (constant)ret_val.get(i);
                    jtf.setText(Integer.toString(c.getValue()));
                    selection.setSelectedItem("Constant");
                }
                else if(ret_val.get(i) instanceof variable)
                {
                    variable v = (variable)ret_val.get(i);
                    jcb.setSelectedItem(v.getName());
                    selection.setSelectedItem("Variable");
                }
            }
        }
    }
  
    // to handle for mouse click
    private void buttonMouseClicked(java.awt.event.MouseEvent e)
    {
        // add the operand results onto the ret_val vector
        int to_close = 1;  // allowed to close the window
        int size = arguments.getComponentCount();
        String selected = new String();
        ret_val = new java.util.Vector<Operand>();
                
        for(int i=0; i<size; i++)
        {
            javax.swing.JPanel tmp = (javax.swing.JPanel)arguments.getComponent(i);
            javax.swing.JTextField jtf = (javax.swing.JTextField)tmp.getComponent(1);
            javax.swing.JComboBox selection = (javax.swing.JComboBox)tmp.getComponent(2);
            selected = (String)selection.getSelectedItem();
            javax.swing.JComboBox jcb = (javax.swing.JComboBox)tmp.getComponent(3);
            
            if(selected.compareTo("Constant") == 0)
            {
                try
                {
                    constant new_constant = new constant();
                    int value = Integer.parseInt(jtf.getText());
                    new_constant.setValue(value);
                    ret_val.addElement(new_constant);
                }
                catch(RuntimeException re)
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                    "Input value for the constant is invalid.\nPlease enter numbers only.\nPlease Try Again.",
                    "Input Invalid",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                    to_close = 0;
                    ret_val = null;
                    break;
                }
            }
            else if(selected.compareTo("Variable") == 0)
            {
                String name = (String)jcb.getSelectedItem();
                variable new_var = current_aux.getVariable(name); // must be local
                ret_val.addElement(new_var);
            }
        }  
        
        if(to_close == 1)
        {
            setVisible(false);
        }
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
     * Return the user input of function call arguments
     * @return User input
     */
    public java.util.Vector<Operand> getUserInput() {
        return ret_val;
    }    
    
    /**
     * This method reacts to state changes in the combo box containing the types of an operand.
     * @param row The position of the arguments
     * @param e PropertyChangeEvent
     */
    public void typeOperAction(java.awt.event.ActionEvent e, int row) {
        String selected = new String();

        javax.swing.JPanel tmp = (javax.swing.JPanel)arguments.getComponent(row);
        javax.swing.JTextField jtf = (javax.swing.JTextField)tmp.getComponent(1);
        javax.swing.JComboBox selection = (javax.swing.JComboBox)tmp.getComponent(2);
        selected = (String)selection.getSelectedItem();
        javax.swing.JComboBox jcb = (javax.swing.JComboBox)tmp.getComponent(3);
        if(selected.compareTo("Constant") == 0)
        {
            jtf.setVisible(true);
            jcb.setVisible(false);         
        }
        else if(selected.compareTo("Variable") == 0)
        {
            jcb.setVisible(true);
            jtf.setVisible(false);              
        }           
        pack();
        setLocationRelativeTo(frame);
        repaint();   
    }
}
