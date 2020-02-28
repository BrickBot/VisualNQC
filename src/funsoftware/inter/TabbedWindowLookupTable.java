/*
 * TabbedWindowLookupTable.java
 *
 * Created on 28 September 2005, 11:38
 */

package funsoftware.inter;

/**
 * This class acts as a helper to TabbedWindowManager.
 * It keeps track of corresponding panel's position in the card layout and its panel's function id
 * @author Thomas Legowo
 */
public class TabbedWindowLookupTable {
    
    // for singleton design pattern
    private static TabbedWindowLookupTable instance = null;
    private java.util.Vector<TabbedWindowEntry> twe;
    
    /** Creates a new instance of TabbedWindowLookupTable */
    protected TabbedWindowLookupTable() {
        twe = new java.util.Vector<TabbedWindowEntry>();
    }
    
    /**
     * To ensure only one instance of the tabbedwindowlookuptable is used, use singleton design pattern
     * @return Instance of this class
     */
    public static TabbedWindowLookupTable Instance()
    {
        if(instance == null)
        {
            instance = new TabbedWindowLookupTable();
        }
        return instance;
    }
    
    /**
     * Get a particular entry's position in this class
     * @param num_id Id
     * @return Position
     */
    public int getEntryPosition(int num_id)
    {
        int ret_val=0;  // default
        for(int i=0; i<twe.size(); i++)
        {
            TabbedWindowEntry tmp = twe.get(i);
            if(tmp.getSubId() == num_id)
            {
                return tmp.getPanelPos();
            }
        }
        return ret_val;
    }
    
    
    /**
     * Add an entry
     * @param pos Position
     * @param num_id Id
     */
    public void addEntry(int num_id, int pos)
    {
        twe.addElement(new TabbedWindowEntry(num_id, pos));
    }
    
    /**
     * Delete an entry, need to update the other entries
     * @param num_id Id
     */
    public void delEntry(int num_id)
    {
        int treshold=0;
        for(int i=0; i<twe.size(); i++)
        {
            TabbedWindowEntry tmp = twe.get(i);
            if(tmp.getSubId() == num_id)
            {
                treshold = tmp.getPanelPos();
                twe.removeElementAt(i);
                break;
            }
        }
        // then update the rest
        // keep in mind that the purpose of this is that say there are 3 components, if the second is deleted, then
        // the third component should have index of 2
        for(int i=0; i<twe.size(); i++)
        {
            TabbedWindowEntry tmp = twe.get(i);
            if(tmp.getPanelPos() > treshold)
            {
                tmp.setPanelPos(tmp.getPanelPos()-1);
            }
        }
    }
}
