/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation.Loader;

import Animation.Data.AnimationData;
import Animation.Data.JointTransformData;
import Animation.Data.KeyFrameData;
import Animation.KERNAL.Animation;
import Animation.KERNAL.JointTransform;
import Animation.KERNAL.KeyFrame;
import Animation.XMLParser.XmlNode;
import Utils.Files.MyFile;
import Utils.Math.Quaternion;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Imad
 */
public class AnimationLoader {
    
    public static Animation loadAnimation(MyFile colladaFile) {
        AnimationData animationData = ColladaLoader.loadColladaAnimation(colladaFile);
        KeyFrame[] frames = new KeyFrame[animationData.keyFrames.length];
        for (int i = 0; i < frames.length; i++) {
                frames[i] = createKeyFrame(animationData.keyFrames[i]);
        }
        return new Animation(animationData.lengthSeconds, frames);
    }

	
	private static KeyFrame createKeyFrame(KeyFrameData data) {
		Map<String, JointTransform> map = new HashMap<String, JointTransform>();
		for (JointTransformData jointData : data.jointTransforms) {
			JointTransform jointTransform = createTransform(jointData);
			map.put(jointData.jointNameId, jointTransform);
		}
		return new KeyFrame(data.time, map);
	}

	
	private static JointTransform createTransform(JointTransformData data) {
		Matrix4f mat = data.jointLocalTransform;
		Vector3f translation = new Vector3f(mat.m30, mat.m31, mat.m32);
		Quaternion rotation = Quaternion.fromMatrix(mat);
		return new JointTransform(translation, rotation);
	}
        
        private static final Matrix4f CORRECTION = new Matrix4f().rotate((float) Math.toRadians(-90), new Vector3f(1, 0, 0));
	
	private XmlNode animationData;
	private XmlNode jointHierarchy;
	
	public AnimationLoader(XmlNode animationData, XmlNode jointHierarchy){
		this.animationData = animationData;
		this.jointHierarchy = jointHierarchy;
	}
	
	public AnimationData extractAnimation(){
		String rootNode = findRootJointName();
		float[] times = getKeyTimes();
		float duration = times[times.length-1];
		KeyFrameData[] keyFrames = initKeyFrames(times);
		List<XmlNode> animationNodes = animationData.getChildren("animation");
		for(XmlNode jointNode : animationNodes){
			loadJointTransforms(keyFrames, jointNode, rootNode);
		}
		return new AnimationData(duration, keyFrames);
	}
	
	private float[] getKeyTimes(){
		XmlNode timeData = animationData.getChild("animation").getChild("source").getChild("float_array");
		String[] rawTimes = timeData.getData().split(" ");
		float[] times = new float[rawTimes.length];
		for(int i=0;i<times.length;i++){
			times[i] = Float.parseFloat(rawTimes[i]);
		}
		return times;
	}
	
	private KeyFrameData[] initKeyFrames(float[] times){
		KeyFrameData[] frames = new KeyFrameData[times.length];
		for(int i=0;i<frames.length;i++){
			frames[i] = new KeyFrameData(times[i]);
		}
		return frames;
	}
	
	private void loadJointTransforms(KeyFrameData[] frames, XmlNode jointData, String rootNodeId){
		String jointNameId = getJointName(jointData);
		String dataId = getDataId(jointData);
		XmlNode transformData = jointData.getChildWithAttribute("source", "id", dataId);
		String[] rawData = transformData.getChild("float_array").getData().split(" ");
		processTransforms(jointNameId, rawData, frames, jointNameId.equals(rootNodeId));
	}
	
	private String getDataId(XmlNode jointData){
		XmlNode node = jointData.getChild("sampler").getChildWithAttribute("input", "semantic", "OUTPUT");
		return node.getAttribute("source").substring(1);
	}
	
	private String getJointName(XmlNode jointData){
		XmlNode channelNode = jointData.getChild("channel");
		String data = channelNode.getAttribute("target");
		return data.split("/")[0];
	}
	
	private void processTransforms(String jointName, String[] rawData, KeyFrameData[] keyFrames, boolean root){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		float[] matrixData = new float[16];
		for(int i=0;i<keyFrames.length;i++){
			for(int j=0;j<16;j++){
				matrixData[j] = Float.parseFloat(rawData[i*16 + j]);
			}
			buffer.clear();
			buffer.put(matrixData);
			buffer.flip();
			Matrix4f transform = new Matrix4f();
			transform.load(buffer);
			transform.transpose();
			if(root){
				//because up axis in Blender is different to up axis in game
				Matrix4f.mul(CORRECTION, transform, transform);
			}
			keyFrames[i].addJointTransform(new JointTransformData(jointName, transform));
		}
	}
	
	private String findRootJointName(){
		XmlNode skeleton = jointHierarchy.getChild("visual_scene").getChildWithAttribute("node", "id", "Armature");
		return skeleton.getChild("node").getAttribute("id");
	}

}
