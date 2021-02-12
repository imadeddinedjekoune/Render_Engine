/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation.Data;

import org.lwjgl.util.vector.Matrix4f;

/**
 *
 * @author Imad
 */
public class JointTransformData {
    public final String jointNameId;
	public final Matrix4f jointLocalTransform;

	public JointTransformData(String jointNameId, Matrix4f jointLocalTransform) {
		this.jointNameId = jointNameId;
		this.jointLocalTransform = jointLocalTransform;
	}
}
