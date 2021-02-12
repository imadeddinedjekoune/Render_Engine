/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation.XMLParser;


import Animation.Data.MeshData;
import Animation.KERNAL.Animation;
import Animation.KERNAL.KeyFrame;
import java.util.Map;
import org.lwjgl.util.vector.Matrix4f;

/**
 *
 * @author Imad
 */
public class Debug {
    public static void printMeshData (MeshData m)
    {
        printArr2DFloat(m.getVertices());
        printArr2DFloat(m.getTextureCoords());
        printArr2DFloat(m.getNormals());
        printArr2DInt(m.getIndices());
    }
    
    public static void printAnim(Animation A)
    {
        System.out.println("Length : "+A.getLength());
        KeyFrame T[] = A.getKeyFrames() ;
        int i ;
        for ( i = 0 ; i <  T.length ; i++)
        {
            printKeyFrame(T[i]);
        }
        
    }
    
    public static void printKeyFrame (KeyFrame kf)
    {
        System.out.println(kf.getTimeStamp());
        System.out.println(kf.getJointKeyFrames());
    }
    
    public static void printArr2DFloat (float [] t)
    {
        for (int i = 0 ; i < t.length ; i++)
        {
            System.out.print(t[i]+" ");
        }
        System.out.println("");
    }
    
    public static void printArr2DInt (int [] t)
    {
        for (int i = 0 ; i < t.length ; i++)
        {
            System.out.print(t[i]+" ");
        }
        System.out.println("");
    }

    public static void PrintMat (org.lwjgl.util.vector.Matrix4f M)
    {
        System.out.print(M.m00+" "+M.m01+" "+M.m02+" "+M.m03+"\n");
        System.out.print(M.m10+" "+M.m11+" "+M.m12+" "+M.m13+"\n");
        System.out.print(M.m20+" "+M.m21+" "+M.m22+" "+M.m23+"\n");
        System.out.print(M.m30+" "+M.m31+" "+M.m32+" "+M.m33+"\n");
        
    }
  
    public static void PrintHashMap (Map<String, Matrix4f> map)
    {
        System.out.println(map);
    }
    
}
