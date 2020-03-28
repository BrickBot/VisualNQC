/*
 * LocalVarWindow.java
 *
 * Created on 15 January 2006, 22:50
 *
 */

package funsoftware.var;

import funsoftware.consts.*;
import funsoftware.ic.var.ArithOpIcon;
import funsoftware.ic.*;
import funsoftware.struct.*;
import funsoftware.st.*;
import funsoftware.functs.*;
import funsoftware.tasks.*;
import funsoftware.events.*;

/**
 * To display all of the local variables.
 * @author Thomas Legowo
 */
    
public class VarWindow extends javax.swing.JPanel{
    
    // the private variables    
    private java.util.Vector<variable> GlobalVars;  // the members, each being a variable
    private java.util.Vector<variable> LocalVars;   // the members, each being a variable
    
    // the temporary variables needed for the construction of a ProgWindow
    
    private javax.swing.JFrame jframe;
    
    private javax.swing.JPanel GlobalPane;
    private javax.swing.JPanel LocalPane;
    private javax.swing.JSeparator[] lines = new javax.swing.JSeparator[2];
    
    private auxiliary current_aux;
    
    /**
     * Creates a new instance of VarWindow
     * @param frame The frame
     */
    public VarWindow(javax.swing.JFrame frame) 
    {
        super();
        super.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
        jframe = frame;
        GlobalVars = new java.util.Vector<variable>();
        LocalVars = new java.util.Vector<variable>();
        GlobalPane = new javax.swing.JPanel();
        GlobalPane.setLayout(new javax.swing.BoxLayout(GlobalPane, javax.swing.BoxLayout.PAGE_AXIS));
        LocalPane = new javax.swing.JPanel();
        LocalPane.setLayout(new javax.swing.BoxLayout(LocalPane, javax.swing.BoxLayout.PAGE_AXIS));

        GlobalPane.setOpaque(false);
        LocalPane.setOpaque(false);
        
        for (int i = 0; i < lines.length; i++)
        {
            lines[i] = new javax.swing.JSeparator();
            lines[i].setForeground(new java.awt.Color(0, 0, 0));
            lines[i].setAlignmentX(0.5f);
            lines[i].setAlignmentY(0.5f);
            lines[i].setPreferredSize(new java.awt.Dimension(0, 1));
            lines[i].setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 1));
        }
        
        // add the initial 3 input sensors variables
        for (int i = 1; i <= 3; i++)
        {
            variable sensor = new variable();
            sensor.setName("SENSOR_" + i);
            sensor.setType(0);
            var_list.Instance().addVariable(sensor);
            javax.swing.JLabel varlb = new javax.swing.JLabel("SENSOR_" + i);
            
            add(varlb);
        }
        
        add(javax.swing.Box.createVerticalStrut(2));
        add(lines[0]);
        add(javax.swing.Box.createVerticalStrut(2));
        
        add(GlobalPane);
  
        add(javax.swing.Box.createVerticalStrut(2));
        add(lines[1]);         
        add(javax.swing.Box.createVerticalStrut(2));
        
        add(LocalPane);
        
        setBackground(new java.awt.Color(247, 247, 247));
    }
    
    /**
     * To paint this task window
     * @param g Graphics
     */
    protected void paintComponent(java.awt.Graphics g) 
    {
        // update the radio buttons texts
        int lim = GlobalPane.getComponentCount(); 
        for(int i=0; i<lim; i++)
        {
            javax.swing.JLabel jlb = (javax.swing.JLabel)GlobalPane.getComponent(i);
            String name = GlobalVars.get(i).getName();
            jlb.setText(name);
        }  
        
        lim = LocalPane.getComponentCount(); 
        for(int i=0; i<lim; i++)
        {
            javax.swing.JLabel jlb = (javax.swing.JLabel)LocalPane.getComponent(i);
            String name = LocalVars.get(i).getName();
            jlb.setText(name); 
        }                
        super.paintComponent(g); 
    }
    
    /**
     * Reset this window, occurs if user wanna start fresh, e.g. from the menu bar, File → New
     */
    public void reset()
    {
        GlobalPane.removeAll();
        LocalPane.removeAll();
        LocalVars = new java.util.Vector<variable>();
        GlobalVars = new java.util.Vector<variable>();
        
        variable sensor = new variable();
        sensor.setName("SENSOR_1");
        sensor.setType(0);
        var_list.Instance().addVariable(sensor);   
        sensor = new variable();
        sensor.setName("SENSOR_2");
        sensor.setType(0);
        var_list.Instance().addVariable(sensor); 
        sensor = new variable();
        sensor.setName("SENSOR_3");
        sensor.setType(0);
        var_list.Instance().addVariable(sensor); 
        
        refreshContainer();
    }
    
    /**
     * Insert a new variable label onto this variable window
     * @param var The variable to be inserted
     */
    public void insertVar(variable var)
    {
        javax.swing.JLabel varlb = new javax.swing.JLabel();
        varlb.setBackground(new java.awt.Color(247, 247, 247));
        String name = var.getName();
        
        if(var.getType() == 0)   // global variables
        {
            GlobalVars.addElement(var);
            varlb.setText(name);
            GlobalPane.add(varlb); 
        }
        else if(var.getType() == 1)  // local variables
        {
            LocalVars.addElement(var);                        
            varlb.setText(name);        
            if(var.getAux().getNumId() == current_aux.getNumId())
            {
                LocalPane.add(varlb);
            }
        }
        
        // to set up the component pop up menu
        javax.swing.JPopupMenu jpm = new javax.swing.JPopupMenu();
        javax.swing.JMenuItem jm = new javax.swing.JMenuItem();

        if(name.compareTo("SENSOR_1") != 0 && name.compareTo("SENSOR_2") != 0 && name.compareTo("SENSOR_3") != 0 && var.getType() != 2)
        {
            jm = new javax.swing.JMenuItem("Edit Variable");
            final variable vari = var;
            jm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                    renameVarPerformed(evt, vari);
                }
            });
            jpm.add(jm);
            jm = new javax.swing.JMenuItem("Delete Variable");
            jm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                        deleteVarPerformed(evt, vari);
                    }
            });
            jpm.add(jm);
            varlb.setComponentPopupMenu(jpm); 
        }

