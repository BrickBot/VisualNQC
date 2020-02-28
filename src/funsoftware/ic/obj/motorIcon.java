/*
 * motorIcon.java
 *
 * Created on 22 July 2005, 11:30
 */

package funsoftware.ic.obj;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.gri.*;
import funsoftware.st.*;
import funsoftware.struct.*;
import funsoftware.var.*;
import funsoftware.consts.*;

/**
 * This is a subclass of icon handling a motor icon
 * @author Thomas Legowo
 */
public class motorIcon extends objectIcon{
    
    // other private attributes
    
    private int power;   // the power level of this motor, from 1 to 8, no zero power allowed, zero for stop motor
                         // default power being one = 8 (full power)
    private variable var_power;
    
    private int direction; // the direction of the motor (0 for FORWARD(CLOCKWISE) or 1 BACKWARD for (ANTICLOCKWISE)), 2
                           // for stop motor
    private int portA;  // 0 if port A is not used by this motor, 1 otherwise
    private int portB;
    private int portC;
    
    // for algorithm representation, represent a motor icon with an identifier of 1
    private int identifier;
    
    /** Creates a new instance of motorIcon */
    public motorIcon() {
    }
    
    /**
     * Creates a new instance of motorIcon
     * @param filepath Source file of this icon's image
     * @param dir Direction of the motor
     */
    public motorIcon(String filepath, int dir)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this));
        power = 8;
        direction = dir;
        identifier = 1;
        portA = 1;
        portB = 1;
        portC = 1;
        var_power = null;
        setImage();
    }
    
    /**
     * Helps provide the transparent background
     * @param g Graphic
     */
    protected void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/power_field.gif"));
        g.drawImage(ii.getImage(), 26, 0, this);
    }    
    
    
    /**
     * Returns the power of this motor
     * @return Power level of this motor
     */
    public int getPower()
    {
        return power;
    }
    
    /**
     * Returns the direction of this motor's spin
     * @return Motor's direction
     */
    public int getDirection()
    {
        return direction;
    }
    
    /**
     * Sets the power level of this motor
     * @param npow New power level
     */
    public void setPower(int npow)
    {
        power = npow;
    }
    
    /**
     * Sets the power level of this motor (variable not constant)
     * @param vp The power variable
     */
    public void setVarPower(variable vp)
    {
        var_power = vp;
    }  
    
    /**
     * Sets the direction of this motor's spin
     * @param ndir New direction
     */
    public void setDirection(int ndir)
    {
        direction = ndir;
    }
    
    // to get the status of individual port
    /**
     * Gets the status of port A
     * @return Port A status
     */
    public int portAStatus()
    {
        return portA;
    }
    
    /**
     * Gets the status of port B
     * @return Port B status
     */
    public int portBStatus()
    {
        return portB;
    }
    
    /**
     * Gets the status of port C
     * @return Port C status
     */
    public int portCStatus()
    {
        return portC;
    }
    
    
    // methods for the ports, these methods also change the graphical representation of the motor icon's attributes
    // the little boxes with the letters 'A', 'B' or 'C' will change in background colour depending on whether
    // the port is turned on
    /**
     * Turn port A on
     */    
    public void turnPortAon()
    {
        portA = 1;
    }
    /**
     * Turn port A off
     */    
    public void turnPortAoff()
    {
        portA = 0;
    }
    /**
     * Turn port B on
     */    
    public void turnPortBon()
    {
        portB = 1;
    }
    /**
     * Turn port B off
     */    
    public void turnPortBoff()
    {
        portB = 0;
    }
    /**
     * Turn port C on
     */    
    public void turnPortCon()
    {
        portC = 1;
    }
    /**
     * Turn port C off
     */
    public void turnPortCoff()
    {
        portC = 0;
    }
   
    /**
     * To set the images of this icon (for move motor icon there are 5 elements --> direction, power level and output ports)
     */
    public void setImage()
    {
        removeAll();
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());
        
        // creating the top panel
        javax.swing.JPanel jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        jp.setPreferredSize(new java.awt.Dimension(40, 29));
        javax.swing.ImageIcon nic;
        if(direction == 0)
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/forward.gif"));
        }
        else
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/backward.gif"));
        }
        PicButton pic = new PicButton(nic);
        jp.add(pic);
        
        javax.swing.JPanel jp4 = new javax.swing.JPanel();
        jp4.setLayout(new java.awt.GridBagLayout());
        jp4.setPreferredSize(new java.awt.Dimension(14, 29));

        
        javax.swing.JPanel dummy = new javax.swing.JPanel();
        dummy.setPreferredSize(new java.awt.Dimension(14, 7));
        dummy.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp4.add(dummy, gridBagConstraints);        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
                            
        javax.swing.JLabel powerLabel;
        if(var_power == null)
        {
            powerLabel = new javax.swing.JLabel(Integer.toString(power));
        }
        else
        {
            powerLabel = new javax.swing.JLabel("V");
        }
        
        powerLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        powerLabel.setPreferredSize(new java.awt.Dimension(14, 16));
        powerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jp4.add(powerLabel, gridBagConstraints);
        
        
        dummy = new javax.swing.JPanel();
        dummy.setPreferredSize(new java.awt.Dimension(14, 6));
        dummy.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jp4.add(dummy, gridBagConstraints);
        jp4.setOpaque(false);
        jp.setOpaque(false);
        
        jp.add(jp4);
        
        // creating the bottom panel
        javax.swing.JPanel jp2 = new javax.swing.JPanel();
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(40, 11));
        if(portA == 0)
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/A_off.gif"));
        }
        else
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/A_on.gif"));
        }
        pic = new PicButton(nic); 
        jp2.add(pic);
        
        if(portB == 0)
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/B_off.gif"));
        }
        else
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/B_on.gif"));
        }
        pic = new PicButton(nic);
        jp2.add(pic);
        
        if(portC == 0)
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/C_off.gif"));
        }
        else
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/C_on.gif"));
        }
        pic = new PicButton(nic);
        jp2.add(pic);
        
        
        // joining the top and bottom panels
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jp, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(jp2, gridBagConstraints);
        setPreferredSize(new java.awt.Dimension(40, 40));        
    }
    
    
    // Following are the methods that responds to the mouse events
    /**
     * Changes the direction of the motor
     * @param evt MouseEvent
     */
    public void changeDirection(java.awt.event.MouseEvent evt)
    {        
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.ImageIcon nic;
            if(direction == 0) // forward
            {
                direction = 1;  // becomes backward
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/backward.gif"));
            }
            else
            {
                direction = 0;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/forward.gif"));
            }
            javax.swing.JPanel jp = (javax.swing.JPanel) getComponent(0);
            PicButton picpic = (PicButton) jp.getComponent(0);
            picpic.setIcon(nic);
            
            // set the states for undo and redo
            UndoRedo.update_state();
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
     * Changes the power level of the motor
     * @param evt MouseEvent
     */
    public void changePowerLevel(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            // prompt the user for new power level of this motor icon
            // get the value of the current power first
            javax.swing.JTextField textField;
            if(var_power != null)
            {
                textField = new javax.swing.JTextField("v");
            }
            else
            {
                textField = new javax.swing.JTextField(Integer.toString(power));
            }

            textField.setFont(new java.awt.Font("Tahoma", 1, 11));
            javax.swing.JPanel jp = (javax.swing.JPanel) getComponent(0);
            jp = (javax.swing.JPanel) jp.getComponent(1);
            jp.removeAll();
            jp.setLayout(new java.awt.GridBagLayout());
            textField.setPreferredSize(new java.awt.Dimension(14,16));
            textField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            textField.setOpaque(false);

            java.awt.GridBagConstraints gridBagConstraints;
            javax.swing.JPanel dummy = new javax.swing.JPanel();
            dummy.setPreferredSize(new java.awt.Dimension(14, 7));
            dummy.setOpaque(false);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            jp.add(dummy, gridBagConstraints);        

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            jp.add(textField, gridBagConstraints);

            dummy = new javax.swing.JPanel();
            dummy.setPreferredSize(new java.awt.Dimension(14, 6));
            dummy.setOpaque(false);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            jp.add(dummy, gridBagConstraints);            
            
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
     * Stops the power level edit mode on this motor icon
     */
    public void stopEditMode()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(0);
        jp = (javax.swing.JPanel) jp.getComponent(1);
        javax.swing.JTextField textField = (javax.swing.JTextField)jp.getComponent(1);
        String text = textField.getText();
                    
        jp.removeAll();
                    
        int tmp_power = power;  // the current power
        // assign the power level
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
                        var_power = vid.getUserInput();
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
                power = Integer.parseInt(text);
                if(power > 8 || power < 1)  // maximum power is 8
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                        "Input value for the motor's power is invalid.\nPermitted value is between 1 to 8.\nPlease Try Again.",
                        "Input Invalid",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    power = tmp_power;                          
                }     
                else
                {
                    var_power = null;
                }
            }
        } 
        catch(RuntimeException e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Input value for the motor's power is invalid.\nPlease enter numbers only (1 to 8).\nPlease Try Again.",
                "Input Invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
        }                    
                                                
        // set the states for undo and redo
        if(tmp_power != power)  // if values are different then store the state for undo and redo (to avoid redundancy)
        {
            UndoRedo.update_state();
        }  
        else if(var_power != null)
        {
            UndoRedo.update_state();
        }
                    
        // put the original stuffs back into the jpanel
        java.awt.GridBagConstraints gridBagConstraints;
        javax.swing.ImageIcon nic;
        PicButton pic;

        jp.setLayout(new java.awt.GridBagLayout());

        javax.swing.JPanel dummy = new javax.swing.JPanel();
        dummy.setPreferredSize(new java.awt.Dimension(14, 7));
        dummy.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(dummy, gridBagConstraints);        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        
        javax.swing.JLabel powerLabel;
        if(var_power == null)
        {
            powerLabel = new javax.swing.JLabel(Integer.toString(power));
        }
        else
        {
            powerLabel = new javax.swing.JLabel("V");
        }
        
        powerLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        powerLabel.setPreferredSize(new java.awt.Dimension(14, 16));
        powerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jp.add(powerLabel, gridBagConstraints);
        
        
        dummy = new javax.swing.JPanel();
        dummy.setPreferredSize(new java.awt.Dimension(14, 6));
        dummy.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jp.add(dummy, gridBagConstraints);

        repaint();
        validate();                       
    }
    
    /**
     * To change the status of port A
     * @param evt MouseEvent
     */
    public void changeA(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.ImageIcon nic = new javax.swing.ImageIcon();
            if(portA == 0) // off
            {
                portA = 1;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/A_on.gif"));
            }
            else if(portA == 1)
            {
                portA = 0;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/A_off.gif"));
            }
            javax.swing.JPanel jp = (javax.swing.JPanel) getComponent(1);
            PicButton picpic = (PicButton) jp.getComponent(0);
            picpic.setIcon(nic);
            
            // set the states for undo and redo
            UndoRedo.update_state();
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
     * To change the status of port B
     * @param evt MouseEvent
     */
    public void changeB(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.ImageIcon nic = new javax.swing.ImageIcon();
            if(portB == 0) // off
            {
                portB = 1;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/B_on.gif"));
            }
            else if(portB == 1)
            {
                portB = 0;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/B_off.gif"));
            }
            javax.swing.JPanel jp = (javax.swing.JPanel) getComponent(1);
            PicButton picpic = (PicButton) jp.getComponent(1);
            picpic.setIcon(nic);
            
            // set the states for undo and redo
            UndoRedo.update_state();
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
     * To change the status of port C
     * @param evt MouseEvent
     */
    public void changeC(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.ImageIcon nic = new javax.swing.ImageIcon();
            if(portC == 0) // off
            {
                portC = 1;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/C_on.gif"));
            }
            else if(portC == 1)
            {
                portC = 0;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/movemotor/C_off.gif"));
            }
            javax.swing.JPanel jp = (javax.swing.JPanel) getComponent(1);
            PicButton picpic = (PicButton) jp.getComponent(2);
            picpic.setIcon(nic);
            
            // set the states for undo and redo
            UndoRedo.update_state();
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
     * Turns off the mouse listeners
     */
    public void turnoffListeners()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel) getComponent(0);
        PicButton pic = (PicButton) jp.getComponent(0);
        java.awt.event.MouseListener[] ml = pic.getMouseListeners();
        for(int i=0; i<ml.length; i++)
        {
            pic.removeMouseListener(ml[0]);
        }        
        
        jp = (javax.swing.JPanel) getComponent(0);
        jp = (javax.swing.JPanel) jp.getComponent(1);
        ml = jp.getMouseListeners();
        for(int i=0; i<ml.length; i++)
        {
            jp.removeMouseListener(ml[0]);
        }  
        
        jp = (javax.swing.JPanel) getComponent(1);
        pic = (PicButton) jp.getComponent(0);
        ml = pic.getMouseListeners();
        for(int i=0; i<ml.length; i++)
        {
            pic.removeMouseListener(ml[0]);
        }  
        
        jp = (javax.swing.JPanel) getComponent(1);
        pic = (PicButton) jp.getComponent(1);
        ml = pic.getMouseListeners();
        for(int i=0; i<ml.length; i++)
        {
            pic.removeMouseListener(ml[0]);
        }  
        
        jp = (javax.swing.JPanel) getComponent(1);
        pic = (PicButton) jp.getComponent(2);
        ml = pic.getMouseListeners();
        for(int i=0; i<ml.length; i++)
        {
            pic.removeMouseListener(ml[0]);
        }  
    }
    
    /**
     * Turns on the mouse listeners
     */
    public void turnonListeners()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel) getComponent(0);
        PicButton pic = (PicButton) jp.getComponent(0);
        pic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeDirection(evt);
            }
        });
        
        jp = (javax.swing.JPanel) getComponent(0);
        jp = (javax.swing.JPanel) jp.getComponent(1);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changePowerLevel(evt);
            }
        });
        
        jp = (javax.swing.JPanel) getComponent(1);
        pic = (PicButton) jp.getComponent(0);
        pic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeA(evt);
            }
        });
        
        jp = (javax.swing.JPanel) getComponent(1);
        pic = (PicButton) jp.getComponent(1);
        pic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeB(evt);
            }
        });
        
        jp = (javax.swing.JPanel) getComponent(1);
        pic = (PicButton) jp.getComponent(2);
        pic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeC(evt);
            }
        });
    }
    
    
    /**
     * Get the help title of this beep icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Motor Icon";
        return s;
    }
    
    /**
     * Get help message of this beep icon
     * @return Help message
     */     
    public String getHelpMsg()
    {
        String s = "DETAILS:\n\nOperating at speed: ";
        
        if(var_power != null)
        {
            s += "Variable "+var_power.getName();
        }
        else
        {
            s += power;
        }

        s+="\nDirection of the motor's rotation: ";
        if(direction == 0)
        {
            s+="forward";
        }
        else if(direction == 1)
        {
            s+="backward";
        }
        s+="\nPort A: ";
        if(portA == 0)
        {
            s+="OFF";
        }
        else if(portA == 1)
        {
            s+="ON";
        }
        s+="\nPort B: ";
        if(portB == 0)
        {
            s+="OFF";
        }
        else if(portB == 1)
        {
            s+="ON";
        }
        s+="\nPort C: ";
        if(portC == 0)
        {
            s+="OFF";
        }
        else if(portC == 1)
        {
            s+="ON";
        }
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */      
    public String getHelpDesc()
    {
        String s = "This icon turns on motors connected to\nthe specified ports at the specified power\nlevel and direction.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */    
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/motorIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public motorIcon Clone()
    {
        return new motorIcon("/icons/motors/movemotor/icon_wheel2.gif", 0);
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        motorIcon newMI = Clone();
        if(var_power != null)
        {
            newMI.setVarPower(var_power);
        }
        else
        {
            newMI.setPower(power);      
        }        
        newMI.setDirection(direction);
        if(portA == 1)
        {
            newMI.turnPortAon();
        }
        else
        {
            newMI.turnPortAoff();
        }        
        if(portB == 1)
        {
            newMI.turnPortBon();
        }
        else
        {
            newMI.turnPortBoff();
        }        
        if(portC == 1)
        {
            newMI.turnPortCon();
        }       
        else
        {
            newMI.turnPortCoff();
        }
        newMI.setImage();
        return newMI;
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
        algo += "direction"+att_value_connector;
        algo += direction;
        algo += att_separator;
        if(var_power != null)
        {
            algo += "power"+att_value_connector;
            algo += "V_"+var_power.getName();            
        }
        else
        {
            algo += "power"+att_value_connector;
            algo += power;            
        }

        algo += att_separator;
        algo += "porta"+att_value_connector;
        algo += portA;
        algo += att_separator;
        algo += "portb"+att_value_connector;
        algo += portB;
        algo += att_separator;
        algo += "portc"+att_value_connector;
        algo += portC;
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
        
        String output = null;   // to represent the output port
        if(portA == 1)
        {
            output = "OUT_A";
        }
        if(portB == 1)
        {
            if(output != null)
            {
                output += " + OUT_B";
            }
            else
            {
                output = "OUT_B";
            }
        }
        if(portC == 1)
        {
            if(output != null)
            {
                output += " + OUT_C";
            }
            else
            {
                output = "OUT_C";
            }
        }
        
        NQCCode += indentation;
        NQCCode += "// turning motors on \n";
        NQCCode += indentation;
        NQCCode += "On(";
        NQCCode += output;
        NQCCode += ");\n";
        
        // setting the power of the motor
        NQCCode += indentation;
        NQCCode += "SetPower(";
        NQCCode += output+", ";
        
        if(var_power != null)
        {
            NQCCode += var_power.getName()+");\n";
        }
        else
        {
            NQCCode += (power-1)+");\n";
        }
        
        // setting the direction of the motor
        NQCCode += indentation;
        NQCCode += "SetDirection(";
        NQCCode += output+", ";
        if(direction == 0)
        {
            NQCCode += "OUT_FWD";
        }
        else if(direction == 1)
        {
            NQCCode += "OUT_REV";
        }
        NQCCode += ");\n\n";
        // temporarily, no attributes mean nothing to do
        if(output == null)
        {
            return "";
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
        
        // do the first one --> direction
        sp2 = sp[0].split(att_value_connector);
        direction = Integer.parseInt(sp2[1]);  

        // do the second one --> power
        sp2 = sp[1].split(att_value_connector);
        
        if(sp2[1].substring(0,1).compareTo("V") == 0)  // must be a variable
        {
            var_power = var_list.Instance().getVariable(sp2[1].substring(2));
            if(var_power == null)  // must be local, not global
            {
                var_power = getAux().getVariable(sp2[1].substring(2));
            }    
        }
        else
        {
            power = Integer.parseInt(sp2[1]);
        }     
        
        // do the third one --> portA
        sp2 = sp[2].split(att_value_connector);
        portA = Integer.parseInt(sp2[1]);
        
        // do the fourth one --> portB
        sp2 = sp[3].split(att_value_connector);
        portB = Integer.parseInt(sp2[1]);
        
        // do the fifth one --> portC
        sp2 = sp[4].split(att_value_connector);
        portC = Integer.parseInt(sp2[1]);
    }
   
    /**
     * To display an input panel to ask for a variable to be used
     * @return The label of the panel
     */
    public javax.swing.JLabel getVarLabel()
    {     
        return new javax.swing.JLabel("Motor Power");
    }    
    
    /**
     * To indicate whether this icon uses a variable
     * @param var Variable to be checked
     * @return True or false
     */
    public Boolean useAVariable(variable var)
    {
        if(var_power != null)
        {
            if(var_power.getType() == var.getType() && var_power.getName().compareTo(var.getName()) == 0)
            {
                return true;    
            }            
        }
        return false;
    }   
}
