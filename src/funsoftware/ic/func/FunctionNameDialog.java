/*
 * FunctionNameDialog.java
 *
 * Created on 30 December 2005, 16:52
 *
 */

package funsoftware.ic.func;

import funsoftware.consts.*;
import funsoftware.struct.*;
import funsoftware.var.*;

/**
 * This class acts as a pop up window prompting the user for a name and arguments for the newly-to-be introduced function.
 * The pop up window also asks for a level to be set on the function.
 * This class also ensures that the inserted name is unique and the variables indicated as arguments exist and are valid.
 * @author Thomas Legowo
 */
public class FunctionNameDialog extends javax.swing.JDialog{
    
    // private variables
    private javax.swing.JTextField functionName;
    private javax.swing.JTextField functionLevel;
        
    java.awt.GridBagConstraints gridBagConstraints;

    private javax.swing.JPanel jp;
    private javax.swing.JPanel jp_top;
    private javax.swing.JPanel arguments;
    private javax.swing.JPanel middle;
    private javax.swing.JPanel jp_bottom;
    private javax.swing.JLabel jl;    
    private javax.swing.JButton button;  
    
    
    private String msg_top;
    private String guide_top;    
    
    private String msg_middle;  // for the function levels
    private String guide_middle;  
    
    // return values
    private String[] ret_val;   
    private local_var_list arg_list;
    private String input;
    
    /**
     * Creates a new instance of FunctionNameDialog
     * @param frame The frame
     * @param input1 The name of the function
     * @param type Whether to set or edit the function
     */
    public FunctionNameDialog(java.awt.Frame frame, String input1, int type) {
        super(frame, true);
        if(type == 0)
        {
            setTitle("Set Function Name and Arguments");
        }
        else
        {
            setTitle("Edit Function Name");
        }
        
        arg_list = new local_var_list();
        ret_val = new String[2];
        input = input1;
        
        functionName = new javax.swing.JTextField(input1, 10);    
        functionLevel = new javax.swing.JTextField("", 10);  
        
        msg_top = "Name";
        guide_top = "At least 1 character";    
                
        msg_middle = "Level";
        guide_middle = "Values (minimum = 1)";    
                
        arguments = new javax.swing.JPanel();
        arguments.setLayout(new javax.swing.BoxLayout(arguments, javax.swing.BoxLayout.Y_AXIS));
        
        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());    
        jp_top = new javax.swing.JPanel();
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
        jp_top.add(functionName, gridBagConstraints);
        
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
                 
        if(type == 0)
        {
            jp_top = new javax.swing.JPanel();                
            jp_top.setLayout(new java.awt.GridBagLayout());
        
            jl = new javax.swing.JLabel(msg_middle);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp_top.add(jl, gridBagConstraints);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 8, 5, 5);
            jp_top.add(functionLevel, gridBagConstraints);

