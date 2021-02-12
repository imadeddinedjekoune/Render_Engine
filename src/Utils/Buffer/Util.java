/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Buffer;

import Utils.Math.Matrix4f;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;

/**
 *
 * @author Imad
 */
public class Util {
    public static IntBuffer createIntBuffer (int size)
    {
        return BufferUtils.createIntBuffer(size);
    }
    public static FloatBuffer createFloatBuffer (int size)
    {
        return BufferUtils.createFloatBuffer(size);
    }
    
    public static IntBuffer store_Array_In_IntBuffer (int array[])
    {
        IntBuffer buffer = createIntBuffer(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }
    
    public static FloatBuffer store_Array_In_FloatBuffer (float array[])
    {
        FloatBuffer buffer = createFloatBuffer(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }
    public static FloatBuffer store_Matrix4f_In_FloatBuffer (Matrix4f value)
    {
        FloatBuffer buffer = createFloatBuffer(4*4);
        
        int i , j ;
        
        for ( i = 0 ; i < 4 ; i++ )
        {
            for ( j = 0 ; j < 4 ; j++ )
            {
                buffer.put(value.get(i, j));
            }
        }
        buffer.flip();
        return buffer;
    }
    
    public static FloatBuffer store_Matrix4f_In_FloatBuffer (Matrix4f[] value)
    {
        FloatBuffer buffer = createFloatBuffer(4*4);
        
        int i , j , k;
        
        for (k = 0 ; k < value.length ; k++ )
        {
            for ( i = 0 ; i < 4 ; i++ )
            {
                for ( j = 0 ; j < 4 ; j++ )
                {
                    buffer.put(value[k].get(i, j));
                }
            }
        }
        buffer.flip();
        return buffer;
    }
    
    public static FloatBuffer store_Matrix4fT_In_FloatBuffer(org.lwjgl.util.vector.Matrix4f[] matrices)
    {
        return createFloatBuffer(0);
    }
}
