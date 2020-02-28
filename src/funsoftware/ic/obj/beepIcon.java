/*
 * beepIcon.java
 *
 * Created on 26 August 2005, 18:34
 */

package funsoftware.ic.obj;

import funsoftware.ic.*;
import funsoftware.st.*;

/**
 * This class handles the icon that manages the robot when it comes to making the beeping sound
 * @author Thomas Legowo 
 * 
 */
public class beepIcon extends objectIcon{
    
    private int sound_type;  // ranging from 1 to 6
    
    // for algorithm representation, represent a beep icon with an identifier of 5
    private int identifier;
    private double zoom_scale;
    /** Creates a new instance of beepIcon */
    public beepIcon() {
    }
    
    /**
     * Creates a new instance of beepIcon
     * @param filepath The source file of this icon's image
     */
    public beepIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); 
        sound_type = 6;  // initially is 6
        identifier = 5;
        zoom_scale = 1;
        setImage();
    }    
    
    // now methods dealing with the other attribute of this beep icon
    /**
     * Gets the sound type
     * @return Sound type
     */
    public int getSoundType()
    {
        return sound_type;
    }
    
    /**
     * Sets the sound type
     * @param nst Sound type
     */
    public void setSoundType(int nst)
    {
        sound_type = nst;
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
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/beeps/topbeep.gif"));
        PicButton pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(pic, gridBagConstraints);
        
        javax.swing.JPanel jp2 = new javax.swing.JPanel();
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(40, 16));
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/beeps/bottomleftbeep.gif"));
        pic = new PicButton(nic);

        jp2.add(pic);
        
        javax.swing.JLabel beepLabel = new javax.swing.JLabel(Integer.toString(sound_type));
        beepLabel.setFont(new java.awt.Font("Tahoma", 1, 10));
        beepLabel.setPreferredSize(new java.awt.Dimension(14, 16));
        beepLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jp2.setOpaque(false);
        setOpaque(false);

        jp2.add(beepLabel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(jp2, gridBagConstraints);
                
        setPreferredSize(new java.awt.Dimension(40, 40));
    }
    
    /**
     * To change the sound type
     * @param evt MouseEvent
     */
    public void changeSoundType(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            // the text field to edit the beep sound
            javax.swing.JTextField textField = new javax.swing.JTextField(Integer.toString(sound_type));
            textField.setFont(new java.awt.Font("Tahoma", 1, 11));
            textField.setPreferredSize(new java.awt.Dimension(14,16));        
            textField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            textField.setOpaque(false);

            java.awt.GridBagConstraints gridBagConstraints;

            removeAll();
            setLayout(new java.awt.GridBagLayout());

            javax.swing.ImageIcon nic;

            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/beeps/topbeep.gif"));
            PicButton pic = new PicButton(nic);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            add(pic, gridBagConstraints);

            javax.swing.JPanel jp2 = new javax.swing.JPanel();
            jp2.setLayout(new java.awt.GridBagLayout());
            jp2.setPreferredSize(new java.awt.Dimension(40, 16));
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/beeps/bottomleftbeep.gif"));
            pic = new PicButton(nic);

            jp2.add(pic);
            jp2.setOpaque(false);
            setOpaque(false);

            jp2.add(textField);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            add(jp2, gridBagConstraints);        

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
     * To handle click that stops edit mode
     */
    public void stopEditMode()
    {
        javax.swing.JPanel panel = (javax.swing.JPanel)getComponent(1);
        javax.swing.JTextField textField = (javax.swing.JTextField)panel.getComponent(1);
        String text = textField.getText();
        removeAll();
                    
        // set the number of loops
        int tmp_st = sound_type;
        try
        {
            sound_type = Integer.parseInt(text);
            if(sound_type > 6 || sound_type < 1) 
            {
                javax.swing.JOptionPane.showMessageDialog(null,
                    "Input value for the beep's sound type is invalid.\nPermitted value is between 1 to 6.\nPlease Try Again.",
                    "Input Invalid",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                sound_type = tmp_st;
            }
        } 
        catch(RuntimeException e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                            "Input value for the beep's sound type is invalid.\nPlease enter numbers (1 to 6) only.\nPlease Try Again.",
                            "Input Invalid",
            javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        
        // set the states for undo and redo
        if(tmp_st != sound_type)  // if values are different then store the state for undo and redo (to avoid redundancy)
        {
            UndoRedo.update_state();
        }
        
        // reset the icon's graphics                    
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
                changeSoundType(evt);
            }
        });
    }

    /**
     * Get the help title of this beep icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Beep Icon";
        return s;
    }
    
    /**
     * Get help message of this beep icon
     * @return Help message
     */    
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nSound Type: ";
        s+=sound_type;
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */    
    public String getHelpDesc()
    {
        String s = "This icon plays one of six\ndifferent beeping sounds.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/beepIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public beepIcon Clone()
    {
        return new beepIcon("/icons/beeps/beeps.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        beepIcon newBI = Clone();
        newBI.setSoundType(sound_type);
        newBI.setImage();
        return newBI;
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
        algo += "soundtype"+att_value_connector;
        algo += sound_type;
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
        
        int output = sound_type - 1;

        NQCCode += indentation+"// playing a tune \n";
        NQCCode += indentation+"PlaySound(";
        NQCCode += output;
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
        
        // do the first one --> sound type
        sp2 = sp[0].split(att_value_connector);
        sound_type = Integer.parseInt(sp2[1]);
    }
}
