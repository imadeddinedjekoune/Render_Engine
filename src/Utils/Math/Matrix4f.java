/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Math;

import org.lwjgl.util.vector.Vector;


public class Matrix4f 
{
    float [][] m ;
    
    public Matrix4f(){
        m = new float[4][4];
    }

    
    public float[][] getM() {
        return m;
    }
    public void setM(float[][] m) {
        this.m = m;
    }
    public float get (int x , int y) {
        return m[x][y];
    }
    public void set (int x , int y , float value){
        m[x][y] = value ;
    }
    public Matrix4f initIdentity (){
        int i , j ;
        for ( i = 0 ; i < 4 ; i++ )
        {
            for ( j = 0 ; j < 4 ; j++ )
            {
                if (i == j)
                {
                    m[i][j] = 1;
                }else
                {
                    m[i][j] = 0;
                }
            }
        }
        return this;
    }
    public Matrix4f mul (Matrix4f m_){
        Matrix4f m_return = new Matrix4f();
        
        int i , j ;
        
        for ( i = 0 ; i < 4 ; i++ )
        {
            for ( j = 0 ; j < 4 ; j++ )
            {
                m_return.set(i, j, m[i][0] * m_.get(0, j)+
                                   m[i][1] * m_.get(1, j)+
                                   m[i][2] * m_.get(2, j)+
                                   m[i][3] * m_.get(3, j));
            }
        }
        
        return m_return ;
    }
    
    public Vector3f mul (Vector3f vec)
    {
        Vector3f ret = new Vector3f(this.get(0, 0)*vec.getX()+
                                    this.get(0, 1)*vec.getY()+
                                    this.get(0, 2)*vec.getZ()+
                                    this.get(0, 3)*1.0f
                                    
                                    , 
                                    
                                    this.get(1, 0)*vec.getX()+
                                    this.get(1, 1)*vec.getY()+
                                    this.get(1, 2)*vec.getZ()+
                                    this.get(1, 3)*1.0f
                
                                    , 
                                        
                                    this.get(2, 0)*vec.getX()+
                                    this.get(2, 1)*vec.getY()+
                                    this.get(2, 2)*vec.getZ()+
                                    this.get(2, 3)*1.0f
                
                                    );
        return ret ;
    }

    public Matrix4f InitScale(float x, float y, float z) 
    {
        m[0][0] = x;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = 0;
	m[1][0] = 0;	m[1][1] = y;	m[1][2] = 0;	m[1][3] = 0;
	m[2][0] = 0;	m[2][1] = 0;	m[2][2] = z;	m[2][3] = 0;
	m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;
        return this ;
    }

    public Matrix4f initTranslation(float x, float y, float z) 
    {
        m[0][0] = 1 ;     m[0][1] = 0 ;     m[0][2] = 0 ;     m[0][3] = x ;     
        m[1][0] = 0 ;     m[1][1] = 1 ;     m[1][2] = 0 ;     m[1][3] = y ;     
        m[2][0] = 0 ;     m[2][1] = 0 ;     m[2][2] = 1 ;     m[2][3] = z ;     
        m[3][0] = 0 ;     m[3][1] = 0 ;     m[3][2] = 0 ;     m[3][3] = 1 ;     
        
        return this ;
    }

