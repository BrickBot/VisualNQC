/*
 * ClearSensorIcon.java
 *
 * Created on 16 December 2005, 16:10
 */

package funsoftware.ic.obj;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.st.*;

/**
 * This class represents an icon that can be used to clear the values of sensors connected to the RCX input ports
 * @author Thomas Legowo
 */
public class clearSensorIcon extends objectIcon{
    
    private int input1;  // if input1 = 0, then all sensors connected to this input 1 would not be cleared
    private int input2;
    private int input3;
    
    // for algorithm representation, represent a stop motor icon with an identifier of 7
    private int identifier;
    
    /** Creates a new instance of ClearSensorIcon */
    public clearSensorIcon() {
    }
    
    /**
     * Creates a new instance of clearsensorIcon
     * @param filepath The source file of this icon's image
     */
    public clearSensorIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); // for startIcon the size of the icon is 26x61
        input1 = 1;
        input2 = 1;
        input3 = 1;
        identifier = 7;
        setImage();
    }

    
    // to get the status of the input sensors
    /**
     * Gets the status of input 1
     * @return input1 status
     */
    public int input1Status()
    {
        return input1;
    }
    
    /**
     * Gets the status of input 2
     * @return input2 status
     */
    public int input2Status()
    {
        return input2;
    }
    
    /**
     * Gets the status of input 3
     * @return input3 status
     */
    public int input3Status()
    {
        return input3;
    }
    
    
    // methods for the input ports, these methods also change the graphical representation of the clear sensor icon's attributes
    // the little boxes with the numbers '1', '2' and '3' will change in background colour depending on whether
    // the sensors connected to the ports are to be cleared
    /**
     * Clear input1
     */
    public void turninput1on()
    {
        input1 = 1;
    }
    /**
     * Don't clear input1
     */    
    public void turninput1off()
    {
        input1 = 0;
    }
    /**
     * Clear input2
     */    
    public void turninput2on()
    {
        input2 = 1;
    }
    /**
     * Don't clear input2
     */    
    public void turninput2off()
    {
        input2 = 0;
    }
    /**
     * Clear input3
     */    
    public void turninput3on()
    {
        input3 = 1;
    }
    /**
     * Don't clear input3
     */    
    public void turninput3off()
    {
        input3 = 0;
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
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/sensors/top.gif"));
        PicButton pic = new PicButton(nic);
        jp.add(pic);
        
        // creating the bottom panel
        javax.swing.JPanel jp2 = new javax.swing.JPanel();
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(40, 10));
        if(input1 == 0)
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/sensors/input1_off.gif"));
        }
        else
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/sensors/input1_clear.gif"));
        }
        pic = new PicButton(nic); 
        jp2.add(pic);
        
        if(input2 == 0)
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/sensors/input2_off.gif"));
        }
        else
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/sensors/input2_clear.gif"));
        }
        pic = new PicButton(nic);
        jp2.add(pic);
        
        if(input3 == 0)
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/sensors/input3_off.gif"));
        }
        else
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/sensors/input3_clear.gif"));
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
     * Change the status of input port 1
     * @param evt MouseEvent
     */
    public void changeInput1(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.ImageIcon nic;
            if(input1 == 0) // not cleared yet
            {
                input1 = 1;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/sensors/input1_clear.gif"));
            }
            else
            {
                input1 = 0;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/sensors/input1_off.gif"));
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
     * Change the status of input port 2
     * @param evt MouseEvent
     */
    public void changeInput2(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.ImageIcon nic;
            if(input2 == 0) // not cleared yet
            {
                input2 = 1;  // now it is cleared
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/sensors/input2_clear.gif"));
            }
            else
            {
                input2 = 0;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/sensors/input2_off.gif"));
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
     * Change the status of input port 3
     * @param evt MouseEvent
     */
    public void changeInput3(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.ImageIcon nic;
            if(input3 == 0) // not cleared yet
            {
                input3 = 1;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/sensors/input3_clear.gif"));
            }
            else
            {
                input3 = 0;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/sensors/input3_off.gif"));
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
     * Turns on the mouse listeners
     */
    public void turnonListeners()
    {
        javax.swing.JPanel jp;
        PicButton pic;
        
        jp = (javax.swing.JPanel) getComponent(1);
        pic = (PicButton) jp.getComponent(0);
        pic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeInput1(evt);
            }
        });
        
        jp = (javax.swing.JPanel) getComponent(1);
        pic = (PicButton) jp.getComponent(1);
        pic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeInput2(evt);
            }
        });
        
        jp = (javax.swing.JPanel) getComponent(1);
        pic = (PicButton) jp.getComponent(2);
        pic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeInput3(evt);
            }
        });
    }
    
    /**
     * Get the help title of this clear sensor icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Clear Sensor Icon";
        return s;
    }
    
    /**
     * Get help message of this clear sensor icon
     * @return Help message
     */        
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nInput 1: ";
        if(input1 == 0)
        {
            s+="No Change";
        }
        else if(input1 == 1)
        {
            s+="Cleared";
        }
        s+="\nInput 2: ";
        if(input2 == 0)
        {
            s+="No Change";
        }
        else if(input2 == 1)
        {
            s+="Cleared";
        }
        s+="\nInput 3: ";
        if(input3 == 0)
        {
            s+="No Change";
        }
        else if(input3 == 1)
        {
            s+="Cleared";
        }
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */      
    public String getHelpDesc()
    {
        String s = "This icon clears the values of all input sensors\nconnected to the specified input ports.";
        return s;
    }

    /**
     * Get help picture of this icon
     * @return Help picture
     */    
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/clearSensorIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public clearSensorIcon Clone()
    {
        return new clearSensorIcon("/icons/sensors/clearsensor.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        clearSensorIcon newMI = Clone();
        if(input1 == 1)
        {
            newMI.turninput1on();
        }
        else
        {
            newMI.turninput1off();
        }        
        if(input2 == 1)
        {
            newMI.turninput2on();
        }
        else
        {
            newMI.turninput2off();
        }        
        if(input3 == 1)
        {
            newMI.turninput3on();
        }       
        else
        {
            newMI.turninput3off();
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
        algo += "input1"+att_value_connector;
        algo += input1;
        algo += att_separator;
        algo += "input2"+att_value_connector;
        algo += input2;
        algo += att_separator;
        algo += "input3"+att_value_connector;
        algo += input3;
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
                
        // temporarily, no attributes mean nothing to do
        if(input1 == 0 && input2 == 0 && input3 == 0)
        {
            return "";
        }
        else
        {
            NQCCode += indentation;
            NQCCode += "// clear the sensors \n";
            if(input1 == 1)
            {
                NQCCode += indentation;
                NQCCode += "ClearSensor(SENSOR_1);\n";
            }
            if(input2 == 1)
            {
                NQCCode += indentation;
                NQCCode += "ClearSensor(SENSOR_2);\n";
            }
            if(input3 == 1)
            {
                NQCCode += indentation;
                NQCCode += "ClearSensor(SENSOR_3);\n";
            }
            NQCCode += "\n";
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
        
        // do the first one --> input1
        sp2 = sp[0].split(att_value_connector);
        input1 = Integer.parseInt(sp2[1]);
        
        // do the second one --> input2
        sp2 = sp[1].split(att_value_connector);
        input2 = Integer.parseInt(sp2[1]);
        
        // do the third one --> input3
        sp2 = sp[2].split(att_value_connector);
        input3 = Integer.parseInt(sp2[1]);
    }
}
