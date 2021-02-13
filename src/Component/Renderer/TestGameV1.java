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
import Component.Mesh.Model.AnimatedPlayer;
import Component.Mesh.Model.Model;
import Component.Mesh.Model.Player;
import Component.Mesh.OBJLoader;
import Component.Mesh.Model.Terrain;
import Component.Mesh.Texture;
import Component.Transform.Camera;
import Component.Transform.Projection;
import Component.Render.Render;
import Component.Shader.AnimShader;
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
public class TestGameV1 {
    

    private Model tree , fern ;
    private AnimatedPlayer man ;
    private TerrainShader terrainShader ;
    private Light_Object_Shader shader_light  ;
    private Light_Source_Shader shader_lampe ;
    private Complex_Light_Object_Shader complex_shader_light ;
    private Projection projection ;
    private Camera camera ;
    private Terrain terrain ;
    private LightSource lampe ;
    private Model house ;
    private ArrayList<Vector3f> position_trees = new ArrayList<>();
    private ArrayList<Vector3f> Scale_trees = new ArrayList<>();
    private ArrayList<Vector3f> position_ferns = new ArrayList<>();
    private AnimShader animationShader ;

    
    
    int nbTrees = 200 ;
    int nbFerns = 5 ;
    
    public TestGameV1 ()
    {
        
        projection = new Projection(0.1f,4000f,Window.getNewWidth(), Window.getNewHeight(), 70f);
        camera = new Camera(new Vector3f(-136.13307f, -2.3382692f , -136.13307f), new Vector3f(1, 0, 1), new Vector3f(0, 1, 0));
        
        terrain = new Terrain(1000, 1000, new Texture(Texture.loadTexture("grass.png")),
                new Texture(Texture.loadTexture("grassFlowers.png")),
                new Texture(Texture.loadTexture("mud.png")),
                new Texture(Texture.loadTexture("path.png")),
                new Texture(Texture.loadTexture("blendMap.png")));
        tree = new Model(OBJLoader.loadObjModel("tree.obj"),
               new Texture(Texture.loadTexture("tree.png")));
        lampe = new LightSource(MeshUtilities.load_To_Mesh(MeshGenerator.cube_position, null,null , null, null, null, 0),
                new Vector3f(1200f, 500f, 1200f), new Vector3f(0.8f, 0.8f, 0.7f));
        house = new Model(OBJLoader.loadObjModel("stall.obj"),
                new Texture(Texture.loadTexture("stallTexture.png")));
        fern = new Model(OBJLoader.loadObjModel("fern.obj"),
               new Texture(Texture.loadTexture("fern.png")));
        fern.setTransparence(true);
        
        AnimatedModel aModel ;
        Animation animation ;
        
        MyFile fileModel = new MyFile("./res/collada/model.dae");
        MyFile fileTexture = new MyFile("./res/collada/diffuse.png");
        
        aModel = AnimatedModelLoader.loadEntity(fileModel, fileTexture);
        animation = AnimationLoader.loadAnimation(new MyFile(fileModel));
        
        
        man = new AnimatedPlayer(aModel,animation,camera);
        man.doAnimation();
        man.playerBestAgrs();
        man.getTransform().setTranslation(new Vector3f(140, 0, 140));
        
        terrainShader = new TerrainShader(projection,camera,terrain,lampe);
        terrainShader.init();
        
        shader_lampe = new Light_Source_Shader(lampe.getTransform(), projection, camera,lampe.getLightColor());
        shader_lampe.init();
        lampe.getTransform().setTranslation(lampe.getLightPos());
        lampe.getTransform().setScale(new Vector3f(20f, 20f, 20f));
        lampe.getTransform().setTranslation(lampe.getLightPos());
        shader_lampe.setUniform("trasnform", lampe.getTransform().getTransformation());
        
        animationShader = new AnimShader(new Transform(), projection, camera, lampe);
        animationShader.init();
        animationShader.setUniform("trasnform", new Transform().getTransformation());

        
        shader_light = new Light_Object_Shader(new Transform(), projection, camera, lampe);
        shader_light.init();
        shader_light.setUniform("trasnform", new Transform().getTransformation());
        
        
        complex_shader_light = new Complex_Light_Object_Shader(new Transform(), projection, camera, lampe);
        complex_shader_light.init();
        complex_shader_light.setUniform("trasnform", new Transform().getTransformation());
        

        
        int scTrees = 700 ;
        // Get Random Position For Trees //
        for (int i = 0 ; i < nbTrees ; i++)
        {
            Random r = new Random();
            position_trees.add(new Vector3f(r.nextInt(scTrees)+1, 
                    0, r.nextInt(scTrees)+1));
            Scale_trees.add(new Vector3f(1, 
                    r.nextInt(3)+1,1));
            
        }
        
        int scFerns = 300 ;
        // Get Random Position For Trees //
        for (int i = 0 ; i < nbTrees ; i++)
        {
            Random r = new Random();
            position_ferns.add(new Vector3f(r.nextInt(scFerns)+1, 
                    0, r.nextInt(scFerns)+1));
        }
        
    }
    