    public Matrix4f initRotation(float x, float y, float z) {
        Matrix4f rx = new Matrix4f();
        Matrix4f ry = new Matrix4f();
	Matrix4f rz = new Matrix4f();
		
	x = (float)Math.toRadians(x);
	y = (float)Math.toRadians(y);
	z = (float)Math.toRadians(z);
		
	rz.m[0][0] = (float)Math.cos(z);    rz.m[0][1] = -(float)Math.sin(z);   rz.m[0][2] = 0;     rz.m[0][3] = 0;
	rz.m[1][0] = (float)Math.sin(z);    rz.m[1][1] = (float)Math.cos(z);    rz.m[1][2] = 0;     rz.m[1][3] = 0;
	rz.m[2][0] = 0;			    rz.m[2][1] = 0;                     rz.m[2][2] = 1;     rz.m[2][3] = 0;
	rz.m[3][0] = 0;			    rz.m[3][1] = 0;			rz.m[3][2] = 0;     rz.m[3][3] = 1;
	
	rx.m[0][0] = 1;					rx.m[0][1] = 0;					rx.m[0][2] = 0;					rx.m[0][3] = 0;
	rx.m[1][0] = 0;					rx.m[1][1] = (float)Math.cos(x);rx.m[1][2] = -(float)Math.sin(x);rx.m[1][3] = 0;
	rx.m[2][0] = 0;					rx.m[2][1] = (float)Math.sin(x);rx.m[2][2] = (float)Math.cos(x);rx.m[2][3] = 0;
	rx.m[3][0] = 0;					rx.m[3][1] = 0;					rx.m[3][2] = 0;					rx.m[3][3] = 1;
		
	ry.m[0][0] = (float)Math.cos(y);ry.m[0][1] = 0;					ry.m[0][2] = -(float)Math.sin(y);ry.m[0][3] = 0;
	ry.m[1][0] = 0;					ry.m[1][1] = 1;					ry.m[1][2] = 0;					ry.m[1][3] = 0;
	ry.m[2][0] = (float)Math.sin(y);ry.m[2][1] = 0;					ry.m[2][2] = (float)Math.cos(y);ry.m[2][3] = 0;
	ry.m[3][0] = 0;					ry.m[3][1] = 0;					ry.m[3][2] = 0;					ry.m[3][3] = 1;
		
	m = rz.mul(ry.mul(rx)).getM();
		
        return this;
    }
    
    
    public Matrix4f initProjection_Perspective (float fov , float width , float height , float zNear , float zFar )
    {
        float tanHalfFov = (float) Math.tan(Math.toRadians(fov/2));  
        float ar = width / height ; // Aspect Ratio //
        float zRange = zNear - zFar ;
        
        m[0][0] = 1/(tanHalfFov*ar);    m[0][1] = 0 ;                   m[0][2] = 0 ;                       m[0][3] = 0 ;     
        m[1][0] = 0 ;                   m[1][1] = 1/(tanHalfFov*ar) ;   m[1][2] = 0 ;                       m[1][3] = 0 ;     
        m[2][0] = 0 ;                   m[2][1] = 0 ;                   m[2][2] = (-zFar-zNear)/zRange ;    m[2][3] = 2 * zFar * zNear / zRange ;     
        m[3][0] = 0 ;                   m[3][1] = 0 ;                   m[3][2] = 1 ;                       m[3][3] = 0 ;     
        
        return this ;
    }
    
    public Matrix4f initCamera (Vector3f forward , Vector3f up)
    {
        Vector3f f = forward;
        f.normalize();
        
        Vector3f r = up ;
        r.normalize();
        r = r.cross(f);
    
        Vector3f u = f.cross(r);
        
        m[0][0] = r.getX() ;    m[0][1] = r.getY() ;    m[0][2] = r.getZ() ;    m[0][3] = 0;     
        m[1][0] = u.getX() ;    m[1][1] = u.getY() ;    m[1][2] = u.getZ() ;    m[1][3] = 0 ;     
        m[2][0] = f.getX() ;    m[2][1] = f.getY() ;    m[2][2] = f.getZ() ;    m[2][3] = 0 ;     
        m[3][0] = 0 ;           m[3][1] = 0 ;           m[3][2] = 0 ;           m[3][3] = 1 ;     
        
        return this ;
    }
    
    public String toString ()
    {
        String ret = "";
        for ( int i = 0 ; i < this.m.length ; i++ )
        {
            for ( int j = 0 ; j < this.m[i].length ; j++ )
            {
                ret += m[i][j]+ " ";
            }
            ret+= "\n";
        }
        return ret ;
    }
    
    public Matrix4f (org.lwjgl.util.vector.Matrix4f MOGL)
    {
        this();
        this.m[0][0] = MOGL.m00;
        this.m[0][1] = MOGL.m01;
        this.m[0][2] = MOGL.m02;
        this.m[0][3] = MOGL.m03;
        
        this.m[1][0] = MOGL.m10;
        this.m[1][1] = MOGL.m11;
        this.m[1][2] = MOGL.m12;
        this.m[1][3] = MOGL.m13;
        
        this.m[2][0] = MOGL.m20;
        this.m[2][1] = MOGL.m21;
        this.m[2][2] = MOGL.m22;
        this.m[2][3] = MOGL.m23;
        
        this.m[3][0] = MOGL.m30;
        this.m[3][1] = MOGL.m31;
        this.m[3][2] = MOGL.m32;
        this.m[3][3] = MOGL.m33;
    }
    
}





