/*
 * UndoRedo.java
 *
 * Created on 27 December 2005, 13:06
 *
 */

package funsoftware.st;

import funsoftware.consts.*;
import funsoftware.comp.*;

/**
 * This class manages the undo and redo operations, it commits the state updates and its buttons
 * @author Thomas Legowo
 */
public class UndoRedo {
    
    private static javax.swing.JButton UndoButton;
    private static javax.swing.JButton RedoButton;
    private static javax.swing.JMenuItem UndoMenu;
    private static javax.swing.JMenuItem RedoMenu;
    
    /**
     * To set the undo button
     * @param NUndo The undo button
     */
    public static void setUndoButton(javax.swing.JButton NUndo)
    {
        UndoButton = NUndo;
    }
    
    /**
     * To set the redo button
     * @param NRedo The redo button
     */
    public static void setRedoButton(javax.swing.JButton NRedo)
    {
        RedoButton = NRedo;
    }
    
    /**
     * To set the undo menu
     * @param NUndo The undo menu
     */
    public static void setUndoMenu(javax.swing.JMenuItem NUndo)
    {
        UndoMenu = NUndo;
    }
    
    /**
     * To set the redo menu
     * @param NRedo The redo menu
     */
    public static void setRedoMenu(javax.swing.JMenuItem NRedo)
    {
        RedoMenu = NRedo;
    }
    
    /**
     * To update the state of the undo redo buttons
     */
    public static void updateRedoUndoButtons()
    {
        states do_state = states.Instance();
        if(do_state.getUndoState().size() == 0)
        {
            UndoButton.setForeground(new java.awt.Color(180, 180, 180));
            UndoMenu.setForeground(new java.awt.Color(180, 180, 180));
        }
        else if(do_state.getUndoState().size() > 0)
        {
            UndoButton.setForeground(new java.awt.Color(0, 0, 0));
            UndoMenu.setForeground(new java.awt.Color(0, 0, 0));
        }
        if(do_state.getRedoState().size() == 0)
        {
            RedoButton.setForeground(new java.awt.Color(180, 180, 180));
            RedoMenu.setForeground(new java.awt.Color(180, 180, 180));
        }
        else if(do_state.getRedoState().size() > 0)
        {
            RedoButton.setForeground(new java.awt.Color(0, 0, 0));
            RedoMenu.setForeground(new java.awt.Color(0, 0, 0));
        }
    }
    
    /**
     * To update the state of the program
     */
    public static void update_state()
    {
        states do_state = states.Instance();
        State tmp_state = new State();
        icons_list icon_list = icons_list.Instance();
        tmp_state.setState(Translator.getAlgorithm(icon_list.getIcons().get(1))); 
        do_state.forward(tmp_state);
        updateRedoUndoButtons();
    }
    
    /**
     * To reset the state of the undo/redo buttons and menu items
     */     
    public static void reset()
    {
        UndoButton.setForeground(new java.awt.Color(180, 180, 180));
        UndoMenu.setForeground(new java.awt.Color(180, 180, 180));
        RedoButton.setForeground(new java.awt.Color(180, 180, 180));
        RedoMenu.setForeground(new java.awt.Color(180, 180, 180));
    }
}
