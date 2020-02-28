/*
 * Wire.java
 *
 * Created on 14 July 2005, 23:22
 */

package funsoftware.wr;

import funsoftware.gri.*;
import funsoftware.ic.*;
import funsoftware.consts.*;

/**
 * This class cater for an individual piece of wire.
 * Note that a piece of wire ALWAYS has two ends.
 * Unlike an icon, a piece of wire does not have one pair of coordinates.
 * Instead a piece of wire will have two pairs of coordinates for its two ends.
 * @author Thomas Legowo
 */
public class Wire {
    
    // The local attributes
    private String id; // the id of this wire, id will never equal to zero, it will be preceeded by the letter W
    
    // wires do not go diagonal!
    // So if the beginning and ending coordinate of the wire differs, then the wire will go one grid segment across,
    // then up a couple of grid segments to match to the destination's Y-coordinates, then one grid segment across.
    // the beginning coordinate of the wire
    private coord begin;
    // the ending coordinate of the wire
    private coord end;
    
    // A piece of wire can be connected to two icons, one on its left, the other one on its right
    // A piece of wire can also be connected to one icon and one wire or one wire and one icon
    private Icon ileft;
    private Icon iright;
    
    // the type of the wire
    // if type = 0 -> normal wiring
    // if type = 1 -> wiring system for branching (Vertical)
    // if type = 2 -> normal wiring inside a loop but with different colour to type 1
    // if type = 3 -> wiring system for initial looping (left side)
    // if type = 4 -> wiring system for initial looping (right side)
    private int type;
    
    private int thickness;  // the thickness of this wire
    
    // The length of the wire is always a block of grid's length
    
    /** Creates a new instance of Wire */
    public Wire() {
        type = 0;// initially type is 0
        begin = new coord();
        end = new coord();
        thickness = 2;  // initially thickness is 2
    }
    
    // This is due to the id of the wire should be unique and can only be determined by the set of wire that is
    // searched through using a hash map technique
    /**
     * Sets the wire's id. A wire's id will be unique
     * @param nid Wire id
     */
    public void setId(String nid)
    {
        id = nid;
    }
   
    /**
     * Return the id of this wire without the letter 'W'
     * @return Wire id
     */
    public int getId()
    {
        int ii = Integer.parseInt(id.substring(1));  //  get rid of the W from the id
        return ii;
    }
    
    /**
     * Return the full id with the letter 'W'
     * @return Full wire id
     */
    public String getFullId()
    {
        return id;
    }
    
    // set and return methods
    /**
     * Set left icon of this piece of wire
     * @param left Left icon
     */
    public void setLeftIcon(Icon left)
    {
        ileft = left;
    }
    
    /**
     * Set right icon of this piece of wire
     * @param right Right icon
     */
    public void setRightIcon(Icon right)
    {
        iright = right;
    }
        
    /**
     * The begin and end set coordinate should be called after the neighbouring icons are set.
     * Set the begin coordinate of this wire. 
     */
    public void setBeginCoordinate()
    {
        if(type == 1 || type == 3 || type == 4)  // only for looping
        {
            begin.setX(getLeftIcon().getCoordinate().getX()+(getLeftIcon().getWidth()/2 - (thickness/2)));
        }
        else
        {
            begin.setX(getLeftIcon().getCoordinate().getX()+getLeftIcon().getWidth());
        }
        begin.setY(getLeftIcon().getCoordinate().getY()+(getLeftIcon().getHeight()/2- (thickness/2)));
    }
    
    /**
     * Set the end coordinate of this wire.
     */
    public void setEndCoordinate()
    {
        if(type == 1 || type == 3 || type == 4)  // only for looping
        {
            end.setX(getRightIcon().getCoordinate().getX()+(getRightIcon().getWidth()/2- (thickness/2)));
        }
        else
        {
            end.setX(getRightIcon().getCoordinate().getX());
        }        
        end.setY(iright.getCoordinate().getY()+(getRightIcon().getHeight()/2- (thickness/2)));              
    }
    
    /**
     * To set the type of this piece of wire. Types are important for colouring of this wire
     * @param ntype New type
     */
    public void setType(int ntype)
    {
        type = ntype;
    }
    
    /**
     * Sets the thickness of the wire
     * @param nth Wire thickness
     */
    public void setWireThickness(int nth)
    {
        thickness = nth;
    }
    
    /**
     * Gets left icon of this wire
     * @return Left icon
     */
    public Icon getLeftIcon()
    {
        return ileft;
    }
    
    /**
     * Gets right icon of this wire
     * @return Right icon
     */
    public Icon getRightIcon()
    {
        return iright;
    }
        
    /**
     * Gets the begin coordinate of this wire
     * @return Begin coordinate
     */
    public coord getBeginCoordinate()
    {
        setBeginCoordinate();
        return begin;
    }
    
    /**
     * Gets the end coordinate of this wire
     * @return End coordinate
     */
    public coord getEndCoordinate()
    {
        setEndCoordinate();
        return end;
    }
    
    /**
     * Get the type of this particular piece of wire
     * @return Type
     */
    public int getType()
    {
        return type;
    }
    
    // Wire is different to icon and it is harder to draw since it can bend twice before reaching its end point
    // So it is best to leave the drawing of the wire within its own object
    // the argument gridDistanceX is the length of one segment horizontally while gridDistanceY is vertically
    /**
     * Draw this wire.
     * @param g Graphics
     */
    public void draw(java.awt.Graphics g)
    {
        setBeginCoordinate();
        setEndCoordinate();
        int distanceX = Constants.gridDistanceX;
        int distanceY = Constants.gridDistanceY;
        
        if(type == 0)
        {
            g.setColor(new java.awt.Color(0,0,0));
        }
        else if(type == 1)
        {
            g.setColor(new java.awt.Color(255,80,0,100));
        }
        else if(type == 2)
        {
            g.setColor(new java.awt.Color(152,32,255,100));
        }
        
        if(type == 0 || type == 1 || type == 2)
        {
            if((end.getY()-begin.getY()) == 0)
            {
                g.fillRect(begin.getX(), begin.getY(), (end.getX()-begin.getX()), thickness);                   
            }
            else
            {
                if(begin.getY()>end.getY())
                {
                    g.fillRect(end.getX(), end.getY(), thickness, Math.abs(end.getY()-begin.getY())+thickness);
                }
                else
                {
                    g.fillRect(begin.getX(), begin.getY(), thickness, Math.abs(end.getY()-begin.getY())+thickness);
                                    g.drawLine(begin.getX(), begin.getY(), end.getX(), end.getY());

                }                
            }             
        }
        // the rest are for the looping
        // beginning of loop
        else if(type == 3)
        {
            g.setColor(new java.awt.Color(152,32,255,100));
            
            g.fillRect(begin.getX(), begin.getY(), thickness, (end.getY() - begin.getY()));
            
            g.fillRect(begin.getX(), end.getY(), (end.getX()-begin.getX()), thickness);
        }
        // end of loop
        else if(type == 4)
        {
            g.setColor(new java.awt.Color(152,32,255,100));
            
            g.fillRect(begin.getX(), begin.getY(), (end.getX()-begin.getX()), thickness);            
            
            g.fillRect(end.getX(), end.getY(), thickness, Math.abs(end.getY() - begin.getY())+thickness);
        }
    }
}
