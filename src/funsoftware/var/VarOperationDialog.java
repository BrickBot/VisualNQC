/*
 * VarOperationDialog.java
 *
 * Created on 10 January 2006, 13:45
 *
 */

package funsoftware.var;

import funsoftware.consts.*;
import funsoftware.struct.*;
import funsoftware.var.*;
import funsoftware.events.*;

/**
 * This class represents a dialog window to prompt user for an arithmetic operation
 * @author Thomas Legowo
 */
public class VarOperationDialog extends javax.swing.JDialog{
    
    // private variables
    private javax.swing.JScrollPane jsp;
    private javax.swing.JPanel jsp_parent;
    private javax.swing.JViewport viewport;  // the viewport of the scrollpane1 jsp
    private java.awt.Frame frame;
    private javax.swing.JComboBox variable;
    private javax.swing.JButton var_button;
    private javax.swing.JComboBox selop1;
    
    // for resizing
    private javax.swing.JPanel dummy;
        
    // for nested operations 
    private NOPanel NO;
    
    private java.awt.GridBagConstraints gridBagConstraints;

    private javax.swing.JPanel jp_block1;
    private javax.swing.JPanel jp_block2;
    
    private javax.swing.JPanel jp_top;  // contains jp_block
    private javax.swing.JPanel jp_buttons;   
    private javax.swing.JPanel jp;
    private javax.swing.JLabel jl;    
    private javax.swing.JButton button;
        
    private java.util.Vector<variable> list_var;
    private auxiliary aux;
    
    private String equal;
    
    // return values
    private ArithmeticOperation ret_val;     // an Arithmetic Operation
        
    /**
     * Creates a new instance of VarOperationDialog
     * @param frame The frame
     * @param aux The auxiliary related to this auxiliary
     */
    public VarOperationDialog(java.awt.Frame frame, auxiliary aux) {
        super(frame, true);
        initSetUp(frame, aux, null);
    }
 
    /**
     * Creates a new instance of VarOperationDialog but for editing purposes
     * In this case the dialog would be in its expanded state according to data obtained from the ArithmeticOperation class
     * @param frame The frame
     * @param ArithOp The arithmetic operation
     * @param aux The auxiliary related to this auxiliary
     */
    public VarOperationDialog(java.awt.Frame frame, ArithmeticOperation ArithOp, auxiliary aux) {    
        super(frame, true);
        initSetUp(frame, aux, ArithOp);
    }
    
