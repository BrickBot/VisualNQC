/*
 * FunctionWindow.java
 *
 * Created on 24 September 2005, 22:02
 *
 */

package funsoftware.functs;

import funsoftware.ic.*;
import funsoftware.ic.func.*;
import funsoftware.inter.*;
import funsoftware.consts.*;
import funsoftware.pallette.*;
import funsoftware.st.*;
import funsoftware.inter.TabbedWindowManager;
import funsoftware.var.*;
import funsoftware.events.*;

/**
 * This class manages the functions within it. 
 * It also acts as a panel, therefore this class is a subclass of JPanel class
 * @author Thomas Legowo
 * 
 */
public class FunctionWindow extends javax.swing.JPanel implements java.awt.event.ActionListener{
   
    // the private variables    
    private java.util.Vector<function> Functions;  // the members, each being a function icon
    private java.awt.Dimension area;   // the area of the panel
    
    // the four temporary variables needed for the construction of a ProgWindow
    private TitlePanelLabel jDirectionsLabel;
    
    private javax.swing.JFrame jFrame;
    
    // the tabbed window manager
    private TabbedWindowManager twm;    
    
    /**
     * Creates a new instance of FunctionWindow
     * @param jf The frame
     * @param jPanel13 Panel on top of programming window
     * @param njDirectionsLabel Real time direction palette
     * @param varw Variable window
     * @param evw Event window
     */
    public FunctionWindow(javax.swing.JFrame jf, javax.swing.JPanel jPanel13,
                          TitlePanelLabel njDirectionsLabel, VarWindow varw, monitorEventWindow evw) 
    {
        super();
        super.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
        jFrame = jf;
        Functions = new java.util.Vector<function>();
        twm = TabbedWindowManager.Instance(jPanel13, varw, evw);
        jDirectionsLabel = njDirectionsLabel;
        setBackground(new java.awt.Color(247, 247, 247));
    }
    
    /**
     * To paint this function window
     * @param g Graphics
     */
    protected void paintComponent(java.awt.Graphics g) 
    {
        // update the radio buttons texts
        int lim = getComponentCount(); 
        for(int i=0; i<lim; i++)  // skip the first one
        {
            javax.swing.JRadioButton jrb = (javax.swing.JRadioButton)getComponent(i);
            jrb.setText(Functions.get(i).getName());
        }        
        super.paintComponent(g);
    }
    
    /**
     * Reset this window, occurs if user wanna start fresh, e.g. from the menu bar, File --> New
     */
    public void reset()
    {
        removeAll();
        Functions = new java.util.Vector<function>();
        
        refreshContainer();
    }
    
