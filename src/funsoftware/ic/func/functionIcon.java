/*
 * functionIcon.java
 *
 * Created on 28 December 2005, 14:40
 */

package funsoftware.ic.func;

import funsoftware.ic.*;
import funsoftware.ic.obj.*;
import funsoftware.st.*;
import funsoftware.inter.*;
import funsoftware.ic.tsk.*;
import funsoftware.ic.ev.*;
import funsoftware.ic.others.*;
import funsoftware.consts.*;
import funsoftware.functs.*;
import funsoftware.var.*;
import funsoftware.struct.*;

/**
 * This class caters for icons that represent a function
 * @author Thomas Legowo
 */
public class functionIcon extends objectIcon{
    
    private String name;   // name of this function as given by the user
    private int num_id;    // id as given by the FunctionWindow class
    
    private java.util.Vector<Operand> arguments;  // values to be passed on to this function call, can either be a constant or a variable
    
    // the function this icon is representing
    private function the_function;
    
    /** Creates a new instance of functionIcon */
    public functionIcon() {
    }
    
    /**
     * Creates a new instance of functionIcon
     * @param filepath Source file of this icon's image
     */
    public functionIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        num_id = 0;   // not assigned originally, this id will be assigned by the function_list (called from ProgWindow class)
        name = "";
        the_function = new function();
        arguments = new java.util.Vector<Operand>();
        setImage();
    }
    
    /**
     * Helps provide the transparent background
     * @param g Graphics
     */
    protected void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/function/nameField.gif"));
        g.drawImage(ii.getImage(), 2, 18, this);
    } 
    
    /**
     * Alters the id of the function this icon is representing
     * @param i New function id
     */
    public void setNumId(int i)
    {
        num_id = i;
    }
    
    /**
     * Returns the id of the function this icon is representing
     * @return function id
     */
    public int getNumId()
    {
        return num_id;
    }
    
    /**
     * Sets the name of this function
     * @param n New function name 
     */
    public void setName(String n)
    {
        name = n;
    }
    /**
     * Returns this function icon's name
     * @return function name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * To set the function this function icon is pointing to
     * @param fu The function this function icon is pointing to
     */
    public void setFunction(function fu)
    {
        the_function = fu;
        num_id = the_function.getNumId(); 
        name = the_function.getName();
    }
    
    /**
     * Returns this function icon's function
     * @return function name
     */
    public function getFunction()
    {
        return the_function;
    }    
    
    // for arguments in function call
    
    /**
     * Returns this function icon's arguments
     * @return function arguments
     */
    public java.util.Vector<Operand> getArguments()
    {
        return arguments;
    }
    
    /**
     * To set the arguments this function icon has
     * @param op The list of operands (arguments)
     */
    public void setArguments(java.util.Vector<Operand> op)
    {
        arguments = op;
    }    
    /**
     * To set the image of this function icon
     */
    public void setImage()
    {
        removeAll();  // remove all first
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());
        javax.swing.ImageIcon nic;
        PicButton pic;
        javax.swing.JPanel jp = new javax.swing.JPanel();
        javax.swing.JPanel jp2 = new javax.swing.JPanel();
        
        // start the top most panel
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/function/functiontop.gif"));
        pic = new PicButton(nic);       
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(pic);
        
        jp = new javax.swing.JPanel();
        jp.setPreferredSize(new java.awt.Dimension(40, 16));
        jp.setLayout(new java.awt.GridBagLayout());
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/function/bottomleft.gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(pic, gridBagConstraints);
        
        
        javax.swing.JLabel nameLabel = new javax.swing.JLabel(name);
        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        nameLabel.setPreferredSize(new java.awt.Dimension(36, 16));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jp.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jp.add(nameLabel, gridBagConstraints);
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/function/bottomright.gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jp.add(pic, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(jp, gridBagConstraints);
        
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/function/functionbottom.gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(pic, gridBagConstraints);
        
        setPreferredSize(new java.awt.Dimension(40, 40));
    }        

    
    /**
     * Get the help title of this function icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Function Icon";
        return s;
    }
    
    /**
     * Get help message of this function icon
     * @return Help message
     */     
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nName: ";
        s+=name;
        if(arguments.size() > 0)  // there exist at least one argument
        {
            s+="\nArguments:";
            for(int i=0; i<arguments.size(); i++)
            {
                Operand O = arguments.get(i);
                if(O instanceof constant)
                {
                    constant c = (constant)O;
                    s+="\n  Constant: "+c.getValue();
                }
                else if(O instanceof variable)
                {
                    variable v = (variable)O;
                    s+="\n  Variable: "+v.getName();                
                }
            }            
        }   
        return s;
    }

    /**
     * Get the description of this icon
     * @return Help Description
     */     
    public String getHelpDesc()
    {
        String s = "This icon represent a call to a function.\nIt contains another set of icons";
        return s;
    }

    /**
     * Get help picture of this icon
     * @return Help picture
     */    
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/functionIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public functionIcon Clone()
    {
        return new functionIcon("/icons/function/function.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        functionIcon newBTI = Clone();
        newBTI.setNumId(num_id);
        newBTI.setFunction(the_function);
        newBTI.setName(name);
        newBTI.setArguments(arguments);
        newBTI.setImage();
        return newBTI;
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
        algo += "function";
        algo += id_separator;
        algo += num_id;        
        algo += id_separator;
        algo += getId();
        algo += id_att_separator;
        algo += att_boundary_begin;
        algo += "name"+att_value_connector;
        algo += name;
        if(arguments.size() > 0)  // there exist at least one argument
        {
            for(int i=0; i<arguments.size(); i++)
            {
                algo += att_separator;
                algo += "argument"+att_value_connector;     
                if(arguments.get(i) instanceof constant)
                {
                    constant c = (constant)arguments.get(i);
                    algo += "c"+id_att_separator;
                    algo += att_boundary_begin;
                    algo += "value"+att_value_connector;
                    algo += c.getValue();
                    algo += att_boundary_end;
                }
                else if(arguments.get(i) instanceof variable)
                {
                    variable v = (variable)arguments.get(i);
                    algo += "v"+id_att_separator;
                    algo += att_boundary_begin;
                    algo += "name"+att_value_connector;
                    algo += v.getName();
                    algo += att_boundary_end;
                }
            }
        }
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
        NQCCode += indentation+"// call a function \n";
        NQCCode += indentation+name;
        if(arguments.size() == 0)
        {
            NQCCode += "();\n\n";
        }
        else
        {
            NQCCode += "(";
            for(int i=0; i<arguments.size(); i++)
            {
                if(arguments.get(i) instanceof variable)
                {
                    variable v = (variable)arguments.get(i);
                    NQCCode += v.getName();
                }
                else if(arguments.get(i) instanceof constant)
                {
                    constant c = (constant)arguments.get(i);
                    NQCCode += c.getValue();
                }                
                if(i != arguments.size()-1)  // not the last one
                {
                    NQCCode += ", ";    
                }                
            }
            NQCCode += ");\n\n";
        }
        return NQCCode;
    }
    
    /**
     * Set its own attributes given a string from a text representation of it
     * @param id_att_separator The id and attribute separator tag
     * @param att_boundary_end The end of the argument list tag
     * @param a The auxiliary
     * @param att Attributes
     * @param att_value_connector Attribute connector
     * @param att_separator Attribute separator
     */
    public void setAttributes(String att, String att_value_connector, String att_separator, String id_att_separator, String att_boundary_end, auxiliary a)
    {
        String[] sp = att.split(att_separator);
        String[] sp2 = new String[2];
        String tmp = new String();
        
        // do the first one --> name
        sp2 = sp[0].split(att_value_connector);
        name = sp2[1];

        // do the next ones
        if(att.indexOf(att_separator) != -1)  // there are arguments
        {
            att = att.substring(att.indexOf(att_separator)+1);
            while(att.compareTo("") != 0) // more arguments to go
            {
                sp = att.split(id_att_separator);
                sp2 = sp[0].split(att_value_connector);
                if(sp2[1].compareTo("c") == 0)
                {
                    att = att.substring(att.indexOf(id_att_separator)+2);
                    sp = att.split(att_value_connector);
                    tmp = sp[1].substring(0, sp[1].indexOf(att_boundary_end));
                    int value = Integer.parseInt(tmp);
                    constant c = new constant();
                    c.setValue(value);
                    arguments.addElement(c);
                }
                else if(sp2[1].compareTo("v") == 0)
                {
                    att = att.substring(att.indexOf(id_att_separator)+2);
                    sp = att.split(att_value_connector);
                    tmp = sp[1].substring(0, sp[1].indexOf(att_boundary_end));
                    variable v = a.getVariable(tmp);
                    arguments.addElement(v);                    
                }     
                                    
                if(att.indexOf(att_separator) != -1)
                {
                    att = att.substring(att.indexOf(att_separator)+1);    
                }
                else
                {
                    break;
                }
            }
        }        
        // the num id of the function this icon is associated to will be set later
    }    
    
   // for this function's icon itself
    /**
     * Programming window where this function icon is on
     * @return Programming window
     */
    public ProgWindow getIndividualProgWindow()
    {
        Icon parent = this.getParentIcon();
        ProgWindow ret_val = new ProgWindow();
        while(true)
        {
            if(parent instanceof startFunctionIcon)
            {
                    ret_val = ((startFunctionIcon)parent).getProgWindow();
                    break;
            }
            else if(parent instanceof startTaskIcon)
            {
                    ret_val = ((startTaskIcon)parent).getProgWindow();
                    break;
            }  
            else if(parent instanceof startEventIcon)
            {
                    ret_val = ((startEventIcon)parent).getProgWindow();
                    break;
            } 
            else
            {
                parent = parent.getParentIcon();
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
        Icon parent = this.getParentIcon();
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
                parent = parent.getParentIcon();
            }           
        }
        
        return ret_val;
    }
}
