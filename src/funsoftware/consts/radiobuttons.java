/*
 * radiobuttons.java
 *
 * Created on 3 January 2006, 13:03
 *
 */

package funsoftware.consts;

/**
 * This class caters for a group of radio buttons that contains both functions and tasks
 * @author Thomas Legowo
 */
public class radiobuttons {
    
    private javax.swing.ButtonGroup group;
    
    // for singleton design pattern
    private static radiobuttons instance = null;
    
    /** Creates a new instance of radiobuttons */
    protected radiobuttons() {
        group = new javax.swing.ButtonGroup();
    }
    
    /**
     * To ensure only one instance of the radiobuttons group is used, use singleton design pattern
     * @return Instance of this icon list
     */
    public static radiobuttons Instance()
    {
        if(instance == null)
        {
            instance = new radiobuttons();
        }
        return instance;
    }
    
    /**
     * To reset the button group
     */
    public void reset()
    {
        group = new javax.swing.ButtonGroup();
    }
    
    /**
     * To return the radio button group managed by an instance of this class
     * @return The button group
     */
    public javax.swing.ButtonGroup getButtonGroup()
    {
        return group;
    }
}
