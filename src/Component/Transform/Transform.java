/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Transform;

import Utils.Math.Matrix4f;
import Utils.Math.Vector2f;
import Utils.Math.Vector3f;

/**
 *
 * @author Imad
 */
public class Transform 
{
    private Vector3f scale ;
    private Vector3f translation ;
    private Vector3f rotation ;
    
    public Transform()
    {
        translation = new Vector3f(0, 0, 0) ;
        scale = new Vector3f(1, 1, 1);
        rotation = new Vector3f(0, 0, 0);
    }
    
    // Setters And Getters //
    public Vector3f getScale() 
    {
        return scale;
    }
    public void setScale(Vector3f scale) 
    {
        this.scale = scale;
    }
    public Vector3f getTranslation() 
    {
        return translation;
    }
    public void setTranslation(Vector3f translation) 
    {
        this.translation = translation;
    }
    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    
    // Matrix Tranformation //
    public Matrix4f getTransformation ()
    { 
        Matrix4f translation_Mat = new Matrix4f().initTranslation
                (translation.getX(),translation.getY() ,translation.getZ() );
        Matrix4f rotation_Mat = new Matrix4f().initRotation(rotation.getX()
                                        , rotation.getY(), rotation.getZ()) ;
        Matrix4f scale_Mat = new Matrix4f().InitScale(scale.getX(), scale.getY(),
                                                scale.getZ());
        return translation_Mat.mul(rotation_Mat.mul(scale_Mat));
    }
    
    
    
}
