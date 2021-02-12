/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Shader;

import Component.Mesh.Model.Model;
import Component.Transform.Camera;
import Component.Transform.Projection;
import Component.Transform.Transform;
import Utils.Math.Matrix4f;
import Utils.Math.Vector3f;

/**
 *
 * @author Imad
 */
public class BasicShader extends Shader
{
    private Camera camera ;
    private Projection projection ;
    private Transform transform ;
    
    public BasicShader(Transform transform , Projection projection , Camera camera ) 
    {
        super("/basic/vs.txt","/basic/fs.txt");
        this.camera = camera ;
        this.projection = projection ;
        this.transform = transform ;
    }
    
    public void init ()
    {
        addUniforme("texture1");
        setUniformi("texture1", 0);
        
        addUniforme("trasnform");
        setUniform("trasnform", transform.getTransformation());
        
        addUniforme("proj");
        setUniform("proj", projection.getProjectedMatrice());
        
        addUniforme("cam");
        setUniform("cam", camera.getCamera());
    }
    
    public void update ()
    {
        setUniform("cam", camera.getCamera());
        setUniform("trasnform", transform.getTransformation());
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
    
    public void binTexture (Model m)
    {
        m.getTexture().bind();
        m.getTexture().Activate(0);
    }
    
}
