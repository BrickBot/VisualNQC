/*
 * ArithComparatorDialog.java
 *
 * Created on 7 February 2006, 17:27
 *
 */

package funsoftware.ic;

import funsoftware.struct.*;
import funsoftware.events.*;
import funsoftware.var.*;
import funsoftware.consts.*;

/**
 * This class ask for an arithmetic expression input from the user
 * @author Thomas Legowo
 */
public class ArithComparatorDialog extends javax.swing.JDialog{
    
    // private variables
    private java.awt.GridBagConstraints gridBagConstraints;
    
    private javax.swing.JComboBox operand_left;
    private javax.swing.JComboBox operator;
    private javax.swing.JComboBox operand_right;
    private javax.swing.JComboBox left_variables;
    private javax.swing.JComboBox right_variables;
    private javax.swing.JTextField right_constant;
    private javax.swing.JTextField left_constant;
    
    private javax.swing.JButton button1;
    private javax.swing.JButton button2;
    
    private javax.swing.JPanel jp;
    private javax.swing.JPanel jp_top;
    private javax.swing.JPanel jp_bottom;
    
    private auxiliary current_aux;
    
    private java.awt.Frame frame;
    private NestedOperation NO;
    
    /**
     * Creates a new instance of ArithComparatorDialog
     * @param frame The frame
     * @param aux The auxiliary this operation is on
     * @param newNO The nested operation for the arithmetic expression
     */
    public ArithComparatorDialog(java.awt.Frame frame, auxiliary aux, NestedOperation newNO) {
        super(frame, true);
        this.frame = frame;
        
        NO = new NestedOperation();
        setTitle("Branch Arithmetic Expression");
        
        if(aux instanceof monitorEvent)
        {
            monitorEvent e = (monitorEvent)aux;
            current_aux = e.getAuxPoint();
            
            if (null == current_aux){
                // then e is global, not local
                current_aux = aux;
            }
        }
        else
        {
            current_aux = aux;
        }
        
        operand_left = new javax.swing.JComboBox();
        operand_left.addItem("Constant");
        operand_left.addItem("Variable");
        operand_right = new javax.swing.JComboBox();
        operand_right.addItem("Constant");  
        operand_right.addItem("Variable");            
        
        left_variables = new javax.swing.JComboBox();
        right_variables = new javax.swing.JComboBox();        
        
        // do the global variables
        java.util.Vector<variable> vv = var_list.Instance().getVariables();
        for(int i=1; i<vv.size(); i++)
        {
            variable tmp_v = vv.get(i);
            if(tmp_v != null)
            {
                left_variables.addItem(tmp_v.getName());   
                right_variables.addItem(tmp_v.getName());     
            }
        }
        
        vv = current_aux.getVariables();
        // then do the local variables
        for(int i=1; i<vv.size(); i++)
        {
            variable tmp_v = vv.get(i);
            if(tmp_v != null)
            {
                left_variables.addItem(tmp_v.getName());    
                right_variables.addItem(tmp_v.getName()); 
            }         
        }        
        
        operator = new javax.swing.JComboBox();
        operator.addItem(">");
        operator.addItem(">=");
        operator.addItem("<");
        operator.addItem("<=");
        operator.addItem("==");     
        operator.addItem("!=");  
        
        left_constant = new javax.swing.JTextField("", 8);
        left_constant.setFont(new java.awt.Font("Tahoma", 0, 12));
        left_constant.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        right_constant = new javax.swing.JTextField("", 8);
        right_constant.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        right_constant.setFont(new java.awt.Font("Tahoma", 0, 12));
        
        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        jp_top = new javax.swing.JPanel();
        jp_top.setLayout(new java.awt.GridBagLayout());
        jp_bottom = new javax.swing.JPanel();
        jp_bottom.setLayout(new java.awt.GridBagLayout());        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(operand_left, gridBagConstraints);   
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(operator, gridBagConstraints);  
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(operand_right, gridBagConstraints);     
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(left_constant, gridBagConstraints);   
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(left_variables, gridBagConstraints);          
        left_variables.setVisible(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(right_constant, gridBagConstraints);      
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(right_variables, gridBagConstraints);            
        right_variables.setVisible(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(jp_top, gridBagConstraints);         
        
        button1 = new javax.swing.JButton("Enter");
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button1MouseClicked();
            }
        });
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_bottom.add(button1, gridBagConstraints);            
        
