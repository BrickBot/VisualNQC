/*
 * GridDialog.java
 *
 * Created on 20 September 2005, 21:24
 *
 */

package funsoftware.gri;

/**
 * This dialog class caters for the dialog window that prompts the user for the size of grid segment's width and height
 * @author Thomas Legowo
 */
public class GridDialog extends javax.swing.JDialog implements java.awt.event.ActionListener,
                                                    java.beans.PropertyChangeListener{
    // private variables
    private javax.swing.JTextField gridWidth;
    private javax.swing.JTextField gridHeight;    
    
    private javax.swing.JPanel jp_top;
    private javax.swing.JPanel jp_bottom;
    private javax.swing.JLabel jl;
    java.awt.GridBagConstraints gridBagConstraints;
    
    private String msg_top;
    private String msg_bottom;
    private String guide_top;
    private String guide_bottom;
    
    private String button1;
    private String button2;
    private String button3;
    
    private javax.swing.JOptionPane optionPane;
    
    // the current distanceX and distanceY
    private int distanceX;
    private int distanceY;
    
    // return values
    private int[] ret_vals; 
    
    /**
     * Creates a new instance of GridDialog
     * @param frame The fUNSoftWare window
     * @param input1 Current horizontal distance
     * @param input2 Current vertical distance
     */
    public GridDialog(java.awt.Frame frame, int input1, int input2) {
        super(frame, true);
        setTitle("Set Grid Sizes");

        // default values
        distanceX = 50;
        distanceY = 55;
        
        ret_vals = new int[2];   // width and height respectively
        ret_vals[0] = input1;
        ret_vals[1] = input2;
        
        gridWidth = new javax.swing.JTextField(Integer.toString(input1), 8);
        gridHeight = new javax.swing.JTextField(Integer.toString(input2), 8);
        
        msg_top = "Grid Width";
        msg_bottom = "Grid Height";
        guide_top = "Min 44";
        guide_bottom = "Min 50";        
                
        button1 = "Enter";
        button2 = "Cancel";
        button3 = "Defaults";
        
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
        jp_top.add(gridWidth, gridBagConstraints);
        
        jl = new javax.swing.JLabel(guide_top);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(jl, gridBagConstraints);
        
        jp_bottom = new javax.swing.JPanel();
        jp_bottom.setLayout(new java.awt.GridBagLayout());
        jl = new javax.swing.JLabel(msg_bottom);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_bottom.add(jl, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_bottom.add(gridHeight, gridBagConstraints);
        
        jl = new javax.swing.JLabel(guide_bottom);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_bottom.add(jl, gridBagConstraints);
        
        Object[] array = {jp_top, jp_bottom};
        Object[] options = {button1, button2, button3};

        optionPane = new javax.swing.JOptionPane(array,
                                    javax.swing.JOptionPane.QUESTION_MESSAGE,
                                    javax.swing.JOptionPane.OK_OPTION,
                                    null,
                                    options,
                                    options[0]);

        setContentPane(optionPane);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent we) {
                /*
                 * Instead of directly closing the window,
                 * we're going to change the JOptionPane's
                 * value property.
                 */
                    optionPane.setValue(new Integer(
                                        javax.swing.JOptionPane.CLOSED_OPTION));
            }
        });
        
         //Ensure the text field always gets the first focus.
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent ce) {
                gridWidth.requestFocusInWindow();
            }
        });

        // Register an event handler that puts the text into the option pane.
        gridWidth.addActionListener(this);
        gridHeight.addActionListener(this);

        // Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
    }
    
    
    /**
     * This method handles events for the text field.
     * @param e ActionEvent
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
        optionPane.setValue(button1);
    }

    /**
     * This method reacts to state changes in the option pane.
     * @param e PropertyChangeEvent
     */
    public void propertyChange(java.beans.PropertyChangeEvent e) {
        String prop = e.getPropertyName();

        if (isVisible()
         && (e.getSource() == optionPane)
         && (javax.swing.JOptionPane.VALUE_PROPERTY.equals(prop) ||
             javax.swing.JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) 
        {
            Object value = optionPane.getValue();

            if (value == javax.swing.JOptionPane.UNINITIALIZED_VALUE) 
            {
                //ignore reset
                return;
            }
            //Reset the JOptionPane's value.
            //If not done, then if the user
            //presses the same button next time, no
            //property change event will be fired.
            optionPane.setValue(javax.swing.JOptionPane.UNINITIALIZED_VALUE);

            if (button1.equals(value)) 
            {
                if(gridWidth.getText().compareTo("") == 0 || gridHeight.getText().compareTo("") == 0)
                {
                    javax.swing.JOptionPane.showMessageDialog(
                                    GridDialog.this,
                                    "Please enter values for both fields,\nclick cancel to close the window",
                                    "Field empty",
                                    javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    int tmp1 = ret_vals[0];
                    int tmp2 = ret_vals[1];
                    ret_vals[0] = Integer.parseInt(gridWidth.getText());
                    ret_vals[1] = Integer.parseInt(gridHeight.getText());
                    // do the checking

                    if (ret_vals[0] >= 44 && ret_vals[1] >= 50) 
                    {
                        // we're done; clear and dismiss the dialog
                        clearAndHide();
                    } 
                    else if(ret_vals[0] < 44)
                    {
                        // input for grid width was invalid
                        gridWidth.selectAll();
                        javax.swing.JOptionPane.showMessageDialog(
                                        GridDialog.this,
                                        "Sorry, minimum value for grid width is 44",
                                        "Try again",
                                        javax.swing.JOptionPane.ERROR_MESSAGE);
                        ret_vals[0] = tmp1;
                        gridWidth.requestFocusInWindow();
                    }
                    else if(ret_vals[1] < 50)
                    {
                        // input for grid height was invalid
                        gridHeight.selectAll();
                        javax.swing.JOptionPane.showMessageDialog(
                                        GridDialog.this,
                                        "Sorry, minimum value for grid height is 50",
                                        "Try again",
                                        javax.swing.JOptionPane.ERROR_MESSAGE);
                        ret_vals[1] = tmp2;
                        gridHeight.requestFocusInWindow();
                    }
                }                
            } 
            else if(button3.equals(value))
            {
                gridWidth.setText(Integer.toString(distanceX));
                gridHeight.setText(Integer.toString(distanceY));
            }
            else 
            { 
                clearAndHide();
            }
        }
    }
    
    /**
     * Return the user input of grid sizes
     * @return User input
     */
    public int[] getUserInput() {
        return ret_vals;
    }

    
    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        gridWidth.setText(null);
        gridHeight.setText(null);
        setVisible(false);
    }
}
