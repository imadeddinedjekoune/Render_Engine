/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Renderer;

import Animation.KERNAL.AnimatedModel;
import Animation.KERNAL.Animation;
import Animation.Loader.AnimatedModelLoader;
import Animation.Loader.AnimationLoader;
import Component.Mesh.Model.LightSource;
import Utils.Math.Vector3f;
import Component.Mesh.Mesh;
import Component.Mesh.MeshGenerator;
import Component.Mesh.MeshUtilities;
import Component.Mesh.Model.Model;
import Component.Mesh.Model.Player;
import Component.Mesh.OBJLoader;
import Component.Mesh.Model.Terrain;
import Component.Mesh.Texture;
import Component.Transform.Camera;
import Component.Transform.Projection;
import Component.Render.Render;
import Component.Shader.AnimShader;
import Component.Shader.AnimationShader;
import Component.Shader.BasicShader;
import Component.Shader.Complex_Light_Object_Shader;
import Component.Shader.Light_Object_Shader;
import Component.Shader.Light_Source_Shader;
import Component.Shader.Shader;
import Component.Shader.TerrainShader;
import Component.Transform.Transform;
import Utils.Files.MyFile;
import Utils.Input.Input;
import Utils.Window.Window;
import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.input.Keyboard;


/**
 *
 * @author Imad
 */
public class Engine {
    


    private Light_Source_Shader shader_lampe ;
    private Projection projection ;
    private Camera camera ;
    
    private LightSource lampe ;

    private AnimatedModel aModel ;
    private Animation animation ;
    private AnimShader animationShader ;

    
    public Engine ()
    {
        
        projection = new Projection(0.1f,4000f,Window.getNewWidth(), Window.getNewHeight(), 70f);
        camera = new Camera(new Vector3f( -2.619249f , -5.3011355f , -5.3956566f), new Vector3f( -0.3556974f , -0.12186941f , -0.92662144f ), new Vector3f(0, 1, 0));
        
        
        lampe = new LightSource(MeshUtilities.load_To_Mesh(MeshGenerator.cube_position, null,null , null,null,null, 0),
                new Vector3f(10f, 10f, 5f), new Vector3f(1f, 1f, 1f));
        
        MyFile fileModel = new MyFile("./res/collada/model.dae");
        MyFile fileTexture = new MyFile("./res/collada/diffuse.png");
        
        aModel = AnimatedModelLoader.loadEntity(fileModel, fileTexture);
        animation = AnimationLoader.loadAnimation(new MyFile(fileModel));
        
        
        shader_lampe = new Light_Source_Shader(lampe.getTransform(), projection, camera,lampe.getLightColor());
        shader_lampe.init();
        lampe.getTransform().setTranslation(lampe.getLightPos());
        lampe.getTransform().setScale(new Vector3f(1f, 1f, 1f));
        lampe.getTransform().setTranslation(lampe.getLightPos());
        shader_lampe.setUniform("trasnform", lampe.getTransform().getTransformation());
        
        animationShader = new AnimShader(new Transform(), projection, camera, lampe);
        animationShader.init();
        animationShader.setUniform("trasnform", new Transform().getTransformation());

        aModel.doAnimation(animation);
    }
    
    public void render ()
    {
        
        shader_lampe.start();
        Render.render_WithoutEBO(lampe.getMesh());
        shader_lampe.start();
        
        animationShader.start();
        animationShader.binTexture(aModel.getTexture());
        animationShader.useFakeLight(false);
        animationShader.setUniformi("useLight",1);
        animationShader.LoadMatrix(aModel.getJointTransforms());
        Render.renderEBO2(aModel.getModel());
        animationShader.stop();
        animationShader.unbinTexture(aModel.getTexture());
    }
    
    public void update ()
    {
        shader_lampe.update_Cam();
        animationShader.update_Cam();
        aModel.update();
    }
    
    
    public void input ()
    {
        camera.input();
        
        if (Input.getKeyDown(Keyboard.KEY_X))
        {
            camera.info();
        }
        
        
        if (Input.getKeyDown(Keyboard.KEY_H))
        {
            renderengineopengl.RenderEngineOPENGL.enable_GUI = !renderengineopengl.RenderEngineOPENGL.enable_GUI ;
            
        }
    }
    
}
