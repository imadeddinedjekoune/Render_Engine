/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation.Data;

/**
 *
 * @author Imad
 */
public class SkeletonData {
    public final int jointCount;
    public final JointData headJoint;
	
    public SkeletonData(int jointCount, JointData headJoint){
    	this.jointCount = jointCount;
    	this.headJoint = headJoint;
    }
}
