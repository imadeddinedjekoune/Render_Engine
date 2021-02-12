/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Shader;

import Component.Mesh.Model.LightSource;
import Component.Mesh.Model.Model;
import Component.Mesh.Model.Terrain;
import Component.Transform.Camera;
import Component.Transform.Projection;
import Component.Transform.Transform;
import Utils.Math.Vector3f;

/**
 *
 * @author Imad
 */
public class Light_Object_Shader extends Shader{
    private Camera camera ;
    private Projection projection ;
    private Transform transform ;
    private LightSource source ;

    public Light_Object_Shader(String vertexFile, String fragmentFile) {
        super(vertexFile, fragmentFile);
    }
    
    
    
    public Light_Object_Shader(Transform transform , Projection projection , Camera camera , LightSource source) {
        super("/light/object/light_vs.txt", "/light/object/light_fs.txt");
        this.camera = camera ;
        this.projection = projection ;
        this.transform = transform ;
        this.source = source ;
    }
    
    public void init ()
    {
        addUniforme("trasnform");
        setUniform("trasnform", transform.getTransformation());
        addUniforme("proj");
        setUniform("proj", projection.getProjectedMatrice());
        addUniforme("cam");
        setUniform("cam", camera.getCamera());
        addUniforme("lightColor");
        addUniforme("lightPos");
        addUniforme("viewPos");
        addUniforme("texture1");
        setUniformi("texture1", 0);
        addUniforme("scaleText");
        setUniformi("scaleText", 1);
        setUniform("lightColor", source.getLightColor() );
        setUniform("lightPos", source.getLightPos());
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
    public void binTexture (Model m)
    {
        m.getTexture().bind();
        m.getTexture().Activate(0);
    }
    
    public void unbinTexture (Model m)
    {
        m.getTexture().unbind();
    }
    
}
