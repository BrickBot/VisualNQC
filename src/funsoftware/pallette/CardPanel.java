/*
 * CardPanel.java
 *
 * Created on 9 February 2006, 17:10
 */

package funsoftware.pallette;

/**
 * Will be of a card layout and house multiple programming windows
 * @author Thomas Legowo
 */
public class CardPanel extends javax.swing.JPanel {
    
    /** Creates a new instance of CentralWindow */
    public CardPanel() {
        super(new java.awt.CardLayout());
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(247, 247, 247), 10, true));
    }
    
//    // overriding the paintComponent
//    /**
//     * Paints this object window
//     * @param g Graphics
//     */
//    protected void paintComponent(java.awt.Graphics g) {
//        super.paintComponent(g);
//        
//        javax.swing.ImageIcon ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/mainWindow/lefttop.gif"));
//        g.drawImage(ii.getImage(), 0, 0, this);
//        
//        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/mainWindow/righttop.gif"));
//        g.drawImage(ii.getImage(), getWidth()-30, 0, this);      
//        
//        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/mainWindow/rightbottom.gif"));
//        g.drawImage(ii.getImage(), getWidth()-30, getHeight()-30, this);      
//        
//        ii = new javax.swing.ImageIcon(getClass().getResource("/icons/interface/mainWindow/leftbottom.gif"));
//        g.drawImage(ii.getImage(), 0, getHeight()-30, this);   
//    }
}
  
