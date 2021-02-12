/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Mesh;

import Utils.Buffer.Util;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_INT;
import org.lwjgl.opengl.GL15;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import org.lwjgl.opengl.GL30;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.opengl.GL30.glVertexAttribIPointer;

/**
 *
 * @author Imad
 */
public class MeshUtilities 
{
    private static  ArrayList<Integer> vaos = new ArrayList<Integer>();
    private static ArrayList<Integer> vbos = new ArrayList<Integer>();
    
    
    public static Mesh load_To_Mesh (float position[] , float text[], float normals [] , 
              int [] idJoints , float [] VertexWeights, int indices[] , int mode)
    {
        int vaoID , lenght;
        Mesh m ;
        switch (mode)
        {
            case 0 : // Position //
                vaoID = createVAO(); 
                storeDataInAttributeList(position,3,0);
                unbindVAO();
                lenght = position.length/3 ;
                m = new Mesh(vaoID, lenght);
                return m;
            case 1 : // Position + Texture //
                vaoID = createVAO(); 
                storeDataInAttributeList(position,3,0);
                storeDataInAttributeList(text,2,1);
                unbindVAO();
                lenght = position.length/3 + text.length/2 ;
                m = new Mesh(vaoID, lenght);
                return m ;
            case 2 : // Position + Normal //
                vaoID = createVAO(); 
                storeDataInAttributeList(position,3,0);
                storeDataInAttributeList(normals,3,2);
                unbindVAO();
                lenght = position.length/3 + normals.length/3 ;
                m = new Mesh(vaoID, lenght);
                return m ;
            case 3 : // Position (Index) //
                vaoID = createVAO(); 
                bindIndicesBuffer(indices); 
                storeDataInAttributeList(position,3,0);
                unbindVAO();
                m = new Mesh(vaoID, indices.length);
                return m;
            case 4 : // Position + Texture (Index) //
                vaoID = createVAO(); 
                bindIndicesBuffer(indices);
                storeDataInAttributeList(position,3,0);
                storeDataInAttributeList(text,2,1);
                unbindVAO();
                m = new Mesh(vaoID, indices.length);
                return m ;
            case 5 : // Position + Normal (Index) //
                vaoID = createVAO(); 
                bindIndicesBuffer(indices);
                storeDataInAttributeList(position,3,0);
                storeDataInAttributeList(normals,3,2);
                unbindVAO();
                m = new Mesh(vaoID, indices.length);
                return m ;
            case 6 : // Position + Texture + Normal //
                vaoID = createVAO(); 
                storeDataInAttributeList(position,3,0);
                storeDataInAttributeList(text, 2, 1);
                storeDataInAttributeList(normals,3,2);
                unbindVAO();
                lenght = position.length/3 + text.length/2 + normals.length/3 ;
                m = new Mesh(vaoID, lenght);
                return m ;
            case 7 : // Position + Texture + Normal (Index) //
                vaoID = createVAO(); 
                bindIndicesBuffer(indices);
                storeDataInAttributeList(position,3,0);
                storeDataInAttributeList(text, 2, 1);
                storeDataInAttributeList(normals,3,2);
                unbindVAO();
                m = new Mesh(vaoID, indices.length);
                return m ;
            case 8 : // Animtion //
                vaoID = createVAO(); 
                bindIndicesBuffer(indices);
                storeDataInAttributeList(position,3,0);
                storeDataInAttributeList(text, 2, 1);
                storeDataInAttributeList(normals,3,2);
                storeDataInAttributeList(idJoints,3,3);
                storeDataInAttributeList(VertexWeights,3,4);
                unbindVAO();
                m = new Mesh(vaoID, indices.length);
                return m ;
               
        }
        return null ;
    }
    
