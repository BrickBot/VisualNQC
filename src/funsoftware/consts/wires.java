/*
 * wires.java
 *
 * Created on 14 July 2005, 15:15
 */

package funsoftware.consts;

import funsoftware.wr.*;

/**
 * This wires class keeps track of the all wires between icons in fUNSoftWare
 * @author Thomas Legowo
 * 
 */
public class wires {
    
    // The list_of_available_id uses technique similar to a hash map
    // BUT IT RECYCLES THE ID
    // i.e. the element's id corresponds to its location in the vector for quick access
    private java.util.Vector<Wire> list_of_wires;
    private java.util.Vector<Integer> list_of_available_id;
    
    // for singleton design pattern
    private static wires instance = null;
    
    /** Creates a new instance of wires */
    protected wires() {
            list_of_wires = new java.util.Vector<Wire>();
            list_of_wires.setSize(1);
            list_of_available_id = new java.util.Vector<Integer>();
    }
    
    /**
     * Ensures only one instance of the wire list is used, use singleton design pattern
     * @return Instance of this class
     */
    public static wires Instance()
    {
        if(instance == null)
        {
            instance = new wires();
        }
        return instance;
    }
    
    /**
     * Adds a new piece of wire into the wire list
     * @param wire Wire
     */
    public void addWire(Wire wire)
    {
        int id = getFirstAvailableId();
        String tmp_id = Integer.toString(id);
        String tmp_id2 = "W";
        tmp_id = tmp_id2.concat(tmp_id);
        wire.setId(tmp_id);
        if(id < list_of_wires.size())
        {
            list_of_wires.setElementAt(wire, id);
        }
        else
        {
            list_of_wires.add(id, wire);
        }
    }
    
    /**
     * Deletes a piece of wire, no need to decrease the size of the vector by 1 since the id is reusable
     * @param wire Wire to be deleted
     */
    public void delWire(Wire wire)
    {
        list_of_wires.setElementAt(null, wire.getId());
        list_of_available_id.addElement(wire.getId());
    }
    

    /**
     * Returns the first available id to be recycled, an id will never equal to zero.
     * If there is no id available to be reused, this function will return the size of the list.
     * @return Id
     */
    public int getFirstAvailableId()
    {
        int id;
        if(list_of_available_id.size() > 0) // if there are any recycled id
        {
            id = list_of_available_id.get(0); // get the first available one
            list_of_available_id.removeElementAt(0); // remove it of the list
        }
        else
        {
            id = list_of_wires.size();
        }
        return id;
    }
    
    /**
     * Get the wires of the list
     * @return Set of wires
     */
    public java.util.Vector<Wire> getWires()
    {
        return list_of_wires;
    }
    
    /**
     * Resets this class' list of wires
     */
    public void resetWireList()
    {
        list_of_wires = new java.util.Vector<Wire>();
        list_of_wires.setSize(1);
        list_of_available_id = new java.util.Vector<Integer>();
    } 
}
