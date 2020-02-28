/*
 * repeatIcon.java
 *
 * Created on 24 July 2005, 11:41
 */

package funsoftware.ic.loo;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.st.*;
import funsoftware.var.*;
import funsoftware.consts.*;

/**
 * This class is for the "Repeat" icon that repeats a certain loop by a specified number of times
 * @author Thomas Legowo
 * 
 */
public class repeatIcon extends loopIcon{
   
    private int num_loop;   // the number of loops this 'repeat' icon is to perform, default value is one   
    private variable var_num_loop;  // alternative to num_loop
    
    // for algorithm representation, this repeat icon has an identifier of 1
    private int identifier;
    
    /** Creates a new instance of repeatIcon */
    public repeatIcon() {
    }
    
    /**
     * Creates a new instance of repeatIcon
     * @param filepath Source file for this icon's image
     */
    public repeatIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        num_loop = 1;
        identifier = 1;
        var_num_loop = null;
        setImage();
    }
    
    /**
     * Gets the number of loop
     * @return Number of loop
     */
    public int getNumLoop()
    {
        return num_loop;
    }
    
    
    /**
     * Sets the number of loops of this "repeat icon"
     * @param nloop New number of loops
     */
    public void setNumLoop(int nloop)
    {
        num_loop = nloop;
    }
    
    /**
     * Sets the number of loops of this "repeat icon"
     * @param var The number of loop variable
     */
    public void setVarNumLoop(variable var)
    {
        var_num_loop = var;
    }
    
    /**
     * Sets the images and labels for this icon
     */
    public void setImage()
    {
        removeAll();
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());
        javax.swing.ImageIcon nic;
                
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/repeat/toploop.gif"));
        PicButton pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(pic, gridBagConstraints);
                
        javax.swing.JLabel repeatLabel;
        if(var_num_loop == null)
        {
            repeatLabel = new javax.swing.JLabel(Integer.toString(num_loop));
        }
        else
        {
            repeatLabel = new javax.swing.JLabel("V");
        }
        repeatLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        repeatLabel.setPreferredSize(new java.awt.Dimension(40, 16));
        repeatLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setOpaque(false);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(repeatLabel, gridBagConstraints);
        
        setPreferredSize(new java.awt.Dimension(40, 40));        
    }
    
    /**
     * Changes the number of loops
     * @param evt MouseEvent
     */
    public void changeNumLoops(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.JTextField textField;
            if(var_num_loop != null)
            {
                textField = new javax.swing.JTextField("v");
            }
            else
            {
                textField = new javax.swing.JTextField(Integer.toString(num_loop));
            }
            textField.setFont(new java.awt.Font("Tahoma", 1, 11));
            textField.setPreferredSize(new java.awt.Dimension(40,16));
            textField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            textField.setOpaque(false);

            java.awt.GridBagConstraints gridBagConstraints;
            javax.swing.ImageIcon nic;
            PicButton pic;

            removeAll();
            setLayout(new java.awt.GridBagLayout());

            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/repeat/toploop.gif"));
            pic = new PicButton(nic);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            add(pic, gridBagConstraints);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            add(textField, gridBagConstraints);
            repaint();
            validate();
            textField.requestFocusInWindow();

            textField.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        //stop edit mode (Enter-key)
                        stopEditMode();
                    }
            });            
        }
        else
        {
            javax.swing.JPopupMenu jpm = this.getComponentPopupMenu();
            jpm.setInvoker(this);
            jpm.pack();
            jpm.show(this, (int)evt.getPoint().getX(), (int)evt.getPoint().getY());
            jpm.setVisible(true);
        } 
        
    }
    
    // method called by changeNumLoops
    /**
      * Handles click that stops edit mode
     */
    public void stopEditMode()
    {
        javax.swing.JTextField textField = (javax.swing.JTextField)getComponent(1);
        String text = textField.getText();
        removeAll();
                    
        // set the number of loops        
        int tmp_num_loop = num_loop;   // current number of loops
        try
        {
            if(text.compareTo("v") == 0 || text.compareTo("V") == 0)
            {
                if(var_list.Instance().isDefinedVariables() == true || findCurrentAux().getLocalVarList().isEmpty() == false)
                {
                    VarInputDialog vid = new VarInputDialog(null, this, findCurrentAux());
                    vid.pack();
                    vid.setLocationRelativeTo(this);
                    vid.setVisible(true);

                    if(vid.getUserInput() != null)
                    {
                        var_num_loop = vid.getUserInput();
                    }                    
                }
                else
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                        "You have not defined any global variables or local variables to this function or task yet.\nPlease Try Again.",
                        "Input Invalid",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                }
            }
            else
            {
                num_loop = Integer.parseInt(text);
                if(num_loop < 0)    // cannot be negative or zero, looping 0 times is for debugging purposes
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                        "Input value for the repeat's value is invalid.\nMinimum value is zero.\nPlease Try Again.",
                        "Input Invalid",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    num_loop = tmp_num_loop;
                }
                else
                {
                    var_num_loop = null;
                }
            }
        }
        catch(RuntimeException e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Input value for the repeat's value is invalid.\nPlease enter numbers only.\nPlease Try Again.",
                "Input Invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        
        // set the states for undo and redo
        if(tmp_num_loop != num_loop || var_num_loop != null)  // if values are different then store the state for undo and redo (to avoid redundancy)
        {
            UndoRedo.update_state();
        }
        
        // reset the icon's graphics                    
        setImage();
                    
        repaint();
        validate();
    }
    
    /**
     * Turns off the mouse listeners
     */
    public void turnoffListeners()
    {        
        java.awt.event.MouseListener[] ml = getMouseListeners();
        for(int i=0; i<ml.length; i++)
        {
            removeMouseListener(ml[0]);
        } 
    }
    
    /**
     * Turn on the mouse listeners
     */
    public void turnonListeners()
    {
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeNumLoops(evt);
            }
        });
    }
    
    /**
     * Get the help title of this repeat icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Repeat Icon";
        return s;
    }
    
    /**
     * Get help message of this repeat icon
     * @return Help message
     */      
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nRepeat: ";
        if(var_num_loop != null)
        {
            s += "Variable "+var_num_loop.getName();
        }
        else
        {
            s += num_loop;
        }
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */    
    public String getHelpDesc()
    {
        String s = "This icon repeats a sequence of icons\na number of times as specified.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */    
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/repeatIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public repeatIcon Clone()
    {
        return new repeatIcon("/icons/loops/repeat/icon_rep.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        repeatIcon newRI = Clone();
        if(var_num_loop != null)
        {
            newRI.setVarNumLoop(var_num_loop);
        }
        else
        {
            newRI.setNumLoop(num_loop);    
        }
        
        newRI.setImage();
        return newRI;
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
        algo += "loop";
        algo += id_separator;
        algo += identifier;
        algo += id_separator;
        algo += getId();
        algo += id_att_separator;
        algo += att_boundary_begin;
        if(var_num_loop != null)
        {
            algo += "numloop"+att_value_connector;
            algo += "V_"+var_num_loop.getName();            
        }
        else
        {
            algo += "numloop"+att_value_connector;
            algo += num_loop;           
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
        NQCCode += indentation;
        NQCCode += "// repeat a number of times a specified\n";
        NQCCode += indentation+"repeat(";
        
        if(var_num_loop != null)
        {
            NQCCode += var_num_loop.getName();
        }
        else
        {
            NQCCode += num_loop;
        }
        
        NQCCode += ")\n";
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
        
        // do the first one --> num_loop
        sp2 = sp[0].split(att_value_connector);
        if(sp2[1].substring(0,1).compareTo("V") == 0)  // must be a variable
        {
            var_num_loop = var_list.Instance().getVariable(sp2[1].substring(2));
            if(var_num_loop == null)  // must be local, not global
            {
                var_num_loop = getAux().getVariable(sp2[1].substring(2));
            }    
        }
        else
        {
            num_loop = Integer.parseInt(sp2[1]);
        }
    }
    
    /**
     * To display an input panel to ask for a variable to be used
     * @return The label of the panel
     */
    public javax.swing.JLabel getVarLabel()
    {     
        return new javax.swing.JLabel("Number of Repeat");
    }    
    
    /**
     * To indicate whether this icon uses a variable
     * @param var Variable to be checked
     * @return True or false
     */
    public Boolean useAVariable(variable var)
    {
        if(var_num_loop != null)
        {
            if(var_num_loop.getType() == var.getType() && var_num_loop.getName().compareTo(var.getName()) == 0)
            {
                return true;    
            }            
        }
        return false;
    }    
}
