/*
 * directionWindow.java
 *
 * Created on 13 September 2005, 19:17
 *
 */

package funsoftware.pallette;

/**
 * This class is for the window that gives a real time direction based on what the user previous action was.
 * @author Thomas
 */
public class directionWindow extends javax.swing.JPanel {
    
    /** Creates a new instance of directionWindow */
    public directionWindow() {
        super(new java.awt.GridBagLayout());
    }
    
    // overriding the paintComponent
    /**
     * Paint the window
     * @param g Graphics
     */
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        
        javax.swing.ImageIcon ic;
        java.awt.Image image;
/*        
        ic = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/dirWindow/background.jpg"));
        image = ic.getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
      
        ic = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/dirWindow/bottomtitle.gif"));
        image = ic.getImage();
        g.drawImage(image, 115, getHeight()-55, this);   
        
        ic = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/dirWindow/bottomtitle2.gif"));
        image = ic.getImage();
        g.drawImage(image, getWidth()-335, getHeight()-60, this);  
 */ 
    }
}