    /**
     * Insert a new function button onto this function window
     * @param func A function icon
     */
    public void insertFunction(function func)
    {
        javax.swing.JRadioButton funcby = new javax.swing.JRadioButton(func.getName());
        funcby.setBackground(new java.awt.Color(247, 247, 247));
        funcby.setActionCommand(Integer.toString(func.getNumId()));
        funcby.addActionListener(this);
        Functions.addElement(func);
        radiobuttons.Instance().getButtonGroup().add(funcby);
        add(funcby);   
        
        // to set up the component pop up menu
        javax.swing.JPopupMenu jpm = new javax.swing.JPopupMenu();
        javax.swing.JMenuItem jm = new javax.swing.JMenuItem();
        jm = new javax.swing.JMenuItem("Delete Function");
        final int num_id = func.getNumId();
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFunctionPerformed(num_id);
            }
        });
        jpm.add(jm);
        
        jm = new javax.swing.JMenuItem("Rename Function");
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameFunctionPerformed(num_id);
            }
        });
        jpm.add(jm);
        
        jm = new javax.swing.JMenuItem("View Level");
        jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewLevelPerformed(num_id);
            }
        });
        jpm.add(jm);
        
        funcby.setComponentPopupMenu(jpm);        
        
        javax.swing.JPanel jp = func.setUpAllIcons(jDirectionsLabel, this);
        twm.addPanel(jp, func.getNumId());

        refreshContainer();
    }
 
    // the action handler for the radio button's pop up menu for renaming functions
    private void renameFunctionPerformed(int num_id)
    {
        FunctionNameDialog func_dialog = new FunctionNameDialog(jFrame, aux_list.Instance().getAuxiliaries().get(num_id).getName(), 1);
        func_dialog.pack();
        func_dialog.setLocationRelativeTo(jFrame);
        func_dialog.setVisible(true);   

        String tmpName = aux_list.Instance().getAuxiliaries().get(num_id).getName();        
        String[] functionName = func_dialog.getUserInput();
        if(functionName != null && functionName[0].compareTo("") != 0 && functionName[0].compareTo(tmpName) != 0)
        {
            // change the names for this function and all of its associated function begin and stop icons   
            function tmp = (function)aux_list.Instance().getAuxiliaries().get(num_id);
            tmp.setName(functionName[0]);
            
            java.util.Vector<Icon> ics = (icons_list.Instance()).getIcons();
            for(int i=1; i<ics.size(); i++)   // check for icons that has the same numid with this one,
                                              // if so, update its name
            {
                Icon tmp_icon = ics.get(i);
                if(tmp_icon != null && tmp_icon instanceof functionIcon)
                {
                    functionIcon tmp_sub = (functionIcon)tmp_icon;
                    if(tmp_sub.getNumId() == num_id)
                    {
                        tmp_sub.setName(functionName[0]);
                        tmp_sub.setImage();
                        tmp_sub.repaint();tmp_sub.validate();
                    }
                }
            }
                        
            // set the name for the drop down menu
            FunctionCombo fcombo = FunctionCombo.Instance();
            int total = fcombo.getItemCount();
            for(int i=1; i<total; i++)
            {
                javax.swing.JMenuItem mi = (javax.swing.JMenuItem)fcombo.getItemAt(i);
                if(mi != null)
                {
                    int code = Integer.parseInt(mi.getActionCommand());
                    if(code == num_id)
                    {
                        mi.setText(functionName[0]);
                        break;
                    }
                }
            }
            
            UndoRedo.update_state();
            
            refreshContainer();
        }    
    }    
    
    // the action handler for the radio button's pop up menu
    private void deleteFunctionPerformed(int num_id)
    {
        int to_delete = 0; // 0 means good to delete
        
        // also delete all related icons
        icons_list icon_list = icons_list.Instance();
        java.util.Vector<Icon> ics = icon_list.getIcons();
        for(int i=1; i<ics.size(); i++)
        {
            Icon tmp_icon = ics.get(i);
            if(tmp_icon != null && tmp_icon instanceof functionIcon)
            {
                functionIcon func = (functionIcon)tmp_icon;
                if(func.getNumId() == num_id)
                {
                    // get its panel
                    javax.swing.JOptionPane.showMessageDialog(null,
                                    "One or more calls to this function exist.\nPlease delete them first.",
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

            int del_pos = 0;
            FunctionCombo fcombo = FunctionCombo.Instance();
            int total = fcombo.getItemCount();
            for(int i=1; i<total; i++)
            {
                javax.swing.JMenuItem mi = (javax.swing.JMenuItem)fcombo.getItemAt(i);
                if(mi != null)
                {
                    int code = Integer.parseInt(mi.getActionCommand());
                    if(code == num_id)
                    {
                        del_pos = i;
                        break;
                    }
                }
            }

            fcombo.removeItemAt(del_pos);    

            if(isFunctionSelected(num_id) == true)
            {
                twm.displayPanel("1");
                java.util.Enumeration<javax.swing.AbstractButton> enum_ab = radiobuttons.Instance().getButtonGroup().getElements();
                while(enum_ab.hasMoreElements() == true)
                {
                    javax.swing.JRadioButton jrb = (javax.swing.JRadioButton)enum_ab.nextElement();
                    if(jrb.getActionCommand().compareTo("1") == 0)
                    {
                        jrb.setSelected(true); 
                        break;
                    }
                } 
            } 
                    
            deleteFunction(num_id); 
            
            UndoRedo.update_state();            
        }        
    }
    
    // the action handler for the radio button's pop up menu
    private void viewLevelPerformed(int num_id)
    {
        function tmp = (function)aux_list.Instance().getAuxiliaries().get(num_id);
        int level = tmp.getLevel();
        javax.swing.JOptionPane.showMessageDialog(null,
                "Level: " + level,
                "Function Level",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }    
    
    // to determine whether the to-be-deleted function is the currently-selected function
    private Boolean isFunctionSelected(int id_del)
    {
        String rad_butt_command = new String();
        java.util.Enumeration<javax.swing.AbstractButton> enum_ab = radiobuttons.Instance().getButtonGroup().getElements();
        javax.swing.JRadioButton jrb;
        int sel_id = 1;
        while(enum_ab.hasMoreElements())
        {
            jrb = (javax.swing.JRadioButton)enum_ab.nextElement();
            if(jrb.isSelected() == true)
            {
                rad_butt_command = jrb.getActionCommand();
                sel_id = Integer.parseInt(rad_butt_command);
                break;
            }
        }
        
        return (sel_id == id_del);
    }
    
    
    /**
     * Delete a function button and all function icons representing that function on this function window based on the given num_id
     * @param num_id Function id
     */
    public void deleteFunction(int num_id)
    {
        int del_pos = 0;
        for(int i=0; i<Functions.size(); i++)
        {
            function tmp = Functions.get(i);
            if(num_id == tmp.getNumId())
            {
                del_pos = i;
                break;
            }
        }
        Functions.removeElementAt(del_pos);
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
