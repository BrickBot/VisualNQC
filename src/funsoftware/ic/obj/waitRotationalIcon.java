/*
 * waitRotationalIcon.java
 *
 * Created on 1 February 2006, 13:41
 *
 */

package funsoftware.ic.obj;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.st.*;
import funsoftware.var.*;
import funsoftware.consts.*;

/**
 * This icon waits until the rotational sensor reads a value less than or greater than a specified value.
 * @author Thomas Legowo
 */
public class waitRotationalIcon extends objectIcon{
    
    // the local variables    
    private int port;   // with default value being 1, port is the value of the input port of this rotational sensor
    private int angle_value;   // default value being 16 (1 rotation)
    private variable var_angle_value;  // alternative to angle_value
    
    private int type;   // originally typed 1, greater than
    
    // for algorithm representation, represent this wait rotation sensor icon with an identifier of 20
    private int identifier;
    
    /** Creates a new instance of waitRotationalIcon */
    public waitRotationalIcon() {
    }
    
    /**
     * Creates a new instance of waitRotationalIcon
     * @param filepath The source file of this icon's image
     */
    public waitRotationalIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        port = 1;
        angle_value = 16;
        identifier = 20;
        var_angle_value = null;
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
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/wait/rotate/rotateField.gif"));
        g.drawImage(ii.getImage(), 14, 24, this);
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
     * Get the angular value of this icon
     * @return Angular value
     */
    public int getAngleValue()
    {
        return angle_value;
    }
    
    /**
     * To set the angular value of this wait rotational sensor icon
     * @param nlsv New angle value
     */
    public void setAngleValue(int nlsv)
    {
        angle_value = nlsv;
    }    
    
