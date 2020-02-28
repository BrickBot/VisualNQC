/*
 * ComparatorDialog.java
 *
 * Created on 18 February 2006, 16:48
 *
 */

package funsoftware.ic;

import funsoftware.ic.bran.*;

/**
 * To display the dialog that asks the user for a comparator for an expression
 * @author Thomas Legowo
 */
public class ComparatorDialog extends javax.swing.JDialog{
    
    // private variables
    private java.awt.GridBagConstraints gridBagConstraints;
    
    private javax.swing.JComboBox comparator;
    
    private javax.swing.JLabel msg1;
    private javax.swing.JLabel msg2;
    private javax.swing.JLabel comparator_display;
    private javax.swing.JLabel guide1;
    
    private javax.swing.JButton button1;
    private javax.swing.JButton button2;
    
    private javax.swing.JPanel jp;
    private javax.swing.JPanel jp_top;
    private javax.swing.JPanel jp_bottom;
    
    private int ret_val;
    
    private String[] array_of_comparator;
    
    /**
     * Creates a new instance of ComparatorDialog
     * @param type The type of whether edit or set
     * @param icon The icon this comparator is in reference to
     * @param frame The frame
     */
    public ComparatorDialog(java.awt.Frame frame, int type, Icon icon) {
        super(frame, true);
        setTitle("Set The Comparator");
        
        ret_val = type;
        
        array_of_comparator = new String[6];
        array_of_comparator[0] = ">";
        array_of_comparator[1] = ">=";
        array_of_comparator[2] = "<";
        array_of_comparator[3] = "<=";
        array_of_comparator[4] = "==";
        array_of_comparator[5] = "!=";
        
        
        comparator = new javax.swing.JComboBox();
        comparator.addItem(array_of_comparator[0]);
        comparator.addItem(array_of_comparator[1]);
        comparator.addItem(array_of_comparator[2]);
        comparator.addItem(array_of_comparator[3]);
        comparator.addItem(array_of_comparator[4]);     
        comparator.addItem(array_of_comparator[5]);  
        
        comparator.setSelectedItem(array_of_comparator[type-1]);
        
        if(icon instanceof branchIcon)
        {
            msg1 = new javax.swing.JLabel("Top Comparator");    
            msg2 = new javax.swing.JLabel("Bottom Comparator");   
            if(type == 1)
            {
                comparator_display = new javax.swing.JLabel(array_of_comparator[3]);    
            }
            else if(type == 2)
            {
                comparator_display = new javax.swing.JLabel(array_of_comparator[2]);   
            }
            else if(type == 3)
            {
                comparator_display = new javax.swing.JLabel(array_of_comparator[1]);   
            }
            else if(type == 4)
            {
                comparator_display = new javax.swing.JLabel(array_of_comparator[0]);   
            }
            else if(type == 5)
            {
                comparator_display = new javax.swing.JLabel(array_of_comparator[5]);   
            }
            else if(type == 6)
            {
                comparator_display = new javax.swing.JLabel(array_of_comparator[4]);   
            }            
        }
        else
        {
            msg1 = new javax.swing.JLabel("Comparator");    
        }
        
        guide1 = new javax.swing.JLabel("Select 1");
        
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
        jp_top.add(msg1, gridBagConstraints);   
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(comparator, gridBagConstraints);  
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp_top.add(guide1, gridBagConstraints);   
        
        if(icon instanceof branchIcon)
        {
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp_top.add(msg2, gridBagConstraints);   

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            jp_top.add(comparator_display, gridBagConstraints);              
        }
                
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(jp_top, gridBagConstraints);         
        
        button1 = new javax.swing.JButton("Select");
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
        
        setContentPane(jp);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        if(icon instanceof branchIcon)
        {
            comparator.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    changeComparator();
                }
            });                 
        }   
    }
    
    // to handle from a change of state in the comparator JComboBox
    private void changeComparator()
    {
        String select = (String)comparator.getSelectedItem();
        
        if(select.compareTo(array_of_comparator[0]) == 0)
        {
            comparator_display.setText(array_of_comparator[3]);    
        }
        else if(select.compareTo(array_of_comparator[1]) == 0)
        {
            comparator_display.setText(array_of_comparator[2]);   
        }
        else if(select.compareTo(array_of_comparator[2]) == 0)
        {
            comparator_display.setText(array_of_comparator[1]);   
        }
        else if(select.compareTo(array_of_comparator[3]) == 0)
        {
            comparator_display.setText(array_of_comparator[0]);   
        }
        else if(select.compareTo(array_of_comparator[4]) == 0)
        {
            comparator_display.setText(array_of_comparator[5]);   
        }
        else if(select.compareTo(array_of_comparator[5]) == 0)
        {
            comparator_display.setText(array_of_comparator[4]);   
        }      
        repaint();
    }
   
    /**
     * To return the comparator type selected
     * @return The comparator selected
     */
    public int getComparator()
    {        
        return ret_val;
    }
  
    // The handler for "Select" buttons
    private void button1MouseClicked()
    {
        String selected = (String)comparator.getSelectedItem();
        
        if(selected.compareTo(">") == 0)
        {
            ret_val = 1;
        }
        else if(selected.compareTo(">=") == 0)
        {
            ret_val = 2;
        }
        else if(selected.compareTo("<") == 0)
        {
            ret_val = 3;
        }
        else if(selected.compareTo("<=") == 0)
        {
            ret_val = 4;
        }
        else if(selected.compareTo("==") == 0)
        {
            ret_val = 5;
        }
        else if(selected.compareTo("!=") == 0)
        {
            ret_val = 6;
        }
        setVisible(false);
    }     
    
    // The handler for "Cancel" buttons
    private void button2MouseClicked()
    {
        setVisible(false);
    }    
}
