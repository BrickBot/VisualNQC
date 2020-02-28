/*
 * objectWindow.java
 *
 * Created on 27 August 2005, 17:30
 *
 */

package funsoftware.pallette;

/**
 * This class cater for the pallette holding the singles icons --> motor, lamp, timer, etc etc
 * @author Thomas Legowo
 */
public class objectWindow extends javax.swing.JPanel {
    
    /** Creates a new instance of objectWindow */
    public objectWindow() {
        super(new java.awt.GridBagLayout());
        setBorder(null);
        setBackground(new java.awt.Color(247, 247, 247));
    }
    
    /**
     * Paints this object window
     * @param g Graphics
     */
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/iconWindow/lefttop.jpg"));
        g.drawImage(ii.getImage(), 0, 0, this);
        
        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/iconWindow/righttop.jpg"));
        g.drawImage(ii.getImage(), getWidth()-10, 0, this);      
        
        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/iconWindow/rightbottom.jpg"));
        g.drawImage(ii.getImage(), getWidth()-10, getHeight()-10, this);      
        
        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/iconWindow/leftbottom.jpg"));
        g.drawImage(ii.getImage(), 0, getHeight()-10, this);         
    }
}
