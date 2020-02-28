/*
 * bottomRightWindow.java
 *
 * Created on 27 August 2005, 20:07
 *
 */

package funsoftware.pallette;

/**
 * The purpose of this window is to house a list of available and reusable subroutine
 * @author Thomas Legowo
 * 
 */
public class bottomRightWindow extends javax.swing.JPanel {
    
    /** Creates a new instance of bottomRightWindow */
    public bottomRightWindow() {
        super(new java.awt.GridBagLayout());
        java.awt.Dimension area = new java.awt.Dimension(210,55);
        setBackground(new java.awt.Color(247, 247, 247));
        super.setPreferredSize(area);
    }
    
    // overriding the paintComponent
    /**
     * Paints this object window
     * @param g Graphics
     */
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        
        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/functionreuse/lefttop.jpg"));
        g.drawImage(ii.getImage(), 0, 0, this);
        
        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/functionreuse/righttop.jpg"));
        g.drawImage(ii.getImage(), getWidth()-10, 0, this);      
        
        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/functionreuse/rightbottom.jpg"));
        g.drawImage(ii.getImage(), getWidth()-10, getHeight()-10, this);      
        
        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/functionreuse/leftbottom.jpg"));
        g.drawImage(ii.getImage(), 0, getHeight()-10, this);         
    }
}