//        update_panel();
        refreshContainer();
    }

    // to edit the variables by changing the name or its initial value
    private void renameVarPerformed(java.awt.event.ActionEvent evt, variable var)
    {
        DeclareVariableDialog dv_dialog = new DeclareVariableDialog(jframe, 1, var);        
        dv_dialog.pack();
        dv_dialog.setLocationRelativeTo(jframe);
        dv_dialog.setVisible(true);
        String[] ret_val = dv_dialog.getUserInput(); 
        
        if(ret_val[0].compareTo("") != 0)  // user didn't press the cancel button
        {
            String varName = ret_val[0];

            // change the name for this variable 
            var.setName(varName);
            var.setValue(Integer.parseInt(ret_val[3]));
            
            UndoRedo.update_state();
//            update_panel();
            
            refreshContainer();
        }    
        UndoRedo.update_state();
    }    
    
    // the action handler for the radio button's pop up menu
    private void deleteVarPerformed(java.awt.event.ActionEvent evt, variable var)
    {
        int del_pos = 0;
        int to_delete = 0;
        String name = var.getName();

        java.util.Vector<Icon> ics = icons_list.Instance().getIcons();
        for(int i=1; i<ics.size(); i++)
        {
            Icon tmp_icon = ics.get(i);
            if(tmp_icon != null && tmp_icon instanceof ArithOpIcon)
            {
                ArithOpIcon AOI = (ArithOpIcon)tmp_icon;
                ArithmeticOperation AO = AOI.getAO();
                if(AO.getVariable().getType() == var.getType() && AO.getVariable().getName().compareTo(var.getName()) == 0)
                {
                    to_delete = 1;
                    break;
                }
                else
                {
                    if(varCheckUsage(AO.getOperand1(), var) == 1)
                    {
                        to_delete = 1;
                        break;
                    }
                }
            }
            else
            {
                if(tmp_icon != null && tmp_icon.useAVariable(var) == true)
                {
                    to_delete = 1;
                    break;
                }
            }
        }    
        
        if(to_delete == 0)
        {
            if(var.getType() == 0)
            {
                for(int i=0; i<GlobalVars.size(); i++)
                {
                    variable v = GlobalVars.get(i);
                    if(name.compareTo(v.getName()) == 0)
                    {
                        del_pos = i;
                        break;
                    }
                }
                GlobalVars.removeElementAt(del_pos); 
                GlobalPane.remove(del_pos);
                var_list.Instance().delVariable(var.getNumId());
            }
            else if(var.getType() == 1)
            {
                for(int i = 0; i < LocalVars.size(); i++)
                {
                    variable v = LocalVars.get(i);
                    if(name.compareTo(v.getName()) == 0)
                    {
                        del_pos = i;
                        break;
                    }
                }
                LocalVars.removeElementAt(del_pos); 
                LocalPane.remove(del_pos);    
                current_aux.delVariable(var);
            }
//            update_panel();
            UndoRedo.update_state();
            
            refreshContainer();
        }   
        else
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                                    "One or more arithmetic operations or icons use this variable.\nPlease delete them first.",
                                    "Delete Forbidden",
                                    javax.swing.JOptionPane.WARNING_MESSAGE);             
        }
    }

    
    // to check whether a variable is in use in of the existing arithmetic operations
    private int varCheckUsage(Operand op, variable to_check)
    {
        int used = 0;  // not used if zero, used if 1
        
        if(op instanceof variable)
        {
            variable tmp = (variable)op;
            if(tmp.getName().compareTo(to_check.getName()) == 0 && tmp.getType() == to_check.getType())
                // a combination of names and types is unique
            {
                used = 1;
            }
        }
        else if(op instanceof NestedOperation)
        {
            NestedOperation NO = (NestedOperation)op;
            used = varCheckUsage(NO.getOperand1(), to_check);
            if(used == 0)
            {
                used = varCheckUsage(NO.getOperand2(), to_check);
            }
        }
        
        return used;
    }
    
    /**
     * To switch the display, more efficient to repaint the panel rather than have multiple panels
     * @param a The auxiliary that will affect the change in the variable window's display
     */
    public void switchDisplay(auxiliary a)
    {
        int index_del = 0;
        current_aux = a;
        LocalVars = new java.util.Vector<variable>();
        LocalPane.removeAll();
        
        java.util.Vector<variable> list_var;
        if(a instanceof monitorEvent)
        {
            monitorEvent evt_tmp = (monitorEvent)a;
            if(evt_tmp.getType().compareTo("Local") == 0)
            {
                list_var = evt_tmp.getAuxPoint().getVariables();    
            }
            else
            {
                list_var = new java.util.Vector<variable>();
            }            
        }
        else
        {
            list_var = a.getVariables(); 
        }
             
        for(int i=1; i<list_var.size(); i++)
        {
            if(list_var.get(i) != null)
            {
                LocalVars.addElement(list_var.get(i));
                javax.swing.JLabel varlb = new javax.swing.JLabel();                
                LocalPane.add(varlb);
                if (list_var.get(i).getType() != 2)
                {
                    varlb.setText(list_var.get(i).getName());
                    javax.swing.JPopupMenu jpm = new javax.swing.JPopupMenu();
                    javax.swing.JMenuItem jm = new javax.swing.JMenuItem();

                        jm = new javax.swing.JMenuItem("Edit Variable");
                        final variable var = list_var.get(i);
                        jm.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                renameVarPerformed(evt, var);
                            }
                        });
                        jpm.add(jm);
                        jm = new javax.swing.JMenuItem("Delete Variable");
                        jm.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    deleteVarPerformed(evt, var);
                            }
                        });
                        jpm.add(jm);
                    varlb.setComponentPopupMenu(jpm);                    
                }
                else
                {
                    varlb.setText(list_var.get(i).getName());
                }
            }             
        }     
