/*
 * Translator.java
 *
 * Created on 14 July 2005, 23:29
 */

package funsoftware.comp;

import funsoftware.ic.*;
import funsoftware.ic.obj.*;
import funsoftware.ic.bran.*;
import funsoftware.ic.loo.*;
import funsoftware.ic.others.*;
import funsoftware.ic.func.*;
import funsoftware.ic.tsk.*;
import funsoftware.ic.ev.*;
import funsoftware.ic.var.*;
import funsoftware.consts.*;
import funsoftware.functs.*;
import funsoftware.tasks.*;
import funsoftware.events.*;
import funsoftware.struct.*;
import funsoftware.var.*;
import funsoftware.properties.*;

/**
 * This class takes in all the information of a created iconic program to create two types of text. 
 * The first one being an algorithm representation and the second one being an NQC code.
 * @author Thomas Legowo
 */
public class Translator {
        
    // the private variables
    private static java.util.Vector<Icon> objects;  // the object icons lookup table
    private static java.util.Vector<Icon> branches; // the branch icons lookup table
    private static java.util.Vector<Icon> loops;    // the loop icons lookup table
    
    // the variables determining the notations for the algorithm translation
    private static String id_separator;         // between the icon's type and its identifier code
    private static String id_att_separator;     // between the identifier and the attributes
    private static String att_boundary_begin;   // encloses the boundary (begin)
    private static String att_boundary_end;     // encloses the boundary (end)
    private static String att_value_connector;  // between the attribute to its value
    private static String att_separator;        // between the attributes
               
    /**
     * Initializes the vectors and notations
     */
    public static void init_lookup_table(){
        // do the object lookup table
        objects = new java.util.Vector<Icon>();
        objects.addElement(new motorIcon());
        objects.addElement(new stopMotorIcon());
        objects.addElement(new directionalMotorIcon());
        objects.addElement(new timerIcon());
        objects.addElement(new beepIcon());
        objects.addElement(new lampIcon());
        objects.addElement(new clearSensorIcon());
        objects.addElement(new clearTimerIcon());
        objects.addElement(new playAnyNoteIcon());
        objects.addElement(new beginTaskIcon());
        objects.addElement(new stopTaskIcon());
        objects.addElement(new ArithOpIcon());
        objects.addElement(new floatMotorIcon());
        objects.addElement(new playSpecificNote());
        objects.addElement(new startEvent());      
        objects.addElement(new returnIcon()); 
        objects.addElement(new waitTouchIcon());           
        objects.addElement(new waitLightIcon());  
        objects.addElement(new waitCelciusIcon()); 
        objects.addElement(new waitRotationalIcon()); 
        objects.addElement(new waitTimerIcon());         
        
        // do the branch lookup table
        branches = new java.util.Vector<Icon>();
        branches.addElement(new BtouchSensorIcon());
        branches.addElement(new BlightSensorIcon());
        branches.addElement(new BcelciusSensorIcon());
        branches.addElement(new BrotationSensorIcon());
        branches.addElement(new BtimerIcon());
        branches.addElement(new BrandomIcon());       
        branches.addElement(new BarithmeticIcon());        
        
        // do the loop lookup table
        loops = new java.util.Vector<Icon>();
        loops.addElement(new repeatIcon());
        loops.addElement(new lightSensorLoopIcon());
        loops.addElement(new infiniteLoopIcon());
        loops.addElement(new timerLoopIcon());
        loops.addElement(new celciusSensorLoopIcon());
        loops.addElement(new rotationSensorLoopIcon());
        loops.addElement(new touchSensorLoopIcon());
        loops.addElement(new arithmeticLoopIcon());        
        
        // initialise the notations
        id_separator = "#";
        id_att_separator = ">>";
        att_boundary_begin = "[";
        att_boundary_end = "]";
        att_value_connector = ":";
        att_separator = ">";
    }
    
    /**
     * Returns the id-attribute separator notation of this class
     * @return Id-attribute separator notation
     */
    public static String getId_Att_Separator()
    {
        return id_att_separator;
    }
    /**
     * Returns the id separator notation of this class
     * @return Id separator notation
     */
    public static String getId_Separator()
    {
        return id_separator;
    }
    
    /**
     * Returns the attribute list begin notation of this class
     * @return Attribute list begin notation
     */
    public static String getAtt_Boundary_Begin()
    {
        return att_boundary_begin;
    }
    /**
     * Returns the attribute list end notation of this class
     * @return Attribute list end notation
     */
    public static String getAtt_Boundary_End()
    {
        return att_boundary_end;
    }
    /**
     * Returns the attribute value separator notation of this class
     * @return Attribute-value separator notation
     */
    public static String getAtt_Value_Connector()
    {
        return att_value_connector;
    }
    /**
     * Returns the attribute separator notation of this class
     * @return Attribute separator notation
     */
    public static String getAtt_Separator()
    {
        return att_separator;
    }
    
