/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Mesh;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import org.lwjgl.opengl.GL20;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
 *
 * @author Imad
 */
public class Mesh {
    private int vaoID , sizeVerts ;
    private ArrayList<Integer> vbos = new ArrayList<Integer>();

    
    public Mesh(int vaoID, int sizeVerts) 
    {
        this.vaoID = vaoID;
        this.sizeVerts = sizeVerts;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getSizeVerts() {
        return sizeVerts;
    }

    public ArrayList<Integer> getVbos() {
        return vbos;
    }

    public void setVbos(ArrayList<Integer> vbos) {
        this.vbos = vbos;
    }
    
    
    
}
