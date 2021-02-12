/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Mesh.Model;

import Component.Mesh.Mesh;
import Component.Transform.Transform;
import Utils.Math.Vector3f;

/**
 *
 * @author Imad
 */
public class LightSource  {
    private Mesh mesh ;
    private Vector3f lightPos ;
    private Vector3f lightColor ;
    private Transform transform ;

    public LightSource(Mesh mesh, Vector3f lightPos, Vector3f lightColor) {
        this.mesh = mesh;
        this.lightPos = lightPos;
        this.lightColor = lightColor;
        transform = new Transform();
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public Vector3f getLightPos() {
        return lightPos;
    }

    public void setLightPos(Vector3f lightPos) {
        this.lightPos = lightPos;
    }

    public Vector3f getLightColor() {
        return lightColor;
    }

    public void setLightColor(Vector3f lightColor) {
        this.lightColor = lightColor;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }
    
    
    
}
