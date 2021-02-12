/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIRenderer;

import Utils.Math.Vector2f;
import Utils.Window.Window;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Imad
 */
public class GUICoordinate 
{
    private int x , y , width , height ;
    private int ID ;
    private static int count = 0 ;

    public GUICoordinate(int x, int y, int width, int height, int ID) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.ID = count++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getID() {
        return ID;
    }
    
    public boolean inRange (int X , int Y )
    {
        if (X >= x && Y >= y && X <= (x+width) && Y < (y+height))
        {
            return true;
        }else
        {
            return false ;
        }
    }
    
    public GUICoordinate (Vector2f pos , Vector2f scale)
    {
        int x_Center = (int)( ((1+pos.getX())) * (Window.getNewWidth()) / 2);
        int y_Center = (int)( ((1+pos.getY())) * (Window.getNewHeight()) / 2);
        
        int x_offset = (int)(scale.getX() * (Window.getNewWidth()))/2;
        int y_offset = (int)(scale.getY() * (Window.getNewHeight()))/2;
        
        this.x = (x_Center-x_offset);
        this.y = (y_Center-y_offset);
        this.width = x_offset*2;
        this.height = y_offset*2;
        this.ID = count++;
        
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
    
    @Override
    public String toString() {
        return "GUICoordinate{" + "x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", ID=" + ID + '}';
    }
    
}
