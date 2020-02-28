/*
 * TabbedWindowEntry.java
 *
 * Created on 28 September 2005, 12:18
 *
 */

package funsoftware.inter;

/**
 * This class simply connect a function id with a panel's position in its parent card layout oriented
 * @author Thomas Legowo
 * 
 * 
 */
public class TabbedWindowEntry {
    
    int func_id;  // function id
    int panel_pos; // panel's position in its parent class
    
    /**
     * Creates a new instance of TabbedWindowEntry
     * @param s Function id
     * @param p Position of panel in its parent panel
     */
    public TabbedWindowEntry(int s, int p) {
        func_id = s; 
        panel_pos = p;
    }
    
    /**
     * Set the entry function's id
     * @param s Function id
     */
    public void setSubId(int s)
    {
        func_id = s;
    }
    
    /**
     * Set the panel position
     * @param p Panel position
     */
    public void setPanelPos(int p)
    {
        panel_pos = p;
    }
    
    /**
     * Get the entry function id
     * @return Function id
     */
    public int getSubId()
    {
        return func_id;
    }
    
    /**
     * Get the panel position
     * @return Panel position
     */
    public int getPanelPos()
    {
        return panel_pos; 
    }
}
