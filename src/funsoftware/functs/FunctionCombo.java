/*
 * FunctionCombo.java
 *
 * Created on 3 October 2005, 23:30
 *
 */

package funsoftware.functs;

/**
 * This class handles the jComboBox that displays available functions that can be reused
 * @author Thomas Legowo
 */
public class FunctionCombo extends javax.swing.JComboBox{
    
   // for singleton design pattern
    private static FunctionCombo instance = null;
    
    /** Creates a new instance of FunctionCombo */
    protected FunctionCombo() {
        setPreferredSize(new java.awt.Dimension(200, 20));
        ComboBoxRenderer renderer= new ComboBoxRenderer();
        renderer.setPreferredSize(new java.awt.Dimension(200, 20));
        this.setRenderer(renderer);
        setMaximumRowCount(5); 
        addItem("  ");
    }
    
    /**
     * To ensure only one instance of this combo box is used and to provide global access to this class, use singleton design pattern
     * @return Instance of this function combo box
     */
    public static FunctionCombo Instance()
    {
        if(instance == null)
        {
            instance = new FunctionCombo();
        }
        return instance;
    }
    
    /**
     * To reset this function combo
     */
    public void reset()
    {
        int total = getItemCount(); 
        if(total > 1)
        {
            for(int i=1; i<total; i++)
            {
                removeItemAt(1);
            }
        }
    }
}
