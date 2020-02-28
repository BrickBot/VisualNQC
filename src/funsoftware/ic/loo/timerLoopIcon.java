/*
 * timerLoopIcon.java
 *
 * Created on 17 December 2005, 19:01
 *
 */

package funsoftware.ic.loo;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.st.*;
import funsoftware.var.*;
import funsoftware.consts.*;

/**
 * This class represents a loop that continues to loop unless the value of the specified timer is less than or greater than a treshold value
 * @author Thomas Legowo
 */
public class timerLoopIcon extends loopIcon {
        
    private int timer_number;  // with default value being 0, can be between 0 and 3
    private int timer_value;   // default value being 50, minimum is 1, unit is in 100ms.
    private variable var_timer_value;  // alternative to timer_value
    private int type;  // if type = 1 it means "greater than"
    
    // for algorithm representation, this timer loop icon has an identifier of 4
    private int identifier;
    
    /** Creates a new instance of timerLoopIcon */
    public timerLoopIcon() {
    }
    
    /**
     * Creates a new instance of infiniteLoopIcon
     * @param filepath The source file of this icon's image
     */
    public timerLoopIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        identifier = 4;
        timer_number = 0;
        timer_value = 50;
        type = 1;
        var_timer_value = null;
        setImage();
    }
    
    /**
     * Helps provide the transparent background
     * @param g Graphic
     */
    protected void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/timer/timer_field.gif"));
        g.drawImage(ii.getImage(), 14, 24, this);
    } 
    
    /**
     * Returns the timer number of this loop
     * @return Timer number
     */
    public int getTimerNumber()
    {
        return timer_number;
    }
    
    /**
     * Gets the timer value threshold
     * @return timer value threshold
     */
    public int getTimerValue()
    {
        return timer_value;
    }
    
    /**
     * Returns the type whether it is greater than or less than
     * @return Comparison type (< or >)
     */
    public int getType()
    {
        return type;
    }
    
    /**
     * Sets the number of the timer
     * @param ntn The new timer number
     */
    public void setTimerNumber(int ntn)
    {
        timer_number = ntn;
    }
    
    /**
     * Sets the timer value threshold of the sensor
     * @param ntv The new timer value
     */
    public void setTimerValue(int ntv)
    {
        timer_value = ntv;
    }
    
    /**
     * Sets the comparison type of the loop icon
     * @param nt New type
     */
    public void setType(int nt)
    {
        type = nt;
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
     * Sets the image of this icon
     */
    public void setImage()
    {
        removeAll();
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());
        javax.swing.ImageIcon nic;    
        PicButton pic;
        javax.swing.JPanel jp2 = new javax.swing.JPanel();
        javax.swing.JPanel jp3 = new javax.swing.JPanel();
        
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(40, 24));
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/timer/toploop.gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp2.add(pic, gridBagConstraints);
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/timer/comp"+type+".gif"));             
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jp2.add(pic, gridBagConstraints);
                
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jp2, gridBagConstraints);
        
        jp3 = new javax.swing.JPanel();
        jp3.setLayout(new java.awt.GridBagLayout());
        jp3.setPreferredSize(new java.awt.Dimension(40, 16)); 
                
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
        jp3.add(jp2, gridBagConstraints);        
        
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
        jp3.add(jp2, gridBagConstraints);       
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jp3.setOpaque(false);
        add(jp3, gridBagConstraints);
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(40, 40));
    }
    
    /**
     * To change the timer number of the sensor
     * @param evt MouseEvent
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
            PicButton pic;

            javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
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
     * To change the timer value threshold
     * @param evt MouseEvent
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
            textField.setPreferredSize(new java.awt.Dimension(25,16));
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
     * To change the comparator of this loop's ending case (either less than or greater than)
     * @param evt MouseEvent
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
            javax.swing.ImageIcon nic = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/timer/comp"+type+".gif"));
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
     * Stop the edit mode on timer number of this icon
     */
    public void stopEditModeTimerNumber()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
        javax.swing.JTextField textField = (javax.swing.JTextField)jp.getComponent(0);
        String text = textField.getText();
        jp.removeAll();
                    
        // parse the timer number
        int tmp_timer_number = timer_number;   // current port number
        try
        {
            timer_number = Integer.parseInt(text);
            if(timer_number > 3 || timer_number < 0)    // maximum of 4 timers
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
            
        javax.swing.JPanel jp2 = new javax.swing.JPanel();
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
                    
        repaint();
        validate();
    }
    
    /**
     * Stops the edit mode for the timer value threshold
     */
    public void stopEditModeTimerValue()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        javax.swing.JTextField textField = (javax.swing.JTextField)jp.getComponent(0);
        String text = textField.getText();
        jp.removeAll();
                    
        // parse the timer value
        int tmp_timer_value = timer_value;   // current timer value
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
            
        javax.swing.JPanel jp2 = new javax.swing.JPanel();
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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(jp2, gridBagConstraints);                    
                    
        repaint();
        validate();
    }
    
    /**
     * Turns off the mouse listeners
     */
    public void turnoffListeners()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
        java.awt.event.MouseListener[] ml = jp.getMouseListeners();
        for(int i=0; i<ml.length; i++)
        {
            jp.removeMouseListener(ml[0]);
        }        
        
        jp = (javax.swing.JPanel)getComponent(0);
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
        PicButton pic;
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeTimerNumber(evt);
            }
        });
        
        jp = (javax.swing.JPanel)getComponent(0);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeComparator(evt);
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
     * Get the help title of this timer loop icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Loop Timer Icon";
        return s;
    }
    
    /**
     * Get help message of this timer sensor loop icon
     * @return Help message
     */     
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nTimer Number: ";
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
        s+="\nComparator: ";
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
     * Get the description of this icon
     * @return Help Description
     */    
    public String getHelpDesc()
    {
        String s = "This icon repeats a sequence of icons\nuntil the specified timer is either\nless than or greater than\nthe specified value.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */    
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/timerLoopIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public timerLoopIcon Clone()
    {
        return new timerLoopIcon("/icons/loops/timer/timer.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        timerLoopIcon newRI = Clone();
        newRI.setTimerNumber(timer_number);
        if(var_timer_value != null)
        {
            newRI.setVarTimerVal(var_timer_value);
        }
        else
        {
            newRI.setTimerValue(timer_value);    
        }
        newRI.setType(type);
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
        algo += "timernumber"+att_value_connector;
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
        algo += "type"+att_value_connector;
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
        String compare = new String();
        if(type == 1)
        {
            compare=">";
        }
        else if(type == 2)
        {
            compare=">=";
        }
        else if(type == 3)
        {
            compare="<";
        }
        else if(type == 4)
        {
            compare="<=";
        }
        else if(type == 5)
        {
            compare="==";
        }
        else if(type == 6)
        {
            compare="!=";
        }      
        
        NQCCode += indentation;
        NQCCode += "// timer loop \n";
        NQCCode += indentation;
        NQCCode += "while(Timer("+timer_number+") "+compare+" ";
                
        if(var_timer_value != null)
        {
            NQCCode += var_timer_value.getName();
        }
        else
        {
            NQCCode += timer_value;
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
        
        // do the first one --> type
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
