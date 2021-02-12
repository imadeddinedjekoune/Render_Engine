/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Window;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 *
 * @author Imad
 */
public class Window 
{
    public static int WIDTH = 640 ;
    public static int HEIGHT = 480 ;
    public static final int FPS_COUNT = 120 ;
    
    public static void creer_fenetre (String title)
    {
        try {
            
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
            Display.setTitle(title);
            Display.setResizable(true);
            Keyboard.create();
        } catch (Exception e) 
        {
            System.out.println("Excepetion .. Dans La Creation De La Fenetre");
        }
    }
    
    public static void detruire_window()
    {
        Display.destroy();
    }
    public static void updateWindow()
    {
        Display.update();
        Display.sync(FPS_COUNT);
    }
    public static boolean isColseRequested ()
    {
        return Display.isCloseRequested();
    }
    public static int getNewWidth ()
    {
        return Display.getWidth();
    }
    public static int getNewHeight ()
    {
        return Display.getHeight();
    }
    
    
}
