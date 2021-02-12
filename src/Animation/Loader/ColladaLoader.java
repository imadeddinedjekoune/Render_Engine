/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation.Loader;

import Animation.Data.AnimatedModelData;
import Animation.Data.AnimationData;
import Animation.Data.MeshData;
import Animation.Data.SkeletonData;
import Animation.Data.SkinningData;
import Animation.XMLParser.XmlNode;
import Animation.XMLParser.XmlParser;
import Utils.Files.MyFile;

/**
 *
 * @author Imad
 */
public class ColladaLoader {
    public static AnimatedModelData loadColladaModel(MyFile colladaFile, int maxWeights) 
    {
        XmlNode node = XmlParser.loadXmlFile(colladaFile);

        SkinLoader skinLoader = new SkinLoader(node.getChild("library_controllers"), maxWeights);
        SkinningData skinningData = skinLoader.extractSkinData();

        SkeletonLoader jointsLoader = new SkeletonLoader(node.getChild("library_visual_scenes"), skinningData.jointOrder);
        SkeletonData jointsData = jointsLoader.extractBoneData();

        GeometryLoader g = new GeometryLoader(node.getChild("library_geometries"), skinningData.verticesSkinData);
        MeshData meshData = g.extractModelData();

        return new AnimatedModelData(meshData, jointsData);
    }
    
    public static AnimationData loadColladaAnimation(MyFile colladaFile) 
    {
        XmlNode node = XmlParser.loadXmlFile(colladaFile);
        XmlNode animNode = node.getChild("library_animations");
        XmlNode jointsNode = node.getChild("library_visual_scenes");
        AnimationLoader loader = new AnimationLoader(animNode, jointsNode);
        AnimationData animData = loader.extractAnimation();
        return animData;
    }
    
}