        button2 = new javax.swing.JButton("Cancel");
        button2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button2MouseClicked();
            }
        });        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_bottom.add(button2, gridBagConstraints);     
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(jp_bottom, gridBagConstraints);            
        
        current_aux = aux;
        
        setContentPane(jp);
        
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
        
         //Ensure the combo box always gets the first focus.
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent ce) {
            }
        });  
        
        operand_left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeOperand(1);
            }
        });
        operand_right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeOperand(2);
            }
        });        
        
        
        // for edit mode
        if(newNO != null)
        {
            // sets the operator
            operator.setSelectedItem(newNO.getOperator().getOperator());
            
            // sets the first operand
            Operand op = newNO.getOperand1();
            if(op instanceof variable)
            {
                operand_left.setSelectedItem("Variable");
                variable tmp_v = (variable)op;
                    
                left_variables.setSelectedItem(tmp_v.getName());                
            }
            else if(op instanceof constant)
            {
                operand_left.setSelectedItem("Constant");
                constant tmp_c = (constant)op;
                left_constant.setText(Integer.toString(tmp_c.getValue()));
            }
            
            // sets the second operand
            op = newNO.getOperand2();
            if(op instanceof variable)
            {
                operand_right.setSelectedItem("Variable");
                variable tmp_v = (variable)op;
                right_variables.setSelectedItem(tmp_v.getName());  
            }
            else if(op instanceof constant)
            {
                operand_right.setSelectedItem("Constant");
                constant tmp_c = (constant)op;
                right_constant.setText(Integer.toString(tmp_c.getValue()));
            }   
        }
    }
    
    // The handler for the changing operand type, constant or variable
    private void changeOperand(int type)
    {
        String selected = new String();
        if(type == 1)
        {
            selected = (String)operand_left.getSelectedItem();
            if(selected.compareTo("Constant") == 0)
            {
                left_constant.setVisible(true);
                left_variables.setVisible(false);
            }
            else if(selected.compareTo("Variable") == 0)
            {
                left_variables.setVisible(true);
                left_constant.setVisible(false);                
            }
        }
        else if(type == 2)
        {
            selected = (String)operand_right.getSelectedItem();
            if(selected.compareTo("Constant") == 0)
            {
                right_constant.setVisible(true);
                right_variables.setVisible(false);                
            }
            else if(selected.compareTo("Variable") == 0)
            {
                right_variables.setVisible(true);
                right_constant.setVisible(false);                   
            }            
        }
        pack();
        setLocationRelativeTo(frame);
    }    
    
    // The handler for the button1 -- Ok
    private void button1MouseClicked()
    {        
        int to_close = 0; // zero means can close, 1 means can't close
        
        // sets the operator
        Operator Ot = new Operator();
        Ot.setOperator((String)operator.getSelectedItem());
        NO.setOperator(Ot);
        
        variable tmp_var;
        
        // sets the left operand
        String selected = (String)operand_left.getSelectedItem();
        if(selected.compareTo("Variable") == 0)
        {
            selected = (String)left_variables.getSelectedItem();
            tmp_var = var_list.Instance().getVariable(selected);
            
            if(tmp_var == null)
            {
                tmp_var = current_aux.getVariable(selected);
            }
            NO.setOperand1(tmp_var);            
        }
        else if(selected.compareTo("Constant") == 0)
        {
            try
            {
                constant c = new constant();
                c.setValue(Integer.parseInt(left_constant.getText()));
                NO.setOperand1(c);
            }
            catch(RuntimeException re)
            {
                javax.swing.JOptionPane.showMessageDialog(null,
                    "Input value for the constant in Operand1 is invalid.\nPlease enter numbers only.\nPlease Try Again.",
                    "Input Invalid",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                    left_constant.requestFocusInWindow();
                    to_close = 1;
            }
        }
        
        if(to_close == 0)
        {
            // sets the right operand
            selected = (String)operand_right.getSelectedItem();
            if(selected.compareTo("Variable") == 0)
            {
                selected = (String)right_variables.getSelectedItem();
                tmp_var = var_list.Instance().getVariable(selected);
            
                if(tmp_var == null)
                {
                    tmp_var = current_aux.getVariable(selected);
                }
                NO.setOperand2(tmp_var);
            }
            else if(selected.compareTo("Constant") == 0)
            {
                try
                {
                    constant c = new constant();
                    c.setValue(Integer.parseInt(right_constant.getText()));
                    NO.setOperand2(c);                
                }
                catch(RuntimeException re)
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                        "Input value for the constant in Operand2 is invalid.\nPlease enter numbers only.\nPlease Try Again.",
                        "Input Invalid",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                        right_constant.requestFocusInWindow();
                        to_close = 1;
                }            
            }        
        }
        
        if(to_close == 0)
        {
            setVisible(false);
        }
    }
    
   
    /**
     * To return the NestedOperation
     * @return NestedOperation configured by the user
     */
    public NestedOperation getExpression()
    {
        return NO;
    }
    
    
    // The handler for the button2 -- Cancel
    private void button2MouseClicked()
    {
        NO = null;
        setVisible(false);
    }    
}
