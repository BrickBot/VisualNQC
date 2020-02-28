/*
 * clearTimerIcon.java
 *
 * Created on 16 December 2005, 17:00
 */

package funsoftware.ic.obj;

import funsoftware.ic.*;
import funsoftware.wr.*;
import funsoftware.st.*;

/**
 * This class represents the clear timer process
 * @author Thomas Legowo
 */
public class clearTimerIcon extends objectIcon{
    
    private int timer; // 0 to 3
    
    // for algorithm representation, represent a cleartimer icon with an identifier of 8
    private int identifier;
    
    /** Creates a new instance of clearTimerIcon */
    public clearTimerIcon() {
    }
    
    /**
     * Creates a new instance of clearTimerIcon
     * @param filepath The source file of this icon's image
     */
    public clearTimerIcon(String filepath)
    {
        super(filepath);
        super.setWidth(super.getImage().getImage().getWidth(this));   // goes vertically
        super.setHeight(super.getImage().getImage().getHeight(this));
        timer = 0; 
        identifier = 8;
        setImage();
    }
    
    /**
     * Helps provide the transparent background
     * @param g Graphic
     */
    protected void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/timers/cleart/timer_field.gif"));
        g.drawImage(ii.getImage(), 26, 24, this);
    }
    
    // now methods dealing with the other attribute of this clear timer icon
    /**
     * Gets the timer type
     * @return Timer type
     */
    public int getTimer()
    {
        return timer;
    }
    
    /**
     * Sets the timer type
     * @param nt The new timer
     */
    public void setTimer(int nt)
    {
        timer = nt;
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
        
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/timers/cleart/top.gif"));
        PicButton pic = new PicButton(nic);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(pic, gridBagConstraints);
        
        javax.swing.JPanel jp2 = new javax.swing.JPanel();
        jp2.setLayout(new java.awt.GridBagLayout());
        jp2.setPreferredSize(new java.awt.Dimension(40, 16));
        nic = new javax.swing.ImageIcon(getClass().getResource("/icons/timers/cleart/bottomleft.gif"));
        pic = new PicButton(nic);

        jp2.add(pic);
        
        javax.swing.JLabel timerLabel = new javax.swing.JLabel(Integer.toString(timer));
        timerLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        timerLabel.setPreferredSize(new java.awt.Dimension(14, 16));
        timerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jp2.setOpaque(false);

        jp2.add(timerLabel);        
                
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(jp2, gridBagConstraints);
        
        setPreferredSize(new java.awt.Dimension(40, 40));
    }
    
    /**
     * To change the timer type
     * @param evt MouseEvent
     */
    public void changeTimer(java.awt.event.MouseEvent evt)
    {
        if(evt.getButton() != 3)  // not a right click
        {
            // the text field to edit the timer type
            javax.swing.JTextField textField = new javax.swing.JTextField(Integer.toString(timer));
            textField.setFont(new java.awt.Font("Tahoma", 1, 11));
            textField.setPreferredSize(new java.awt.Dimension(14,16));
            textField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            textField.setOpaque(false);
            java.awt.GridBagConstraints gridBagConstraints;

            removeAll();
            setLayout(new java.awt.GridBagLayout());

            javax.swing.ImageIcon nic;

            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/timers/cleart/top.gif"));
            PicButton pic = new PicButton(nic);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            add(pic, gridBagConstraints);

            javax.swing.JPanel jp2 = new javax.swing.JPanel();
            jp2.setLayout(new java.awt.GridBagLayout());
            jp2.setPreferredSize(new java.awt.Dimension(40, 16));
            nic = new javax.swing.ImageIcon(getClass().getResource("/icons/timers/cleart/bottomleft.gif"));
            pic = new PicButton(nic);
            jp2.setOpaque(false);
            jp2.add(pic);

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
        int tmp_st = timer;
        try
        {
            timer = Integer.parseInt(text);
            if(timer > 3 || timer < 0) 
            {
                javax.swing.JOptionPane.showMessageDialog(null,
                        "Input value for the timer type is invalid.\nPermitted value is between 0 to 3.\nPlease Try Again.",
                        "Input Invalid",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                timer = tmp_st;
            }
        } 
        catch(RuntimeException e)
        {
            javax.swing.JOptionPane.showMessageDialog(null,
                                    "Input value for the timer type is invalid.\nPlease enter numbers (0 to 3) only.\nPlease Try Again.",
                                    "Input Invalid",
                                    javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        
                            // set the states for undo and redo
        if(tmp_st != timer)  // if values are different then store the state for undo and redo (to avoid redundancy)
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
     * Turn on the mouse listeners
     */
    public void turnonListeners()
    {
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeTimer(evt);
            }
        });
    }

    /**
     * Get the help title of this clear timer icon
     * @return Help title
     */
    public String getHelpTitle()
    {
        String s = "Clear Timer Icon";
        return s;
    }
    
    /**
     * Get help message of this clear timer icon
     * @return Help message
     */    
    public String getHelpMsg()
    {
        String s = "DETAILS:";
        s+="\n\nTimer Cleared: ";
        s+=timer;
        return s;
    }
    
    /**
     * Get the description of this icon
     * @return Help Description
     */    
    public String getHelpDesc()
    {
        String s = "This icon clears the value of\none of the 4 timers in RCX.";
        return s;
    }
    
    /**
     * Get help picture of this icon
     * @return Help picture
     */
    public javax.swing.Icon getHelpIcon()
    {
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/helps/clearTimerIcon.gif"));
        return ii;
    }
    
    /**
     * To clone this icon
     * @return New instance of this icon
     */
    public clearTimerIcon Clone()
    {
        return new clearTimerIcon("/icons/timers/cleart/cleartimer.gif");
    }
    
    /**
     * To copy this icon
     * @return New instance of this icon with its attributes set
     */
    public Icon Copy()
    {
        clearTimerIcon newBI = Clone();
        newBI.setTimer(timer);
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
        algo += "timer"+att_value_connector;
        algo += timer;
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

        NQCCode += indentation+"// clear a timer \n";
        NQCCode += indentation+"ClearTimer(";
        NQCCode += timer;
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
        timer = Integer.parseInt(sp2[1]);
    }
}