    /**
     * To set the angular value of this wait rotational sensor icon
     * @param var The angle value variable
     */
    public void setVarAngle(variable var)
    {
        var_angle_value = var;
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
        PicButton pic;
        
        javax.swing.JPanel jp, jp2;        
                
        // add le stuffs into le jp
        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        jp.setPreferredSize(new java.awt.Dimension(40, 24));         
        
        // add le stuffs into le jp
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/wait/rotate/top_left.gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(pic, gridBagConstraints);        
        
        // displays the comparator according to its type
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/wait/rotate/comp"+type+".gif"));
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
        
        javax.swing.JLabel portLabel = new javax.swing.JLabel(Integer.toString(port));
        portLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        portLabel.setPreferredSize(new java.awt.Dimension(14, 16));
        portLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jp2.setOpaque(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp2.add(portLabel, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(jp2, gridBagConstraints);    
        
        // ---------- the light value panel  
        
        jp2 = new javax.swing.JPanel();
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(26, 16));       
                
        javax.swing.JLabel angleLabel;
        if(var_angle_value == null)
        {
            angleLabel = new javax.swing.JLabel(Integer.toString(angle_value));
        }
        else
        {
            angleLabel = new javax.swing.JLabel("V");
        }
        angleLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        angleLabel.setPreferredSize(new java.awt.Dimension(26, 16));
        angleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jp2.setOpaque(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp2.add(angleLabel, gridBagConstraints);    

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
     * To change the angular value of this wait rotational sensor icon
     * @param evt Mouse event
     */
    public void changeAngleValue(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.JTextField textField;
            if(var_angle_value != null)
            {
                textField = new javax.swing.JTextField("v");
            }
            else
            {
                textField = new javax.swing.JTextField(Integer.toString(angle_value));
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
                        stopEditModeAngle();
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
            javax.swing.ImageIcon nic = new javax.swing.ImageIcon(getClass().getResource("/icons/wait/rotate/comp"+type+".gif"));
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
     * Stop the edit mode in port value of this icon
     */
    public void stopEditModePort()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
        javax.swing.JTextField textField = (javax.swing.JTextField)jp.getComponent(0);
        String text = textField.getText();
        jp.removeAll();
                    
        // parse the port number
        int tmp_port = port;   // current port number
        try{
            port = Integer.parseInt(text);
            if(port > 3 || port < 1)    // maximum of 3 input ports
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
        javax.swing.ImageIcon nic;
        PicButton pic;

        jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
        jp.removeAll();
        jp.setLayout(new java.awt.GridBagLayout());

        javax.swing.JLabel portLabel = new javax.swing.JLabel(Integer.toString(port));
        portLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        portLabel.setPreferredSize(new java.awt.Dimension(14, 16));
        portLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(portLabel, gridBagConstraints);

        jp.setOpaque(false);

        repaint();
        validate();
    }
    
    /**
     * This method is to stop the edit mode of the angle value on this wait icon
     */
    public void stopEditModeAngle()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        javax.swing.JTextField textField = (javax.swing.JTextField)jp.getComponent(0);
        String text = textField.getText();
        jp.removeAll();
                    
        // parse the angle sensor value
        int tmp_angle = angle_value;   // current port number
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
                        var_angle_value = vid.getUserInput();
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
                angle_value = Integer.parseInt(text);
                var_angle_value = null;
            }
        } 
        catch(RuntimeException e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Input value for the rotational sensor threshold is invalid.\nPlease enter numbers greater than 1 only.\nPlease Try Again.",
                "Input Invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
        };
                    
        // set the states for undo and redo
        if(tmp_angle != angle_value || var_angle_value != null)  // if values are different then store the state for undo and redo (to avoid redundancy)
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
            
        javax.swing.JLabel angleLabel;
        if(var_angle_value == null)
        {
            angleLabel = new javax.swing.JLabel(Integer.toString(angle_value));
        }
        else
        {
            angleLabel = new javax.swing.JLabel("V");
        }
        angleLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        angleLabel.setPreferredSize(new java.awt.Dimension(26, 16));
        angleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);        
            
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(angleLabel, gridBagConstraints);
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
        jp = (javax.swing.JPanel)jp.getComponent(0);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    changePort(evt);
            }
        });
        
        jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    changeAngleValue(evt);
            }
        });
    }   
    
    /**
     * Get the help title of this wait rotation sensor icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Wait Rotational Sensor Icon";
        return s;
    }
    
    /**
     * Get help message of this wait rotation sensor icon
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
        s+="\nAngle Value Treshold: ";
        if(var_angle_value != null)
        {
            s += "Variable "+var_angle_value.getName();
        }
        else
        {
            s += angle_value;
        }
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */    
    public String getHelpDesc()
    {
        String s = "This icon make the RCX waits until\nthe rotational sensor reads a certain value.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/waitRotationalIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public waitRotationalIcon Clone()
    {
        return new waitRotationalIcon("/icons/wait/rotate/wait_rotate.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        waitRotationalIcon newWTI = Clone();
        newWTI.setPort(port);
        if(var_angle_value != null)
        {
            newWTI.setVarAngle(var_angle_value);
        }
        else
        {
            newWTI.setAngleValue(angle_value);    
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
        algo += "object";
        algo += id_separator;
        algo += identifier;
        algo += id_att_separator;
        algo += att_boundary_begin;
        algo += "port"+att_value_connector;
        algo += port;
        algo += att_separator;
        if(var_angle_value != null)
        {
            algo += "anglevalue"+att_value_connector;
            algo += "V_"+var_angle_value.getName();            
        }
        else
        {
            algo += "anglevalue"+att_value_connector;
            algo += angle_value;           
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
        NQCCode += "// wait rotational sensor\n";
        
        // first make sure that the loop's sensor is not already defined

        NQCCode += indentation+"if(SensorType(SENSOR_"+port+") != SENSOR_TYPE_ROTATION)\n";
        NQCCode += indentation+"{\n";        
        NQCCode += indentation+"\tSetSensorType(";
        NQCCode += "SENSOR_"+port;
        NQCCode += ", SENSOR_TYPE_ROTATION);\n";
        NQCCode += indentation+"}\n";
        
        NQCCode += indentation;
        NQCCode += "until(SENSOR_"+port+" ";
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
        if(var_angle_value != null)
        {
            NQCCode += " "+var_angle_value.getName()+"){}\n\n";
        }
        else
        {
            NQCCode += " "+angle_value+"){}\n\n";
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
        port = Integer.parseInt(sp2[1]);
        
        // do the second one --> angle_sensor_value
        sp2 = sp[1].split(att_value_connector);
        if(sp2[1].substring(0,1).compareTo("V") == 0)  // must be a variable
        {
            var_angle_value = var_list.Instance().getVariable(sp2[1].substring(2));
            if(var_angle_value == null)  // must be local, not global
            {
                var_angle_value = getAux().getVariable(sp2[1].substring(2));
            }    
        }
        else
        {
            angle_value = Integer.parseInt(sp2[1]);
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
        return new javax.swing.JLabel("Rotational Value Threshold");
    }    
    
    /**
     * To indicate whether this icon uses a variable
     * @param var Variable to be checked
     * @return True or false
     */
    public Boolean useAVariable(variable var)
    {
        if(var_angle_value != null)
        {
            if(var_angle_value.getType() == var.getType() && var_angle_value.getName().compareTo(var.getName()) == 0)
            {
                return true;    
            }            
        }
        return false;
    }   
}