//        update_panel();
        refreshContainer();
    }
    
    
    // to update the width of the panels
//    private void update_panel() {}
//    {
//        int widest = 0;
//        int height_global = 16*GlobalPane.getComponentCount();
//        int height_local = 16*LocalPane.getComponentCount();
//
//        int lim = GlobalPane.getComponentCount(); 
//        for(int i=0; i<lim; i++)
//        {
//            javax.swing.JLabel jlb = (javax.swing.JLabel)GlobalPane.getComponent(i);
//            if(jlb.getPreferredSize().getWidth() > widest)
//            {
//                widest = (int)jlb.getPreferredSize().getWidth();
//            }
//        }  
//        
//        lim = LocalPane.getComponentCount(); 
//        for(int i=0; i<lim; i++)
//        {
//            javax.swing.JLabel jlb = (javax.swing.JLabel)LocalPane.getComponent(i);  
//            if(jlb.getPreferredSize().getWidth() > widest)
//            {
//                widest = (int)jlb.getPreferredSize().getWidth();
//            }            
//        }   
//        
//        if(widest < 120)
//        {
//            widest = 120;
//        }
//        
//        LocalPane.setPreferredSize(new java.awt.Dimension(widest, height_local));
//        LocalPane.setMinimumSize(new java.awt.Dimension(widest, height_local));
//        GlobalPane.setPreferredSize(new java.awt.Dimension(widest, height_global));
//        GlobalPane.setMinimumSize(new java.awt.Dimension(widest, height_global));
////        line1.setPreferredSize(new java.awt.Dimension(widest, 1));
////        line2.setPreferredSize(new java.awt.Dimension(widest, 1));
//    }
    
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
}