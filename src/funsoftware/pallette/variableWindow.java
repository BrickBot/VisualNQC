/*
 * variableWindow.java
 *
 * Created on 10 February 2006, 18:08
 *
 */

package funsoftware.pallette;

/**
 * For the variable pallette
 * @author Thomas Legowo
 */
public class variableWindow extends javax.swing.JPanel{
    
    /** Creates a new instance of variableWindow */
    public variableWindow() {
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
        
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/varWindow/lefttop.jpg"));
        g.drawImage(ii.getImage(), 0, 0, this);
        
        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/varWindow/righttop.jpg"));
        g.drawImage(ii.getImage(), getWidth()-10, 0, this);      
        
        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/varWindow/rightbottom.jpg"));
        g.drawImage(ii.getImage(), getWidth()-10, getHeight()-10, this);      
        
        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/varWindow/leftbottom.jpg"));
        g.drawImage(ii.getImage(), 0, getHeight()-10, this);         
    }
}
