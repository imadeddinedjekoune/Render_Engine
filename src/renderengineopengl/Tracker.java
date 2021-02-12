/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderengineopengl;

import GUIRenderer.GUICoordinate;
import GUIRenderer.GUIEngine;
import Utils.Window.Window;

/**
 *
 * @author Imad
 */
public class Tracker {
    public static void tracker_la_fenetre ()
    {
        int cpt = 0 ;
        if (Window.WIDTH != Window.getNewWidth())
        {
            cpt += 1 ;
        }
        {
            cpt += 2 ;
        }
        /*
            cpt = 0 : Aucun Changement 
            cpt = 1 : width a chnager
            cpt = 2 : height a chnager
            cpt = 3 : width et height ont chnager
        */
        switch (cpt)
        {
            case 1 :
                for (int i = 0 ; i < GUIEngine.coord.size() ; i++ )
                {
                    int old_x = GUIEngine.coord.get(i).getX();
                    int old_w = GUIEngine.coord.get(i).getWidth();
                    
                    GUIEngine.coord.get(i).setX(old_x*Window.getNewWidth()/Window.WIDTH); // nouveau x
                    GUIEngine.coord.get(i).setWidth(old_w*Window.getNewWidth()/Window.WIDTH); // nouveau w
                    
                }
                Window.WIDTH = Window.getNewWidth() ;
                break ;
            case 2 :
                for (int i = 0 ; i < GUIEngine.coord.size() ; i++ )
                {
                    int old_y = GUIEngine.coord.get(i).getY();
                    int old_h = GUIEngine.coord.get(i).getHeight();
                    
                    GUIEngine.coord.get(i).setY(old_y*Window.getNewHeight()/Window.HEIGHT); // nouveau y
                    GUIEngine.coord.get(i).setHeight(old_h*Window.getNewHeight()/Window.HEIGHT); // nouveau h 
                    
                }
                Window.HEIGHT = Window.getNewHeight() ;
                break ;
            case 3 :
                for (int i = 0 ; i < GUIEngine.coord.size() ; i++ )
                {
                    int old_x = GUIEngine.coord.get(i).getX();
                    int old_y = GUIEngine.coord.get(i).getY();
                    int old_w = GUIEngine.coord.get(i).getWidth();
                    int old_h = GUIEngine.coord.get(i).getHeight();
                    
                    GUIEngine.coord.get(i).setX(old_x*Window.getNewWidth()/Window.WIDTH); // nouveau x
                    GUIEngine.coord.get(i).setY(old_y*Window.getNewHeight()/Window.HEIGHT); // nouveau y
                    GUIEngine.coord.get(i).setWidth(old_w*Window.getNewWidth()/Window.WIDTH); // nouveau w
                    GUIEngine.coord.get(i).setHeight(old_h*Window.getNewHeight()/Window.HEIGHT); // nouveau h 
                    
                }
                Window.WIDTH = Window.getNewWidth() ;
                Window.HEIGHT = Window.getNewHeight() ;
                break ;
        }
    }
}
