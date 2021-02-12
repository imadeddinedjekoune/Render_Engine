/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Transform;

import Utils.Input.Input;
import Utils.Math.Matrix4f;
import Utils.Math.Vector3f;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Imad
 */
public class Camera {
    public static final Vector3f yAxis = new Vector3f(0, 1, 0);
    public static final Vector3f xAxis = new Vector3f(1, 0, 0);
    public static final Vector3f zAxis = new Vector3f(0, 0, 1);
    
    
    
    private Vector3f pos ;
    private Vector3f forward ;
    private Vector3f up ;
    
    private float pitch ;
    private float yaw ;
    
    float movAmt  = (float) (0.005f );
    float rotAmt  = (float) (1f );
    

    public Camera()
    {
        this(new Vector3f(0, 0, 0),new Vector3f(0, 0, 1),new Vector3f(0, 1, 0));
        System.out.println(movAmt);
    }
    
    public Camera(Vector3f pos, Vector3f forward, Vector3f up) {
        this.pos = pos;
        this.forward = forward;
        this.up = up;
        
        up.normalize();
        forward.normalize();
        
        pitch = calcPitch(forward);
        yaw = calcYaw(forward);
    }
    
    public void move (Vector3f dir , float amt)
    {
        pos = pos.add(dir.mul(amt));
    }

    public Vector3f getLeft ()
    {
        Vector3f left = forward.cross(up) ;
        left.normalize();
        return left;
    }
    public Vector3f getRight ()
    {
        Vector3f right = up.cross(forward) ;
        right.normalize();
        return right;
    }
    
    public void rotatX (float angle)
    {
        Vector3f Haxis = yAxis.cross(forward) ;
        Haxis.normalize() ;
        
        forward.rotate(angle , Haxis);
        forward.normalize();
       
    }
    
    public void rotatY (float angle)
    {
        Vector3f Haxis = yAxis.cross(forward) ;
        Haxis.normalize() ;
        
        forward.rotate(angle , yAxis);
        forward.normalize() ;
        
    }
    
    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector3f getForward() {
        return forward;
    }

    public void setForward(Vector3f forward) {
        this.forward = forward;
    }

    public Vector3f getUp() {
        return up;
    }

    public void setUp(Vector3f up) {
        this.up = up;
    }

    public void input() {
        
        //movAmt = 0.00005f * renderengineopengl.RenderEngineOPENGL.FPS ;
        if (Input.getKeyDown(Keyboard.KEY_I))
        {
            movAmt *= 2;
        }
         
        if (Input.getKeyDown(Keyboard.KEY_O))
            movAmt /= 2;
        
         if (Input.getKey(Keyboard.KEY_P))
            move(getForward(), movAmt);
        
        if (Input.getKey(Keyboard.KEY_S))
            move(getForward(), movAmt);
        
        if (Input.getKey(Keyboard.KEY_Z))
            move(getForward(), -movAmt);
        
        if (Input.getKey(Keyboard.KEY_D))
            move(getLeft(), movAmt);
        
        if (Input.getKey(Keyboard.KEY_Q))
            move(getRight(), movAmt);
        
        if (Input.getKey(Keyboard.KEY_E))
            move(getUp(), movAmt);
        
        if (Input.getKey(Keyboard.KEY_A))
            move(getUp(), -movAmt);
        
        if (Input.getKey(Keyboard.KEY_DOWN))
            rotatX(rotAmt);
        
        if (Input.getKey(Keyboard.KEY_UP))
            rotatX(-rotAmt);
        
        if (Input.getKey(Keyboard.KEY_RIGHT))
            rotatY(rotAmt);
        
        if (Input.getKey(Keyboard.KEY_LEFT))
            rotatY(-rotAmt);
        
    }
    
    public Matrix4f getCamera ()
    {
        Matrix4f cameraRotation = new Matrix4f().initCamera(getForward(), getUp());
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(getPos().getX(),getPos().getY(),getPos().getZ());
        
        return cameraRotation.mul(cameraTranslation) ;
    }
    
    public static float calcPitch (Vector3f forward)
    {
        return (float)(Math.asin(forward.getY())) ;
    }
    
    public static float calcYaw (Vector3f forward)
    {
        return (float)(Math.acos(forward.dot(xAxis)));
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }
    
    public void updatePY ()
    {
        pitch = calcPitch(forward);
        yaw = calcYaw(forward);
    }
    
    public void setForward ()
    {
        float xzLen = (float)(Math.cos(pitch));
        float x = xzLen * (float)(Math.cos(yaw));
        float y = (float)(Math.sin(pitch));
        float z = xzLen * (float)(Math.sin(yaw));
        
        forward = new Vector3f(x, y, z);
    }
    
   
    public void info()
    {
        System.out.println("Camera Pos : "+getPos());
            System.out.println("Camera For : "+getForward());
            System.out.println("Camera Up_ : "+getUp());
            System.out.println("Camera Lft : "+getLeft());
            System.out.println("Camera Rgt : "+getRight());
            System.out.println("Info : Pitch : "+getPitch() +" , Yaw : "+getYaw());
            System.out.println("-------------------------------");
    }
    
}
