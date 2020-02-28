/*
 * playAnyNoteIcon.java
 *
 * Created on 17 December 2005, 18:45
 */

package funsoftware.ic.obj;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.st.*;
import funsoftware.var.*;
import funsoftware.consts.*;

/**
 * This class represents the icon that can play notes on the specified frequency and period
 * @author Thomas Legowo
 */
public class playAnyNoteIcon extends objectIcon{
    
    // other private attributes
    
    private int duration;   // the amount of time this note will be played in (per 10ms)
    private int frequency;  // 0 frequency of this note
    private variable var_frequency;  // alternative to frequency

    // for algorithm representation, represent a playanynote icon with an identifier of 9
    private int identifier;
    
    /** Creates a new instance of playAnyNoteIcon */
    public playAnyNoteIcon() {
    }
    
    /**
     * Creates a new instance of playanynote Icon
     * @param filepath Source file of this icon's image
     */
    public playAnyNoteIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); // for startIcon the size of the icon is 26x61
        duration = 100;
        frequency = 33;
        identifier = 9;
        var_frequency =  null;
        setImage();
    }
    
    /**
     * Helps to provide the transparent background
     * @param g Graphic
     */
    protected void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/notes/free/textField.gif"));
        g.drawImage(ii.getImage(), 15, 1, this);
        
        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/notes/free/textField.gif"));
        g.drawImage(ii.getImage(), 15, 23, this);
    } 
    
    /**
     * Returns the duration
     * @return Duration value
     */
    public int getDuration()
    {
        return duration;
    }
    
    /**
     * Returns the frequency
     * @return Frequency
     */
    public int getFrequency()
    {
        return frequency;
    }
    
    /**
     * Sets the duration
     * @param ndur Duration
     */
    public void setDuration(int ndur)
    {
        duration = ndur;
    }
    
    /**
     * Sets the frequency
     * @param nfre Frequency
     */
    public void setFrequency(int nfre)
    {
        frequency = nfre;
    }
    
    /**
     * Sets the variable frequency
     * @param newv The new frequency variable
     */
    public void setVarFrequency(variable newv)
    {
        var_frequency = newv;
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
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/notes/free/left.gif"));
        PicButton pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(pic, gridBagConstraints);
        
        javax.swing.JPanel jp2 = new javax.swing.JPanel();
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(25, 40));
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/notes/free/line.gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp2.add(pic, gridBagConstraints);
        
        javax.swing.JPanel jp3 = new javax.swing.JPanel();
        jp3.setLayout(new java.awt.GridBagLayout());
        jp3.setPreferredSize(new java.awt.Dimension(25, 16));
        
        javax.swing.JLabel durationLabel = new javax.swing.JLabel(Integer.toString(duration));
        durationLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        durationLabel.setPreferredSize(new java.awt.Dimension(25, 16));
        durationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp3.add(durationLabel, gridBagConstraints);
        jp3.setOpaque(false);
                
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jp2.add(jp3, gridBagConstraints);
        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/notes/free/middle.gif"));
        pic = new PicButton(nic);
        jp2.add(pic, gridBagConstraints);
        
        javax.swing.JLabel frequencyLabel;
        if(var_frequency == null)
        {
            frequencyLabel = new javax.swing.JLabel(Integer.toString(frequency));
        }
        else
        {
            frequencyLabel = new javax.swing.JLabel("V");
        }
        frequencyLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        frequencyLabel.setPreferredSize(new java.awt.Dimension(25, 16));
        frequencyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        jp3 = new javax.swing.JPanel();
        jp3.setLayout(new java.awt.GridBagLayout());
        jp3.setPreferredSize(new java.awt.Dimension(25, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;                
        jp3.add(frequencyLabel, gridBagConstraints);
        jp3.setOpaque(false);
                
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jp2.add(jp3, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/notes/free/line.gif"));
        pic = new PicButton(nic);
        jp2.add(pic, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        add(jp2, gridBagConstraints);        
        
        jp2.setOpaque(false);
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(40, 40));        
    }
    
    /**
     * Change the duration
     * @param evt MouseEvent
     */
    public void changeDuration(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.JTextField textField = new javax.swing.JTextField(Integer.toString(duration));
            textField.setFont(new java.awt.Font("Tahoma", 1, 11));
            textField.setPreferredSize(new java.awt.Dimension(25,16));
            textField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            textField.setOpaque(false);

            java.awt.GridBagConstraints gridBagConstraints;

            javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
            jp = (javax.swing.JPanel)jp.getComponent(1);
            jp.remove(0);

            jp.add(textField);

            repaint();
            validate();
            textField.requestFocusInWindow();

            textField.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        //stop edit mode (Enter-key)
                        stopDurationEditMode();
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
     * Change the frequency
     * @param evt MouseEvent
     */
    public void changeFrequency(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.JTextField textField;
            if(var_frequency != null)
            {
                textField = new javax.swing.JTextField("v");
            }
            else
            {
                textField = new javax.swing.JTextField(Integer.toString(frequency));
            }
            textField.setFont(new java.awt.Font("Tahoma", 1, 11));
            textField.setPreferredSize(new java.awt.Dimension(25,16));
            textField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            textField.setOpaque(false);

            java.awt.GridBagConstraints gridBagConstraints;

            javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
            jp = (javax.swing.JPanel)jp.getComponent(3);
            jp.remove(0);

            jp.add(textField);

            repaint();
            validate();
            textField.requestFocusInWindow();

            textField.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        //stop edit mode (Enter-key)
                        stopFrequencyEditMode();
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
    public void stopDurationEditMode()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        javax.swing.JTextField textField = (javax.swing.JTextField)jp.getComponent(0);
        String text = textField.getText();
              
        int tmp_duration = duration;
        try
        {
            duration = Integer.parseInt(text);
            if(duration <= 0)   // it is pointless to time for 0 or negative values
            {
                javax.swing.JOptionPane.showMessageDialog(null,
                                    "Input value for the duration is invalid.\nPlease enter value greater than 0.\nPlease Try Again.",
                                    "Input Invalid",
                                    javax.swing.JOptionPane.WARNING_MESSAGE);
                duration = tmp_duration;
            }

        } 
        catch(RuntimeException e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                        "Input value for the duration is invalid.\nEnter only numbers greater than zero.\nPlease Try Again.",
                        "Input Invalid",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
        };
        
        // set the states for undo and redo
        if(tmp_duration != duration)  // if values are different then store the state for undo and redo (to avoid redundancy)
        {
            UndoRedo.update_state();
        }     
            
        // put the original stuffs back into the jpanel
       
        java.awt.GridBagConstraints gridBagConstraints;
        
        jp.remove(0);
        
        javax.swing.JLabel durationLabel = new javax.swing.JLabel(Integer.toString(duration));
        durationLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        durationLabel.setPreferredSize(new java.awt.Dimension(25, 16));
        durationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jp.add(durationLabel);

        jp.setOpaque(false);
            
        repaint();
        validate();
    }
    
    
    /**
     * This is to handle click that stops edit mode
     */
    public void stopFrequencyEditMode()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(3);
        javax.swing.JTextField textField = (javax.swing.JTextField)jp.getComponent(0);
        String text = textField.getText();
        
        int tmp_frequency = frequency;
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
                        var_frequency = vid.getUserInput();
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
                frequency = Integer.parseInt(text);
                if(frequency <= 0)   // it is pointless to time for 0 or negative values
                {
                    javax.swing.JOptionPane.showMessageDialog(null,
                                        "Input value for the frequency is invalid.\nPlease enter value greater than 0.\nPlease Try Again.",
                                        "Input Invalid",
                                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    frequency = tmp_frequency;
                }
                else
                {
                    var_frequency = null;
                }                
            }        
        } 
        catch(RuntimeException e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Input value for the frequency is invalid.\nEnter only numbers greater than zero.\nPlease Try Again.",
                "Input Invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
        };
                
        // set the states for undo and redo
        if(tmp_frequency != frequency || var_frequency != null)  // if values are different then store the state for undo and redo (to avoid redundancy)
        {
            UndoRedo.update_state();
        }
            
        // put the original stuffs back into the jpanel
       
        java.awt.GridBagConstraints gridBagConstraints;
        
        jp.remove(0);
        
        javax.swing.JLabel frequencyLabel;
        if(var_frequency == null)
        {
            frequencyLabel = new javax.swing.JLabel(Integer.toString(frequency));
        }
        else
        {
            frequencyLabel = new javax.swing.JLabel("V");
        }
        frequencyLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        frequencyLabel.setPreferredSize(new java.awt.Dimension(25, 16));
        frequencyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);        
        
        jp.add(frequencyLabel);
        jp.setOpaque(false);
            
        repaint();
        validate();
    }
    
    /**
     * Turns off the mouse listeners
     */
    public void turnoffListeners()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        java.awt.event.MouseListener[] ml = jp.getMouseListeners();
        for(int i=0; i<ml.length; i++)
        {
            jp.removeMouseListener(ml[0]);
        }        
        
        jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(3);
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
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeDuration(evt);
            }
        });
        
        jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(3);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeFrequency(evt);
            }
        });
    }        
        
    /**
     * Get the help title of this playanynote icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Play Any Note Icon";
        return s;
    }
    
    /**
     * Get help message of this playanynote icon
     * @return Help message
     */     
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nDuration of tone played: ";
        
        float tmp = (float) duration;
        s+=tmp/100+" seconds";
        
        s+="\nFrequency of tone played: ";
        if(var_frequency != null)
        {
            s += "Variable "+var_frequency.getName();
        }
        else
        {
            s += frequency;
        }
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */     
    public String getHelpDesc()
    {
        String s = "This icon plays any note according\nto the specified duration and frequency.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */    
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/playAnyNoteIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public playAnyNoteIcon Clone()
    {
        return new playAnyNoteIcon("/icons/notes/free/freenote.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        playAnyNoteIcon newPI = Clone();
        newPI.setDuration(duration);
        if(var_frequency != null)
        {
            newPI.setVarFrequency(var_frequency);
        }
        else
        {
            newPI.setFrequency(frequency);     
        }        
        newPI.setImage();
        return newPI;
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
        algo += "duration"+att_value_connector;
        algo += duration;
        algo += att_separator;
        if(var_frequency != null)
        {
            algo += "frequency"+att_value_connector;
            algo += "V_"+var_frequency.getName();            
        }
        else
        {
            algo += "frequency"+att_value_connector;
            algo += frequency;           
        }
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
        
        NQCCode += indentation+"// Play a note in the specified frequency and duration \n";
        NQCCode += indentation+"PlayTone(";
                
        if(var_frequency != null)
        {
            NQCCode += var_frequency.getName();
        }
        else
        {
            NQCCode += frequency;
        }
        NQCCode += ", "+duration;
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
        
        // do the first one --> duration
        sp2 = sp[0].split(att_value_connector);
        duration = Integer.parseInt(sp2[1]);
        
        // do the second one --> frequency
        sp2 = sp[1].split(att_value_connector);
        if(sp2[1].substring(0,1).compareTo("V") == 0)  // must be a variable
        {
            var_frequency = var_list.Instance().getVariable(sp2[1].substring(2));
            if(var_frequency == null)  // must be local, not global
            {
                var_frequency = getAux().getVariable(sp2[1].substring(2));
            }    
        }
        else
        {
            frequency = Integer.parseInt(sp2[1]);
        }
    }

    /**
     * To display an input panel to ask for a variable to be used
     * @return The label of the panel
     */
    public javax.swing.JLabel getVarLabel()
    {     
        return new javax.swing.JLabel("Note Frequency");
    }    
    
    /**
     * To indicate whether this icon uses a variable
     * @param var Variable to be checked
     * @return True or false
     */
    public Boolean useAVariable(variable var)
    {
        if(var_frequency != null)
        {
            if(var_frequency.getType() == var.getType() && var_frequency.getName().compareTo(var.getName()) == 0)
            {
                return true;    
            }            
        }
        return false;
    }
}
