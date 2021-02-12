/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Imad
 */
public class WritingPanel extends JPanel 
{
    private JLabel text ;
    public WritingPanel (int width,int lenth, String text)
    {
        //setBackground(Color.red);
        this.text = new JLabel("64.2");
        setLayout(new BorderLayout());
        add(this.text , BorderLayout.CENTER);
        setSize(width,lenth);
    }
    public void saveImage(String name,String type)
    {
        BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
	Graphics2D g2 = image.createGraphics();
	paint(g2);
	try{
		ImageIO.write(image, type, new File(name+"."+type));
	} catch (Exception e) {
		e.printStackTrace();
	}
    }
}
