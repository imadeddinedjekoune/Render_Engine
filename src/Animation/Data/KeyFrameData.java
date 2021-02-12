/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Imad
 */
public class KeyFrameData {
    public final float time;
	public final List<JointTransformData> jointTransforms = new ArrayList<JointTransformData>();
	
	public KeyFrameData(float time){
		this.time = time;
	}
	
	public void addJointTransform(JointTransformData transform){
		jointTransforms.add(transform);
	}
}
