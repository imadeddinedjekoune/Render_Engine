/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIRenderer;

import javax.swing.JOptionPane;

/**
 *
 * @author Imad
 */
public class Actions {
    
    static void doAction (int num_action)
    {
        switch (num_action)
        {
            case 0 :
                //JOptionPane.showMessageDialog(null, "Byee Duh", "Exit", 1);
                System.exit(0);
                break ;
            
            case 1 :
                if (!GUIEngine.guis.get(2).isActive())
                    GUIEngine.guis.get(2).setActive(true);
                else
                    GUIEngine.guis.get(2).setActive(false);
                break ;
            
        }
    }
}
