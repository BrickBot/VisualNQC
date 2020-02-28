/*
 * stopTaskIcon.java
 *
 * Created on 30 December 2005, 16:41
 *
 */

package funsoftware.ic.tsk;

import funsoftware.ic.obj.*;
import funsoftware.inter.*;
import funsoftware.ic.*;
import funsoftware.ic.others.*;
import funsoftware.ic.tsk.*;
import funsoftware.ic.ev.*;
import funsoftware.ic.func.*;
import funsoftware.consts.*;
import funsoftware.struct.*;
import funsoftware.tasks.*;

/**
 * This class represents an icon that indicates a currently running task is to be stopped.
 * @author Thomas Legowo
 */
public class stopTaskIcon extends objectIcon{
    
    // private variables
    private int num_id;    // id of the task to be stopped as given by the TaskWindow class
    private String name;   // name of this task as given by the user    
    // for algorithm representation, represent a stopTask icon with an identifier of 11
    private int identifier;
        
    // the task this icon is starting
    private task the_task;
    
    /** Creates a new instance of stopTaskIcon */
    public stopTaskIcon() {
    }
    
    /**
     * Creates a new instance of stopTaskIcon
     * @param filepath The filepath of the icon's image
     */
    public stopTaskIcon(String filepath) {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        identifier = 11;
        name = "";
        setImage();        
    }

    /**
     * Helps provide the transparent background
     * @param g Graphic
     */
    protected void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/task/stop/nameField.gif"));
        g.drawImage(ii.getImage(), 11, 13, this);
    }    
    
    /**
     * Alters the id of the task this icon is representing
     * @param i New task id
     */
    public void setNumId(int i)
    {
        num_id = i;
    }
    
    /**
     * Returns the id of the task this icon is representing
     * @return task id
     */
    public int getNumId()
    {
        return num_id;
    }
    
   /**
     * To set the task this icon is pointing to
     * @param tk The task this icon is pointing to
     */
    public void setTask(task tk)
    {
        the_task = tk;
        num_id = the_task.getNumId(); 
        name = the_task.getName();
    }    

   /**
     * To set the name of this icon
     * @param n The name
     */
    public void setName(String n)
    {
        name = n;
    }  
    
    /**
     * Returns this task icon's name
     * @return task name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns the task this icon is connected to
     * @return The task
     */
    public task getTask()
    {
        return the_task;
    }    
    
    /**
     * To set the image of this task icon
     */
    public void setImage()
    {
        removeAll();
        setLayout(new java.awt.GridBagLayout());                
        java.awt.GridBagConstraints gridBagConstraints;
        
        // creating the top panel
        javax.swing.ImageIcon nic;
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/task/stop/stopleft.gif"));
        PicButton pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(pic, gridBagConstraints);
        
        javax.swing.JPanel jp = new javax.swing.JPanel();
        jp.setPreferredSize(new java.awt.Dimension(28, 40));
        jp.setLayout(new java.awt.GridBagLayout());
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/task/stop/stoptop.gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(pic, gridBagConstraints);
        
        javax.swing.JLabel nameLabel = new javax.swing.JLabel(name);
        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        nameLabel.setPreferredSize(new java.awt.Dimension(28, 14));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jp.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jp.add(nameLabel, gridBagConstraints);
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/task/stop/stopbottom.gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jp.add(pic, gridBagConstraints);        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        add(jp, gridBagConstraints);
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/task/stop/stopright.gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        add(pic, gridBagConstraints);
        
        setPreferredSize(new java.awt.Dimension(40, 40));
    }

    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public stopTaskIcon Clone()
    {
        return new stopTaskIcon("/icons/task/stop/stopTask.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        stopTaskIcon newBTI = Clone();
        newBTI.setNumId(num_id);
        newBTI.setTask(the_task);
        newBTI.setName(name);
        newBTI.setImage();
        return newBTI;
    }
    
    /**
     * Get the help title of this stop task icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Stop Task Icon";
        return s;
    }
    
    /**
     * Get help message of this stop task icon
     * @return Help message
     */     
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nThe task started: ";        
        java.util.Vector<auxiliary> al = aux_list.Instance().getAuxiliaries();        
        s+=al.get(num_id).getName();
        
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */     
    public String getHelpDesc()
    {
        String s = "This icon stops a specified task.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */    
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/stopTaskIcon.gif"));
        return ii;
    }
    
    
    /**
     * Algorithm translation for the Translator object to create a text representation of this icon
     * @param id_separator To separate ids
     * @param id_att_separator To separate id with attribute
     * @param att_boundary_begin To start attributes
     * @param att_boundary_end To end attributes
     * @param att_value_connector To separate attributes and its values
     * @param att_separator To separate attributes
     * @return Translation
     */
    public String getTranslation(String id_separator, String id_att_separator, 
                                 String att_boundary_begin, String att_boundary_end, String att_value_connector, String att_separator)
    {
        String algo = new String();
        algo += "\n";  // to begin with
        algo += "object";
        algo += id_separator;
        algo += identifier;
        algo += id_att_separator;
        algo += att_boundary_begin;
        algo += "name"+att_value_connector;
        algo += name;
        algo += att_boundary_end;
        return algo;
    }
    
    /**
     * Returns a string format of the NQCCode representation of this object
     * @param indentation Indentation
     * @return The NQC Code
     */
    public String getNQCCode(String indentation)
    {
        String NQCCode = new String();     
        
        NQCCode += indentation+"// Stop a task \n";
        NQCCode += indentation+"stop ";
        java.util.Vector<auxiliary> al = aux_list.Instance().getAuxiliaries();        
        NQCCode+=al.get(num_id).getName();
        NQCCode += ";\n\n";
        return NQCCode;
    }
    
    /**
     * Set its own attributes given a string from a text representation of it
     * @param att Attributes
     * @param att_value_connector Attribute connector
     * @param att_separator Attribute separator
     */
    public void setAttributes(String att, String att_value_connector, String att_separator)
    {
        String[] sp = att.split(att_separator);
        String[] sp2 = new String[2];
        
        // do the first one --> task id
        sp2 = sp[0].split(att_value_connector);
        name = sp2[1];
    }
    
    
    /**
     * Programming window where this function icon is on
     * @return Programming window
     */
    public ProgWindow getIndividualProgWindow()
    {
        Icon parent = new Icon();
        ProgWindow ret_val = new ProgWindow();
        while(true)
        {
            if(parent instanceof startIcon)
            {
                if(parent instanceof startFunctionIcon)
                {
                    ret_val = ((startFunctionIcon)parent).getProgWindow();
                }
                else if(parent instanceof startTaskIcon)
                {
                    ret_val = ((startTaskIcon)parent).getProgWindow();
                }          
                else if(parent instanceof startEventIcon)
                {
                    ret_val = ((startEventIcon)parent).getProgWindow();
                }                 
                break;
            }
            else
            {
                parent = this.getParentIcon();
            }            
        }        
        return ret_val;
    }
    
    /**
     * Returns scroll pane of the individual programming window
     * @return Scroll Pane
     */
    public javax.swing.JScrollPane getIndividualScroller()
    {
        Icon parent = new Icon();
        javax.swing.JScrollPane ret_val = new javax.swing.JScrollPane();
        while(true)
        {
            if(parent instanceof startIcon)
            {
                ret_val = ((startIcon)parent).getScroller();
                break;
            }
            else
            {
                parent = this.getParentIcon();
            }            
        }        
        return ret_val;
    }
}
