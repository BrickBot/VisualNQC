/*
 * ComboBoxRenderer.java
 *
 * Created on 4 January 2006, 18:06
 *
 */

package funsoftware.functs;

/**
 * For the function combo box's renderer
 * @author Thomas Legowo
 */
public class ComboBoxRenderer extends javax.swing.JLabel implements javax.swing.ListCellRenderer{
    
    /** Creates a new instance of ComboBoxRenderer */
    public ComboBoxRenderer(){                        
        setOpaque(true);            
        setHorizontalAlignment(CENTER);            
        setVerticalAlignment(CENTER);
    }
    
    /**
     * This method finds the text corresponding
     * to the selected value and returns the label, set up
     * to display the text and image.
     * @param list list
     * @param value value
     * @param index index
     * @param isSelected whether it is selected
     * @param cellHasFocus focus
     * @return The selected component
     */
    public java.awt.Component getListCellRendererComponent(javax.swing.JList list,
                                                           Object value,
                                                           int index,
                                                           boolean isSelected,
                                                           boolean cellHasFocus) 
    {   
        if (isSelected) 
        {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else 
        {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        if(value != null && value instanceof javax.swing.JMenuItem)
        {
            javax.swing.JMenuItem label = (javax.swing.JMenuItem) value;    
            setText(label.getText());
        }
        else
        {
            setText("");
        }
        return this;
    }
}
