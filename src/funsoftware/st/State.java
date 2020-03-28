/*
 * State.java
 *
 * Created on 17 September 2005, 13:24
 *
 */

package funsoftware.st;

/**
 * This class maintains a state (or states) of a set of icons and wires on the programming window
 * Used for Memento design patterns (Undo and Redo) and deciding whether a file has been saved
 * @author Thomas Legowo
 * 
 */
public class State {
    
    int saved;   // initially zero for not saved, 1 for saved
    String save_file_path;  // for save file path
    String filename;
    String algorithm;   // representation of the icons (snapshot of a state)
    
    /** Creates a new instance of State */
    public State() {
        saved = 0;
        save_file_path = new String();
        filename = new String();
        algorithm = new String();
    }
 
    /**
     * To set status of the current state whether it has been saved
     * @param s Save status
     */
    public void setSaveStatus(int s)
    {
        saved = s;
    }
    
    /**
     * To set save file path
     * @param s Save file path
     */
    public void setSaveFilePath(String s)
    {
        save_file_path = s;
    }    
    
    /**
     * To set save file name
     * @param s Save file name
     */
    public void setSaveFileName(String s)
    {
        if(s.lastIndexOf(".") != -1)
        {
            s = s.substring(0, s.lastIndexOf("."));
        }
        filename = s;
    } 
    
    /**
     * Return the save file path
     * @return Save file path
     */
    public String returnSaveFilePath()
    {
        return save_file_path;
    }   
    
    /**
     * Return the save file name
     * @return Save file name
     */
    public String returnSaveFileName()
    {
        return filename;
    }     
    
    /**
     * Return the save status of the current state
     * @return Save status
     */
    public int returnSaveStatus()
    {
        return saved;
    }
    
    /**
     * Set the current state
     * @param a State in string format
     */
    public void setState(String a)
    {
        algorithm = a;
    }
    
    /**
     * Return the current state
     * @return String format of the state
     */
    public String returnState()
    {
        return algorithm;
    }
}
