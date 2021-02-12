/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Swing;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Imad
 */
public class Frame extends JFrame
{
    private static int width = 800  , height = 480  ;
    private WritingPanel p ;
    
    public Frame()
    {
        p = new WritingPanel(100,100,"imad");
        setTitle("Frame");
	setResizable(false);
        setSize(width,height);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2,
                    (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2);
        setLayout(new BorderLayout());
        add(p,BorderLayout.CENTER);
        
        Thread t1 = new Thread(() -> {
            for ( int i = 0 ; i< 1000 ; i++)
            {
                System.out.println("i = "+i);
            }
            p.saveImage("tttt", "jpg");
        });
        t1.start();
        
    }
}
