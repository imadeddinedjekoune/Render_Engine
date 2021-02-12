/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation.Data;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.util.vector.Matrix4f;

/**
 *
 * @author Imad
 */
public class JointData {
    public final int index;
    public final String nameId;
    public final Matrix4f bindLocalTransform;

    public final List<JointData> children = new ArrayList<JointData>();

    public JointData(int index, String nameId, Matrix4f bindLocalTransform) {
            this.index = index;
            this.nameId = nameId;
            this.bindLocalTransform = bindLocalTransform;
    }

    public void addChild(JointData child) {
            children.add(child);
    }
}
