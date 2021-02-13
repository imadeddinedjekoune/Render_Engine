package Animation.KERNAL;

import Component.Mesh.Mesh;
import Component.Mesh.Texture;
import org.lwjgl.util.vector.Matrix4f;


public class AnimatedModel 
{
    private Mesh mesh ;
    private Texture texture;

    private final Joint rootJoint;
    private final int jointCount;

    private final Animator animator;


    public AnimatedModel(Mesh model, Texture texture, Joint rootJoint, int jointCount) {
            this.mesh = model;
            this.texture = texture;
            this.rootJoint = rootJoint;
            this.jointCount = jointCount;
            this.animator = new Animator(this);
            rootJoint.calcInverseBindTransform(new Matrix4f());
    }

    public Mesh getModel() {
            return mesh;
    }

    public Texture getTexture() {
            return texture;
    }

    public Joint getRootJoint() {
            return rootJoint;
    }

    public Animator getAnimator() {
        return animator;
    }


    public void doAnimation(Animation animation) {
            animator.doAnimation(animation);
    }

    public void update() {
            animator.update();
    }

    public Matrix4f[] getJointTransforms() {
            Matrix4f[] jointMatrices = new Matrix4f[jointCount];
            addJointsToArray(rootJoint, jointMatrices);
            return jointMatrices;
    }

    private void addJointsToArray(Joint headJoint, Matrix4f[] jointMatrices) {
            jointMatrices[headJoint.index] = headJoint.getAnimatedTransform();
            for (Joint childJoint : headJoint.children) {
                    addJointsToArray(childJoint, jointMatrices);
            }
    }
    
    public void aff()
    {
        rootJoint.aff();
    }

}
