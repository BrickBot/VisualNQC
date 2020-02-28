/*
 * typeEventsCoordinator.java
 *
 * Created on 2 February 2006, 12:22
 *
 */

package funsoftware.consts;

/**
 * Maintains all event types and modes
 * @author Thomas Legowo
 */
public class typeEventsCoordinator {
    
    /**
     * The sensors as the sources of an event
     */
    public static String[] event_source = {"SENSOR_TOUCH", "SENSOR_LIGHT", "SENSOR_ROTATION", "SENSOR_CELCIUS", "TIMER"};

    /**
     * The types of checking done for an event if the source is the touch sensor
     */
    public static String[] event_type1 = {"EVENT_TYPE_PRESSED", "EVENT_TYPE_RELEASED"};
    
    /**
     * The types of checking done for an event if the source is not the touch sensor
     */
    public static String[] event_type2 = {"EVENT_TYPE_LOW", "EVENT_TYPE_HIGH"};
    
}