    public static Mesh loadToMesh (float position[] , float text[], float normals [] , int indices[])
    {
        int vaoID = createVAO(); // Creer VAO //
        bindIndicesBuffer(indices); // Lier Les Indexes //
        storeDataInAttributeList(position,3,0);
        storeDataInAttributeList(text,2,1);
        storeDataInAttributeList(normals,3,2);
        unbindVAO();
        Mesh m = new Mesh(vaoID, indices.length);
        return m;
    }
    
    public static Mesh loadToMesh_pos_text_norm (float position[] , float text[], float normals [])
    {
        int vaoID = createVAO(); // Creer VAO //
        storeDataInAttributeList(position,3,0);
        storeDataInAttributeList(text,2,1);
        storeDataInAttributeList(normals,3,2);
        unbindVAO();
        int lenght = position.length/3 + text.length/2 + normals.length/3 ;
        Mesh m = new Mesh(vaoID, lenght);
        return m;
    }
    
    public static Mesh loadToMesh_pos (float position[])
    {
        int vaoID = createVAO(); // Creer VAO //
        storeDataInAttributeList(position,3,0);
        unbindVAO();
        int lenght = position.length/3 ;
        Mesh m = new Mesh(vaoID, lenght);
        return m;
    }
    
    public static Mesh loadToMesh_pos_norm (float position[] , float [] normals)
    {
        int vaoID = createVAO(); // Creer VAO //
        storeDataInAttributeList(position,3,0);
        storeDataInAttributeList(normals,3,1);
        unbindVAO();
        int lenght = position.length/3 + normals.length/3  ;
        Mesh m = new Mesh(vaoID, lenght);
        return m;
    }
    
    public static Mesh loadToMesh_GUI (float position[])
    {
        int vaoID = createVAO(); // Creer VAO //
        storeDataInAttributeList(position,2,0);
        unbindVAO();
        Mesh m = new Mesh(vaoID, position.length/2);
        return m;
    }
    
    private static int createVAO ()
    {
        int vaoID = glGenVertexArrays();
        vaos.add(vaoID);
        glBindVertexArray(vaoID);
        return vaoID;
    }
    
    private static void unbindVAO ()
    {
        glBindVertexArray(0);
    }
    
    private static void storeDataInAttributeList(FloatBuffer buff,int size, int attribNummber)
    {
        int vboID = glGenBuffers();
        vbos.add(vboID);
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, buff , GL_STATIC_DRAW);
        glVertexAttribPointer(attribNummber,size,GL_FLOAT,false,0,0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
    
    private static void storeDataInAttributeList( float data[] ,int size, int attribNummber)
    {
        int vboID = glGenBuffers();
        vbos.add(vboID);
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, Util.store_Array_In_FloatBuffer(data) , GL_STATIC_DRAW);
        glVertexAttribPointer(attribNummber,size,GL_FLOAT,false,0,0);
        glBindBuffer(GL_ARRAY_BUFFER, 0); // Unbind //
    }
    
    private static void storeDataInAttributeList( int data[] ,int size, int attribNummber)
    {
        int vboID = glGenBuffers();
        vbos.add(vboID);
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, Util.store_Array_In_IntBuffer(data) , GL_STATIC_DRAW);
        glVertexAttribIPointer(attribNummber, size, GL11.GL_INT, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0); // Unbind //
    }
    
    private static void bindIndicesBuffer(int indices[])
    {
        int vboID = glGenBuffers();
        vbos.add(vboID);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.store_Array_In_IntBuffer(indices),GL_STATIC_DRAW );
    }
    
    public static void ChangeIndicesBuffer(int indices[] , int vboID )
    {   
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.store_Array_In_IntBuffer(indices),GL_STATIC_DRAW );
    }
    
    public static void cleanUP ()
    {
        int i ;
        for ( i = 0 ; i < vaos.size() ; i++ )
        {
            glDeleteVertexArrays(vaos.get(i));
        }
        for ( i = 0 ; i < vbos.size() ; i++ )
        {
            glDeleteBuffers(vbos.get(i));
        }
    }
}
