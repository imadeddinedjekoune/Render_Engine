package Utils.Math;


public class Vector3f 
{
    private float x , y , z ;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
    
    public String toString ()
    {
        return "( "+x+" , "+y+" , "+z+" )";
    }

     public float length ()
    {
        return (float) Math.sqrt(x*x+y*y+z*z);
    }
    
    public float dot (Vector3f v)
    {
        return x * v.x + y * v.y + z * v.z ;
    }
    
    public Vector3f normalize ()
    {
        float length = length();
        x /= length ;
        y /= length ;
        z /= length ;
        
        return this ;
    }
    
    public Vector2f rotate (float angle)
    {
        return null ;
    }
    
    public Vector3f cross (Vector3f v)
    {
        float x_ = y * v.z - z * v.y ;
        float y_ = z * v.x - x * v.z ;
        float z_ = x * v.y - y * v.x ;
        
        return new Vector3f(x_, y_, z_) ;
    }
    
    public Vector3f add (Vector3f v)
    {
        return new Vector3f(x + v.x , y + v.y , z + v.z );
    }
    
    public Vector3f add (float f)
    {
        return new Vector3f(x+f, y+f , z+f);
    }
    
    public Vector3f sub (Vector3f v)
    {
        return new Vector3f(x - v.x , y - v.y , z - v.z );
    }
    
    public Vector3f sub (float f)
    {
        return new Vector3f(x-f, y-f , z-f);
    }
    
    public Vector3f mul (Vector3f v)
    {
        return new Vector3f(x * v.x , y * v.y , z * v.z );
    }
    
    public Vector3f mul (float f)
    {
        return new Vector3f(x*f, y*f , z*f);
    }
    
    public Vector3f div (Vector3f v)
    {
        return new Vector3f(x / v.x , y / v.y , z / v.z );
    }
    
    public Vector3f div (float f)
    {
        return new Vector3f(x/f, y/f , z/f);
    }

    public Vector3f rotate(float angle, Vector3f axis) // rtate a vector with angle and axes using quat //
    {
        float sinHalfAngle = (float) Math.sin(Math.toRadians(angle/2));
        float cosHalfAngle = (float) Math.cos(Math.toRadians(angle/2));
        
        float rx = axis.getX() * sinHalfAngle;
        float ry = axis.getY() * sinHalfAngle;
        float rz = axis.getZ() * sinHalfAngle;
        float rw = cosHalfAngle ;
        
        Quaternion rotation = new Quaternion(rx, ry, rz, rw);
        Quaternion conjugate = rotation.conjugate();
        Quaternion w = rotation.mul(this).mul(conjugate);
        
        x = w.getX();
        y = w.getY();
        z = w.getZ();
        
        return this ;
    }
    
}
