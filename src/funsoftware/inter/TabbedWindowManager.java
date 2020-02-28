/*
 * TabbedWindowManager.java
 *
 * Created on 25 September 2005, 22:03
 *
 */

package funsoftware.inter;

import funsoftware.consts.*;
import funsoftware.var.*;
import funsoftware.events.*;

/**
 * This class manages the programming window that now is able to show multiple functions one at a time without the need of 
 * resetting the programming window
 * Now this window manager also manages the variable window
 * @author Thomas Legowo
 */
public class TabbedWindowManager {
    
    TabbedWindowLookupTable twt;
    private javax.swing.JPanel jCardPanel;   // the card panel parent for tabbed windows
    
//    private javax.swing.JScrollPane jScrollPane1;  // this contains jCurrentProgWindow
//    private ProgWindow jCurrentProgWindow;   // this is the CURRENT programming window
//    private javax.swing.JPanel jPanel18;  // this contains jScrollPane1
    
    private VarWindow varw;
    private monitorEventWindow evw;
    
    // for singleton design pattern
    private static TabbedWindowManager instance = null;
    
    /**
     * Creates a new instance of TabbedWindowManager
     * @param cardPanel Card panel parent for tabbed windows
     * @param varw The variable window
     * @param evw The event window
    */
    protected TabbedWindowManager(javax.swing.JPanel cardPanel,
                                VarWindow varw, monitorEventWindow evw) {
        // the argument cardPanel indicates the panel that has the card layout
        // this class simply decides which of the panel on cardPanel should be displayed
        jCardPanel = cardPanel;
//        jCurrentProgWindow = jp2;
//        jScrollPane1 = jsp;
//        jPanel18 = jp3;
        this.varw = varw;
        this.evw = evw;
        
        twt = TabbedWindowLookupTable.Instance();
    }
    
    /**
     * Ensure only one instance of the TabbedWindowManager is used, use singleton design pattern
     * @return The singleton instance of this class
     * @param cardPanel Card panel parent for tabbed windows
     * @param varw The variable window
     * @param evw The event window
     */
    public static TabbedWindowManager Instance(javax.swing.JPanel cardPanel,
                                               VarWindow varw, monitorEventWindow evw)
    {
        if(instance == null)
        {
            instance = new TabbedWindowManager(cardPanel, varw, evw);
        }
        return instance;
    }
    
    /**
     * Return the card panel this class manages
     * @return Panel
     */
    public javax.swing.JPanel getCardPanel()
    {
        return jCardPanel;
    }
    
    /**
     * Decides which panel to show based on the code word
     * @param code Code word
     */
    public void displayPanel(String code)
    {
        // this contains jScrollPane1
        javax.swing.JPanel jPanel18 = (javax.swing.JPanel)(jCardPanel.getComponent(twt.getEntryPosition(Integer.parseInt(code))));
        java.awt.CardLayout cl = (java.awt.CardLayout)(jCardPanel.getLayout());
        cl.show(jCardPanel, code);

        javax.swing.JScrollPane jScrollPane1 = (javax.swing.JScrollPane)jPanel18.getComponent(0);
//        int tmp_g = Constants.getGridStatus();
       
        ProgWindow jCurrentProgWindow = (ProgWindow)(jScrollPane1.getViewport().getView());
       
//        Constants.setGridStatus(tmp_g);
        window.setProgrammingWindow(jPanel18, jScrollPane1, jCurrentProgWindow);
        
        varw.switchDisplay(aux_list.Instance().getAuxiliaries().get(Integer.parseInt(code)));
        evw.switchDisplay(aux_list.Instance().getAuxiliaries().get(Integer.parseInt(code)));
    }
    
    /**
     * Add a panel onto the card-layout panel
     * @param new_panel New panel
     * @param num_id Function id
     */
    public void addPanel(javax.swing.JPanel new_panel, int num_id)
    {
        jCardPanel.add(new_panel, Integer.toString(num_id));
        
        // update the tabbedwindowpanel
        twt.addEntry(num_id, jCardPanel.getComponentCount()-1); 
    }
    
    /**
     * Removes a panel 
     * @param num_id Function id
     */
    public void delPanel(int num_id)
    { 
        jCardPanel.remove(twt.getEntryPosition(num_id));
        twt.delEntry(num_id);
    }
    
    /**
     * Reset this class and all of its variables
     */
    public void reset()
    {                
        if(jCardPanel.getComponentCount() > 1)
        {
            int total_comp_count = jCardPanel.getComponentCount();
            for(int i = 1; i < total_comp_count; i++)  // remove all except the first panel
            {
                // As each component is removed, the component index shifts by one,
                // so we always act on index 1 until everything at index 1 and above is removed
                javax.swing.JPanel tmp_jpanel = (javax.swing.JPanel)jCardPanel.getComponent(1);
                javax.swing.JScrollPane tmp_jsp = (javax.swing.JScrollPane)tmp_jpanel.getComponent(0);          
//                ProgWindow tmp = (ProgWindow)(tmp_jsp.getViewport().getView());
                tmp_jsp.remove(0);
                tmp_jpanel.remove(0);
                jCardPanel.remove(1);
            }            
        }
        
//        javax.swing.JPanel tmp_jpanel = (javax.swing.JPanel)jCardPanel.getComponent(0);
//        javax.swing.JScrollPane tmp_jsp = (javax.swing.JScrollPane)tmp_jpanel.getComponent(0);          
//        ProgWindow tmp = (ProgWindow)(tmp_jsp.getViewport().getView());
    }
    
    /**
     * Reset the current programming window and its panels and scroll panels
     */
    public void panels_reset()
    {
//        javax.swing.JPanel tmp_jpanel = (javax.swing.JPanel)jCardPanel.getComponent(0);
//        javax.swing.JScrollPane tmp_jsp = (javax.swing.JScrollPane)tmp_jpanel.getComponent(0);          
//        ProgWindow tmp = (ProgWindow)(tmp_jsp.getViewport().getView());
//        
//        window.setProgrammingWindow(tmp_jpanel, tmp_jsp, tmp);
    }
    
    /**
     * For resizing window, all of the jScrollPane instances must be resize appropriately
     */
    public void resizeAllPanels()
    {
        if(jCardPanel.getComponentCount() > 1)
        {
            int total_comp_count = jCardPanel.getComponentCount();
            for(int i=1; i<total_comp_count; i++)  // resize all except the first panel
            {                
                javax.swing.JPanel tmp_jpanel = (javax.swing.JPanel)jCardPanel.getComponent(i);
                javax.swing.JScrollPane tmp_jsp = (javax.swing.JScrollPane)tmp_jpanel.getComponent(0);          
                ProgWindow tmp = (ProgWindow)(tmp_jsp.getViewport().getView());
                tmp.resizeProgWindow(tmp_jsp);
            }            
        }        
    }
}
