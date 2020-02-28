/*
 * TaskWindow.java
 *
 * Created on 24 September 2005, 22:02
 *
 */

package funsoftware.tasks;

import funsoftware.ic.*;
import funsoftware.ic.tsk.*;
import funsoftware.inter.*;
import funsoftware.consts.*;
import funsoftware.pallette.*;
import funsoftware.st.*;
import funsoftware.var.*;
import funsoftware.events.*;

/**
 * This class manages the tasks within it. 
 * It also acts as a panel, therefore this class is a subclass of JPanel class
 * @author Thomas Legowo 
 */
public class TaskWindow extends javax.swing.JPanel implements java.awt.event.ActionListener{
    
    // the private variables    
    private java.util.Vector<task> Tasks;  // the members, each being a task icon
    private java.awt.Dimension area;   // the area of the panel
    
    // the four temporary variables needed for the construction of a ProgWindow
    private TitlePanelLabel jDirectionsLabel;
    
    private javax.swing.JFrame jFrame;
    
    // the tabbed window manager
    private TabbedWindowManager twm;    
    
    /**
     * Creates a new instance of TaskWindow
     * @param jf The frame
     * @param jPanel13 Panel on top of programming window
     * @param njDirectionsLabel Real time direction palette
     * @param evw The event window
     */
    public TaskWindow(javax.swing.JFrame jf, javax.swing.JPanel jPanel13,
                        TitlePanelLabel njDirectionsLabel, VarWindow varw, monitorEventWindow evw) 
    {
        super();
        super.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
        jFrame = jf; 
        Tasks = new java.util.Vector<task>();
        twm = TabbedWindowManager.Instance(jPanel13, varw, evw);
        jDirectionsLabel = njDirectionsLabel;
        setBackground(new java.awt.Color(247, 247, 247));
    }
    
    /**
     * To paint this task window
     * @param g Graphics
     */
    protected void paintComponent(java.awt.Graphics g) 
    {
        // update the radio buttons texts
        int lim = getComponentCount(); 
        for(int i=0; i<lim; i++)
        {
            javax.swing.JRadioButton jrb = (javax.swing.JRadioButton)getComponent(i);
            jrb.setText(Tasks.get(i).getName()); 
        }        
        super.paintComponent(g);
    }
    
    /**
     * Reset this window, occurs if user wanna start fresh, e.g. from the menu bar, File --> New
     */
    public void reset()
    {
        removeAll();
        Tasks = new java.util.Vector<task>();
        
        refreshContainer();
    }
    
