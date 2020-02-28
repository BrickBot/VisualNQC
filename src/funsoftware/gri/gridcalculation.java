/*
 * gridcalculation.java
 *
 * Created on 14 July 2005, 13:21
 */

package funsoftware.gri;

import funsoftware.ic.*;
import funsoftware.consts.*;

/**
 * This class is used only to calculate the required grid position after the user had click on any pixel of the programming window 
 * in the fUNSoftWare program.
 * The calculations will return either the X position or Y position of a grid intersection.
 * @author Thomas Legowo
 */
public class gridcalculation {
        
    /** Creates a new instance of gridcalculation */
    /** Also need to know the distances between grid intersections */
    public gridcalculation() {
    }
    
    // the four calculations methods (2 for X coordinates (left and right) and 2 for Y coordinates (top and bottom))
    // they require the X and the Y positions of the mouse click 
    // the half_icon argument is the half size of the icon,
    // for example a motor icon would have 16 as its half size
    /**
     * Calculates the closest left X coordinate to the clicked coordinate
     * @param clickedX X coordinate of user click
     * @return X coordinate
     */
    public static int calculateXleft(int clickedX)
    {
        int dx = Constants.gridDistanceX;
        int x = (clickedX/dx) * dx;
        return x;
    }
    
    /**
     * Calculates the closest right X coordinate to the clicked coordinate
     * @param clickedX X coordinate of user click
     * @return X coordinate
     */
    public static int calculateXright(int clickedX)
    {
        int dx = Constants.gridDistanceX;
        int x = (clickedX/dx) * dx + dx;
        return x;
    }
    
    // Unlike the X coordinate, the Y coordinate will always be the closest one to the mouse click coordinate
    /**
     * Calculates the closest Y coordinate to the clicked coordinate
     * @param clickedY Y coordinate of user click
     * @return Y coordinate
     */
    public static int calculateY(int clickedY)
    {
        int dy = Constants.gridDistanceY;
        int y;
        // always check for two possibilities, either the Y coordinate top of the desired one, or
        // Y coordinate bottom of the desired one
        int ytop; 
        int ybottom;
        ytop = (clickedY/dy) * dy;
        ybottom = (clickedY/dy) * dy + dy;
        
        int tempY1; // difference between clickedY and ytop
        int tempY2; // difference between clickedY and ybottom
        
        tempY1 = Math.abs(clickedY-ytop);
        tempY2 = Math.abs(clickedY-ybottom);
        
        if(tempY1 > tempY2)
        {
            y = ybottom;
        }
        else
        {
            y = ytop;
        }
        return y;
    }
    
    /**
     * Calculates the mid point in terms of a grid intersection in between two existing icon coordinates
     * @param left Left coordinate
     * @param right Right coordinate
     * @return X coordinate of the mid point
     */
    public static int midPoint(coord left, coord right)
    {
        int ret_val;
        int x_left = left.getX();
        int x_right = right.getX();
        ret_val = calculateXright(x_left+(x_right - x_left)/2);
        return ret_val;
    }
    
    /**
     * Given a grid intersection and an icon, compute the required coordinate of that icon that resides on the intersection
     * @param icon Icon
     * @param grid_inter Grid intersection coordinate 
     * @return Coordinate
     */
    public static coord computeCoord(Icon icon, coord grid_inter)
    {
        int x = grid_inter.getX();
        int y = grid_inter.getY();
        
        x = x - (icon.getWidth()/2);
        y = y - (icon.getHeight()/2);
        
        coord co = new coord(x,y);
        return co;
    }
}
