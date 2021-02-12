/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component.Shader;

import Utils.Math.Matrix4f;
import Utils.Math.Vector3f;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VALIDATE_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glDetachShader;
import static org.lwjgl.opengl.GL20.glGetProgram;
import static org.lwjgl.opengl.GL20.glGetShader;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUniformMatrix4;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;

/**
 *
 * @author Imad
 */
public class Shader 
{
    private int programID ;
    private int VertexShaderID ;
    private int FragmentShaderID ;
    private HashMap <String , Integer > uniforms = new HashMap<String , Integer >();
    
    public Shader(String vertexFile , String fragmentFile)
    {
        VertexShaderID = loadShader(vertexFile, GL_VERTEX_SHADER);
        FragmentShaderID = loadShader(fragmentFile, GL_FRAGMENT_SHADER);
        
        programID = glCreateProgram();
        
        glAttachShader(programID, VertexShaderID);
        glAttachShader(programID, FragmentShaderID);
        glLinkProgram(programID);
        if (glGetProgram(programID,GL_LINK_STATUS) == 0)
        {
            System.err.println(glGetShaderInfoLog(programID, 1024));
            System.exit(1);
        }
        glValidateProgram(programID);
        if (glGetProgram(programID,GL_VALIDATE_STATUS) == 0)
        {
            System.err.println(glGetShaderInfoLog(programID, 1024));
            System.exit(1);
        }
        
    }
    public void start ()
    {
        glUseProgram(programID);
    }
    public void stop ()
    {
        glUseProgram(0);
    }
    private void CleanUp ()
    {
        stop ();
        glDetachShader(programID, VertexShaderID);
        glDetachShader(programID, FragmentShaderID);
        glDeleteShader(VertexShaderID);
        glDeleteShader(FragmentShaderID);
        glDeleteProgram(programID);
    }
    private static int loadShader (String fileName , int type)
    {
        StringBuilder shaderSource = new StringBuilder();
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader("./res/shaders/"+fileName));
            String line ;
            while ((line = reader.readLine()) != null)
            {
                shaderSource.append(line).append("\n");
            }
            
            reader.close();
        } 
        catch (Exception ex) {
            System.out.println(ex);
            System.exit(1);
        }
        
        int shaderID = glCreateShader(type);
        glShaderSource(shaderID,shaderSource);
        glCompileShader(shaderID);
        
        if (glGetShader(shaderID,GL_COMPILE_STATUS) == GL_FALSE)
        {
            
            System.out.println(glGetShaderInfoLog(shaderID, 1024 ));
            System.out.println("COMPILE ERROR IN : "+type+" / Vertex : "+
                    GL_VERTEX_SHADER+" , Fragment : "+GL_FRAGMENT_SHADER);
            System.exit(1);
        }
        return shaderID ;
    }
    
    public void addUniforme (String uniforme)
    {
        int uniforme_Location = glGetUniformLocation(programID,uniforme);
        
        if (uniforme_Location == 0xFFFFFFFF)
        {
            System.err.println("Error : Could Not Found Uniforme Variable : "+uniforme);
            new Exception().printStackTrace();
            System.exit(1);
        }
        uniforms.put(uniforme,uniforme_Location);
    }
    
    public void setUniformi (String uniforme_Name , int Value )
    {
        start();
        glUniform1i(uniforms.get(uniforme_Name),Value);
    }
    public void setUniformf (String uniforme_Name , float Value )
    {
        start();
        glUniform1f(uniforms.get(uniforme_Name),Value);
    }
    public void setUniform (String uniforme_Name , Vector3f Value )
    {
        start();
        glUniform3f(uniforms.get(uniforme_Name),Value.getX(),Value.getY(),Value.getZ());
    }
    
    public void setUniform (String uniforme_Name , Matrix4f value )
    {
        start();
        glUniformMatrix4(uniforms.get(uniforme_Name), true ,Utils.Buffer.Util.store_Matrix4f_In_FloatBuffer(value));
    }
    
    public void setUniformMM (String uniforme_Name , org.lwjgl.util.vector.Matrix4f value[] )
    {
        start();
        glUniformMatrix4(uniforms.get(uniforme_Name), true ,Utils.Buffer.Util.store_Matrix4fT_In_FloatBuffer(value));
    }
}
