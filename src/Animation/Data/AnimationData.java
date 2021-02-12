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
public class AnimationData 
{
    public final float lengthSeconds;
    public final KeyFrameData[] keyFrames;

    public AnimationData(float lengthSeconds, KeyFrameData[] keyFrames) {
        this.lengthSeconds = lengthSeconds;
        this.keyFrames = keyFrames;
    }
}
