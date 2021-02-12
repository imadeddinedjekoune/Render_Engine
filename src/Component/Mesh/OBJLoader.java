/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Mesh;

import Utils.Math.Vector2f;
import Utils.Math.Vector3f;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Imad
 */
public class OBJLoader {
    public static Mesh loadObjModel (String fileName)
    {
        FileReader fr = null ;
        
        try {
            fr = new FileReader(new File("./res/models/"+fileName));
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            System.exit(1);
        }
        
        BufferedReader reader = new BufferedReader(fr);
        String line ;
        
        ArrayList <Vector3f> vertecies = new ArrayList<Vector3f>();
        ArrayList <Vector2f> textureCoord = new ArrayList<Vector2f>();
        ArrayList <Vector3f> normals = new ArrayList<Vector3f>();
        ArrayList <Integer> indices = new ArrayList<Integer>();
        
        
        float textureArr[] = null ;
        float verticesArr[] = null ;
        float normalArr[] = null ;
        int indicesArr[] = null ;
        
        
        try {
            
            while (true)
            {
                line = reader.readLine();
                
                String currentLine [] = line.split(" ");
                if (line.startsWith("v "))
                {
                    Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]),
                            Float.parseFloat(currentLine[2]), 
                            Float.parseFloat(currentLine[3])
                    );
                    vertecies.add(vertex);
                }else
                {
                    if (line.startsWith("vt "))
                    {
                        Vector2f text = new Vector2f(Float.parseFloat(currentLine[1]), 
                                                    Float.parseFloat(currentLine[2])
                        );
                        textureCoord.add(text);
                    }else
                    {
                        if (line.startsWith("vn "))
                        {
                            Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]),
                            Float.parseFloat(currentLine[2]), 
                            Float.parseFloat(currentLine[3])
                            );
                            normals.add(normal);
                        }else
                        {
                            if (line.startsWith("f "))
                            {
                                textureArr = new float[vertecies.size()*2];
                                normalArr = new float[vertecies.size()*3];
                                break ;
                            }
                        }
                    }
                }
            }
            
            
            while (line != null )
            {
                if (!line.startsWith("f "))
                {
                    line = reader.readLine();
                    continue;
                }
                String [] cuurentLine = line.split(" ");
                String [] vertex1 = cuurentLine[1].split("/");
                String [] vertex2 = cuurentLine[2].split("/");
                String [] vertex3 = cuurentLine[3].split("/");
                
                processVertex(vertex1, indices, textureCoord, normals, textureArr,normalArr );
                processVertex(vertex2, indices, textureCoord, normals, textureArr,normalArr );
                processVertex(vertex3, indices, textureCoord, normals, textureArr,normalArr );
                
                line = reader.readLine();
                
            }
            reader.close();
            
        } catch (IOException ex) {
            Logger.getLogger(OBJLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        verticesArr = new float[vertecies.size()*3];
        indicesArr = new int[indices.size()];
        

        int vertexPointer = 0 ;
        
        for (Vector3f vertex : vertecies)
        {
            verticesArr[vertexPointer++] = vertex.getX();
            verticesArr[vertexPointer++] = vertex.getY();
            verticesArr[vertexPointer++] = vertex.getZ();
       }
        
        for ( int i = 0 ; i < indices.size() ; i++ )
        {
            indicesArr[i] = indices.get(i);
        }
        
        return MeshUtilities.loadToMesh(verticesArr,textureArr, normalArr, indicesArr) ;
    }
    
    private static void processVertex (String [] vertexData , ArrayList<Integer> indices ,
            ArrayList<Vector2f> texture , ArrayList<Vector3f> normal , float textureArr []
            , float [] normalArr)
    {
        
        int currentVertexPointer = Integer.parseInt(vertexData[0])-1;
        indices.add(currentVertexPointer);
        
        Vector2f curentTex = texture.get(Integer.parseInt(vertexData[1])-1);
        textureArr[currentVertexPointer*2] = curentTex.getX();
        textureArr[currentVertexPointer*2+1] = 1 - curentTex.getY();
        
        Vector3f currentNorm = normal.get(Integer.parseInt(vertexData[2])-1);
        normalArr[currentVertexPointer*3] = currentNorm.getX();
        normalArr[currentVertexPointer*3+1] = currentNorm.getY();
        normalArr[currentVertexPointer*3+2] = currentNorm.getZ();
    }
    
}
