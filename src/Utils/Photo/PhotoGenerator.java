/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Photo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Imad
 */
public class PhotoGenerator 
{
    
    
    
    public static BufferedImage intToImage (int numero)
    {
        int buffer ;
        BufferedImage i1;
        
        // Le Premier Chiffre //
        buffer = numero%10 ;
        numero = numero / 10 ;
        i1 = ChiffreToImage(buffer);
        
        while (numero != 0)
        {
            i1 = CombineImages(ChiffreToImage(numero%10), i1);
            numero = numero / 10 ;
        }
        return i1 ;
    }
    
    private static BufferedImage getImage (String path)
    {
        try {
            return  ImageIO.read(new File(path));
        } catch (Exception e) {
            System.err.println("Cant Load Image");
            System.exit(1);
        }
        return null ;
    }
    
    private static void writeImage (BufferedImage bufferedImage,String path)
    {
       File file = new File(path);
        try {
            ImageIO.write(bufferedImage, "PNG", file);
        } catch (IOException ex) {
            Logger.getLogger(PhotoGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static BufferedImage ChiffreToImage (int chiffre)
    {
        BufferedImage img = getImage("./res/image/numeros/tous.png");
        BufferedImage tst = new BufferedImage(getBounds(chiffre)[1]-getBounds(chiffre)[0],
                getBounds(chiffre)[3]-getBounds(chiffre)[2], 2);
        
        for ( int i = getBounds(chiffre)[0] ;i < getBounds(chiffre)[1] ; i++ )
        {
            for ( int j = getBounds(chiffre)[2] ;j < getBounds(chiffre)[3] ; j++ )
            {
                tst.setRGB(i-getBounds(chiffre)[0], j-getBounds(chiffre)[2],img.getRGB(i, j) );
            }
        }
        return  tst ;
    }
    
    public static BufferedImage CombineChiffre(int chiffre1 , int chiffre2)
    {
        BufferedImage _5 = ChiffreToImage(chiffre1);
        BufferedImage _1 = ChiffreToImage(chiffre2);
        
        
        return CombineImages(_5,_1);
    }
    
    public static BufferedImage CombineImages (BufferedImage image1 , BufferedImage image2)
    {
        // create the new image, canvas size is the max. of both image sizes
        int w = image1.getWidth()+ image2.getWidth();
        int h = image1.getHeight();
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        // paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics();
        g.drawImage(image1, 0, 0, null);
        g.drawImage(image2, image1.getWidth(), 0, null);

        g.dispose();

        // Save as new image
        return combined;
    }
    
    private static int [] getBounds (int chiffre)
    {
        int T[] = new int[4];
        int pos = chiffre % 5 ;
        T[0] = 35+250*pos ;
        T[1] = 250+250*pos ;
        if ( chiffre > 4 )
        {
            T[2] = 470 ;
            T[3] = 800 ;
        }else
        {
            T[2] = 75 ;
            T[3] = 400 ;
        }
        return T;
    }
    
}
