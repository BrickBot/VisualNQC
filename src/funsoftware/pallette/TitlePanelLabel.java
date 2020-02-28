/*
 * TitlePanelLabel.java
 *
 * Created on 13 December 2018
 *
 */

package funsoftware.pallette;

/**
 *
 * @author Matthew Sheets
 */
public class TitlePanelLabel extends javax.swing.JPanel {
    
    javax.swing.JLabel jLabelTitle = new javax.swing.JLabel();
    java.awt.GridBagConstraints gridBagConstraints;

    /** Creates a new instance */
    public TitlePanelLabel() {
        super();
        
        this.setBackground(new java.awt.Color(234, 234, 234));
        this.setForeground(new java.awt.Color(0, 0, 0));
        this.setFont(new java.awt.Font("Verdana", 1, 14));
        
        this.setMinimumSize(new java.awt.Dimension(130, 20));
        this.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, this.getMinimumSize().height));
        this.setPreferredSize(this.getMinimumSize());
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        this.setLayout(new java.awt.GridBagLayout());
        this.add(jLabelTitle, gridBagConstraints);
    }
    
    public String getText()
    {
        return jLabelTitle.getText();
    }
    
    public void setText(String text)
    {
        jLabelTitle.setText(text);
    }
    
    public void setBackground(java.awt.Color color)
    {
        super.setBackground(color);
        if (jLabelTitle != null)
        {
            jLabelTitle.setBackground(color);
        }
    }
    
    public void setForeground(java.awt.Color color)
    {
        super.setForeground(color);
        if (jLabelTitle != null)
        {
            jLabelTitle.setForeground(color);
        }
    }
        
    public void setFont(java.awt.Font font)
    {
        super.setFont(font);
        if (jLabelTitle != null)
        {
            jLabelTitle.setFont(font);
        }
    }
}
