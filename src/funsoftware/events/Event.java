/*
 * Event.java
 *
 * Created on 20 January 2006, 18:17
 *
 */

package funsoftware.events;

import funsoftware.ic.*;
import funsoftware.consts.*;
import funsoftware.ic.var.*;
import funsoftware.st.*;
import funsoftware.ic.ev.*;
import funsoftware.struct.*;
import funsoftware.var.*;

/**
 * This class manages an event monitoring. It contains a sequence of icons that are to be executed while monitoring an event.
 * @author Thomas Legowo
 */
public class Event{
    
    private String name;   // name of this event as given by the user
    // the event window
    private eventWindow ev_win;
    
    // for the NQC
    private String source;  // the input this event will monitor on (1, 2 and 3 are possible values for ports, and 0,1,2 and 3 are for timers)
    private String type_of_sensor;
    private String type_of_comparator;
    private int event_number;    // from the event_number_tracker
    private Operand threshold;   // can be a constant or a variable
    
    
    /** Creates a new instance of Event */
    public Event()
    {
        super();
        name = "event";  // default name
    }
        
    /**
     * Alters the event Id for NQC translation
     * @param i New event id
     */
    public void setEventId(int i)
    {
        event_number = i;
    }
    
    /**
     * Returns the event Id for NQC translation, NumId is for event_monitor
     * @return event id
     */
    public int getEventId()
    {
        return event_number;
    }    
    
    /**
     * Sets the major elements of this event
     * @param source The port or timer number
     * @param type_of_sensor Sensor used
     * @param type_of_comparator Comparison process type
     * @param threshold The threshold value in the comparison process
     */    
    public void setEventElements(String source, String type_of_sensor, String type_of_comparator, Operand threshold) 
    {
        this.source = source;
        this.type_of_sensor = type_of_sensor;
        this.type_of_comparator = type_of_comparator;   // null if it is an EVENT_TYPE_PRESSED OR EVENT_TYPE_RELEASED
        this.threshold = threshold;
    }
        
    /**
     * Returns this event's source
     * @return event source
     */    
    public String getSource()
    {
        return source;
    }    
    
    /**
     * Returns this event's threshold
     * @return event threshold
     */    
    public Operand getThreshold()
    {
        return threshold;
    }    
    
    /**
     * Returns this event's source type (type of sensor used)
     * @return event source type
     */    
    public String getEventSource()
    {
        return type_of_sensor;
    }    
    
    /**
     * Returns this event's type of comparison (pressed, touched, low or high)
     * @return event type of comparison
     */    
    public String getEventType()
    {
        return type_of_comparator;
    }      
    
    /**
     * Sets the name of this event
     * @param n New event name 
     */
    public void setName(String n)
    {
        name = n;
    }
    /**
     * Returns this event icon's name
     * @return event name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Get the help title of this event
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Event";
        return s;
    }
    
    /**
     * Get help message of this event
     * @return Help message
     */    
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nName: ";
        s+=name;
        s+="\nSource: ";
        s+=source;
        s+="\nEvent-Source: ";
        s+=type_of_sensor;
        s+="\nEvent-Type: ";
        s+=type_of_comparator;
        if(type_of_sensor.compareTo("SENSOR_TOUCH") != 0)
        {
            s+="\nThreshold: ";
            if(threshold instanceof constant)
            {
                constant c = (constant)threshold;
                s+=c.getValue();
            }
            else if(threshold instanceof variable)
            {
                variable v = (variable)threshold;
                s+=v.getName();
            }
        }
        return s;
    }    
    
    
    /**
     * Algorithm translation for the Translator object to create a text representation of this icon
     * @param id_separator To separate ids
     * @param id_att_separator To separate id with attribute
     * @param att_boundary_begin To start attributes
     * @param att_boundary_end To end attributes
     * @param att_value_connector To separate attributes and its values
     * @param att_separator To separate attributes
     * @return Translation
     */
    public String getTranslation(String id_separator, String id_att_separator, 
                                 String att_boundary_begin, String att_boundary_end, String att_value_connector, String att_separator)
    {
        String algo = new String();
        algo += "\n";  // to begin with
        algo += "event";
        algo += id_separator;
        algo += event_number;        
        algo += id_att_separator;
        algo += att_boundary_begin;
        algo += "name"+att_value_connector;
        algo += name;   
        algo += att_separator;        
        algo += "source"+att_value_connector;
        algo += source; 
        algo += att_separator;        
        algo += "sensortype"+att_value_connector;
        if(type_of_sensor == null)
        {
            algo += ".";  // means it is empty
        }
        else
        {
            algo += type_of_sensor;       
        }       
        algo += att_separator;        
        algo += "sensorcomparator"+att_value_connector;
        if(type_of_comparator == null)
        {
            algo += ".";  // means it is empty
        }
        else
        {
            algo += type_of_comparator;       
        }                     
        algo += att_separator;  
        algo += "threshold"+att_value_connector;
        if(threshold == null)
        {
            algo += ".";  // means it is empty
        }
        else
        {
            if(threshold instanceof constant)
            {
                constant c = (constant)threshold;
                algo += c.getAOTranslation(id_separator, id_att_separator, att_boundary_begin, 
                                           att_boundary_end, att_value_connector, att_separator);
            }
            else if(threshold instanceof variable)
            {
                variable v = (variable)threshold;
                algo += v.getAOTranslation(id_separator, id_att_separator, att_boundary_begin, 
                                           att_boundary_end, att_value_connector, att_separator);                
            }                   
        }         
        algo += att_boundary_end;

        return algo;
    }
    
    /**
     * Returns the event header of this event
     * @return The NQC Code events
     * @param indentation How many indentations
     */
    public String getNQCCode(String indentation)
    {
        // due to the id from the event tracker list to start from zero, for the purpose of translation it will be substracted 1
        String header = new String();
        header += indentation+"// an event set up\n";
        header += indentation+"SetEvent("+(event_number-1)+", "+source+", "+type_of_comparator+");\n";
        if(type_of_sensor.compareTo("SENSOR_TOUCH") != 0)
        {            
            String display_threshold = new String();
            if(threshold instanceof variable)
            {
                variable tmp_v = (variable)threshold;
                display_threshold = tmp_v.getName(); 
            }
            else if(threshold instanceof constant)
            {
                constant tmp_c = (constant)threshold;
                display_threshold = Integer.toString(tmp_c.getValue()); 
            }
            header += indentation+"SetLowerLimit("+(event_number-1)+", "+display_threshold+");\n";
            header += indentation+"SetUpperLimit("+(event_number-1)+", "+display_threshold+");\n";
        }
        return header;
    }
    
    /**
     * To clone this event infrastructure
     * @return New instance of this event
     */
    public Event Clone()
    {
        return new Event();
    }
}
