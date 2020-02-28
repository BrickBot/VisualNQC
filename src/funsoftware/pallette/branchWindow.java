/*
 * branchWindow.java
 *
 * Created on 27 August 2005, 19:44
 *
 */

package funsoftware.pallette;

/**
 * This class cater for the branch icons pallettes
 * @author Thomas Legowo
 */
public class branchWindow extends javax.swing.JPanel {
    
    /** Creates a new instance of branchWindow */
    public branchWindow() {
        super(new java.awt.GridBagLayout());
        setBorder(null);
        setBackground(new java.awt.Color(247, 247, 247));
    }
    
    // overriding the paintComponent
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
