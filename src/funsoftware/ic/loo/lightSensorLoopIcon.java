/*
 * lightSensorLoopIcon.java
 *
 * Created on 6 September 2005, 22:01
 *
 */

package funsoftware.ic.loo;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.st.*;
import funsoftware.var.*;
import funsoftware.consts.*;

/**
 * This class is for the "Repeat" icon that repeats until the light sensor read a value greater than the specified treshold value.
 * @author Thomas Legowo
 */
public class lightSensorLoopIcon extends loopIcon{
    
    private int port;   // with default value being 1, port is the value of the input port of this light sensor
    private int light_sensor_value;   // default value being 55, minimum is 1 and maximum is 100
    private variable var_light_sensor_value;  // alternative to light_sensor_value
    
    private int type;  // if type = 1 it means "greater than"
    
    // for algorithm representation, represent a loop light sensor icon with an identifier of 2
    private int identifier;
    
    /** Creates a new instance of lightSensorLoopIcon */
    public lightSensorLoopIcon() {
    }
    
    /**
     * Creates a new instance of lightSensorLoopIcon
     * @param filepath Source file for the icon's image
     */
    public lightSensorLoopIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));  
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        port = 1;
        light_sensor_value = 55;   // loop until the value read from the RCX's light sensor exceeded 55 by default
        type = 1;
        identifier = 2;
        var_light_sensor_value = null;
        setImage();
    }
 
    /**
     * Helps provide the transparent background
     * @param g Graphic
     */
    protected void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/lightrep/light_field.gif"));
        g.drawImage(ii.getImage(), 14, 24, this);
    }    
    
    /**
     * Returns the port this light sensor is operating in
     * @return Port value
     */
    public int getPort()
    {
        return port;
    }
    
    /**
     * Gets the light sensor value threshold
     * @return Light sensor value threshold
     */
    public int getLightSensorValue()
    {
        return light_sensor_value;
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
     * Sets the port of the sensor
     * @param nport New port value
     */
    public void setPort(int nport)
    {
        port = nport;
    }
    
    /**
     * Sets the light sensor value threshold of the sensor
     * @param nlsv New light sensor value threshold
     */
    public void setLightSensorValue(int nlsv)
    {
        light_sensor_value = nlsv;
    }
    
    /**
     * Set the light sensor value of this icon
     * @param var The light value variable
     */
    public void setVarLight(variable var)
    {
        var_light_sensor_value = var;
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
        
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(40, 24));
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/lightrep/toploop.gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp2.add(pic, gridBagConstraints);
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/lightrep/comp"+type+".gif"));       
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
        jp3.add(jp2, gridBagConstraints);        
        
        jp2 = new javax.swing.JPanel();
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(26, 16));
        
        javax.swing.JLabel lightLabel;
        if(var_light_sensor_value == null)
        {
            lightLabel = new javax.swing.JLabel(Integer.toString(light_sensor_value));
        }
        else
        {
            lightLabel = new javax.swing.JLabel("V");
        }
        lightLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        lightLabel.setPreferredSize(new java.awt.Dimension(26, 16));
        lightLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jp2.setOpaque(false);  
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp2.add(lightLabel, gridBagConstraints);
        
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
     * To change the light sensor value threshold
     * @param evt MouseEvent
     */
    public void changeLightSensorValue(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.JTextField textField;
            if(var_light_sensor_value != null)
            {
                textField = new javax.swing.JTextField("v");
            }
            else
            {
                textField = new javax.swing.JTextField(Integer.toString(light_sensor_value));
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
                        stopEditModeLightSensorValue();
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
            javax.swing.ImageIcon nic = new javax.swing.ImageIcon(getClass().getResource("/icons/loops/lightrep/comp"+type+".gif"));
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
     * Stop the edit mode on port value of this icon
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
        javax.swing.ImageIcon nic;
        PicButton pic;

        jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
        jp.removeAll();
        jp.setLayout(new java.awt.GridBagLayout());

        javax.swing.JPanel jp2 = new javax.swing.JPanel();
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
                    
        repaint();
        validate();
    }
    
    /**
     * Stops the edit mode for the light sensor value threshold
     */
    public void stopEditModeLightSensorValue()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        javax.swing.JTextField textField = (javax.swing.JTextField)jp.getComponent(0);
        String text = textField.getText();
        jp.removeAll();
                    
        // parse the light sensor value
        int tmp_light = light_sensor_value;   // current port number
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
                        var_light_sensor_value = vid.getUserInput();
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
                light_sensor_value = Integer.parseInt(text);
                if(light_sensor_value > 100 || light_sensor_value < 1)    
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                        "Input value for the light sensor threshold is invalid.\nMinimum value is 1 and maximum value is 100.\nPlease Try Again.",
                        "Input Invalid",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    light_sensor_value = tmp_light;
                }
                else
                {
                    var_light_sensor_value = null;
                }                
            }
        }  
        catch(RuntimeException e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Input value for the light sensor threshold is invalid.\nPlease enter numbers only.\nPlease Try Again.",
                "Input Invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
        };
                    
        // set the states for undo and redo
        if(tmp_light != light_sensor_value || var_light_sensor_value != null)  // if values are different then store the state for undo and redo (to avoid redundancy)
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

        javax.swing.JLabel lightLabel;
        if(var_light_sensor_value == null)
        {
            lightLabel = new javax.swing.JLabel(Integer.toString(light_sensor_value));
        }
        else
        {
            lightLabel = new javax.swing.JLabel("V");
        }
        lightLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        lightLabel.setPreferredSize(new java.awt.Dimension(26, 16));
        lightLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jp2.setOpaque(false);     

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp2.add(lightLabel, gridBagConstraints);            
            
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
                changePort(evt);
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
                changeLightSensorValue(evt);
            }
        });        
    }
    
    /**
     * Get the help title of this light sensor loop icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Light Sensor Repeat Icon";
        return s;
    }
    
    /**
     * Get help message of this light sensor loop icon
     * @return Help message
     */     
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nInput Port: ";
        s+=port;
        s+="\nLight Sensor Value Treshold: ";
        if(var_light_sensor_value != null)
        {
            s += "Variable "+var_light_sensor_value.getName();
        }
        else
        {
            s += light_sensor_value;
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
        String s = "This icon repeats a sequence of icons\nuntil the light sensor value is either\nless than or greater than\nthe specified value.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */    
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/lightSensorLoopIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public lightSensorLoopIcon Clone()
    {
        return new lightSensorLoopIcon("/icons/loops/lightrep/light_rep.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        lightSensorLoopIcon newRI = Clone();
        newRI.setPort(port);
        if(var_light_sensor_value != null)
        {
            newRI.setVarLight(var_light_sensor_value);
        }
        else
        {
            newRI.setLightSensorValue(light_sensor_value);    
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
        algo += "port"+att_value_connector;
        algo += port;
        algo += att_separator;
        if(var_light_sensor_value != null)
        {
            algo += "lightsensorvalue"+att_value_connector;
            algo += "V_"+var_light_sensor_value.getName();            
        }
        else
        {
            algo += "lightsensorvalue"+att_value_connector;
            algo += light_sensor_value;            
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
        NQCCode += "// light sensor loop \n";
        
        // first make sure that the loop's sensor is not already defined

        NQCCode += indentation+"if(SensorType(SENSOR_"+port+") != SENSOR_TYPE_LIGHT)\n";
        NQCCode += indentation+"{\n";        
        NQCCode += indentation+"\tSetSensorType(";
        NQCCode += "SENSOR_"+port;
        NQCCode += ", SENSOR_TYPE_LIGHT);\n";
        NQCCode += indentation+"}\n";
        
        NQCCode += indentation;
        NQCCode += "while(SENSOR_"+port+" "+compare+" ";
                
        if(var_light_sensor_value != null)
        {
            NQCCode += var_light_sensor_value.getName()+")\n";
        }
        else
        {
            NQCCode += light_sensor_value+")\n";
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
        
        // do the second one --> light_sensor_value
        sp2 = sp[1].split(att_value_connector);
        if(sp2[1].substring(0,1).compareTo("V") == 0)  // must be a variable
        {
            var_light_sensor_value = var_list.Instance().getVariable(sp2[1].substring(2));
            if(var_light_sensor_value == null)  // must be local, not global
            {
                var_light_sensor_value = getAux().getVariable(sp2[1].substring(2));
            }    
        }
        else
        {
            light_sensor_value = Integer.parseInt(sp2[1]);
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
        return new javax.swing.JLabel("Light Value Threshold");
    }   
    
    /**
     * To indicate whether this icon uses a variable
     * @param var Variable to be checked
     * @return True or false
     */
    public Boolean useAVariable(variable var)
    {
        if(var_light_sensor_value != null)
        {
            if(var_light_sensor_value.getType() == var.getType() && var_light_sensor_value.getName().compareTo(var.getName()) == 0)
            {
                return true;    
            }            
        }
        return false;
    }    
}
