/*
 * local_var_list.java
 *
 * Created on 17 January 2006, 12:07
 *
 */

package funsoftware.struct;

import funsoftware.var.*;

/**
 * Acts like a var_list but is not unique and each auxiliary has one (for local variables)
 * @author Thomas Legowo
 */
public class local_var_list {
    
    // BUT IT RECYCLES THE ID
    // i.e. the element's id corresponds to its location in the vector for quick access
    private java.util.Vector<variable> list_of_vars;
    private java.util.Vector<Integer> list_of_available_id;    
    
    /** Creates a new instance of local_var_list */
    public local_var_list() {
        list_of_vars = new java.util.Vector<variable>();
        list_of_vars.setSize(1);
        list_of_available_id = new java.util.Vector<Integer>();
    }    
    
    /**
     * Adds a variable
     * @param var The variable
     */
    public void addVariable(variable var)
    {
        int id = getFirstAvailableId();

        var.setNumId(id);
        
        if(id < list_of_vars.size())
        {
            list_of_vars.setElementAt(var, id);
        }
        else
        {
            list_of_vars.add(id, var);
        }
    }
    
    /**
     * Deletes a variable, no need to decrease the size of the vector by 1 since the id is reusable
     * @param num_id Variable to be deleted
     */
    public void delVariable(int num_id)
    {
        list_of_vars.setElementAt(null, num_id);
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
            id = list_of_vars.size();
        }
        return id;
    }
    
    /**
     * Gets the list of variables
     * @return Set of variable structures
     */
    public java.util.Vector<variable> getVariables()
    {
        return list_of_vars;
    }
    
    /**
     * Resets this class' list of variables
     */
    public void resetVarList()
    {
        list_of_vars = new java.util.Vector<variable>();
        list_of_vars.setSize(1);
        list_of_available_id = new java.util.Vector<Integer>();
    }
    
    /**
     * Returns 1 if the name is not unique, 0 otherwise, 2 if does not conform to rules of allowed characters
     * @param name The name to be checked
     * @param num_id The num_id of the local variable
     * @return The status of the process' outcome
     */
    public int checkName(String name, int num_id)
    {
        int maxIndex;
 
        if(num_id != 0 && name.compareTo(list_of_vars.get(num_id).getName()) == 0)  // for unchanged name
        {
        }
        else
        {
            maxIndex = list_of_vars.size();
            for(int i=1; i<maxIndex; i++)
            {
                variable tmp_var = list_of_vars.get(i);
                if(tmp_var != null && tmp_var.getName().compareTo(name) == 0)
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
        }        
        
        return 0;
    }    
    
    /**
     * For the reverse translation process
     * @param name The name of the variable to be searched
     * @return The variable
     */
    public variable getVariable(String name)
    {
        variable tmp = null;
        for(int i=1; i<list_of_vars.size(); i++)
        {
            tmp = list_of_vars.get(i); 
                if(tmp.getName().compareTo(name) == 0)
                {
                    return tmp;
                }
        }
        tmp = null;
        return tmp;
    }  
    
    /**
     * To determine whether the local variable list is empty
     * @return True or false
     */
    public Boolean isEmpty()
    {
        if(list_of_vars.size() > 1)
        {
            for(int i=1; i<list_of_vars.size(); i++)
            {
                if(list_of_vars.get(i) != null)
                {
                    return false;
                }
            }
        }
        return true;
    }    
}
