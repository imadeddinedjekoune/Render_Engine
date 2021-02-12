/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Shader;

import Component.Mesh.Model.LightSource;
import Component.Mesh.Model.Terrain;
import Component.Render.Render;
import Component.Transform.Camera;
import Component.Transform.Projection;
import Component.Transform.Transform;
import Utils.Math.Vector3f;

/**
 *
 * @author Imad
 */
public class TerrainShader extends Shader
{
    private Camera camera ;
    private Projection projection ;
    private Transform transform ;
    private Terrain terrain ;
    private LightSource source ;
    
    public TerrainShader(String vertexFile, String fragmentFile) {
        super(vertexFile, fragmentFile);
    }
    
    public TerrainShader( Projection projection , Camera camera , Terrain t , LightSource source ) 
    {
        super("/terrain/vsterrain.txt","/terrain/fsterrain.txt");
        this.camera = camera ;
        this.projection = projection ;
        this.terrain = t ;
        this.transform = terrain.getTransform() ;
        this.source = source ;
    }
    
    public void init ()
    {
        addUniforme("texture1");
        setUniformi("texture1", 0);
        
        addUniforme("texture2");
        setUniformi("texture2", 1);
        
        addUniforme("texture3");
        setUniformi("texture3", 2);
        
        addUniforme("texture4");
        setUniformi("texture4", 3);
        
        addUniforme("texture5");
        setUniformi("texture5", 4);
        
        addUniforme("trasnform");
        setUniform("trasnform", transform.getTransformation());
        
        addUniforme("proj");
        setUniform("proj", projection.getProjectedMatrice());
        
        addUniforme("cam");
        setUniform("cam", camera.getCamera());
        
        addUniforme("cam");
        setUniform("cam", camera.getCamera());
        
        addUniforme("scaleText");
        setUniformi("scaleText", 40);
        
        addUniforme("lightColor");
        setUniform("lightColor", source.getLightColor() );
        
        addUniforme("lightPos");
        setUniform("lightPos", source.getLightPos());
        
        addUniforme("viewPos");
        
        addUniforme("skyColor");
        setUniform("skyColor", new Vector3f(Render.R_CIEL,Render.G_CIEL,Render.B_CIEL));
        
        addUniforme("useLight");
        setUniformi("useLight", 1);
    }
    
    public void bindTexture ()
    {
        terrain.getTextureBackGround().Activate(0);
        terrain.getTextureBackGround().bind();
        
        terrain.getTextureR().Activate(1);
        terrain.getTextureR().bind();
        
        terrain.getTextureR().Activate(2);
        terrain.getTextureG().bind();
        
        terrain.getTextureR().Activate(3);
        terrain.getTextureB().bind();
        
        terrain.getTextureR().Activate(4);
        terrain.getTextureMap().bind();
        
    }
    
    public void unbindTexture ()
    {
        terrain.getTextureBackGround().unbind();
        terrain.getTextureR().unbind();
        terrain.getTextureG().unbind();
        terrain.getTextureB().unbind();
        terrain.getTextureMap().unbind();
    }
    
    public void update ()
    {
        setUniform("cam", camera.getCamera());
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setProjection(Projection projection) {
        this.projection = projection;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }
    
    public void update_Render ()
    {
        setUniform("viewPos",new Vector3f(-camera.getPos().getX()
                , -camera.getPos().getY(), -camera.getPos().getZ()));
    }
    
    public void update_Cam ()
    {
        setUniform("cam", camera.getCamera());
    }
}
