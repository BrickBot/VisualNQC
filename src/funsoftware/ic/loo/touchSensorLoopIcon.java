/*
 * touchSensorLoopIcon.java
 *
 * Created on 30 January 2006, 10:00
 *
 */

package funsoftware.ic.loo;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.st.*;

/**
 * This icon loops a set of icons if the specified touch sensor is touched
 * @author Thomas Legowo
 */
public class touchSensorLoopIcon extends loopIcon{
    
    private int port;  // with default value being 1, port is the value of the input port of this touch sensor
    private int type;  // default = 0 means "Not touched", if type = 1 it means "Touched"
    
    // for algorithm representation, this touch sensor loop icon has an identifier of 7
    private int identifier;
    
    /** Creates a new instance of touchSensorLoopIcon */
    public touchSensorLoopIcon() {
    }
   
    /**
     * Creates a new instance of touchSensorLoopIcon
     * @param filepath The source file of this icon's image
     */
    public touchSensorLoopIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        identifier = 7;
        port = 1;
        type = 1;
        setImage();
    }    

    /**
     * Returns the port this touch sensor is operating in
     * @return Port value
     */
    public int getPort()
    {
        return port;
    }
        
    /**
     * Returns the type whether it is greater than or less than
     * @return Comparison type (Y or N)
     */
    public int getType()
    {
        return type;
    }
    
    /**
     * Sets the port of the sensor
     * @param nport New port value
     */
    public void setPort(int nport)
    {
        port = nport;
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
        javax.swing.JPanel jp4 = new javax.swing.JPanel();
        
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(29, 40));
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/touch/toptouch.gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp2.add(pic, gridBagConstraints);
        
        jp4.setLayout(new java.awt.GridBagLayout());
        jp4.setPreferredSize(new java.awt.Dimension(29, 16));        
        
        jp3.setLayout(new java.awt.GridBagLayout());
        jp3.setPreferredSize(new java.awt.Dimension(14, 16));
        
        javax.swing.JLabel portLabel = new javax.swing.JLabel(Integer.toString(port));
        portLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        portLabel.setPreferredSize(new java.awt.Dimension(14, 16));
        portLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jp3.setOpaque(false);     
        jp4.setOpaque(false);
        jp2.setOpaque(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp3.add(portLabel, gridBagConstraints);        

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp4.add(jp3, gridBagConstraints); 
        
        jp3 = new javax.swing.JPanel();
        jp3.setPreferredSize(new java.awt.Dimension(15, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jp4.add(jp3, gridBagConstraints); 
        jp3.setOpaque(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jp2.add(jp4, gridBagConstraints);
               

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jp2, gridBagConstraints);        
        
        if(type == 0)
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/touch/signal0.gif"));
        }
        else if(type == 1)
        {
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/touch/signal1.gif"));
        }    
        
        pic = new PicButton(nic);        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        add(pic, gridBagConstraints);        
        
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(40, 40));
    }
    
    /**
     * To change the port of the sensor
     * @param evt MouseEvent
     */
    public void changePort(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.JTextField textField = new javax.swing.JTextField(Integer.toString(port));
            textField.setFont(new java.awt.Font("Tahoma", 1, 11));
            textField.setPreferredSize(new java.awt.Dimension(14,16));
            textField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            textField.setOpaque(false);
            
            java.awt.GridBagConstraints gridBagConstraints;
            javax.swing.ImageIcon nic;
            PicButton pic;

            javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(0);
            jp = (javax.swing.JPanel)jp.getComponent(1);
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
                        stopEditModePort();
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
     * Stop the edit mode on port value of this icon
     */
    public void stopEditModePort()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(0);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
        javax.swing.JTextField textField = (javax.swing.JTextField)jp.getComponent(0);
        String text = textField.getText();
        jp.removeAll();
                    
        // parse the input port
        int tmp_port = port;   // current port number
        try
        {
            port = Integer.parseInt(text);
            if(port > 3 || port < 1)    // 3 ports
            {
                javax.swing.JOptionPane.showMessageDialog(null,
                    "Input value for the input port is invalid.\nIt can be either 1, 2 or 3.\nPlease Try Again.",
                    "Input Invalid",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                    port = tmp_port;
            }
        } 
        catch(RuntimeException e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Input value for the input port is invalid.\nPlease enter numbers only (1 to 3).\nPlease Try Again.",
                "Input Invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
        };
        
        // set the states for undo and redo
        if(tmp_port != port)  // if values are different then store the state for undo and redo (to avoid redundancy)
        {
            UndoRedo.update_state();
        }        

        // put the original stuffs back into the jpanel
        java.awt.GridBagConstraints gridBagConstraints;
        javax.swing.ImageIcon nic;
        PicButton pic;

        jp = (javax.swing.JPanel)getComponent(0);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
        jp.removeAll();
            
        jp.setLayout(new java.awt.GridBagLayout());
        jp.setPreferredSize(new java.awt.Dimension(14, 16));

        javax.swing.JLabel portLabel = new javax.swing.JLabel(Integer.toString(port));
        portLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        portLabel.setPreferredSize(new java.awt.Dimension(14, 16));
        portLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jp.setOpaque(false);     
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(portLabel, gridBagConstraints);                    
                    
        repaint();
        validate();
    }
    
    /**
     * To change the comparator of this loop's ending case (either less than or greater than)
     * @param evt MouseEvent
     */
    public void changeComparator(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.ImageIcon nic = new javax.swing.ImageIcon();
            if(type == 0)
            {
                type = 1;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/touch/signal1.gif"));
            }
            else if(type == 1)
            {
                type = 0;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/touch/signal0.gif"));
            } 
            
            PicButton picpic = (PicButton)getComponent(1);
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
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(0);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        java.awt.event.MouseListener[] ml = jp.getMouseListeners();
        for(int i=0; i<ml.length; i++)
        {
            jp.removeMouseListener(ml[0]);
        }        
        
        PicButton pic = (PicButton)getComponent(1);
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
        PicButton pic;
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(0);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changePort(evt);
            }
        });
        
        pic = (PicButton)getComponent(1);
        pic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeComparator(evt);
            }
        });      
    }
    
    /**
     * Get the help title of this touch sensor loop icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Touch Sensor Repeat Icon";
        return s;
    }
    
    /**
     * Get help message of this touch sensor loop icon
     * @return Help message
     */     
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nInput Port: ";
        s+=port;
        s+="\nType: ";
        if(type == 0)
        {
            s+="Not Touched";
        }
        else if(type == 1)
        {
            s+="Touched";
        }
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */    
    public String getHelpDesc()
    {
        String s = "This icon repeats a sequence of icons\nuntil the touch sensor is either\npushed in or released.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */    
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/touchSensorLoopIcon.gif"));
        return ii;
    }        
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public touchSensorLoopIcon Clone()
    {
        return new touchSensorLoopIcon("/icons/loops/touch/touch.gif");
    }    
 
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        touchSensorLoopIcon newRI = Clone();
        newRI.setPort(port);
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
        algo += "port"+att_value_connector;
        algo += port;
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
            compare = "true";    
        }
        else if(type == 0)
        {
            compare = "false";    
        }        
                
        NQCCode += indentation;
        NQCCode += "// touch sensor loop \n";
        
        // first make sure that the loop's sensor is not already defined

        NQCCode += indentation+"if(SensorType(SENSOR_"+port+") != SENSOR_TYPE_TOUCH)\n";
        NQCCode += indentation+"{\n";        
        NQCCode += indentation+"\tSetSensorType(";
        NQCCode += "SENSOR_"+port;
        NQCCode += ", SENSOR_TYPE_TOUCH);\n";
        NQCCode += indentation+"}\n";
        
        NQCCode += indentation;
        NQCCode += "while(SENSOR_"+port+" == "+compare+")\n";
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
        port = Integer.parseInt(sp2[1]);
        
        // do the first one --> type
        sp2 = sp[1].split(att_value_connector);
        type = Integer.parseInt(sp2[1]);
    }    
    
}
