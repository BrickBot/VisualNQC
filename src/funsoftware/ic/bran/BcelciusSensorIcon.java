/*
 * BcelciusSensorIcon.java
 *
 * Created on 6 September 2005, 21:58
 *
 */

package funsoftware.ic.bran;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.st.*;
import funsoftware.var.*;
import funsoftware.consts.*;

/**
 * This class is for the Branch Temperature Celcius Sensor Icon of the fUNSoftWare
 * @author Thomas Legowo
 */
public class BcelciusSensorIcon extends branchIcon{
    
    // the local variables    
    private int port;          // with default value being 1, port is the value of the input port of this temperature sensor
    private int temperature;   // default value being 30
    private variable var_temperature;  // alternative to temperature
    
    // for algorithm representation, represent a branch temperature celcius sensor icon with an identifier of 3
    private int identifier;
  
    private int type;   // originally type 1, ">" for greater than    
    
    /**
     * Creates a new instance of BcelciusSensorIcon
     */
    public BcelciusSensorIcon() {
    }
    
    /**
     * Creates a new instance of BcelciusSensorIcon
     * @param filepath The source file of the BCelciusSensorIcon icon
     */
    public BcelciusSensorIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        port = 1;
        temperature = 300;
        identifier = 3;
        var_temperature = null;
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
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/branches/celcius/celcius_field.gif"));
        g.drawImage(ii.getImage(), 14, 24, this);
    }    
    
    /**
     * To return the input port this temperature sensor is connected to
     * @return Input port
     */
    public int getPort()
    {
        return port;
    }
    
    /**
     * To get the value of temperature reading from the sensor
     * @return Temperature value
     */
    public int getTemperatureValue()
    {
        return temperature;
    }
    
    /**
     * To set the port attribute
     * @param nport New port
     */
    public void setPort(int nport)
    {
        port = nport;
    }
    
    /**
     * To set the temperature value attribute of this icon
     * @param nt New temperature value
     */
    public void setTemperatureValue(int nt)
    {
        temperature = nt;
    }
    
    /**
     * To set the temperature value attribute of this icon
     * @param var The temperature variable
     */
    public void setVarTemperature(variable var)
    {
        var_temperature = var;
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
        
        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        jp.setPreferredSize(new java.awt.Dimension(40, 24));         
        
        // add le stuffs into le jp
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/branches/celcius/top.gif"));
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
                
        javax.swing.JLabel temperatureLabel;
        if(var_temperature == null)
        {
            temperatureLabel = new javax.swing.JLabel(Integer.toString(temperature));
        }
        else
        {
            temperatureLabel = new javax.swing.JLabel("V");
        } 
        temperatureLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        temperatureLabel.setPreferredSize(new java.awt.Dimension(26, 16));
        temperatureLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jp2.setOpaque(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp2.add(temperatureLabel, gridBagConstraints);    

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
     * To change the value of the port attribute
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
     * To change the temperature attribute of this icon
     * @param evt Mouse event
     */
    public void changeTemperatureValue(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.JTextField textField;
            if(var_temperature != null)
            {
                textField = new javax.swing.JTextField("v");
            }
            else
            {
                textField = new javax.swing.JTextField(Integer.toString(temperature));
            } 
            textField.setFont(new java.awt.Font("Tahoma", 1, 11));
            textField.setPreferredSize(new java.awt.Dimension(26,16));
            textField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);        

            textField.setOpaque(false);

            java.awt.GridBagConstraints gridBagConstraints;
            javax.swing.ImageIcon nic;
            javax.swing.JPanel jp;
            PicButton pic;
            
            jp = (javax.swing.JPanel)getComponent(1);
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
                        stopEditModeTemperature();
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
     * To stop edit mode for port text field of this icon
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
     * Stop the edit mode of the light value on this branch icon
     */
    public void stopEditModeTemperature()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        javax.swing.JTextField textField = (javax.swing.JTextField)jp.getComponent(0);
                    
        String text = textField.getText();
        jp.removeAll();
                    
        // parse the light sensor value
        int tmp_temp = temperature;
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
                        var_temperature = vid.getUserInput();
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
                temperature = Integer.parseInt(text);
                var_temperature = null;
            }
        } 
        catch(RuntimeException e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Input value for the temperature sensor threshold is invalid.\nPlease enter numbers only.\nPlease Try Again.",
                "Input Invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
        };
                    
        // set the states for undo and redo
        if(tmp_temp != temperature || var_temperature != null)  // if values are different then store the state for undo and redo (to avoid redundancy)
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
            
        javax.swing.JLabel temperatureLabel;
        if(var_temperature == null)
        {
            temperatureLabel = new javax.swing.JLabel(Integer.toString(temperature));
        }
        else
        {
            temperatureLabel = new javax.swing.JLabel("V");
        } 
        temperatureLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        temperatureLabel.setPreferredSize(new java.awt.Dimension(26, 16));
        temperatureLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);           
            
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp.add(temperatureLabel, gridBagConstraints);
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
                    changePort(evt);
            }
        });
        
        jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    changeTemperatureValue(evt);
            }
        });
    } 
    

    /**
     * Get the help title of this branch temperature sensor icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Branch Temperature Sensor Icon";
        return s;
    }
    
    /**
     * Get help message of this branch temperature sensor icon
     * @return Help message
     */
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nInput Port: ";
        s+=port;
        s+="\nTemperature Treshold: ";
        if(var_temperature != null)
        {
            s += "Variable "+var_temperature.getName();
        }
        else
        {
            float tmp = (float)temperature;
            s += tmp/10+" degree Celcius";
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
     * Get the description of this icon
     * @return Help Description
     */
    public String getHelpDesc()
    {
        String s = "This icon acts as a branch.\nIt chooses one between two paths\ndepending on whether the reading from the\n" +
                   "temperature sensor (in Celcius)\nis greater or less than and equal to the\nspecified value.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/BcelciusSensorIcon.gif"));
        return ii;
    }

    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public BcelciusSensorIcon Clone()
    {
        return new BcelciusSensorIcon("/icons/branches/celcius/celcius.gif");
    }
 
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        BcelciusSensorIcon newWTI = Clone();
        newWTI.setPort(port);
        if(var_temperature != null)
        {
            newWTI.setVarTemperature(var_temperature);
        }
        else
        {
            newWTI.setTemperatureValue(temperature);    
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
        algo += "port"+att_value_connector;
        algo += port;
        algo += att_separator;
        if(var_temperature != null)
        {
            algo += "temperature"+att_value_connector;
            algo += "V_"+var_temperature.getName();            
        }
        else
        {
            algo += "temperature"+att_value_connector;
            algo += temperature;            
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
        NQCCode += "// temperature celcius sensor test(branch) \n";
        
        // first make sure that the branch's sensor is not already defined

        NQCCode += indentation+"if(SensorType(SENSOR_"+port+") != SENSOR_TYPE_TEMPERATURE)\n";
        NQCCode += indentation+"{\n";        
        NQCCode += indentation+"\tSetSensorType(";
        NQCCode += "SENSOR_"+port;
        NQCCode += ", SENSOR_TYPE_TEMPERATURE);\n";
        NQCCode += indentation+"}\n";
        
        NQCCode += indentation;
        NQCCode += "if(SENSOR_"+port+" ";
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
        if(var_temperature != null)
        {
            NQCCode += " "+var_temperature.getName()+")\n";
        }
        else
        {
            NQCCode += " "+temperature+")\n";
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
        
        // do the second one --> temperature
        sp2 = sp[1].split(att_value_connector); 
        if(sp2[1].substring(0,1).compareTo("V") == 0)  // must be a variable
        {
            var_temperature = var_list.Instance().getVariable(sp2[1].substring(2));
            if(var_temperature == null)  // must be local, not global
            {
                var_temperature = getAux().getVariable(sp2[1].substring(2));
            }    
        }
        else
        {
            temperature = Integer.parseInt(sp2[1]);
        }
        // do the third one --> comparator
        sp2 = sp[2].split(att_value_connector);
        type = Integer.parseInt(sp2[1]);             
    }
 
    /**
     * To display an input panel to ask for a variable to be used
     * @return The label for the input panel
     */
    public javax.swing.JLabel getVarLabel()
    {     
        return new javax.swing.JLabel("Temperature Value Threshold");
    }  
    
    /**
     * To indicate whether this icon uses a variable
     * @param var Variable to be checked
     * @return True or false
     */
    public Boolean useAVariable(variable var)
    {
        if(var_temperature != null)
        {
            if(var_temperature.getType() == var.getType() && var_temperature.getName().compareTo(var.getName()) == 0)
            {
                return true;    
            }            
        }
        return false;
    }     
}
