/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Mesh;

/**
 *
 * @author Imad
 */
public class MeshGenerator {
    public static float cube_normals [] = {
         0.0f,  0.0f, -1.0f,
          0.0f,  0.0f, -1.0f,
          0.0f,  0.0f, -1.0f,
          0.0f,  0.0f, -1.0f,
          0.0f,  0.0f, -1.0f,
          0.0f,  0.0f, -1.0f,

          0.0f,  0.0f,  1.0f,
          0.0f,  0.0f,  1.0f,
          0.0f,  0.0f,  1.0f,
          0.0f,  0.0f,  1.0f,
          0.0f,  0.0f,  1.0f,
          0.0f,  0.0f,  1.0f,

         -1.0f,  0.0f,  0.0f,
         -1.0f,  0.0f,  0.0f,
         -1.0f,  0.0f,  0.0f,
         -1.0f,  0.0f,  0.0f,
         -1.0f,  0.0f,  0.0f,
         -1.0f,  0.0f,  0.0f,

          1.0f,  0.0f,  0.0f,
          1.0f,  0.0f,  0.0f,
          1.0f,  0.0f,  0.0f,
          1.0f,  0.0f,  0.0f,
          1.0f,  0.0f,  0.0f,
          1.0f,  0.0f,  0.0f,

          0.0f, -1.0f,  0.0f,
          0.0f, -1.0f,  0.0f,
          0.0f, -1.0f,  0.0f,
          0.0f, -1.0f,  0.0f,
          0.0f, -1.0f,  0.0f,
          0.0f, -1.0f,  0.0f,

          0.0f,  1.0f,  0.0f,
          0.0f,  1.0f,  0.0f,
          0.0f,  1.0f,  0.0f,
          0.0f,  1.0f,  0.0f,
          0.0f,  1.0f,  0.0f,
          0.0f,  1.0f,  0.0f
        };
        
        public static float[] cube_position = {	
         -0.5f, -0.5f, -0.5f,
         0.5f, -0.5f, -0.5f,
         0.5f,  0.5f, -0.5f,
         0.5f,  0.5f, -0.5f,
        -0.5f,  0.5f, -0.5f,
        -0.5f, -0.5f, -0.5f,

        -0.5f, -0.5f,  0.5f,
         0.5f, -0.5f,  0.5f,
         0.5f,  0.5f,  0.5f,
         0.5f,  0.5f,  0.5f,
        -0.5f,  0.5f,  0.5f,
        -0.5f, -0.5f,  0.5f,

        -0.5f,  0.5f,  0.5f,
        -0.5f,  0.5f, -0.5f,
        -0.5f, -0.5f, -0.5f,
        -0.5f, -0.5f, -0.5f,
        -0.5f, -0.5f,  0.5f,
        -0.5f,  0.5f,  0.5f,

         0.5f,  0.5f,  0.5f,
         0.5f,  0.5f, -0.5f,
         0.5f, -0.5f, -0.5f,
         0.5f, -0.5f, -0.5f,
         0.5f, -0.5f,  0.5f,
         0.5f,  0.5f,  0.5f,

        -0.5f, -0.5f, -0.5f,
         0.5f, -0.5f, -0.5f,
         0.5f, -0.5f,  0.5f,
         0.5f, -0.5f,  0.5f,
        -0.5f, -0.5f,  0.5f,
        -0.5f, -0.5f, -0.5f,

        -0.5f,  0.5f, -0.5f,
         0.5f,  0.5f, -0.5f,
         0.5f,  0.5f,  0.5f,
         0.5f,  0.5f,  0.5f,
        -0.5f,  0.5f,  0.5f,
        -0.5f,  0.5f, -0.5f
        };
		
        public static float[] cube_textureCoords = {
            0.0f, 0.0f,
            1.0f, 0.0f,
            1.0f, 1.0f,
            1.0f, 1.0f,
            0.0f, 1.0f,
            0.0f, 0.0f,

            0.0f, 0.0f,
            1.0f, 0.0f,
            1.0f, 1.0f,
            1.0f, 1.0f,
            0.0f, 1.0f,
            0.0f, 0.0f,

            1.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 1.0f,
            0.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f,

            1.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 1.0f,
            0.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f,

            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f,
            1.0f, 0.0f,
            0.0f, 0.0f,
            0.0f, 1.0f,

            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f,
            1.0f, 0.0f,
            0.0f, 0.0f,
            0.0f, 1.0f
        };
}
