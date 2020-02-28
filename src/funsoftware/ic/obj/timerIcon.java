/*
 * timerIcon.java
 *
 * Created on 23 July 2005, 00:09
 */

package funsoftware.ic.obj;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.st.*;
import funsoftware.struct.*;
import funsoftware.var.*;
import funsoftware.consts.*;

/**
 * This class is for the timer icon of the fUNSoftWare.
 * @author Thomas Legowo
 * 
 */
public class timerIcon extends objectIcon{
    
    // other private attributes
    
    private int timer;   // the amount of time this timer is suppose to count until, default value is 1 second
    private int type;    // 0 for 's' -- seconds, 1 for 'm' -- minutes, 2 for 'h' -- N hundredths of a second, default is zero being second

    private variable var_timer;
    
    // for algorithm representation, represent a timer icon with an identifier of 4
    private int identifier;
    
    /** Creates a new instance of timerIcon */    
    public timerIcon()
    {                
    }
    
    /**
     * Creates a new instance of timerIcon
     * @param filepath Source file of this icon's image
     */
    public timerIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        timer = 1;
        type = 0;
        identifier = 4;
        var_timer = null;
        setImage();
    }
    
    /**
     * Helps provide the transparent background
     * @param g Graphic
     */
    protected void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/timers/wait/timer_field.gif"));
        g.drawImage(ii.getImage(), 0, 24, this);
    }
    
    /**
     * Returns the timer value
     * @return Timer value
     */
    public int getTimer()
    {
        return timer;
    }
    
    /**
     * Returns the unit of the time -- seconds(type 0), minutes(type 1) or hours(type 2)
     * @return Type
     */
    public int getType()
    {
        return type;
    }
    
    /**
     * Sets the time of this timer
     * @param ntimer Timer
     */
    public void setTimer(int ntimer)
    {
        timer = ntimer;
    }
    
    /**
     * Sets the type(or unit) of this timer
     * @param ntype New type
     */
    public void setType(int ntype)
    {
        type = ntype;
    }
    
    
    /**
     * Translating the integer value of the time and its units into string
     * @return String representation of timer value
     */
    public String returnTime()
    {
        String unit = new String();
        String time = new String();
        if(type == 0)
        {
            unit = "s";
        }
        else if(type == 1)
        {
            unit = "m";
        }
        else if(type == 2)
        {
            unit = "h";
        }
        
        time = Integer.toString(timer);
        
        time = time.concat(unit);
        
        return time;
    }

    /**
     * To set the images and labels of this icon
     */
    public void setImage()
    {
        removeAll();
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());
        javax.swing.ImageIcon nic;
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/timers/wait/lefttime.gif"));
        PicButton pic = new PicButton(nic);
        add(pic);
        
        javax.swing.JPanel jp2 = new javax.swing.JPanel();
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(30, 40));
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/timers/wait/toptime.gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp2.add(pic, gridBagConstraints);
        
        javax.swing.JLabel timerLabel;
        if(var_timer == null)
        {
            timerLabel = new javax.swing.JLabel(returnTime());
        }
        else
        {
            timerLabel = new javax.swing.JLabel("V");
        }
        
        timerLabel.setBorder(null);
        timerLabel.setFont(new java.awt.Font("Tahoma", 0, 11));
        timerLabel.setPreferredSize(new java.awt.Dimension(28, 12));
        timerLabel.setMinimumSize(new java.awt.Dimension(28, 12));
        timerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        jp2.setOpaque(false);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jp2.add(timerLabel, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/timers/wait/bottomtime.gif"));
        pic = new PicButton(nic);
        jp2.add(pic, gridBagConstraints);

        add(jp2);
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/timers/wait/rightime.gif"));
        pic = new PicButton(nic);
        add(pic);
        
        setPreferredSize(new java.awt.Dimension(40, 40));        
    }
    
    /**
     * Change the time and its unit or type
     * @param evt MouseEvent
     */
    public void changeTime(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.JTextField textField;
            if(var_timer != null)
            {
                textField = new javax.swing.JTextField("v");
            }
            else
            {
                textField = new javax.swing.JTextField(returnTime());
            }
            textField.setFont(new java.awt.Font("Tahoma", 0, 11));
            textField.setPreferredSize(new java.awt.Dimension(40,14));
            textField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            textField.setOpaque(false);
            java.awt.GridBagConstraints gridBagConstraints;

            removeAll();
            setLayout(new java.awt.GridBagLayout());

            javax.swing.ImageIcon nic;

            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/timers/wait/fulltoptime.gif"));
            PicButton pic = new PicButton(nic);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            add(pic, gridBagConstraints);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            add(textField, gridBagConstraints);

            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/timers/wait/fullbottomtime.gif"));
            pic = new PicButton(nic);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            add(pic, gridBagConstraints);

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
    
    /**
     * This is to handle click that stops edit mode
     */
    public void stopEditMode()
    {
        javax.swing.JTextField textField = (javax.swing.JTextField)getComponent(1);
        String text = textField.getText();
        removeAll();
                    
        int tmp_timer = timer;
        int tmp_type = type;
                
        // parse the unit of the time, second will be taken as default should the user does not indicate
        // the unit
        int i=text.length();
        if(i == 1)
        {
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
                            var_timer = vid.getUserInput();
                            type = 2;
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
                    timer = Integer.parseInt(text.substring(0, i));
                    type = 0;                    
                    if(timer <= 0)   // it is pointless to time for 0 or negative values
                    {
                        javax.swing.JOptionPane.showMessageDialog(null,
                                            "Input value for the timer is invalid.\nPlease enter value greater than 0.\nPlease Try Again.",
                                            "Input Invalid",
                                            javax.swing.JOptionPane.WARNING_MESSAGE);
                        timer = tmp_timer; 
                        type = tmp_type;
                    }        
                    else
                    {
                        var_timer = null;
                    }
                }
            } 
            catch(RuntimeException e)
            {
                            javax.swing.JOptionPane.showMessageDialog(null,
                                        "Input value for the timer is invalid.\nPlease Try Again.",
                                        "Input Invalid",
                                        javax.swing.JOptionPane.WARNING_MESSAGE);
            };
        }
        else if(i > 1)
        {
            try
            {                
                if((text.substring(i-1)).compareTo("s") == 0)
                {                    
                    timer = Integer.parseInt(text.substring(0, i-1));
                    type = 0;
                }
                else if((text.substring(i-1)).compareTo("m") == 0)
                {
                    timer = Integer.parseInt(text.substring(0, i-1));
                    type = 1;
                }
                else if((text.substring(i-1)).compareTo("h") == 0)
                {                    
                    timer = Integer.parseInt(text.substring(0, i-1));
                    type = 2;
                }
                else
                {
                    timer = Integer.parseInt(text.substring(0, i));
                    type = 0;
                }                
                
                if(timer <= 0)   // it is pointless to time for 0 or negative values
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                                        "Input value for the timer is invalid.\nPlease enter value greater than 0.\nPlease Try Again.",
                                        "Input Invalid",
                                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    timer = tmp_timer;
                    type = tmp_type;
                }
                else
                {
                    var_timer = null;
                }                
            } 
            catch(RuntimeException e)
            {
                javax.swing.JOptionPane.showMessageDialog(null,
                                        "Input value for the timer is invalid.\nPlease Try Again.",
                                        "Input Invalid",
                                        javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        }
        
        // set the states for undo and redo
        if(tmp_timer != timer || tmp_type != type)  // if values are different then store the state for undo and redo (to avoid redundancy)
        {
            UndoRedo.update_state();
        }
        else if(var_timer != null)
        {
            UndoRedo.update_state();
        }
        
        // put the original stuffs back into the jpanel
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
     * Turns on the mouse listeners
     */
    public void turnonListeners()
    {
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeTime(evt);
            }
        });
    }        
        
    /**
     * Get the help title of this beep icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Timer Icon";
        return s;
    }
    
    /**
     * Get help message of this beep icon
     * @return Help message
     */     
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nDuration of wait: ";
        if(var_timer != null)
        {
            s += "Variable "+var_timer.getName();
        }
        else
        {
            s += timer;
            if(type == 0)
            {
                s+=" seconds";
            }
            else if(type == 1)
            {
                s+=" minutes";
            }
            else if(type == 2)
            {
                s+=" hundredths of a second";
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
        String s = "This icon indicates the amount\nof time to wait for.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */    
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/timerIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public timerIcon Clone()
    {
        return new timerIcon("/icons/timers/wait/icon_time.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        timerIcon newTI = Clone();
        newTI.setTimer(timer);
        newTI.setType(type);
        newTI.setImage();
        return newTI;
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
        if(var_timer != null)
        {
            algo += "timer"+att_value_connector;
            algo += "V_"+var_timer.getName();            
        }
        else
        {
            algo += "timer"+att_value_connector;
            algo += timer;            
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
        
        int output = timer * 100;  // the argument for Wait in NQC is the amount of 1/100ths of a second, so if wait 2 seconds
                                   // then it is 200 in NQC
        
        if(type == 1)   // for minutes, time by 60
        {
            output *= 60;
        }
        else if(type == 2)
        {
            // for type 2 that is hundredths of a second, divide back by 100
            output /= 100;
        }        
        
        NQCCode += indentation+"// waits for a specified amount of time \n";
        NQCCode += indentation+"Wait(";
        if(var_timer != null)
        {
            NQCCode += var_timer.getName();
        }
        else
        {
            NQCCode += output;
        }
        NQCCode += ");\n\n";
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
        // the first attribute will be the timer and second will be the type (unit --> seconds, minutes or hours)
        
        // do the first one --> timer
        sp2 = sp[0].split(att_value_connector);
     
        if(sp2[1].substring(0,1).compareTo("V") == 0)  // must be a variable
        {
            var_timer = var_list.Instance().getVariable(sp2[1].substring(2));
            if(var_timer == null)  // must be local, not global
            {
                var_timer = getAux().getVariable(sp2[1].substring(2));
            }    
        }
        else
        {
            timer = Integer.parseInt(sp2[1]);
        }
        
        // do the second one --> type
        sp2 = sp[1].split(att_value_connector);
        type = Integer.parseInt(sp2[1]);
    }
    
    /**
     * To display an input panel to ask for a variable to be used
     * @return The label of the panel
     */
    public javax.swing.JLabel getVarLabel()
    {     
        return new javax.swing.JLabel("Timer (in miliseconds)");
    }   
    
    /**
     * To indicate whether this icon uses a variable
     * @param var Variable to be checked
     * @return True or false
     */
    public Boolean useAVariable(variable var)
    {
        if(var_timer != null)
        {
            if(var_timer.getType() == var.getType() && var_timer.getName().compareTo(var.getName()) == 0)
            {
                return true;    
            }            
        }
        return false;
    } 
}
