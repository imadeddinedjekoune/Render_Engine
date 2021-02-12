/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Mesh.Model;

import Component.Mesh.Mesh;
import Component.Mesh.Texture;
import Component.Transform.Transform;

/**
 *
 * @author Imad
 */
public class Model 
{
    private Mesh mesh ;
    private Texture texture ;
    private boolean transparence = false ;
    private Transform transform ;

    public Model(Mesh mesh, Texture texture) {
        this.mesh = mesh;
        this.texture = texture;
        this.transform = new Transform();
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public boolean isTransparence() {
        return transparence;
    }

    public void setTransparence(boolean transparence) {
        this.transparence = transparence;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }
    
    
    
}
