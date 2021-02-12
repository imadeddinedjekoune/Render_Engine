/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Time;

import Utils.Photo.PhotoGenerator;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 *
 * @author Imad
 */
public class FPS {
    public static BufferedImage image = PhotoGenerator.intToImage(renderengineopengl.RenderEngineOPENGL.FPS);
    
    public static void start ()
    {
        Thread t1 = new Thread(() -> {
                image = PhotoGenerator.intToImage(renderengineopengl.RenderEngineOPENGL.FPS);
                System.out.println("done");
        });
        t1.start();
    }
    
}