            jl = new javax.swing.JLabel(guide_middle);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp_top.add(jl, gridBagConstraints);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp.add(jp_top, gridBagConstraints);            
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp.add(arguments, gridBagConstraints);             
        }
        else
        {
            // for the sake of it to get rid of function level changes
            functionLevel.setText("1");
        }
        
        jp_bottom = new javax.swing.JPanel();
        jp_bottom.setLayout(new java.awt.GridBagLayout());
        
        if(type == 0)
        {
            middle = new javax.swing.JPanel();
            middle.setLayout(new java.awt.GridBagLayout());    
            button = new javax.swing.JButton("Add Argument");
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    addMouseClicked(evt);
                }
            });       
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            middle.add(button, gridBagConstraints);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp.add(middle, gridBagConstraints);            
        }
        

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
        gridBagConstraints.gridy = 4;
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
            }
        });
        
         //Ensure the text field always gets the first focus.
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent ce) {
                functionName.requestFocusInWindow();
            }
        });    
    }    
    
    // to handle for mouse click
    private void buttonMouseClicked(java.awt.event.MouseEvent e)
    {
        // clear the arguments textfields first
        int size = arguments.getComponentCount();
        int check=0;
        for(int i=0; i<size; i++)
        {
            javax.swing.JPanel tmp = (javax.swing.JPanel)arguments.getComponent(i);
            javax.swing.JTextField jtf = (javax.swing.JTextField)tmp.getComponent(0);
            jtf.setBackground(new java.awt.Color(255, 255, 255));
        }    
            
        // check the validity for the input name first
        if(functionName.getText().compareTo(input) == 0 && functionName.getText().compareTo("") != 0)  // must be valid already
        {
            checkArguments();
        }
        else
        {
            String tmp = functionName.getText();
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
                    checkArguments();
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
 
    // called after the function name is cleared of any invalid input
    private void checkArguments()
    {
        // now check the validity of the argument names
        // NOTE --> All arguments are local variables so their name must not clash with existing local variables within
        // the function
        
        int size = arguments.getComponentCount();
        String to_check;
        int check=0;        // not zero means error encountered

        ret_val[0] = functionName.getText();
        try
        {
            int level = Integer.parseInt(functionLevel.getText());

            if(level < 1)
            {
                javax.swing.JOptionPane.showMessageDialog(null,
                    "Input value for function level is invalid.\nPlease enter numbers greater than one only.\nPlease Try Again.",
                    "Input Invalid",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                functionLevel.requestFocusInWindow();   
                check = 1;
            }
            else
            {
                ret_val[1] = functionLevel.getText();
                check = 0;                   
            }
        }
        catch(RuntimeException re)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Input value for function level is invalid.\nPlease enter numbers greater than one only.\nPlease Try Again.",
                "Input Invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            functionLevel.requestFocusInWindow();
            check = 1;
        }  

        if(check == 0)
        {
            for(int i=0; i<size; i++)
            {
                javax.swing.JPanel tmp = (javax.swing.JPanel)arguments.getComponent(i);
                javax.swing.JTextField jtf = (javax.swing.JTextField)tmp.getComponent(0);
                to_check = jtf.getText();
                if(to_check.compareTo("") == 0)
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                    "Please input at least 1 character.",
                    "Input Invalid", javax.swing.JOptionPane.WARNING_MESSAGE); 
                        jtf.requestFocusInWindow();
                        jtf.setBackground(new java.awt.Color(255, 190, 190));
                        check = 2;
                    break;
                }
                else
                {
                    // should needed, the following is to check the name validity

                    // add to the vector
                    check = arg_list.checkName(to_check, 0);
                    if(check == 0)
                    {
                        variable newv = new variable();
                        newv.setName(to_check);
                        newv.setValue(0);
                        newv.setType(2);
                        arg_list.addVariable(newv);                    
                        continue;
                    }
                    else if(check == 1)
                    {
                        javax.swing.JOptionPane.showMessageDialog(null,
                            "Input value is invalid.\nThe name has been used.\nPlease Try Again.",
                            "Input Invalid", javax.swing.JOptionPane.WARNING_MESSAGE);
                        jtf.requestFocusInWindow();
                        jtf.setBackground(new java.awt.Color(255, 190, 190));
                        arg_list = new local_var_list();
                        break;
                    }
                    else if(check == 2)
                    {
                        javax.swing.JOptionPane.showMessageDialog(null,
                            "Input value is invalid.\nPlease enter string and numbers only.\nFirst character has to be a letter.\nPlease Try Again.",
                            "Input Invalid", javax.swing.JOptionPane.WARNING_MESSAGE);  
                        jtf.requestFocusInWindow();
                        jtf.setBackground(new java.awt.Color(255, 190, 190));
                        arg_list = new local_var_list();
                        break;
                    }                
                }
                
            }  
            if(check == 0)
            {  
                clearAndHide();  
            }
        }
    }
    
    /**
     * To add more arguments
     */
    private void addMouseClicked(java.awt.event.MouseEvent e)
    {
        javax.swing.JPanel tmp = new javax.swing.JPanel();
        java.awt.GridBagConstraints gridBagConstraints;        
        tmp.setLayout(new java.awt.GridBagLayout());
        
        javax.swing.JTextField jtf = new javax.swing.JTextField("", 10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        tmp.add(jtf, gridBagConstraints);
        
        javax.swing.JLabel jl = new javax.swing.JLabel("Variable Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        tmp.add(jl, gridBagConstraints);     
        
        javax.swing.JButton jb = new javax.swing.JButton("Delete Argument");
        final javax.swing.JPanel tmp_jp = tmp; 
        jb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delArgMouseClicked(evt, tmp_jp);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        tmp.add(jb, gridBagConstraints);  
        
        arguments.add(tmp);
        pack();
        repaint();
    }    

    /**
     * To handle for the delete argument mouse button clicked
     */
    private void delArgMouseClicked(java.awt.event.MouseEvent e, javax.swing.JPanel jp)
    {
        arguments.remove(jp);
        pack();
        repaint();
    }    
    
    /**
     * To handle for the cancel mouse button clicked
     */
    private void cancelMouseClicked(java.awt.event.MouseEvent e)
    {
        ret_val = null;
        functionName.setText(null);
        setVisible(false);
    }
    
    /**
     * Return the user input of function name
     * @return User input
     */
    public String[] getUserInput() {
        return ret_val;
    }
    
    /**
     * Return the user input of the function's arguments
     * @return Arguments
     */
    public local_var_list getArgumentsInput() {
        return arg_list;
    }    

    
    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        functionName.setText(null);
        setVisible(false);
    }    
}