    /**
     * Insert a new task radio button onto this task window
     * @param tsk A task icon
     */
    public void insertTask(task tsk)
    {
        javax.swing.JRadioButton tskrb = new javax.swing.JRadioButton(tsk.getName()); 
        tskrb.setBackground(new java.awt.Color(247, 247, 247));
        tskrb.setActionCommand(Integer.toString(tsk.getNumId()));  
        tskrb.addActionListener(this);
        Tasks.addElement(tsk);
        radiobuttons.Instance().getButtonGroup().add(tskrb);
        add(tskrb);
        
        // to set up the component pop up menu
        javax.swing.JPopupMenu jpm = new javax.swing.JPopupMenu();
        javax.swing.JMenuItem jm = new javax.swing.JMenuItem();
        jm = new javax.swing.JMenuItem("Delete Task");
        final int num_id = tsk.getNumId();

        if(num_id != 1)  // the task Main has id = 1, cannot delete nor rename it
        {
            jm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    deleteTaskPerformed(num_id);
                }
            });
            jpm.add(jm);
            jm = new javax.swing.JMenuItem("Rename Task");
            jm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                        renameTaskPerformed(num_id);
                }
            });
            jpm.add(jm);
            tskrb.setComponentPopupMenu(jpm); 
        }
        javax.swing.JPanel jp = tsk.setUpAllIcons(jDirectionsLabel, this);
        twm.addPanel(jp, tsk.getNumId());
        
        refreshContainer();
    }

    // to edit the task's attributes, being its name
    private void renameTaskPerformed(int num_id)
    {
        TaskNameDialog tsk_dialog = new TaskNameDialog(jFrame, aux_list.Instance().getAuxiliaries().get(num_id).getName(), 1);
        tsk_dialog.pack();
        tsk_dialog.setLocationRelativeTo(jFrame);
        tsk_dialog.setVisible(true);    
        
        String tmpName = aux_list.Instance().getAuxiliaries().get(num_id).getName();
        String taskName = tsk_dialog.getUserInput();
        if(taskName.compareTo("") != 0 && taskName.compareTo(tmpName) != 0)  // not the current value and not an empty string
        {
            // change the names for this task and all of its associated task begin and stop icons   
            task tmp = (task)aux_list.Instance().getAuxiliaries().get(num_id);
            tmp.setName(taskName);
            
            java.util.Vector<Icon> ics = (icons_list.Instance()).getIcons();
            for(int i=1; i<ics.size(); i++)   // check for icons that has the same numid with this one,
                                              // if so, update its name
            {
                Icon tmp_icon = ics.get(i);
                if(tmp_icon != null)
                {
                    if(tmp_icon instanceof beginTaskIcon)
                    {
                        beginTaskIcon tmp_sub = (beginTaskIcon)tmp_icon;
                        if(tmp_sub.getNumId() == num_id)
                        {
                            tmp_sub.setName(taskName);
                            tmp_sub.setImage();
                            tmp_sub.repaint();
                            tmp_sub.validate();
                        }
                    }
                    else if(tmp_icon instanceof stopTaskIcon)
                    {
                        stopTaskIcon tmp_sub = (stopTaskIcon)tmp_icon;
                        if(tmp_sub.getNumId() == num_id)
                        {
                            tmp_sub.setName(taskName);
                            tmp_sub.setImage();
                            tmp_sub.repaint();
                            tmp_sub.validate();
                        }                        
                    }
                }
            }
            
            UndoRedo.update_state();
            
            refreshContainer();
        }
    }    
    
    // the action handler for the radio button's pop up menu
    private void deleteTaskPerformed(int num_id)
    {
        int to_delete = 0;  // all is good for delete
        // also delete all related icons
        icons_list icon_list = icons_list.Instance();
        java.util.Vector<Icon> ics = icon_list.getIcons();
        for(int i=1; i<ics.size(); i++)
        {
            Icon tmp_icon = ics.get(i);
            // unlike function, in tasks, we gotta clean up any start task icons as well as the stop task icons.
            
            if(tmp_icon != null && tmp_icon instanceof beginTaskIcon)
            {
                beginTaskIcon func = (beginTaskIcon)tmp_icon;
                if(func.getNumId() == num_id)
                {
                    // get its panel
                    /*ProgWindow jp = func.getIndividualProgWindow();
                    javax.swing.JScrollPane jsp = func.getIndividualScroller();
                    jp.deleteFunctionIcon(tmp_icon, jsp);*/
                    javax.swing.JOptionPane.showMessageDialog(null,
                                    "One or more task calls to this task exist.\nPlease delete them first.",
                                    "Delete Forbidden",
                                    javax.swing.JOptionPane.WARNING_MESSAGE);                
                    to_delete = 1;
                    break;
                }                
            }
            else if(tmp_icon != null && tmp_icon instanceof stopTaskIcon)
            {
                stopTaskIcon func = (stopTaskIcon)tmp_icon;
                if(func.getNumId() == num_id)
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                                    "One or more task stops to this task exist.\nPlease delete them first.",
                                    "Delete Forbidden",
                                    javax.swing.JOptionPane.WARNING_MESSAGE);
                    to_delete = 1;
                    break; 
                }               
            } 
        }
                
        if(to_delete == 0)
        {             
            aux_list func_list = aux_list.Instance();
            func_list.delAuxiliary(num_id);

            if(isTaskSelected(num_id) == true)
            {
                twm.displayPanel("1");
                ((javax.swing.JRadioButton)getComponent(0)).setSelected(true);                
            }
        
            deleteTask(num_id);   
            
            UndoRedo.update_state();            
        }
    }
    
    // to determine whether the to-be-deleted task is the currently-selected task
    private Boolean isTaskSelected(int id_del)
    {
        String rad_butt_command = new String();
        java.util.Enumeration<javax.swing.AbstractButton> enum_ab = radiobuttons.Instance().getButtonGroup().getElements();
        javax.swing.JRadioButton jrb;
        int sel_id = 1;
        while(enum_ab.hasMoreElements() == true)
        {
            jrb = (javax.swing.JRadioButton)enum_ab.nextElement();
            if(jrb.isSelected() == true)
            {
                rad_butt_command = jrb.getActionCommand();
                sel_id = Integer.parseInt(rad_butt_command);
                break;
            }
        }
        
        if(sel_id == id_del)
        {
            return true;
        }        
        return false;
    }    
    
    /**
     * Delete a task button and all task icons representing that task on this task window based on the given num_id
     * @param num_id Task id
     */
    public void deleteTask(int num_id)
    {
        int del_pos = 0;
        for(int i=0; i<Tasks.size(); i++)
        {
            task tmp = Tasks.get(i);
            if(num_id == tmp.getNumId())
            {
                del_pos = i;
                break;
            }
        }
        Tasks.removeElementAt(del_pos);
            
        radiobuttons.Instance().getButtonGroup().remove((javax.swing.JRadioButton)getComponent(del_pos));                    
        remove(del_pos);
        twm.delPanel(num_id);
        
        refreshContainer();
    }
    
    /**
     * Refresh the UI for this component and its parent
     */
    public void refreshContainer()
    {
        this.repaint();
        this.revalidate();
        
        if (this.getParent() != null)
        {
            this.getParent().revalidate();
        }
    }
    
    /**
     * Return the window manager that manages the card laid out panels as controlled by the radio button group
     * @return Window manager
     */
    public TabbedWindowManager getTabbedWinMan()
    {
        return twm;
    }
    
    /**
     * Return the radio button group
     * @return Radio button group
     */
    public javax.swing.ButtonGroup getButtonGroup()
    {
        return radiobuttons.Instance().getButtonGroup();
    }
   
    
    // ---------------------------------
    
    /**
     * Listens to the radio buttons.
     * @param e ActionEvent
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
        twm.displayPanel(e.getActionCommand());
    }
}
