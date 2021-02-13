/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Mesh.Model;

import Animation.KERNAL.AnimatedModel;
import Animation.KERNAL.Animation;
import Component.Mesh.Mesh;
import Component.Mesh.Texture;
import Component.Transform.Camera;
import Component.Transform.Transform;
import Utils.Input.Input;
import Utils.Math.Vector3f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import renderengineopengl.RenderEngineOPENGL;

/**
 *
 * @author Imad
 */
public class AnimatedPlayer {
    private AnimatedModel model ;
    private Animation animation ;
    private Transform transform ;
    private boolean jump = false;
    private Camera cam ;
    
    private float distanceFromPlayer = 1 ;
    private float angleAroundThePlayer = 0 ;

    public AnimatedPlayer(AnimatedModel m , Animation a , Camera cam) {
        this.animation = a ;
        this.model = m ;
        this.transform = new Transform();
        transform.setRotation(new Vector3f(0, -45, 0));
        this.cam = cam ;
    }
    
    public AnimatedModel getMesh() {
        return model;
    }
    
    public void updateAnimation ()
    {
        this.getMesh().update();
    }

    public void doAnimation()
    {
        model.doAnimation(animation);
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }
    
    public void playerBestAgrs ()
    {
        cam.setPitch(0.2399997f);
        distanceFromPlayer =  5.630021f;
    }
    
    public void move_()
    {
        // Partie De Camera //
        calculatePitch();

        float hz_dist = (float)(distanceFromPlayer*Math.cos(cam.getPitch()));
        float vc_dist = (float)(distanceFromPlayer*Math.sin(cam.getPitch()));
        
        calculateCameraPos(hz_dist,vc_dist);
        cam.setYaw(1.57079f+(float)(Math.toRadians(transform.getRotation().getY()+angleAroundThePlayer)));
        cam.setForward();
        
        // Partie De Mouvement //
        boolean playerInputs[] = getInputs();
        int cpt = 0 ;
        
        if (playerInputs[0])
        {
            cpt++;
            if (cpt != 0)
                animateCorectement();
            
            float speed = 0.0005f ;
            float distance ;
            if (RenderEngineOPENGL.FPS == 0)
                distance = speed * RenderEngineOPENGL.FPS ;
            else
                distance = speed * 120 ;
            
            float dx = (float)(distance * Math.sin( -Math.toRadians(transform.getRotation().getY()))) ;
            float dz = (float)(distance * Math.cos(-(Math.toRadians(transform.getRotation().getY())))) ;
            transform.getTranslation().setX(transform.getTranslation().getX()+dx);
            transform.getTranslation().setZ(transform.getTranslation().getZ()+dz);
        }
        if (playerInputs[1])
        {
            cpt++;
            if (cpt != 0)
                animateCorectement();
            float speed = -0.0005f ;
            float distance ;
            if (RenderEngineOPENGL.FPS == 0)
                distance = speed * RenderEngineOPENGL.FPS ;
            else
                distance = speed * 120 ;
            
            float dx = (float)(distance * Math.sin(-Math.toRadians(transform.getRotation().getY()))) ;
            float dz = (float)(distance * Math.cos(-(Math.toRadians(transform.getRotation().getY())))) ;
            transform.getTranslation().setX(transform.getTranslation().getX()+dx);
            transform.getTranslation().setZ(transform.getTranslation().getZ()+dz);
        }
        if (playerInputs[2])
        {
            cpt++;
            Vector3f newRotation = new Vector3f(0, transform.getRotation().getY()-2, 0);
            transform.setRotation(newRotation);
        }
        if (playerInputs[3])
        {
            cpt++;
            Vector3f newRotation = new Vector3f(0, transform.getRotation().getY()+2, 0);
            transform.setRotation(newRotation);
        }
        if (playerInputs[4])
        {
            cpt++;
            jump = true ;
        }
        
        if (jump)
        {
            if ((cpt != 1))
                animateCorectement();
            if (!time_Dawn)
            {
                val += (float)(Math.log10(val+1.62f))*0.09;
                if (val > 1)
                {
                    time_Dawn = true ;
                }
            }else
            {
                val -= (float)(Math.log10(val+1.62f))*0.09;
                if(val < 0)
                {
                    val = 0 ;
                    jump = false;
                    time_Dawn = false ;
                }
            }
        }
        transform.getTranslation().setY(val);
        if (cpt == 0)
        {
           this.model.getAnimator().setAnimationTime(0.6f);
           updateAnimation();
           
        }
    }
    
    
    
    private boolean [] getInputs()
    {
        boolean T[] = new boolean[5];
        
        if (Input.getKey(Keyboard.KEY_SPACE))
            T[4] = true ;
        
        if (Input.getKey(Keyboard.KEY_UP))
            T[0] = true ;
        
        if (Input.getKey(Keyboard.KEY_DOWN))
            T[1] = true ;
        
        if (Input.getKey(Keyboard.KEY_RIGHT))
            T[2] = true ;
        
        if (Input.getKey(Keyboard.KEY_LEFT))
            T[3] = true ;
        
        
        return T ;
    }
    
    float val = 0 ;
    boolean time_Dawn = false ;
   

    public Camera getCam() {
        return cam;
    }

    public void setCam(Camera cam) {
        this.cam = cam;
    }
    
    
    public void calculatePitch ()
    {
        if (Mouse.isButtonDown(1))
        {
            float pitchChange = Mouse.getDY() * 0.01f ;
            cam.setPitch(cam.getPitch()-pitchChange);
            //System.out.println(cam.getPitch()+" "+this.distanceFromPlayer);
        }
        
        if (Mouse.isButtonDown(0))
        {
            distanceFromPlayer += Mouse.getDY()*0.01f;
        }
    }
    
    public void calculateCameraPos (float hz , float vc)
    {
        float x , y , z , theta , offsetX , offsetZ ;
        
        theta = -transform.getRotation().getY() + angleAroundThePlayer ;
        offsetX = (float)(hz * Math.sin(Math.toRadians(theta)));
        offsetZ = (float)(hz * Math.cos(Math.toRadians(theta)));
        
        x = this.transform.getTranslation().getX() - offsetX ;
        y = this.transform.getTranslation().getY() + vc ;
        z = this.transform.getTranslation().getZ() - offsetZ ;
        cam.setPos(new Vector3f(-x, -y-1, -z));
    }
    
    public void animateCorectement ()
    {
        if (this.model.getAnimator().getAnimationTime() < animation.getLength() )
        {
            updateAnimation();
        }
    }
    
}
