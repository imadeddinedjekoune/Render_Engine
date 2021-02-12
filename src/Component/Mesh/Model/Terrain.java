/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Mesh.Model;

import Component.Mesh.Mesh;
import Component.Mesh.MeshUtilities;
import Component.Mesh.Texture;
import Component.Transform.Transform;
import Utils.Math.Vector3f;
import Utils.Noise.OpenSimplexNoise;
import java.util.Random;

/**
 *
 * @author Imad
 */
public class Terrain 
{
    private static final int SIZE = 700 ;
    private static final int VERTEX_COUNT = 128 ;
    
    private float x , z ;
    private Mesh model ;
    private Texture textureBackGround ;
    private Texture textureR ;
    private Texture textureG ;
    private Texture textureB ;
    private Texture textureMap ;
    private Transform transform; 

    public Mesh getModel() {
        return model;
    }

    
    public Terrain (int gridX , int gridZ , Texture t1
            ,  Texture t2 ,Texture t3 ,Texture t4 ,Texture t5 
    )
    {
        textureBackGround = t1 ;
        textureR = t2 ;
        textureG = t3 ;
        textureB = t4 ;
        textureMap = t5 ;
        
        x = gridX * SIZE ;
        z = gridZ * SIZE ;
        model = generateTerrain();
        this.transform = new Transform();
    }
    
    private Mesh generateTerrain()
    {
        OpenSimplexNoise noise = new OpenSimplexNoise();
        int count = VERTEX_COUNT * VERTEX_COUNT;
        float[] vertices = new float[count * 3];
        float[] normals = new float[count * 3];
        float[] textureCoords = new float[count*2];
        int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT-1)];
        int vertexPointer = 0;
        for(int i=0;i<VERTEX_COUNT;i++){
            for(int j=0;j<VERTEX_COUNT;j++){
                float z = (float) noise.eval((float)j/((float)VERTEX_COUNT - 1) * SIZE, (float)i/((float)VERTEX_COUNT - 1) * SIZE);
                vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * SIZE;
                vertices[vertexPointer*3+1] = 0;
                vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * SIZE;
                normals[vertexPointer*3] = 0;
                normals[vertexPointer*3+1] = 1;
                normals[vertexPointer*3+2] = 0;
                textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1);
                textureCoords[vertexPointer*2+1] = (float)i/((float)VERTEX_COUNT - 1);
                vertexPointer++;
            }
        }
        int pointer = 0;
        for(int gz=0;gz<VERTEX_COUNT-1;gz++){
                for(int gx=0;gx<VERTEX_COUNT-1;gx++){
                        int topLeft = (gz*VERTEX_COUNT)+gx;
                        int topRight = topLeft + 1;
                        int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
                        int bottomRight = bottomLeft + 1;
                        indices[pointer++] = topLeft;
                        indices[pointer++] = bottomLeft;
                        indices[pointer++] = topRight;
                        indices[pointer++] = topRight;
                        indices[pointer++] = bottomLeft;
                        indices[pointer++] = bottomRight;
                }
        }
        
        return MeshUtilities.loadToMesh(vertices, textureCoords, normals, indices);
    }

    public Texture getTextureBackGround() {
        return textureBackGround;
    }

    public Texture getTextureR() {
        return textureR;
    }

    public Texture getTextureG() {
        return textureG;
    }

    public Texture getTextureB() {
        return textureB;
    }

    public Texture getTextureMap() {
        return textureMap;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }
    
    
    
}
