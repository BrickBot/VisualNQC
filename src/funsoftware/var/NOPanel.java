/*
 * NOPanel.java
 *
 * Created on 12 January 2006, 12:37
 *
 */

package funsoftware.var;

import funsoftware.consts.*;
import funsoftware.struct.*;
import funsoftware.var.*;
import funsoftware.events.*;

/**
 * This class represents a panel that can call another identical panel to be nested in it and returns a representation of it
 * in the form of a NestedOperation class
 * @author Thomas Legowo
 */
public class NOPanel extends javax.swing.JPanel{
    
    // the parent jdialog
    VarOperationDialog jd;
    java.awt.GridBagConstraints gridBagConstraints;
    
    // the list of local variables passed onto this panel 
    private java.util.Vector<variable> list_var;
    
    private java.awt.Frame frame;
    private javax.swing.JComboBox operand1;
    private javax.swing.JButton operand1_button;
    private javax.swing.JComboBox operator; 
    
    private javax.swing.JComboBox main_selop1;
    private javax.swing.JComboBox main_selop2;
    private javax.swing.JComboBox main_selop3;
    
    // for the textfields to get a constant value
    private javax.swing.JTextField text1;
    
    private javax.swing.JLabel left_bracket;
    private javax.swing.JLabel right_bracket;
    private javax.swing.JLabel top_left_bracket;
    private javax.swing.JLabel top_right_bracket;
    private auxiliary aux;
    
    private javax.swing.JPanel jp_block1;   // variable block
    private javax.swing.JPanel jp_block2;   // constant block
    private javax.swing.JPanel jp_block3;   // nested operation block
        
    // for nested operations 
    private NOPanel NO1;
    private NOPanel NO2;
    
    private NOPanel parent;
    
