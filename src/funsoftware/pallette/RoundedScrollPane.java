/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funsoftware.pallette;

/**
 *
 * @author Matthew
 */
public class RoundedScrollPane extends javax.swing.JScrollPane {
    
    public RoundedScrollPane(){
        super();
        
        this.setMinimumSize(new java.awt.Dimension(130, 115));
        this.setPreferredSize(this.getMinimumSize());
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(247, 247, 247), 5, true));
    }
    
}
