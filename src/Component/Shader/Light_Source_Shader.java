/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Shader;

import Component.Transform.Camera;
import Component.Transform.Projection;
import Component.Transform.Transform;
import Utils.Math.Vector3f;

/**
 *
 * @author Imad
 */
public class Light_Source_Shader extends Shader {
    
    private Camera camera ;
    private Projection projection ;
    private Transform transform ;
    private Vector3f color ;
    
    public Light_Source_Shader(Transform transform , Projection projection , Camera camera , Vector3f color) {
        super("/light/lampe/lampe_vs.txt", "/light/lampe/lampe_fs.txt");
        this.camera = camera ;
        this.projection = projection ;
        this.transform = transform ;
        this.color = color ;
    }
    
    public void init ()
    {
        addUniforme("trasnform");
        setUniform("trasnform", transform.getTransformation());
        addUniforme("proj");
        setUniform("proj", projection.getProjectedMatrice());
        addUniforme("cam");
        setUniform("cam", camera.getCamera());
        addUniforme("color");
        setUniform("color",color);
    }
    
    public void update_Cam ()
    {
        setUniform("cam", camera.getCamera());
    }
    
}