    /**
     * Creates a new instance of NOPanel
     * @param frame The frame
     * @param jdg The parent dialog that calls this dialog
     * @param aux The auxiliary on the arithmetic operation
     */
    public NOPanel(java.awt.Frame frame, VarOperationDialog jdg, auxiliary aux) {
        initSetUp(frame, jdg, aux, null);
    }
 
    
    /**
     * Creates a new instance of NOPanel for editing operation purposes
     * @param frame The frame
     * @param NOp The operand
     * @param jdg The parent dialog that calls this dialog
     * @param aux The auxiliary on the arithmetic operation
     */
    public NOPanel(java.awt.Frame frame, Operand NOp, VarOperationDialog jdg, auxiliary aux) {
        initSetUp(frame, jdg, aux, NOp);
    }   

    
    /**
     * To set up the default panels, buttons and menus for a minimum set up of a variable related arithmetic operation dialog
     * @param frame The frame
     * @param jdg The parent dialog that calls this dialog
     * @param aux_local The auxiliary on the arithmetic operation
     * @param NOp The operand
     */
    public void initSetUp(java.awt.Frame frame, VarOperationDialog jdg, auxiliary aux_local, Operand NOp)
    {
        jd = jdg;

        this.aux = aux_local;
        this.frame = frame;
        // Initialise the panels with all of its elements
            
        if(aux instanceof monitorEvent)
        {
            monitorEvent e = (monitorEvent)aux_local;
            this.aux = e.getAuxPoint();
        }
            
        list_var = aux.getVariables();        
        
        operand1 = new javax.swing.JComboBox();    
        operator = new javax.swing.JComboBox();
        operator.setBackground(new java.awt.Color(255, 255, 190));
        main_selop1 = new javax.swing.JComboBox();
        main_selop1.setBackground(new java.awt.Color(255, 255, 190));
        main_selop2 = new javax.swing.JComboBox();
        main_selop2.setBackground(new java.awt.Color(255, 255, 190));
        main_selop3 = new javax.swing.JComboBox();
        main_selop3.setBackground(new java.awt.Color(255, 255, 190));
        
        text1 = new javax.swing.JTextField();
        text1.setFont(new java.awt.Font("Tahoma", 0, 12));
        text1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text1.setPreferredSize(new java.awt.Dimension(70,20));
        text1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        main_selop1.addItem("Variable");
        main_selop1.addItem("Constant");
        main_selop1.addItem("Operation");
        
        main_selop2.addItem("Variable");
        main_selop2.addItem("Constant");
        main_selop2.addItem("Operation");
        
        main_selop3.addItem("Variable");
        main_selop3.addItem("Constant");
        main_selop3.addItem("Operation");
        
        left_bracket = new javax.swing.JLabel("(");
        left_bracket.setFont(new java.awt.Font("Tahoma", 1, 16));
        left_bracket.setForeground(new java.awt.Color(0, 155, 255));
        right_bracket = new javax.swing.JLabel(")");
        right_bracket.setFont(new java.awt.Font("Tahoma", 1, 16));
        right_bracket.setForeground(new java.awt.Color(0, 155, 255));
                
        top_left_bracket = new javax.swing.JLabel("(");
        top_left_bracket.setFont(new java.awt.Font("Tahoma", 1, 16));
        top_left_bracket.setForeground(new java.awt.Color(0, 155, 255));
        top_right_bracket = new javax.swing.JLabel(")");
        top_right_bracket.setFont(new java.awt.Font("Tahoma", 1, 16));
        top_right_bracket.setForeground(new java.awt.Color(0, 155, 255));
        
        // fill in the variables' drop down menu
        java.util.Vector<variable> vv = var_list.Instance().getVariables();
        for(int i=1; i<vv.size(); i++)
        {
            variable tmp_v = vv.get(i);
            if(tmp_v != null)
            {
                operand1.addItem(tmp_v.getName());      
            }
        }
        // now add the local variables
        for(int i=1; i<list_var.size(); i++)
        {
            variable tmp_v = list_var.get(i);
            if(tmp_v != null)
            {
                operand1.addItem(tmp_v.getName());      
            }          
        }
        
        operand1_button = new javax.swing.JButton((String)(operand1.getSelectedItem()));
        
        // fill in the operators' drop down menu        
        operator.addItem(" + ");   // the default value
        operator.addItem(" - ");
        operator.addItem(" * ");
        operator.addItem(" / ");    
        
        setLayout(new java.awt.GridBagLayout());
        
        // variable block
        jp_block1 = new javax.swing.JPanel();
        jp_block1.setLayout(new java.awt.GridBagLayout());                
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp_block1.add(main_selop1, gridBagConstraints); 
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp_block1.add(operand1, gridBagConstraints);
        operand1.setVisible(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp_block1.add(operand1_button, gridBagConstraints);
        operand1_button.setVisible(true);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jp_block1, gridBagConstraints);        
                      
        // constant block
        jp_block2 = new javax.swing.JPanel();
        jp_block2.setLayout(new java.awt.GridBagLayout());                
        jp_block2.setVisible(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp_block2.add(main_selop2, gridBagConstraints); 
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp_block2.add(text1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        
        add(jp_block2, gridBagConstraints);    
        
        
        // NO block
        jp_block3 = new javax.swing.JPanel();
        jp_block3.setLayout(new java.awt.GridBagLayout());                
        jp_block3.setVisible(false);
        
        javax.swing.JPanel tmp_panel = new javax.swing.JPanel();
        tmp_panel.setLayout(new java.awt.GridBagLayout());
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tmp_panel.add(main_selop3, gridBagConstraints); 
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tmp_panel.add(operator, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp_block3.add(tmp_panel, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        
        add(jp_block3, gridBagConstraints);            
        
        
        main_selop1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeOperAction(evt, 1);
            }
        });
        main_selop2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeOperAction(evt, 2);
            }
        });     
        main_selop3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeOperAction(evt, 3);
            }
        }); 

        operand1_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelect(evt, 1);
            }
        });
        operand1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operand1Action(evt, 1);
            }
        });        
        
        if(NOp != null)   // for loading
        {
            if(NOp instanceof variable)
            {
                variable v = (variable)NOp;
                String name = v.getName();
                
                main_selop1.setSelectedItem("Variable");
                operand1.setSelectedItem(name);       
                operand1_button.setText((String)(operand1.getSelectedItem()));
            }
            else if(NOp instanceof constant)
            {
                main_selop2.setSelectedItem("Constant");
                
                constant c = (constant)NOp;
                text1.setText(Integer.toString(c.getValue()));
            }
            else if(NOp instanceof NestedOperation)
            {
                
                
                NestedOperation tmp = (NestedOperation)NOp;
                String to_select = " "+tmp.getOperator().getOperator()+" ";
                operator.setSelectedItem(to_select);
                NO1 = new NOPanel(frame, tmp.getOperand1(), jd, aux); 
                
                tmp_panel = new javax.swing.JPanel();
                tmp_panel.setLayout(new java.awt.GridBagLayout());

                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
                tmp_panel.add(top_left_bracket, gridBagConstraints); 

                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 1;
                gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
                tmp_panel.add(left_bracket, gridBagConstraints); 

                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
                jp_block3.add(tmp_panel, gridBagConstraints); 

                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
                jp_block3.add(NO1, gridBagConstraints);
            
                NO2 = new NOPanel(frame, tmp.getOperand2(), jd, aux); 
                
                tmp_panel = new javax.swing.JPanel();
                tmp_panel.setLayout(new java.awt.GridBagLayout());

                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 3;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
                jp_block3.add(NO2, gridBagConstraints);       

                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
                tmp_panel.add(top_right_bracket, gridBagConstraints); 

                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 1;
                gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
                tmp_panel.add(right_bracket, gridBagConstraints); 

                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 4;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
                jp_block3.add(tmp_panel, gridBagConstraints);
                
                main_selop3.setSelectedItem("Operation");
            }          
        }        
    }    
    
    /**
     * This method reacts to state changes in the combo box containing the types of an operand.
     * @param type The type of this operand
     * @param e ActionEvent
     */
    public void typeOperAction(java.awt.event.ActionEvent e, int type) {
        String selected = new String();
                
        if(NO1 == null)  // not yet initialised
        {
            javax.swing.JPanel tmp_panel = new javax.swing.JPanel();
            tmp_panel.setLayout(new java.awt.GridBagLayout());
        
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
            tmp_panel.add(top_left_bracket, gridBagConstraints); 
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
            tmp_panel.add(left_bracket, gridBagConstraints); 
        
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
            jp_block3.add(tmp_panel, gridBagConstraints); 
        
            NO1 = new NOPanel(frame, jd, aux); 
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
            jp_block3.add(NO1, gridBagConstraints);
        }
        
        if(NO2 == null)
        {
            javax.swing.JPanel tmp_panel = new javax.swing.JPanel();
            tmp_panel.setLayout(new java.awt.GridBagLayout());
            
            NO2 = new NOPanel(frame, jd, aux);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
            jp_block3.add(NO2, gridBagConstraints);       
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
            tmp_panel.add(top_right_bracket, gridBagConstraints); 
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
            tmp_panel.add(right_bracket, gridBagConstraints); 
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
            jp_block3.add(tmp_panel, gridBagConstraints); 
        }        
        
        if(type == 1)
        {
            selected = (String)main_selop1.getSelectedItem();
            if(selected.compareTo("Variable") == 0)
            {
                main_selop1.setSelectedItem("Variable");                
                jp_block1.setVisible(true);
                jp_block2.setVisible(false);
                jp_block3.setVisible(false);
                jd.resetViewport(); 
            }
            else if(selected.compareTo("Constant") == 0)
            {                
                main_selop2.setSelectedItem("Constant");
                jp_block2.setVisible(true);
                jp_block1.setVisible(false);
                jp_block3.setVisible(false);
                jd.resetViewport(); 
            }
            else if(selected.compareTo("Operation") == 0)
            {
                main_selop3.setSelectedItem("Operation");
                jp_block3.setVisible(true);
                jp_block1.setVisible(false);
                jp_block2.setVisible(false);                
                jd.resetViewport();
            }
            jd.setLocationRelativeTo(frame);
            repaint();
        }
        else if(type == 2)
        {
            selected = (String)main_selop2.getSelectedItem();
            if(selected.compareTo("Variable") == 0)
            {
                main_selop1.setSelectedItem("Variable");                
                jp_block1.setVisible(true);
                jp_block2.setVisible(false);
                jp_block3.setVisible(false);
                jd.resetViewport(); 
            }
            else if(selected.compareTo("Constant") == 0)
            {                
                main_selop2.setSelectedItem("Constant");
                jp_block2.setVisible(true);
                jp_block1.setVisible(false);
                jp_block3.setVisible(false);
                jd.resetViewport(); 
            }
            else if(selected.compareTo("Operation") == 0)
            {
                main_selop3.setSelectedItem("Operation");
                jp_block3.setVisible(true);
                jp_block1.setVisible(false);
                jp_block2.setVisible(false);                
                jd.resetViewport();
            }
            jd.setLocationRelativeTo(frame);
            repaint();
        }
        else if(type == 3)
        {
            selected = (String)main_selop3.getSelectedItem();
            if(selected.compareTo("Variable") == 0)
            {
                main_selop1.setSelectedItem("Variable");                
                jp_block1.setVisible(true);
                jp_block2.setVisible(false);
                jp_block3.setVisible(false);
                jd.resetViewport(); 
            }
            else if(selected.compareTo("Constant") == 0)
            {                
                main_selop2.setSelectedItem("Constant");
                jp_block2.setVisible(true);
                jp_block1.setVisible(false);
                jp_block3.setVisible(false);
                jd.resetViewport();     
            }      
            else if(selected.compareTo("Operation") == 0)
            {
                main_selop3.setSelectedItem("Operation");
                jp_block3.setVisible(true);
                jp_block1.setVisible(false);
                jp_block2.setVisible(false);
                jd.resetViewport();
            }            
            jd.setLocationRelativeTo(frame);
            repaint();
        }        
    }    
    
    /**
     * To get an operand class out of this as well as checking for validity
     * @return The operand
     */
    public Operand getOperand()
    {
        Operand ret_val = new Operand();
        if(jp_block1.isVisible() == true)  // a variable
        {
            // get the variable
            String tmps = (String)operand1.getSelectedItem();
            variable tmp_var = var_list.Instance().getVariable(tmps);

            if(tmp_var == null)
            {
                tmp_var = aux.getVariable(tmps);
            }
            ret_val = tmp_var;            
        }
        else if(jp_block2.isVisible() == true)  // a constant
        {
            // Check for validity here            
            try{
                constant tmp_constant = new constant();
                tmp_constant.setValue(Integer.parseInt(text1.getText()));
                ret_val = tmp_constant;
                text1.setBackground(new java.awt.Color(255, 255, 255));
            }
            catch(RuntimeException re)
            {
                javax.swing.JOptionPane.showMessageDialog(null,
                "Input value for the constant in Operand1 is invalid.\nPlease enter numbers only.\nPlease Try Again.",
                "Input Invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
                text1.requestFocusInWindow();
                text1.setBackground(new java.awt.Color(255, 190, 190));
                return null;
            }
        }
        else if(jp_block3.isVisible() == true)  // a nested operation
        {
            NestedOperation NO = new NestedOperation();
            Operand tmp = NO1.getOperand();
            if(tmp == null)
            {
                return null;
            }
            else
            {
                NO.setOperand1(tmp);
            }    
            tmp = NO2.getOperand();
            if(tmp == null)
            {
                return null;
            }
            else
            {
                NO.setOperand2(tmp);
            }
            Operator op = new Operator();
            String sel = (String)operator.getSelectedItem();
            op.setOperator(sel.trim());
            NO.setOperator(op);
            ret_val = NO;
        }
        
        return ret_val;
    } 
        
    /**
     * This method reacts to state changes in the combo box containing the types of an operand.
     * @param type The type of this operand
     * @param e ActionEvent
     */
    public void operand1Action(java.awt.event.ActionEvent e, int type) 
    {
        if(type == 1)
        {
            operand1.setVisible(false);
            operand1_button.setVisible(true);
            operand1_button.setText((String)(operand1.getSelectedItem()));
        }
    }           
        
    /**
     * This method reacts to state changes in the variable button.
     * @param type The type of operand selected
     * @param e ActionEvent
     */
    public void buttonSelect(java.awt.event.ActionEvent e, int type)
    {
        if(type == 1)
        {
            operand1_button.setVisible(false);
            operand1.setVisible(true);
        }        
    }
    
}
