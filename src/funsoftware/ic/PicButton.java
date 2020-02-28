/*
 * PicButton.java
 *
 * Created on 2 August 2005, 15:59
 */

package funsoftware.ic;

/**
 * This class is for the little buttons constructing the larger button for each icon
 * @author Thomas Legowo
 */
public class PicButton extends javax.swing.JLabel{
    
    /**
     * Creates a new instance of PicButton
     * @param nic The image icon
     */
    public PicButton(javax.swing.ImageIcon nic) {
        super();
        super.setPreferredSize(new java.awt.Dimension(nic.getImage().getWidth(this), nic.getImage().getHeight(this)));
        setOpaque(true);
        setIcon(nic);        
        setBackground(new java.awt.Color(255, 255, 255));
    }    
}
