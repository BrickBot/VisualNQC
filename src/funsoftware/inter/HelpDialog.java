/*
 * HelpDialog.java
 *
 * Created on 14 February 2006, 18:14
 *
 */

package funsoftware.inter;

import funsoftware.properties.*;

/**
 * To display a help document
 * @author Thomas Legowo
 */
public class HelpDialog extends javax.swing.JDialog{
    // private variables
    private javax.swing.JEditorPane help;
    private javax.swing.JScrollPane editorScrollPane;    
    
    private javax.swing.JPanel jp;
    
    java.awt.GridBagConstraints gridBagConstraints;
  
    private javax.swing.JButton button;
         
    /**
     * Creates a new instance of HelpDialog
     * @param frame The frame
     */
    public HelpDialog(java.awt.Frame frame) {
        super(frame, true);
        setTitle(AppProperties.appName + " Documentation");
        
        try
        {
            help = new javax.swing.JEditorPane(getClass().getResource("/readme.txt"));
            help.setFont(new java.awt.Font("Verdana", 0, 12));
            help.setEditable(false);
        }
        catch (java.io.IOException e) 
        {
            System.err.println("Attempted to read a bad readme file.");
        }

        editorScrollPane = new javax.swing.JScrollPane();
        javax.swing.JViewport vp = new javax.swing.JViewport();
        vp.setView(help);
        editorScrollPane.setViewport(vp);
        editorScrollPane.setPreferredSize(new java.awt.Dimension(710, 470));
        editorScrollPane.setMinimumSize(new java.awt.Dimension(710, 470));
        
        jp = new javax.swing.JPanel();
        jp.setLayout(new java.awt.GridBagLayout());
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(editorScrollPane, gridBagConstraints);   
        
        button = new javax.swing.JButton("OK");
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonMouseClicked();
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jp.add(button, gridBagConstraints);        

        setContentPane(jp);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent we) {
                buttonMouseClicked();
            }
        });
    }
 
    
    // to handle for "Ok" button clicked
    private void buttonMouseClicked()
    {
        setVisible(false);
    }
}
