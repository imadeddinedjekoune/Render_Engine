/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation.Data;

import java.util.List;

/**
 *
 * @author Imad
 */
public class SkinningData {
	
    public final List<String> jointOrder;
    public final List<VertexSkinData> verticesSkinData;

    public SkinningData(List<String> jointOrder, List<VertexSkinData> verticesSkinData)
    {
        this.jointOrder = jointOrder;
        this.verticesSkinData = verticesSkinData;
    }


}
