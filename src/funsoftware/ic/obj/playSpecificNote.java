/*
 * playSpecificNote.java
 *
 * Created on 30 January 2006, 10:00
 *
 */

package funsoftware.ic.obj;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.st.*;
import funsoftware.consts.*;

/**
 * This icon plays a discrete as indicated by the user
 * @author Thomas Legowo
 */
public class playSpecificNote extends objectIcon{
    
    // private variables
    private String note;    // possible notes --> C, C#, D, D#, E, E#, F, F#, G, G#, A, A#, B, B#
    
    private int duration;   // the amount of time this note will be played in (per 10ms)
    private int octave;     // possible values between 1 and 7

    // for algorithm representation, represent a playspecificnote icon with an identifier of 14
    private int identifier;    
    
    /** Creates a new instance of playSpecificNote */
    public playSpecificNote() {
    }

    /**
     * Creates a new instance of playSpecificNote Icon
     * @param filepath Source file of this icon's image
     */
    public playSpecificNote(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this)); // for startIcon the size of the icon is 26x61
        duration = 100;
        octave = 4;
        identifier = 14;
        note = "C";
        setImage();
    }    

    /**
     * Helps provide the transparent background
     * @param g Graphic
     */
    protected void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/notes/specific/durationField.gif"));
        g.drawImage(ii.getImage(), 0, 24, this);
        
        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/notes/specific/noteField.gif"));
        g.drawImage(ii.getImage(), 20, 8, this);
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
     * Returns the octave
     * @return Octave
     */
    public int getOctave()
    {
        return octave;
    }
    
    /**
     * Returns the note
     * @return Note
     */
    public String getNote()
    {
        return note;
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
     * Sets the octave
     * @param no Octave
     */
    public void setOctave(int no)
    {
        octave = no;
    }    
    
    /**
     * Sets the note
     * @param nno New note
     */
    public void setNote(String nno)
    {
        note = nno;
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
        
        javax.swing.JPanel jp1 = new javax.swing.JPanel();
        javax.swing.JPanel jp2 = new javax.swing.JPanel();
        javax.swing.JPanel jp3 = new javax.swing.JPanel();
        
        jp1.setLayout(new java.awt.GridBagLayout());
        jp1.setPreferredSize(new java.awt.Dimension(40, 24));
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/notes/specific/topleft.gif"));
        PicButton pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp1.add(pic, gridBagConstraints);        
                
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(20, 24));
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/notes/specific/topright.gif"));
        pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp2.add(pic, gridBagConstraints);       
        
        jp3.setLayout(new java.awt.GridBagLayout());
        jp3.setPreferredSize(new java.awt.Dimension(20, 16));
        
        javax.swing.JLabel noteLabel = new javax.swing.JLabel(note);
        noteLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        noteLabel.setPreferredSize(new java.awt.Dimension(20, 16));
        noteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp3.add(noteLabel, gridBagConstraints); 
        jp3.setOpaque(false);
        jp2.setOpaque(false);
        jp1.setOpaque(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jp2.add(jp3, gridBagConstraints);          
        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jp1.add(jp2, gridBagConstraints);  
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jp1, gridBagConstraints);
        
        
        jp1 = new javax.swing.JPanel();
        jp2 = new javax.swing.JPanel();
        jp3 = new javax.swing.JPanel();        
        
        jp1.setLayout(new java.awt.GridBagLayout());
        jp1.setPreferredSize(new java.awt.Dimension(40, 16));      
        
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(26, 16));
        
        javax.swing.JLabel durationLabel = new javax.swing.JLabel(Integer.toString(duration));
        durationLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        durationLabel.setPreferredSize(new java.awt.Dimension(26, 16));
        durationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp2.add(durationLabel, gridBagConstraints);  
        jp2.setOpaque(false);
        jp1.setOpaque(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp1.add(jp2, gridBagConstraints);  
        
        jp3.setLayout(new java.awt.GridBagLayout());
        jp3.setPreferredSize(new java.awt.Dimension(14, 16));
        
        javax.swing.JLabel octaveLabel = new javax.swing.JLabel(Integer.toString(octave));
        octaveLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        octaveLabel.setPreferredSize(new java.awt.Dimension(14, 16));
        octaveLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jp3.add(octaveLabel, gridBagConstraints); 
        jp3.setOpaque(false);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jp1.add(jp3, gridBagConstraints);         
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(jp1, gridBagConstraints);        
        
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(40, 40));        
    }    

    /**
     * Change the note
     * @param evt MouseEvent
     */
    public void changeNote(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.JTextField textField = new javax.swing.JTextField(note);
            textField.setFont(new java.awt.Font("Tahoma", 1, 11));
            textField.setPreferredSize(new java.awt.Dimension(20,16));
            textField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            textField.setOpaque(false);

            java.awt.GridBagConstraints gridBagConstraints;

            javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(0);
            jp = (javax.swing.JPanel)jp.getComponent(1);
            jp = (javax.swing.JPanel)jp.getComponent(1);
            jp.remove(0);

            jp.add(textField);

            repaint();
            validate();
            textField.requestFocusInWindow();

            textField.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        //stop edit mode (Enter-key)
                        stopNoteEditMode();
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
     * Change the duration
     * @param evt MouseEvent
     */
    public void changeDuration(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.JTextField textField = new javax.swing.JTextField(Integer.toString(duration));
            textField.setFont(new java.awt.Font("Tahoma", 1, 11));
            textField.setPreferredSize(new java.awt.Dimension(26,16));
            textField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            textField.setOpaque(false);

            java.awt.GridBagConstraints gridBagConstraints;

            javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
            jp = (javax.swing.JPanel)jp.getComponent(0);
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
     * Change the octave
     * @param evt MouseEvent
     */
    public void changeOctave(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            javax.swing.JTextField textField = new javax.swing.JTextField(Integer.toString(octave));
            textField.setFont(new java.awt.Font("Tahoma", 1, 11));
            textField.setPreferredSize(new java.awt.Dimension(14,16));
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
                        stopOctaveEditMode();
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
     * This is to handle click that stops edit mode for note editing
     */
    public void stopNoteEditMode()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(0);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        javax.swing.JTextField textField = (javax.swing.JTextField)jp.getComponent(0);
        String text = textField.getText();
              
        String tmp_note = note;
        try
        {
            note = text;
            if(note.compareTo("C")  != 0 && note.compareTo("C#") != 0 && note.compareTo("D")  != 0 &&
               note.compareTo("D#") != 0 && note.compareTo("E")  != 0 && note.compareTo("F")  != 0 &&
               note.compareTo("F#") != 0 && note.compareTo("G")  != 0 && note.compareTo("G#") != 0 &&
               note.compareTo("A")  != 0 && note.compareTo("A#") != 0 && note.compareTo("B")  != 0)
            {
                javax.swing.JOptionPane.showMessageDialog(null,
                    "Input value for the note is invalid.\nPossible values are C, C#, D, D#, E, F, F#, G, G#, A, A#, B.\nPlease Try Again.",
                    "Input Invalid",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                note = tmp_note;
            }
        } 
        catch(RuntimeException e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Input value for the note is invalid.\nPossible values are C, C#, D, D#, E, F, F#, G, G#, A, A#, B.\nPlease Try Again.",
                "Input Invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
        };
        
        // set the states for undo and redo
        if(tmp_note.compareTo(note) != 0)  // if values are different then store the state for undo and redo (to avoid redundancy)
        {
            UndoRedo.update_state();
        }     
            
        // put the original stuffs back into the jpanel
       
        java.awt.GridBagConstraints gridBagConstraints;
        
        jp.remove(0);
        
        javax.swing.JLabel noteLabel = new javax.swing.JLabel(note);
        noteLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        noteLabel.setPreferredSize(new java.awt.Dimension(20, 16));
        noteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);  
        jp.add(noteLabel);

        jp.setOpaque(false);
            
        repaint();
        validate();
    }    
    
    
    /**
     * This is to handle click that stops edit mode
     */
    public void stopDurationEditMode()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
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
        durationLabel.setPreferredSize(new java.awt.Dimension(26, 16));
        durationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jp.add(durationLabel);

        jp.setOpaque(false);
            
        repaint();
        validate();
    }
    
    
    /**
     * This is to handle click that stops edit mode for octave input
     */
    public void stopOctaveEditMode()
    {
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        javax.swing.JTextField textField = (javax.swing.JTextField)jp.getComponent(0);
        String text = textField.getText();
        
        int tmp_octave = octave;
        try
        {
            octave = Integer.parseInt(text);
            if(octave < 1 || octave > 7)   // possible values for octave are between 1 and 7
            {
                javax.swing.JOptionPane.showMessageDialog(null,
                    "Input value for the octave is invalid.\nPlease enter a value between 1 and 7.\nPlease Try Again.",
                    "Input Invalid",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                octave = tmp_octave;
            }
        } 
        catch(RuntimeException e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Input value for the octave is invalid.\nEnter only numbers between 1 and 7.\nPlease Try Again.",
                "Input Invalid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
        };
                
        // set the states for undo and redo
        if(tmp_octave != octave)  // only if values are different then store the state for undo and redo (to avoid redundancy)
        {
            UndoRedo.update_state();
        }
            
        // put the original stuffs back into the jpanel
       
        java.awt.GridBagConstraints gridBagConstraints;
        
        jp.remove(0);
        
        javax.swing.JLabel octaveLabel = new javax.swing.JLabel(Integer.toString(octave));
        octaveLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        octaveLabel.setPreferredSize(new java.awt.Dimension(14, 16));
        octaveLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);         
        
        jp.add(octaveLabel);
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
        jp = (javax.swing.JPanel)jp.getComponent(0);
        java.awt.event.MouseListener[] ml = jp.getMouseListeners();
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
        
        jp = (javax.swing.JPanel)getComponent(0);
        jp = (javax.swing.JPanel)jp.getComponent(1);
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
        javax.swing.JPanel jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(0);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeDuration(evt);
            }
        });
        
        jp = (javax.swing.JPanel)getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeOctave(evt);
            }
        });
        
        jp = (javax.swing.JPanel)getComponent(0);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        jp = (javax.swing.JPanel)jp.getComponent(1);
        jp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeNote(evt);
            }
        });        
    }        
        
    /**
     * Get the help title of this playspecificnote icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Play A Note Icon";
        return s;
    }
    
    /**
     * Get help message of this playspecificnote icon
     * @return Help message
     */     
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nNote: ";
        s+=note;        
        s+="\nDuration of tone played: ";
        float tmp = (float) duration;
        s+=tmp/100+" seconds";
        s+="\nOctave of tone played: ";
        s+=octave;
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */     
    public String getHelpDesc()
    {
        String s = "This icon plays a note according\nto the specified duration, octave and note";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */    
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/playSpecificNoteIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public playSpecificNote Clone()
    {
        return new playSpecificNote("/icons/notes/specific/specific.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        playSpecificNote newPI = Clone();
        newPI.setDuration(duration);
        newPI.setOctave(octave);
        newPI.setNote(note);
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
        algo += "note"+att_value_connector;
        algo += note;
        algo += att_separator;        
        algo += "duration"+att_value_connector;
        algo += duration;
        algo += att_separator;
        algo += "octave"+att_value_connector;
        algo += octave;
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
        NQCCode += findFrequency()+","+duration;
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
        
        // do the first one --> note
        sp2 = sp[0].split(att_value_connector);
        note = sp2[1];
        // do the second one --> duration
        sp2 = sp[1].split(att_value_connector);
        duration = Integer.parseInt(sp2[1]);        
        // do the third one --> octave
        sp2 = sp[2].split(att_value_connector);
        octave = Integer.parseInt(sp2[1]);
    } 
    
    // to find a suitable frequency of a note
    private int findFrequency()
    {
        int frequency;
        
        int vertical_column = octave-1;
        int horizontal_column = 0;
        
        
        if(note.compareTo("C") == 0)
        {
            horizontal_column = 0;
        }
        else if(note.compareTo("C#") == 0)
        {
            horizontal_column = 1;
        }
        else if(note.compareTo("D") == 0)
        {
            horizontal_column = 2;
        }
        else if(note.compareTo("D#") == 0)
        {
            horizontal_column = 3;
        }
        else if(note.compareTo("E") == 0)
        {
            horizontal_column = 4;
        }
        else if(note.compareTo("F") == 0)
        {
            horizontal_column = 5;
        }
        else if(note.compareTo("F#") == 0)
        {
            horizontal_column = 6;
        }
        else if(note.compareTo("G") == 0)
        {
            horizontal_column = 7;
        }
        else if(note.compareTo("G#") == 0)
        {
            horizontal_column = 8;
        }
        else if(note.compareTo("A") == 0)
        {
            horizontal_column = 9;
        }
        else if(note.compareTo("A#") == 0)
        {
            horizontal_column = 10;
        }
        else if(note.compareTo("B") == 0)
        {
            horizontal_column = 11;
        }      
        
        frequency = Constants.frequencies[vertical_column][horizontal_column];
        
        return frequency;
    }    
}
