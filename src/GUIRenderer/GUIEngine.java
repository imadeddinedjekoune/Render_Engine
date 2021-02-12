/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIRenderer;

import Component.Mesh.Texture;
import Component.Shader.Shader;
import Utils.Input.Input;
import Utils.Math.Vector2f;
import Utils.Photo.PhotoGenerator;
import Utils.Time.FPS;
import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Imad
 */
public class GUIEngine 
{
    private RenderGUI ren = new RenderGUI();
    public static ArrayList<GUITexture> guis = new ArrayList<>();
    private Shader  guishder ;
    public static ArrayList <GUICoordinate> coord = new ArrayList<>();
    private GUITexture t1 , t2 ,t3 ;
    
    
    public GUIEngine()
    {
        t1 = new GUITexture(new Texture(Texture.loadTexture("exit.png")), 
                new Vector2f(0.9f,0.9f), new Vector2f(0.1f,0.1f));
        t2 = new GUITexture(new Texture(Texture.loadTexture("fps.png")), 
                new Vector2f(0.7f,0.9f), new Vector2f(0.1f,0.1f));
        t3 = new GUITexture(new Texture(Texture.loadTexture(PhotoGenerator.intToImage(renderengineopengl.RenderEngineOPENGL.FPS))), 
                new Vector2f(-0.7f,0.8f), new Vector2f(0.2f,0.2f));
        
        t1.setActive(true);
        t2.setActive(true);
        
        
        guis.add(t1);
        coord.add(new GUICoordinate(new Vector2f(0.9f,0.9f), new Vector2f(0.1f,0.1f))); 
        
        guis.add(t2);
        coord.add(new GUICoordinate(new Vector2f(0.7f,0.9f), new Vector2f(0.1f,0.1f))); 
        
        guis.add(t3);
        coord.add(new GUICoordinate(new Vector2f(0.7f,0.9f), new Vector2f(0.1f,0.1f))); 
        
        
        guishder = new Shader("/GUI/vsg.txt","/GUI/fsg.txt");
        guishder.addUniforme("transformationMatrix");
        GUIRenderer.RenderGUI.sh =  guishder;
    }
    
    public void input()
    {
        if (Input.getMouseUp(0))
        {
            for (int i = 0 ; i < coord.size() ; i++ )
            {
                if (coord.get(i).inRange(Mouse.getX(),Mouse.getY()))
                {
                    Actions.doAction(coord.get(i).getID());
                    break ;
                }
            }
        }
    }
    
    public void render ()
    {
        ren.render(guis);
    }
    
    int oldFPS = 0 ;
    public void update ()
    {
        
        if (t3.isActive())
        {
            if (renderengineopengl.RenderEngineOPENGL.FPS != oldFPS)
            {
                oldFPS = renderengineopengl.RenderEngineOPENGL.FPS ;
                
                t3.setTexture(new Texture(Texture.loadTexture(FPS.image)));
                FPS.start();
            }
            
        }
    }
    
}
