/*
 * coord.java
 *
 * Created on 18 July 2005, 20:32
 */

package funsoftware.gri;

/**
 * This class defines a coordinate (X and Y), to be used by icons and wires
 * @author Thomas Legowo
 * 
 * 
 */
public class coord {
    
    private int x;
    private int y;
    
    /** Creates a new instance of coord */
    public coord() {
    }
    
    /**
     * Creates a new instance of coord
     * @param nx X coordinate
     * @param ny Y coordinate
     */
    public coord(int nx, int ny)
    {
        x = nx;
        y = ny;
    }
    
    /**
     * Get X coordinate
     * @return X coordinate
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * Get Y coordinate
     * @return Y coordinate
     */
    public int getY()
    {
        return y;
    }
    
    /**
     * Set the x coordinate
     * @param nx New x coordinate
     */
    public void setX(int nx)
    {
        x = nx;
    }
    
    /**
     * Set the y coordinate
     * @param ny New y coordinate
     */
    public void setY(int ny)
    {
        y = ny;
    }
}
