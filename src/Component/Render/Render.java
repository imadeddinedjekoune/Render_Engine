/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Render;

import Utils.Window.Window;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_SRGB;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import Component.Mesh.Mesh;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.glPolygonMode;

/**
 *
 * @author Imad
 */
public class Render {
    public static final float R_CIEL = 0.529f ;
    public static final float G_CIEL = 0.807f ;
    public static final float B_CIEL = 0.921f ;
    
    
    public static void initGUI ()
    {
        glClearColor(R_CIEL, G_CIEL, B_CIEL, 0.0f);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_FRAMEBUFFER_SRGB);
        //glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
    }
    
    public static void update ()
    {
        glViewport(0,0, Window.getNewWidth(), Window.getNewHeight());
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
    
    public static void renderEBO (Mesh mesh)
    {
        glBindVertexArray(mesh.getVaoID());
        glEnableVertexAttribArray(0); // Position //
        glEnableVertexAttribArray(1); // Texture //
        glEnableVertexAttribArray(2); // Normal //
        
        
        glDrawElements(GL_TRIANGLES, mesh.getSizeVerts(),GL_UNSIGNED_INT,0);
        
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        
        
        glBindVertexArray(0);
    }
    
    public static void renderEBO2 (Mesh mesh)
    {
        glBindVertexArray(mesh.getVaoID());
        glEnableVertexAttribArray(0); // Position //
        glEnableVertexAttribArray(1); // Texture //
        glEnableVertexAttribArray(2); // Normal //
        glEnableVertexAttribArray(3); // Joints //
        glEnableVertexAttribArray(4); // Weight //
        
        
        glDrawElements(GL_TRIANGLES, mesh.getSizeVerts(),GL_UNSIGNED_INT,0);
        
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glDisableVertexAttribArray(3);
        glDisableVertexAttribArray(4);
        
        
        glBindVertexArray(0);
    }
    
    public static void render_WithoutEBO (Mesh mesh)
    {
        glBindVertexArray(mesh.getVaoID());
        glEnableVertexAttribArray(0); // Position //
        glEnableVertexAttribArray(1); // Texture //
        glEnableVertexAttribArray(2); // Normal //
        
        GL11.glDrawArrays(GL_TRIANGLES,0,mesh.getSizeVerts());
        
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        
        glBindVertexArray(0);
    }
    
    public static void enableCulling ()
    {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_FRONT);
    }
    public static void disableCulling ()
    {
        GL11.glDisable(GL11.GL_CULL_FACE);
    }
    
    
}


    