    /**
     * To set up the default panels, buttons and menus for a minimum set up of a variable related arithmetic operation dialog
     * @param frame The frame
     * @param aux_local The auxiliary related to this auxiliary
     * @param ArithOp The arithmetic operation
     */
    public void initSetUp(java.awt.Frame frame, auxiliary aux_local, ArithmeticOperation ArithOp)
    {
        this.aux = aux_local;
        this.frame = frame;

        if(aux_local instanceof monitorEvent)
        {
            monitorEvent e = (monitorEvent)aux_local;
            if(e.getType().compareTo("Local") == 0)
            {
                this.aux = e.getAuxPoint();    
            }
            else
            {
                this.aux = new auxiliary();
            }
        }   

        list_var = aux.getVariables();     

        jsp_parent = new javax.swing.JPanel();
        jsp_parent.setLayout(new java.awt.GridBagLayout());
        
        dummy = new javax.swing.JPanel();
        dummy.setMinimumSize(new java.awt.Dimension(1,1));
        
        setTitle("Create an Arithmetic Operation");
        jsp = new javax.swing.JScrollPane();
        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        viewport = new javax.swing.JViewport();
        viewport.setView(jp);
        jsp.setViewport(viewport);
        
        // the label(s)
        equal = "=";
        
        ret_val = new ArithmeticOperation();  
        
        NO = new NOPanel(frame, this, aux); 
        
        variable = new javax.swing.JComboBox();              
        
        selop1 = new javax.swing.JComboBox();
        selop1.addItem("Variable");
        selop1.setBackground(new java.awt.Color(255, 255, 190));
          
        

        // fill in the global variables' drop down menu
        java.util.Vector<variable> vv = var_list.Instance().getVariables();
        for(int i=1; i<vv.size(); i++)
        {
            variable tmp_v = vv.get(i);
            if(tmp_v != null)
            {
                if(tmp_v.getNumId() > 3)   // the sensors being the first three global variables cannot have their values manually assigned
                {
                    variable.addItem(tmp_v.getName());                 
                } 
            }
        }
        
        // now add the local variables
        for(int i=1; i<list_var.size(); i++)
        {
            variable tmp_v = list_var.get(i);
            if(tmp_v != null)
            {
                variable.addItem(tmp_v.getName());       
            }         
        }                
        
        
        var_button = new javax.swing.JButton((String)(variable.getSelectedItem()));
        
        // adding the titles first
        jp_top = new javax.swing.JPanel();
        jp_top.setLayout(new java.awt.GridBagLayout());
               
        // first block
        jp_block1 = new javax.swing.JPanel();
        jp_block1.setLayout(new java.awt.GridBagLayout());
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp_block1.add(selop1, gridBagConstraints);        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp_block1.add(variable, gridBagConstraints);    
        
        variable.setVisible(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp_block1.add(var_button, gridBagConstraints);         
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp_top.add(jp_block1, gridBagConstraints);
        
        
        // second block
        jp_block2 = new javax.swing.JPanel();
        jp_block2.setLayout(new java.awt.GridBagLayout());
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp_block2.add(new javax.swing.JLabel(equal), gridBagConstraints);        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp_block2.add(new javax.swing.JLabel(equal), gridBagConstraints);    
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp_top.add(jp_block2, gridBagConstraints);    
        
   
        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp.add(jp_top, gridBagConstraints);


        // set up le buttons
        jp_buttons = new javax.swing.JPanel();
        jp_buttons.setLayout(new java.awt.GridBagLayout());        
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
        jp_buttons.add(button, gridBagConstraints);
        
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
        jp_buttons.add(button, gridBagConstraints);
 
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp.add(jp_buttons, gridBagConstraints);
        
        // for resizing purposes
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jp.add(dummy, gridBagConstraints);
        
        // set the scroller into the jsp_parent
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jsp_parent.add(jsp, gridBagConstraints);        
        
        setContentPane(jsp_parent);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent we) {
                /*
                 * Instead of directly closing the window,
                 * we're going to change the JOptionPane's
                 * value property.
                 */
                   ret_val = null;
                   clearAndHide(); 
            }
        });
        
         //Ensure the combo box always gets the first focus.
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent ce) {
                //variable.requestFocusInWindow();
            }
        });    
                
        var_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelect(evt, 1);
            }
        });
        variable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeOperAction(evt, 1);
            }
        });

        if(ArithOp != null)
        {
            // set the variables
            variable v = (variable)ArithOp.getVariable();
            String name = v.getName();
                    
            variable.setSelectedItem(name);            
            var_button.setText((String)(variable.getSelectedItem()));
            // set the operands
            Operand tmp1 = ArithOp.getOperand1();
            NO = new NOPanel(frame, tmp1, this, aux);  
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
            jp_top.add(NO, gridBagConstraints);
        }        
        else
        {
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
            jp_top.add(NO, gridBagConstraints);            
        }
        resetViewport();
        setLocationRelativeTo(frame);
    }
    
    // to set up the variable
    private void buttonMouseClicked(java.awt.event.MouseEvent e)
    {
        ArithmeticOperation AO = new ArithmeticOperation();
                
        String tmps = (String)variable.getSelectedItem();
        variable tmp_var = var_list.Instance().getVariable(tmps);
            
        if(tmp_var == null)
        {
            tmp_var = aux.getVariable(tmps);
        }
        AO.setVariable(tmp_var); 
        
        Operand res = NO.getOperand();
        if(res != null)
        {
            AO.setOperand1(NO.getOperand());    
            clearAndHide();
            ret_val = AO;
        }        
    }
 
    // to cancel the arithmetic operation
    private void cancelMouseClicked(java.awt.event.MouseEvent e)
    {
        ret_val = null;
        clearAndHide();
    }    
    
    /**
     * Return the user input
     * @return User input
     */
    public ArithmeticOperation getUserInput() {
        return ret_val;
    }

    /**
     * To maintain the dialog's maximum size
     */
    public void maintainSize()
    {
        jp.validate();
        jp.revalidate();
        jsp.validate();

        int maxX = (int)dummy.getLocation().getX()+50;
        int maxY = (int)dummy.getLocation().getY()+50; // vertically this window never gets bigger
        
        pack();
        if(maxX >= 1000)
        {
            maxX = 1000;
        }
        
        jsp.setMinimumSize(new java.awt.Dimension(maxX, maxY));
        jsp.setPreferredSize(new java.awt.Dimension(maxX, maxY));
        jsp.setSize(new java.awt.Dimension(maxX, maxY)); 
        
        jsp_parent.repaint();
        jsp_parent.revalidate();
        pack();
    }
    
    /**
     * This method reacts to state changes in the combo box containing the types of an operand.
     * @param type The type of the operand
     * @param e ActionEvent
     */
    public void typeOperAction(java.awt.event.ActionEvent e, int type) 
    {
        if(type == 1)
        {
            variable.setVisible(false);
            var_button.setVisible(true);
            var_button.setText((String)(variable.getSelectedItem()));
        }
    }           
        
    /**
     * This method reacts to state changes in the variable button.
     * @param type The type of the operand
     * @param e ActionEvent
     */
    public void buttonSelect(java.awt.event.ActionEvent e, int type)
    {
        if(type == 1)
        {
            var_button.setVisible(false);
            variable.setVisible(true);
        }        
    }
    
    
    /**
     * For the nested operations panels, returns the viewport of the dialog's scrollpane
     * @return The viewport
     */
    public javax.swing.JViewport getViewport()
    {
        return viewport;
    }
    
    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        setVisible(false);
    }
    
    /**
     * Returns the old viewpoint of the viewports
     */
    public void resetViewport()
    {
        java.awt.Point ret = viewport.getViewPosition();
        pack();
        maintainSize();
        viewport.setViewPosition(new java.awt.Point((int)(ret.getX()+120), (int)(ret.getY()+80)));
        repaint();
    }
    
}
