/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIRenderer;

import Component.Mesh.Mesh;
import Component.Mesh.MeshUtilities;
import Component.Shader.Shader;
import Component.Transform.Transform;
import Utils.Math.Vector3f;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
 *
 * @author Imad
 */
public class RenderGUI 
{
    private Mesh quad ;
    public static Shader sh ;
    
    public RenderGUI ()
    {
        quad = MeshUtilities.loadToMesh_GUI(new float []{
            -1,1,-1,-1,1,1,1,-1
        });
    }
    
    public void render (ArrayList <GUITexture> guis)
    {
        glBindVertexArray(quad.getVaoID());
        glEnableVertexAttribArray(0); // Position //
        
        sh.start();
        for( int i = 0 ; i < guis.size() ; i++)
        {
            if(guis.get(i).isActive())
            {
                guis.get(i).Activate(0);
                Transform tranfrom = new Transform();
                tranfrom.setScale(new Vector3f(guis.get(i).getScale().getX(), guis.get(i).getScale().getY(), 0));
                tranfrom.setTranslation(new Vector3f(guis.get(i).getPos().getX(),
                        guis.get(i).getPos().getY(), 0));
                sh.setUniform("transformationMatrix", tranfrom.getTransformation());
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0,quad.getSizeVerts());
            }
        }
        sh.stop();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }
    
}
