/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Input;

import Utils.Math.Vector2f;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Imad
 */
public class Input {
    public static final int NUM_KEYCODES = 256 ;
    public static final int NUM_MOUSE_BUTTONS = 5 ;
    
    private static ArrayList<Integer> currentKeys = new ArrayList<Integer>();
    private static ArrayList<Integer> downKeys = new ArrayList<Integer>();
    private static ArrayList<Integer> upKeys = new ArrayList<Integer>();
    
    private static ArrayList<Integer> currentMouse = new ArrayList<Integer>();
    private static ArrayList<Integer> downMouse = new ArrayList<Integer>();
    private static ArrayList<Integer> upMouse = new ArrayList<Integer>();
    
    
    public static void update ()
    {
        int i ; 
        
        upMouse.clear();
        for (i = 0 ; i < NUM_MOUSE_BUTTONS ; i++ )
        {
            if (!getMouse(i) && currentMouse.contains(i))
                upMouse.add(i);
        }
        
        downMouse.clear();
        for (i = 0 ; i < NUM_MOUSE_BUTTONS ; i++ )
        {
            if (getMouse(i) && !currentMouse.contains(i))
                downMouse.add(i);
        }
        
        upKeys.clear();
        for (i = 0 ; i < NUM_KEYCODES ; i++ )
        {
            if (!getKey(i) && currentKeys.contains(i))
                upKeys.add(i);
        }
        
        downKeys.clear();
        for (i = 0 ; i < NUM_KEYCODES ; i++ )
        {
            if (getKey(i) && !currentKeys.contains(i))
                downKeys.add(i);
        }
        
        
        currentKeys.clear();
        for (i = 0 ; i < NUM_KEYCODES ; i++ )
        {
            if (getKey(i))
                currentKeys.add(i);
        }
        
        currentMouse.clear();
        for (i = 0 ; i < NUM_MOUSE_BUTTONS ; i++ )
        {
            if (getMouse(i))
                currentMouse.add(i);
        }
        
        
    }
    
    public static boolean getKey (int keyCode)
    {
        return Keyboard.isKeyDown(keyCode);
    }
    
    public static boolean getKeyDown (int keyCode)
    {
        return downKeys.contains(keyCode) ;
    }
    
    public static boolean getKeyUp (int keyCode)
    {
        return upKeys.contains(keyCode) ;
    }
    
    public static boolean getMouse (int MouseButton)
    {
        return Mouse.isButtonDown(MouseButton);
    }
    
    public static boolean getMouseDown (int MouseButton)
    {
        return downMouse.contains(MouseButton) ;
    }
    public static boolean getMouseUp (int MouseButton)
    {
        return upMouse.contains(MouseButton) ;
    }
    
    public static Vector2f getMousePosition ()
    {
        return new Vector2f (Mouse.getX(),Mouse.getY());
    }
    
}
