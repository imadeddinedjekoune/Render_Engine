/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation.Data;

import Animation.Data.MeshData;
import Animation.Data.SkeletonData;

/**
 *
 * @author Imad
 */
public class AnimatedModelData 
{
    private final SkeletonData joints;
    private final MeshData mesh;

    public AnimatedModelData(MeshData mesh, SkeletonData joints){
            this.joints = joints;
            this.mesh = mesh;
    }

    public SkeletonData getJointsData(){
            return joints;
    }

    public MeshData getMeshData(){
            return mesh;
    }
}
