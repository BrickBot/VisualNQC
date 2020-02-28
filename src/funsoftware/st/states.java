/*
 * states.java
 *
 * Created on 17 September 2005, 14:17
 */

package funsoftware.st;

/**
 * This class handles for different states of the program, it contains a vector of State(s).
 * @author Thomas Legowo
 */
public class states {
    
    private java.util.Vector<State> undo_state;
    private java.util.Vector<State> redo_state;
    private State current_state;
    private int undo_lim_states;   // num of states stored in undo
    private int redo_lim_states;   // num of states stored in redo
    
    // for singleton design pattern
    private static states instance = null;
    
    /** Creates a new instance of states */
    protected states() {
        undo_state = new java.util.Vector<State>();
        redo_state = new java.util.Vector<State>();
        current_state = null;  // initially
        undo_lim_states = 20;   // maximum 20 states to undo
        redo_lim_states = 20;   // maximum 20 states to redo
    }
    
    /**
     * To ensure only one instance of the states list is used, use singleton design pattern
     * @return Instance of this class
     */
    public static states Instance() {
        if(instance == null) {
            instance = new states();
        }
        return instance;
    }
    
    /**
     * Resets this states object and its attributes
     */
    public void reset() {
        undo_state = new java.util.Vector<State>();
        redo_state = new java.util.Vector<State>();
        current_state = null;   // initially
        undo_lim_states = 20;
        redo_lim_states = 20;
    }
    
    /**
     * Gets the current state of the program
     * @return Current state
     */
    public State getCurrentState() {
        return current_state;
    }
    
    // dealing with the states to be used in Undo operations
    /**
     * Adds a state in the undo database
     * @param new_state New state
     */
    public void addStateInUndo(State new_state) {
        if(undo_state.size() == undo_lim_states)   // limit reached, so overwrite the oldest first
        {
            undo_state.removeElementAt(undo_lim_states-1);
        }
        undo_state.add(0, new_state);
    }
    
    // remove the first one always and return it
    /**
     * Removes a state from the undo database
     * @return A state in undo database
     */
    public State removeStateInUndo() {
        State ret_val;
        ret_val = undo_state.get(0);
        undo_state.removeElementAt(0);
        
        return ret_val;
    }
    
    // to do the undo operation
    // the return value is the current state set to be the next available state in the undo state list
    /**
     * Execute the undo operation
     * @return Current state
     */
    public State commitUndo() {
        if(undo_state.size() == 0)  // can't undo anymore
        {
            return null;
        }
        addStateInRedo(current_state);      // preserve the current state in redo
        current_state = removeStateInUndo();
        
        return current_state;
    }
    
    // dealing with the states to be used in Redo operations
    /**
     * Adds a state in the redo database
     * @param new_state New state
     */
    public void addStateInRedo(State new_state) {
        if(redo_state.size() == redo_lim_states)   // limit reached, so overwrite the oldest first
        {
            redo_state.removeElementAt(redo_lim_states-1);
        }
        redo_state.add(0, new_state);
    }
    
    /**
     * Removes a state from the redo database
     * @return A state in redo database
     */
    public State removeStateInRedo() {
        State ret_val;
        ret_val = redo_state.get(0);
        redo_state.removeElementAt(0);
        return ret_val;
    }
    
    // to do the redo operation
    /**
     * Execute the redo operation
     * @return Current state
     */
    public State commitRedo() {
        if(redo_state.size() == 0)  // can't redo anymore
        {
            return null;
        }
        addStateInUndo(current_state);      // preserve the current state in undo
        current_state = removeStateInRedo();
        return current_state;
    }
    
    // the method to keep on going forward from one state to another
    // i.e. keep filling in the Undo state vector
    /**
     * Going forward and taking more states as users execute operations
     * @param new_state New state to be stored in the undo database
     */
    public void forward(State new_state) {
        if(current_state != null)   // not the beginning
        {
            addStateInUndo(current_state);   // move current state to undo
        }
        redo_state = new java.util.Vector<State>();   // reset the redo
        current_state = new_state;       // set the current_state
    }
    
    /**
     * Get the states from the undo database
     * @return Set of states
     */
    public java.util.Vector<State> getUndoState() {
        return undo_state;
    }
    /**
     * Get the states from the undo database
     * @return Set of states
     */
    public java.util.Vector<State> getRedoState() {
        return redo_state;
    }
}
