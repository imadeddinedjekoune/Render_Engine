/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Shader;

import Component.Mesh.Model.LightSource;
import Component.Mesh.Model.Model;
import Component.Mesh.Model.Terrain;
import Component.Mesh.Texture;
import Component.Render.Render;
import Component.Transform.Camera;
import Component.Transform.Projection;
import Component.Transform.Transform;
import Utils.Math.Vector3f;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Imad
 */
public class AnimShader extends Shader
{
    
    private Camera camera ;
    private Projection projection ;
    private Transform transform ;
    private LightSource source ;
    
    public AnimShader(Transform transform , Projection projection
            , Camera camera , LightSource source)
    {
        super("/animated/Alight_vs.txt", "/animated/Alight_fs.txt");
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
        addUniforme("fakeLight");
        addUniforme("lightPos");
        addUniforme("viewPos");
        addUniforme("useLight");
        setUniformi("useLight",1);
        addUniforme("texture1");
        setUniformi("texture1", 0);
        addUniforme("scaleText");
        setUniformi("scaleText", 1);
        setUniform("lightColor", source.getLightColor() );
        setUniform("lightPos", source.getLightPos());
        
        addUniforme("skyColor");
        setUniform("skyColor", new Vector3f(Render.R_CIEL,Render.G_CIEL,Render.B_CIEL));
        
        for (int i = 0 ; i < 50 ; i++)
        {
            addUniforme("jointTransforms["+i+"]");
        }
        
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
        m.getTexture().Activate(0);
        m.getTexture().bind();
    }
    public void binTexture (Texture m)
    {
        m.Activate(0);
        m.bind();
    }
    public void unbinTexture (Model m)
    {
        m.getTexture().unbind();
    }
    public void unbinTexture (Texture m)
    {
        m.unbind();
    }
    
    public void useFakeLight (boolean b)
    {
        if (b)
        {
            setUniformf("fakeLight", 1.0f);
        }else
        {
            setUniformf("fakeLight", 0.0f);
        }
    }
}
