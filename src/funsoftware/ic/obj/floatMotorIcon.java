/*
 * floatMotorIcon.java
 *
 * Created on 31 January 2006, 09:49
 *
 */

package funsoftware.ic.obj;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.st.*;

/**
 * Float motor means to turn off the motors, but without applying the brakes on them.
 * @author Thomas Legowo
 */
public class floatMotorIcon extends objectIcon{
    
    // other private attributes

    private int portA;  // 0 if port A is not floated, 1 if port A is floated
    private int portB;
    private int portC;
    
    // for algorithm representation, represent a stop motor icon with an identifier of 13
    private int identifier;
    
    /** Creates a new instance of floatMotorIcon */    
    public floatMotorIcon()
    {        
    }
    
    /**
     * Creates a new instance of floatMotorIcon
     * @param filepath Source file of this icon's image
     */
    public floatMotorIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        identifier = 13;
        portA = 1;
        portB = 1;
        portC = 1;
        setImage();
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
    
    
    // methods for the ports, these methods also change the graphical representation of the float motor icon's attributes
    // the little boxes with the letters 'A', 'B' or 'C' will change in background colour depending on whether
    // the port is turned floated
    /**
     * Floats output port A
     */
    public void floatPortA()
    {
        portA = 1;
    }
    /**
     * Turns output port A off
     */    
    public void turnPortAoff()
    {
        portA = 0;
    }
    /**
     * Floats output port B
     */    
    public void floatPortB()
    {
        portB = 1;
    }
    /**
     * Turns output port B off
     */    
    public void turnPortBoff()
    {
        portB = 0;
    }
    /**
     * Floats output port C
     */    
    public void floatPortC()
    {
        portC = 1;
    }
    /**
     * Turns output port C off
     */    
    public void turnPortCoff()
    {
        portC = 0;
    }
    
    
    /**
     * Sets the image of this icon
     */
    public void setImage()
    {
        removeAll();
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());
        
        // creating the top panel
        javax.swing.JPanel jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        jp.setPreferredSize(new java.awt.Dimension(40, 30));
        javax.swing.ImageIcon nic;
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/floatmotor/topfloat.gif"));
        PicButton pic = new PicButton(nic);
        jp.add(pic);
        
        // creating the bottom panel
        javax.swing.JPanel jp2 = new javax.swing.JPanel();
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(40, 10));
        if(portA == 0)
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/floatmotor/Anofloat.gif"));
        }
        else
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/floatmotor/Afloat.gif"));
        }
        pic = new PicButton(nic); 
        jp2.add(pic);
        
        if(portB == 0)
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/floatmotor/Bnofloat.gif"));
        }
        else
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/floatmotor/Bfloat.gif"));
        }
        pic = new PicButton(nic);
        jp2.add(pic);
        
        if(portC == 0)
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/floatmotor/Cnofloat.gif"));
        }
        else
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/floatmotor/Cfloat.gif"));
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

    // the handlers method
    /**
     * Change the status of output port A
     * @param evt MouseEvent
     */
    public void changeA(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.ImageIcon nic;
            if(portA == 0) // not turned off yet
            {
                portA = 1;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/floatmotor/Afloat.gif"));
            }
            else
            {
                portA = 0;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/floatmotor/Anofloat.gif"));
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
     * Change the status of output port B
     * @param evt MouseEvent
     */
    public void changeB(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.ImageIcon nic;
            if(portB == 0) // not turned off yet
            {
                portB = 1;  // now it is turned off
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/floatmotor/Bfloat.gif"));
            }
            else
            {
                portB = 0;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/floatmotor/Bnofloat.gif"));
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
     * Change the status of output port C
     * @param evt MouseEvent
     */
    public void changeC(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.ImageIcon nic;
            if(portC == 0) // not turned off yet
            {
                portC = 1;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/floatmotor/Cfloat.gif"));
            }
            else
            {
                portC = 0;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/motors/floatmotor/Cnofloat.gif"));
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
        javax.swing.JPanel jp = (javax.swing.JPanel) getComponent(1);
        PicButton pic = (PicButton) jp.getComponent(0);
        java.awt.event.MouseListener[] ml = pic.getMouseListeners();
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
     * Turn on the mouse listeners
     */
    public void turnonListeners()
    {
        javax.swing.JPanel jp;
        PicButton pic;
        
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
     * Get the help title of this float motor icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Float Motor Icon";
        return s;
    }
    
    /**
     * Get help message of this float motor icon
     * @return Help message
     */        
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nPort A: ";
        if(portA == 0)
        {
            s+="NO ACTION";
        }
        else if(portA == 1)
        {
            s+="FLOAT";
        }
        s+="\nPort B: ";
        if(portB == 0)
        {
            s+="NO ACTION";
        }
        else if(portB == 1)
        {
            s+="FLOAT";
        }
        s+="\nPort C: ";
        if(portC == 0)
        {
            s+="NO ACTION";
        }
        else if(portC == 1)
        {
            s+="FLOAT";
        }
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */      
    public String getHelpDesc()
    {
        String s = "This icon floats all of the output\nconnected to the specified ports.";
        return s;
    }

    /**
     * Get help picture of this icon
     * @return Help picture
     */    
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/floatMotorIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public floatMotorIcon Clone()
    {
        return new floatMotorIcon("/icons/motors/floatmotor/floatmotor.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        floatMotorIcon newMI = Clone();
        if(portA == 1)
        {
            newMI.floatPortA();
        }
        else
        {
            newMI.turnPortAoff();
        }        
        if(portB == 1)
        {
            newMI.floatPortB();
        }
        else
        {
            newMI.turnPortBoff();
        }        
        if(portC == 1)
        {
            newMI.floatPortC();
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
        NQCCode += "// float the outputs\n";
        NQCCode += indentation;
        NQCCode += "Float(";
        NQCCode += output;
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
        
        // do the first one --> portA
        sp2 = sp[0].split(att_value_connector);
        portA = Integer.parseInt(sp2[1]);
        
        // do the second one --> portB
        sp2 = sp[1].split(att_value_connector);
        portB = Integer.parseInt(sp2[1]);
        
        // do the third one --> portC
        sp2 = sp[2].split(att_value_connector);
        portC = Integer.parseInt(sp2[1]);
    }
    
}
