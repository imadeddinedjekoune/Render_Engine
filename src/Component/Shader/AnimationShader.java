/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Shader;


import Component.Mesh.Model.LightSource;
import Component.Mesh.Model.Model;
import Component.Mesh.Texture;
import Component.Render.Render;
import Component.Transform.Camera;
import Component.Transform.Projection;
import Component.Transform.Transform;
import Utils.Math.Vector3f;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix;
import org.lwjgl.util.vector.Matrix4f;

/**
 *
 * @author Imad
 */
public class AnimationShader extends Shader{
    private Camera camera ;
    private Projection projection ;
    private Transform transform ;
    
    public AnimationShader(Transform transform , Projection projection , Camera camera ) 
    {
        super("/animated/animatedEntityVertex.txt","/animated/animatedEntityFragment.txt");
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
        
        for (int i = 0 ; i < 50 ; i++)
        {
            addUniforme("jointTransforms["+i+"]");
        }
        
    }
    
    public void update ()
    {
        setUniform("trasnform", transform.getTransformation());
    }
   public void update_Render ()
    {
        setUniform("viewPos",new Vector3f(-camera.getPos().getX()
                , -camera.getPos().getY(), -camera.getPos().getZ()));
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void LoadMatrix (org.lwjgl.util.vector.Matrix4f M[])
    {
        for (int i = 0 ; i < M.length ; i++)
        {
            FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
        matrixBuffer.put(M[i].m00);
        matrixBuffer.put(M[i].m01);
        matrixBuffer.put(M[i].m02);
        matrixBuffer.put(M[i].m03);
        matrixBuffer.put(M[i].m10);
        matrixBuffer.put(M[i].m11);
        matrixBuffer.put(M[i].m12);
        matrixBuffer.put(M[i].m13);
        matrixBuffer.put(M[i].m20);
        matrixBuffer.put(M[i].m21);
        matrixBuffer.put(M[i].m22);
        matrixBuffer.put(M[i].m23);
        matrixBuffer.put(M[i].m30);
        matrixBuffer.put(M[i].m31);
        matrixBuffer.put(M[i].m32);
        matrixBuffer.put(M[i].m33);
        matrixBuffer.flip();
	GL20.glUniformMatrix4(i, false, matrixBuffer);
        }
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
    public void unbinTexture (Texture m)
    {
        m.unbind();
    }

    public void unbinTexture (Model m)
    {
        m.getTexture().unbind();
    }
    public void binTexture (Texture m)
    {
        m.bind();
        m.Activate(0);
    }
    
    
  
}
