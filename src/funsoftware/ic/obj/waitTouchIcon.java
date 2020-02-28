/*
 * waitTouchIcon.java
 *
 * Created on 1 February 2006, 13:39
 *
 */

package funsoftware.ic.obj;

import funsoftware.ic.*;
import funsoftware.st.*;

/**
 * This icon waits until the touch sensor is either pushed in or released.
 * @author Thomas Legowo
 */
public class waitTouchIcon extends objectIcon{
            
    private int port;   // with default value being 1, port is the value of the input port of this touch sensor
    // for algorithm representation, represent a wait for touch icon with an identifier of 17
    private int identifier;   
    
    private int type;   // originally typed 1, "Y" for sensor touched
    
    /** Creates a new instance of waitTouchIcon */
    public waitTouchIcon() {
    }
    
    /**
     * Creates a new instance of waitTouchIcon
     * @param filepath The source file of this icon's image
     */
    public waitTouchIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        port = 1;
        identifier = 17;
        type = 1;
        setImage();
    }    
    
    /**
     * Get the port value of this icon
     * @return Port value
     */
    public int getPort()
    {
        return port;
    }
    
    /**
     * Set the port value of this icon
     * @param nport New port value
     */
    public void setPort(int nport)
    {
        port = nport;
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
     * To set the image of this icon
     */
    public void setImage()
    {
        removeAll();
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());
        javax.swing.ImageIcon nic;
        
        javax.swing.JPanel jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        jp.setPreferredSize(new java.awt.Dimension(40, 24));         
        
        // add le stuffs into le jp
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/wait/touch/top_left.gif"));
        PicButton pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(pic, gridBagConstraints);        
        
        // displays the comparator according to its type
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/wait/touch/comp"+type+".gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jp.add(pic, gridBagConstraints);      
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jp, gridBagConstraints); 
        
        javax.swing.JPanel jp2 = new javax.swing.JPanel();
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(40, 16));
        
        javax.swing.JPanel jp3 = new javax.swing.JPanel();
        jp3.setLayout(new java.awt.GridBagLayout());
        jp3.setPreferredSize(new java.awt.Dimension(15, 16));
        
        javax.swing.JLabel portLabel = new javax.swing.JLabel(Integer.toString(port));
        portLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        portLabel.setPreferredSize(new java.awt.Dimension(15, 16));
        portLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp3.add(portLabel, gridBagConstraints);
        jp3.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp2.add(jp3, gridBagConstraints);
        
        jp3 = new javax.swing.JPanel();
        jp3.setLayout(new java.awt.GridBagLayout());
        jp3.setPreferredSize(new java.awt.Dimension(25, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jp2.add(jp3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(jp2, gridBagConstraints);
        
        jp3.setOpaque(false);
        jp2.setOpaque(false);
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(40, 40));        
    }
    
    /**
     * To change the port value of this icon
     * @param evt Mouse event
     */
    public void changePort(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.JTextField textField = new javax.swing.JTextField(Integer.toString(port));
            textField.setFont(new java.awt.Font("Tahoma", 1, 11));
            textField.setPreferredSize(new java.awt.Dimension(15,16));
            textField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);        

            java.awt.GridBagConstraints gridBagConstraints;

            javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
            jp = (javax.swing.JPanel)jp.getComponent(0);
            jp.removeAll();
            jp.setLayout(new java.awt.GridBagLayout());

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            jp.add(textField, gridBagConstraints);


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
     * To change the comparator type of this icon
     * @param evt Mouse event
     */
    public void changeComparator(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.ImageIcon nic = new javax.swing.ImageIcon();
            if(type == 2)
            {
                type = 1;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/wait/touch/comp1.gif"));
            }
            else if(type == 1)
            {
                type = 2;
                nic = new javax.swing.ImageIcon(getClass().getResource("/icons/wait/touch/comp2.gif"));
            } 
            
            javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(0);      
            jp.remove(1);
            // displays the comparator according to its type
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
     * Stop edit mode of this icon
     */
    public void stopEditMode()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
        javax.swing.JTextField textField = (javax.swing.JTextField)jp.getComponent(0);
        String text = textField.getText();
                    
        // parse the unit of the time, second will be taken as default should the user does not indicate
        // the unit
        int tmp_port = port;   // current port number
        try
        {
            port = Integer.parseInt(text);
            if(port > 3 || port <= 0)    // maximum of 3 input ports
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
                "Input value for the input port is invalid.\nPlease enter numbers only.\nPlease Try Again.",
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

        jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
        jp.removeAll();
        jp.setLayout(new java.awt.GridBagLayout());

        javax.swing.JLabel portLabel = new javax.swing.JLabel(Integer.toString(port));
        portLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        portLabel.setPreferredSize(new java.awt.Dimension(15, 16));
        portLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(portLabel, gridBagConstraints);
                    
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
        ml = jp.getMouseListeners();
        for(int i=0; i<ml.length; i++)
        {
            jp.removeMouseListener(ml[0]);
        }  
    }
    
    /**
     * Turns on the mouse listeners
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
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changePort(evt);
            }
        });
    }
    
    /**
     * Get the help title of this wait touch sensor icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Wait Touch Sensor Icon";
        return s;
    }
    
    /**
     * Get help message of this wait touch sensor icon
     * @return Help message
     */    
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nInput Port: ";
        s+=port;
        s+="\nComparator: ";
        if(type == 1)
        {
            s+="Y";
        }
        else if(type == 2)
        {
            s+="N";
        }
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */    
    public String getHelpDesc()
    {
        String s = "This icon make the RCX waits until\nthe touch sensor is pressed or released.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/waitTouchIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public waitTouchIcon Clone()
    {
        return new waitTouchIcon("/icons/wait/touch/wait_touch.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        waitTouchIcon newWTI = Clone();
        newWTI.setPort(port);
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
        algo += "object";
        algo += id_separator;
        algo += identifier;
        algo += id_att_separator;
        algo += att_boundary_begin;
        algo += "port"+att_value_connector;
        algo += port;
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
        NQCCode += "// wait touch sensor\n";
        
        // first make sure that the loop's sensor is not already defined

        NQCCode += indentation+"if(SensorType(SENSOR_"+port+") != SENSOR_TYPE_TOUCH)\n";
        NQCCode += indentation+"{\n";        
        NQCCode += indentation+"\tSetSensorType(";
        NQCCode += "SENSOR_"+port;
        NQCCode += ", SENSOR_TYPE_TOUCH);\n";
        NQCCode += indentation+"}\n";
        
        NQCCode += indentation;
        NQCCode += "until(SENSOR_"+port+" == ";
        if(type == 1)
        {
            NQCCode+="true";
        }
        else if(type == 2)
        {
            NQCCode+="false";
        }                
        NQCCode+="){}\n\n";
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
        
        // do the second one --> comparator
        sp2 = sp[1].split(att_value_connector);
        type = Integer.parseInt(sp2[1]);              
    }  
}
