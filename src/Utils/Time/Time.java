/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Time;

import java.time.Duration;
import java.time.Instant;
import static renderengineopengl.RenderEngineOPENGL.FPS;

/**
 *
 * @author Imad
 */
public class Time 
{
    private static long startTime = System.nanoTime();
    private static float frames  = 0;
        
    public static void FPS_Sync ()
    {
        frames++;
            
        if(System.nanoTime() - startTime >= 1000000000) {
               
            FPS = (int)(frames) ;
            frames = 0;
            startTime = System.nanoTime();
        }
    }
    
}
