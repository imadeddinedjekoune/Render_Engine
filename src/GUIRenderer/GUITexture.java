/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIRenderer;

import Component.Mesh.Texture;
import Utils.Math.Vector2f;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

/**
 *
 * @author Imad
 */
public class GUITexture 
{
    private Texture texture ;
    private Vector2f pos ;
    private Vector2f scale ;
    private boolean active = false ;
    
    public GUITexture(Texture texture, Vector2f pos, Vector2f scale) {
        this.texture = texture;
        this.pos = pos;
        this.scale = scale;
    }

    public Texture getTexture_ID() {
        return texture;
    }

    public Vector2f getPos() {
        return pos;
    }

    public Vector2f getScale() {
        return scale;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    
    public void Activate (int n)
    {
        glBindTexture(GL_TEXTURE_2D, texture.getId());
        glActiveTexture(GL_TEXTURE0+n);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}
