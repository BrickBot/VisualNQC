/*
 * loopWindow.java
 *
 * Created on 27 August 2005, 19:44
 *
 */

package funsoftware.pallette;

/**
 * This class cater for the pallette containing all kind of loop icons
 * @author Thomas Legowo
 */
public class loopWindow extends javax.swing.JPanel {
    
    /** Creates a new instance of loopWindow */
    public loopWindow() {
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
        
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/loopWindow/lefttop.jpg"));
        g.drawImage(ii.getImage(), 0, 0, this);
        
        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/loopWindow/righttop.jpg"));
        g.drawImage(ii.getImage(), getWidth()-10, 0, this);      
        
        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/loopWindow/rightbottom.jpg"));
        g.drawImage(ii.getImage(), getWidth()-10, getHeight()-10, this);      
        
        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/loopWindow/leftbottom.jpg"));
        g.drawImage(ii.getImage(), 0, getHeight()-10, this);         
    }
}
