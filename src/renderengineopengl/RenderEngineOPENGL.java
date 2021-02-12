/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderengineopengl;

import Animation.KERNAL.AnimatedModel;
import Animation.KERNAL.Animation;
import Animation.Loader.AnimatedModelLoader;
import Animation.Loader.AnimationLoader;
import Component.Mesh.MeshUtilities;
import Component.Mesh.Mesh;
import Component.Render.Render;
import Component.Renderer.Engine;
import Component.Renderer.TestGameV1;
import GUIRenderer.GUIEngine;
import Swing.Frame;
import Utils.Files.MyFile;
import Utils.Input.Input;
import Utils.Photo.PhotoGenerator;
import Utils.Time.Time;
import Utils.Window.Window;
import java.time.Instant;
import javax.swing.SwingUtilities;

/**
 *
 * @author Imad
 */
public class RenderEngineOPENGL {

    /**
     * @param args the command line arguments
     */
    
    public static int FPS = 0 ;
    public static boolean enable_GUI = false ;
    
    
    
    public static void main(String[] args) 
    {
        // Init //
        Window.creer_fenetre("Render ENGINE 1.02");
        Render.initGUI();
        //Engine myEngine = new Engine();  // Ceci Et Pour La Skeletle Aniomation .. //
        TestGameV1 myEngine = new TestGameV1(); // Ceci Et Pour Le Jeu Open World .. //
        /*
            Pour Lanimatoin Utiliser Cette Ligne : TestGameV1 myEngine = new TestGameV1();
                au lieu de ca  : Engine myEngine = new Engine();
            
        */
        GUIEngine guiEngine = new GUIEngine();
        
        
        while (!Window.isColseRequested())
        {
            Render.update();
            
            // Render Engine //
            myEngine.render();
            myEngine.input();
            myEngine.update();
            
            if (enable_GUI)
            {
                // GUI Enigine //
                guiEngine.render();
                guiEngine.input();
                guiEngine.update();

                Tracker.tracker_la_fenetre();
            }

            Input.update();
            Window.updateWindow();
            
            // FPS //
            Time.FPS_Sync();
        }
        
        // Memoire Management //
        Window.detruire_window();
        //MeshUtilities.cleanUP();
        
    }    
}