    /**
     * Return an icon as specified by its identifier on a text representation as well as setting its attributes
     * @param rep String representation of an icon's attributes
     * @param a The auxiliary this icon resides in
     * @return The formed icon
     */
    public static Icon getIcon(String rep, auxiliary a)
    {
        Icon tmp_icon = new Icon();
        String[] sp = new String[2];   // the identifiers
        String[] sp2 = new String[5];  // an icon's attributes can be up to 5 in total
        String line = new String();
        int pos = 0;
        
        sp2 = rep.split(id_att_separator);
        sp = sp2[0].split(id_separator); 
        pos = Integer.parseInt(sp[1]) - 1; 
        if(sp[0].compareTo("branch") == 0)
        {            
            tmp_icon = branches.get(pos);
            String tmp_string[] = rep.split("\n");  // the temporary string that represent only the branch icon
            sp2 = tmp_string[0].split(id_att_separator);
            sp = sp2[0].split(id_separator);
            
            if(pos == 6)  // SPECIAL for arithmetic operation
            {
                line = tmp_string[0].substring(tmp_string[0].indexOf(att_boundary_begin), tmp_string[0].lastIndexOf(att_boundary_end)+1);
            }
            else
            {
                line = sp2[1];
                line = line.substring(line.indexOf(att_boundary_begin)+1, line.indexOf(att_boundary_end));
            }
            
            if(pos == 0)
            {
                BtouchSensorIcon ic = ((BtouchSensorIcon)tmp_icon).Clone();
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }
            else if(pos == 1)
            {
                BlightSensorIcon ic = ((BlightSensorIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }
            else if(pos == 2)
            {
                BcelciusSensorIcon ic = ((BcelciusSensorIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }
            else if(pos == 3)
            {
                BrotationSensorIcon ic = ((BrotationSensorIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }
            else if(pos == 4)
            {
                BtimerIcon ic = ((BtimerIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }
            else if(pos == 5)
            {
                BrandomIcon ic = ((BrandomIcon)tmp_icon).Clone();
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }   
            else if(pos == 6)
            {
                BarithmeticIcon ic = ((BarithmeticIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator, id_att_separator);   // special !!
                ic.setImage();
                return ic;
            }            
        }
        else if(sp[0].compareTo("loop") == 0)
        {            
            tmp_icon = loops.get(pos);
            String tmp_string[] = rep.split("\n");  // the temporary string that represent only the loop icon
            sp2 = tmp_string[0].split(id_att_separator);
            sp = sp2[0].split(id_separator);
            
            if(pos == 7)  // SPECIAL for arithmetic operation
            {
                line = tmp_string[0].substring(tmp_string[0].indexOf(att_boundary_begin), tmp_string[0].lastIndexOf(att_boundary_end)+1);
            }
            else
            {
                line = sp2[1];
                line = line.substring(line.indexOf(att_boundary_begin)+1, line.indexOf(att_boundary_end));
            }
            
            if(pos == 0)
            {
                repeatIcon ic = ((repeatIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }
            else if(pos == 1)
            {
                lightSensorLoopIcon ic = ((lightSensorLoopIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }    
            else if(pos == 2)
            {
                infiniteLoopIcon ic = ((infiniteLoopIcon)tmp_icon).Clone();
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }
            else if(pos == 3)
            {
                timerLoopIcon ic = ((timerLoopIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }
            else if(pos == 4)
            {
                celciusSensorLoopIcon ic = ((celciusSensorLoopIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }
            else if(pos == 5)
            {
                rotationSensorLoopIcon ic = ((rotationSensorLoopIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }
            else if(pos == 6)
            {
                touchSensorLoopIcon ic = ((touchSensorLoopIcon)tmp_icon).Clone();
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }      
            else if(pos == 7)
            {
                arithmeticLoopIcon ic = ((arithmeticLoopIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator, id_att_separator);   // special !!
                ic.setImage();
                return ic;
            }            
        }
        else if(sp[0].compareTo("object") == 0)
        {
            tmp_icon = objects.get(pos);
            if(pos == 11)  // SPECIAL for arithmetic operation
            {
                line = rep.substring(rep.indexOf(att_boundary_begin), rep.lastIndexOf(att_boundary_end)+1);
            }
            else
            {
                line = sp2[1];
                line = line.substring(line.indexOf(att_boundary_begin)+1, line.indexOf(att_boundary_end));
            }
            
            if(pos == 0)
            {
                motorIcon ic = ((motorIcon)tmp_icon).Clone();         
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }
            else if(pos == 1)
            {
                stopMotorIcon ic = ((stopMotorIcon)tmp_icon).Clone();
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }
            else if(pos == 2)
            {
                directionalMotorIcon ic = ((directionalMotorIcon)tmp_icon).Clone();
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }
            else if(pos == 3)
            {
                timerIcon ic = ((timerIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;
            }
            else if(pos == 4)
            {
                beepIcon ic = ((beepIcon)tmp_icon).Clone();
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;    
            }
            else if(pos == 5)
            {
                lampIcon ic = ((lampIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;                
            }
            else if(pos == 6)
            {
                clearSensorIcon ic = ((clearSensorIcon)tmp_icon).Clone();
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;                
            }
            else if(pos == 7)
            {
                clearTimerIcon ic = ((clearTimerIcon)tmp_icon).Clone();
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;                
            }
            else if(pos == 8)
            {
                playAnyNoteIcon ic = ((playAnyNoteIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;                
            }
            else if(pos == 9)
            {
                beginTaskIcon ic = ((beginTaskIcon)tmp_icon).Clone();
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;                
            }
            else if(pos == 10)
            {
                stopTaskIcon ic = ((stopTaskIcon)tmp_icon).Clone();
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;                
            }     
            else if(pos == 11)
            {
                ArithOpIcon ic = ((ArithOpIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator, id_att_separator);   // special !!
                ic.setImage();
                return ic;                
            }   
            else if(pos == 12)
            {
                floatMotorIcon ic = ((floatMotorIcon)tmp_icon).Clone();
                ic.setAttributes(line, att_value_connector, att_separator);  
                ic.setImage();
                return ic;                
            }
            else if(pos == 13)
            {
                playSpecificNote ic = ((playSpecificNote)tmp_icon).Clone();
                ic.setAttributes(line, att_value_connector, att_separator);   
                ic.setImage();
                return ic;                
            }     
            else if(pos == 14)
            {
                startEvent ic = ((startEvent)tmp_icon).Clone();
                ic.setAttributes(line, att_value_connector, att_separator);   
                ic.setImage();
                return ic;                
            }
            else if(pos == 15)
            {
                returnIcon ic = ((returnIcon)tmp_icon).Clone();
                ic.setAttributes(line, att_value_connector, att_separator);   
                ic.setImage();
                return ic;                
            }     
            else if(pos == 16)
            {
                waitTouchIcon ic = ((waitTouchIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);   
                ic.setImage();
                return ic;                
            }    
            else if(pos == 17)
            {
                waitLightIcon ic = ((waitLightIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);   
                ic.setImage();
                return ic;                
            }    
            else if(pos == 18)
            {
                waitCelciusIcon ic = ((waitCelciusIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);   
                ic.setImage();
                return ic;                
            }
            else if(pos == 19)
            {
                waitRotationalIcon ic = ((waitRotationalIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);   
                ic.setImage();
                return ic;                
            }
            else if(pos == 20)
            {
                waitTimerIcon ic = ((waitTimerIcon)tmp_icon).Clone();
                ic.setAux(a);
                ic.setAttributes(line, att_value_connector, att_separator);   
                ic.setImage();
                return ic;                
            }            
        }  
        else if(sp[0].compareTo("function") == 0)
        {
            line = sp2[1];
            line = rep.substring(rep.indexOf(att_boundary_begin)+1, rep.lastIndexOf(att_boundary_end));

            functionIcon ic = (new functionIcon()).Clone();
            ic.setAttributes(line, att_value_connector, att_separator, id_att_separator, att_boundary_end, a);  
            ic.setImage();
            return ic;
        }
        return tmp_icon;
    }
    
    /**
     * Given a line of a algorithm, this method determines what kind of icon it is, either object, branch, loop, function or variable
     * @param line A line of algorithm
     * @return 0 for undefined, 1 for object, 2 for branch, 3 for loop, 4 for function and 5 for variable
     */
    public static int checkOBLTag(String line)
    {
        int ret_val = 0;
        String[] tmp = new String[2];
        String[] tmp2 = new String[3];
        
        tmp = line.split(id_att_separator);
        tmp2 = tmp[0].split(id_separator);
        if(tmp2[0].compareTo("object") == 0)
        {
            ret_val = 1;
        }
        else if(tmp2[0].compareTo("branch") == 0)
        {
            ret_val = 2;
        }
        else if(tmp2[0].compareTo("loop") == 0)
        {
            ret_val = 3;
        }
        else if(tmp2[0].compareTo("function") == 0)
        {
            ret_val = 4;
        }
        else if(tmp2[0].compareTo("variable") == 0)
        {
            ret_val = 5;
        }
        return ret_val;
    }    
    
    
    /**
     * Given a line of a algorithm, this method determines whether it is a function, a task, a variable, an event, or a monitorevent
     * @param line A line of algorithm
     * @return 0 for undefined, 1 for function, 2 for task, 3 for variable, 4 for event, 5 for monitorevent
     */
    public static int checkTFTag(String line)
    {
        int ret_val = 0;
        String[] tmp = new String[2];
        String[] tmp2 = new String[2];   // big difference!, check OBLTag has 3 not 2
        
        tmp = line.split(id_att_separator);
        tmp2 = tmp[0].split(id_separator);
        if(tmp2[0].compareTo("function") == 0)
        {
            ret_val = 1;
        }
        else if(tmp2[0].compareTo("task") == 0)
        {
            ret_val = 2;
        }
        else if(tmp2[0].compareTo("variable") == 0)
        {
            ret_val = 3;
        }
        else if(tmp2[0].compareTo("event") == 0)
        {
            ret_val = 4;
        }   
        else if(tmp2[0].compareTo("monitorevent") == 0)
        {
            ret_val = 5;
        } 
        return ret_val;
    }
    
    /**
     * Gets the end tag of a branch, a loop, a function, a task or a monitorevent
     * @param line A line of algorithm
     * @return The end tag
     */
    public static String getEndTag(String line)
    {
        String ret_val = new String();
        String[] tmp = new String[2];
        String[] tmp2 = new String[3];
        
        tmp = line.split(id_att_separator);
        tmp2 = tmp[0].split(id_separator); 
        if(tmp2[0].compareTo("branch") == 0)
        {
            ret_val = "endbranch";
            ret_val += id_separator;
            ret_val += tmp2[2];
            ret_val += ".";
        }
        else if(tmp2[0].compareTo("loop") == 0)
        {
            ret_val = "endloop";
            ret_val += id_separator;
            ret_val += tmp2[2];
            ret_val += ".";
        }
        else if(tmp2[0].compareTo("function") == 0)
        { 
            ret_val = "endfunction";
            ret_val += id_separator;
            ret_val += tmp2[1]; 
            ret_val += ".";
        }
        else if(tmp2[0].compareTo("task") == 0)
        { 
            ret_val = "endtask";
            ret_val += id_separator;
            ret_val += tmp2[1]; 
            ret_val += ".";
        }
        else if(tmp2[0].compareTo("monitorevent") == 0)
        { 
            ret_val = "endmonitorevent";
            ret_val += id_separator;
            ret_val += tmp2[1]; 
            ret_val += ".";
        }        
        else
        {
            return null;
        }
        return ret_val;
    }
    
    // methods to get the six begin tags --> task, function, loop, if, endif, else and endelse
    
    /**
     * Get the task tag given a line of algorithm
     * @param line A line of algorithm
     * @return The task tag
     */
    public static String getTaskTag(String line)
    {
        String ret_val = new String();
        String[] tmp = new String[2];
        String[] tmp2 = new String[3];
        
        tmp = line.split(id_att_separator);
        tmp2 = tmp[0].split(id_separator);
        if(tmp2[0].compareTo("task") == 0)
        {
            ret_val = "starttask";
            ret_val += id_separator;
            ret_val += tmp2[1];
        }
        else
        {
            return null;
        }
        return ret_val;
    }
    
    /**
     * Given a task first line algorithm, this method returns a task's name
     * @param line A line of algorithm
     * @return The task name
     */
    public static String getTaskName(String line)
    {
        String task_name = new String();
        line = line.substring(0, line.indexOf("\n"));
        String[] sp = line.split(id_att_separator);
        task_name = sp[1];
        task_name = task_name.substring(1, task_name.indexOf(att_boundary_end));
        sp = task_name.split(att_value_connector);
        task_name = sp[1];   
        return task_name;
    }
    
    /**
     * Get the event monitor tag given a line of algorithm
     * @param line A line of algorithm
     * @return The envent monitor tag
     */
    public static String getEventMonitorTag(String line)
    {
        String ret_val = new String();
        String[] tmp = new String[2];
        String[] tmp2 = new String[3];
        
        tmp = line.split(id_att_separator);
        tmp2 = tmp[0].split(id_separator);
        if(tmp2[0].compareTo("monitorevent") == 0)
        {
            ret_val = "startmonitorevent";
            ret_val += id_separator;
            ret_val += tmp2[1];
        }
        else
        {
            return null;
        }
        return ret_val;
    }   

    /**
     * Given a event monitor first line algorithm, this method returns a event monitor's name
     * @param line A line of algorithm
     * @return The event monitor name
     */
    public static String getEventMonitorName(String line)
    {
        String event_name = new String();
        line = line.substring(line.indexOf(att_boundary_begin)+1, line.indexOf("\n"));
        String[] sp = line.split(att_value_connector);
        event_name = sp[1];
        event_name = event_name.substring(0, event_name.indexOf(att_separator));
        return event_name;
    }    
 
    /**
     * Given an event first line algorithm, this method returns the event's attributes
     * @param line A line of algorithm
     * @return The event attributes
     */
    public static String[] getEventAtt(String line)
    {
        String[] function_att = new String[6];
        String argument = new String();
        line = line.substring(line.indexOf(att_boundary_begin)+1, line.lastIndexOf(att_boundary_end));

        String[] sp = line.split(att_separator);
        
        // do the first one --> name   
        argument = line.substring(0, line.indexOf(att_separator));
        sp = argument.split(att_value_connector);
        function_att[0] = sp[1];
        
        line = line.substring(line.indexOf(att_separator)+1);                
        
        // second one --> port
        argument = line.substring(0, line.indexOf(att_separator));
        sp = argument.split(att_value_connector);
        function_att[1] = sp[1];
        
        line = line.substring(line.indexOf(att_separator)+1);    
                
        // third one --> sensor type
        argument = line.substring(0, line.indexOf(att_separator));
        sp = argument.split(att_value_connector);
        function_att[2] = sp[1];
        
        line = line.substring(line.indexOf(att_separator)+1);    
        
        // fourth one --> sensor comparator
        argument = line.substring(0, line.indexOf(att_separator));
        sp = argument.split(att_value_connector);
        function_att[3] = sp[1];
        
        line = line.substring(line.indexOf(att_separator)+1);           
        
        // fifth one --> threshold
        argument = line.substring(line.indexOf(att_value_connector)+1);  
        function_att[4] = argument.substring(0, 1);
        
        line = line.substring(line.indexOf(att_value_connector)+1);
        if(line.compareTo(".") != 0)
        {
            line = line.substring(line.indexOf(id_att_separator)+2);

            // sixth one --> threshold's constant value     
            sp = line.split(att_value_connector);
            argument = sp[1];
            argument = argument.substring(0, argument.indexOf(att_boundary_end));
            function_att[5] = argument;           
        }

        
        return function_att;
    }    
    
    /**
     * Given an event monitor first line algorithm, this method returns the event monitor's attributes
     * @param line A line of algorithm
     * @return The event monitor attributes
     */
    public static java.util.Vector<String> getEventMonitorAtt(String line)
    {
        java.util.Vector<String> function_att = new java.util.Vector<String>();
        String argument = new String();
        int total_events = 0;
        line = line.substring(line.indexOf(att_boundary_begin)+1, line.lastIndexOf(att_boundary_end));

        String[] sp = line.split(att_separator);
        
        // do the first one --> name   
        argument = line.substring(0, line.indexOf(att_separator));
        sp = argument.split(att_value_connector);
        function_att.addElement(sp[1]);
        
        line = line.substring(line.indexOf(att_separator)+1);                
        
        // second one --> type
        argument = line.substring(0, line.indexOf(att_separator));
        sp = argument.split(att_value_connector);
        function_att.addElement(sp[1]);
        
        line = line.substring(line.indexOf(att_separator)+1);    
                
        // third one --> level
        argument = line.substring(0, line.indexOf(att_separator));
        sp = argument.split(att_value_connector);
        function_att.addElement(sp[1]);
        
        line = line.substring(line.indexOf(att_separator)+1);   
        
        // fourth one --> auxiliary
        argument = line.substring(0, line.indexOf(att_separator));
        sp = argument.split(att_value_connector);
        function_att.addElement(sp[1]);
        
        line = line.substring(line.indexOf(att_separator)+1); 
        
        // fifth one --> number of monitored events
        argument = line.substring(0, line.indexOf(att_separator));
        sp = argument.split(att_value_connector);
        total_events = Integer.parseInt(sp[1]);
        
        line = line.substring(line.indexOf(att_separator)+1); 
        
        // sixth one --> the monitored events
        for(int i=0; i<total_events; i++)
        {
            if(i == total_events - 2) // last one
            {
                argument = line.substring(0, line.indexOf(att_separator));
                sp = argument.split(att_value_connector);
                function_att.addElement(sp[1]);
                line = line.substring(line.indexOf(att_separator)+1); 
            }      
            else if(i >= total_events - 1)
            {
                argument = line;
                sp = argument.split(att_value_connector);
                function_att.addElement(sp[1]);
                break;
            }
            else
            {
                argument = line.substring(0, line.indexOf(att_separator));
                sp = argument.split(att_value_connector);
                function_att.addElement(sp[1]);
                line = line.substring(line.indexOf(att_separator)+1);     
            }   
        }
        
        return function_att;
    }    
    
    /**
     * Given a function first line algorithm, this method returns the function's attributes
     * @param line A line of algorithm
     * @return The function attributes
     */
    public static String[] getFunctionAtt(String line)
    {
        String[] function_att = new String[2];
        line = line.substring(line.indexOf(att_boundary_begin)+1, line.indexOf(att_boundary_end));
        
        // do the first one --> name
        String[] sp = line.split(att_separator);
        String[] sp2 = sp[0].split(att_value_connector);        
        function_att[0] = sp2[1];
        
        // second one --> level
        sp2 = sp[1].split(att_value_connector);        
        function_att[1] = sp2[1];
        
        return function_att;
    }
    
    /**
     * Get the function tag given a line of algorithm
     * @param line A line of algorithm
     * @return The function tag
     */
    public static String getFunctionTag(String line)
    {
        String ret_val = new String();
        String[] tmp = new String[2];
        String[] tmp2 = new String[3];
        
        tmp = line.split(id_att_separator);
        tmp2 = tmp[0].split(id_separator);
        if(tmp2[0].compareTo("function") == 0)
        {
            ret_val = "startfunction";
            ret_val += id_separator;
            ret_val += tmp2[1];
        }
        else
        {
            return null;
        }
        return ret_val;
    }
        
    /**
     * Get if tag for a branch
     * @param line A line of algorithm
     * @return If tag
     */
    public static String getIfTag(String line)
    {
        String ret_val = new String();
        String[] tmp = new String[2];
        String[] tmp2 = new String[3];
        
        tmp = line.split(id_att_separator);
        tmp2 = tmp[0].split(id_separator);
        if(tmp2[0].compareTo("branch") == 0)
        {
            ret_val = "if";
            ret_val += id_separator;
            ret_val += tmp2[2];
            ret_val += ".";
        }
        else
        {
            return null;
        }
        return ret_val;
    }
    
    // 2nd method for the branch tags
    /**
     * Get ending tag for the if tag of a branch
     * @param line A line of algorithm
     * @return End if tag
     */
    public static String getEndIfTag(String line)
    {
        String ret_val = new String();
        String[] tmp = new String[2];
        String[] tmp2 = new String[3];
        
        tmp = line.split(id_att_separator);
        tmp2 = tmp[0].split(id_separator);
        if(tmp2[0].compareTo("branch") == 0)
        {
            ret_val = "endif";
            ret_val += id_separator;
            ret_val += tmp2[2];
            ret_val += ".";
        }
        else
        {
            return null;
        }
        return ret_val;
    }
    
    // 3rd method --> else
    /**
     * Gets else tag of a branch
     * @param line A line of algorithm
     * @return Else tag
     */
    public static String getElseTag(String line)
    {
        String ret_val = new String();
        String[] tmp = new String[2];
        String[] tmp2 = new String[3];
        
        tmp = line.split(id_att_separator);
        tmp2 = tmp[0].split(id_separator);
        if(tmp2[0].compareTo("branch") == 0)
        {
            ret_val = "else";
            ret_val += id_separator;
            ret_val += tmp2[2];
            ret_val += ".";
        }
        else
        {
            return null;
        }
        return ret_val;
    }
        
    // 4th method --> endelse
    /**
     * Gets the ending tag of an else part of a branch icon
     * @param line A line of algorithm
     * @return End else tag
     */
    public static String getEndElseTag(String line)
    {
        String ret_val = new String();
        String[] tmp = new String[2];
        String[] tmp2 = new String[3];
        
        tmp = line.split(id_att_separator);
        tmp2 = tmp[0].split(id_separator);
        if(tmp2[0].compareTo("branch") == 0)
        {
            ret_val = "endelse";
            ret_val += id_separator;
            ret_val += tmp2[2];
            ret_val += ".";
        }
        else
        {
            return null;
        }
        return ret_val;
    }
    
    /**
     * Gets begin tag of a loop
     * @param line A line of algorithm
     * @return Begin loop tag
     */
    public static String getBeginLoopTag(String line)
    {
        String ret_val = new String();
        String[] tmp = new String[2];
        String[] tmp2 = new String[3];
        
        tmp = line.split(id_att_separator);
        tmp2 = tmp[0].split(id_separator);
        if(tmp2[0].compareTo("loop") == 0)
        {
            ret_val = "beginloop";
            ret_val += id_separator;
            ret_val += tmp2[2];
            ret_val += ".";
        }
        else
        {
            return null;
        }
        return ret_val;
    }

    /**
     * Works in similar ways to the OBL, but it extracts event, event monitor, variable, task and function structures instead
     * @param code Algorithm
     * @return Set of algorithm pieces
     */    
    public static java.util.Vector<String> getTFTags(String code)
    {
        java.util.Vector<String> ret_val = new java.util.Vector<String>();
        String argument = new String();
        String line = new String();
        // add code an ending case
        code += "\nendingcase"; 
        
        while(code.compareTo("endingcase") != 0 || code.indexOf("endingcase") == -1)
        {
            line = code.substring(0, code.indexOf("\n"));
            // check if it is a branch, loop or object    
            int check = checkTFTag(line);
            
            if(check == 0)
            {
                return null;  // undefined tag
            }
            else if(check == 1)   // function
            {
                if(Translator.getEndTag(line) == null)   // error in reading the end tag
                {
                    return null;
                }               
                argument = code.substring(0, code.indexOf(Translator.getEndTag(line)));                   
                code = code.substring(code.indexOf(Translator.getEndTag(line)));                 
                argument += code.substring(0, code.indexOf("\n"));  // cut the code                 
                code = code.substring(code.indexOf("\n")+1);  // cut the code
            }
            else if(check == 2)  // task
            {
                if(Translator.getEndTag(line) == null)   // error in reading the end tag
                {
                    return null;
                }               
                argument = code.substring(0, code.indexOf(Translator.getEndTag(line)));                   
                code = code.substring(code.indexOf(Translator.getEndTag(line)));                 
                argument += code.substring(0, code.indexOf("\n"));  // cut the code                 
                code = code.substring(code.indexOf("\n")+1);  // cut the code
            }
            else if(check == 3)  // global variable
            {
                argument = line;
                code = code.substring(code.indexOf("\n")+1);  // cut the code
            }
            else if(check == 4)  // events
            {
                argument = line;
                code = code.substring(code.indexOf("\n")+1);  // cut the code
            }
            else if(check == 5)  // event monitor
            {
                if(Translator.getEndTag(line) == null)   // error in reading the end tag
                {
                    return null;
                }               
                argument = code.substring(0, code.indexOf(Translator.getEndTag(line)));                   
                code = code.substring(code.indexOf(Translator.getEndTag(line)));               
                argument += code.substring(0, code.indexOf("\n"));  // cut the code                 
                code = code.substring(code.indexOf("\n")+1);  // cut the code
            }            
            ret_val.addElement(argument);             
        }
        return ret_val;
    }
    
    
    /**
     * Divide an algorithm into icon definitions of one level (ie. it will disregard icons within branches and loops, instead
     * treats the branches and loops as separate entities), OBL is not Osama Bin Laden, but it is Objects, Branches and Loops
     * @param code Algorithm
     * @return Set of algorithm pieces
     */
    public static java.util.Vector<String> getOBLTags(String code)
    {
        java.util.Vector<String> ret_val = new java.util.Vector<String>();
        String argument = new String();
        String line = new String();
        // add code an ending case
        code += "\nendingcase"; 
        
        while(code.compareTo("endingcase") != 0 || code.indexOf("endingcase") == -1)
        {
            line = code.substring(0, code.indexOf("\n"));
            // check if it is a branch, loop or object
            
            int check = checkOBLTag(line);
            if(check == 0)
            {
                return null;  // undefined tag
            }
            else if(check == 1)
            {
                argument = line;
                code = code.substring(code.indexOf("\n")+1);  // cut the code
            }
            else if(check == 2)
            {
                if(Translator.getEndTag(line) == null)   // error in reading the end tag
                {
                    return null;
                }
                argument = code.substring(0, code.indexOf(Translator.getEndTag(line)));                
                code = code.substring(code.indexOf(Translator.getEndTag(line))); 
                argument += code.substring(0, code.indexOf("\n"));  // cut the code                 
                code = code.substring(code.indexOf("\n")+1);  // cut the code
            }
            else if(check == 3)
            {
                if(Translator.getEndTag(line) == null)   // error in reading the end tag
                {
                    return null;
                }
                argument = code.substring(0, code.indexOf(Translator.getEndTag(line)));                
                code = code.substring(code.indexOf(Translator.getEndTag(line))); 
                argument += code.substring(0, code.indexOf("\n"));  // cut the code                 
                code = code.substring(code.indexOf("\n")+1);  // cut the code
            }
            else if(check == 4)
            {
                argument = line;
                code = code.substring(code.indexOf("\n")+1);  // cut the code
            }
            else if(check == 5)  // local variable
            {
                argument = line;
                code = code.substring(code.indexOf("\n")+1);  // cut the code
            }
            ret_val.addElement(argument); 
        }
        return ret_val;
    }
 
    /**
     * To get a variable to set its own attributes
     * @param v Variable
     * @param code The line of code passed
     */
    public static void setVarAttributes(variable v, String code)
    {
        String[] sp = new String[2];   // the identifiers
        String line = new String();
        
        sp = code.split(id_att_separator); 
        line = sp[1];
        line = line.substring(line.indexOf(att_boundary_begin)+1, line.indexOf(att_boundary_end));
        v.setAttributes(line, att_value_connector, att_separator);  
    }
    
    /**
     * Get algorithm representation of the current program
     * @param parent_icon First icon
     * @return Algorithm
     */
    public static String getAlgorithm(Icon parent_icon)
    {
       // created_sub_id = new java.util.Vector<Integer>();
        String ret_val = AppProperties.getProgramTypeID();
        ret_val += "\nstart";
        ret_val += getGlobalVariablesAlgorithm();
        ret_val += getAuxAlgorithm();
        ret_val += "\nendprogram";
        return ret_val;
    }
    
    /**
     * Current string algorithm representation of the iconic program
     * @return Algorithm
     * @param parent_icon First icon
     */
    public static String getIndividualAlgorithm(Icon parent_icon)
    {
        String code = new String();
        java.util.Vector<Icon> ics;
        
        // commence the translation !
       
        code += parent_icon.getTranslation(id_separator, id_att_separator, att_boundary_begin, 
                                           att_boundary_end, att_value_connector, att_separator);   
                                                // as the header, it should be the parent icon's translation
                                                // e.g. the GREEN STARTING ICON should only have "start"
        
        if(parent_icon instanceof startTaskIcon)
        {
            // add the arguments where possible        
            startTaskIcon sti = (startTaskIcon)parent_icon;
            java.util.Vector<variable> tmp_list_var = sti.getTask().getVariables();
            for(int i=1; i<tmp_list_var.size(); i++)
            {
                variable tmp_v = tmp_list_var.get(i);
                if(tmp_v != null)
                {
                    code += tmp_v.getTranslation(id_separator, id_att_separator, att_boundary_begin, 
                                                 att_boundary_end, att_value_connector, att_separator);    
                }            
            }            
        }
        else if(parent_icon instanceof startFunctionIcon)
        {
            // add the arguments where possible        
            startFunctionIcon sfi = (startFunctionIcon)parent_icon;
            java.util.Vector<variable> tmp_list_var = sfi.getFunction().getVariables();
            for(int i=1; i<tmp_list_var.size(); i++)
            {
                variable tmp_v = tmp_list_var.get(i);
                if(tmp_v != null)
                {
                    code += tmp_v.getTranslation(id_separator, id_att_separator, att_boundary_begin, 
                                                 att_boundary_end, att_value_connector, att_separator);    
                }            
            }            
        }
        
        // get the members
        // start to recurse between the structures and object icons
        if(parent_icon instanceof branchIcon)
        {
            ics = parent_icon.getIfMembers();
            code += "\nif"+id_separator+parent_icon.getId()+".";
            for(int i=0; i<ics.size(); i++)
            {
                Icon tmp_icon = ics.get(i);
                if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
                {
                    code += getIndividualAlgorithm(tmp_icon);
                }
                else
                {
                    code += tmp_icon.getTranslation(id_separator, id_att_separator, att_boundary_begin, 
                                                    att_boundary_end, att_value_connector, att_separator); // already includes the new line character
                }
            }
            code += "\nendif"+id_separator+parent_icon.getId()+".\nelse"+id_separator+parent_icon.getId()+".";
            ics = parent_icon.getElseMembers();
            for(int i=0; i<ics.size(); i++)
            {
                Icon tmp_icon = ics.get(i);
                if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
                {
                    code += getIndividualAlgorithm(tmp_icon);
                }
                else
                {
                    code += tmp_icon.getTranslation(id_separator, id_att_separator, att_boundary_begin, 
                                                    att_boundary_end, att_value_connector, att_separator); // already includes the new line character
                }
            }
            code += "\nendelse"+id_separator+parent_icon.getId()+".";
        }
        else if(parent_icon instanceof loopIcon)
        {
            ics = parent_icon.getMembers();
            code += "\nbeginloop"+id_separator+parent_icon.getId()+".";
            for(int i=0; i<ics.size(); i++)
            {
                Icon tmp_icon = ics.get(i);
                if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
                {
                    code += getIndividualAlgorithm(tmp_icon);
                }
                else
                {
                    code += tmp_icon.getTranslation(id_separator, id_att_separator, att_boundary_begin, 
                                                    att_boundary_end, att_value_connector, att_separator); // already includes the new line character
                }
            }
        }
        else
        {
            ics = parent_icon.getMembers();
            for(int i=0; i<ics.size(); i++)
            {
                Icon tmp_icon = ics.get(i);
                if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
                {
                    code += getIndividualAlgorithm(tmp_icon);
                }
                else
                { 
                    code += tmp_icon.getTranslation(id_separator, id_att_separator, att_boundary_begin, 
                                                    att_boundary_end, att_value_connector, att_separator); // already includes the new line character
                }
            }
        }
        
        if(parent_icon instanceof branchIcon)
        {
            code += "\nendbranch" + id_separator + parent_icon.getId() + ".";
        }
        else if(parent_icon instanceof loopIcon)
        {
            code += "\nendloop" + id_separator + parent_icon.getId() + ".";
        }     
        return code;
    }

    /**
     * Returns the algorithm representations of the global variables
     * @return algorithm for global variables
     */    
    private static String getGlobalVariablesAlgorithm()
    {
        String code = new String();  
        
        // all global variables except the sensors 1, 2 and 3 will be declared here
        java.util.Vector<variable> vars = var_list.Instance().getVariables();
        for(int i = 1; i < vars.size(); i++)
        {
            variable tmp_v = vars.get(i);
            if(tmp_v != null && tmp_v.getNumId() > 3)
            {
                code += tmp_v.getTranslation(id_separator, id_att_separator, att_boundary_begin, 
                                       att_boundary_end, att_value_connector, att_separator);
            }
        }
        return code;        
    }    
    
    /**
     * Returns an algorithm representation of a function or a task or an event or an event monitor (auxiliaries) and its definition
     * @return code The algorithm
     */    
    public static String getAuxAlgorithm()
    {
        String code = new String();
        
        aux_list alist = aux_list.Instance();
        java.util.Vector<auxiliary> auxs = alist.getAuxiliaries();
        
        // first do the tasks
        for(int i=1; i<auxs.size(); i++)
        {
            auxiliary tmp = auxs.get(i);
            if(tmp != null && tmp instanceof task)
            {
                code += tmp.getTranslation(id_separator, id_att_separator, att_boundary_begin, 
                                           att_boundary_end, att_value_connector, att_separator);
                code += getIndividualAlgorithm(tmp.getBeginIcon());
            }            
        }
        
        // then do the functions
        for(int i=1; i<auxs.size(); i++)
        {
            auxiliary tmp = auxs.get(i);
            if(tmp != null && tmp instanceof function)
            {
                code += tmp.getTranslation(id_separator, id_att_separator, att_boundary_begin, 
                                           att_boundary_end, att_value_connector, att_separator);
                code += getIndividualAlgorithm(tmp.getBeginIcon());
            }            
        }    
        
        // do the events
        java.util.Vector<Event> event_list = event_number_tracker.Instance().getEvents();
        for(int i=1; i<event_list.size(); i++)
        {
            Event tmp = event_list.get(i);
            if(tmp != null)
            {
                code += tmp.getTranslation(id_separator, id_att_separator, att_boundary_begin, 
                                           att_boundary_end, att_value_connector, att_separator);
            }
        }
        
        // finally do the monitor events
        for(int i=1; i<auxs.size(); i++)
        {
            auxiliary tmp = auxs.get(i);
            if(tmp != null && tmp instanceof monitorEvent)
            {
                code += tmp.getTranslation(id_separator, id_att_separator, att_boundary_begin, 
                                           att_boundary_end, att_value_connector, att_separator);
                code += getIndividualAlgorithm(tmp.getBeginIcon());
            }            
        }        
        return code;
    }
    
    
    // this the ultimate method to get the robot working, dancing, spinning, bla bla !!
    // the other argument is important for a good layout of a well indented NQC (if the NQC is made visible for the users in the future)
    // we start at 0 indentation
    /**
     * Returns the RCX bytecodes given a string representation of the iconic program
     * @return NQC Code
     * @param num_indent Number of indentations
     */
    public static String getRCXNQCCode(int num_indent)
    {
        String NQCCode = new String();
        NQCCode = "/"+"*\n"+"     This NQC code has been generated by "
                + AppProperties.appNameVersion + " for " + AppProperties.getDeviceName() + "\n*/\n";
        NQCCode += getRCXNQCGlobalVariables();
        NQCCode += getRCXNQCFunctionsTasks(num_indent);        
        return NQCCode;
    }
   
    
    /**
     * This method translates the body of a function or a task
     * @param parent_icon First icon
     * @param num_indent Number of indentations
     * @return NQC Code
     */
    
    public static String getBodyNQCCode(Icon parent_icon, int num_indent)
    {
        String NQCCode = new String();      // a whole NQC code
        String indentation_outer = new String();   // for the brackets
        String indentation_inner = new String();   // for the members

        java.util.Vector<Icon> ics;
        for(int i=0; i<num_indent-1; i++)     // apply the indentation a number of times as specified
        {
            indentation_outer += "\t";
        }
        for(int i=0; i<num_indent; i++)     // apply the indentation a number of times as specified
        {
            indentation_inner += "\t";
        }
        NQCCode += parent_icon.getNQCCode(indentation_outer); 
        if(parent_icon instanceof branchIcon)
        {            
            ics = parent_icon.getIfMembers();
            NQCCode += indentation_outer;
            NQCCode += "{\n";
            for(int i=0; i<ics.size(); i++)
            {
                Icon tmp_icon = ics.get(i);
                if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
                {
                    NQCCode += getBodyNQCCode(tmp_icon, num_indent+1);
                }
                else if(tmp_icon instanceof startEvent)
                {
                    startEvent SE = (startEvent)tmp_icon;
                    NQCCode += SE.getNQCCode(indentation_inner, num_indent);
                }                
                else
                {
                    NQCCode += tmp_icon.getNQCCode(indentation_inner);
                }
            } 
            NQCCode += indentation_outer;
            NQCCode += "}\n";
            ics = parent_icon.getElseMembers();
            if(ics.size() > 2)  // excluding join icons
            {
                NQCCode += indentation_outer;
                NQCCode += "else\n";
                NQCCode += indentation_outer+"{\n";

                for(int i=0; i<ics.size(); i++)
                {
                    Icon tmp_icon = ics.get(i);
                    if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
                    {
                        NQCCode += getBodyNQCCode(tmp_icon, num_indent+1);
                    }
                    else if(tmp_icon instanceof startEvent)
                    {
                        startEvent SE = (startEvent)tmp_icon;
                        NQCCode += SE.getNQCCode(indentation_inner, num_indent);
                    }                    
                    else
                    {
                        NQCCode += tmp_icon.getNQCCode(indentation_inner);
                    }
                }
                NQCCode += indentation_outer;
                NQCCode += "}\n\n";
            }            
        }
        else if(parent_icon instanceof loopIcon)
        {
            ics = parent_icon.getMembers();
            NQCCode += indentation_outer;
            NQCCode += "{\n";
            for(int i=0; i<ics.size(); i++)
            {
                Icon tmp_icon = ics.get(i);
                if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
                {
                    NQCCode += getBodyNQCCode(tmp_icon, num_indent+1);
                }
                else if(tmp_icon instanceof startEvent)
                {
                    startEvent SE = (startEvent)tmp_icon;
                    NQCCode += SE.getNQCCode(indentation_inner, num_indent);
                }                
                else
                {
                    NQCCode += tmp_icon.getNQCCode(indentation_inner);
                }
            }
            NQCCode += indentation_outer;
            NQCCode += "}\n\n";
        }
        else
        {
            ics = parent_icon.getMembers();
            for(int i=0; i<ics.size(); i++) 
            {
                Icon tmp_icon = ics.get(i);
                if(tmp_icon instanceof branchIcon || tmp_icon instanceof loopIcon)
                {
                    NQCCode += getBodyNQCCode(tmp_icon, num_indent+1);
                }
                else if(tmp_icon instanceof startEvent)
                {
                    startEvent SE = (startEvent)tmp_icon;
                    NQCCode += SE.getNQCCode(indentation_inner, num_indent);
                }
                else if(tmp_icon instanceof endEventIcon)   // can only occur inside a startEventIcon
                {
                    NQCCode += tmp_icon.getNQCCode(indentation_outer); 
                }                    
                else
                {
                    NQCCode += tmp_icon.getNQCCode(indentation_inner); 
                }
            }
        }
        return NQCCode;
    }
        

    /**
     * Returns the NQC representations of the global variables
     * @return NQCCode for variables
     */    
    private static String getRCXNQCGlobalVariables()
    {
        String ret_val = new String();  
        
        // all global variables except the sensors 1, 2 and 3 will be declared here
        java.util.Vector<variable> vars = var_list.Instance().getVariables();
        if(vars.size() > 4)
        {
            ret_val += "\n/"+"*\n"+"     Global variable declarations\n*/\n\n";
            for(int i=1; i<vars.size(); i++)
            {
                variable tmp_v = vars.get(i);
                if(tmp_v != null && tmp_v.getNumId() > 3)
                {
                    ret_val += tmp_v.getNQCDeclaration();
                }
            }
        }
        return ret_val;        
    }
    
    /**
     * Returns the NQC representations of the functions and tasks
     * @param num_indent Number of indentations
     * @return NQC Code functions
     */
    private static String getRCXNQCFunctionsTasks(int num_indent)
    {
        String ret_val = new String();
        
        // to separate from the main task with the auxiliary functions
        ret_val += "\n";
        
        aux_list alist = aux_list.Instance();
        java.util.Vector<auxiliary> auxs = alist.getAuxiliaries();
        
        // do the tasks first
        for(int i=1; i<auxs.size(); i++)
        {
            auxiliary tmp = auxs.get(i);
            if(tmp != null && tmp instanceof task)
            {
                ret_val += tmp.getNQCTaskHeader();
                // now fill in the tasks
                ret_val += getBodyNQCCode(tmp.getBeginIcon(), num_indent);
            }            
        }
        
        // then do the functions
        for(int i=1; i<auxs.size(); i++)
        {
            auxiliary tmp = auxs.get(i);
            if(tmp != null && tmp instanceof function)
            {
                ret_val += tmp.getNQCFunctionHeader();
                // now fill in the functions
                ret_val += getBodyNQCCode(tmp.getBeginIcon(), num_indent);
            }            
        }
        return ret_val;
    }
}
