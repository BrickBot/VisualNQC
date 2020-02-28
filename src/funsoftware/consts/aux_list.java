/*
 * function_list.java
 *
 * Created on 27 September 2005, 22:19
 *
 */

package funsoftware.consts;
import funsoftware.struct.*;
import funsoftware.tasks.*;
import funsoftware.functs.*;
import funsoftware.events.*;

/**
 * Just like the icons and wires list, this list has the feature of id recycling and it maintains a list of functions and tasks.
 * @author Thomas Legowo
 */
public class aux_list {
    
    // BUT IT RECYCLES THE ID
    // i.e. the element's id corresponds to its location in the vector for quick access
    private java.util.Vector<auxiliary> list_of_auxs;
    private java.util.Vector<Integer> list_of_available_id;
    
    // for singleton design pattern
    private static aux_list instance = null;
    
    /** Creates a new instance of aux_list */
    protected aux_list() {
        list_of_auxs = new java.util.Vector<auxiliary>();
        list_of_auxs.setSize(1);
        list_of_available_id = new java.util.Vector<Integer>();
    }
    
    /**
     * To ensure only one instance of the auxiliary list is used, use singleton design pattern
     * @return Instance of this list
     */
    public static aux_list Instance()
    {
        if(instance == null)
        {
            instance = new aux_list();
        }
        return instance;
    }
    
    /**
     * Adds a function or a task or an event
     * @param func The function to be added
     */
    public void addAuxiliary(auxiliary func)
    {
        int id = getFirstAvailableId();

        func.setNumId(id);
        
        if(id < list_of_auxs.size())
        {
            list_of_auxs.setElementAt(func, id);
        }
        else
        {
            list_of_auxs.add(id, func);
        }
    }
    
    /**
     * Deletes a function or a task, no need to decrease the size of the vector by 1 since the id is reusable
     * @param num_id Function or Task to be deleted
     */
    public void delAuxiliary(int num_id)
    {
        list_of_auxs.setElementAt(null, num_id);
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
            id = list_of_auxs.size();
        }
        return id;
    }
    
    /**
     * Gets the list of auxiliaries
     * @return Set of auxiliary structures
     */
    public java.util.Vector<auxiliary> getAuxiliaries()
    {
        return list_of_auxs;
    }
    
    /**
     * Resets this class' list of auxiliaries
     */
    public void resetAuxList()
    {
        list_of_auxs = new java.util.Vector<auxiliary>();
        list_of_auxs.setSize(1);
        list_of_available_id = new java.util.Vector<Integer>();
    }
    
    /**
     * Returns 1 if the name is not unique, 0 otherwise, 2 if does not conform to rules of allowed characters
     * @param name The name to be checked
     * @return The number to indicate the result of this process
     */
    public int checkName(String name)
    {
        int maxIndex = list_of_auxs.size();
        for(int i=1; i<maxIndex; i++)
        {
            auxiliary tmp_func = list_of_auxs.get(i);
            if(tmp_func != null && tmp_func.getName().compareTo(name) == 0)
            {
                return 1;
            }
        }
        
        if(var_list.Instance().checkNameForAux(name) != 0) // already used in global variables
        {
            return 1;
        }
        
        // after the name is valid, it has to conform to rules such as only _, letters and numbers
        // do a loop that validates every single character of the entered name
        int length = name.length();
        int index = 0;  // checks from left to right

        while(index < length)
        {
            char c= name.charAt(index);   // first character has to be an alphabet (letter)
            if((index == 0 && ((c >= 65 && c <= 90) || (c >= 97 && c <= 122) || c == 95))
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
     * (Special for variable name checking when an auxiliary is created)
     * Returns 1 if the name is not unique, 0 otherwise, 2 if does not conform to rules of allowed characters
     * @param name The name to be checked
     * @return The number to indicate the result of this process
     */
    public int checkNameForVar(String name)
    {
        int maxIndex = list_of_auxs.size();
        for(int i=1; i<maxIndex; i++)
        {
            auxiliary tmp_func = list_of_auxs.get(i);
            if(tmp_func != null && tmp_func.getName().compareTo(name) == 0)
            {
                return 1;
            }
        }
        return 0;
    }    
    
    /**
     * For the reverse translation process
     * @param name The name of the auxiliary to be searched
     * @return The auxiliary returned
     */
    public auxiliary getAuxiliary(String name)
    {
        auxiliary tmp = null;
        for(int i=1; i<list_of_auxs.size(); i++)
        {
            tmp = list_of_auxs.get(i);
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
}
