/*
 * icons_list.java
 *
 * Created on 14 July 2005, 14:59
 */

package funsoftware.consts;

import funsoftware.ic.*;

/**
 * This class manages the list of all icons in the programming window of fUNSoftWare
 * This class adopts the recycling id mechanism for all of the icons it governs
 * @author Thomas Legowo 
 * 
 */
public class icons_list {
    
    // The list_of_available_id uses technique similar to a hash map
    // BUT IT RECYCLES THE ID
    // i.e. the element's id corresponds to its location in the vector for quick access
    private java.util.Vector<Icon> list_of_icons;
    private java.util.Vector<Integer> list_of_available_id;
    
    // for singleton design pattern
    private static icons_list instance = null;
    
    /** Creates a new instance of icons_list */
    protected icons_list() {
        list_of_icons = new java.util.Vector<Icon>();
        list_of_icons.setSize(1);
        list_of_available_id = new java.util.Vector<Integer>();
    }
    
    /**
     * To ensure only one instance of the icon list is used, use singleton design pattern
     * @return Instance of this icon list
     */
    public static icons_list Instance()
    {
        if(instance == null)
        {
            instance = new icons_list();
        }
        return instance;
    }
    
    /**
     * Adds an icon to the list
     * @param icon New icon
     */
    public void addIcon(Icon icon)
    { 
        int id = getFirstAvailableId();
        String tmp_id = Integer.toString(id);
        String tmp_id2 = "I";
        tmp_id = tmp_id2.concat(tmp_id);
        icon.setId(tmp_id);
        
        if(id < list_of_icons.size())
        {
            list_of_icons.setElementAt(icon, id);
        }
        else
        {
            list_of_icons.add(id, icon);
        }
    }
    
    /**
     * Deletes an icon, no need to decrease the size of the vector by 1 since the id is reusable
     * @param icon Icon to be deleted
     */
    public void delIcon(Icon icon)
    {
        list_of_icons.setElementAt(null, icon.getId());        
        list_of_available_id.addElement(icon.getId());
    }
    

    /**
     * Returns the first available id to be recycled, an id will never equal to zero.
     * If there is no id available to be reused, this function will return the size of the list.
     * @return First available id
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
            id = list_of_icons.size();
        }
        return id;
    }
    
    /**
     * Get the icons of the list 
     * @return Set of icons
     */
    public java.util.Vector<Icon> getIcons()
    {
        return list_of_icons;
    }
    
    /**
     * FOR DEBUGGING PURPOSES -- Get set of avaiable recycled ids
     * @return Set of ids
     */
    public java.util.Vector<Integer> getRId()
    {
        return list_of_available_id;
    }
    
    /**
     * Resets its list of icons
     */
    public void resetIconList()
    {
        list_of_icons = new java.util.Vector<Icon>();
        list_of_icons.setSize(1);
        list_of_available_id = new java.util.Vector<Integer>();
    }    
}
