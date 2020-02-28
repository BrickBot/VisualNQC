/*
 * event_number_tracker.java
 *
 * Created on 2 February 2006, 11:53
 *
 */

package funsoftware.consts;

import funsoftware.struct.*;
import funsoftware.events.*;

/**
 * This list keeps track of all events and make sure that there are only 16 events at a time and theirs ids are 
 * unique and are between 0 and 15
 * @author Thomas Legowo
 */
public class event_number_tracker {
    
    // BUT IT RECYCLES THE ID
    // i.e. the element's id corresponds to its location in the vector for quick access
    private java.util.Vector<Event> list_of_events;
    private java.util.Vector<Integer> list_of_available_id;
    
    // for singleton design pattern
    private static event_number_tracker instance = null;
    
    /** Creates a new instance of event_number_tracker */
    protected event_number_tracker() {
        list_of_events = new java.util.Vector<Event>();
        list_of_events.setSize(1);
        list_of_available_id = new java.util.Vector<Integer>();
    }
    
    /**
     * To ensure only one instance of this class is used, use singleton design pattern
     * @return Instance of this list
     */
    public static event_number_tracker Instance()
    {
        if(instance == null)
        {
            instance = new event_number_tracker();
        }
        return instance;
    }
    
    /**
     * Adds an event
     * @param ev The event to be added
     * @return Whether this process is successful
     */
    public int addEvent(Event ev)
    {
        int ret_val = 0;  // could be added, have not exceeded 16 events yet
        int id = getFirstAvailableId();

        if(id == 0)  // this list is full, no more events are allowed
        {
            return 1;  // no addition allowed
        }
        
        ev.setEventId(id);
        
        if(id < list_of_events.size())
        {
            list_of_events.setElementAt(ev, id);
        }
        else
        {
            list_of_events.add(id, ev);
        }
        
        return ret_val;
    }
    
    /**
     * Deletes a Event, no need to decrease the size of the vector by 1 since the id is reusable
     * @param num_id Event to be deleted
     */
    public void delEvent(int num_id)
    {
        list_of_events.setElementAt(null, num_id);
        list_of_available_id.addElement(num_id);
    }
    
    /**
     * Returns the first available id to be recycled, an id will never equal to zero.
     * If there is no id available to be reused, this function will return the size of the list
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
            id = list_of_events.size();
        }
        
        if(id > 16)   // the guard that ensures a maximum of 16 events used
        {
            return 0;
        }
        return id;
    }
    
    /**
     * Gets the list of events
     * @return Set of events structures
     */
    public java.util.Vector<Event> getEvents()
    {
        return list_of_events;
    }
    
    /**
     * Resets this class
     */
    public void resetEvList()
    {
        list_of_events = new java.util.Vector<Event>();
        list_of_events.setSize(1);
        list_of_available_id = new java.util.Vector<Integer>();
    }
    
    /**
     * Returns 1 if the name is not unique, 0 otherwise, 2 if does not conform to rules of allowed characters
     * @param name The name to be check
     * @return Indicates the outcome of this process
     */
    public int checkName(String name)
    {
        int maxIndex = list_of_events.size();
        for(int i=1; i<maxIndex; i++)
        {
            Event tmp_ev = list_of_events.get(i);
            if(tmp_ev != null && tmp_ev.getName().compareTo(name) == 0)
            {
                return 1;
            }
        }
        // after the name is valid, it has to conform to rules such as only _, letters and numbers
        // do a loop that validates every single character of the entered name
        int length = name.length();
        int index = 0;  // checks from left to right

        while(index < length)
        {
            char c= name.charAt(index);   // first character has to be an alphabet (letter)
            if((index == 0 && ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)))
               || (index > 0 && ((c >=48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122) || c == 95)))
            {                                
                index++;
                continue;
            }
            else
            {
                return 2;
            }                            
        }
        return 0;
    }
    
    /**
     * For the reverse translation process
     * @param name The name of the event to be retrieved
     * @return The event
     */
    public Event getEvent(String name)
    {
        Event tmp = null;
        for(int i=1; i<list_of_events.size(); i++)
        {
            tmp = list_of_events.get(i);
            if(tmp != null)
            {
                if(tmp.getName().compareTo(name) == 0)
                {
                    return tmp;
                }                
            }
        }
        tmp = null;
        return tmp;
    }
    
    /**
     * To decide whether or not this list is empty
     * @return True or false
     */
    public Boolean isEmpty()
    {
        Event tmp = null;
        for(int i=1; i<list_of_events.size(); i++)
        {
            tmp = list_of_events.get(i);
            if(tmp != null)
            {
                return false;              
            }
        }
        return true;
    }
}
