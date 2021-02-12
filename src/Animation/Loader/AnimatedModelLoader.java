/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation.Loader;
import Animation.Data.AnimatedModelData;
import Animation.Data.JointData;
import Animation.Data.MeshData;
import Animation.Data.SkeletonData;
import Animation.KERNAL.AnimatedModel;
import Animation.KERNAL.Joint;
import Animation.XMLParser.*;
import Component.Mesh.Mesh;
import Component.Mesh.MeshUtilities;
import Component.Mesh.Texture;
import Utils.Files.MyFile;
import java.util.List;

/**
 *
 * @author Imad
 */
public class AnimatedModelLoader 
{

	public static AnimatedModel loadEntity(MyFile modelFile, MyFile textureFile) {
		AnimatedModelData entityData = ColladaLoader.loadColladaModel(modelFile, 3);
                Mesh model = createMesh(entityData.getMeshData());
		Texture texture = loadTexture(textureFile);
		SkeletonData skeletonData = entityData.getJointsData();
		Joint headJoint = createJoints(skeletonData.headJoint);
		return new AnimatedModel(model, texture, headJoint, skeletonData.jointCount);
	}


	private static Texture loadTexture(MyFile textureFile) {
            Texture diffuseTexture = new Texture(Texture.loadTexture(textureFile.getName()));
            return diffuseTexture;
	}

	private static Joint createJoints(JointData data) 
        {
            Joint joint = new Joint(data.index, data.nameId, data.bindLocalTransform);
            for (JointData child : data.children) 
            {
                joint.addChild(createJoints(child));
            }
            return joint;
	}

    private static Mesh createMesh(MeshData data) {
        Mesh m = MeshUtilities.load_To_Mesh(data.getVertices(),  data.getTextureCoords(),
                data.getNormals(), data.getJointIds(), data.getVertexWeights(), data.getIndices(), 8);
        
        return m ;
    }


}
