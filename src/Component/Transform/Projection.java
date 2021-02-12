/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Transform;

import Utils.Math.Matrix4f;

/**
 *
 * @author Imad
 */
public class Projection {
    
    private float zNear ;
    private float zFar ;
    private float width ;
    private float height ; 
    private float fov ;

    public Projection(float zNear, float zFar, float width, float height, float fov) {
        this.zNear = zNear;
        this.zFar = zFar;
        this.width = width;
        this.height = height;
        this.fov = fov;
    }

    // Matrix Projection //
    public Matrix4f getProjectedMatrice ()
    {
        Matrix4f projection_Mat = new Matrix4f().initProjection_Perspective(fov, width, height, zNear, zFar);
        return projection_Mat ;
    }
    
    public Matrix4f test ()
    {
        
        return null;
    }
}