    public void render ()
    {
        // Render Terrain //
        terrainShader.bindTexture();
        terrainShader.setUniformi("scaleText",40);
        terrainShader.start();
        terrainShader.update_Render();
        Render.renderEBO(terrain.getModel());
        terrainShader.stop();
        terrainShader.unbindTexture();
        
        
        // Render Tree //
        complex_shader_light.useFakeLight(false);
        complex_shader_light.start();
        complex_shader_light.update_Render();
        complex_shader_light.binTexture(tree);
        complex_shader_light.setUniformi("scaleText", 1);
        complex_shader_light.setUniformi("useLight",1);
        
        Random r = new Random();
        for ( int i = 0 ; i < nbTrees ; i++ )
        {
            tree.getTransform().setTranslation(position_trees.get(i));
            tree.getTransform().setScale(Scale_trees.get(i));
            complex_shader_light.setUniform("trasnform", tree.getTransform().getTransformation());
            Render.renderEBO(tree.getMesh());
        }
        
        complex_shader_light.unbinTexture(tree);
        
        
        // Render Stall //
        complex_shader_light.binTexture(house);
        house.getTransform().setTranslation(new Vector3f(150, 0, 150));
        house.getTransform().setScale(new Vector3f(0.3f, 0.3f, 0.3f));
        complex_shader_light.setUniform("trasnform", house.getTransform().getTransformation());
        Render.renderEBO(house.getMesh());
        complex_shader_light.stop();
        complex_shader_light.unbinTexture(house);
        
        // Render Fern //
        complex_shader_light.start();
        complex_shader_light.binTexture(fern);
        complex_shader_light.useFakeLight(fern.isTransparence());
        complex_shader_light.setUniformi("useLight",1);
        for (int i = 0 ; i < nbFerns ; i++)
        {
            fern.getTransform().setTranslation(position_ferns.get(i));
            fern.getTransform().setScale(new Vector3f(0.3f,0.3f,0.3f));
            complex_shader_light.setUniform("trasnform", fern.getTransform().getTransformation());
            Render.renderEBO(fern.getMesh());
        }
        complex_shader_light.stop();
        complex_shader_light.unbinTexture(fern);
        
        shader_lampe.start();
        Render.render_WithoutEBO(lampe.getMesh());
        shader_lampe.start();
        
        animationShader.start();
        animationShader.binTexture(man.getMesh().getTexture());
        animationShader.useFakeLight(false);
        animationShader.setUniformi("useLight",1);
        animationShader.LoadMatrix(man.getMesh().getJointTransforms());
        man.getTransform().setScale(new Vector3f(0.1f,0.1f,0.1f));
        animationShader.setUniform("trasnform",man.getTransform().getTransformation());
        Render.renderEBO2(man.getMesh().getModel());
        animationShader.stop();
        animationShader.unbinTexture(man.getMesh().getTexture());
    }
    
    public void update ()
    {
        terrainShader.update_Cam();
        shader_lampe.update_Cam();
        shader_light.update_Cam();
        complex_shader_light.update_Cam();
        animationShader.update_Cam();
    }
    
    boolean manControl = false ;
    
    public void input ()
    {
        if (!manControl)
        {
            camera.input();
        }else
        {
            man.move_();
        }
        
        
        if (Input.getKeyDown(Keyboard.KEY_X))
        {
            camera.info();
            manControl = !manControl;
        }
        
        
        if (Input.getKeyDown(Keyboard.KEY_H))
        {
            renderengineopengl.RenderEngineOPENGL.enable_GUI = !renderengineopengl.RenderEngineOPENGL.enable_GUI ;
            
        }
    }
    
}
