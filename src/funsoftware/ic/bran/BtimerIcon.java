/*
 * BtimerIcon.java
 *
 * Created on 17 December 2005, 18:58
 *
 */

package funsoftware.ic.bran;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.st.*;
import funsoftware.var.*;
import funsoftware.consts.*;

/**
 * This class represents the branch that tests whether the timer is of a certain value
 * @author Thomas Legowo
 */
public class BtimerIcon extends branchIcon{
    
    // the local variables
    
    private int timer_number;   // with default value being 0, timer number can be 0 to 3 (4 timers in the RCX bot)
    private int timer_value;    // default value being 50, minimum is 1, unit is in 100ms.
    private variable var_timer_value;  // alternative to timer_value
    
    // for algorithm representation, represent a branch timer icon with an identifier of 5
    private int identifier;
        
    private int type;   // originally typed 1, ">" for greater than    
    
    /**
     * Creates a new instance of BtimerIcon
     */
    public BtimerIcon() {
    }
    
    /**
     * Creates a new instance of BtimerIcon
     * @param filepath The source file of this icon's graphic
     */
    public BtimerIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        timer_number = 0;
        timer_value = 50;
        identifier = 5;
        var_timer_value = null;
        type = 1;
        setImage();
    } 
    
    /**
     * Helps provide the transparent background
     * @param g Graphic
     */
    protected void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/branches/timer/timer_field.gif"));
        g.drawImage(ii.getImage(), 14, 24, this);
    }     
    
    
    /**
     * Return the timer number is operating in
     * @return Timer number
     */
    public int getTimer()
    {
        return timer_number;
    }
    
    /**
     * Return the light sensor value threshold of this branch timer icon
     * @return Timer value
     */
    public int getTimerValue()
    {
        return timer_value;
    }
    
    /**
     * Set the timer number
     * @param ntimer New Timer number
     */
    public void setTimer(int ntimer)
    {
        timer_number = ntimer;
    }
    
    /**
     * Set the timer value of this icon
     * @param ntv New timer value
     */
    public void setTimerValue(int ntv)
    {
        timer_value = ntv;
    }
    
    /**
     * Set the timer value of this icon
     * @param var The timer value variable
     */
    public void setVarTimerVal(variable var)
    {
        var_timer_value = var;
    }  
    
    /**
     * Sets the new comparator type to this icon
     * @param nct New comparator type
     */
    public void setComparatorType(int nct)
    {
        type = nct;
    }    
    
    /**
     * Gets the comparator type to this icon
     * @return Comparator type
     */
    public int getComparatorType()
    {
        return type;
    }      
    
    /**
     * Set the image of this icon
     */
    public void setImage()
    {
        removeAll();
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());
        javax.swing.ImageIcon nic;
        PicButton pic;
        
        javax.swing.JPanel jp, jp2;        
                
        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        jp.setPreferredSize(new java.awt.Dimension(40, 24));         
        
        // add le stuffs into le jp
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/branches/timer/top.gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(pic, gridBagConstraints);        
        
        // displays the comparator according to its type
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/branches/comparator/comp"+type+".gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jp.add(pic, gridBagConstraints);      
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jp, gridBagConstraints);             
                
        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        jp.setPreferredSize(new java.awt.Dimension(40, 16));        
        
        // do the panel within jp
        jp2 = new javax.swing.JPanel();
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(14, 16));        
        
        javax.swing.JLabel timerNumberLabel = new javax.swing.JLabel(Integer.toString(timer_number));
        timerNumberLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        timerNumberLabel.setPreferredSize(new java.awt.Dimension(14, 16));
        timerNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jp2.setOpaque(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp2.add(timerNumberLabel, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(jp2, gridBagConstraints);    
        
        // ---------- the light value panel  
        
        jp2 = new javax.swing.JPanel();
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(26, 16));       
                
        javax.swing.JLabel timerValueLabel;
        if(var_timer_value == null)
        {
            timerValueLabel = new javax.swing.JLabel(Integer.toString(timer_value));
        }
        else
        {
            timerValueLabel = new javax.swing.JLabel("V");
        }
        timerValueLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        timerValueLabel.setPreferredSize(new java.awt.Dimension(26, 16));
        timerValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jp2.setOpaque(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp2.add(timerValueLabel, gridBagConstraints);    

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jp.add(jp2, gridBagConstraints); 
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(jp, gridBagConstraints);
        
        jp.setOpaque(false);
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(40, 40));
    }
    
    /**
     * To change the port value of this icon
     * @param evt Mouse event
     */
    public void changeTimerNumber(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.JTextField textField = new javax.swing.JTextField(Integer.toString(timer_number));
            textField.setFont(new java.awt.Font("Tahoma", 1, 11));
            textField.setPreferredSize(new java.awt.Dimension(14,16));
            textField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);  
            textField.setOpaque(false);

            java.awt.GridBagConstraints gridBagConstraints;
            javax.swing.ImageIcon nic;
            javax.swing.JPanel jp;
            PicButton pic;

            jp = (javax.swing.JPanel)getComponent(1);
            jp = (javax.swing.JPanel)jp.getComponent(0);
            jp.removeAll();
            jp.setLayout(new java.awt.GridBagLayout());
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            jp.add(textField, gridBagConstraints);

            jp.setOpaque(false);

            repaint();
            validate();
            textField.requestFocusInWindow();

            textField.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        //stop edit mode (Enter-key)
                        stopEditModeTimerNumber();
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
    
    /**
     * To change the timer value threshold of this icon
     * @param evt Mouse event
     */
    public void changeTimerValue(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.JTextField textField;
            if(var_timer_value != null)
            {
                textField = new javax.swing.JTextField("v");
            }
            else
            {
                textField = new javax.swing.JTextField(Integer.toString(timer_value));
            }
            textField.setFont(new java.awt.Font("Tahoma", 1, 11));
            textField.setPreferredSize(new java.awt.Dimension(26,16));
            textField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);   
            textField.setOpaque(false);

            java.awt.GridBagConstraints gridBagConstraints;
            javax.swing.ImageIcon nic;
            PicButton pic;
                        
            javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
            jp = (javax.swing.JPanel)jp.getComponent(1);
            jp.removeAll();
            jp.setLayout(new java.awt.GridBagLayout());
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            jp.add(textField, gridBagConstraints);

            jp.setOpaque(false);

            repaint();
            validate();
            textField.requestFocusInWindow();

            textField.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        //stop edit mode (Enter-key)
                        stopEditModeTimerValue();
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

    /**
     * To change the comparator type of this icon
     * @param evt Mouse event
     */
    public void changeComparator(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            ComparatorDialog c_dialog = new ComparatorDialog(null, type, this);
            c_dialog.pack();
            c_dialog.setLocationRelativeTo(this);
            c_dialog.setVisible(true);  

            type = c_dialog.getComparator();
            
            javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(0);      
            jp.remove(1);
            // displays the comparator according to its type
            javax.swing.ImageIcon nic = new javax.swing.ImageIcon(getClass().getResource("/icons/branches/comparator/comp"+type+".gif"));
            PicButton pic = new PicButton(nic);
            java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            jp.add(pic, gridBagConstraints);  
                        
            // set the states for undo and redo
            UndoRedo.update_state();
            
            repaint();
            validate();
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

    /**
     * Stop the edit mode of the timer number on this branch icon
     */
    public void stopEditModeTimerNumber()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
        javax.swing.JTextField textField = (javax.swing.JTextField)jp.getComponent(0);
        String text = textField.getText();
        jp.removeAll();
                    
        // parse the port number
        int tmp_timer_number = timer_number;   // current port number
        try{
            timer_number = Integer.parseInt(text);
            if(timer_number > 3 || timer_number < 0)    // values between 0 to 3 only
            {
                javax.swing.JOptionPane.showMessageDialog(null,
                        "Input value for the timer number is invalid.\nIt can only be either between 0 and 3.\nPlease Try Again.",
                        "Input Invalid",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                timer_number = tmp_timer_number;
            }
        } 
        catch(RuntimeException e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                        "Input value for the timer number is invalid.\nPlease enter numbers only (0 to 3).\nPlease Try Again.",
                        "Input Invalid",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
        };

        // set the states for undo and redo
        if(tmp_timer_number != timer_number)  // if values are different then store the state for undo and redo (to avoid redundancy)
        {
            UndoRedo.update_state();
        } 

        // put the original stuffs back into the jpanel
                    
        java.awt.GridBagConstraints gridBagConstraints;
        javax.swing.ImageIcon nic;
        PicButton pic;

        jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
        jp.removeAll();
        jp.setLayout(new java.awt.GridBagLayout());
            
        javax.swing.JLabel timerNumberLabel = new javax.swing.JLabel(Integer.toString(timer_number));
        timerNumberLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        timerNumberLabel.setPreferredSize(new java.awt.Dimension(14, 16));
        timerNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(timerNumberLabel, gridBagConstraints);

        jp.setOpaque(false);
                    
        repaint();
        validate();
    }
    

    /**
     * Stop the edit mode of the timer value on this branch icon
     */
    public void stopEditModeTimerValue()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        javax.swing.JTextField textField = (javax.swing.JTextField)jp.getComponent(0);
        String text = textField.getText();
        jp.removeAll();
                    
        // parse the light sensor value
        int tmp_timer_value = timer_value;   // current port number
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
                        var_timer_value = vid.getUserInput();
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
                timer_value = Integer.parseInt(text);
                if(timer_value < 1)    
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                        "Input value for the timer threshold is invalid.\nMinimum value is 1.\nPlease Try Again.",
                        "Input Invalid",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    timer_value = tmp_timer_value;
                }
                else
                {
                    var_timer_value = null;
                }
            }
        } 
        catch(RuntimeException e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Input value for the timer threshold is invalid.\nPlease enter numbers only (Greater than 1).\nPlease Try Again.",
                "Input Invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
        };
                    
        // set the states for undo and redo
        if(tmp_timer_value != timer_value || var_timer_value != null)  // if values are different then store the state for undo and redo (to avoid redundancy)
        {
            UndoRedo.update_state();
        }
                    
        // put the original stuffs back into the jpanel
                    
        java.awt.GridBagConstraints gridBagConstraints;
        javax.swing.ImageIcon nic;
        PicButton pic;

        jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        jp.removeAll();
        jp.setLayout(new java.awt.GridBagLayout());
            
        javax.swing.JLabel timerValueLabel;
        if(var_timer_value == null)
        {
            timerValueLabel = new javax.swing.JLabel(Integer.toString(timer_value));
        }
        else
        {
            timerValueLabel = new javax.swing.JLabel("V");
        }
        timerValueLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        timerValueLabel.setPreferredSize(new java.awt.Dimension(26, 16));
        timerValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);          
            
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(timerValueLabel, gridBagConstraints);
        jp.setOpaque(false);
                    
        repaint();
        validate();
    }
    
    /**
     * Turns off the mouse listeners
     */
    public void turnoffListeners()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(0);
        java.awt.event.MouseListener[] ml = jp.getMouseListeners();
        for(int i=0; i<ml.length; i++)
        {
            jp.removeMouseListener(ml[0]);
        }        
        
        jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
        ml = jp.getMouseListeners();
        for(int i=0; i<ml.length; i++)
        {
            jp.removeMouseListener(ml[0]);
        }  
        
        jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        ml = jp.getMouseListeners();
        for(int i=0; i<ml.length; i++)
        {
            jp.removeMouseListener(ml[0]);
        }  
    }
    
    /**
     * Turn on the mouse listeners
     */
    public void turnonListeners()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(0);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeComparator(evt);
            }
        });    
        
        jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    changeTimerNumber(evt);
            }
        });
        
        jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    changeTimerValue(evt);
            }
        });
    }   
    

    /**
     * Get help title of this icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Branch Timer Icon";
        return s;
    }
    
    /**
     * Get help message of this icon
     * @return Help message
     */
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nTimer number: ";
        s+=timer_number;
        s+="\nTimer Value Treshold: ";
        if(var_timer_value != null)
        {
            s += "Variable "+var_timer_value.getName();
        }
        else
        {
            float tmp = (float)timer_value;
            s += tmp/10+" seconds";
        }
        s+="\nTop comparator: ";
        if(type == 1)
        {
            s+=">";
        }
        else if(type == 2)
        {
            s+=">=";
        }
        else if(type == 3)
        {
            s+="<";
        }
        else if(type == 4)
        {
            s+="<=";
        }
        else if(type == 5)
        {
            s+="==";
        }
        else if(type == 6)
        {
            s+="!=";
        }
        return s;
    }
    
    /**
     * Get help description of this icon
     * @return Help description
     */
    public String getHelpDesc()
    {
        String s = "This icon acts as a branch.\nIt chooses one between two paths\ndepending on whether the reading from the\n" +
                   "specified timer is greater or less than\nand equal to the specified value.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/BtimerIcon.gif"));
        return ii;
    }

    /**
     * To clone this icon 
     * @return New instance of this icon
     */
    public BtimerIcon Clone()
    {
        return new BtimerIcon("/icons/branches/timer/timer.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        BtimerIcon newWTI = Clone();
        newWTI.setTimer(timer_number);
        if(var_timer_value != null)
        {
            newWTI.setVarTimerVal(var_timer_value);
        }
        else
        {
            newWTI.setTimerValue(timer_value);    
        }
        newWTI.setComparatorType(type);
        newWTI.setImage();
        return newWTI;
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
        algo += "branch";
        algo += id_separator;
        algo += identifier;
        algo += id_separator;
        algo += getId();
        algo += id_att_separator;
        algo += att_boundary_begin;
        algo += "number"+att_value_connector;
        algo += timer_number;
        algo += att_separator;
        if(var_timer_value != null)
        {
            algo += "timervalue"+att_value_connector;
            algo += "V_"+var_timer_value.getName();            
        }
        else
        {
            algo += "timervalue"+att_value_connector;
            algo += timer_value;           
        } 
        algo += att_separator;
        algo += "comparator"+att_value_connector;
        algo += type;           
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
        NQCCode += "// timer test(branch) \n";
        NQCCode += indentation;
        NQCCode += "if(Timer("+timer_number+") ";
        if(type == 1)
        {
            NQCCode+=">";
        }
        else if(type == 2)
        {
            NQCCode+=">=";
        }
        else if(type == 3)
        {
            NQCCode+="<";
        }
        else if(type == 4)
        {
            NQCCode+="<=";
        }
        else if(type == 5)
        {
            NQCCode+="==";
        }
        else if(type == 6)
        {
            NQCCode+="!=";
        }    
        if(var_timer_value != null)
        {
            NQCCode += " "+var_timer_value.getName()+")\n";
        }
        else
        {
            NQCCode += " "+timer_value+")\n";
        }
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
        
        // do the first one --> port
        sp2 = sp[0].split(att_value_connector);
        timer_number = Integer.parseInt(sp2[1]);
        
        // do the second one --> timer_value
        sp2 = sp[1].split(att_value_connector);
        if(sp2[1].substring(0,1).compareTo("V") == 0)  // must be a variable
        {
            var_timer_value = var_list.Instance().getVariable(sp2[1].substring(2));
            if(var_timer_value == null)  // must be local, not global
            {
                var_timer_value = getAux().getVariable(sp2[1].substring(2));
            }    
        }
        else
        {
            timer_value = Integer.parseInt(sp2[1]);
        }    
        // do the third one --> comparator
        sp2 = sp[2].split(att_value_connector);
        type = Integer.parseInt(sp2[1]);           
    }
   
    /**
     * To display an input panel to ask for a variable to be used
     * @return The label of the panel
     */
    public javax.swing.JLabel getVarLabel()
    {     
        return new javax.swing.JLabel("Timer Value Threshold");
    }    
    
    /**
     * To indicate whether this icon uses a variable
     * @param var Variable to be checked
     * @return True or false
     */
    public Boolean useAVariable(variable var)
    {
        if(var_timer_value != null)
        {
            if(var_timer_value.getType() == var.getType() && var_timer_value.getName().compareTo(var.getName()) == 0)
            {
                return true;    
            }            
        }
        return false;
    }     
}
