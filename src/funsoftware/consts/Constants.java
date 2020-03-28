/*
 * Constants.java
 *
 * Created on 29 September 2005, 15:17
 *
 */

package funsoftware.consts;

/**
 * This class contains all the constants used in this program providing a global entry point to it
 * @author Thomas Legowo
 * 
 */
public class Constants {
    
    /** Creates a new instance of Constants */
    public Constants() {
    }
    
    // the global variables
    /**
     * The horizontal distance of programming grid
     */
    public static int gridDistanceX = 50;
    
    /**
     * The vertical distance of programming grid
     */
    public static int gridDistanceY = 55;
    
    /**
     * The status of the grid
     */
    public static int gridStatus = 0;
    
    /**
     * The maximum horizontal size of the programming window
     */
    public static int maxHorizontalProgWindow = 640;
    
    /**
     * The maximum vertical size of the programming window
     */
    public static int maxVerticalProgWindow = 530;
    
    /**
     * The width of the scroll bars
     */
    public static int scrollbarWidth = ((Integer)javax.swing.UIManager.get("ScrollBar.width")).intValue();
        
    /**
     * The lookup table for the music icon (playSpecificNote):  Note and octave = frequency
     */
    public static int[][] frequencies = {{  33,   35,   37,   39,   41,   44,   46,   49,   52,   55,   58,   62},
                                         {  65,   69,   73,   78,   82,   87,   92,   98,  104,  110,  117,  123},
                                         { 131,  139,  147,  156,  165,  175,  185,  196,  208,  220,  233,  247},
                                         { 262,  277,  294,  311,  330,  349,  370,  392,  415,  440,  466,  494},
                                         { 523,  554,  587,  622,  659,  698,  740,  784,  831,  880,  932,  988},
                                         {1047, 1109, 1175, 1245, 1319, 1397, 1480, 1568, 1661, 1760, 1865, 1976},
                                         {2093, 2217, 2349, 2489, 2637, 2794, 2960, 3136, 3322, 3520, 3951, 4186}};    
    
    
//    /**
//     * For horizontal resizing
//     */
//    public static int horizontalWindowSize;
//    
//    /**
//     * For vertical resizing
//     */    
//    public static int verticalWindowSize;
                                         
    /**
     * To set the horizontal distance of programming grid
     * @param ndx New horizontal distance
     */
    public static void setGridDistanceX(int ndx)
    {
        gridDistanceX = ndx;
    }
    
    /**
     * To set the vertical distance of programming grid
     * @param ndy New vertical distance
     */
    public static void setGridDistanceY(int ndy)
    {
        gridDistanceY = ndy;
    }
    
    /**
     * To get the status of the grid lines
	 * @return 0 = Hide grid lines; 1 = Show grid lines
     */
    public static int getGridStatus()
    {
        return gridStatus;
    }
    
    /**
     * To set the status of the grid lines
	 * @param status 0 = Hide grid lines; 1 = Show grid lines
     */
    public static void setGridStatus(int status)
    {
        gridStatus = status;
    }
    
    /**
     * To set the maximum horizontal size of the programming window
     * @param h New maximum horizontal size of the programming window
     */
    public static void setMaxHorizontalProgWindow(int h)
    {
        maxHorizontalProgWindow = h;
    }
    
    /**
     * To set the maximum vertical size of the programming window
     * @param v New maximum vertical size of the programming window
     */
    public static void setMaxVerticalProgWindow(int v)
    {
        maxVerticalProgWindow = v;
    }
    
//    /**
//     * To set horizontal size of the fUNSoftWare's window
//     * @param h New horizontal size of the fUNSoftWare's window
//     */
//    public static void setHorizontalWindowSize(int h)
//    {
//        horizontalWindowSize = h;
//    }
//    
//    /**
//     * To set vertical size of the fUNSoftWare's window
//     * @param v New vertical size of the fUNSoftWare's window
//     */
//    public static void setVerticalWindowSize(int v)
//    {
//        verticalWindowSize = v;
//    }
    
    /**
     * To track the width of the scrollbars as rendered by the system
     * @param w Width of a scrollbar as rendered by the system
     */
    public static void setScrollbarWidth(int w)
    {
        scrollbarWidth = w;
    }
}
